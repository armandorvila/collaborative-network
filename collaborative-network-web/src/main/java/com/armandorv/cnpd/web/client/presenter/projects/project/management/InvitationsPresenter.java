package com.armandorv.cnpd.web.client.presenter.projects.project.management;

import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.projects.InvitationsOnCreationPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Extends AddContactsOnCreationPresenter presenter which add contacts 
 * while project creation to send projects invitations whenever.
 * 
 * @author armandorv
 *
 */
public class InvitationsPresenter extends InvitationsOnCreationPresenter
{
   public InvitationsPresenter(Presenter parent, Caller<ContactsService> contactsService,
         Caller<ProjectsService> projectsService, UserInfo user, ProjectInfo project)
   {
      super(parent, contactsService, projectsService, user, project);
      loadMembers();
   }
   
   private void loadMembers(){
      
      projectsService.call(new RemoteCallback<List<ContactInfo>>()
            {
               @Override
               public void callback(List<ContactInfo> response)
               {
                  display.getToAddContacts().removeAll(response);
               }
            }).getMembers(project.getId());
   }

   @Override
   protected SelectHandler accept()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            projectsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Contacts invited.", "Error inviting contacts.")
                  .message(response);
                  
                  if(response)
                     display.asWindow().hide();
               }
            }).inviteContacts(project.getId(), display.getAddedContacts());
         }
      };
   }

}