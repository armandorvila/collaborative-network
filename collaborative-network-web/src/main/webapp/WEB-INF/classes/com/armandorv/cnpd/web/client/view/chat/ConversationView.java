package com.armandorv.cnpd.web.client.view.chat;

import com.armandorv.cnpd.web.client.presenter.chat.ConversationPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.sencha.gxt.widget.core.client.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * View for a single conversation, host widgets for receive and send text.
 * 
 * @author armandorv
 * 
 */
public class ConversationView extends Composite implements ConversationPresenter.Display
{

   /* *************** Ui Binder ************** */

   @UiTemplate("ConversationView.ui.xml")
   interface ConversationUiBinder extends UiBinder<Widget, ConversationView>
   {
   }

   private static ConversationUiBinder uiBinder = GWT.create(ConversationUiBinder.class);

   /* *************** UiFields************** */

   @UiField
   TextButton clearButton;

   @UiField
   TextButton sendButton;

   @UiField
   TextArea incomingText;

   @UiField
   TextArea sendingText;

   @UiField
   FramedPanel conversation;

   private String contactMail;

   public ConversationView()
   {
      initWidget(uiBinder.createAndBindUi(this));
   }

   /* *********** Display complaint methods ******** */

   public HasSelectHandlers getSendButton()
   {
      return sendButton;
   }

   public HasSelectHandlers getClearButton()
   {
      return clearButton;
   }

   @Override
   public void addText(String text)
   {
      String current = incomingText.getText();
      if (!"".equals(current))
         current += "\n";
      current += text;
      this.incomingText.setText(current);
   }

   @Override
   public String getSentText()
   {
      return this.sendingText.getText();
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Chat fault", string);

   }

   @Override
   public void setRecivedText(String text)
   {
      this.incomingText.setText(text);

   }

   @Override
   public void setSentText(String text)
   {
      this.sendingText.setText(text);
   }

   @Override
   public void setContactMail(String mail)
   {
      this.contactMail = mail;
   }

   @Override
   public String getContactMail()
   {
      return this.contactMail;
   }

}