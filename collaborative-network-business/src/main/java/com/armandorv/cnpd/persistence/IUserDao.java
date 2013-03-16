package com.armandorv.cnpd.persistence;

import java.util.List;

import com.armandorv.cnpd.model.User;

/**
 * Extends the basic {@link IDao} class to add some useful methods for users.
 * 
 * @author armandorv
 *
 */
public interface IUserDao extends IDao<User>
{
   /**
    * Get a user by their username.
    * 
    * @param username
    *            a user name of {@link AccountData} class.
    * @return the user who match with given username or null;
    */
   public User findByUsername(String username);

   /**
    * @return  all users who has a invitation to a given project.
    */
   public List<User> findProjectInvitedUsers(Long projectId);
   
   /**
    * @return  all users who has a invitation to a given meeting.
    */
   public List<User> findMeetingInvitedUsers(Long meetingId);

}
