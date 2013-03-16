package com.armandorv.cnpd.web.server.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.business.exception.BusinessException;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.google.GoogleContactsProxy;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.exception.IllegalStatefullCallException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.remote.SingupService;

@Service
@SessionScoped
public class SingupServiceImpl implements Serializable, SingupService
{
   private static final long serialVersionUID = 9150126673953063052L;

   private static Logger log = Logger.getLogger(SingupServiceImpl.class);

   @Inject
   @Contacts
   private Mapper<User, ContactInfo> contactMapper;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IContactsManager contactsManager;

   //FIXME
   private transient GoogleContactsProxy googleProxy = new GoogleContactsProxy();

   private AccountData account = null;

   private PersonalData personalInformation = null;

   private List<String> degrees = null;

   private List<String> jobs = null;

   private Set<User> requests = null;

   @Override
   public ValidationResponse saveAndValidateGoogleAccount(String user, String password)
   {
      if (!googleProxy.validate(user, password))
         return new ValidationResponse(false, "Username not avaiable in Google Contacts Service.");

      log.info("Validating user :" + user + " against busiiness tier.");

      if (usersManager.getUserByUsername(user) != null)
         return new ValidationResponse(false, "Username already is into system.");

      account = new AccountData();
      account.setAccount(user);
      account.setPassword(password);

      log.info("Singup :User validated " + user);

      return new ValidationResponse(true, "User is Correct.");
   }

   @Override
   public boolean savePersonalData(UserInfo data)
   {
      this.checkGoogleAccountState();

      this.personalInformation = new PersonalData();

      this.personalInformation.setName(data.getName());
      this.personalInformation.setSurname1(data.getLastname1());
      this.personalInformation.setSurname2(data.getLastname2());
      this.personalInformation.setDateOfBirthday(data.getBirthday());
      this.personalInformation.setCity(data.getCity());
      this.personalInformation.setWebSite(data.getWebSite());

      log.info("Singup :Personal data saved.");

      return true;
   }

   @Override
   public boolean saveAcademicInfo(List<String> degrees)
   {

      this.checkGoogleAccountState();
      this.checkPersonalInfoState();

      this.degrees = degrees;

      log.info("Singup :Degrees saved.");

      return true;
   }

   @Override
   public boolean saveProfessionalInfo(List<String> jobs)
   {
      this.checkGoogleAccountState();
      this.checkPersonalInfoState();
      this.checkAcademicInfoState();

      this.jobs = jobs;

      log.info("Singup :Jobs saved.");

      return true;
   }

   @Override
   public List<ContactInfo> getHypotheticalContacts()
   {
      this.checkGoogleAccountState();
      this.checkPersonalInfoState();
      this.checkProfessionalState();

      List<ContactInfo> hypotheticalContacts = new ArrayList<ContactInfo>();

      List<User> users = usersManager.ListAllUser();

      List<ContactInfo> googleContacts = googleProxy.getContacts(this.account.getAccount(), this.account.getPassword());
      
      for (int i = 0; i < users.size() && !googleContacts.isEmpty(); i++)
      {
         ContactInfo mapped = contactMapper.map(users.get(i));

         if (googleContacts.contains(mapped))
         {
            hypotheticalContacts.add(mapped);
            googleContacts.remove(mapped);
         }
      }

      return hypotheticalContacts;
   }

   @Override
   public boolean saveContactsInfo(List<ContactInfo> contactsInfo)
   {
      this.checkGoogleAccountState();
      this.checkPersonalInfoState();
      this.checkProfessionalState();

      this.requests = new HashSet<User>();

      for (ContactInfo contactInfo : contactsInfo)
      {

         this.requests.add(usersManager.getUserByUsername(contactInfo.getGmailAddress()));
      }

      return true;
   }

   @Override
   public ValidationResponse commitUser()
   {
      this.checkGoogleAccountState();
      this.checkPersonalInfoState();
      this.checkProfessionalState();
      this.checkContactsState();

      User user = new User();
      user.setGoogleAccount(account);
      user.setPersonalData(personalInformation);
      user.setDegrees(degrees);
      user.setJobs(jobs);

      try
      {
         User created = usersManager.createUser(user);

         for (User requested : requests)
            contactsManager.addContactRequest(requested.getId(), user.getId());

         this.account = null;
         this.personalInformation = null;
         this.degrees = null;
         this.jobs = null;
         this.requests = null;

         log.info("User " + created.getId() + " enrolled sucessfull");

         return new ValidationResponse(true, "Users requested for contact.");
      }
      catch (BusinessException e)
      {
         log.error("There was a problem creating user.");
         return new ValidationResponse(false, "Problem creating user.");
      }
      finally
      {
         this.account = null;
         this.personalInformation = null;
         this.degrees = null;
         this.jobs = null;
         this.requests = null;
      }

   }

   /* ***************** Private Methods ************************* */

   private void checkGoogleAccountState()
   {
      if (account == null)
         throw new IllegalStatefullCallException("A GoogleAccount related  method was called at incorrect order");
   }

   private void checkPersonalInfoState()
   {
      if (personalInformation == null)
         throw new IllegalStatefullCallException("A PersonalInfo related  method was called at incorrect order");
   }

   private void checkAcademicInfoState()
   {
      if (degrees == null)
         throw new IllegalStatefullCallException("An academic info related  method was called at incorrect order");

   }

   private void checkProfessionalState()
   {
      if (jobs == null)
         throw new IllegalStatefullCallException("A ProfessionalInfo related  method was called at incorrect order");

   }

   private void checkContactsState()
   {
      if (requests == null)
         throw new IllegalStatefullCallException(" A contacts related method  was called at incorrect order");
   }

}