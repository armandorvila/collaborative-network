package com.armandorv.cnpd.web.shared.remote;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * Service that hold chat communications related concerns.
 * 
 * @author armandorv
 */
@Remote
public interface ChatService
{
   /**
    * Connect a user to the chat communications system.
    * 
    * @return true if all OK.
    */
   boolean connect(String username);

   /**
    * Send a message to a contact.
    * 
    * @param gmailAddress
    *            gmail address of addressee contact.
    * @param sentText
    *            text of the message.
    * @return true.
    */
   boolean sendMessage(String username, String gmailAddressTo, String sentText);

   /**
    * Disconnect a user from the chat communications system.
    * 
    * @return true if all OK.
    */
   boolean disconnectt(String username);

   /**
    * Add a contact to Chat contacts list of a user GTalk contacts list (Not google contacts).
    * 
    * @param username
    * @param contactUsername
    * 
    * @return true if all OK.
    */
   boolean addContactToChat(String username, String contactUsername);

}