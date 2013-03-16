package com.armandorv.cnpd.client;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.client.application.JNDIHelper;
import com.armandorv.cnpd.client.presenter.ErrorPresenter;

/**
 * Class with the main method, create a Weld container and launch de application the application.
 * 
 * @author armandorv
 * 
 */
public class Launcher
{
   /**
    * Launch the application by executing the runnable injected.
    */
   public void launch()
   {
      if (this.checkConnection())
      {
         Weld weld = new Weld();
         WeldContainer container = weld.initialize();
         container.event().select(ContainerInitialized.class);
         weld.shutdown();
      }
   }

   /**
    * Check if the business tier are available.
    * 
    * @return
    */
   private boolean checkConnection()
   {
      try
      {
         IUsersManager manager = new JNDIHelper<IUsersManager>(JNDIHelper.USERS_MANAGER_NAME)
               .doLookup();
         manager.getUserByUsername("none");

         return true;
      }
      catch (Exception e)
      {
         new ErrorPresenter(e).present(null);

         return false;
      }
   }

   public static void main(String[] args)
   {
      new Launcher().launch();
   }

}