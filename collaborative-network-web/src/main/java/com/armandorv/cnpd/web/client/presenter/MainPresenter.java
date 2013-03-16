package com.armandorv.cnpd.web.client.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.event.EntryContactsEvent;
import com.armandorv.cnpd.web.client.event.EntryMeetingsEvent;
import com.armandorv.cnpd.web.client.event.EntryMessagesEvent;
import com.armandorv.cnpd.web.client.event.EntryNotificationsEvent;
import com.armandorv.cnpd.web.client.event.EntryProjectsEvent;
import com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter;
import com.armandorv.cnpd.web.client.presenter.info.InfoPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter;
import com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter;
import com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ChatMessage;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.ContactMinus;
import com.armandorv.cnpd.web.shared.qualifiers.ContactPlus;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.LoadingService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter of Main view of this application, deal with chat and info panel,
 * and set up history support for tab selections. Delegate tab content
 * management to more concrete presenter, like NewPresenter.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class MainPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      HasSelectionHandlers<Widget> getSelecteablePanel();

      HasSelectHandlers getSelectableChat();

      void addContact(ContactInfo contact);

      void addContacts(List<ContactInfo> contacts);

      public void selectTab(Integer index);

      void removeContact(ContactInfo contact);

      HasSelectHandlers getDisconnectChat();

      void setConnected(boolean connected);
   }

   @Inject
   private Caller<LoadingService> loadingService;

   @Inject
   private Caller<ContactsService> cotnactsService;

   @Inject
   private Display display;

   @Inject
   private ConversationsPresenter conversationsPresenter;

   @Inject
   private InfoPresenter infoPresenter;

   @Inject
   private HandlerManager eventBus;

   @Inject
   private RootLayoutPanel container;

   private List<ContactInfo> connectedContacts = new ArrayList<ContactInfo>();

   private UserInfo user;

   private boolean connected = false;

   @Override
   public void present()
   {
      /*This present is execute only once.*/
      this.setUpChatConversations();

      container.add(display.asWidget());

      display.getSelecteablePanel().addSelectionHandler(new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            if (event.getSelectedItem() instanceof NotificationsPresenter.Display)
               eventBus.fireEvent(new EntryNotificationsEvent());

            if (event.getSelectedItem() instanceof MessagesPresenter.Display)
               eventBus.fireEvent(new EntryMessagesEvent());

            if (event.getSelectedItem() instanceof ContactsPresenter.Display)
               eventBus.fireEvent(new EntryContactsEvent());

            if (event.getSelectedItem() instanceof MeetingsPresenter.Display)
               eventBus.fireEvent(new EntryMeetingsEvent());

            if (event.getSelectedItem() instanceof ProjectsPresenter.Display)
               eventBus.fireEvent(new EntryProjectsEvent());
         }
      });
   }

   /**
    * Observe a event fired at application startup which set the current user
    * to client side.
    */
   public void setUser(@Observes UserInfo user)
   {
      this.user = user;

      infoPresenter.setUser(user);
      infoPresenter.present();

      loadContacts();
      connectOrDisconnect();
   }

   /**
    * Load the connected contacts and set it into display.
    */
   private void loadContacts()
   {
      loadingService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            connectedContacts.addAll(response);
            display.addContacts(response);
         }
      }).getConnectedContacts(user.getUsername());
   }

   /**
    * Connect or disconnect the contact.
    */
   private void connectOrDisconnect()
   {
      RemoteCallback<Boolean> rc = new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            if (response)
            {
               connected = !connected;
               display.setConnected(connected);
            }
            else
            {
               throw new ClientsideException("Error connecting from client.");
            }
         }
      };

      if (!connected)
         loadingService.call(rc).connectCurrentUser(user.getUsername());

      else
         loadingService.call(rc).disconnectCurrentUser(user.getUsername());
   }

   /**
    * Prepare Chat functionality.
    */
   private void setUpChatConversations()
   {
      display.getSelectableChat().addSelectHandler(new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            ContactInfo contact = connectedContacts.get(event.getContext().getIndex());

            conversationsPresenter.addConversation(contact);
            conversationsPresenter.present();
         }
      });

      display.getDisconnectChat().addSelectHandler(new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            connectOrDisconnect();
            loadContacts();
         }
      });
   }

   /**
    * When a new User connect.
    */
   public void contactConnected(@Observes @ContactPlus final ContactInfo contact)
   {
      if (!contact.getGmailAddress().equals(user.getUsername()))
      {
         if (!connectedContacts.contains(contact))
         {
            this.cotnactsService.call(new RemoteCallback<Boolean>()
            {
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     connectedContacts.add(contact);
                     display.addContact(contact);
                  }
               }
            }).isContactOf(user.getId(), contact.getId());
         }
      }
   }

   /**
    * When a new User disconnect.
    */
   public void contactDisconnected(@Observes @ContactMinus ContactInfo contact)
   {
      if (connectedContacts.contains(contact))
      {
         connectedContacts.remove(contact);
         display.removeContact(contact);
      }
   }

   /**
    * Open a conversation with sender contact, the reading of message is a
    * responsibility of ConversationPersenter;
    * 
    * @param message message sent by a contact.
    */
   public void newChatMessage(@Observes ChatMessage message)
   {
      if (!message.getFrom().equals(user.getUsername()) && message.getTo().equals(user.getUsername()))
      {
         if (!this.conversationsPresenter.hasConversation(message.getFrom()))
         {
            conversationsPresenter.addConversation(this.getContact(message.getFrom()));
         }
         conversationsPresenter.present();
         conversationsPresenter.updateConversation(message.getFrom(), message.getText());
      }
   }

   /**
    * @param mail must be != user.username
    * 
    * @return connected contact with this mail;
    */
   private ContactInfo getContact(String mail)
   {
      for (ContactInfo contact : connectedContacts)
      {
         if (contact.getGmailAddress().equals(mail))
            return contact;
      }
      throw new ClientsideException("Recived a message of an unconnected contact.");
   }

   /**
    * When a presenter is executed by history management system, fire an event with their index.
    * This method catch it and select the tab.
    * <ul>
    *   <li>This method set a flag to avoid that any presenter will be executed twice.</li>
    *   <li>If tab is already selected, will be ignored.</li>
    * </ul>
    * 
    * @param index of tab to select.
    */
   public void listenHistorySelection(@Observes @Main Integer index)
   {
      display.selectTab(index);
   }
}