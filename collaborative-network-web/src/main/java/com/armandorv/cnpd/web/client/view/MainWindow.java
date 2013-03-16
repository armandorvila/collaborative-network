package com.armandorv.cnpd.web.client.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.web.client.presenter.MainPresenter;
import com.armandorv.cnpd.web.client.view.chat.ChatView;
import com.armandorv.cnpd.web.client.view.contacts.ContactsView;
import com.armandorv.cnpd.web.client.view.info.InfoPanelView;
import com.armandorv.cnpd.web.client.view.meetings.MeetingsView;
import com.armandorv.cnpd.web.client.view.messages.MessagesView;
import com.armandorv.cnpd.web.client.view.notifications.NotificationsView;
import com.armandorv.cnpd.web.client.view.projects.ProjectsView;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;

/**
 * Main window of The Application ..
 * 
 * @author armandorv
 * 
 */
public class MainWindow implements MainPresenter.Display, IsWidget
{
   public static class Indexes
   {
      public static final int NEWS_INDEX = 0;

      public static final int MESSAGES_INDEX = 1;

      public static final int CONTACTS_INDEX = 2;

      public static final int MEETINGS_INDEX = 3;

      public static final int PROJECTS_INDEX = 4;
   }

   public interface MainWindowUiBinder extends UiBinder<Component, MainWindow>
   {
   }

   private static MainWindowUiBinder uiBinder = GWT.create(MainWindowUiBinder.class);

   private Widget mainView = null;

   @UiField
   TabPanel tabPanel;

   @UiField(provided = true)
   @Inject
   InfoPanelView infoPanel;

   @UiField(provided = true)
   @Inject
   ChatView chat;

   @Inject
   @UiField(provided = true)
   NotificationsView news;

   @Inject
   @UiField(provided = true)
   MessagesView messages;

   @Inject
   @UiField(provided = true)
   ContactsView contacts;

   @Inject
   @UiField(provided = true)
   MeetingsView meetings;

   @Inject
   @UiField(provided = true)
   ProjectsView projects;

   @UiField
   TextButton disconnectButton;

   @PostConstruct
   public void initMainView()
   {
      mainView = uiBinder.createAndBindUi(this);
   }

   @Override
   public void addContact(ContactInfo contact)
   {
      chat.addContact(contact);
   }

   @Override
   public void addContacts(List<ContactInfo> contacts)
   {
      chat.addContacts(contacts);
   }

   @Override
   public HasSelectionHandlers<Widget> getSelecteablePanel()
   {
      return tabPanel;
   }

   @Override
   public HasSelectHandlers getSelectableChat()
   {
      return chat.getClickable();
   }

   @Override
   public HasSelectHandlers getDisconnectChat()
   {
      return disconnectButton;
   }

   @Override
   public Widget asWidget()
   {
      Viewport viewPort = new Viewport();
      viewPort.add(mainView);
      return viewPort;
   }

   @Override
   public void selectTab(Integer index)
   {
      if (index != null && this.tabPanel != null)
      {
         Widget selected = tabPanel.getWidget(index);

         if (!tabPanel.getActiveWidget().equals(selected))
            this.tabPanel.setActiveWidget(selected);
      }
   }

   @Override
   public void removeContact(ContactInfo contact)
   {
      chat.removeContact(contact);
   }

   @Override
   public void setConnected(boolean connected)
   {
      chat.setEnabled(connected);
      disconnectButton.setText(connected ? "Disconnect" : "Connect");
   }

}