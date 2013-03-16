package com.armandorv.cnpd.test.util;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.test.ArquillianSupport;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.pojo.Business;

//@Ignore
@RunWith(Arquillian.class)
public class BBDDBusinessPopulator extends ArquillianSupport
{

   @Business
   @Inject
   private IPojoBuilder builder;

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IResourcesManager resourcesManager;

   @Inject
   private IContactsManager contactsManager;

   private final int kasSize = 10;

   private final int usersSize = 80;

   private final int projectsSize = 40;

   private final int resourcesSize = 54;

   private final int internalResources = 6;

   private final int discussionsSize = 160;

   private final int referencesSize = 260;

   private final int milestonesSize = 260;

   private final int invitationsSize = 260;

   @Test
   public void pupulateKnowledgeAreas()
   {

      for (int i = 0; i < kasSize; i++)
         projectsManager.createKnowledgeArea(builder.buildKnowledgeArea());
   }

   @Test
   public void populateUsers()
   {
      for (int i = 0; i < usersSize; i++)
         usersManager.createUser(builder.buildUser("user-"
               + UUID.randomUUID().getMostSignificantBits()));

   }

   @Test
   public void pupulateProjects()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertTrue(users.size() == usersSize);
      List<KnowledgeArea> kas = projectsManager.getAllKnowledgeAreas();

      for (int i = 0; i < projectsSize; i++)
         projectsManager.createProject(
               users.get(new Random().nextInt(usersSize)).getId(), kas
                     .get(new Random().nextInt(10)).getId(), builder
                     .buildProject());
   }

   @Test
   public void populateResources()
   {

      List<Project> projects = projectsManager.getAllProjects();

      for (int i = 0; i < resourcesSize; i++)
      {

         long projectId = projects.get(new Random().nextInt(projectsSize))
               .getId();

         Resource res = resourcesManager.createResource(projectId, -1,
               builder.buildResource(ResourceKind.FOLDER));

         for (int j = 0; j < internalResources; j++)
         {
            resourcesManager.createResource(projectId, res.getId(),
                  builder.buildResource(ResourceKind.FILE));
         }
      }

   }

   @Test
   public void populateDrafts()
   {

      List<Project> projects = projectsManager.getAllProjects();

      for (int i = 0; i < resourcesSize; i++)
      {

         long projectId = projects.get(new Random().nextInt(projectsSize))
               .getId();

         Resource res = resourcesManager.createResourceAsDraft(projectId,
               -1, builder.buildResource(ResourceKind.FOLDER));

         for (int j = 0; j < internalResources; j++)
         {
            resourcesManager.createResourceAsDraft(projectId, res.getId(),
                  builder.buildResource(ResourceKind.FOLDER));
         }
      }

   }

   @Test
   public void populateDiscussions()
   {

      List<Project> projects = projectsManager.getAllProjects();

      for (int i = 0; i < discussionsSize; i++)
      {

         long projectId = projects.get(new Random().nextInt(projectsSize))
               .getId();

         projectsManager.addDiscussion(projectId, builder.buildDiscussion());

      }

   }

   @Test
   public void populateReferences()
   {

      List<Project> projects = projectsManager.getAllProjects();

      for (int i = 0; i < referencesSize; i++)
      {

         long projectId = projects.get(new Random().nextInt(projectsSize))
               .getId();

         projectsManager.addNewReference(projectId, builder.buildReference());

      }

   }

   @Test
   public void populateMilestones()
   {

      List<Project> projects = projectsManager.getAllProjects();

      for (int i = 0; i < milestonesSize; i++)
      {

         long projectId = projects.get(new Random().nextInt(projectsSize))
               .getId();

         projectsManager
               .addNewMilestone(projectId, builder.buildMilestone());

      }
   }

   @Test
   public void populateProjectInviations()
   {

      List<Project> projects = projectsManager.getAllProjects();

      List<User> users = usersManager.ListAllUser();

      for (int i = 0; i < invitationsSize; i++)
      {

         Project project = projects.get(new Random().nextInt(projectsSize));

         long userId = users.get(new Random().nextInt(usersSize)).getId();

         Set<Project> uprojects = projectsManager
               .getProjectsByUser(userId);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(userId);

         if (!invitations.contains(project) && !uprojects.contains(project))
         {
            projectsManager
                  .sendInvitationToProject(userId, project.getId());
         }

      }
   }

   @Test
   public void populateContacts()
   {

      List<User> users = usersManager.ListAllUser();

      for (int i = 0; i < (usersSize / 2) - 4; i++)
      {

         contactsManager.addContactRequest(users.get(i).getId(),
               users.get(usersSize - i - 1).getId());

         contactsManager.resolveRequest(users.get(i).getId(),
               users.get(usersSize - i - 1).getId(), true);
      }

   }

   @Test
   public void populateMessages()
   {

      List<User> users = usersManager.ListAllUser();

      for (int i = 0; i < (usersSize / 2) - 4; i++)
      {

         Message message = new Message();

         message.setSender(users.get(i));
         message.setAddressee(users.get(usersSize - i - 1));
         message.setDate(new Date());
         message.setContent("Testing ");

         contactsManager.sendMessage(message);
      }
   }
}
