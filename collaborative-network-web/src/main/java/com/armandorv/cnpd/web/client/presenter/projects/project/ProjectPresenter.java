package com.armandorv.cnpd.web.client.presenter.projects.project;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.event.EntryDiscussionsEvent;
import com.armandorv.cnpd.web.client.event.EntryMilestonesEvent;
import com.armandorv.cnpd.web.client.event.EntryProjectEvent;
import com.armandorv.cnpd.web.client.event.EntryProjectsEvent;
import com.armandorv.cnpd.web.client.event.EntryReferencesEvent;
import com.armandorv.cnpd.web.client.event.EntryResourcesEvent;
import com.armandorv.cnpd.web.client.event.EntryTasksEvent;
import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.info.ProjectInfoView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.ContactMinus;
import com.armandorv.cnpd.web.shared.qualifiers.ContactPlus;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for ProjectView (not ProjectsView).
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ProjectPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      HasSelectionHandlers<Widget> getSelecteablePanel();

      HasSelectHandlers getSelectableChat();

      void selectTab(Integer index);

      Integer getIndex();

      void renderProjectData(ProjectInfo project);

      void setMembers(List<ContactInfo> members);

      void setChatMembers(List<ContactInfo> members);

      void removeFromChat(ContactInfo contact);

      void addToChat(ContactInfo contact);
   }

   private ProjectInfo project;

   @Inject
   private Display display;

   @Inject
   private ConversationsPresenter conversationsPresenter;

   @Inject
   private ManagementPresenter managementPresenter;

   @Inject
   private HandlerManager eventBus;

   @Inject
   private Caller<ProjectsService> projectsService;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getSelectableChat().addSelectHandler(createChatHandler());
      display.asWindow().addBeforeHideHandler(createHideHandler());
      display.getSelecteablePanel().addSelectionHandler(selectPresenter());
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      projectsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            project.setMembers(response);

            for (ContactInfo member : project.getMembers())
            {
               if (member.getId() == project.getManagerId())
               {
                  project.setManager(member.getName());
               }
            }

            display.setMembers(project.getMembers());
            display.renderProjectData(project);

            projectsService.call(new RemoteCallback<List<ContactInfo>>()
            {
               @Override
               public void callback(List<ContactInfo> response)
               {
                  display.setChatMembers(response);
               }
            }).getConnectdMembers(project.getId(), user.getId());

            managementPresenter.setProject(project);
            managementPresenter.setProjectDisplay(display);
            managementPresenter.present();
         }
      }).getMembers(project.getId());

      if (!display.asWindow().isVisible())
         display.asWindow().show();

      display.selectTab(display.getIndex());
   }

   private SelectHandler createChatHandler()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            ContactInfo contact = project.getMembers().get(event.getContext().getIndex());
            conversationsPresenter.addConversation(contact);
            conversationsPresenter.present();
         }
      };
   }

   private BeforeHideHandler createHideHandler()
   {
      return new BeforeHideHandler()
      {
         @Override
         public void onBeforeHide(BeforeHideEvent event)
         {
            eventBus.fireEvent(new EntryProjectsEvent());
         }
      };
   }

   /**
    * Select handler which execute the correct presenter for each tab.
    */
   private SelectionHandler<Widget> selectPresenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            if (event.getSelectedItem() instanceof ProjectInfoView)
            {
               eventBus.fireEvent(new EntryProjectEvent());
            }
            else if (event.getSelectedItem() instanceof ResourcesPresenter.Display)
            {
               eventBus.fireEvent(new EntryResourcesEvent());
            }
            else if (event.getSelectedItem() instanceof ReferencesPresenter.Display)
            {
               eventBus.fireEvent(new EntryReferencesEvent());
            }
            else if (event.getSelectedItem() instanceof MilestonesPresenter.Display)
            {
               eventBus.fireEvent(new EntryMilestonesEvent());
            }
            else if (event.getSelectedItem() instanceof DiscussionsPresenter.Display)
            {
               eventBus.fireEvent(new EntryDiscussionsEvent());
            }
            else if (event.getSelectedItem() instanceof TasksPresenter.Display)
            {
               eventBus.fireEvent(new EntryTasksEvent());
            }
         }
      };

   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

   public void contactConnected(@Observes @ContactPlus final ContactInfo contact)
   {
      if (project != null)
      {
         if (project.getMembers() != null && project.getMembers().contains(contact))
         {
            if (contact.getId() != user.getId())
               display.addToChat(contact);
         }
      }
   }

   /**
    * When a new User disconnect.
    */
   public void contactDisconnected(@Observes @ContactMinus ContactInfo contact)
   {
      if (project != null)
      {
         if (project.getMembers() != null && project.getMembers().contains(contact))
         {
            if (contact.getId() != user.getId())
               display.removeFromChat(contact);
         }
      }
   }

   /**
    * When a presenter is executed by history management system, fire an event with their index.
    * This method catch it and select the tab.
    * 
    * @param index of tab to select.
    */
   public void listenHistorySelection(@Observes @Project Integer index)
   {
      display.selectTab(index);
   }

}