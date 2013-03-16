package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IUserDao;
import com.armandorv.cnpd.persistence.exception.DuplicatedUsernameException;

/**
 * Class which encapsulate data access for users, it is necessary to be into a
 * managed context
 * 
 * @author armandorv
 * 
 */
public class UserDao extends JpaDaoSupport implements IUserDao
{
   private static Logger log = Logger.getLogger(UserDao.class);

   @Override
   public User findById(Long id)
   {
      return em.find(User.class, id);
   }

   @Override
   public List<User> findAll()
   {
      return em.createNamedQuery("User.findAll", User.class).getResultList();
   }

   @Override
   public void persist(User entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(User entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(User entity)
   {
      em.refresh(entity);
   }

   @Override
   public User findByUsername(String username)
   {

      TypedQuery<User> query = em.createNamedQuery("User.findByUsername",
            User.class);

      query.setParameter("account", username);

      List<User> result = query.getResultList();

      if (result.size() > 1)
      {

         log.error("Duplicated username :" + username + ", thre is:"
               + result.size() + "users.");

         throw new DuplicatedUsernameException("Username :" + username
               + " is duplicated");

      }

      if (result.size() == 0)
         return null;

      return result.get(0);
   }

   @Override
   public List<User> findProjectInvitedUsers(Long projectId)
   {

      TypedQuery<User> query = em.createNamedQuery("User.findProjectInvited",
            User.class);

      query.setParameter("projectId", projectId);

      return query.getResultList();
   }

   @Override
   public List<User> findMeetingInvitedUsers(Long meetingId)
   {
      TypedQuery<User> query = em.createNamedQuery("User.findMeetingInvited",
            User.class);

      query.setParameter("meetingId", meetingId);

      return query.getResultList();
   }

}