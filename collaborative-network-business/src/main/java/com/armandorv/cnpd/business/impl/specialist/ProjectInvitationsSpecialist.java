package com.armandorv.cnpd.business.impl.specialist;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.IllegalResolutionException;
import com.armandorv.cnpd.business.exception.InvalidInvitationException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.CnpdStrings;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.NotificationKind;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.INotificationDao;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.persistence.IUserDao;

@HandleBusinessException
public class ProjectInvitationsSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private IProjectDao projectDao;

   @Inject
   private INotificationDao notificationDao;

   private FindByIdExecutor<User> findUserById;

   private FindByIdExecutor<Project> findProjectById;

   @PostConstruct
   public void setUp()
   {
      findUserById = new FindByIdExecutor<User>(userDao);
      findProjectById = new FindByIdExecutor<Project>(projectDao);
   }

   public Set<Project> getProjectInvitations(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Project> invitations = user.getProjectInvitations();
      invitations.size();

      return invitations;

   }

   public void sendInvitationToProject(long userId, long projectId)
   {
      User user = findUserById.findById(userId);

      Project project = findProjectById.findById(projectId);

      if (user.getProjectInvitations().contains(project))
         throw new InvalidInvitationException(
               "There is already a project invitation, user " + userId
                     + "project " + projectId);

      if (user.getProjects().contains(project))
         throw new InvalidInvitationException("User " + userId
               + " is already a member of project " + projectId);

      user.addProjectInvitation(project);
      notificationDao.persist(buildProjectInvitationNotification(user, project));

   }

   private Notification buildProjectInvitationNotification(User addresse, Project cause)
   {
      Notification notification = new Notification();

      notification.setDate(new Date());
      notification.setKind(NotificationKind.PROJECT_INVITATION);
      notification.setUser(addresse);
      notification.setMessage(CnpdStrings.getProjectInvitationNotification());
      notification.setThirdPartId(cause.getId());
      notification.setNotified(false);

      return notification;
   }

   public void resolveInvitation(long userId, long projectId, boolean accept)
   {
      User user = findUserById.findById(userId);

      Project project = findProjectById.findById(projectId);

      if (!user.getProjectInvitations().contains(project))
         throw new IllegalResolutionException(
               "No project invitation user :" + userId + " project :"
                     + projectId);

      if (accept)
      {
         user.addProject(project);
         project.addMember(user);
      }

      user.removeProjectInvitation(project);

   }

}