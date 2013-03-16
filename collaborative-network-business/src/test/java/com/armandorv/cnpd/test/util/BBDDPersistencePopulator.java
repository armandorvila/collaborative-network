package com.armandorv.cnpd.test.util;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.persistence.impl.DiscussionDaoTest;
import com.armandorv.cnpd.persistence.impl.KnowledgeAreaDaoTest;
import com.armandorv.cnpd.persistence.impl.MeetingDaoTest;
import com.armandorv.cnpd.persistence.impl.MessageDaoTest;
import com.armandorv.cnpd.persistence.impl.MilestoneDaoTest;
import com.armandorv.cnpd.persistence.impl.NotificationDaoTest;
import com.armandorv.cnpd.persistence.impl.ProjectDaoTest;
import com.armandorv.cnpd.persistence.impl.ReferenceDaoTest;
import com.armandorv.cnpd.persistence.impl.ResourceDaoTest;
import com.armandorv.cnpd.persistence.impl.UserDaoTest;
import com.armandorv.cnpd.test.TransactionalTestSupport;

/**
 * Execute persist test for each DAO test.
 * @author armandorv
 *
 */
@Ignore
@RunWith(Arquillian.class)
public class BBDDPersistencePopulator extends TransactionalTestSupport
{

   @Inject
   private DiscussionDaoTest discussionTest;

   @Inject
   private KnowledgeAreaDaoTest areaTest;

   @Inject
   private MeetingDaoTest meetingTest;

   @Inject
   private MessageDaoTest messageTest;

   @Inject
   private MilestoneDaoTest milestoneTest;

   @Inject
   private NotificationDaoTest notificationTest;

   @Inject
   private ProjectDaoTest projectTest;

   @Inject
   private ReferenceDaoTest referenceTest;

   @Inject
   private ResourceDaoTest resourceTest;

   @Inject
   private UserDaoTest userTest;

   @Test
   public void pupulateDiscussions()
   {

      for (int i = 0; i < 30; i++)
         discussionTest.testPersist();
   }

   @Test
   public void pupulateAreas()
   {

      for (int i = 0; i < 30; i++)
         areaTest.testPersist();
   }

   @Test
   public void pupulateMessges()
   {

      for (int i = 0; i < 30; i++)
         messageTest.testPersist();
   }

   @Test
   public void pupulateMeetings()
   {

      for (int i = 0; i < 30; i++)
         meetingTest.testPersist();
   }

   @Test
   public void pupulateMilestones()
   {

      for (int i = 0; i < 30; i++)
         milestoneTest.testPersist();
   }

   @Test
   public void pupulateNotifications()
   {

      for (int i = 0; i < 30; i++)
         notificationTest.testPersist();
   }

   @Test
   public void pupulateProjects()
   {

      for (int i = 0; i < 30; i++)
         projectTest.testPersist();
   }

   @Test
   public void pupulateReferencess()
   {

      for (int i = 0; i < 30; i++)
         referenceTest.testPersist();

   }

   @Test
   public void pupulateResources()
   {

      for (int i = 0; i < 30; i++)
         resourceTest.testPersist();
   }

   @Test
   public void pupulateUsers()
   {

      for (int i = 0; i < 30; i++)
         userTest.testPersist();
   }

}
