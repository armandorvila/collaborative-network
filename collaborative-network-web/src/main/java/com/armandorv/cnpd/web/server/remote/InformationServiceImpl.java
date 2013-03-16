package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Messages;
import com.armandorv.cnpd.web.shared.qualifiers.Notifications;
import com.armandorv.cnpd.web.shared.remote.InformationService;

@Service
@ApplicationScoped
public class InformationServiceImpl implements InformationService
{
   private static Logger log = Logger.getLogger(InformationServiceImpl.class);

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IContactsManager contactsManager;

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   @Notifications
   private Mapper<Notification, NotificationInfo> notificationMapper;

   @Inject
   @Messages
   private Mapper<Message, MessageInfo> messageMapper;

   @Override
   @HandleServersideException
   public List<NotificationInfo> getNotifications(long userId)
   {
      List<NotificationInfo> notifications = new ArrayList<NotificationInfo>();

      Set<Notification> businessNotifications = usersManager.getNotifications(userId);

      for (Notification notification : businessNotifications)
         notifications.add(notificationMapper.map(notification));

      return notifications;
   }

   @Override
   @HandleBooleanException
   public boolean markNotified(NotificationInfo notification)
   {
      usersManager.markAsNotified(notification.getId());
      return true;
   }

   @Override
   @HandleServersideException
   public List<MessageInfo> getMessages(long userId)
   {
      List<MessageInfo> messages = new ArrayList<MessageInfo>();
      Set<Message> businessMessages = contactsManager.getMessages(userId);

      for (Message message : businessMessages)
      {
         messages.add(messageMapper.map(message));
      }

      return messages;
   }

   @Override
   @HandleServersideException
   public List<IdNameGenericInfo> retrieveAllKnowledgeAreas()
   {
      List<IdNameGenericInfo> areas = new ArrayList<IdNameGenericInfo>();
      List<KnowledgeArea> businessKnowledgeAreas = projectsManager.getAllKnowledgeAreas();

      for (KnowledgeArea knowledgeArea : businessKnowledgeAreas)
         areas.add(new IdNameGenericInfo(knowledgeArea.getId(), knowledgeArea.getName()));

      return areas;
   }

   @Override
   @HandleBooleanException
   public boolean sendMessage(String from, String to, String sendText, boolean mail)
   {
      log.info("Sending message from " + from + to + (mail ? " Mail active." : ""));

      User addresse = usersManager.getUserByUsername(to);
      User sender = usersManager.getUserByUsername(from);

      Message message = new Message();
      message.setAddressee(addresse);
      message.setSender(sender);
      message.setDate(new Date());
      message.setMail(mail);
      message.setContent(sendText);
      message.setRead(false);

      contactsManager.sendMessage(message);

      return true;
   }

   @Override
   @HandleBooleanException
   public boolean deleteMessage(long messageId)
   {
      contactsManager.deleteMessage(messageId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean markMessageAsRead(long messageId)
   {
      contactsManager.markMessageAsRead(messageId);
      return true;
   }

}