package com.armandorv.cnpd.web.server.remote;

import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.qualifiers.ContactMinus;
import com.armandorv.cnpd.web.shared.qualifiers.ContactPlus;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.LoadingService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class LoadingServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(LoadingServiceTest.class);

   private final String username = ResourceBundle.getBundle("test").getString("test.username");

   private static ContactInfo connected = null;

   private static ContactInfo disconnected = null;

   @Inject
   private LoadingService loadingService;

   @Inject
   private ContactsService contactsService;

   @Test
   public void testConnectCurrentUser() throws InterruptedException
   {
      Assert.assertTrue(loadingService.connectCurrentUser(username));

      Assert.assertNotNull(connected);
      Assert.assertEquals(username, connected.getGmailAddress());

      connected = null;
   }

   @Test
   public void testDisconnectCurrentUser()
   {
      Assert.assertTrue(loadingService.disconnectCurrentUser(username));
      Assert.assertNotNull(disconnected);
      Assert.assertEquals(username, disconnected.getGmailAddress());

      disconnected = null;
   }

   @Test
   public void testGetConnectedContacts()
   {
      Assert.assertTrue(loadingService.connectCurrentUser(username));

      List<ContactInfo> connectedCotnacts = loadingService.getConnectedContacts(username);
      Assert.assertNotNull(connectedCotnacts);

      Assert.assertTrue(connectedCotnacts.isEmpty());

      List<ContactInfo> allContacts = contactsService.getContactsByUser(username);
      Assert.assertNotNull(allContacts);
      Assert.assertFalse(allContacts.isEmpty());

      Assert.assertTrue(loadingService.connectCurrentUser(allContacts.get(0).getGmailAddress()));

      List<ContactInfo> connectedCotnactsNow = loadingService.getConnectedContacts(username);
      Assert.assertNotNull(connectedCotnactsNow);

      Assert.assertFalse(connectedCotnactsNow.isEmpty());
      Assert.assertTrue(connectedCotnactsNow.contains(allContacts.get(0)));
   }

   @Test
   public void testGetAppScoppedService()
   {
      LoadingService service = LoadingServiceImpl.getAppScoppedService();
      Assert.assertNotNull(service);
   }

   public void observeConnection(@Observes @ContactPlus ContactInfo contact)
   {
      log.info("Event plus recived " + contact);
      connected = contact;
   }

   public void observeDisconnection(@Observes @ContactMinus ContactInfo contact)
   {
      log.info("Event contact minus recived " + contact);
      disconnected = contact;
   }

}