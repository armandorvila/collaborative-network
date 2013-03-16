package com.armandorv.cnpd.web.server.smack;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

/**
 * ChatListener implementation that prepare a message listener if a contact
 * create a chat with me.
 * 
 * @author armandorv
 * 
 */
public class ChatListener implements ChatManagerListener
{

   private static Logger log = Logger.getLogger(ChatListener.class);

   private MessageListener incomingMessageListener;
   
   private String username;

   public ChatListener(MessageListener incomingMessageListener , String username)
   {
      this.incomingMessageListener = incomingMessageListener;
      this.username = username;
   }

   @Override
   public void chatCreated(Chat chat, boolean createdLocally)
   {
      if (createdLocally)
      {
         log.info("A chat was created locally by" + username);
      }
      else
      { 
         log.info("A chat was created by:" + chat.getParticipant());
         chat.addMessageListener(incomingMessageListener);
      }
   }

}
