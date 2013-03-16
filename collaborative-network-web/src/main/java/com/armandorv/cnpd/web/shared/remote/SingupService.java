package com.armandorv.cnpd.web.shared.remote;

import java.lang.annotation.Annotation;
import java.util.List;
import org.jboss.errai.bus.server.annotations.Remote;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;

/**
 * GWT Remote service for Sign up process, It is an statefull service,Methods
 * must be called in the correcto order:
 * 
 * <ul>
 * <li>Step 1:saveAndVallidateGoogleAccount</li>
 * <li>Step 2:saveAndValidatePersonalData</li>
 * <li>Step 3:saveProfessionalInfo</li>
 * <li>Step 4 (Optional):getHypotheticalContacts</li>
 * <li>Step 5:saveContactsInfo</li>
 * <li>Step 6:commitUser</li>
 * </ul>
 * 
 * @author armandorv
 * 
 */
@Remote
public interface SingupService
{

   /**
    * Check account against my business tier and google contacts.
    * 
    * @param user
    *            user of Google username of the new user, and their future
    *            username in this system.
    * @param pass
    *            Google password of the new user and their future password in
    *            this system.
    * @return boolean if user, pass couple not exist in my system and yes in
    *         google system.
    */
   ValidationResponse saveAndValidateGoogleAccount(String user, String password);

   /**
    * Save and validate info given by user about their studies, no validation
    * is necessary.
    * 
    * @return true if there is no problems.
    */
   boolean saveAcademicInfo(List<String> degrees);

   /**
    * Save information of Personal Form into a statefull singup process.
    * 
    * @param data
    *            {@link Annotation} @UserInfo object of presentation model with
    *            the information.
    * @return
    */
   boolean savePersonalData(UserInfo data);

   /**
    * Save and validate info given by user about their jobs, no validation
    * is necessary.
    * 
    * @return true if there is no problems.
    */
   boolean saveProfessionalInfo(List<String> jobs);

   /**
    * Retrieve hypothetical contacts of an user, giving it to Google Contacts
    * Service, it is mostly used for contacts import operation.
    * 
    * @return a List of ContactInfo related with the given user.
    */
   List<ContactInfo> getHypotheticalContacts();

   /**
    * Save the contacts selected by user. No validation is necessary.
    * 
    * @param contacts
    *            a @List of @ContactInfo with information about hypothetical
    *            new contacts of our new user.
    * @return true if no problems.
    */
   boolean saveContactsInfo(List<ContactInfo> contacts);

   /**
    * Persist all information given by user during sign up wizard.
    * 
    * @return true if there is not problems enrolling the new user.
    */
   ValidationResponse commitUser();

}
