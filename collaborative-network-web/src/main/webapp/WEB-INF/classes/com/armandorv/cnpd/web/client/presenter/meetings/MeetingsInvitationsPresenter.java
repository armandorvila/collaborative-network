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
public class MeetingsInvitationsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setMeetings(List<MeetingInfo> meetings);

      void refresh();

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getAccept();

      HasSelectionHandlers<Item> getRefuse();

      MeetingInfo getSelected(int index);

      void removeInvitation(MeetingInfo selected);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<MeetingsService> meetingService;

   private MeetingInfo selected;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getAccept().addSelectionHandler(accept());
      display.getRefuse().addSelectionHandler(refuse());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("Error user must be set previously.");

      loadInvitations();
   }

   private void loadInvitations()
   {
      meetingService.call(new RemoteCallback<List<MeetingInfo>>()
      {
         @Override
         public void callback(List<MeetingInfo> response)
         {
            display.setMeetings(response);
            display.refresh();
         }
      }).getInvitations(user.getId());
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getSelected(event.getContext().getIndex());
         }
      };
   }

   private SelectionHandler<Item> refuse()
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
                        .getMessenger("Invitations refused.", "Error refusing invitations")
                        .message(response);
                  
                  if(response){
                     display.removeInvitation(selected);
                     display.refresh();
                  }
               }
            }).refuseInvitation(selected.getId(), user.getId());
         }
      };
   }

   private SelectionHandler<Item> accept()
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
                  .getMessenger("Invitation accepted.", "Error accepting invitation")
                  .message(response);
            
                  if(response){
                     display.removeInvitation(selected);
                     display.refresh();
                  }
               
               }
            }).acceptInvitation(selected.getId(), user.getId());
         }
      };
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}
