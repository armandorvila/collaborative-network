package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;

public class NotificationMapperTest
{

   private static final String userId = ResourceBundle.getBundle("test").getString("test.user");

   private static Mapper<Notification, NotificationInfo> notificationMapper;

   private static Notification notificationToMap = new Notification();

   @BeforeClass
   public static void bforeClass()
   {

      notificationMapper = new NotificationMapper();

      notificationToMap = new EJBProducer().produceUsersManager().getNotifications(Long.parseLong(userId)).iterator()
            .next();

   }

   @Before
   public void before()
   {
      Assert.assertNotNull(notificationMapper);
      Assert.assertNotNull(notificationToMap);
   }

   @Test
   public void testMap()
   {

      NotificationInfo info = notificationMapper.map(notificationToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(notificationToMap.getMessage(), info.getMessage());
      Assert.assertEquals(notificationToMap.getDate(), info.getDate());
      Assert.assertEquals(notificationToMap.getThirdPartId(), new Long(info.getObject()));

   }

}
