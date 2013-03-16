package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.ContactInfo;

/**
 * Service for deal with contacts.
 * 
 * @author armandorv
 * 
 */
@Remote
public interface ContactsService
{
   /**
    * @userId identifier of user who you wants get their contacts.
    * 
    * @return a List with all contacts of given user identifier.
    */
   List<ContactInfo> getContacts(long userId);

   /**
    * Retrieves a list of contacts given a username.
    * 
    * @param username
    *            username of user who you want get their contacts.
    * @return a list of contacts info object.
    */
   List<ContactInfo> getContactsByUser(String username);

   /**
    * Accept a contact request.
    * 
    * @param userId
    *            user who are accepting.
    * @param contactId
    *            accepted contact identifier.
    * @return true if all is right.
    */
   boolean addAsContact(long userId, long contactId);

   /**
    * Denied a contact request.
    * 
    * @param userId
    *            user requested.
    * @param contactId
    *            contact who do the request, the requester.
    * @return true if all is right.
    */
   boolean deniedCotnactRequest(long userId, long contactId);

   /**
    * Remove a contact of a user contacts list.
    * 
    * @param userId
    *            identifier of user
    * @param contactId
    *            identifier of contact to remove
    * @return true if all is right.
    */
   boolean removeContact(long userId, long contactId);

   /**
    * Get a contact given their identifier.
    * 
    * @param contacId
    *            identifier of contact to get.
    * @return the contact info.
    */
   ContactInfo getContact(long contacId);

   List<ContactInfo> searchContacts(long userId, String searchName, String searchLastname1, String searchLastname2);

   List<ContactInfo> getContactRequests(long id);

   boolean sendContactRequest(long requestedId, long requesterId);

   boolean thereIsRequest(long userId, long contactId);

   boolean isContactOf(long userId, long contactId);

}
