package com.armandorv.cnpd.web.client.presenter.meetings;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class MeetingsListPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setMeetings(List<MeetingInfo> meetings);

      void refresh();

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getLeave();

      HasSelectionHandlers<Item> getDetails();

      MeetingInfo getSelected(int index);

      void showProgress();

      void setLeaveEnabled(boolean enabled);

      void removeMeeting(MeetingInfo selected);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<MeetingsService> meetingService;
   
   @Inject
   private MeetingDetailsPresenter meetingDetailsPresenter;

   private MeetingInfo selected;

   private UserInfo user;

   private boolean isStart;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getLeave().addSelectionHandler(leave());
      display.getDetails().addSelectionHandler(details());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("Error user must be set previously.");

      loadMeetings();
      
      if (isStart)
      {
         display.showProgress();
         isStart = false;
      }
   }
   
   private void loadMeetings()
   {
      meetingService.call(new RemoteCallback<List<MeetingInfo>>()
      {
         @Override
         public void callback(List<MeetingInfo> response)
         {
            display.setMeetings(response);
            display.refresh();
         }
      }).getMeetings(user.getId());
   }


   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getSelected(event.getContext().getIndex());

            display.setLeaveEnabled(!(selected.getInstigatorId() == user.getId()));
         }
      };
   }

   private SelectionHandler<Item> leave()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            meetingService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger
                        .getMessenger("Meeting leaft", "Error leaving meeting.")
                        .message(response);

                  if (response){
                     display.removeMeeting(selected);
                     display.refresh();  
                  }
               }
            }).leaveMeeting(selected.getId(), user.getId());
         }
      };
   }

   private SelectionHandler<Item> details()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            meetingDetailsPresenter.setMeeting(selected);
            meetingDetailsPresenter.setUser(user);
            
            meetingDetailsPresenter.present();
         }
      };
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}
