package com.armandorv.cnpd.web.server.remote;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.smack.ChatListener;
import com.armandorv.cnpd.web.server.smack.IncomingMessageListener;
import com.armandorv.cnpd.web.server.smack.SmackSupport;
import com.armandorv.cnpd.web.shared.model.ChatMessage;
import com.armandorv.cnpd.web.shared.remote.ChatService;

@Service
@ApplicationScoped
public class ChatServiceImpl extends SmackSupport implements ChatService
{
   private static Logger log = Logger.getLogger(ChatServiceImpl.class);

   @Inject
   private IUsersManager usersManager;

   @Inject
   private Event<ChatMessage> messageEvent;

   @Override
   public boolean sendMessage(String gmailAddressFrom, String gmailAddressTo, String sentText)
   {
      log.info("Sending message from:" + gmailAddressFrom + " to " + gmailAddressTo);

      Chat chat = super.createNewChat(gmailAddressFrom, gmailAddressTo);

      try
      {
         chat.sendMessage(sentText);
         return true;
      }
      catch (XMPPException e)
      {
         log.error("XMPP Error, message not sent:" + e.getMessage());
         return false;
      }
   }

   @Override
   @HandleBooleanException
   public boolean addContactToChat(String username, String contactUsername)
   {
      super.addContactToRoster(username, contactUsername);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean connect(String username)
   {
      log.info("Connecting user " + username + " to chat.");

      User user = usersManager.getUserByUsername(username);

      MessageListener messageListener = new IncomingMessageListener(messageEvent, username);

      super.connect(username, user.getGoogleAccount().getPassword(), new ChatListener(messageListener, username),
            messageListener);

      return true;
   }

   @Override
   @HandleBooleanException
   public boolean disconnectt(String username)
   {
      super.disconnect(username);
      return true;
   }

}