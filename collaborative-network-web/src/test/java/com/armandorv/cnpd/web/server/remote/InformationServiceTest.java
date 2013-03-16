package com.armandorv.cnpd.web.server.remote;

import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class InformationServiceTest extends ArquillianSupport
{
   private static Long userId = Long.parseLong(ResourceBundle.getBundle("test").getString("test.user"));

   private static String username = ResourceBundle.getBundle("test").getString("test.username");

   @Inject
   private InformationService informationService;

   @Inject
   private ContactsService contactsService;

   @Test
   public void testGetNotifications()
   {

      List<NotificationInfo> notifications = informationService.getNotifications(userId);
      Assert.assertNotNull(notifications);
      Assert.assertFalse(notifications.isEmpty());
   }

   @Test
   public void testMarkNotified()
   {
      List<NotificationInfo> notifications = informationService.getNotifications(userId);
      Assert.assertNotNull(notifications);
      Assert.assertFalse(notifications.isEmpty());

      Assert.assertTrue(informationService.markNotified(notifications.get(0)));

      List<NotificationInfo> notificationsNow = informationService.getNotifications(userId);
      Assert.assertNotNull(notificationsNow);
      Assert.assertFalse(notificationsNow.isEmpty());
      Assert.assertFalse(notificationsNow.get(0).isNew());

   }

   @Test
   public void testSendMessage()
   {
      List<ContactInfo> contacts = contactsService.getContacts(userId);
      Assert.assertNotNull(contacts);
      Assert.assertFalse(contacts.isEmpty());

      List<MessageInfo> messages = informationService.getMessages(userId);
      Assert.assertNotNull(messages);

      Assert.assertTrue(informationService.sendMessage(contacts.get(0).getGmailAddress(), username, "testing", false));

      List<MessageInfo> messagesNow = informationService.getMessages(userId);
      Assert.assertNotNull(messagesNow);
      Assert.assertFalse(messagesNow.isEmpty());
      Assert.assertEquals(messages.size() + 1, messagesNow.size());
   }

   @Test
   public void testGetMessages()
   {
      List<MessageInfo> messages = informationService.getMessages(userId);
      Assert.assertNotNull(messages);
      Assert.assertFalse(messages.isEmpty());
   }

   @Test
   public void testDeleteMessage()
   {
      List<MessageInfo> messages = informationService.getMessages(userId);
      Assert.assertNotNull(messages);
      Assert.assertFalse(messages.isEmpty());

      Assert.assertTrue(informationService.deleteMessage(messages.get(0).getId()));

      List<MessageInfo> messagesNow = informationService.getMessages(userId);
      Assert.assertNotNull(messagesNow);
      Assert.assertFalse(messagesNow.isEmpty());
      Assert.assertEquals(messages.size() - 1, messagesNow.size());
   }

   @Test
   public void testMarkMessageAsRead()
   {
      List<MessageInfo> messages = informationService.getMessages(userId);
      Assert.assertNotNull(messages);
      Assert.assertFalse(messages.isEmpty());

      Assert.assertTrue(informationService.markMessageAsRead(messages.get(0).getId()));

      List<MessageInfo> messagesNow = informationService.getMessages(userId);
      Assert.assertNotNull(messagesNow);
      Assert.assertFalse(messagesNow.isEmpty());
      Assert.assertTrue(messagesNow.get(0).isRead());
   }

   @Test
   public void testRetrieveAllKnowledgeAreas()
   {

      List<IdNameGenericInfo> kas = informationService.retrieveAllKnowledgeAreas();
      Assert.assertNotNull(kas);
      Assert.assertFalse(kas.isEmpty());
   }

}