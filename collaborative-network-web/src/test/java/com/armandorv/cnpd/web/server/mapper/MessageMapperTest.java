package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.MessageInfo;

public class MessageMapperTest
{
   private static final String userId = ResourceBundle.getBundle("test").getString("test.user");

   private static Mapper<Message, MessageInfo> messageMapper = null;

   private static Message messageToMap = null;

   @BeforeClass
   public static void beforeClass()
   {
      messageMapper = new MessageMapper();

      IContactsManager manager = new EJBProducer().produceContactsManager();

      messageToMap = manager.getMessages(Long.parseLong(userId)).iterator().next();;
   }

   @Before
   public void before()
   {
      Assert.assertNotNull(messageMapper);
      Assert.assertNotNull(messageToMap);
   }

   @Test
   public void testMap()
   {
      MessageInfo info = messageMapper.map(messageToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(messageToMap.getContent(), info.getContent());
      Assert.assertEquals(messageToMap.getDate(), info.getDate());
      Assert.assertEquals(messageToMap.getAddressee().getGoogleAccount().getAccount(), info.getTo());

      Assert.assertEquals(messageToMap.getSender().getGoogleAccount().getAccount(), info.getSender());
      Assert.assertEquals(messageToMap.getAddressee().getGoogleAccount().getAccount(), info.getTo());
   }

}