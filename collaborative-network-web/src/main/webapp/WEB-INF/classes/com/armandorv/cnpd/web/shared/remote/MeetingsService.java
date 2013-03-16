package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;

/**
 * Remote Service to meetings section.
 * 
 * @author armandorv
 *
 */
@Remote
public interface MeetingsService
{
   /**
    * Get all meeting which a user is participant.
    * 
    * @param userId identifier of the user.
    * @return a list of meetings.
    */
   List<MeetingInfo> getMeetings(long userId);

   /**
    * Get all meetings which a user are invited to go.
    * 
    * @param userId identifier of the user.
    * @return a list of meetings.
    */
   List<MeetingInfo> getInvitations(long userId);
   
   /**
    * Create a new meeting.
    * 
    * @param meeting new meeting.
    * @return true if all OK.
    */
   boolean createMeeting(long userId , MeetingInfo meeting , List<ContactInfo> participants);

   /**
    * A user accept an invitation to a meeting.
    * 
    * @param meetingId identifier of the meeting.
    * @param userId identifier of the user.
    * @return true if all OK. 
    */
   boolean acceptInvitation(long meetingId, long userId);

   /**
    * Refuse an invitation to a meeting.
    * 
    *  @param meetingId identifier of the meeting.
    * @param userId identifier of the user.
    * @return true if all OK.
    */
   boolean refuseInvitation(long meetingId, long userId);

   /**
    * A user leave a meeting.
    * 
    * @param meetingId identifier of the meeting.
    * @param userId identifier of the user.
    * @return true if all OK.
    */
   boolean leaveMeeting(long meetingId, long userId);

   /**
    * A meeting is deleted.
    * 
    *  @param meetingId identifier of the meeting.
    * @return true if all OK.
    */
   boolean deleteMeeting(long meetingId);

   List<ContactInfo> getMeetingParticipants(long id);
   
   /**
    * Send  meeting invitations.
    * 
    * @param mmetingId identifier of the meeting.
    * @param userId identifier of the user 
    * @return true if all OK.
    */
   boolean sendInvitations(long mmetingId,List<ContactInfo> contacts);

   boolean clausureMeeting(long meetingId, String conclusions);
}
