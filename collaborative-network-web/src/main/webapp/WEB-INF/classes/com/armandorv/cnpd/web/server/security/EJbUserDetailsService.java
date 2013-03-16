package com.armandorv.cnpd.web.server.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.User;

/**
 * Custom implementation of UserDetailsService (SpringSecurity interface) which
 * authenticate against my EJB tier.
 * 
 * @author armandorv
 * 
 */
public class EJbUserDetailsService implements UserDetailsService
{
   private static Logger log = Logger.getLogger(EJbUserDetailsService.class);

   /**
    * Injected by Spring from jndi.
    */
   private IUsersManager usersManager;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
   {
      if (usersManager == null)
         throw new IllegalArgumentException("Users manager resolved null");

      log.info("Attempting logging for:" + username);

      User user = usersManager.getUserByUsername(username);
      UserDetails details = new UserDetailsAdaptor(user);

      log.info("User logued");

      return details;
   }

   /**
    * Setter for Spring dependency injection.
    * 
    * @param usersManager
    */
   public void setUsersManager(IUsersManager usersManager)
   {
      this.usersManager = usersManager;
   }

}