package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.persistence.IMessageDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class MessageDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(MessageDaoTest.class);

   @Inject
   private IMessageDao messageDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {

      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      Message newMessage = builder.buildMessage();
      messageDao.persist(newMessage);

      Assert.assertNotNull(newMessage);
      Assert.assertNotNull(newMessage.getId());

      List<Message> allNow = messageDao.findAll();
      Assert.assertNotNull(allNow);
      Assert.assertEquals(all.size() + 1, allNow.size());

   }

   @Test
   public void testFindById()
   {
      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() == 0)
         fail("no messages");
      else
      {
         Message message = all.get(0);
         Assert.assertNotNull("Message was null", message);
         log.info("Fisrst message is :" + message);
      }
   }

   @Test
   public void testFindAll()
   {
      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() == 0)
         fail("no messages");
      else
      {
         log.info("All messages are:" + all);
      }
   }

   @Test
   public void testUpdate()
   {

      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String content = "Changed";

         Message message = all.get(0);
         Assert.assertNotNull(message);

         message.setContent(content);

         Message found = messageDao.findById(message.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(message, found);
         Assert.assertEquals(content, found.getContent());

      }
      else
      {
         fail("There is no messages.");
      }
   }

   @Test
   public void testRemove()
   {

      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Message message = all.get(0);
         Assert.assertNotNull(message);

         messageDao.remove(message);

         List<Message> allNow = messageDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
      {
         fail("There is no messages.");
      }
   }

   @Test
   public void testRefresh()
   {

      List<Message> all = messageDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Message first = all.get(0);
         Assert.assertNotNull(first);

         String oldContent = first.getContent();
         String newContent = "Temporary content";

         first.setContent(newContent);
         messageDao.refresh(first);

         Assert.assertNotSame(newContent, first.getContent());
         Assert.assertEquals(oldContent, first.getContent());

         log.info("Message after refresh Test :" + first);

      }
      else
      {
         fail(" !!! There is not messages into the bbdd !!!");
      }
   }

}
