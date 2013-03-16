package com.armandorv.cnpd.web.client.presenter.messages;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.view.messages.MessageView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Presenter for the messageView widget, deal with reading and sending messages
 * logic.
 * 
 * @author armandorv
 * 
 */
public class MessagePresenter implements Presenter
{
   public interface Display
   {
      Dialog asDialog();

      HasSelectHandlers getSendButton();

      String getSendText();

      boolean mail();

      void disableIncomingText();
   }

   private Display display;

   private MessageInfo message;

   private Caller<InformationService> informationService;

   /**
    * Constructor to deal with a message and respond it.
    * 
    * @param informationService
    *            service to respond message.
    * @param message
    *            incoming message.
    */
   public MessagePresenter(Caller<InformationService> informationService, MessageInfo message)
   {
      this.informationService = informationService;
      /*This is the response*/
      this.message = new MessageInfo();
      this.message.setSender(message.getTo());
      this.message.setTo(message.getSender());
      /*Display use the incoming*/
      display = new MessageView(message);
   }

   /**
    * Constructor to send a message without incoming message.
    * 
    * @param informationService
    *            service to send message.
    * @param to
    *            mail address of contact to send mail.
    */
   public MessagePresenter(Caller<InformationService> informationService, String from, String to)
   {
      this.informationService = informationService;
     
      display = new MessageView();
      display.disableIncomingText();

      /* Send button use message.getSender as destiny. */
      message = new MessageInfo();
      message.setTo(to);
      message.setSender(from);
   }

   /**
    * Container is not necessary.
    */
   @Override
   public void present()
   {
      display.getSendButton().addSelectHandler(this.createSendButtonHandler());
      display.asDialog().show();
   }

   /* ********* Handlers for widget events ******* */

   private SelectHandler createSendButtonHandler()
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
                     throw new ClientsideException("Error sending message!!");
               }
            }).sendMessage(message.getSender(), message.getTo(), display.getSendText(), display.mail());
            Info.display("Notification", "Message sent");
            display.asDialog().hide();
         }
      };
   }

}