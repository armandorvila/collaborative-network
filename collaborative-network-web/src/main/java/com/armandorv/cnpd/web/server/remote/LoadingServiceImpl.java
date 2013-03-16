package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.armandorv.cnpd.business.IContactsManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.exception.UnAuthenticatedUserException;
import com.armandorv.cnpd.web.shared.exception.UnconnectedUserException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.ContactMinus;
import com.armandorv.cnpd.web.shared.qualifiers.ContactPlus;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.qualifiers.Users;
import com.armandorv.cnpd.web.shared.remote.LoadingService;

/**
 * 
 * @author armandorv
 * 
 */
@Service
@ApplicationScoped
public class LoadingServiceImpl implements LoadingService
{
   private static Logger log = Logger.getLogger(LoadingServiceImpl.class);

   private Set<String> connectedUsers = new ConcurrentSkipListSet<String>();

   @Inject
   @ContactPlus
   private Event<ContactInfo> connectedContact;

   @Inject
   @ContactMinus
   private Event<ContactInfo> disconnectedContact;

   @Inject
   @Users
   private Mapper<User, UserInfo> userMapper;

   @Inject
   @Contacts
   private Mapper<User, ContactInfo> contactMapper;

   @Inject
   private ChatServiceImpl chatService;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IContactsManager contactsManager;

   private static LoadingService me = null;

   @PostConstruct
   public void postConstruct()
   {
      me = this;
   }

   @Override
   @HandleServersideException
   public UserInfo loadCurrentUser()
   {
      //TODO try another way
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null)
         throw new UnAuthenticatedUserException("There is no authenticated user");

      String username = authentication.getName();

      UserInfo userInfo = userMapper.map(usersManager.getUserByUsername(username));

      return userInfo;
   }

   @Override
   @HandleBooleanException
   public boolean connectCurrentUser(String username)
   {
      if (!connectedUsers.contains(username))
      {
         connectedUsers.add(username);

         ContactInfo contact = contactMapper.map(usersManager.getUserByUsername(username));
 
         if(!chatService.connect(username))
            return false;
            
         connectedContact.fire(contact);

         log.info("Added user to connected list:" + username);
      }

      return true;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getConnectedContacts(String username)
   {
      List<ContactInfo> result = new ArrayList<ContactInfo>();

      Set<User> contacts = contactsManager.getContacts(usersManager.getUserByUsername(username).getId());

      for (User user : contacts)
      {
         if (connectedUsers.contains(user.getGoogleAccount().getAccount()))
            result.add(contactMapper.map(user));
      }

      return result;
   }

   @Override
   @HandleBooleanException
   public boolean disconnectCurrentUser(String username)
   {
      log.info("Disconnecting user:" + username);

      if (!connectedUsers.contains(username))
         throw new UnconnectedUserException("User must be connected !!" + username);

      this.connectedUsers.remove(username);

      this.chatService.disconnectt(username);

      ContactInfo contact = contactMapper.map(usersManager.getUserByUsername(username));

      this.disconnectedContact.fire(contact);

      return true;
   }

   /**
    * To get static access to the application scoped instance of this class.
    * 
    * @return null if CDI context hasn't be loaded yet.
    */
   public static synchronized LoadingService getAppScoppedService()
   {
      return me;
   }

   @Override
   @HandleServersideException
   public Set<String> getConnectedUsers()
   {
      return connectedUsers;
   }

}