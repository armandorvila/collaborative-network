package com.armandorv.cnpd.web.server.smack;

import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.MessageListener;

public class SmackChannel
{
   private Connection connection;

   private ChatManagerListener chatListner;

   private MessageListener messageListener;

   public SmackChannel(Connection connection, ChatManagerListener chatListner, MessageListener messageListener)
   {
      super();
      this.connection = connection;
      this.chatListner = chatListner;
      this.messageListener = messageListener;
   }

   public Connection getConnection()
   {
      return connection;
   }

   public ChatManagerListener getChatListner()
   {
      return chatListner;
   }

   public MessageListener getMessageListener()
   {
      return messageListener;
   }

}
