package com.armandorv.cnpd.web.client.view.chat;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter.Display;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.BeforeCloseEvent.HasBeforeCloseHandlers;

/**
 * Widget that hold all conversations, it has a dynamic tab panel with a tab per
 * conversation.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class ConversationsView implements Display
{

   @UiTemplate("ConversationsView.ui.xml")
   interface ContactViewBinder extends UiBinder<Widget, ConversationsView>
   {
   }

   private static ContactViewBinder uiBinder = GWT.create(ContactViewBinder.class);

   private TabPanel advanced;

   private int index = 0;

   @UiField
   Window conversationWindow;

   public ConversationsView()
   {
      advanced = new TabPanel();
      advanced.setPixelSize(600, 250);
      advanced.setAnimScroll(true);
      advanced.setTabScroll(true);
      advanced.setCloseContextMenu(true);

      uiBinder.createAndBindUi(this);
      this.conversationWindow.add(advanced);
   }

   @Override
   public Window asWindow()
   {
      return conversationWindow;
   }

   @Override
   public void addConversation(String contactName, Widget conversationView)
   {
      conversationView.addStyleName("pad-text");
      advanced.add(conversationView, new TabItemConfig(contactName, index != 1));
   }

   public HasBeforeCloseHandlers<Widget> getClosable()
   {
      return advanced;
   }

}
