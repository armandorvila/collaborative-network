package com.armandorv.cnpd.web.client.presenter.contacts;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class FindUsersPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      String getSearchName();

      String getSearchLastname1();

      String getSearchLastname2();

      HasSelectHandlers getSearchButton();

      ContactInfo getFoundContact(int row);

      void setFoundContacts(List<ContactInfo> contacts);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getVisit();

      HasSelectionHandlers<Item> getRequest();

      void addErrorMessage(String string);

      void addSuccessMessage(String string);
   }

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private ContactPresenter contactPresenter;

   @Inject
   private Display display;

   private UserInfo user;

   private ContactInfo selected;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getSearchButton().addSelectHandler(search());
      display.getActions().addSelectHandler(select());
      display.getRequest().addSelectionHandler(sendRequest());
      display.getVisit().addSelectionHandler(visit());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            selected = display.getFoundContact(event.getContext().getIndex());
         }
      };
   }

   private SelectionHandler<Item> visit()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            contactPresenter.setContact(selected);
            contactPresenter.present();
         }
      };
   }

   private SelectionHandler<Item> sendRequest()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            contactsService.call(new RemoteCallback<Boolean>()
            {
               public void callback(Boolean response)
               {
                  if (!response)
                  {
                     contactsService.call(new RemoteCallback<Boolean>()
                     {
                        public void callback(Boolean response)
                        {
                           if (response)
                              display.addSuccessMessage("Request done successfully.");
                           else
                              display.addErrorMessage("Error sending your request, try again later.");
                        }
                     }).sendContactRequest(selected.getId(), user.getId());
                  }
                  else
                  {
                     display.addErrorMessage("You did already a request to this user.");
                  }
               }
            }).thereIsRequest(user.getId(), selected.getId());
         }
      };
   }

   private SelectHandler search()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            if ("".equals(display.getSearchName()) && "".equals(display.getSearchLastname1())
                  && "".equals(display.getSearchLastname2()))
            {
               Info.display("Error", "Youo must intoroduce at least one parameter.");
            }
            else
            {
               contactsService.call(new RemoteCallback<List<ContactInfo>>()
               {
                  @Override
                  public void callback(List<ContactInfo> response)
                  {
                     display.setFoundContacts(response);
                     
                     if(response.isEmpty())
                        Info.display("Notification" , "No users found.");
                  }
               }).searchContacts(user.getId(), display.getSearchName(), display.getSearchLastname1(),
                     display.getSearchLastname2());
            }
         }
      };
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}