package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.persistence.IReferenceDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class ReferenceDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(ReferenceDaoTest.class);

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Inject
   private IReferenceDao referenceDao;

   @Test
   public void testFindAll()
   {
      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);
      log.info("There are " + all.size() + " references.");

   }

   @Test
   public void testPersist()
   {

      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);

      Reference newReference = builder.buildReference();
      Assert.assertNotNull(newReference);

      referenceDao.persist(newReference);
      Assert.assertNotNull(newReference);
      Assert.assertNotNull(newReference.getId());

      List<Reference> allNow = referenceDao.findAll();
      Assert.assertEquals(all.size() + 1, allNow.size());
      log.info("References after create test:" + all.size());
   }

   @Test
   public void testFindById()
   {

      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() >= 1)
      {

         Reference reference = all.get(0);
         Assert.assertNotNull(reference);

         Reference retrived = referenceDao.findById(reference.getId());
         Assert.assertNotNull(retrived);

         Assert.assertEquals(reference, retrived);

      }
      else
         fail("There is not references into the bbdd !!!");
   }

   @Test
   public void testUpdate()
   {

      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String name = "Changed";

         Reference reference = all.get(0);
         Assert.assertNotNull(reference);

         reference.setName(name);

         Reference found = referenceDao.findById(reference.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(name, found.getName());

      }
      else
      {
         fail("There is no references.");
      }
   }

   @Test
   public void testRemove()
   {

      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Reference reference = all.get(0);
         referenceDao.remove(reference);

         List<Reference> allNow = referenceDao.findAll();
         Assert.assertNotNull(allNow);
         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
         fail(" !!! There is not References into the bbdd !!!");
   }

   @Test
   public void testRefresh()
   {

      List<Reference> all = referenceDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Reference reference = all.get(0);

         String oldName = reference.getName();
         String newName = "testing refresh reference.";

         reference.setName(newName);
         referenceDao.refresh(reference);
         Assert.assertNotNull(reference);

         Assert.assertNotSame(newName, reference.getName());
         Assert.assertEquals(oldName, reference.getName());

         log.info("Reference after refresh Test :" + reference);

      }
      else
         fail(" !!! There is not References into the bbdd !!!");
   }

}
