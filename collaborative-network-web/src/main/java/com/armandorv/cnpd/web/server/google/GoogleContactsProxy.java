package com.armandorv.cnpd.web.server.google;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.web.shared.exception.GoogleAccessException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * Class to invoke operations against Google Contacts Service.
 * 
 * @author armandorv
 * 
 */
public class GoogleContactsProxy
{
   private static Logger log = Logger.getLogger(GoogleContactsProxy.class);

   private GoogleService googleContactsService = new ContactsService("CNPD");

   private static final int MAX_CONTACTS_RESULT = 1000;

   /**
    * Method to check if an user is valid in google contacts service so if is
    * valid in google.
    * 
    * @param user
    *            the user name with @gmail, without @gmail is not tested.
    * @param password
    *            password of the user at Gmail.
    * @return true if the user is valid at Gmail
    */
   public boolean validate(String user, String password)
   {
      try
      {
         googleContactsService.setUserCredentials(user, password);

         log.debug("Valid Google Account.");

         return true;
      }
      catch (AuthenticationException e)
      { //AuthenticationException e) {
         log.debug("Invalid account :" + e.getMessage());
         return false;
      }
   }

   /**
    * Get contacts from Google Contacts.
    * 
    * @param gmaiuser
    * @param gmailpassword
    * @return a list of contacts of google contacts list of user.
    */
   public List<ContactInfo> getContacts(String gmaiuser, String gmailpassword)
   {
      try
      {
         googleContactsService.setUserCredentials(gmaiuser, gmailpassword);
         return this.extractContacts(retriveContactEntries());
      }
      catch (Exception e)
      {
         log.error("Error retriving contacts:" + e.getMessage());
         throw new GoogleAccessException("Errro retriving contacs.", e);
      }
   }

   /* *************** Private Methods *********************** */

   private List<ContactInfo> extractContacts(List<ContactEntry> entries)
   {
      List<ContactInfo> contacts = new ArrayList<ContactInfo>();

      long falseId = 1;

      for (ContactEntry entry : entries)
      {
         if (entry.hasEmailAddresses() && entry.hasName())
         {
            ContactInfo contact = new ContactInfo();
            contact.setId(falseId);
            contact.setName(entry.getName().getFullName().getValue());
            contact.setGmailAddress(entry.getEmailAddresses().get(0).getAddress());

            /* fill it to avoid marshaling problems */
            contact.setFullName(contact.getName());
            contact.setLastname1("");
            contact.setLastname2("");

            contacts.add(contact);
            falseId++;
         }
      }
      return contacts;
   }

   private List<ContactEntry> retriveContactEntries() throws IOException, ServiceException
   {
      URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
      Query myQuery = new Query(feedUrl);
      myQuery.setMaxResults(MAX_CONTACTS_RESULT);
      ContactFeed resultFeed = googleContactsService.query(myQuery, ContactFeed.class);
      List<ContactEntry> entries = resultFeed.getEntries();
      return entries;
   }

   /* ************************************************************* */
}