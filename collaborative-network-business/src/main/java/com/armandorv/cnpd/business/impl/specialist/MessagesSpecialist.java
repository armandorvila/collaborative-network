package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.InvalidMessageException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.mail.MailHelper;
import com.armandorv.cnpd.business.impl.util.CnpdStrings;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IMessageDao;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with messages concerns, is used by contacts manager and
 * must be within persistent context and be injected with @Inject, like all
 * specialists.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class MessagesSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private IMessageDao messageDao;

   @Inject
   private MailHelper mailer;

   private FindByIdExecutor<User> findUserById;

   private FindByIdExecutor<Message> findMessageById;

   @PostConstruct
   public void setUp()
   {
      this.findUserById = new FindByIdExecutor<User>(userDao);
      this.findMessageById = new FindByIdExecutor<Message>(messageDao);

   }

   public Set<Message> getMessages(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Message> messages = user.getMessages();
      messages.size();

      return messages;
   }

   public Message sendMessage(Message message)
   {
      if (message.getAddressee() == null)
         throw new InvalidMessageException("addresee mustn't be null");

      if (message.getSender() == null)
         throw new InvalidMessageException("sender mustn't be null");

      if (message.getAddressee().equals(message.getSender()))
         throw new InvalidMessageException(
               "sender mustn't be the same that addressee");

      messageDao.persist(message);

      if (message.isMail())
      {
         String from = message.getSender().getGoogleAccount().getAccount();
         String to = message.getAddressee().getGoogleAccount().getAccount();

         mailer.sendMail(from, to, CnpdStrings.getMessageSubject() + " " +  from,
               message.getContent());
      }

      return message;

   }

   public void deleteMessage(long messageId)
   {
      Message message = findMessageById.findById(messageId);
      messageDao.remove(message);
   }

   public void markMessageAsRead(long messageId)
   {
      Message message = findMessageById.findById(messageId);
      message.setRead(true);

   }

}