package com.armandorv.cnpd.web.server.remote;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.MilestonesService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class MilestonesServiceTest extends ArquillianSupport
{
   private final String userId = ResourceBundle.getBundle("test").getString("test.user");
   
   @Inject
   private ProjectsService projectsService;
   
   @Inject
   private MilestonesService milestoneService;
   
   @Test
   public void testGetMilestones()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<MilestoneInfo> milestones = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestones);
      Assert.assertFalse(milestones.isEmpty());
   }

   @Test
   public void testAddMilestone()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<MilestoneInfo> milestones = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestones);

      MilestoneInfo milestone = new MilestoneInfo();
      milestone.setName("Testing");
      milestone.setDate(new Date());

      milestone.setId(milestoneService.addMilestone(info.getId(), milestone.getName(), milestone.getDate()));

      List<MilestoneInfo> milestonesNow = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestonesNow);

      Assert.assertFalse(milestonesNow.isEmpty());
      Assert.assertEquals(milestones.size() + 1, milestonesNow.size());
      Assert.assertTrue(milestonesNow.contains(milestone));
   }

   @Test
   public void testDeleteMilestone()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<MilestoneInfo> milestones = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestones);
      Assert.assertFalse(milestones.isEmpty());

      MilestoneInfo milestone = milestones.get(0);

      Assert.assertTrue(milestoneService.deleteMilestone(info.getId(), milestone.getId()));

      List<MilestoneInfo> milestonesNow = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestonesNow);

      Assert.assertEquals(milestones.size() - 1, milestonesNow.size());
      Assert.assertFalse(milestonesNow.contains(milestone));
   }

   @Test
   public void testSetLastMilestone()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<MilestoneInfo> milestones = milestoneService.getMilestones(info.getId());
      Assert.assertNotNull(milestones);
      Assert.assertFalse(milestones.isEmpty());

      MilestoneInfo milestone = milestones.get(0);

      Assert.assertTrue(milestoneService.setLastMilestone(info.getId(), milestone.getId()));
      // TODO check that is set the last milestone.
   }
}
