package com.armandorv.cnpd.web.client;

import java.lang.annotation.Annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.MainPresenter;
import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter;
import com.armandorv.cnpd.web.client.util.HistoryEventRegister;
import com.armandorv.cnpd.web.client.util.HistoryToken;
import com.armandorv.cnpd.web.client.util.PresenterRetriever;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.LoadingService;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Manage logic that is not specific of any presenter and deals with history
 * management.
 * 
 * @author armandorv
 * 
 */
@ApplicationScoped
public class AppController implements ValueChangeHandler<String>
{
   @Inject
   private HandlerManager eventBus;
   
   @Inject
   private PresenterRetriever presenterRetriever;

   @Inject
   private Caller<LoadingService> loadingService;

   @Inject
   private Event<UserInfo> loadCurrentUser;

   public void startApp()
   {     
      HistoryEventRegister eventRegister = new HistoryEventRegister(eventBus);
      eventRegister.registerHistoryManagementEvents();

      if ("signup".equals(History.getToken()))
      {
         Presenter signUpPresnter = presenterRetriever.retrievePresenter(GoogleFormPresenter.class);
         signUpPresnter.present();
      }
      else
      {
         if (RootPanel.get("app_id") == null)
         {
            Window.Location.assign("/login.jsp");
         }
         else
         {
            final ValueChangeHandler<String> handler = this;
            
            this.loadingService.call(new RemoteCallback<UserInfo>()
            {
               @Override
               public void callback(UserInfo response)
               {
                  Presenter mainPresenter =  presenterRetriever.retrievePresenter(MainPresenter.class);
                  NotificationsPresenter notificationsPresenter =  (NotificationsPresenter)presenterRetriever.retrievePresenter(NotificationsPresenter.class);
                  notificationsPresenter.setUser(response);
                  
                  loadCurrentUser.fire(response);
                 
                  History.addValueChangeHandler(handler);
                  History.newItem(HistoryToken.NOTIFICATIONS.getTokenName());
                  
                  mainPresenter.present();
                  notificationsPresenter.present();
               }
            }).loadCurrentUser();
         }
      }
   }

   @Override
   public void onValueChange(ValueChangeEvent<String> event)
   {
      String token = event.getValue();

      if (token != null)
      {
         Presenter presenter = presenterRetriever.retrievePresenter(HistoryToken.getFromString(token));
         if (presenter != null)
         {
            presenter.present();
         }
         else {
            throw new ClientsideException("No presenter found for current history token.");
         }
      }
   }

   public Annotation getQualifierAsAnnotation(final Class<? extends Annotation> type)
   {
      return new Annotation()
      {
         @Override
         public Class<? extends Annotation> annotationType()
         {
            return type;
         }
      };
   }

}