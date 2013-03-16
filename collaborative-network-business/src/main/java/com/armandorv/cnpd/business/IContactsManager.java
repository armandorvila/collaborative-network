package com.armandorv.cnpd.business;

import java.util.Set;

import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;

/**
 * Interface for deal with contacts and their concerns.
 * 
 * @author armandorv
 * 
 */
public interface IContactsManager
{

   /**
    * Get all contacts of a user, given their identifier.
    * 
    * @param user
    *            a user identifier.
    * @return A list of users who are contacts of given user.
    */
   Set<User> getContacts(long userId);

   /**
    * If accept: Add a user B as contact of another user A, user B must be in
    * requests list of user A, else user B will be removed of requesters.
    * 
    * @param user
    *            user A.
    * @param contact
    *            user B, the new contact.
    */
   void resolveRequest(long userId, long contactId, boolean accept);

   /**
    * Generate a request of a user B to another user A, after this user A will
    * able to accept it.
    * 
    * @param userId
    *            user A, requested.
    * @param contactId
    *            user B requester.
    */
   void addContactRequest(long requestedId, long requesterId);

   /**
    * @return all user who did a request to this user.
    */
   Set<User> getContactRequests(long userId);

   /**
    * Delete a user from the contacts list of given contact.
    * 
    * @param user
    * @param contact
    */
   void removeContact(long userId, long contactId);

   /**
    * Retrieves messages of a user.
    * 
    * @param user
    *            user to get messages.
    * @return a list with all messages of this user, even read messages.
    */
   Set<Message> getMessages(long userId);

   /**
    * Add a message to a given user
    * 
    * @param user
    * 
    * @param message
    */
   Message sendMessage(Message message);

   /**
    * Delete a message from system.
    * 
    * @param messageId
    *            identifier of message to be delete
    */
   void deleteMessage(long messageId);

   /**
    * Mark a message as read.
    * 
    * @param messageId
    *            identifier of message to read.
    */
   void markMessageAsRead(long messageId);

   /**
    * Method to retrieve all meetings which a user has whenever participated.
    * 
    * @param userId
    *            identifier of user who you want know their meetings.
    * @return A Set of meetings.
    */
   Set<Meeting> getMeetings(long userId);

   /**
    * Return all invitations of a user to a meeting.
    * 
    * @param userId
    * @return
    */
   Set<Meeting> getMeetingInvitations(long userId);
   
   /**
    * Retrieve all user of a meeting.
    * 
    * @param meetingId identifier of the meeting.
    * @return a set of users.
    */
   Set<User> getMeetingParticipants(long meetingId);

   /**
    * Create a new meeting.
    * 
    * @param userId
    *            user of creator id.
    * @param meeting
    *            info of new meeting, instigator and participants are not
    *            required.
    * @param particpantsId
    *            list of is to send a meeting invitations.
    * @return the new meeting full filled.
    */
   Meeting createMeeting(long userId, Meeting meeting, long... particpantsId);

   /**
    * Send and meeting invitation to one or more users.
    * 
    * @param meetingId
    *            id of meeting.
    * @param invitedUserId
    *            identifier of user to invite, at least one identifier is
    *            required.
    */
   void sendMeetingInvitation(long meetingId, long... invitedUserId);

   /**
    * A user leave a meeting, there is no constraints about meeting instigator.
    * 
    * @param userId
    *            user who want leave the meeting.
    * @param meetingId
    *            meeting that user are leaving.
    */
   void leaveMeeting(long userId, long meetingId);
   
   /**
    * Delete a meeting from the System.
    * 
    * @param meetingId identifier of the meeting to delete.
    */
   void deleteMeeting(long meetingId);
   
   /**
    * Closure a meeting
    * 
    * @param meetingId
    * @param conclusions
    */
   void clousureMeeting(long meetingId,String conclusions);

   /**
    * Resolve a meeting invitation.
    * 
    * @param meetingId
    *            identifier of the meeting.
    * @param userId
    *            identifier of user.
    * @param accept
    *            true if user wants join to meeting.
    */
   void resolveMeetingInvitation(long meetingId, long userId, boolean accept);

}
