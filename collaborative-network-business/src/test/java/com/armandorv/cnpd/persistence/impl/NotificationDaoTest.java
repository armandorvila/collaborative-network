package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.persistence.INotificationDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class NotificationDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(NotificationDaoTest.class);

   @Inject
   private INotificationDao notificationDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {

      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      Notification newNotification = builder.buildNotification();
      Assert.assertNotNull(newNotification);

      notificationDao.persist(newNotification);
      Assert.assertNotNull(newNotification);
      Assert.assertNotNull(newNotification.getId());

      List<Notification> allNow = notificationDao.findAll();
      Assert.assertNotNull(allNow);

      Assert.assertEquals(all.size(), allNow.size() - 1);

   }

   @Test
   public void testFindAll()
   {

      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      log.info("Found " + all.size() + "notifications.");
   }

   @Test
   public void testFindById()
   {
      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Notification notification = all.get(0);
         Assert.assertNotNull(notification);

         Notification found = notificationDao.findById(notification.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(notification, found);

      }
      else
      {
         fail("0 notification found.");
      }
   }

   @Test
   public void testUpdate()
   {

      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String message = "Changed";

         Notification notification = all.get(0);
         Assert.assertNotNull(notification);

         notification.setMessage(message);

         Notification found = notificationDao.findById(notification.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(message, found.getMessage());

      }
      else
      {
         fail("There is no notifications.");
      }
   }

   @Test
   public void testRemove()
   {
      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Notification notification = all.get(0);
         Assert.assertNotNull(notification);

         notificationDao.remove(notification);

         List<Notification> allNow = notificationDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
      {
         fail("There is no notifications.");
      }
   }

   @Test
   public void testRefresh()
   {

      List<Notification> all = notificationDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Notification first = all.get(0);
         Assert.assertNotNull(first);

         String oldMessage = first.getMessage();
         String newMessage = "Temporary name";

         first.setMessage(newMessage);
         notificationDao.refresh(first);

         Assert.assertNotSame(newMessage, first.getMessage());
         Assert.assertEquals(oldMessage, first.getMessage());

         log.info("Notification after refresh Test :" + first);

      }
      else
      {
         fail(" !!! There is not notifications into the bbdd !!!");
      }
   }

}
