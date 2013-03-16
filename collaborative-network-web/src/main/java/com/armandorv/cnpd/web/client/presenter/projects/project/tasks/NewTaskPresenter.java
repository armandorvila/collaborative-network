package com.armandorv.cnpd.web.client.presenter.projects.project.tasks;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.TasksService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

@Singleton
public class NewTaskPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      String getName();

      Date getBeguining();

      Integer getDuration();

      HasSelectHandlers getCreate();

      void clear();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<TasksService> tasksService;

   private ProjectInfo project;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getCreate().addSelectHandler(create());
   }

   private SelectHandler create()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            if (validateFileds())
            {
               tasksService.call(new RemoteCallback<Boolean>()
               {
                  @Override
                  public void callback(Boolean response)
                  {
                     BooleanMessenger.getMessenger("Task created successfully", "Error creating task.")
                           .message(response);

                     if (response)
                        display.clear();
                  }
               }).createTask(project.getId(), display.getName(), display.getDuration(), display.getBeguining());

            }
         }

         private boolean validateFileds()
         {
            if (display.getBeguining() == null)
            {
               Info.display("Error", "You have to set the beguining date");
               return false;
            }

            if (display.getName() == null || "".equals(display.getName()))
            {
               Info.display("Error", "You have to set a name.");
               return false;
            }

            return true;
         }
      };
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      display.clear();
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}