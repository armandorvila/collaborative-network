package com.armandorv.cnpd.business.impl;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.test.ArquillianSupport;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.pojo.Business;

@RunWith(Arquillian.class)
public class ContactsManagerTest extends ArquillianSupport
{

   private static Logger log = Logger.getLogger(ContactsManagerTest.class);

   @Inject
   private IContactsManager contactsManager;

   @Inject
   private IUsersManager usersManager;

   private List<User> users;

   @Business
   @Inject
   private IPojoBuilder builder;

   @Before
   public void before()
   {
      Assert.assertNotNull(contactsManager);
      Assert.assertNotNull(usersManager);

      users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

   }

   /* ********************* testGetContacts ****************** */

   @Test
   public void testGetContacts()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> contacts = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contacts);

         log.info("Retrieved " + contacts.size() + " contacts.");

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testGetContactRequests ****************** */
   @Test
   public void testGetContactRequests()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> requests = contactsManager.getContactRequests(user
               .getId());

         log.info("Retrieved " + requests.size() + " requests.");

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testAddContactRequest ****************** */
   @Test
   public void testAddContactRequest()
   {
      if (users.size() > 1)
      {
         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> requests = contactsManager.getContactRequests(user
               .getId());
         Assert.assertNotNull(requests);

         Set<User> contacts = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contacts);

         User requester = users.get(1);
         Assert.assertNotNull(requester);

         if (contacts.contains(requester))
         {
            contactsManager.removeContact(user.getId(), requester.getId());
            contacts.remove(requester);
         }

         if (requests.contains(requester))
         {
            contactsManager.resolveRequest(user.getId(), requester.getId(),
                  false);
            requests.remove(requester);
         }

         contactsManager.addContactRequest(user.getId(), requester.getId());

         Set<User> requestsAfter = contactsManager.getContactRequests(user
               .getId());
         Assert.assertEquals(requests.size(), requestsAfter.size() - 1);
      }
      else
      {
         fail("No enough users found");
      }
   }

   /* ********************* testResolveRequest to False ****************** */
   @Test
   public void testResolveRequestToFalse()
   {

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> contacts = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contacts);

         Set<User> requests = contactsManager.getContactRequests(user
               .getId());
         Assert.assertNotNull(requests);

         log.info("Retrieved " + requests.size() + " contacts.");

         if (requests.isEmpty())
         {
            User requester = users.get(1);
            Assert.assertNotNull(requester);
            contactsManager.addContactRequest(user.getId(),
                  requester.getId());
            requests = contactsManager.getContactRequests(user.getId());
         }

         User request = requests.iterator().next();
         Assert.assertNotNull(request);

         contactsManager
               .resolveRequest(user.getId(), request.getId(), false);

         Set<User> contactsNow = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contactsNow);

         Set<User> requestsNow = contactsManager.getContactRequests(user
               .getId());
         Assert.assertNotNull(requestsNow);

         Assert.assertEquals(contacts.size(), contactsNow.size());
         Assert.assertEquals(requests.size() - 1, requestsNow.size());

         Assert.assertFalse(contactsNow.contains(request));
         Assert.assertFalse(requestsNow.contains(request));

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testResolveRequest ****************** */
   @Test
   public void testResolveRequestToTrue()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> contacts = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contacts);

         Set<User> requests = contactsManager.getContactRequests(user
               .getId());
         Assert.assertNotNull(requests);

         log.info("Retrieved " + requests.size() + " contacts.");

         if (requests.isEmpty())
         {
            User requester = users.get(1);
            Assert.assertNotNull(requester);
            contactsManager.addContactRequest(user.getId(),
                  requester.getId());
            requests = contactsManager.getContactRequests(user.getId());
         }

         User request = requests.iterator().next();
         Assert.assertNotNull(request);

         contactsManager.resolveRequest(user.getId(), request.getId(), true);

         Set<User> contactsNow = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contactsNow);

         Set<User> requestsNow = contactsManager.getContactRequests(user
               .getId());
         Assert.assertNotNull(requestsNow);

         Assert.assertEquals(contacts.size() + 1, contactsNow.size());
         Assert.assertEquals(requests.size() - 1, requestsNow.size());

         Assert.assertTrue(contactsNow.contains(request));
         Assert.assertFalse(requestsNow.contains(request));

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testDeleteContact ****************** */
   @Test
   public void testRemoveContact()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> contacts = contactsManager.getContacts(user.getId());
         Assert.assertNotNull(contacts);

         if (contacts.size() > 0)
         {

            User contact = contacts.iterator().next();
            Assert.assertTrue(contacts.contains(contact));

            contactsManager.removeContact(user.getId(), contact.getId());

            Set<User> contactsNow = contactsManager.getContacts(user
                  .getId());
            Assert.assertNotNull(contactsNow);

            Assert.assertEquals(contacts.size() - 1, contactsNow.size());
            Assert.assertFalse(contactsNow.contains(contact));

         }
         else
         {
            fail("User hasn't contacts.");
         }

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testGetMessages ****************** */
   @Test
   public void testGetMessages()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Message> messages = contactsManager.getMessages(user.getId());
         Assert.assertNotNull(messages);

         log.info("Retrieved " + messages.size() + " messages for user "
               + user.getId());

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testSendMessage ****************** */
   @Test
   public void testSendMessage()
   {

      if (users.size() > 1)
      {

         User addressee = users.get(0);
         Assert.assertNotNull(addressee);

         User sender = users.get(1);
         Assert.assertNotNull(sender);

         Message message = builder.buildMessage();
         Assert.assertNotNull(message);

         message.setSender(sender);
         message.setAddressee(addressee);

         message = contactsManager.sendMessage(message);
         Assert.assertNotNull(message);
         Assert.assertNotNull(message.getId());

         Set<Message> messages = contactsManager.getMessages(addressee
               .getId());
         Assert.assertNotNull(messages);

         Assert.assertTrue(messages.contains(message));

      }
      else
      {
         fail("There is not enough users");
      }
   }

   /* ********************* testMarkMessageAsRead ****************** */

   @Test
   public void testMarkMessageAsRead()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Message> messages = contactsManager.getMessages(user.getId());
         Assert.assertNotNull(messages);

         if (messages.size() > 0)
         {

            Message message = messages.iterator().next();
            Assert.assertNotNull(message);

            contactsManager.markMessageAsRead(message.getId());

            Message now = contactsManager.getMessages(user.getId())
                  .iterator().next();
            Assert.assertNotNull(now);
            Assert.assertTrue(now.isRead());

         }
         else
         {
            fail("No messages found");
         }

      }
      else
      {
         fail("No users found");
      }
   }

   /* ********************* testDeleteMessage ****************** */
   @Test
   public void testDeleteMessage()
   {

      /* Delete a message with a user. */
      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Message> messages = contactsManager.getMessages(user.getId());
         Assert.assertNotNull(messages);

         if (messages.size() > 0)
         {

            Message message = messages.iterator().next();
            Assert.assertNotNull(message);

            contactsManager.deleteMessage(message.getId());

            Set<Message> messagesNow = contactsManager.getMessages(user
                  .getId());

            Assert.assertNotNull(messagesNow);
            Assert.assertEquals(messages.size(), messagesNow.size() + 1);
            Assert.assertFalse(messagesNow.contains(message));

         }
         else
         {
            fail("No messages found");
         }

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testGetMeetings()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Meeting> meetings = contactsManager.getMeetings(user.getId());
         Assert.assertNotNull(meetings);

         log.info("Found " + meetings.size() + " meetings.");

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testCreateMeeting()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Meeting> meetings = contactsManager.getMeetings(user.getId());
         Assert.assertNotNull(meetings);

         Meeting meeting = builder.buildMeeting();
         Assert.assertNotNull(meeting);

         meeting = contactsManager.createMeeting(user.getId(), meeting);
         Assert.assertNotNull(meeting);
         Assert.assertNotNull(meeting.getId());

         Set<Meeting> meetingsNow = contactsManager
               .getMeetings(user.getId());
         Assert.assertNotNull(meetingsNow);

         Assert.assertEquals(meetings.size() + 1, meetingsNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void leaveMeetingTest()
   {

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Meeting> meetings = contactsManager.getMeetings(user.getId());
         Assert.assertNotNull(meetings);

         if (meetings.isEmpty())
         {
            Meeting meeting = builder.buildMeeting();
            meeting = contactsManager.createMeeting(user.getId(), meeting);
            Assert.assertNotNull(meeting);
            meetings = contactsManager.getMeetings(user.getId());
            Assert.assertNotNull(meetings);
         }

         Meeting meeting = meetings.iterator().next();
         Assert.assertNotNull(meeting);

         contactsManager.leaveMeeting(user.getId(), meeting.getId());

         Set<Meeting> meetingNow = contactsManager.getMeetings(user.getId());
         Assert.assertNotNull(meetingNow);

         Assert.assertEquals(meetings.size() - 1, meetingNow.size());
         Assert.assertFalse(meetingNow.contains(meeting));

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testSendMeetingInvitation()
   {

      if (users.size() > 1)
      {

         User user = users.get(1);
         Assert.assertNotNull(user);

         User invited = users.get(0);
         Assert.assertNotNull(invited);

         Set<Meeting> meetings = contactsManager.getMeetings(user.getId());
         Assert.assertNotNull(meetings);

         if (meetings.isEmpty())
         {
            contactsManager.createMeeting(user.getId(),
                  builder.buildMeeting());
            meetings = contactsManager.getMeetings(user.getId());
            Assert.assertNotNull(meetings);
            Assert.assertFalse(meetings.isEmpty());
         }

         Meeting meeting = meetings.iterator().next();
         Assert.assertNotNull(meeting);

         Set<Meeting> invitedInvitations = contactsManager
               .getMeetingInvitations(invited.getId());

         Set<Meeting> invitedMeetings = contactsManager
               .getMeetings(invited.getId());

         if (invitedMeetings.contains(meeting))
         {
            contactsManager.leaveMeeting(invited.getId(), meeting.getId());

            invitedMeetings = contactsManager
                  .getMeetings(invited.getId());
         }

         if (invitedInvitations.contains(meeting))
         {
            contactsManager.resolveMeetingInvitation(meeting.getId(),
                  invited.getId(), false);
            invitedInvitations = contactsManager
                  .getMeetingInvitations(invited.getId());
         }

         contactsManager.sendMeetingInvitation(meeting.getId(),
               invited.getId());

         Set<Meeting> invitationsNow = contactsManager
               .getMeetingInvitations(invited.getId());

         Assert.assertNotNull(invitationsNow);
         Assert.assertFalse(invitationsNow.isEmpty());
         Assert.assertEquals(invitedInvitations.size() + 1,
               invitationsNow.size());

      }
      else
      {
         fail("No enough users found");
      }
   }

   @Test
   public void testResolveMeetingInvitationToTrue()
   {

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Meeting> meetings = contactsManager.getMeetings(user
               .getId());
         Assert.assertNotNull(meetings);

         Set<Meeting> invitations = contactsManager
               .getMeetingInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (invitations.isEmpty())
         {
            testSendMeetingInvitation();
            invitations = contactsManager
                  .getMeetingInvitations(user.getId());
         }

         Meeting invitation = invitations.iterator().next();
         Assert.assertNotNull(invitation);

         contactsManager.resolveMeetingInvitation(invitation.getId(),
               user.getId(), true);

         Set<Meeting> invitationsNow = contactsManager
               .getMeetingInvitations(user.getId());
         Assert.assertNotNull(invitationsNow);

         Assert.assertEquals(invitations.size() - 1, invitationsNow.size());

         Set<Meeting> meetingsNow = contactsManager
               .getMeetings(user.getId());
         Assert.assertNotNull(meetingsNow);

         Assert.assertEquals(meetings.size() + 1, meetingsNow.size());
         Assert.assertTrue(meetingsNow.contains(invitation));

      }
      else
      {
         fail("No enough users found");
      }

   }

   @Test
   public void testResolveMeetingInvitationToFalse()
   {

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Meeting> meetings = contactsManager.getMeetings(user
               .getId());
         Assert.assertNotNull(meetings);

         Set<Meeting> invitations = contactsManager
               .getMeetingInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (invitations.isEmpty())
         {

            testSendMeetingInvitation();

            meetings = contactsManager.getMeetings(user
                  .getId());
            invitations = contactsManager
                  .getMeetingInvitations(user.getId());
         }

         Meeting invitation = invitations.iterator().next();
         Assert.assertNotNull(invitation);

         contactsManager.resolveMeetingInvitation(invitation.getId(),
               user.getId(), false);

         Set<Meeting> invitationsNow = contactsManager
               .getMeetingInvitations(user.getId());
         Assert.assertNotNull(invitationsNow);

         Assert.assertEquals(invitations.size() - 1, invitationsNow.size());

         Set<Meeting> meetingsNow = contactsManager
               .getMeetings(user.getId());
         Assert.assertNotNull(meetingsNow);

         Assert.assertEquals(meetings.size(), meetingsNow.size());
         Assert.assertFalse(meetingsNow.contains(invitation));

      }
      else
      {
         fail("No enough users found");
      }
   }

}
