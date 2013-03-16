package com.armandorv.cnpd.web.client.presenter.notifications;

import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Singleton
public class NotificationsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setNotifications(List<NotificationInfo> notifications);

      void setAsNotified(NotificationInfo notification);

      void addNotification(NotificationInfo notification);

      Integer getIndex();

      HasSelectHandlers getSelectable();

      void showProgress();

      NotificationInfo getSelected(int row);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<InformationService> infoService;

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   @Main
   Event<Integer> tabSelectedEvent;

   private UserInfo user;

   private boolean isStart = true;

   @AfterInitialization
   public void afterInitialization()
   {
      this.display.getSelectable().addSelectHandler(showNotification());
   }

   @Override
   public void present()
   {
      if(user == null)
         throw new ClientsideException("User must be set previously.");
      
      this.loadNotifications();

      if (isStart)
      {
         display.showProgress();
         isStart = false;
      }

      tabSelectedEvent.fire(display.getIndex());
   }

   private void loadNotifications()
   {
      this.infoService.call(new RemoteCallback<List<NotificationInfo>>()
      {

         public void callback(List<NotificationInfo> response)
         {
            display.setNotifications(response);
         }
      }).getNotifications(user.getId());

   }

   private SelectHandler showNotification()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            int row = event.getContext().getIndex();
            NotificationInfo notification = display.getSelected(row);
            processNotification(notification);
         }
      };
   }

   /**
    * Call the correct RPC service to get more information about the selected
    * notification, and then, create a presenter for the notification.
    * 
    * @param notification
    *            notification to process
    */
   private void processNotification(final NotificationInfo notification)
   {
      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_REQUEST))
      {
         projectsService.call(new GetProjectCallback(new NotificationPresenter(infoService, display, notification)))
               .getProject(notification.getObject());
      }

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_ACCEPTED))
      {
         projectsService.call(new GetProjectCallback(new NotificationPresenter(infoService, display, notification)))
               .getProject(notification.getObject());
      }

      if (notification.getKind().equals(NotificationInfo.Kind.PROJECT_PUBLISHT))
      {
         projectsService.call(new GetProjectCallback(new NotificationPresenter(infoService, display, notification)))
               .getProject(notification.getObject());
      }

      if (notification.getKind().equals(NotificationInfo.Kind.CONTACT_REQUEST))
      {
         contactsService.call(new GetContactCallback(new NotificationPresenter(infoService, display, notification)))
               .getContact(notification.getObject());
      }

      if (notification.getKind().equals(NotificationInfo.Kind.CONTACT_ACEPPTED))
      {
         contactsService.call(new GetContactCallback(new NotificationPresenter(infoService, display, notification)))
               .getContact(notification.getObject());
      }
   }

   public void notification(@Observes NotificationInfo notification)
   {
      display.addNotification(notification);
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

   public Display getDisplay()
   {
      return display;
   }

}