package com.armandorv.cnpd.web.server.remote;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ProjectServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(ProjectServiceTest.class);

   private final String username = ResourceBundle.getBundle("test").getString("test.username");

   private final String userId = ResourceBundle.getBundle("test").getString("test.user");

   @Inject
   private ProjectsService projectsService;

   @Test
   public void testGetProjectsById()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      log.info("Founds " + projects.size() + " projects for user " + userId);
   }

   @Test
   public void testGetProject()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      ProjectInfo found = projectsService.getProject(info.getId());
      Assert.assertNotNull(found);
      Assert.assertEquals(info, found);
   }

   @Test
   public void testGetProjectsByUsername()
   {
      List<ProjectInfo> projects = projectsService.getContactProjects(username);
      Assert.assertNotNull(projects);

      log.info("Founds " + projects.size() + " projects for user " + username);
   }

   @Test
   public void testCreateProject()
   {
      ProjectInfo info = new ProjectInfo();
      info.setTitle("Web tier created");
      info.setDescription("Web tier arquillian junit");
      info.setKnowledgeArea(new IdNameGenericInfo(1, ""));
      info.setMembers(new ArrayList<ContactInfo>());

      Assert.assertTrue(projectsService.createProject(Long.parseLong(userId), info));
   }

   @Test
   public void testGetMembers()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<ContactInfo> members = projectsService.getMembers(info.getId());
      Assert.assertNotNull(members);
      Assert.assertFalse(members.isEmpty());
      log.info(members.size());
   }
  
   @Test
   public void testSetManager()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projectsService.getProject(1956);//FIXME projects.get(0);
      Assert.assertNotNull(info);

      List<ContactInfo> members = projectsService.getMembers(info.getId());
      Assert.assertNotNull(members);
      Assert.assertTrue(members.size() > 1);
      
      long newManagerId = (members.get(0).getId() == info.getManagerId()) ? members.get(1).getId() : members.get(0).getId();
      
      projectsService.setProjectManager(info.getId(), newManagerId);
      
      info = projectsService.getProject(info.getId());
      
      Assert.assertEquals(info.getManagerId(),newManagerId);
   }
   
   @Test
   public void testPublishProject()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projectsService.getProject(1956);//FIXME projects.get(0);
      Assert.assertNotNull(info);

      projectsService.publishProject(info.getId());
      
      info = projectsService.getProject(info.getId());
      
      Assert.assertTrue(info.isPublished());
   }

   @Test
   @Ignore
   public void testSearchProjects()
   {
      fail("Not yet implemented");
   }

   @Test
   @Ignore
   public void testGetProjectInvitations()
   {
      fail("Not yet implemented");
   }

   @Test
   @Ignore
   public void testAcceptProjectInvitation()
   {
      fail("Not yet implemented");
   }

   @Test
   @Ignore
   public void testRefuseProjectInvitation()
   {
      fail("Not yet implemented");
   }

}