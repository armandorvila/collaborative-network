package com.armandorv.cnpd.web.server.smack;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.armandorv.cnpd.web.shared.exception.AlreadyConnectedException;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.exception.UnconnectedUserException;

/**
 * Template method class which give support for smack operations, smack is an
 * XMPP client used in this application to connect against Google Talk XMPP
 * server. This class hold a map with a connection per connected user.
 * 
 * @author armandorv
 * 
 */
public abstract class SmackSupport
{
   private static final ResourceBundle gtalkProperties = ResourceBundle.getBundle("gtalk");

   private static final String host = gtalkProperties.getString("google.talk.host");

   private static final String port = gtalkProperties.getString("google.talk.port");

   private static final String serviceName = gtalkProperties.getString("google.talk.serviceName");

   private static Logger log = Logger.getLogger(SmackSupport.class);

   private Map<String, SmackChannel> channels = new ConcurrentHashMap<String, SmackChannel>();

   private ConnectionConfiguration config = new ConnectionConfiguration(host, Integer.parseInt(port), serviceName);

   /**
    * Connect with Google Talk server.
    * 
    * @param username
    *            username of an authenticated user an valid.
    * @param password
    *            password of an authenticated an valid user.
    * @param chatListener
    * 
    * @throws UnconnectedUserException
    *             if there is any problem connecting user.
    */
   protected void connect(String username, String password, ChatListener chatListener, MessageListener messageListener)
   {
      try
      {
         log.info("Connecting user" + username + "to chat");
         
         if (!channels.containsKey(username))
         {
            SmackChannel kit = new SmackChannel(new XMPPConnection(config), chatListener, messageListener);
            channels.put(username, kit);
         }

         Connection connection = channels.get(username).getConnection();
         connection.getChatManager().addChatListener(chatListener);

         if (!connection.isConnected())
            connection.connect();

         if (!connection.isAuthenticated())
            connection.login(username, password);
      }
      catch (XMPPException e)
      {
         log.error("the conection was imposible.");
         new AlreadyConnectedException(e.getMessage(), e);
      }
   }

   /**
    * Create chat manager if no exists, and return a smack chat with given
    * user.
    * 
    * @param to
    *            user@gmail of an user in the system.
    * @return Chat with given contact.
    */
   protected Chat createNewChat(String username, String to)
   {
      SmackChannel channel = channels.get(username);

      if (channel != null && channel.getConnection().isConnected())
      {
         log.info("Creating chat with " + to);

         if (!channel.getConnection().getRoster().contains(to))
         {
           log.warn("Unrostered users, user must be have all contacts in their roster, " +
           		"it use to be caused by connection problems.");
         }

         return channels.get(username).getConnection().getChatManager()
               .createChat(to, channels.get(username).getMessageListener());
      }
      else
      {
         log.debug("You must be connected to create a chat.");
         throw new UnconnectedUserException("You must be connected.");
      }
   }

   protected void addContactToRoster(String username, String contactUsername)
   {
      SmackChannel channel = channels.get(username);

      if (!channel.getConnection().getRoster().contains(contactUsername))
      {
         try
         {
            channel.getConnection().getRoster().createEntry(contactUsername, contactUsername, null);
            log.info("Add user " + contactUsername + " to roster of " + username);
         }
         catch (XMPPException e)
         {
            log.error("Error adding contact" + contactUsername + "to roster");
            throw new ServersideException("Error creating roster entry for " + contactUsername + " at " + username
                  + " roster.", e);
         }
      }
   }

   /**
    * Disconnect the user from GTalk and remove their connection.
    * 
    * @param username
    *            username of user that you want disconnect.
    */
   protected void disconnect(String username)
   {
      SmackChannel channel = channels.get(username);

      if (channel != null)
      {
         channel.getConnection().disconnect();
         channels.remove(username);
      }
      else
      {
         throw new UnconnectedUserException("User is not connected to GTalk.");
      }

   }

}