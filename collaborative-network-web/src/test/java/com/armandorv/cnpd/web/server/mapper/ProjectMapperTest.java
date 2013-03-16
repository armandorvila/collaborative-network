package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;

public class ProjectMapperTest
{
   private static final String projectId = ResourceBundle.getBundle("test").getString("test.project");

   private static Mapper<Project, ProjectInfo> projectMapper;

   private static Project projectToMap;

   @BeforeClass
   public static void beforeClass()
   {
      projectMapper = new ProjectMapper();
      projectToMap = new EJBProducer().produceProjectsManager().getProject(Long.parseLong(projectId));
   }

   @Before
   public void before()
   {

   }

   @Test
   public void testMap()
   {
      ProjectInfo info = projectMapper.map(projectToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(projectToMap.getTitle(), info.getTitle());
      Assert.assertEquals(projectToMap.getDescription(), info.getDescription());
      Assert.assertEquals(projectToMap.isPublisht(), info.isPublished());
      Assert.assertEquals(projectToMap.getKnowledgeArea().getId(), new Long(info.getKnowledgeArea().getId()));

   }

}