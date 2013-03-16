package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.persistence.IKnowledgeAreaDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class KnowledgeAreaDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(KnowledgeAreaDaoTest.class);

   @Inject
   private IKnowledgeAreaDao knowledgeAreaDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {

      List<KnowledgeArea> areas = knowledgeAreaDao.findAll();
      Assert.assertNotNull(areas);

      KnowledgeArea area = builder.buildKnowledgeArea();
      Assert.assertNotNull(area);

      knowledgeAreaDao.persist(area);

      Assert.assertNotNull(area.getId());

      KnowledgeArea found = knowledgeAreaDao.findById(area.getId());

      Assert.assertEquals(area, found);
   }

   @Test
   public void testFindAll()
   {

      List<KnowledgeArea> areas = knowledgeAreaDao.findAll();
      Assert.assertNotNull(areas);
      System.out.println("Found " + areas.size() + " areas.");
   }

   @Test
   public void testFindById()
   {
      List<KnowledgeArea> areas = knowledgeAreaDao.findAll();
      Assert.assertNotNull(areas);

      if (areas.size() > 0)
      {

         KnowledgeArea area = areas.get(0);
         Assert.assertNotNull(area);

         KnowledgeArea found = knowledgeAreaDao.findById(area.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(area, found);

      }
      else
      {
         fail("There is no knowledege areas.");
      }
   }

   @Test
   public void testUpdate()
   {

      List<KnowledgeArea> all = knowledgeAreaDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String name = "Changed";

         KnowledgeArea area = all.get(0);
         Assert.assertNotNull(area);

         area.setName(name);

         KnowledgeArea found = knowledgeAreaDao.findById(area.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(name, found.getName());

      }
      else
      {
         fail("There is no areas.");
      }
   }

   @Test
   public void testRemove()
   {

      List<KnowledgeArea> areas = knowledgeAreaDao.findAll();
      Assert.assertNotNull(areas);

      if (areas.size() > 0)
      {

         KnowledgeArea area = areas.get(0);

         knowledgeAreaDao.remove(area);

         List<KnowledgeArea> areasNow = knowledgeAreaDao.findAll();
         Assert.assertNotNull(areasNow);

         Assert.assertEquals(areas.size() - 1, areasNow.size());

      }
      else
      {
         fail("There is no knowledege areas.");
      }
   }

   @Test
   public void testRefresh()
   {
      List<KnowledgeArea> all = knowledgeAreaDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         KnowledgeArea first = all.get(0);
         Assert.assertNotNull(first);

         String oldName = first.getName();
         String newName = "Temporary name";

         first.setName(newName);
         knowledgeAreaDao.refresh(first);

         Assert.assertNotSame(newName, first.getName());
         Assert.assertEquals(oldName, first.getName());

         log.info("Knowledge area after refresh Test :" + first);

      }
      else
         fail(" !!! There is not areas into the bbdd !!!");
   }

}
