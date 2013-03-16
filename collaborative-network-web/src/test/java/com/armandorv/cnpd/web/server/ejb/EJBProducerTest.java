package com.armandorv.cnpd.web.server.ejb;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class EJBProducerTest extends ArquillianSupport
{
   @Inject
   private EJBProducer producer;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IContactsManager contactsManager;

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private IResourcesManager resourcesManager;

   @Test
   public void testProduceUsersManager()
   {
      Assert.assertNotNull(usersManager);

      IUsersManager manager = producer.produceUsersManager();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testProduceContactsManager()
   {
      Assert.assertNotNull(contactsManager);

      IContactsManager manager = producer.produceContactsManager();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testProduceProjectsManager()
   {
      Assert.assertNotNull(projectsManager);

      IProjectsManager manager = producer.produceProjectsManager();
      Assert.assertNotNull(manager);
   }

   @Test
   public void testProduceResourcesManager()
   {
      Assert.assertNotNull(resourcesManager);

      IResourcesManager manager = producer.produceResourcesManager();
      Assert.assertNotNull(manager);
   }
}