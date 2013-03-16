package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.INotificationDao;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with notifications concerns, is used by users manager, it
 * must be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class NotificationsSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private INotificationDao notificationDao;

   private FindByIdExecutor<User> findUserById;

   private FindByIdExecutor<Notification> findNotificationById;

   @PostConstruct
   public void setUp()
   {
      findUserById = new FindByIdExecutor<User>(userDao);
      findNotificationById = new FindByIdExecutor<Notification>(
            notificationDao);
   }

   public Set<Notification> getNotifications(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Notification> notifications = user.getNotifications();
      notifications.size();

      return notifications;
   }

   public Notification markAsNotified(long IdNotification)
   {
      Notification notification = findNotificationById
            .findById(IdNotification);
      notification.setNotified(true);

      return notification;
   }

}