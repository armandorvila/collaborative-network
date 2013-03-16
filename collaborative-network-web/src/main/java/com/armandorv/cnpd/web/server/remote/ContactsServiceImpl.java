package com.armandorv.cnpd.web.server.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.remote.ChatService;
import com.armandorv.cnpd.web.shared.remote.ContactsService;

/**
 * Implementation of @ContactsService
 * 
 * @author armandorv
 * 
 */
@Service
@ApplicationScoped
public class ContactsServiceImpl implements Serializable, ContactsService
{
   private static final long serialVersionUID = -4858372284688684982L;

   private static Logger log = Logger.getLogger(ContactsServiceImpl.class);

   @Inject
   private IContactsManager contactsManager;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private ChatService chatService;

   @Inject
   @Contacts
   private Mapper<User, ContactInfo> contactMapper;


   @Override
   @HandleServersideException
   public List<ContactInfo> getContacts(long userId)
   {
      log.info("Retrieving contacts for " + userId);

      List<ContactInfo> contacts = new ArrayList<ContactInfo>();
      Set<User> businessContacts = contactsManager.getContacts(userId);

      for (User user : businessContacts)
      {
         contacts.add(contactMapper.map(user));
      }

      log.info("founds " + businessContacts.size());
      log.info("returning" + contacts.size());

      return contacts;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getContactsByUser(String username)
   {
      User user = usersManager.getUserByUsername(username);

      if (user == null)
         throw new ServersideException("User " + username + " not found.");

      return this.getContacts(user.getId());
   }

   @Override
   public boolean addAsContact(long userId, long contactId)
   {
      return this.resolveContact(userId, contactId, true);
   }

   @Override
   public boolean deniedCotnactRequest(long userId, long contactId)
   {
      return this.resolveContact(userId, contactId, false);
   }

   @HandleBooleanException
   private boolean resolveContact(long userId, long contactId, boolean accept)
   {
      contactsManager.resolveRequest(userId, contactId, accept);

      if (accept)
      {
         User user = usersManager.getUserById(userId);
         User contact = usersManager.getUserById(contactId);

         return chatService.addContactToChat(user.getGoogleAccount().getAccount(), contact.getGoogleAccount()
               .getAccount());
      }

      return true;
   }

   @Override
   @HandleServersideException
   public ContactInfo getContact(long contactId)
   {
      User user = usersManager.getUserById(contactId);
      return contactMapper.map(user);
   }

   @Override
   @HandleBooleanException
   public boolean removeContact(long userId, long contactId)
   {
      contactsManager.removeContact(userId, contactId);
      return true;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> searchContacts(long userId, String searchName,
         String searchLastname1, String searchLastname2)
   {
      List<ContactInfo> contacts = new ArrayList<ContactInfo>();

      List<User> users = usersManager.ListAllUser();
      Set<User> businessContacts = contactsManager.getContacts(userId);

      for (User user : users)
      {
         PersonalData data = user.getPersonalData();

         if (user.getId() != userId)
         {
            if (match(data.getName(), data.getSurname1(), data.getSurname2(), searchName,
                  searchLastname1, searchLastname2))
            {
               ContactInfo contact = contactMapper.map(user);

               if (businessContacts.contains(user))
                  contact.setContact(true);

               contacts.add(contact);
            }
         }
      }
      return contacts;
   }

   private boolean match(String value1, String value2, String value3, String param1, String param2, String param3)
   {
      if ("".equals(value1) && "".equals(value2) && "".equals(value3))
         return false;

      return equalsOrEmpty(value1, param1) && equalsOrEmpty(value2, param2) && equalsOrEmpty(value3, param3);
   }

   private boolean equalsOrEmpty(String value, String parameter)
   {
      if ("".equals(parameter))
         return true;

      return value.equalsIgnoreCase(parameter);

   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getContactRequests(long userId)
   {
      List<ContactInfo> requests = new ArrayList<ContactInfo>();

      Set<User> businessRequests = contactsManager.getContactRequests(userId);
      Set<User> contacts = contactsManager.getContacts(userId);

      for (User user : businessRequests)
      {
         ContactInfo request = contactMapper.map(user);
         request.setContact(contacts.contains(user));
         requests.add(request);
      }
      return requests;
   }

   @Override
   @HandleBooleanException
   public boolean sendContactRequest(long requestedId, long requesterId)
   {
      contactsManager.addContactRequest(requestedId, requesterId);
      return true;
   }

   @Override
   @HandleServersideException
   public boolean thereIsRequest(long userId, long contactId)
   {
      Set<User> userRequests = contactsManager.getContactRequests(userId);
      Set<User> contactRequests = contactsManager.getContactRequests(contactId);

      User contact = usersManager.getUserById(contactId);
      User user = usersManager.getUserById(userId);

      return userRequests.contains(contact) || contactRequests.contains(user);
   }

   @Override
   @HandleServersideException
   public boolean isContactOf(long userId, long contactId)
   {
      Set<User> contacts = contactsManager.getContacts(userId);
      User contact = usersManager.getUserById(contactId);

      return contacts.contains(contact);
   }
}