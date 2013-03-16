package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;

public class DiscussionMapperTest
{
   /*Check it*/
   private static final String projectId = ResourceBundle.getBundle("test").getString("test.project");

   private static Mapper<Discussion, DiscussionInfo> discussionMapper = null;

   private static Discussion discussionToMap = null;

   @BeforeClass
   public static void beforeClass()
   {
      discussionMapper = new DiscussionMapper();

      IProjectsManager manager = new EJBProducer().produceProjectsManager();
      Set<Discussion> discuss = manager.getDiscussions(Long.parseLong(projectId));

      discussionToMap = discuss.iterator().next();
   }

   @Before
   public void before()
   {
      Assert.assertNotNull(discussionMapper);
      Assert.assertNotNull(discussionToMap);
   }

   @Test
   public void testMap()
   {
      DiscussionInfo info = discussionMapper.map(discussionToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(discussionToMap.getTitle(), info.getTitle());
      Assert.assertEquals(discussionToMap.getDescription(), info.getDescription());
      Assert.assertEquals(discussionToMap.getOpen(), info.isOpen());
      Assert.assertEquals(discussionToMap.getOptions().size(), info.getOptions().size());
   }

}