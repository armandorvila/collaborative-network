package com.armandorv.cnpd.web.client.presenter.notifications;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.view.notifications.NotificationView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Show notification and if use accept mark it as notified.
 * 
 * @author armandorv
 * 
 */
public class NotificationPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      HasSelectHandlers getAccept();

      HasSelectHandlers getCancel();

      void setNotificationText(String text);

      void setNotificationObject(String text);

      void setSpecificLabel(String specificLabel);
   }

   private Caller<InformationService> informationService;

   private Display display = new NotificationView();

   private NotificationInfo notification;

   private String objectName = "";

   private NotificationsPresenter.Display notificationsView;

   public NotificationPresenter(Caller<InformationService> service, NotificationsPresenter.Display notificationsView,
         NotificationInfo notification)
   {
      this.informationService = service;
      this.notification = notification;
      this.notificationsView = notificationsView;
   }

   /** Container is ignored. */
   @Override
   public void present ()
   {
      display.getAccept().addSelectHandler(this.accept());
      display.getCancel().addSelectHandler(this.cancel());

      display.setNotificationText(this.selectCorrectText());
      display.setNotificationObject(objectName);
      display.setSpecificLabel(this.getSpecificLabel());

      display.asWindow().show();
   }

   private String getSpecificLabel()
   {
      if (notification.getKind().equals(NotificationInfo.Kind.CONTACT_REQUEST)
            || notification.getKind().equals(NotificationInfo.Kind.CONTACT_ACEPPTED))
         return "Contact";

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_ACCEPTED)
            || notification.getKind().equals(NotificationInfo.Kind.PROJECT_PUBLISHT)
            || notification.getKind().equals(NotificationInfo.Kind.PROJECT_PUBLISHT))
         return "Project";

      return "";
   }

   private String selectCorrectText()
   {
      if (notification.getKind().equals(NotificationInfo.Kind.CONTACT_REQUEST))
         return Notifications.CONTACT_REQUEST_GENERIC_TEXT;

      if (notification.getKind().equals(NotificationInfo.Kind.CONTACT_ACEPPTED))
         return Notifications.CONTACT_ACCEPT_GENERIC_TEXT;

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_REQUEST))
         return Notifications.PROJECT_REQUEST_GENERIC_TEXT;

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_ACCEPTED))
         return Notifications.PROJECT_ACCEPT_GENERIC_TEXT;

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_PUBLISHT))
         return Notifications.PROJECT_PUBLISHT_GENERIC_TEXT;

      return "";
   }

   private SelectHandler cancel()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            display.asWindow().hide();
         }
      };
   }

   private SelectHandler accept()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            informationService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (!response)
                  {
                     throw new ClientsideException("Error resolving notification.");
                  }
                  else
                  {
                     notification.setNew(false);
                     notificationsView.setAsNotified(notification);
                  }
               }
            }).markNotified(notification);
            display.asWindow().hide();
         }
      };
   }

   public void setNotificationObjectName(String objectName)
   {
      this.objectName = objectName;
   }

}