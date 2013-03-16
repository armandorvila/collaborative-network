package com.armandorv.cnpd.client.application;

import java.util.Hashtable;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

/**
 * Get a proxy for a EJB given a name and a type. EJB will be remote or local in
 * function of final name that is retrieved from the ejb-names.properties file.
 * 
 * @author armandorv
 * 
 */
public class JNDIHelper<T>
{

   private static Logger log = Logger.getLogger(JNDIHelper.class);

   private static ResourceBundle resources = ResourceBundle
         .getBundle("ejb-names");

   public static final String USERS_MANAGER_NAME = resources
         .getString("ejb.users.name");

   public static final String CONTACTS_MANAGER_NAME = resources
         .getString("ejb.contacts.name");

   public static final String PROJECTS_MANAGER_NAME = resources
         .getString("ejb.projects.name");

   public static final String RESOURCES_MANAGER_NAME = resources
         .getString("ejb.resources.name");

   private final String URL_PKG_PREFIXES = resources.getString("java.naming.factory.url.pkgs");

   private String jndiName;

   public JNDIHelper(String jndiName)
   {
      this.jndiName = jndiName;
   }

   public T doLookup()
   {

      try
      {

         final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();

         jndiProperties.put(Context.URL_PKG_PREFIXES,
               URL_PKG_PREFIXES);

         final Context context = new InitialContext(jndiProperties);

         Object object = context.lookup(jndiName);

         @SuppressWarnings("unchecked")
         T result = (T) object;

         return result;

      }
      catch (Exception e)
      {

         log.error("Error obtain remote ejb for " + jndiName, e);

         throw new RuntimeException("Error obtain remote ejb for " + jndiName, e);
      }

   }

}
