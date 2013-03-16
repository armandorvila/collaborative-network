package com.armandorv.cnpd.web.client.presenter.projects;

import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
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
public class ProjectsListPresenter implements Presenter
{
   public interface Display
   {
      ProjectInfo getSelected(int row);

      Widget asWidget();

      void addProject(ProjectInfo project);

      void setProjects(List<ProjectInfo> projects);
      
      void refresh();

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getOpen();

      HasSelectionHandlers<Item> getLeave();

      void showProgress();

      void remove(ProjectInfo selected);
   }

   @Inject
   private Caller<ProjectsService> projectsService;
   
   @Inject
   private Event<ProjectInfo> selectProject;

   @Inject
   private ProjectPresenter projectPresenter;

   @Inject
   private Display display;

   private UserInfo user;

   private ProjectInfo selected;

   private boolean isStart = true;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getOpen().addSelectionHandler(open());
      display.getLeave().addSelectionHandler(leave());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      this.loadProjects();

      if (isStart)
      {
         display.showProgress();
         isStart = false;
      }
   }

   private void loadProjects()
   {
      this.projectsService.call(new RemoteCallback<List<ProjectInfo>>()
      {
         public void callback(List<ProjectInfo> response)
         {
            display.setProjects(response);
            display.refresh();
         }
      }).getProjects(user.getId());

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

   private SelectionHandler<Item> open()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            selectProject.fire(selected);
            projectPresenter.setProject(selected);
            projectPresenter.present();
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
            projectsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger(
                        "Project leaft.", "Error leaving project.")
                        .message(response);
                  
                  if(response){
                     display.remove(selected);
                     display.refresh();
                  }
               }
            }).leaveProject(user.getId(), selected.getId());
         }
      };

   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}