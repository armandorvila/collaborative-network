package com.armandorv.cnpd.web.client.presenter.contacts;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.messages.MessagePresenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class ContactsListPresenter implements Presenter
{
   public interface Display
   {
      void setContacts(List<ContactInfo> contacts);

      void addContact(ContactInfo contact);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getVisit();

      HasSelectionHandlers<Item> getMessage();

      HasSelectionHandlers<Item> getDelete();

      ContactInfo getContact(int row);

      void removeContact(ContactInfo selected);

      void showProgress();
   }

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private Caller<InformationService> informationService;

   @Inject
   private ContactPresenter contactPresenter;

   @Inject
   private Display display;

   private ContactInfo selected;

   private UserInfo user;

   private boolean isStart = true;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getMessage().addSelectionHandler(message());
      display.getDelete().addSelectionHandler(delete());
      display.getVisit().addSelectionHandler(visit());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      this.loadContacts();
      
      if (isStart)
      {
         display.showProgress();
         isStart = false;
      }
   }

   private void loadContacts()
   {
      this.contactsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         public void callback(List<ContactInfo> response)
         {
            display.setContacts(response);
         }
      }).getContacts(user.getId());

   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            int row = event.getContext().getIndex();
            selected = display.getContact(row);
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

   private SelectionHandler<Item> message()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            new MessagePresenter(informationService, user.getUsername(), selected.getGmailAddress()).present();
         }
      };
   }

   private SelectionHandler<Item> delete()
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
                     Info.display("Notification", "Contact removed.");
                     display.removeContact(selected);
                  }
                  else
                  {
                     Info.display("Notification", "Error removing contact.");
                  }
               }
            }).removeContact(user.getId(), selected.getId());
         }
      };
   }

   public void addNewContact(@Observes @Contacts ContactInfo contact)
   {
      display.addContact(contact);
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}