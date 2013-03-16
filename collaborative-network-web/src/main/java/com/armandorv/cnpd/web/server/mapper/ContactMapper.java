package com.armandorv.cnpd.web.server.mapper;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;

/**
 * Map a user of business model to a contact info. It must be serializable to
 * be injected into SingupService, because SingupService declares a passivation
 * scope.
 * 
 * @author armandorv
 * 
 */
@Dependent
@Contacts
public class ContactMapper implements Mapper<User, ContactInfo>, Serializable
{

   private static final long serialVersionUID = -7562640651631117555L;

   @Override
   public ContactInfo map(User object)
   {
      if (object.getId() == null)
         throw new MappingErrorException("Contact must has a identifier.");

      ContactInfo contact = new ContactInfo();

      PersonalData data = object.getPersonalData();

      contact.setId(object.getId());

      contact.setFullName(data.getName() + " " + data.getSurname1());

      contact.setName(data.getName());

      contact.setLastname1(data.getSurname1());

      contact.setLastname2(data.getSurname2());

      contact.setGmailAddress(object.getGoogleAccount().getAccount());

      return contact;
   }

}
