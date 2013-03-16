package com.armandorv.cnpd.web.client.presenter.projects.project.management;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.util.ConfirmationView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Presenter for Members view that deal with projects members management.
 * 
 * @author armandorv
 *
 */
@Singleton
public class MembersPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setMembers(List<ContactInfo> members);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getManager();

      HasSelectionHandlers<Item> getExclude();

      void setManagerEnabled(boolean enabled);

      void setExcludeEnabled(boolean enabled);

      ContactInfo getMember(int row);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<ProjectsService> projectsService;

   private ProjectPresenter.Display projectDisplay;

   private ProjectInfo project;

   private boolean changeManager;

   private boolean excludeContacts;

   private ContactInfo selected;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getManager().addSelectionHandler(changeManager());
      display.getExclude().addSelectionHandler(exclude());
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      display.setMembers(project.getMembers());
      display.setManagerEnabled(changeManager);
      display.setExcludeEnabled(excludeContacts);

      display.asWindow().show();
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getMember(event.getContext().getIndex());
         }
      };
   }

   private SelectionHandler<Item> exclude()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            if (selected.getId() != project.getManagerId())
            {
               new ConfirmationView("User will be excluded, are you sure ?", new ConfirmationView.Action()
               {
                  @Override
                  public void execute()
                  {
                     projectsService.call(new RemoteCallback<Boolean>()
                     {
                        @Override
                        public void callback(Boolean response)
                        {
                           BooleanMessenger.getMessenger("User Excluded.", "Error excluding user.")
                                 .message(response);

                           if (response)
                           {
                              project.getMembers().remove(selected);
                              projectDisplay.setMembers(project.getMembers());
                           }
                        }
                     }).excludeMemberOfProject(selected.getId(), project.getId());
                  }
               }).show();
            }
            else
            {
               Info.display("Error", "Manager won't able be exluded.");
            }
         }
      };
   }

   private SelectionHandler<Item> changeManager()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            new ConfirmationView("Manager will be changed, are you sure ?", new ConfirmationView.Action()
            {
               @Override
               public void execute()
               {
                  if (project.getManagerId() != selected.getId())
                  {
                     projectsService.call(new RemoteCallback<Boolean>()
                     {
                        @Override
                        public void callback(Boolean response)
                        {
                           BooleanMessenger.getMessenger("Manager Changed.", "Error changing manager.").message(
                                 response);

                           if (response){
                              display.asWindow().hide();
                              projectDisplay.asWindow().hide();
                           }
                        }
                     }).setProjectManager(project.getId(), selected.getId());
                  }
                  else
                  {
                     Info.display("Notification", "The selected users is already the project manager.");
                  }
               }
            }).show();
         }
      };
   }

   public void setChangeManager(boolean changeManager)
   {
      this.changeManager = changeManager;
   }

   public void setExcludeContacts(boolean excludeContacts)
   {
      this.excludeContacts = excludeContacts;
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

   public void setProjctDisplay(ProjectPresenter.Display projectDisplay)
   {
      this.projectDisplay = projectDisplay;
   }

}