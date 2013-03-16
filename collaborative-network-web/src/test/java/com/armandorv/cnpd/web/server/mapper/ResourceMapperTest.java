package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;

public class ResourceMapperTest
{
   private static final String projectId = ResourceBundle.getBundle("test").getString("test.project");

   private static Mapper<Resource, ResourceInfo> resourceMapper;

   private static Resource resourceToMap;

   @BeforeClass
   public static void beforeClass()
   {
      resourceMapper = new ResourceMapper();

      IResourcesManager manager = new EJBProducer().produceResourcesManager();

      resourceToMap = manager.getResources(Long.parseLong(projectId)).iterator().next();
   }

   @Before
   public void before()
   {

      Assert.assertNotNull(resourceMapper);
      Assert.assertNotNull(resourceToMap);
   }

   @Test
   public void testMap()
   {
      ResourceInfo info = resourceMapper.map(resourceToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(resourceToMap.getChilds().size(), info.getChilds().size());
      Assert.assertEquals(resourceToMap.getName(), info.getName());
      Assert.assertEquals(resourceToMap.getUrl(), info.getUrl());
   }

}