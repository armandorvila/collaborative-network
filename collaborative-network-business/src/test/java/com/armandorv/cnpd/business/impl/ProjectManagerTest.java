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

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.test.ArquillianSupport;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.pojo.Business;

@RunWith(Arquillian.class)
public class ProjectManagerTest extends ArquillianSupport
{

   private static Logger log = Logger.getLogger(ProjectManagerTest.class);

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IResourcesManager resourcesManager;

   @Business
   @Inject
   private IPojoBuilder builder;

   private List<Project> projects;

   @Before
   public void before()
   {
      Assert.assertNotNull(projectsManager);
      Assert.assertNotNull(usersManager);

      projects = projectsManager.getAllProjects();
      Assert.assertNotNull(projects);

   }

   @Test
   public void testGetAllProjects()
   {

      Assert.assertNotNull(projects);
      log.info("There is " + projects.size() + " projects.");

   }

   @Test
   public void testGetAllKnowledgeAreas()
   {

      List<KnowledgeArea> areas = projectsManager.getAllKnowledgeAreas();

      Assert.assertNotNull(areas);

      log.info("Retrieved " + areas.size() + " areas.");

   }

   @Test
   public void testCreateKnowledgeArea()
   {

      List<KnowledgeArea> areas = projectsManager.getAllKnowledgeAreas();
      Assert.assertNotNull(areas);

      KnowledgeArea newKA = builder.buildKnowledgeArea();
      Assert.assertNotNull(newKA);

      KnowledgeArea created = projectsManager.createKnowledgeArea(newKA);
      Assert.assertNotNull(created);

      List<KnowledgeArea> areasNow = projectsManager.getAllKnowledgeAreas();
      Assert.assertNotNull(areasNow);

      Assert.assertEquals(areasNow.size(), areas.size() + 1);

   }

   @Test
   public void testCreateProject()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

      List<KnowledgeArea> areas = projectsManager.getAllKnowledgeAreas();
      Assert.assertNotNull(areas);

