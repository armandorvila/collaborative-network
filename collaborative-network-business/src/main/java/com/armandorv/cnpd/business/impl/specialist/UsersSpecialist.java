package com.armandorv.cnpd.business.impl.specialist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.mail.MailHelper;
import com.armandorv.cnpd.business.impl.mail.MailProperties;
import com.armandorv.cnpd.business.impl.util.CnpdStrings;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with users, is used by users manager, It must be within
 * persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class UsersSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private ProjectsSpecialist projectsSepcialist;

   @Inject
   private MailHelper mailHelper;

   private FindByIdExecutor<User> findUserById;

   @PostConstruct
   public void setUp()
   {
      findUserById = new FindByIdExecutor<User>(userDao);
   }

   public User getUserById(long id)
   {
      return userDao.findById(id);
   }

   public List<User> ListAllUser()
   {
      return userDao.findAll();
   }

   public User getUserByUsername(String username)
   {
      return userDao.findByUsername(username);
   }

   public void modifyUserAccount(long userId, AccountData account)
   {
      User user = findUserById.findById(userId);
      user.setGoogleAccount(account);
   }

   public void modifyDegrees(long userId, List<String> degrees)
   {
      User user = findUserById.findById(userId);
      user.setDegrees(degrees);
   }

   public void modifyJobs(long userId, List<String> jobs)
   {
      User user = findUserById.findById(userId);
      user.setJobs(jobs);
   }

   public void modifyPersonalData(long userId, PersonalData personalData)
   {
      User user = findUserById.findById(userId);
      user.setPersonalData(personalData);
   }

   public User createUser(User user)
   {
      userDao.persist(user);
      return user;
   }

   public void deleteUser(long userId)
   {
      User user = findUserById.findById(userId);

      for (User contact : user.getContacts())
      {
         contact.removeContact(user);
         user.removeContact(user);
      }

      Set<Project> projects = user.getProjects();

      if (user.getProjects().size() == 0)
      {
         userDao.remove(user);
      }
      else
      {
         List<Project> toDelete = new ArrayList<Project>();

         for (Project project : projects)
         {

            if (project.getMembers().size() > 1)
            {
               if (project.getManagerId().equals(userId))
               {
                  this.reasignManagerStatus(project, user);
               }
            }

            project.removeMember(user);

            if (project.getMembers().isEmpty())
               toDelete.add(project);

         }

         userDao.remove(user);

         for (Project project : toDelete)
         {
            projectsSepcialist.deleteProject(project.getId());
         }

         mailHelper.sendMail(MailProperties.Properties().getManagerMail(),
               user.getGoogleAccount().getAccount(), CnpdStrings.getUserElimintationSubject(),
               CnpdStrings.getUserEliminationText());
      }
   }

   /**
    * Set another manager to a project.
    */
   private void reasignManagerStatus(Project project, User oldManager)
   {

      int size = project.getMembers().size();
      int index = new Random().nextInt(size);

      User manager = null;

      int i = 0;
      while (i < index)
         manager = project.getMembers().iterator().next();

      if (manager.equals(oldManager))
         reasignManagerStatus(project, oldManager);

      project.setManagerId(manager.getId());
   }
}