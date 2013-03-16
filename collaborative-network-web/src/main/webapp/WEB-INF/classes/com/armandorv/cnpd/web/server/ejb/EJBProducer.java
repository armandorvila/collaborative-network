package com.armandorv.cnpd.web.server.ejb;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;

/**
 * Factory that produces instances to work against the EJB facades of business
 * tier. This factory is used by CDI container to inject EJB proxies into web
 * tier CDI contextual instances.
 * 
 * @author armandorv
 * 
 */
@ApplicationScoped
public class EJBProducer
{
   private JNDIHelper<IUsersManager> usersJndiHelper = new JNDIHelper<IUsersManager>(JNDIHelper.USERS_MANAGER_NAME);

   private JNDIHelper<IContactsManager> contactsJndiHelper = new JNDIHelper<IContactsManager>(
         JNDIHelper.CONTACTS_MANAGER_NAME);

   private JNDIHelper<IProjectsManager> projectsJndiHelper = new JNDIHelper<IProjectsManager>(
         JNDIHelper.PROJECTS_MANAGER_NAME);

   private JNDIHelper<IResourcesManager> resourcesJndiHelper = new JNDIHelper<IResourcesManager>(
         JNDIHelper.RESOURCES_MANAGER_NAME);

   /* ************* Produces annotated methods ************** */

   @Produces
   public IUsersManager produceUsersManager()
   {
      return usersJndiHelper.doLookup();
   }

   @Produces
   public IContactsManager produceContactsManager()
   {
      return contactsJndiHelper.doLookup();
   }

   @Produces
   public IProjectsManager produceProjectsManager()
   {
      return projectsJndiHelper.doLookup();
   }

   @Produces
   public IResourcesManager produceResourcesManager()
   {
      return resourcesJndiHelper.doLookup();
   }
}