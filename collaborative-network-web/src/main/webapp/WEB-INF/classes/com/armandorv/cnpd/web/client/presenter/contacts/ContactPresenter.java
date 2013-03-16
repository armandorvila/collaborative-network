package com.armandorv.cnpd.web.client.presenter.contacts;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for CotnactView.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ContactPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setContacts(List<ContactInfo> response);

      void setProjects(List<ProjectInfo> response);

      HasSelectHandlers getOpen();

      ProjectInfo getProject(int row);

      void setUser(UserInfo response);
   }

   private ContactInfo contact;

   @Inject
   private Display display;

   @Inject
   private Caller<ContactsService> contactService;

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Caller<UsersService> usersService;

   @Inject
   private ContactProjectPresenter contactProjectPresenter;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getOpen().addSelectHandler(new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {  
            contactProjectPresenter.setProject(display.getProject(event.getContext().getIndex()));
            contactProjectPresenter.present();
         }
      });
   }

   @Override
   public void present()
   {
      this.loadUser();
      this.loadContacts();
      this.loadProjects();

      display.asWindow().show();
   }

   private void loadUser()
   {
      usersService.call(new RemoteCallback<UserInfo>()
      {
         @Override
         public void callback(UserInfo response)
         {
            display.setUser(response);
         }
      }).getUserByUsername(contact.getGmailAddress());

   }

   private void loadProjects()
   {
      projectsService.call(new RemoteCallback<List<ProjectInfo>>()
      {
         @Override
         public void callback(List<ProjectInfo> response)
         {
            display.setProjects(response);
         }

      }).getContactProjects(contact.getGmailAddress());
   }

   private void loadContacts()
   {
      contactService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            display.setContacts(response);
         }
      }).getContactsByUser(contact.getGmailAddress());
   }

   public ContactInfo getContact()
   {
      return contact;
   }

   public void setContact(ContactInfo contact)
   {
      this.contact = contact;
   }

}