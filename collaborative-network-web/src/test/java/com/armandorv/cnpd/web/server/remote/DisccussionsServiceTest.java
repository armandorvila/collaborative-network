package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class DisccussionsServiceTest extends ArquillianSupport
{
   private final String userId = ResourceBundle.getBundle("test").getString("test.user");
   
   @Inject
   private DiscussionsService discussionService;
   
   @Inject
   private ProjectsService projectsService;
   
   @Test
   public void testGetDiscussions()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<DiscussionInfo> discussions = discussionService.getDiscussions(1956);
      Assert.assertNotNull(discussions);
      Assert.assertFalse(discussions.isEmpty());
   }

   @Test
   public void testAddDiscussion()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<DiscussionInfo> discussions = discussionService.getDiscussions(info.getId());
      Assert.assertNotNull(discussions);

      DiscussionInfo discuss = new DiscussionInfo();
      discuss.setDescription("Testing");
      discuss.setTitle("Testing");
      discuss.setOpen(true);
      List<IdNameGenericInfo> options = new ArrayList<IdNameGenericInfo>();
      options.add(new IdNameGenericInfo(1, "Test"));
      discuss.setOptions(options);

      Assert.assertTrue(discussionService.addDiscussion(info.getId(), discuss));

      List<DiscussionInfo> discussionsNow = discussionService.getDiscussions(info.getId());
      Assert.assertNotNull(discussionsNow);

      Assert.assertEquals(discussions.size() + 1, discussionsNow.size());
   }
   
}