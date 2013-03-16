package com.armandorv.cnpd.web.client.presenter.chat;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.view.chat.ConversationView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ChatService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for a single conversation, get input text of user widget and send
 * it through chat service in the other side, this presenter listen text of chat
 * and set into user widget.
 * 
 * @author armandorv
 * 
 */
public class ConversationPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      HasSelectHandlers getSendButton();

      HasSelectHandlers getClearButton();

      void addText(String text);

      void setRecivedText(String text);

      String getSentText();

      void setSentText(String text);

      void addErrorMessage(String string);

      void setContactMail(String mail);

      String getContactMail();
   }

   private Display display = new ConversationView();

   private Caller<ChatService> chatService;

   private ContactInfo contact = null;

   private UserInfo user;

   public ConversationPresenter()
   {
      this.display.getSendButton().addSelectHandler(sendMessage());
      this.display.getClearButton().addSelectHandler(clear());
   }

   @Override
   public void present()
   {
      if (chatService == null)
         throw new ClientsideException(
               "Error calling ConversationPresenter.go, a chatService must be setting previously.");

      if (contact == null)
         throw new ClientsideException("Error calling ConversationPresenter.go, a contact must be setting previously.");

      if (user == null)
         throw new ClientsideException("Error calling ConversationPresenter.go, a user must be setting previously.");

      display.setContactMail(contact.getGmailAddress());
   }

   private SelectHandler clear()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            display.setRecivedText("");
            display.setSentText("");
         }
      };
   }

   private SelectHandler sendMessage()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            chatService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     display.addText(user.getName() + " says :" + display.getSentText());
                     display.setSentText("");
                  }
                  else
                  {
                     display.addErrorMessage("Error sending message to contact:" + contact.getFullName());
                  }
               }
            }).sendMessage(user.getUsername(), contact.getGmailAddress(), display.getSentText());
         }
      };
   }

   public Display getDisplay()
   {
      return display;
   }

   public void setChatService(Caller<ChatService> chatService)
   {
      this.chatService = chatService;
   }

   public void setContact(ContactInfo contact)
   {
      this.contact = contact;
   }

   public void setUser(UserInfo user)
   {
      this.user = user;
   }

   public void reciveText(String text)
   {
      display.addText(contact.getName() + " says :" + text);
   }

}