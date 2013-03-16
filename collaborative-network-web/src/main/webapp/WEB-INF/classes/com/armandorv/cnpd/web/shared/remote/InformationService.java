package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;

/**
 * Service which define operations to retrieve information.
 * 
 * @author armandorv
 * 
 */
@Remote
public interface InformationService
{
   /**
    * Retrieve all notifications for the user.
    * 
    */
   List<NotificationInfo> getNotifications(long userId);

   /**
    * Mark a request as notified.
    * 
    * @param notification
    */
   boolean markNotified(NotificationInfo notification);

   /**
    * Retrieve all messages for this user.
    */
   List<MessageInfo> getMessages(long userId);

   /**
    * @return all knowledge areas.
    */
   List<IdNameGenericInfo> retrieveAllKnowledgeAreas();

   boolean sendMessage(String from, String to, String sendText, boolean mail);

   boolean deleteMessage(long messageId);

   boolean markMessageAsRead(long messageId);
}
