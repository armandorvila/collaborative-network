package com.armandorv.cnpd.business;

import java.util.List;
import java.util.Set;

import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;

/**
 * Interface to works with users, the users manager is concerned about all users
 * concerns.
 * 
 * @author armandorv
 * 
 */
public interface IUsersManager
{

   /**
    * Create a new user, all fields o new user must has not null values.
    * 
    * @param user
    *            the new user.
    */
   User createUser(User user);

   /**
    * Delete a new user, checking if users has projects and if user is manager
    * of any of their projects.
    * 
    * @param user
    */
   void deleteUser(long userId);

   /**
    * Set a new account for a given user.
    * 
    * @param user
    *            user to be modified.
    * @param account
    *            new account, previously validated against google.
    */
   void modifyUserAccount(long userId, AccountData account);

   /**
    * Change user degrees, the given degrees list will replace the current user
    * degrees list.
    * 
    * @param user
    *            user to be modified.
    * @param degrees
    *            new list of user degrees.
    */
   void modifyDegrees(long userId, List<String> degrees);

   /**
    * Change user jobs, the given jobs list will replace the current user jobs
    * list.
    * 
    * @param user
    *            user to be modified.
    * @param degrees
    *            new list of user jobs.
    */
   void modifyJobs(long userId, List<String> jobs);

   /**
    * Modify user personal information, name , surname1 and birthday are
    * required.
    * 
    * @param user
    *            user to be modified.
    * @param personalData
    *            data to be set as user personal data.
    */
   void modifyPersonalData(long userId, PersonalData personalData);

   /**
    * Get a user given their username, username must be unique, if there is not
    * a user with given username null will be returned.
    * 
    * @param username
    *            username of desiered user.
    */
   User getUserByUsername(String username);

   /**
    * Retrieve user for a given identifier.
    * 
    * @param id
    *            identifier of user.
    * @return A User object, this user won't have their contacts neither their
    *         projects.
    */
   User getUserById(long id);

   /**
    * @return List with all users of system.
    * 
    */
   List<User> ListAllUser();

   /**
    * Retrieve all notifications for the given user.
    * 
    * @param user
    *            a user to get their notifications.
    * @return a List of users.
    */
   Set<Notification> getNotifications(long userId);

   /**
    * Set a given notification as notified.
    * 
    * @param idNotification
    *            identifier of notification.
    * 
    * @return The notification notified.
    */
   Notification markAsNotified(long idNotification);

}
