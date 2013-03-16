package com.armandorv.cnpd.web.client.presenter.contacts;

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
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class ContactRequestsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      ContactInfo getRequest(int row);

      void removeRequest(ContactInfo request);

      void setRequests(List<ContactInfo> requests);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getVisit();

      HasSelectionHandlers<Item> getAccept();

      HasSelectionHandlers<Item> getDenied();

      void addErrorMessagee(String string);

      void addSuccessMessage(String string);
   }

   private UserInfo user;

   private ContactInfo selected;

   @Inject
   private Display display;

   @Inject
   @Contacts
   private Event<ContactInfo> addCotnactEvent;

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private ContactPresenter contactPresenter;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(this.select());
      display.getAccept().addSelectionHandler(this.acceptRequest());
      display.getDenied().addSelectionHandler(this.refuseRequest());
      display.getVisit().addSelectionHandler(this.visit());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      this.loadRequests();
   }

   private void loadRequests()
   {
      this.contactsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         public void callback(List<ContactInfo> response)
         {
            display.setRequests(response);
         }
      }).getContactRequests(user.getId());
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {

         public void onSelect(SelectEvent event)
         {
            selected = display.getRequest(event.getContext().getIndex());
         }
      };
   }

   private SelectionHandler<Item> acceptRequest()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            contactsService.call(new RemoteCallback<Boolean>()
            {
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     display.removeRequest(selected);
                     addCotnactEvent.fire(selected);
                     display.addSuccessMessage("Request accepted.");
                  }
                  else
                  {
                     display.addErrorMessagee("Error to add contact, try againg later.");
                  }
               }
            }).addAsContact(user.getId(), selected.getId());
         }
      };
   }

   private SelectionHandler<Item> refuseRequest()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            contactsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     display.removeRequest(selected);
                     display.addSuccessMessage("Request refust.");
                  }
                  else
                  {
                     display.addErrorMessagee("Error resolving request.");
                  }
               }
            }).deniedCotnactRequest(user.getId(), selected.getId());
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

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}