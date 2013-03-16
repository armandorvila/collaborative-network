package com.armandorv.cnpd.business.impl;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.specialist.NotificationsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.UsersSpecialist;
import com.armandorv.cnpd.business.local.IUsersManagerLocal;
import com.armandorv.cnpd.business.remote.IUsersManagerRemote;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;

/**
 * Implementation of UsersManager Facade with Remote and local EJB capabilities.
 * This class need to be transactional in order to work fine. This class
 * delegate all methods to more specialist classes.
 * 
 * @author armandorv
 * 
 */

@Stateless
public class UsersManager implements IUsersManagerRemote, IUsersManagerLocal
{

   @Inject
   private NotificationsSpecialist notificationsSpecialist;

   @Inject
   private UsersSpecialist usersSpecialist;

   @Override
   public User getUserById(long id)
   {
      return this.usersSpecialist.getUserById(id);
   }

   @Override
   public User getUserByUsername(String username)
   {
      return this.usersSpecialist.getUserByUsername(username);
   }

   @Override
   public List<User> ListAllUser()
   {
      return this.usersSpecialist.ListAllUser();
   }

   @Override
   public User createUser(User user)
   {
      return this.usersSpecialist.createUser(user);
   }

   @Override
   public void deleteUser(long userId)
   {
      this.usersSpecialist.deleteUser(userId);
   }

   @Override
   public void modifyUserAccount(long userId, AccountData account)
   {
      this.usersSpecialist.modifyUserAccount(userId, account);
   }

   @Override
   public void modifyDegrees(long userId, List<String> degrees)
   {
      this.usersSpecialist.modifyDegrees(userId, degrees);
   }

   @Override
   public void modifyJobs(long userId, List<String> jobs)
   {
      this.usersSpecialist.modifyJobs(userId, jobs);
   }

   @Override
   public void modifyPersonalData(long userId, PersonalData personalData)
   {
      this.usersSpecialist.modifyPersonalData(userId, personalData);
   }

   @Override
   public Set<Notification> getNotifications(long userId)
   {
      return notificationsSpecialist.getNotifications(userId);
   }

   @Override
   public Notification markAsNotified(long idNotification)
   {
      return this.notificationsSpecialist.markAsNotified(idNotification);
   }

}
