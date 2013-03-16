package com.armandorv.cnpd.web.client.view.projects;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * View for user projects, here a user 'll able to list their projects and
 * select it
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ProjectsView extends Composite implements ProjectsPresenter.Display
{
   public interface ProjectsViewUIBinder extends UiBinder<Widget, ProjectsView>
   {
   }

   private ProjectsViewUIBinder uiBinder = GWT.create(ProjectsViewUIBinder.class);

   @UiField
   ProjectsListView projectsListView;

   @UiField
   NewProjectView newProjectView;

   @UiField
   FindProjectsView findProjectsView;

   @UiField
   ProjectInvitationsView projectInvitationsView;

   @UiField
   TabPanel tabPanel;

   public ProjectsView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return MainWindow.Indexes.PROJECTS_INDEX;
   }

   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }

   @Produces
   @Singleton
   public ProjectsListPresenter.Display produceListProjectsView()
   {
      return projectsListView;
   }

   @Produces
   @Singleton
   public NewProjectPresenter.Display produceNewProjectView()
   {
      return newProjectView;
   }

   @Produces
   @Singleton
   public FindProjectsPresenter.Display produceFindProjectsView()
   {
      return findProjectsView;
   }

   @Produces
   @Singleton
   public ProjectInvitationsPresenter.Display produceProjectInvitationsView()
   {
      return projectInvitationsView;
   }

   @Override
   public void selectFirstTab()
   {
      if (!tabPanel.getActiveWidget().equals(projectsListView))
         tabPanel.setActiveWidget(projectsListView);
   }
}