package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;

public class ReferenceMapperTest
{
   private static final String projectId = ResourceBundle.getBundle("test").getString("test.project");

   private static Mapper<Reference, ReferenceInfo> referenceMapper = null;

   private static Reference referenceToMap = null;

   @BeforeClass
   public static void beforeClass()
   {
      referenceMapper = new ReferenceMapper();

      IProjectsManager manager = new EJBProducer().produceProjectsManager();

      referenceToMap = manager.getReferences(Long.parseLong(projectId)).iterator().next();
   }

   @Before
   public void before()
   {
      Assert.assertNotNull(referenceMapper);
      Assert.assertNotNull(referenceToMap);
   }

   @Test
   public void testMap()
   {
      ReferenceInfo info = referenceMapper.map(referenceToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(referenceToMap.getName(), info.getName());
      Assert.assertEquals(referenceToMap.getUrl(), info.getUrl());
   }

}