package com.armandorv.cnpd.web.server.remote;

import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ContactsServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(ContactsServiceTest.class);

   private static String username = ResourceBundle.getBundle("test").getString("test.username");

   private static Long userId = Long.parseLong(ResourceBundle.getBundle("test").getString("test.user"));

   @Inject
   private ContactsService contactsService;

   @Inject
   private IUsersManager usersManager;

   @Before
   public void before()
   {
      Assert.assertNotNull(username);
      Assert.assertNotNull(userId);
   }

   @Test
   public void testGetContacts()
   {
      List<ContactInfo> contacts = contactsService.getContacts(userId);
      Assert.assertNotNull(contacts);

      log.info("Found " + contacts.size() + " contacts for " + userId);

   }

   @Test
   public void testGetContactsByUser()
   {
      List<ContactInfo> contacts = contactsService.getContactsByUser(username);
      Assert.assertNotNull(contacts);

      log.info("Found " + contacts.size() + " contacts for " + userId);
   }

   @Test
   public void testGetContact()
   {
      List<ContactInfo> contacts = contactsService.getContactsByUser(username);
      Assert.assertNotNull(contacts);
      Assert.assertFalse(contacts.isEmpty());

      ContactInfo contact = contactsService.getContact(contacts.get(0).getId());
      Assert.assertNotNull(contact);
      Assert.assertEquals(contacts.get(0).getId(), contact.getId());
   }

   @Test
   public void testAddAsContact()
   {
      List<User> all = usersManager.ListAllUser();

      List<ContactInfo> contacts = contactsService.getContacts(userId);

      List<ContactInfo> requests = contactsService.getContactRequests(userId);

      if (requests.isEmpty())
      {
         long rid = -1;
         int i = 0;

         while (i < all.size() && rid == -1)
         {
            ContactInfo c = new ContactInfo();
            c.setId(all.get(i).getId());

            if (!contacts.contains(c))
               rid = all.get(i).getId();

            i++;
         }
         contactsService.sendContactRequest(userId, rid);
         requests = contactsService.getContactRequests(userId);
      }

      Assert.assertFalse(requests.isEmpty());

      Long requesterId = requests.get(0).getId();

      contactsService.addAsContact(userId, requesterId);

      List<ContactInfo> contactsNow = contactsService.getContacts(userId);
      Assert.assertNotNull(contactsNow);
      Assert.assertEquals(contacts.size() + 1, contactsNow.size());

      List<ContactInfo> requestsNow = contactsService.getContactRequests(userId);
      Assert.assertNotNull(requestsNow);
      Assert.assertEquals(requests.size() - 1, requestsNow.size());

      ContactInfo c = new ContactInfo();
      c.setId(requesterId);
      Assert.assertTrue(contactsNow.contains(c));
   }

   @Test
   public void testDeniedContactRequest()
   {
      List<User> all = usersManager.ListAllUser();

      List<ContactInfo> contacts = contactsService.getContacts(userId);

      List<ContactInfo> requests = contactsService.getContactRequests(userId);

      if (requests.isEmpty())
      {
         long rid = -1;
         int i = 0;

         while (i < all.size() && rid == -1)
         {
            ContactInfo c = new ContactInfo();
            c.setId(all.get(i).getId());

            if (!contacts.contains(c))
               rid = all.get(i).getId();

            i++;
         }

         contactsService.sendContactRequest(userId, rid);

         requests = contactsService.getContactRequests(userId);
      }

      Assert.assertFalse(requests.isEmpty());

      Long requesterId = requests.get(0).getId();

      contactsService.deniedCotnactRequest(userId, requesterId);

      List<ContactInfo> requestsNow = contactsService.getContactRequests(userId);
      Assert.assertNotNull(requestsNow);
      Assert.assertEquals(requests.size() - 1, requestsNow.size());

      List<ContactInfo> contactsNow = contactsService.getContacts(userId);
      Assert.assertNotNull(contactsNow);
      Assert.assertEquals(contacts.size(), contactsNow.size());
   }

   @Test
   public void testRemoveContact()
   {
      List<ContactInfo> contacts = contactsService.getContacts(userId);

      Assert.assertFalse(contacts.isEmpty());

      Long contactId = contacts.get(0).getId();

      contactsService.removeContact(userId, contactId);

      List<ContactInfo> contactsNow = contactsService.getContacts(userId);
      Assert.assertNotNull(contactsNow);
      Assert.assertEquals(contacts.size() - 1, contactsNow.size());

      ContactInfo c = new ContactInfo();
      c.setId(contactId);
      Assert.assertFalse(contactsNow.contains(c));
   }

}