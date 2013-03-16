package com.armandorv.cnpd.web.server.remote;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class MeetingsServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(MeetingsServiceTest.class);
   
   private static Long userId = Long.parseLong(ResourceBundle.getBundle("test").getString("test.user"));

   @Inject
   private MeetingsService meetingsService;
   
   @Inject
   private ContactsService contactsService;

   @Test
   public void testCreateMeeting()
   {
      MeetingInfo meeting = new MeetingInfo();
      meeting.setDescription("A meeting to test from web tier");
      meeting.setTitle("WTMetting");
      meeting.setPlace("dsdsd");
      meeting.setDate(new Date());

      Assert.assertTrue(meetingsService.createMeeting(userId, meeting, new ArrayList<ContactInfo>()));
   }

   @Test
   public void testGetMeetings()
   {
     List<MeetingInfo> meetings = meetingsService.getMeetings(userId);
     Assert.assertNotNull(meetings);
     Assert.assertFalse(meetings.isEmpty());
     
     log.info("Found " + meetings.size() + " meetings");
   }

   @Test
   public void testGetInvitations()
   {
      List<MeetingInfo> meetings = meetingsService.getInvitations(24);
      Assert.assertNotNull(meetings);
      Assert.assertFalse(meetings.isEmpty());
      
      log.info("Found " + meetings.size() + " invitations");
   }

   @Test
   public void testSendInvitation()
   {
      List<MeetingInfo> meetings = meetingsService.getMeetings(userId);
      Assert.assertNotNull(meetings);
      Assert.assertFalse(meetings.isEmpty());
      
      List<ContactInfo> contacts = contactsService.getContacts(userId);
      Assert.assertNotNull(contacts);
      Assert.assertFalse(contacts.isEmpty());
      
      Assert.assertTrue(meetingsService.sendInvitations(meetings.get(0).getId(), contacts));
   }
   
   @Test
   public void testDeleteMeeting(){
      List<MeetingInfo> meetings = meetingsService.getMeetings(userId);
      Assert.assertNotNull(meetings);
      Assert.assertFalse(meetings.isEmpty());
      
      Assert.assertTrue(meetingsService.deleteMeeting(meetings.get(0).getId()));
   }

   @Test
   public void testAcceptInvitation()
   {
      fail("Not yet implemented");
   }

   @Test
   public void testRefuseInvitation()
   {
      fail("Not yet implemented");
   }

   @Test
   public void testLeaveMeeting()
   {
      fail("Not yet implemented");
   }

}
