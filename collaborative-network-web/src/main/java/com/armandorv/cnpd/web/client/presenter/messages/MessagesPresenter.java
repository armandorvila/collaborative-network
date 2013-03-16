package com.armandorv.cnpd.web.client.presenter.messages;

import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.armandorv.cnpd.web.shared.remote.InformationService;
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
public class MessagesPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setMessages(List<MessageInfo> messages);

      void addMessage(MessageInfo message);

      HasSelectionHandlers<Item> getShowClickable();

      HasSelectionHandlers<Item> getDeleteClickable();

      HasSelectHandlers getSelectable();

      MessageInfo getSelected(int row);

      Integer getIndex();

      void removeMessage(MessageInfo selected);

      void showProgress();

      void refresh();
   }

   @Inject
   private Caller<InformationService> infoService;

   @Inject
   private Display display;

   @Inject
   @Main
   private Event<Integer> tabSelectedEvent;

   private MessageInfo selected;

   private UserInfo user;

   private boolean isStart = true;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getSelectable().addSelectHandler(createSelectHandler());
      display.getDeleteClickable().addSelectionHandler(deleteMessage());
      display.getShowClickable().addSelectionHandler(showMessage());
   }

   @Override
   public void present()
   {
      this.loadMessages();

      if (isStart)
      {
         display.showProgress();
         isStart = false;
      }

      this.tabSelectedEvent.fire(display.getIndex());
   }

   private SelectionHandler<Item> showMessage()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            new MessagePresenter(infoService, selected).present();

            infoService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     selected.setRead(true);
                     display.refresh();
                  }
               }
            }).markMessageAsRead(selected.getId());
         }
      };
   }

   private SelectionHandler<Item> deleteMessage()
   {
      return new SelectionHandler<Item>()
      {
         public void onSelection(SelectionEvent<Item> event)
         {
            infoService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     Info.display("Notification", "Message deleted.");
                     display.removeMessage(selected);
                  }
                  else
                  {
                     Info.display("Notification", "Error deleting message.");
                  }
               }
            }).deleteMessage(selected.getId());
         }
      };
   }

   private SelectHandler createSelectHandler()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            int row = event.getContext().getIndex();
            selected = display.getSelected(row);
         }
      };
   }

   private void loadMessages()
   {
      infoService.call(new RemoteCallback<List<MessageInfo>>()
      {
         public void callback(List<MessageInfo> response)
         {
            display.setMessages(response);
         }
      }).getMessages(user.getId());

   }

   public Display getDisplay()
   {
      return display;
   }

   public void newMessage(@Observes MessageInfo message)
   {
      display.addMessage(message);
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}