package com.armandorv.cnpd.web.client.view.contacts;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * View for contacts section of main panel.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ContactsView extends Composite implements ContactsPresenter.Display
{
   public interface ContactsViewUiBinder extends UiBinder<Widget, ContactsView>
   {
   }

   private static ContactsViewUiBinder uiBinder = GWT.create(ContactsViewUiBinder.class);

   @UiField
   ContactsListView contacts;

   @UiField
   ContactRequestsView requests;

   @UiField
   FindUsersView search;

   @UiField
   TabPanel tabPanel;

   public ContactsView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return MainWindow.Indexes.CONTACTS_INDEX;
   }

   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }

   @Produces
   @Singleton
   public ContactsListPresenter.Display produceContactList()
   {
      return contacts;
   }

   @Produces
   @Singleton
   public ContactRequestsPresenter.Display produceContactRequests()
   {
      return requests;
   }

   @Produces
   @Singleton
   public FindUsersPresenter.Display produceFindUsers()
   {
      return search;
   }
}