package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.persistence.IDiscussionDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class DiscussionDaoTest extends TransactionalTestSupport
{
   private static Logger log = Logger.getLogger(DiscussionDaoTest.class);

   @Persistence
   @Inject
   IPojoBuilder builder;

   @Inject
   private IDiscussionDao discussionDao;

   @Test
   public void testPersist()
   {

      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);

      Discussion newDiscuss = builder.buildDiscussion();
      Assert.assertNotNull(newDiscuss);

      discussionDao.persist(newDiscuss);
      Assert.assertNotNull(newDiscuss.getId());

      List<Discussion> allNow = discussionDao.findAll();
      Assert.assertNotNull(all);

      Assert.assertEquals(all.size() + 1, allNow.size());

      Discussion found = discussionDao.findById(newDiscuss.getId());
      Assert.assertNotNull(found);

      Assert.assertEquals(newDiscuss, found);
   }

   @Test
   public void testFindAll()
   {
      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);

      log.info("All are:" + all);
   }

   @Test
   public void testFindById()
   {

      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Discussion first = all.get(0);
         Assert.assertNotNull(first);

         Discussion found = discussionDao.findById(all.get(0).getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(first, found);

      }
      else
      {
         fail("There is not discussions.");
      }

   }

   @Test
   public void testUpdate()
   {

      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);
      log.info("All  are:" + all);

      Discussion newDisc = builder.buildDiscussion();

      discussionDao.persist(newDisc);
      Assert.assertNotNull(newDisc);
      Assert.assertNotNull(newDisc.getId());

      List<Discussion> updatedAll = discussionDao.findAll();
      Assert.assertNotNull(updatedAll);

      Assert.assertEquals(all.size() + 1, updatedAll.size());
      log.info("Updated All Are:");

   }

   @Test
   public void testRemove()
   {
      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)

      {
         Discussion toRemove = all.get(0);
         Assert.assertNotNull(toRemove);
         discussionDao.remove(toRemove);

         List<Discussion> allNow = discussionDao.findAll();

         Assert.assertNotNull(all);
         Assert.assertEquals(all.size(), allNow.size() + 1);

         Discussion discussion = discussionDao.findById(toRemove.getId());
         Assert.assertNull(discussion);

      }
      else
      {
         fail("there is not discussions!!");
      }

   }

   @Test
   public void testRefresh()
   {

      List<Discussion> all = discussionDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Discussion first = all.get(0);
         Assert.assertNotNull(first);

         String oldTitle = first.getTitle();
         String newTitle = "Temporary title";

         first.setTitle(newTitle);
         discussionDao.refresh(first);

         Assert.assertNotSame(newTitle, first.getTitle());
         Assert.assertEquals(oldTitle, first.getTitle());

         log.info("Discussion after refresh Test :" + first);

      }
      else
         fail(" !!! There is not discussions into the bbdd !!!");
   }

}
