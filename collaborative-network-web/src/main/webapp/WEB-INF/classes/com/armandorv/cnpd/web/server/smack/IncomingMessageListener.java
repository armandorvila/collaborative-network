package com.armandorv.cnpd.web.server.smack;

import javax.enterprise.event.Event;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;
import com.armandorv.cnpd.web.shared.model.ChatMessage;

/**
 * Listen a message from smack client and execute the correct action, in this
 * case fire an event to client side. Must me only one listener per local
 * context.
 * 
 * @author armandorv
 * 
 */
public class IncomingMessageListener implements MessageListener
{
   private static Logger log = Logger.getLogger(IncomingMessageListener.class);
   
   private static String lastMessageID = "";

   private Event<ChatMessage> messageEvent;
   
   /**
    * It is the username who is this listener for.
    */
   private String username;

   public IncomingMessageListener(Event<ChatMessage> messageEvent , String username)
   {
      this.messageEvent = messageEvent;
      this.username = username;
   }

   @Override
   public void processMessage(Chat chat, Message message)
   {
      log.info(" Processing message as :" + username);

      log.info("Chat message :" + message.getPacketID() + " from " + message.getFrom() + "to " + message.getTo());

      if (lastMessageID.equals(message.getPacketID()))
      {
         log.info("Repeated message:" + message.getPacketID());
      }
      else
      {
         lastMessageID = message.getPacketID();

         String senderMail = message.getFrom().split("/")[0];

         messageEvent.fire(new ChatMessage(senderMail, username, message.getBody()));
      }
   }
}