      if (users.size() > 0 && areas.size() > 0)
      {

         User manager = users.get(0);
         Assert.assertNotNull(users);

         KnowledgeArea area = areas.get(0);
         Assert.assertNotNull(area);

         Project newProject = builder.buildProject();
         Assert.assertNotNull(newProject);

         Project project = projectsManager.createProject(manager.getId(),
               area.getId(), newProject);
         Assert.assertNotNull(project);
         Assert.assertNotNull(project.getId());

         Project check = projectsManager.getProject(project.getId());
         Assert.assertNotNull(check);
         Assert.assertEquals(project, check);

         Set<User> members = projectsManager.getProjectMemebers(project.getId());
         Assert.assertNotNull(members);
         Assert.assertTrue(members.contains(manager));
         Assert.assertEquals(project.getManagerId(), manager.getId());

      }
      else
      {
         fail("There is no users or areas !!");
      }

   }

   @Test
   public void testGetProject()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Project retrieved = projectsManager.getProject(project.getId());
         Assert.assertNotNull(retrieved);

         Assert.assertEquals(project, retrieved);

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testGetProjectsByUser()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Project> projects = projectsManager.getProjectsByUser(user.getId());
         Assert.assertNotNull(projects);

         log.info("Retrieved " + projects.size() + " projects for "
               + user.getPersonalData().getName());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testLeaveProject()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

      if (users.size() > 0)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         Set<Project> projects = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(projects);

         if (projects.isEmpty())
         {

            KnowledgeArea area = projectsManager.getAllKnowledgeAreas().get(0);
            projectsManager.createProject(user.getId(),
                  area.getId(), builder.buildProject());
         }

         Project project = projects.iterator().next();
         Assert.assertNotNull(project);

         projectsManager.leaveProject(user.getId(), project.getId());
         Set<Project> projectsNow = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(projects);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testLeaveProjectWithMembers()
   {

      List<User> users = usersManager.ListAllUser();
      Assert.assertNotNull(users);

      if (users.size() > 1)
      {

         User user = users.get(0);
         Assert.assertNotNull(user);

         User member2 = users.get(1);
         Assert.assertNotNull(member2);

         Set<Project> projects = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(projects);

         if (projects.isEmpty())
         {

            KnowledgeArea area = projectsManager.getAllKnowledgeAreas().get(0);
            projectsManager.createProject(user.getId(),
                  area.getId(), builder.buildProject());
            projects = projectsManager.getProjectsByUser(user
                  .getId());
         }

         Project project = projects.iterator().next();
         Assert.assertNotNull(project);

         Set<User> members = projectsManager.getProjectMemebers(project.getId());
         Assert.assertNotNull(members);

         if (members.size() == 1)
         {
            projectsManager.sendInvitationToProject(member2.getId(), project.getId());
            projectsManager.resolveProjectInvitation(member2.getId(), project.getId(), true);
            members = projectsManager.getProjectMemebers(project.getId());
         }

         /* *************************************************** */
         projectsManager.leaveProject(user.getId(), project.getId());
         /* *************************************************** */

         /* reload */
         Set<Project> projectsNow = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(projects);

         Project projectNow = projectsManager.getProject(project.getId());
         Assert.assertNotNull(projectNow);

         Set<User> membersNow = projectsManager.getProjectMemebers(projectNow.getId());
         Assert.assertNotNull(members);

         /* Checks */
         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertEquals(members.size() - 1, membersNow.size());
         Assert.assertEquals(projectNow.getManagerId(), member2.getId());

      }
      else
      {
         fail("No users found");
      }
   }

   @Test
   public void testGetExternalReferences()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Reference> references = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(references);

         log.info("There is " + references.size() + " references.");

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testGetDiscussions()
   {

      List<Project> projects = projectsManager.getAllProjects();
      Assert.assertNotNull(projects);

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Discussion> discussions = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussions);

         log.info("There is " + discussions.size() + " discussions.");

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testGetMilestones()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Milestone> milestones = projectsManager.getMilestones(project
               .getId());

         Assert.assertNotNull(milestones);

         log.info("There is " + milestones.size() + " milestones.");

      }
      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testCreateMilestone()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Milestone> milestones = projectsManager.getMilestones(project
               .getId());
         Assert.assertNotNull(milestones);

         Milestone milestone = builder.buildMilestone();

         Milestone added = projectsManager.addNewMilestone(project.getId(),
               milestone);
         Assert.assertNotNull(added);
         Assert.assertNotNull(added.getId());

         Set<Milestone> milestonesNow = projectsManager
               .getMilestones(project.getId());
         Assert.assertNotNull(milestonesNow);

         Assert.assertEquals(milestones.size() + 1, milestonesNow.size());

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testRemoveMilestone()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Milestone> milestones = projectsManager.getMilestones(project
               .getId());
         Assert.assertNotNull(milestones);

         if (milestones.size() > 0)
         {

            Milestone toRemove = milestones.iterator().next();
            projectsManager.removeMilestone(project.getId(),
                  toRemove.getId());

            Set<Milestone> milestonesNow = projectsManager
                  .getMilestones(project.getId());
            Assert.assertNotNull(milestonesNow);

            Assert.assertEquals(milestones.size() - 1, milestonesNow.size());

         }
         else
         {
            fail("There is not milestones.!!!");
         }

      }
      else
      {
         fail("There is not projects. !!!");
      }
   }

   @Test
   public void testSetLastMilestone()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Milestone> milestones = projectsManager.getMilestones(project
               .getId());
         Assert.assertNotNull(milestones);

         if (milestones.isEmpty())
         {
            projectsManager.addNewMilestone(project.getId(),
                  builder.buildMilestone());
            milestones = projectsManager.getMilestones(project.getId());
         }

         Milestone lastReached = milestones.iterator().next();

         projectsManager.setLastMilestone(project.getId(),
               lastReached.getId());

         Set<Milestone> milestonesNow = projectsManager
               .getMilestones(project.getId());
         Assert.assertNotNull(milestonesNow);
         Assert.assertEquals(milestones.size(), milestonesNow.size());

         Project projectNow = projectsManager.getProject(project.getId());
         Assert.assertNotNull(project);

         Assert.assertEquals(lastReached, projectNow.getLastMilestone());

      }
      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testAddDiscussion()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Discussion> discussions = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussions);

         Discussion discussion = builder.buildDiscussion();
         Assert.assertNotNull(discussion);

         Discussion created = projectsManager.addDiscussion(project.getId(),
               discussion);
         Assert.assertNotNull(created);

         Set<Discussion> discussionsNow = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussionsNow);

         Assert.assertEquals(discussionsNow.size(), discussions.size() + 1);

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testRemoveDiscussion()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Discussion> discussions = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussions);

         if (discussions.isEmpty())
         {
            projectsManager.addDiscussion(project.getId(),
                  builder.buildDiscussion());
            discussions = projectsManager.getDiscussions(project.getId());
         }

         Discussion toRemove = discussions.iterator().next();
         Assert.assertNotNull(toRemove);

         projectsManager.removeDiscussion(toRemove.getId());

         Set<Discussion> discussionsNow = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussionsNow);

         Assert.assertEquals(discussions.size() - 1, discussionsNow.size());
         Assert.assertFalse(discussionsNow.contains(toRemove));

      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testAddExternalReference()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Reference> references = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(references);

         Reference reference = builder.buildReference();
         Assert.assertNotNull(reference);

         Reference added = projectsManager.addNewReference(
               project.getId(), reference);
         Assert.assertNotNull(added);
         Assert.assertNotNull(added.getId());

         Set<Reference> referencesNow = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(referencesNow);

         Assert.assertEquals(references.size() + 1, referencesNow.size());

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testRemoveExternalReference()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Reference> references = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(references);

         if (references.isEmpty())
         {
            projectsManager.addNewReference(project.getId(),
                  builder.buildReference());
            references = projectsManager.getReferences(project
                  .getId());
         }

         Reference toRemove = references.iterator().next();
         Assert.assertNotNull(toRemove);

         projectsManager.removeReference(project.getId(), toRemove.getId());

         Set<Reference> referencesNow = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(referencesNow);

         Assert.assertEquals(references.size() - 1, referencesNow.size());
         Assert.assertFalse(referencesNow.contains(toRemove));

      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testGetProjectInvitations()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         User user = all.get(0);
         Assert.assertNotNull(user);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitations);

         log.info("There is " + invitations.size());
      }

      else
      {
         fail("There is not users.");
      }
   }

   @Test
   public void testSendProjectInvitations()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (projects.size() > 0 && all.size() > 0)
      {

         User user = all.get(0);
         Assert.assertNotNull(user);

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Project> userProjects = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(userProjects);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (userProjects.contains(project))
         {
            fail("Quit user " + user.getId() + " from " + project.getId());
         }

         if (invitations.contains(project))
         {
            projectsManager.resolveProjectInvitation(user.getId(), project.getId(), false);
            invitations = projectsManager
                  .getProjectInvitations(user.getId());
         }

         projectsManager.sendInvitationToProject(user.getId(),
               project.getId());

         Set<Project> invitationsNow = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitationsNow);

         Assert.assertEquals(invitations.size() + 1, invitationsNow.size());
         Assert.assertTrue(invitationsNow.contains(project));
      }

      else
      {
         fail("There is not projects or users.");
      }
   }

   @Test
   public void testResolveProjectInvitationToTrue()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (projects.size() > 0 && all.size() > 0)
      {

         User user = all.get(0);
         Assert.assertNotNull(user);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (invitations.isEmpty())
            sendInvitation(projects.get(0), user);

         Project invitation = invitations.iterator().next();
         Assert.assertNotNull(invitation);

         Set<Project> userProjects = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(userProjects);

         /* *************************************************** */
         projectsManager.resolveProjectInvitation(user.getId(),
               invitation.getId(), true);
         /* *************************************************** */

         Set<Project> invitationsNow = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitationsNow);

         Set<Project> userProjectsNow = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(userProjectsNow);

         Assert.assertEquals(invitations.size() - 1, invitationsNow.size());
         Assert.assertFalse(invitationsNow.contains(invitation));

         Assert.assertEquals(userProjects.size() + 1, userProjectsNow.size());
         Assert.assertTrue(userProjectsNow.contains(invitation));

      }

      else
      {
         fail("There is not projects.");
      }
   }

   private void sendInvitation(Project project, User user)
   {

      Set<Project> userProjects = projectsManager.getProjectsByUser(user
            .getId());
      Assert.assertNotNull(userProjects);

      if (userProjects.contains(project))
      {
         fail("Quit user " + user.getId() + " from " + project.getId());

         if (projects.isEmpty())
            fail("There is no projects");

      }
      projectsManager.sendInvitationToProject(user.getId(),
            project.getId());
   }

   @Test
   public void testResolveProjectInvitationToFalse()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (projects.size() > 0 && all.size() > 0)
      {

         User user = all.get(all.size() - 1);
         Assert.assertNotNull(user);

         Set<Project> invitations = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (invitations.isEmpty())
         {
            sendInvitation(projects.get(0), user);
            invitations = projectsManager
                  .getProjectInvitations(user.getId());
         }

         Project invitation = invitations.iterator().next();
         Assert.assertNotNull(invitation);

         Set<Project> userProjects = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(userProjects);

         /* *************************************************** */
         projectsManager.resolveProjectInvitation(user.getId(),
               invitation.getId(), false);
         /* *************************************************** */

         Set<Project> invitationsNow = projectsManager
               .getProjectInvitations(user.getId());
         Assert.assertNotNull(invitationsNow);

         Set<Project> userProjectsNow = projectsManager.getProjectsByUser(user
               .getId());
         Assert.assertNotNull(userProjectsNow);

         Assert.assertEquals(invitations.size() - 1, invitationsNow.size());
         Assert.assertFalse(invitationsNow.contains(invitation));

         Assert.assertEquals(userProjects.size(), userProjectsNow.size());
         Assert.assertFalse(userProjectsNow.contains(invitation));

      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteProject()
   {

      /* Delete a project with a manager */
      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));

      }
      else
      {
         fail("There is not projects.");
      }

   }

   @Test
   public void testDeleteProjectWithResources()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> resources = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(resources);

         if (resources.isEmpty())
            resourcesManager.createResource(project.getId(), -1,
                  builder.buildResource(ResourceKind.FOLDER));

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteProjectWithDrafts()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> drafts = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(drafts);

         if (drafts.isEmpty())
            resourcesManager.createResourceAsDraft(project.getId(), -1,
                  builder.buildResource(ResourceKind.FOLDER));

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteProjectWithExternalReferences()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Reference> references = projectsManager
               .getReferences(project.getId());
         Assert.assertNotNull(references);

         if (references.isEmpty())
            projectsManager.addNewReference(project.getId(),
                  builder.buildReference());

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteProjectWithMilestones()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Milestone> milestones = projectsManager.getMilestones(project
               .getId());
         Assert.assertNotNull(milestones);

         if (milestones.isEmpty())
            projectsManager.addNewMilestone(project.getId(),
                  builder.buildMilestone());

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   public void testDeleteProjectWithMembers()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (projects.size() > 0 && all.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         if (project.getMembers().isEmpty())
         {
            User user = all.get(0);
            projectsManager.sendInvitationToProject(user.getId(),
                  project.getId());
            projectsManager.resolveProjectInvitation(user.getId(),
                  project.getId(), true);
         }

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteProjectWithInvitations()
   {

      List<User> all = usersManager.ListAllUser();
      Assert.assertNotNull(all);

      if (projects.size() > 0 && all.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         User user = all.get(0);
         Assert.assertNotNull(user);

         Set<Project> invitations = projectsManager.getProjectInvitations(user.getId());
         Assert.assertNotNull(invitations);

         if (!invitations.contains(project))
         {
            Set<Project> userPoj = projectsManager.getProjectsByUser(user.getId());

            if (userPoj.contains(project))
               fail("Preapre correct data for this test.");

            projectsManager.sendInvitationToProject(user.getId(), project.getId());
         }

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));
      }

      else
      {
         fail("There is not projects.");
      }
   }

   public void testDeleteProjectWithDiscussions()
   {

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Discussion> discussions = projectsManager
               .getDiscussions(project.getId());
         Assert.assertNotNull(discussions);

         if (discussions.isEmpty())
            projectsManager.addDiscussion(project.getId(),
                  builder.buildDiscussion());

         projectsManager.deleteProject(project.getId());

         List<Project> projectsNow = projectsManager.getAllProjects();
         Assert.assertNotNull(projectsNow);

         Assert.assertEquals(projects.size() - 1, projectsNow.size());
         Assert.assertFalse(projectsNow.contains(project));

      }
      else
      {
         fail("There is not projects.");
      }
   }

}
