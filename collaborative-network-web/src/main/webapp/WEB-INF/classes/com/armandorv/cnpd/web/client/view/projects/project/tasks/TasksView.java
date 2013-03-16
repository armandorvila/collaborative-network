package com.armandorv.cnpd.web.client.view.projects.project.tasks;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabPanel;

@Singleton
public class TasksView extends Composite implements TasksPresenter.Display
{
   public interface TasksViewUiBinder extends UiBinder<Widget, TasksView>
   {
   }

   private static TasksViewUiBinder uiBinder = GWT.create(TasksViewUiBinder.class);

   @UiField
   TabPanel tabPanel;

   @UiField
   TasksListView tasksListView;

   @UiField
   NewTaskView newTaskView;

   @UiField
   TasksSummaryView tasksSummaryView;

   public TasksView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.TASKS_INDEX;
   }

   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }

   @Override
   public void selectFirst()
   {
      tabPanel.setActiveWidget(tabPanel.getWidget(0));
   }
   
   @Produces
   @Singleton
   public TasksListPresenter.Display produceTasksListView()
   {
      return tasksListView;
   }

   @Produces
   @Singleton
   public NewTaskPresenter.Display produceNewTaskView()
   {
      return newTaskView;
   }

   @Produces
   @Singleton
   public TasksSummaryPresenter.Display produceTasksSummaryView()
   {
      return tasksSummaryView;
   }

}