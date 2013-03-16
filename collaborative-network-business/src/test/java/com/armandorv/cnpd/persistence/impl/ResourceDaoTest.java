package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.persistence.IResourceDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class ResourceDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(ResourceDaoTest.class);

   @Inject
   private IResourceDao resourceDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {

      List<Resource> resources = resourceDao.findAll();
      Assert.assertNotNull(resources);

      Resource newResource = builder.buildResource(ResourceKind.FILE);
      Assert.assertNotNull(newResource);

      resourceDao.persist(newResource);
      Assert.assertNotNull(newResource);
      Assert.assertNotNull(newResource.getId());

      List<Resource> allNow = resourceDao.findAll();
      Assert.assertNotNull(allNow);

      Assert.assertEquals(resources.size(), allNow.size() - 1);

   }

   @Test
   public void testFindAll()
   {

      List<Resource> resources = resourceDao.findAll();
      Assert.assertNotNull(resources);
      log.info("Found " + resources + " resources");
   }

   @Test
   public void testFindById()
   {

      List<Resource> resources = resourceDao.findAll();
      Assert.assertNotNull(resources);

      if (resources.size() > 0)
      {

         Resource resource = resources.get(0);
         Assert.assertNotNull(resource);

         Resource found = resourceDao.findById(resource.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(found, resource);
      }
      else
      {
         fail("There is no resources.");
      }

   }

   @Test
   public void testUpdate()
   {

      List<Resource> all = resourceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String name = "Changed";

         Resource resource = all.get(0);
         Assert.assertNotNull(resource);

         resource.setName(name);

         Resource found = resourceDao.findById(resource.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(name, found.getName());

      }
      else
      {
         fail("There is no resources.");
      }
   }

   @Test
   public void testRemove()
   {

      List<Resource> resources = resourceDao.findAll();
      Assert.assertNotNull(resources);

      if (resources.size() > 0)
      {

         Resource resource = resources.get(0);
         resourceDao.remove(resource);

         List<Resource> allNow = resourceDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(resources.size(), allNow.size() + 1);

      }
      else
      {
         fail("There is no resources.");
      }

   }

   @Test
   public void testRefresh()
   {

      List<Resource> all = resourceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Resource first = all.get(0);
         Assert.assertNotNull(first);

         String oldName = first.getName();
         String newName = "Temporary name";

         first.setName(newName);
         resourceDao.refresh(first);

         Assert.assertNotSame(newName, first.getName());
         Assert.assertEquals(oldName, first.getName());

         log.info("Resource after refresh Test :" + first);

      }
      else
      {
         fail(" !!! There is not resources into the bbdd !!!");
      }
   }
}
