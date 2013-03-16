package com.armandorv.cnpd.web.client.view.projects.project;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.view.chat.ChatView;
import com.armandorv.cnpd.web.client.view.projects.project.discussions.DiscussionsView;
import com.armandorv.cnpd.web.client.view.projects.project.info.ProjectInfoView;
import com.armandorv.cnpd.web.client.view.projects.project.management.ManagementView;
import com.armandorv.cnpd.web.client.view.projects.project.milestones.MilestonesView;
import com.armandorv.cnpd.web.client.view.projects.project.references.ReferencesView;
import com.armandorv.cnpd.web.client.view.projects.project.resources.ResourcesView;
import com.armandorv.cnpd.web.client.view.projects.project.tasks.TasksView;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;

/**
 * View for a concrete Project, there is a view for each project that you 'll
 * want open.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ProjectView implements ProjectPresenter.Display
{
   public static class Indexes
   {
      public static final int PROJECT_INDEX = 0;

      public static final int RESOURCES_INDEX = 1;

      public static final int REFERENCES_INDEX = 2;

      public static final int DISCUSSIONS_INDEX = 3;

      public static final int MILESTONES_INDEX = 4;

      public static final int TASKS_INDEX = 5;
   }

   @UiTemplate("ProjectView.ui.xml")
   public interface ProjectViewUiBinder extends UiBinder<Widget, ProjectView>
   {
   }

   private ProjectViewUiBinder uiBinder = GWT.create(ProjectViewUiBinder.class);

   @UiField
   ProjectInfoView info;

   @UiField
   ChatView projectChat;

   @Inject
   @UiField(provided = true)
   ManagementView managementView;

   @Inject
   @UiField(provided = true)
   ResourcesView resourcesView;

   @Inject
   @UiField(provided = true)
   ReferencesView referencesView;

   @Inject
   @UiField(provided = true)
   DiscussionsView discussionsView;

   @Inject
   @UiField(provided = true)
   MilestonesView scheduleView;

   @Inject
   @UiField(provided = true)
   TasksView tasksView;

   @UiField
   Window window;

   @UiField
   TabPanel tabPanel;
   
   @UiField
   ContentPanel managementPanel;

   @PostConstruct
   public void initView()
   {
      uiBinder.createAndBindUi(this);
   }

   @Override
   public Window asWindow()
   {
      managementPanel.collapse();
     
      return window;
   }

   @Override
   public HasSelectionHandlers<Widget> getSelecteablePanel()
   {
      return tabPanel;
   }

   @Override
   public void selectTab(Integer index)
   {
      if (index != null && this.tabPanel != null)
      {
         Widget widget = tabPanel.getWidget(index);
         if (!tabPanel.getActiveWidget().equals(widget))
            this.tabPanel.setActiveWidget(widget);
      }
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.PROJECT_INDEX;
   }

   @Override
   public HasSelectHandlers getSelectableChat()
   {
      return this.projectChat.getClickable();
   }

   @Override
   public void renderProjectData(ProjectInfo project)
   {
      info.renderProjectData(project);
   }

   @Override
   public void setMembers(List<ContactInfo> members)
   {
      info.renderMembers(members);
   }

   @Override
   public void setChatMembers(List<ContactInfo> members)
   {
      projectChat.addContacts(members);
   }

   @Override
   public void removeFromChat(ContactInfo contact)
   {
      projectChat.removeContact(contact);
   }

   @Override
   public void addToChat(ContactInfo contact)
   {
      projectChat.addContact(contact);
   }

}