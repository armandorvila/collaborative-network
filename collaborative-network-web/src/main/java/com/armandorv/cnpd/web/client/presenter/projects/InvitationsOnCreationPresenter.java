package com.armandorv.cnpd.web.client.presenter.projects;

import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.InviteContactsView;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for invite contacts during project creation process.
 * 
 * @author armandorv
 *
 */
public class InvitationsOnCreationPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      HasSelectHandlers getAcceptButton();

      HasSelectHandlers getCancelButton();

      void setContacts(List<ContactInfo> contacts);
      
      List<ContactInfo> getToAddContacts();

      List<ContactInfo> getAddedContacts();

      void showProgress();
   }

   protected Display display = new InviteContactsView();

   protected Caller<ProjectsService> projectsService;

   protected UserInfo user;

   protected ProjectInfo project;

   protected Presenter parent;

   public InvitationsOnCreationPresenter(Presenter parent, Caller<ContactsService> contactsService,
         Caller<ProjectsService> projectsService, UserInfo user, ProjectInfo project)
   {
      this.user = user;
      this.project = project;
      this.projectsService = projectsService;
      this.parent = parent;

      contactsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            display.setContacts(response);
         }
     
      }).getContactsByUser(user.getUsername());

      this.display.getAcceptButton().addSelectHandler(accept());
      this.display.getCancelButton().addSelectHandler(cancel());
   }

   @Override
   public void present()
   {
      display.asWindow().show();
   }

   protected SelectHandler cancel()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            display.asWindow().hide();
         }
      };
   }

   protected SelectHandler accept()
   {
      project.setMembers(display.getAddedContacts());

      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            display.asWindow().hide();
            
            projectsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                     parent.present();

                  BooleanMessenger.getMessenger("Project created Successfully.", "Error creating project.")
                        .message(response);
               }
            }).createProject(user.getId(), project);
           
            display.showProgress();
         }
      };
   }

}