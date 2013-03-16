package com.armandorv.cnpd.business.impl.specialist;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.IllegalResolutionException;
import com.armandorv.cnpd.business.exception.InvalidRequestException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.CnpdStrings;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.NotificationKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.INotificationDao;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with contacts requests concerns, is used by contacts
 * manager, It must be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class RequestsSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private INotificationDao notificationDao;

   private FindByIdExecutor<User> findUserById;

   @PostConstruct
   public void setUp()
   {
      this.findUserById = new FindByIdExecutor<User>(userDao);
   }

   public void resolveRequest(long userId, long contactId, boolean accept)
   {
      User user = findUserById.findById(userId);

      User requester = findUserById.findById(contactId);

      if (!user.getContactRequests().contains(requester))
         throw new IllegalResolutionException("No request of " + contactId
               + " for " + userId);

      if (user.getContacts().contains(requester))
         throw new IllegalResolutionException("Repeated contact "
               + contactId + " for " + userId);

      if (accept)
      {
         user.addContact(requester);
         requester.addContact(user);
         
         if (!requester.getContactRequests().contains(user))
            requester.removeContactRequest(user);
         

         Notification notification = buildNotification(requester, user,
               NotificationKind.CONTACT_ACEPPTED,
               CnpdStrings.getContactAcceptedNotification());
         notificationDao.persist(notification);
      }

      user.removeContactRequest(requester);
   }

   private Notification buildNotification(User addresse, User cause,
         NotificationKind kind, String message)
   {
      Notification notification = new Notification();

      notification.setKind(kind);
      notification.setMessage(message);
      notification.setUser(addresse);
      notification.setThirdPartId(cause.getId());
      notification.setDate(new Date());
      notification.setNotified(false);

      return notification;
   }

   public void addContactRequest(long requestedId, long requesterId)
   {
      if (requestedId == requesterId)
         throw new InvalidRequestException(
               "Requested and requester are the same: " + requestedId);

      User requested = findUserById.findById(requestedId);

      User requester = findUserById.findById(requesterId);

      Set<User> requestedRequests = requested.getContactRequests();

      if (requestedRequests.contains(requester))
         throw new InvalidRequestException(
               "There is already a request betwen " + requestedId
                     + " and " + requesterId);

      if (requested.getContacts().contains(requester))
         throw new InvalidRequestException("Requester (" + requesterId
               + ") is already a contact of " + requested);

      requested.addContactRequest(requester);  

      Notification notification = buildNotification(requested, requester,
            NotificationKind.CONTACT_REQUEST,
            CnpdStrings.getContactRequestNotification());

      notificationDao.persist(notification);
   }

   public Set<User> getContactRequests(long userId)
   {

      User user = findUserById.findById(userId);

      Set<User> requests = user.getContactRequests();
      requests.size();

      return requests;
   }
}