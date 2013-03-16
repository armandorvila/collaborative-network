package com.armandorv.cnpd.web.client.presenter.meetings;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.InviteContactsView;
import com.armandorv.cnpd.web.client.view.util.PromptView;
import com.armandorv.cnpd.web.client.view.util.PromptView.Action;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Singleton
public class MeetingDetailsPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setParticipants(List<ContactInfo> participants);

      List<ContactInfo> getParticipants();

      void setMeeting(MeetingInfo meeting);

      HasSelectHandlers invite();

      HasSelectHandlers clausure();

      HasSelectHandlers delete();

      void setManagementEnabled(boolean enabled);

      void setInviteEnabled(boolean enabled);
   }

   private InviteContactsView inviteContactsView = null;

   @Inject
   private Display display;

   @Inject
   private Caller<MeetingsService> meetingService;

   @Inject
   private Caller<ContactsService> contactsService;

   private MeetingInfo meeting;

   private UserInfo user;

   @AfterInitialization
   public void afterInitizlization()
   {
      display.delete().addSelectHandler(delete());
      display.invite().addSelectHandler(invite());
      display.clausure().addSelectHandler(clausure());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must e set previously.");

      if (meeting == null)
         throw new ClientsideException("A meeting must e set previously.");

      display.setMeeting(meeting);
      display.setManagementEnabled(user.getId() == meeting.getInstigatorId());
      display.setInviteEnabled(!meeting.isCelebrated());

      loadParticipants();

      display.asWindow().show();
   }

   private void loadParticipants()
   {
      meetingService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            display.setParticipants(response);
         }
      }).getMeetingParticipants(meeting.getId());

   }

   /*
    * Must be called after after loadParticipants
    */
   private void loadContactsToInvite()
   {
      contactsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            response.removeAll(display.getParticipants());
            inviteContactsView.setContacts(response);
            inviteContactsView.asWindow().show();
         }
      }).getContacts(user.getId());
   }

   private SelectHandler invite()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            inviteContactsView = new InviteContactsView();

            inviteContactsView.getAcceptButton().addSelectHandler(accept());
            inviteContactsView.getCancelButton().addSelectHandler(cancel());
            loadContactsToInvite();
         }
      };
   }

   private SelectHandler clausure()
   {
      return new SelectHandler()
      {

         @Override
         public void onSelect(SelectEvent event)
         {
            PromptView prompt = new PromptView("Meeting conclusions", "Introduce the conclusions of the meeting.");

            prompt.addAction(new Action()
            {
               @Override
               public void execute(final String value)
               {
                  meetingService.call(new RemoteCallback<Boolean>()
                  {
                     @Override
                     public void callback(Boolean response)
                     {
                        BooleanMessenger.getMessenger("Meeting clousured.", "Error clausuring meeting.").message(
                              response);
                        
                        if(response){
                           meeting.setConclusions(value);
                           display.setMeeting(meeting); 
                        }
                     }
                  }).clausureMeeting(meeting.getId() , value);
               }
            });

            prompt.show();

         }
      };
   }

   private SelectHandler delete()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            meetingService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger
                        .getMessenger("Meeting deleted", "Error deleting meeting.")
                        .message(response);

                  display.asWindow().hide();
               }
            }).deleteMeeting(meeting.getId());
         }
      };
   }

   private SelectHandler cancel()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            inviteContactsView.asWindow().hide();
         }
      };
   }

   private SelectHandler accept()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            meetingService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Contacts invited.", "There is duplicated invitations.")
                        .message(response);

                  inviteContactsView.asWindow().hide();
               }

            }).sendInvitations(meeting.getId(), inviteContactsView.getAddedContacts());
         }
      };
   }

   public void setUser(UserInfo user)
   {
      this.user = user;
   }

   public void setMeeting(MeetingInfo meeting)
   {
      this.meeting = meeting;
   }

}