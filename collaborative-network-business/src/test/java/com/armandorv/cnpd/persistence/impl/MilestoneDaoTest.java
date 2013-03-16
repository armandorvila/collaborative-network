package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.persistence.IMilestoneDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class MilestoneDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(MilestoneDaoTest.class);

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Inject
   private IMilestoneDao milestoneDao;

   @Test
   public void testPersist()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      Milestone milestone = builder.buildMilestone();
      milestoneDao.persist(milestone);
      Assert.assertNotNull(milestone.getId());

      Milestone found = milestoneDao.findById(milestone.getId());
      Assert.assertNotNull(found);

      Assert.assertEquals(milestone, found);

      List<Milestone> allNow = milestoneDao.findAll();
      Assert.assertEquals(all.size() + 1, allNow.size());
   }

   @Test
   public void testFindAll()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      log.info("Found " + all.size() + " milestones.");
   }

   @Test
   public void testFindById()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Milestone milestone = all.get(0);
         Assert.assertNotNull(milestone);

         Milestone found = milestoneDao.findById(milestone.getId());
         Assert.assertNotNull(milestone);

         Assert.assertEquals(milestone, found);

      }
      else
      {
         fail("There is no milestones.");
      }
   }

   @Test
   public void testUpdate()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String name = "Changed";

         Milestone milestone = all.get(0);
         Assert.assertNotNull(milestone);

         milestone.setName(name);

         Milestone found = milestoneDao.findById(milestone.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(name, found.getName());

      }
      else
      {
         fail("There is no milestones.");
      }
   }

   @Test
   public void testRemove()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Milestone milestone = all.get(0);
         Assert.assertNotNull(milestone);

         milestoneDao.remove(milestone);

         List<Milestone> allNow = milestoneDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
      {
         fail("There is no milestones.");
      }
   }

   @Test
   public void testRefresh()
   {

      List<Milestone> all = milestoneDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Milestone first = all.get(0);
         Assert.assertNotNull(first);

         String oldName = first.getName();
         String newContent = "Temporary name";

         first.setName(newContent);
         milestoneDao.refresh(first);

         Assert.assertNotSame(newContent, first.getName());
         Assert.assertEquals(oldName, first.getName());

         log.info("Milestone after refresh Test :" + first);

      }
      else
      {
         fail(" !!! There is not milestones into the bbdd !!!");
      }
   }

}
