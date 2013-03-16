package com.armandorv.cnpd.web.client.presenter.projects.project.tasks;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.shared.remote.TasksService;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class TasksSummaryPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();
      
      void setTasks(List<TaskInfo> tasks);
      
      void refresh();

      void showProgress();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<TasksService> tasksService;

   private ProjectInfo project;

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");
      
      loadTasks();
      display.showProgress();
   }
   
   private void loadTasks()
   {
      tasksService.call(new RemoteCallback<List<TaskInfo>>()
      {
         @Override
         public void callback(List<TaskInfo> response)
         {
            display.setTasks(response);
            display.refresh();
         }
      }).getTasks(project.getId());
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}