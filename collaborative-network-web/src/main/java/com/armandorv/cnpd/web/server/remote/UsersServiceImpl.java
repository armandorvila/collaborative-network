package com.armandorv.cnpd.web.server.remote;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.google.GoogleContactsProxy;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Users;
import com.armandorv.cnpd.web.shared.remote.UsersService;

@Service
@ApplicationScoped
public class UsersServiceImpl implements UsersService
{
   @Inject
   private IUsersManager usersManager;

   @Inject
   @Users
   private Mapper<User, UserInfo> userMapper;

   @Override
   @HandleServersideException
   public UserInfo getUserByUsername(String username)
   {
      User user = usersManager.getUserByUsername(username);

      if (user == null)
         throw new ServersideException("No user found for username : " + username);

      return userMapper.map(user);
   }

   @Override
   @HandleBooleanException
   public boolean setUserInformation(UserInfo info)
   {
      PersonalData personalData = new PersonalData();

      personalData.setName(info.getName());
      personalData.setSurname1(info.getLastname1());
      personalData.setSurname2(info.getLastname2());
      personalData.setWebSite(info.getWebSite());
      personalData.setDateOfBirthday(info.getBirthday());
      personalData.setCity(info.getCity());

      usersManager.modifyPersonalData(info.getId(), personalData);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setUserDegrees(long userId, List<String> degrees)
   {
      usersManager.modifyDegrees(userId, degrees);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setUserJobs(long userId, List<String> jobs)
   {
      usersManager.modifyJobs(userId, jobs);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setUserAccount(long userId, String newUsername, String newPassword)
   {
      AccountData account = new AccountData();
      account.setAccount(newUsername);
      account.setPassword(newUsername);

      usersManager.modifyUserAccount(userId, account);

      return true;
   }

   @Override
   @HandleBooleanException
   public boolean deleteUser(long userId)
   {
      usersManager.deleteUser(userId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean validate(String username, String password)
   {
      User user = usersManager.getUserByUsername(username);
      
      if(user == null)
         return false;
      
      if(user.getGoogleAccount() == null)
         return false;
      
      if(user.getGoogleAccount().getPassword() == null)
         return false;
      
      return user.getGoogleAccount().getPassword().equals(password);
   }

   @Override
   @HandleBooleanException
   public boolean validateAgainstGoogle(String username, String password)
   {
      return  new GoogleContactsProxy().validate(username, password);
   }

}