package com.armandorv.cnpd.web.client.presenter.chat;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.view.chat.ConversationView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ChatService;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent.BeforeCloseHandler;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent.HasBeforeCloseHandlers;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;

/**
 * Presenter for Conversations widget, instantiate a new Conversation presenter
 * per conversation.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ConversationsPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void addConversation(String contactName, Widget conversationView);

      HasBeforeCloseHandlers<Widget> getClosable();
   }

   private Map<String, ConversationPresenter> conversationPresenters = new HashMap<String, ConversationPresenter>();

   private boolean closed = true;

   @Inject
   private Display display;

   @Inject
   private Caller<ChatService> chatService;

   private UserInfo user;

   @AfterInitialization
   public void initialize()
   {
      this.display.asWindow().addBeforeHideHandler(this.hide());
   }

   /**
    * @return an anonymous handler which deal with closing concerns.
    */
   private BeforeHideHandler hide()
   {
      return new BeforeHideHandler()
      {
         @Override
         public void onBeforeHide(BeforeHideEvent event)
         {
            closed = true;
         }
      };
   }

   @Override
   public void present()
   {
      if (closed)
      {
         closed = false;
         display.asWindow().show();
      }
   }

   /**
    * set current contact field to next go call.
    * 
    * @param contact
    *            the next contact to chat.
    */
   public void addConversation(ContactInfo contact)
   {
      if (contact == null)
      {
         throw new ClientsideException("The conversation contact mustn't be null.");
      }

      if (!conversationPresenters.containsKey(contact.getGmailAddress()))
      {
         ConversationPresenter conversationPresenter = new ConversationPresenter();
         conversationPresenter.setChatService(chatService);
         conversationPresenter.setUser(user);
         conversationPresenter.setContact(contact);

         conversationPresenters.put(contact.getGmailAddress(), conversationPresenter);

         display.getClosable().addBeforeCloseHandler(this.closeConversation(contact.getGmailAddress()));

         display.addConversation(contact.getFullName(), conversationPresenter.getDisplay().asWidget());
         conversationPresenter.present();
      }
      else
      {
         conversationPresenters.get(contact.getGmailAddress()).present();
      }
   }

   private BeforeCloseHandler<Widget> closeConversation(String gmailAddress)
   {
      return new BeforeCloseHandler<Widget>()
      {
         @Override
         public void onBeforeClose(BeforeCloseEvent<Widget> event)
         {
            Widget widget = event.getItem();
            if (widget instanceof ConversationView)
            {
               ConversationView view = (ConversationView) widget;
               conversationPresenters.remove(view.getContactMail());
            }
         }
      };
   }

   /**
    * Give the user to conversations presenter.
    * 
    * @param user
    *            the principal user.
    */
   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

   /**
    * Method to check if conversations presenter hold a conversation with a
    * concrete user.
    * 
    * @param username
    *            username of hypothetical chatter.
    * @return true if has a conversation with username
    */
   public boolean hasConversation(String username)
   {
      return this.conversationPresenters.containsKey(username);
   }

   public void updateConversation(String contactMail, String text)
   {
      this.conversationPresenters.get(contactMail).reciveText(text);
      /* TODO set selected tab. */
   }

}