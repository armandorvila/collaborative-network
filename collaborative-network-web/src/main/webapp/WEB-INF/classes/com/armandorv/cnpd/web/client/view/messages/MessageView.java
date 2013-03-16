package com.armandorv.cnpd.web.client.view.messages;

import com.armandorv.cnpd.web.client.presenter.messages.MessagePresenter;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;

/**
 * View that show a contact message and let you send another one.
 * 
 * @author armandorv
 * 
 */
public class MessageView implements MessagePresenter.Display
{

   @UiTemplate("MessageView.ui.xml")
   public interface MessageViewUiBinder extends UiBinder<Widget, MessageView>
   {
   }

   private MessageViewUiBinder uiBinder = GWT.create(MessageViewUiBinder.class);

   @UiField
   Dialog messageDialog;

   @UiField
   BorderLayoutContainer container;

   TextButton sendButton = new TextButton("Send");

   @UiField
   HtmlEditor incomingText;

   @UiField
   HtmlEditor sendingText;

   CheckBox mail = new CheckBox();

   @UiField
   ContentPanel incomingTextPanel;

   @UiField
   ContentPanel outcomingTextPanel;

   public MessageView(MessageInfo message)
   {
      uiBinder.createAndBindUi(this);
      this.incomingText.setValue(message.getContent());
      incomingTextPanel.setHeadingText(message.getSender() + " wrote :");
      this.setUp();
   }

   public MessageView()
   {
      uiBinder.createAndBindUi(this);
      this.setUp();
   }

   private void setUp()
   {

      this.messageDialog.setPredefinedButtons(PredefinedButton.CLOSE);
      this.messageDialog.addButton(sendButton);
      this.messageDialog.addButton(new Label("Mail :"));
      mail.setToolTip("If you mark this check box this message will be sent to user mail too.");
      this.messageDialog.addButton(mail);
      this.messageDialog.setHideOnButtonClick(true);
   }

   public HasSelectHandlers getSendButton()
   {
      return sendButton;
   }

   public Dialog asDialog()
   {
      return this.messageDialog;
   }

   @Override
   public String getSendText()
   {
      return sendingText.getValue();
   }

   @Override
   public boolean mail()
   {
      return mail.getValue();
   }

   @Override
   public void disableIncomingText()
   {
      this.container.remove(this.container.getNorthWidget());
      outcomingTextPanel.setHeadingText("Write your message :");
   }

}