package com.armandorv.cnpd.web.client.presenter.projects.project.tasks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.shared.remote.TasksService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class TasksListPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setTasks(List<TaskInfo> tasks);

      TaskInfo getTask(int row);

      void removeTask(TaskInfo task);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getWork();

      HasSelectionHandlers<Item> getDelete();

      HasSelectionHandlers<Item> getDone();

      PromptMessageBox getHoursMessageBox();

      void refresh();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<TasksService> tasksService;

   private ProjectInfo project;

   private TaskInfo selected;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getDelete().addSelectionHandler(delete());
      display.getDone().addSelectionHandler(done());

      display.getWork().addSelectionHandler(askHours());
      display.getHoursMessageBox().addHideHandler(work());
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      loadTasks();
   }

   private void loadTasks()
   {
      tasksService.call(new RemoteCallback<List<TaskInfo>>()
      {
         @Override
         public void callback(List<TaskInfo> response)
         {
            display.setTasks(response);
         }
      }).getTasks(project.getId());
   }

   private SelectionHandler<Item> done()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            tasksService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Task completed.", "Error setting task as completed.")
                        .message(response);

                  if (response){
                     selected.setCompleted(true);
                     display.refresh();
                  }
               }
            }).setTaskAsCompleted(selected.getId());
         }
      };
   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            tasksService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Task deleted.", "Error deleting task.")
                        .message(response);

                  if (response)
                     display.removeTask(selected);
               }
            }).deleteTask(selected.getId());
         }
      };
   }

   private SelectionHandler<Item> askHours()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            display.getHoursMessageBox().show();
         }
      };
   }

   private HideHandler work()
   {
      return new HideHandler()
      {
         @Override
         public void onHide(HideEvent event)
         {
            if ("OK".equals(display.getHoursMessageBox().getHideButton().getText()))
            {
               final String value = display.getHoursMessageBox().getValue().trim();

               if (value != null && value.matches("[0-9]+"))
               {
                  tasksService.call(new RemoteCallback<Boolean>()
                  {
                     @Override
                     public void callback(Boolean response)
                     {
                        BooleanMessenger.getMessenger("Addedd " + value + " hours.", "Error to add hours.").message(response);

                        if (response){
                           selected.setWorkedHours(selected.getWorkedHours() + Integer.parseInt(value));
                           display.refresh();
                        }
                     }
                  }).imputeHoursToTask(selected.getId(), Integer.parseInt(value));
               }
               else
               {
                  Info.display("Error", "You have to introduce an integer value.");
               }
            }
         }

      };

   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getTask(event.getContext().getIndex());
         }
      };
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}