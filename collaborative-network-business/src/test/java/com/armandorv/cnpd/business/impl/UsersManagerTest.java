package com.armandorv.cnpd.business.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.test.ArquillianSupport;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.pojo.Business;

/**
 * Testing class over UsersManager as local EJB.
 * 
 * @author armandorv
 * 
 */
@RunWith(Arquillian.class)
public class UsersManagerTest extends ArquillianSupport
{

   private Logger log = Logger.getLogger(UsersManagerTest.class);

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private IContactsManager contactsManager;

   @Business
   @Inject
   private IPojoBuilder builder;

   private List<User> users;

   @Before
   public void before()
   {

      Assert.assertNotNull(usersManager);

      users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

   }

   @Test
   public void ListAllUser()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertNotNull(users);
      log.info("Retrieved " + users.size() + " users.");
   }

   @Test
   public void testCreateUser()
   {

      User newUser = builder.buildUser("Any username-" + UUID.randomUUID());
      Assert.assertNotNull(newUser);

      User user = usersManager.createUser(newUser);

      Assert.assertNotNull(user);
      Assert.assertNotNull(user.getId());

      List<User> allNow = usersManager.ListAllUser();
      Assert.assertNotNull(allNow);

      Assert.assertEquals(users.size() + 1, allNow.size());

      User found = usersManager.getUserById(user.getId());
      Assert.assertNotNull(found);

      Assert.assertEquals(user, found);

   }

   @Test
   public void testGetUserByUsername()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         User retrieved = usersManager.getUserByUsername(user
               .getGoogleAccount().getAccount());
         Assert.assertNotNull(retrieved);

         Assert.assertEquals(user, retrieved);

      }
      else
      {
         fail("No users found");
      }

   }

   @Test
   public void getUserById()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         User retrieved = usersManager.getUserById(user.getId());
         Assert.assertNotNull(retrieved);

         Assert.assertEquals(user, retrieved);

      }
      else
      {
         fail("No users found");
      }

   }

   @Test
   public void testGetNotifications()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Notification> notifications = usersManager
               .getNotifications(user.getId());
         Assert.assertNotNull(notifications);

         log.info("Retrieved " + notifications.size() + " notifications.");

      }
      else
      {
         fail("No users found");
      }

   }

   @Test
   public void testMarkAsNotified()
   {

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Notification> notifications = usersManager
               .getNotifications(user.getId());
         Assert.assertNotNull(notifications);

         if (notifications.isEmpty())
         {

            User requester = users.get(1);
            contactsManager.addContactRequest(user.getId(),
                  requester.getId());
            notifications = usersManager.getNotifications(user.getId());
         }

         Notification notification = notifications.iterator().next();

         if (notification.isNotified())
            fail("The notification is already notified.");

         Notification notified = usersManager.markAsNotified(notification
               .getId());

         Assert.assertTrue(notified.isNotified());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testModifyUserAccount()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         AccountData data = user.getGoogleAccount();
         data.setAccount(UUID.randomUUID().getLeastSignificantBits() + "");
         data.setExpired(true);
         data.setPassword("changed");

         usersManager.modifyUserAccount(user.getId(), data);

         User modified = usersManager.getUserById(user.getId());
         Assert.assertNotNull(modified);

         Assert.assertEquals(modified.getGoogleAccount(), data);

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testModifyDegrees()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         List<String> degrees = new ArrayList<String>();
         degrees.add("Testing degree");

         usersManager.modifyDegrees(user.getId(), degrees);

         User modified = usersManager.getUserById(user.getId());
         Assert.assertNotNull(modified);

         Assert.assertEquals(degrees.size(), modified.getDegrees().size());
         Assert.assertEquals(degrees.get(0), modified.getDegrees().get(0));

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testModifyJobs()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         List<String> jobs = new ArrayList<String>();
         jobs.add("Testing degree");

         usersManager.modifyJobs(user.getId(), jobs);

         User modified = usersManager.getUserById(user.getId());
         Assert.assertNotNull(modified);

         Assert.assertEquals(jobs.size(), modified.getJobs().size());
         Assert.assertEquals(jobs.get(0), modified.getJobs().get(0));

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testModifyPersonalData()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         PersonalData data = user.getPersonalData();
         data.setName("TestModify");
         data.setSurname1("Test Surname");
         data.setDateOfBirthday(new Date());

         usersManager.modifyPersonalData(user.getId(), data);

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testDeleteUser()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         usersManager.deleteUser(user.getId());
         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testDeleteUserWithProjects()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Project> projects = projectsManager.getProjectsByUser(user
               .getId());

         if (projects.isEmpty())
         {

            List<KnowledgeArea> kas = projectsManager
                  .getAllKnowledgeAreas();

            KnowledgeArea ka = kas.isEmpty() ? kas.get(0) : projectsManager
                  .createKnowledgeArea(builder.buildKnowledgeArea());

            projectsManager.createProject(user.getId(), ka.getId(),
                  builder.buildProject());

         }

         usersManager.deleteUser(user.getId());

         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testDeleteUserWithMeesages()
   {

      if (users.size() > 2)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Message> messages = contactsManager.getMessages(user.getId());

         if (messages.isEmpty())
         {

            User to = users.get(1);
            Assert.assertNotNull(to);

            Message message = builder.buildMessage();
            message.setSender(user);
            message.setAddressee(to);
            contactsManager.sendMessage(message);

         }

         usersManager.deleteUser(user.getId());

         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No enough users found: 2 are nedded.");
      }
   }

   @Test
   public void testDeleteUserWithNotifications()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Notification> notifications = usersManager
               .getNotifications(user.getId());

         if (notifications.isEmpty())
         {

            List<Project> projects = projectsManager.getAllProjects();

            Assert.assertFalse(projects.isEmpty());

            if (projectsManager.getProjectsByUser(user.getId()).contains(
                  projects.get(0)))
            {
               projectsManager.leaveProject(user.getId(), projects.get(0)
                     .getId());
            }

            if (projectsManager.getProjectInvitations(user.getId())
                  .contains(projects.get(0)))
            {
               projectsManager.resolveProjectInvitation(user.getId(),
                     projects.get(0).getId(), true);
            }

            projectsManager.sendInvitationToProject(user.getId(), projects
                  .get(0).getId());

            notifications = usersManager.getNotifications(user.getId());
            Assert.assertFalse(notifications.isEmpty());

         }

         usersManager.deleteUser(user.getId());

         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testDeleteUserWithProjectInvitations()
   {

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(user.getId());

         if (invitations.isEmpty())
         {

            List<Project> projects = projectsManager.getAllProjects();
            if (projects.size() > 0)
            {

               Project project = projects.get(0);
               Assert.assertNotNull(project);

               projectsManager.sendInvitationToProject(user.getId(),
                     project.getId());
            }
         }

         usersManager.deleteUser(user.getId());

         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testDeleteUserWithContacts()
   {

      if (users.size() > 2)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<User> contacts = contactsManager.getContacts(user.getId());

         if (contacts.isEmpty())
         {

            User contact = users.get(1);
            Assert.assertNotNull(contact);

            contactsManager
                  .addContactRequest(user.getId(), contact.getId());
            contactsManager.resolveRequest(user.getId(), contact.getId(),
                  true);
         }

         usersManager.deleteUser(user.getId());

         User deleted = usersManager.getUserById(user.getId());
         Assert.assertNull(deleted);

         List<User> usersNow = usersManager.ListAllUser();
         Assert.assertEquals(users.size() - 1, usersNow.size());

      }
      else
      {
         fail("No enough users found: 2 are nedded.");
      }
   }

}
