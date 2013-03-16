package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.persistence.IMeetingDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class MeetingDaoTest extends TransactionalTestSupport
{
   private static Logger log = Logger.getLogger(MeetingDaoTest.class);

   @Inject
   private IMeetingDao meetingDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {
      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);

      log.info("All mettings are:" + all);

      Meeting meeting = builder.buildMeeting();
      Assert.assertNotNull(meeting);

      meetingDao.persist(meeting);
      Assert.assertNotNull(meeting);
      Assert.assertNotNull(meeting.getId());

      List<Meeting> allNow = meetingDao.findAll();
      Assert.assertNotNull(allNow);

      Assert.assertEquals(all.size() + 1, allNow.size());
   }

   @Test
   public void testFindAll()
   {

      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);
      log.info("There is " + all.size() + " meetings.");

   }

   @Test
   public void testFindById()
   {
      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         Meeting meeting = all.get(0);
         Assert.assertNotNull(meeting);

         Meeting found = meetingDao.findById(meeting.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(meeting, meeting);
      }
      else
      {
         fail("There is no meetings");
      }
   }

   @Test
   public void testRemove()
   {
      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         Meeting meeting = all.get(0);
         Assert.assertNotNull(meeting);

         meetingDao.remove(meeting);

         List<Meeting> allNow = meetingDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size() - 1, allNow.size());
         Assert.assertFalse(allNow.contains(meeting));
      }
      else
      {
         fail("There is no meetings");
      }
   }

   @Test
   public void testRefresh()
   {
      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         Meeting first = all.get(0);
         Assert.assertNotNull(first);

         String oldTitle = first.getTitle();
         String newTitle = "Temporary name";

         first.setTitle(newTitle);
         meetingDao.refresh(first);

         Assert.assertNotSame(newTitle, first.getTitle());
         Assert.assertEquals(oldTitle, first.getTitle());

         log.info("Meeting after refresh Test :" + first);
      }
      else
      {
         fail(" !!! There is not meetings into the bbdd !!!");
      }
   }

   @Test
   public void testGetMeetingsByTitle()
   {
      List<Meeting> all = meetingDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         Meeting first = all.get(0);
         Assert.assertNotNull(first);

         List<Meeting> founds = meetingDao.findByTitle(first.getTitle());
         Assert.assertNotNull(founds);

         Assert.assertTrue(founds.contains(first));
      }

      else
      {
         fail(" !!! There is not meetings into the bbdd !!!");
      }
   }


}