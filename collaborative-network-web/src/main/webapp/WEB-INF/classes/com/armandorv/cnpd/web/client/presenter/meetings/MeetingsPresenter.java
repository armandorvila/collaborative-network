package com.armandorv.cnpd.web.client.presenter.meetings;

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
public class MeetingsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();
      
      void showProgress();
   }

   @Main
   @Inject
   private Event<Integer> tabSelectedEvent;

   @Inject
   private Display display;

   @Inject
   private MeetingsListPresenter meetingsListPresenter;

   @Inject
   private MeetingsInvitationsPresenter meetingsInvitationsPresenter;

   @Inject
   private NewMeetingPresenter newMeetingPresenter;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getTabPanel().addSelectionHandler(selectPresenter());
   }

   @Override
   public void present()
   {
      meetingsListPresenter.present();
      tabSelectedEvent.fire(display.getIndex());
   }

   /**
    * Select the correct presenter for the selected tab and execute it.
    */
   private SelectionHandler<Widget> selectPresenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            if (event.getSelectedItem() instanceof MeetingsListPresenter.Display)
            {
               meetingsListPresenter.present();
            }
            if (event.getSelectedItem() instanceof MeetingsInvitationsPresenter.Display)
            {
               meetingsInvitationsPresenter.present();
            }
            if (event.getSelectedItem() instanceof NewMeetingPresenter.Display)
            {
               newMeetingPresenter.present();
            }
         }
      };
   }

}
