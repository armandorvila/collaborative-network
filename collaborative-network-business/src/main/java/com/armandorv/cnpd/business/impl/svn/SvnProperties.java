package com.armandorv.cnpd.business.impl.svn;

import java.util.ResourceBundle;

/**
 * Class that expose properties for SVN.
 * 
 * @author armandorv
 * 
 */
public class SvnProperties
{

   private static final String SVN_URL_PROPERTY_NAME = "svn.url";

   private static final String SVN_URL_PROPERTY_USER_NAME = "svn.username";

   private static final String SVN_URL_PROPERTY_PASSWORD = "svn.password";

   /**
    * Resources bundle point to svn.properties
    */
   private static ResourceBundle bundle = ResourceBundle.getBundle("svn");

   /* *************** Statics methods to resources access **************** */

   public static String URL()
   {
      return bundle.getString(SVN_URL_PROPERTY_NAME);
   }

   public static String USERNAME()
   {
      return bundle.getString(SVN_URL_PROPERTY_USER_NAME);
   }

   public static String PASSWORD()
   {
      return bundle.getString(SVN_URL_PROPERTY_PASSWORD);
   }

}
