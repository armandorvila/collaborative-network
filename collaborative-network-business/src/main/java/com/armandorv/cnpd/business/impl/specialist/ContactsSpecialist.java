package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.ContactNotFoundException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with contacts concerns, is used by contacts manager and
 * must be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class ContactsSpecialist
{
   @Inject
   private IUserDao userDao;

   private FindByIdExecutor<User> findUserById;

   @PostConstruct
   public void setUp()
   {
      this.findUserById = new FindByIdExecutor<User>(userDao);
   }

   public Set<User> getContacts(long userId)
   {
      User user = findUserById.findById(userId);

      Set<User> contacts = user.getContacts();
      contacts.size();

      return contacts;
   }

   public void removeContact(long userId, long contactId)
   {
      User user = findUserById.findById(userId);
      User contact = findUserById.findById(contactId);

      Set<User> userContacts = user.getContacts();
      Set<User> contactContacts = user.getContacts();

      if (!userContacts.contains(contact) || contactContacts.contains(user))
         throw new ContactNotFoundException("Contact " + contactId
               + " was not found as contact of " + userId);

      user.removeContact(contact);
      contact.removeContact(user);
   }
}