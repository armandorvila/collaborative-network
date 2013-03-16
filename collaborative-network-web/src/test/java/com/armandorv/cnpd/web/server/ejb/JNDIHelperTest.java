package com.armandorv.cnpd.web.server.ejb;

import org.junit.Assert;
import org.junit.Test;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.web.server.ejb.JNDIHelper;

public class JNDIHelperTest
{
   @Test
   public void testDoLookupUsersManager()
   {
      JNDIHelper<IUsersManager> helper = new JNDIHelper<IUsersManager>(JNDIHelper.USERS_MANAGER_NAME);
      Assert.assertNotNull(helper);

      IUsersManager manager = helper.doLookup();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testDoLookupContactsManager()
   {
      JNDIHelper<IContactsManager> helper = new JNDIHelper<IContactsManager>(JNDIHelper.CONTACTS_MANAGER_NAME);
      Assert.assertNotNull(helper);

      IContactsManager manager = helper.doLookup();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testDoLookupProjectsManager()
   {
      JNDIHelper<IProjectsManager> helper = new JNDIHelper<IProjectsManager>(JNDIHelper.PROJECTS_MANAGER_NAME);
      Assert.assertNotNull(helper);

      IProjectsManager manager = helper.doLookup();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testDoLookupResourcesManager()
   {
      JNDIHelper<IResourcesManager> helper = new JNDIHelper<IResourcesManager>(JNDIHelper.RESOURCES_MANAGER_NAME);
      Assert.assertNotNull(helper);

      IResourcesManager manager = helper.doLookup();
      Assert.assertNotNull(manager);
   }
}