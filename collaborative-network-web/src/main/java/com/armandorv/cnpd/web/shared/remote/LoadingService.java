package com.armandorv.cnpd.web.shared.remote;

import java.util.List;
import java.util.Set;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;

/**
 * Service for give support to client side during loading period.
 * 
 * @author armandorv
 * 
 */
@Remote
public interface LoadingService
{
   /**
    * 
    * @return a presentation object with current user information and add this
    *         user to server holder as connected user.
    */
   UserInfo loadCurrentUser();

   /**
    * Say hello rest of users, to add of their chat.
    * 
    * @param username
    *            username of connected user.
    */
   boolean connectCurrentUser(String username);

   /**
    * @return all connected contacts of user.
    */
   List<ContactInfo> getConnectedContacts(String username);

   /**
    * @return All connected user names. (call getConnectedContacts if posible).
    */
   Set<String> getConnectedUsers();

   /**
    * Remove a user from connected users list.
    */
   boolean disconnectCurrentUser(String username);

}
