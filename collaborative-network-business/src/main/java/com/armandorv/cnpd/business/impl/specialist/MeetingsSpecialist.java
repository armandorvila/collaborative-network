package com.armandorv.cnpd.business.impl.specialist;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.IllegalResolutionException;
import com.armandorv.cnpd.business.exception.InvalidInvitationException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IMeetingDao;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on solve business rules related with meetings.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class MeetingsSpecialist
{
   @Inject
   private IMeetingDao meetingDao;

   @Inject
   private IUserDao userDao;

   private FindByIdExecutor<User> findUserById;

   private FindByIdExecutor<Meeting> findMeetingById;

   @PostConstruct
   public void postConstruct()
   {
      findUserById = new FindByIdExecutor<User>(userDao);
      findMeetingById = new FindByIdExecutor<Meeting>(meetingDao);
   }

   public Set<Meeting> getMeetings(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Meeting> meetings = user.getMeetings();

      meetings.size();

      return meetings;
   }

   public Set<Meeting> getMeetingInvitations(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Meeting> invitations = user.getMeetingInvitations();

      invitations.size();

      return invitations;
   }

   public Meeting createMeeting(long userId, Meeting meeting, long... particpantsId)
   {
      User user = findUserById.findById(userId);

      meetingDao.persist(meeting);

      user.addMeeting(meeting);

      meeting.setInstigatorId(user.getId());

      meeting.addParticipant(user);

      for (long id : particpantsId)
         sendMeetingInvitations(meeting, id);

      return meeting;
   }

   public Set<User> getParticipants(long meetingId)
   {
      Meeting meeting = findMeetingById.findById(meetingId);

      meeting.getParticipants().size();

      return meeting.getParticipants();
   }

   public void sendMeetingInvitations(long meetingId, long[] invitedUserId)
   {
      if (invitedUserId.length == 0)
         throw new InvalidInvitationException(
               "At least a user id is requeried.");

      Meeting meeting = findMeetingById.findById(meetingId);

      for (long userId : invitedUserId)
         sendMeetingInvitations(meeting, userId);
   }

   private void sendMeetingInvitations(Meeting meeting, long userId)
   {
      User user = findUserById.findById(userId);

      if (user.getMeetingInvitations().contains(meeting))
         throw new InvalidInvitationException(
               "Thre is already a invitation for user " + userId
                     + " to meeting " + meeting.getId());

      if (user.getMeetings().contains(meeting))
         throw new InvalidInvitationException("User " + userId
               + " is already a participant of meeting " + meeting.getId());

      user.addMeetingInvitation(meeting);
   }

   public void resolveMeetingInvitation(long meetingId, long userId,
         boolean accept)
   {
      Meeting meeting = findMeetingById.findById(meetingId);

      User user = findUserById.findById(userId);

      if (!user.getMeetingInvitations().contains(meeting))
         throw new IllegalResolutionException(
               "No invitations of user : " + userId + " to meeting "
                     + meetingId);

      if (accept)
      {
         meeting.addParticipant(user);
         user.addMeeting(meeting);
      }

      user.removeMeetingInvitations(meeting);
   }

   public void leaveMeeting(long userId, long meetingId)
   {
      User user = findUserById.findById(userId);
      Meeting meeting = findMeetingById.findById(meetingId);

      meeting.removeParticipant(user);
      user.removeMeeting(meeting);
   }

   public void delete(long meetingId)
   {
      Meeting meeting = findMeetingById.findById(meetingId);

      for (User user : meeting.getParticipants())
      {
         user.removeMeeting(meeting);
      }

      List<User> invitedUsers = userDao.findMeetingInvitedUsers(meetingId);

      for (User user : invitedUsers)
      {
         user.removeMeeting(meeting);
      }

      meetingDao.remove(meeting);
   }

   public void clousureMeeting(long meetingId, String conclusions)
   {
      Meeting meeting = findMeetingById.findById(meetingId);
      meeting.setCelebrated(true);
      meeting.setConclusion(conclusions);
   }

}