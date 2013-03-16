package com.armandorv.cnpd.web.client.presenter.projects;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class ProjectInvitationsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setInvitations(List<ProjectInfo> invitations);

      ProjectInfo getInvitation(int row);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getAccept();

      HasSelectionHandlers<Item> getRefuse();

      HasSelectionHandlers<Item> getDetails();

      void addErrorMessage(String string);

      void removeInvitation(ProjectInfo selected);

      void addSuccessMessage(String string);

      void showDetails(ProjectInfo selected);
   }

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Display display;

   private UserInfo user;

   private ProjectInfo selected;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getAccept().addSelectionHandler(accept());
      display.getRefuse().addSelectionHandler(refuse());
      display.getDetails().addSelectionHandler(details());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      this.loadInvitations();
   }

   private void loadInvitations()
   {
      this.projectsService.call(new RemoteCallback<List<ProjectInfo>>()
      {

         @Override
         public void callback(List<ProjectInfo> response)
         {
            display.setInvitations(response);
         }
      }).getProjectInvitations(user.getId());
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {

         public void onSelect(SelectEvent event)
         {
            int row = event.getContext().getIndex();
            selected = display.getInvitation(row);
         }
      };
   }

   private SelectionHandler<Item> accept()
   {
      return new SelectionHandler<Item>()
      {

         public void onSelection(SelectionEvent<Item> event)
         {

            projectsService.call(new RemoteCallback<Boolean>()
            {

               public void callback(Boolean response)
               {

                  if (response)
                  {
                     display.removeInvitation(selected);
                     display.addSuccessMessage("Project Invitation added.");
                  }
                  else
                  {
                     display.addErrorMessage("Error resolving invitation, try again later.");
                  }

               }
            }).acceptProjectInvitation(user.getId(), selected.getId());

         }
      };
   }

   private SelectionHandler<Item> refuse()
   {
      return new SelectionHandler<Item>()
      {

         public void onSelection(SelectionEvent<Item> event)
         {

            projectsService.call(new RemoteCallback<Boolean>()
            {

               public void callback(Boolean response)
               {

                  if (response)
                  {
                     display.removeInvitation(selected);
                     display.addSuccessMessage("Project Invitation refused.");
                  }
                  else
                  {
                     display.addErrorMessage("Error resolving invitation, try again later.");
                  }

               }
            }).refuseProjectInvitation(user.getId(), selected.getId());

         }
      };
   }

   private SelectionHandler<Item> details()
   {
      return new SelectionHandler<Item>()
      {

         public void onSelection(SelectionEvent<Item> event)
         {
            display.showDetails(selected);
         }
      };
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}