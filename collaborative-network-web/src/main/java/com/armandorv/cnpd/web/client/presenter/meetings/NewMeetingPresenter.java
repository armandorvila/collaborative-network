package com.armandorv.cnpd.web.client.presenter.meetings;

import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.InviteContactsView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

@Singleton
public class NewMeetingPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void clear();

      String getMeetingTitle();
      
      String getPlace();

      String getDescription();

      Date getDate();

      HasSelectHandlers getCreate();
   }
   
   private InviteContactsView inviteContactsView = new InviteContactsView(); 

   @Inject
   private Display display;

   @Inject
   private Caller<MeetingsService> meetingService;
   
   @Inject
   private Caller<ContactsService> contactsService;
   
   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      inviteContactsView.getAcceptButton().addSelectHandler(create());
      inviteContactsView.getCancelButton().addSelectHandler(cancel());
      display.getCreate().addSelectHandler(addContacts());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      display.clear();
      
      loadContacts();
   }

   private void loadContacts()
   {
      contactsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
           inviteContactsView.setContacts(response);
         }
      }).getContacts(user.getId());
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
   
   private SelectHandler addContacts()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            inviteContactsView.asWindow().show();
         }
      };
   }

   private SelectHandler create()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            if(validate()){
            
            MeetingInfo meeting = new MeetingInfo();
            meeting.setTitle(display.getMeetingTitle());
            meeting.setPlace(display.getPlace());
            meeting.setDescription(display.getDescription());
            meeting.setDate(display.getDate());

            meetingService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Meeting created.", "Error creatign meeting.")
                        .message(response);
                  
                  if(response)
                     display.clear();
                  
                  inviteContactsView.asWindow().hide();
               }
            }).createMeeting(user.getId(), meeting, inviteContactsView.getAddedContacts());
         }
         }
      };
   }
   
   private boolean validate(){
      
      if("".equals(display.getMeetingTitle()))
      {
         Info.display("Error", "You have to introduce a title.");
         return false;
      }
      
      if("".equals(display.getPlace()))
      {
         Info.display("Error", "You have to introduce a place.");
         return false;
      }
      
      if("".equals(display.getDescription()))
      {
         Info.display("Error", "You have to introduce a description.");
         return false;
      }
      
      if(display.getDate() == null){
         Info.display("Error", "You have to introduce a date.");
         return false;
      }
      
      return true;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}