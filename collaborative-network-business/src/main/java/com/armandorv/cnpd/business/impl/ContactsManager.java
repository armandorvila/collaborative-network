package com.armandorv.cnpd.business.impl;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.specialist.ContactsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.MeetingsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.MessagesSpecialist;
import com.armandorv.cnpd.business.impl.specialist.RequestsSpecialist;
import com.armandorv.cnpd.business.local.IContactsManagerLocal;
import com.armandorv.cnpd.business.remote.IContactsManagerRemote;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;

@Stateless
public class ContactsManager implements IContactsManagerLocal,
      IContactsManagerRemote
{
   @Inject
   private ContactsSpecialist contactsSpecialist;

   @Inject
   private RequestsSpecialist requestsSpecialist;

   @Inject
   private MessagesSpecialist messagesSpecialist;

   @Inject
   private MeetingsSpecialist meetingspecialist;

   @Override
   public Set<User> getContacts(long userId)
   {
      return this.contactsSpecialist.getContacts(userId);
   }

   @Override
   public void resolveRequest(long userId, long contactId, boolean accept)
   {
      this.requestsSpecialist.resolveRequest(userId, contactId, accept);
   }

   @Override
   public void addContactRequest(long requestedId, long requesterId)
   {
      this.requestsSpecialist.addContactRequest(requestedId, requesterId);
   }

   @Override
   public Set<User> getContactRequests(long userId)
   {
      return this.requestsSpecialist.getContactRequests(userId);
   }

   @Override
   public void removeContact(long userId, long contactId)
   {
      this.contactsSpecialist.removeContact(userId, contactId);
   }

   @Override
   public Set<Message> getMessages(long userId)
   {
      return messagesSpecialist.getMessages(userId);
   }

   @Override
   public Message sendMessage(Message message)
   {
      return messagesSpecialist.sendMessage(message);
   }

   @Override
   public void deleteMessage(long idMessage)
   {
      this.messagesSpecialist.deleteMessage(idMessage);
   }

   @Override
   public void markMessageAsRead(long idMessage)
   {
      this.messagesSpecialist.markMessageAsRead(idMessage);
   }

   @Override
   public Set<Meeting> getMeetings(long userId)
   {
      return meetingspecialist.getMeetings(userId);
   }

   @Override
   public Meeting createMeeting(long userId, Meeting meeting,
         long... particpantsId)
   {
      return meetingspecialist.createMeeting(userId, meeting, particpantsId);
   }

   @Override
   public void sendMeetingInvitation(long meetingId, long... invitedUserId)
   {
      meetingspecialist.sendMeetingInvitations(meetingId, invitedUserId);
   }

   @Override
   public void resolveMeetingInvitation(long meetingId, long userId, boolean accept)
   {
      meetingspecialist.resolveMeetingInvitation(meetingId, userId, accept);
   }

   @Override
   public Set<Meeting> getMeetingInvitations(long userId)
   {
      return meetingspecialist.getMeetingInvitations(userId);
   }

   @Override
   public void leaveMeeting(long userId, long meetingId)
   {
      meetingspecialist.leaveMeeting(userId, meetingId);
   }

   @Override
   public void deleteMeeting(long meetingId)
   {
      meetingspecialist.delete(meetingId);
   }

   @Override
   public Set<User> getMeetingParticipants(long meetingId)
   {
      return meetingspecialist.getParticipants(meetingId);
   }

   @Override
   public void clousureMeeting(long meetingId, String conclusions)
   {
      meetingspecialist.clousureMeeting(meetingId , conclusions);
   }

}
