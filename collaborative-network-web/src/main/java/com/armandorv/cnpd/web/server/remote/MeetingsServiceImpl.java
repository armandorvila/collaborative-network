package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.qualifiers.Meetings;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;

@Service
@ApplicationScoped
public class MeetingsServiceImpl implements MeetingsService
{
   @Inject
   private IContactsManager contactsManager;

   @Inject
   @Meetings
   private Mapper<Meeting, MeetingInfo> meetingMapper;

   @Inject
   @Contacts
   private Mapper<User, ContactInfo> contactMapper;

   @Override
   @HandleServersideException
   public List<MeetingInfo> getMeetings(long userId)
   {
      Set<Meeting> businessMeetings = contactsManager.getMeetings(userId);
      return getMeetingsInfo(businessMeetings);
   }

   @Override
   @HandleServersideException
   public List<MeetingInfo> getInvitations(long userId)
   {
      Set<Meeting> businessMeetings = contactsManager.getMeetingInvitations(userId);
      return getMeetingsInfo(businessMeetings);
   }

   private List<MeetingInfo> getMeetingsInfo(Set<Meeting> businessMeetings)
   {
      List<MeetingInfo> meetings = new ArrayList<MeetingInfo>();

      for (Meeting meeting : businessMeetings)
      {
         meetings.add(meetingMapper.map(meeting));
      }

      return meetings;
   }

   @Override
   @HandleBooleanException
   public boolean acceptInvitation(long meetingId, long userId)
   {
      contactsManager.resolveMeetingInvitation(meetingId, userId, true);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean refuseInvitation(long meetingId, long userId)
   {
      contactsManager.resolveMeetingInvitation(meetingId, userId, false);
      return false;
   }

   @Override
   @HandleBooleanException
   public boolean leaveMeeting(long meetingId, long userId)
   {
      contactsManager.leaveMeeting(userId, meetingId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean deleteMeeting(long meetingId)
   {
      contactsManager.deleteMeeting(meetingId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean createMeeting(long userId, MeetingInfo meetingInfo, List<ContactInfo> participants)
   {
      long[] ids = new long[participants.size()];

      for (int i = 0; i < participants.size(); i++)
      {
         ids[i] = participants.get(i).getId();
      }

      Meeting meeting = new Meeting();
      meeting.setCelebrated(false);
      meeting.setConclusion("");
      meeting.setDate(meetingInfo.getDate());
      meeting.setDescription(meetingInfo.getDescription());
      meeting.setPlace(meetingInfo.getPlace());
      meeting.setTitle(meetingInfo.getTitle());
      meeting.setInstigatorId(userId);
      meeting.setParticipants(new HashSet<User>());

      contactsManager.createMeeting(userId, meeting, ids);

      return true;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getMeetingParticipants(long meetingId)
   {
      Set<User> businessParticioants = contactsManager.getMeetingParticipants(meetingId);
      List<ContactInfo> participants = new ArrayList<ContactInfo>();

      for (User user : businessParticioants)
      {
         participants.add(contactMapper.map(user));
      }

      return participants;
   }

   @Override
   @HandleBooleanException
   public boolean sendInvitations(long meetingId, List<ContactInfo> contacts)
   {
      long[] ids = new long[contacts.size()];

      for (int i = 0; i < contacts.size(); i++)
      {
         ids[i] = contacts.get(i).getId();
      }

      contactsManager.sendMeetingInvitation(meetingId, ids);

      return true;
   }

   @Override
   @HandleBooleanException
   public boolean clausureMeeting(long meetingId,String conclusions)
   {
      contactsManager.clousureMeeting(meetingId , conclusions);
      return true;
   }

}