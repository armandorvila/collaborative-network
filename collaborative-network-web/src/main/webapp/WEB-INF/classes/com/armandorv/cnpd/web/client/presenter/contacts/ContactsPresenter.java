package com.armandorv.cnpd.web.client.presenter.contacts;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.ioc.client.api.AfterInitialization;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class ContactsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();
   }

   @Inject
   private Display display;

   @Inject
   @Main
   private Event<Integer> tabSelectedEvent;

   @Inject
   private ContactsListPresenter contactsListPresenter;

   @Inject
   private ContactRequestsPresenter contactRequestsPresenter;

   @Inject
   private FindUsersPresenter findUsersPresenter;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getTabPanel().addSelectionHandler(this.selectPrsenter());
   }

   private SelectionHandler<Widget> selectPrsenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            Widget widget = event.getSelectedItem();

            if (widget instanceof ContactsListPresenter.Display)
               contactsListPresenter.present();

            if (widget instanceof ContactRequestsPresenter.Display)
               contactRequestsPresenter.present();

            if (widget instanceof FindUsersPresenter.Display)
               findUsersPresenter.present();
         }
      };
   }

   @Override
   public void present()
   {
      contactsListPresenter.present();
      tabSelectedEvent.fire(display.getIndex());
   }

   public Display getDisplay()
   {
      return display;
   }

}