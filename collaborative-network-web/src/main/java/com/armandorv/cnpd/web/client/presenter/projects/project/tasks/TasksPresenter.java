package com.armandorv.cnpd.web.client.presenter.projects.project.tasks;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.ioc.client.api.AfterInitialization;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class TasksPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();

      void selectFirst();
   }

   @Inject
   @Project
   private Event<Integer> tabSelectedEvent;

   @Inject
   private Display display;

   @Inject
   private TasksListPresenter tasksListPresenter;

   @Inject
   private NewTaskPresenter newTaskPresenter;

   @Inject
   private TasksSummaryPresenter tasksSummaryPresenter;

   private ProjectInfo project;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getTabPanel().addSelectionHandler(selectPresenter());
   }

   private SelectionHandler<Widget> selectPresenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            Widget widget = event.getSelectedItem();

            if (widget instanceof TasksListPresenter.Display)
            {
               tasksListPresenter.setProject(project);
               tasksListPresenter.present();
            }

            if (widget instanceof NewTaskPresenter.Display)
            {
               newTaskPresenter.setProject(project);
               newTaskPresenter.present();
            }

            if (widget instanceof TasksSummaryPresenter.Display)
            {
               tasksSummaryPresenter.setProject(project);
               tasksSummaryPresenter.present();
            }
         }
      };
   }

   @Override
   public void present( )
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      tasksListPresenter.setProject(project);
      tasksListPresenter.present();

      display.selectFirst();
      
      tabSelectedEvent.fire(display.getIndex());
   }

   public void setProject(@Observes ProjectInfo project)
   {
      this.project = project;
   }

}