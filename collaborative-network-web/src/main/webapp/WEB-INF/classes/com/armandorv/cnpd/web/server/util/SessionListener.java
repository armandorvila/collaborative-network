package com.armandorv.cnpd.web.server.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.armandorv.cnpd.web.server.remote.LoadingServiceImpl;
import com.armandorv.cnpd.web.shared.remote.LoadingService;

/**
 * Listener to disconnect users.
 * 
 * @author armandorv
 * 
 */
public class SessionListener implements HttpSessionListener
{

   private static Logger log = Logger.getLogger(SessionListener.class);

   @Override
   public void sessionCreated(HttpSessionEvent sessionEvent)
   {
      log.info("--------New Session created--------.");

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      LoadingService loading = LoadingServiceImpl.getAppScoppedService();

      /*
       * Only for debugging reasons, i want know how many sessions are created
       * ,when and for whom.
       */

      if (auth == null)
      {
         log.info("There 's no user logued");

      }
      if (loading == null)
      {
         log.info("No CDI initialized.");
      }

      log.info("-------------------------------------");

   }

   @Override
   public void sessionDestroyed(HttpSessionEvent sessionEvent)
   {
      log.info("--------Session destroyed--------");

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      LoadingService loading = LoadingServiceImpl.getAppScoppedService();

      if (auth != null && loading != null)
      {
         log.info("Desconnecting user:" + auth.getName());
         loading.disconnectCurrentUser(auth.getName());
      }

      if (auth == null)
      {
         log.info("No user logued");

      }
      if (loading == null)
      {
         log.info("No CDI initialized.");
      }

      log.info("-------------------------------------");

   }

}
