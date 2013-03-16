package com.armandorv.cnpd.web.client;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point of the GWT application, taking advantages of Errai @EntryPoint
 * annotation we avoid implements and declare as EntryPoint.
 * 
 * @author armandorv
 * 
 */
@EntryPoint
public class AppLoader
{
   private HandlerManager eventBus = new HandlerManager(null);
   
   private RootLayoutPanel container = RootLayoutPanel.get();

   @Inject
   private AppController appController;

   @AfterInitialization
   public void loadApp()
   {
      appController.startApp();
   }

   @Produces
   HandlerManager produceEventBus()
   {
      return eventBus;
   }
   
   @Produces
   RootLayoutPanel produceContainer(){
      return container;
   }

}