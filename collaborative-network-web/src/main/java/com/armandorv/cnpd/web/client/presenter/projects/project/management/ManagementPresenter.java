package com.armandorv.cnpd.web.client.presenter.projects.project.management;

import javax.enterprise.event.Observes;
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
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;

@Singleton
public class ManagementPresenter implements Presenter
{
   public interface Executable
   {
      void execute();
   }

   public interface Display
   {
      void setPublish(Executable publish);

      void setDelete(Executable delete);

      void setChangeManager(Executable changeManager);

      void setInviteContacts(Executable inviteCotnacts);

      void setExcludeContacts(Executable excludeCotnacts);

      void setEnabled(boolean enabled);

      void setModify(Executable modify);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private Caller<InformationService> informationService;

   @Inject
   private MembersPresenter membersPresenter;

   private ProjectInfo project;

   private UserInfo user;

   private ProjectPresenter.Display projectDisplay;

   @AfterInitialization
   public void afterInitialization()
   {
      display.setPublish(publish());
      display.setDelete(delete());
      display.setModify(modify());
      display.setChangeManager(changeManager());
      display.setInviteContacts(inviteContacts());
      display.setExcludeContacts(excludeContacts());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      if (project == null)
         throw new ClientsideException("A project must be set previously.");

      display.setEnabled(user.getId() == project.getManagerId());
   }

   private Executable excludeContacts()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            membersPresenter.setChangeManager(false);
            membersPresenter.setExcludeContacts(true);
            membersPresenter.setProject(project);
            membersPresenter.setProjctDisplay(projectDisplay);
            membersPresenter.present();
         }
      };
   }

   private Executable inviteContacts()
   {
      final Presenter parent = this;

      return new Executable()
      {
         @Override
         public void execute()
         {
            new InvitationsPresenter(parent, contactsService, projectsService, user, project).present();
         }
      };
   }

   private Executable changeManager()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            membersPresenter.setChangeManager(true);
            membersPresenter.setExcludeContacts(false);
            membersPresenter.setProject(project);
            membersPresenter.setProjctDisplay(projectDisplay);
            membersPresenter.present();
         }
      };
   }

   private Executable delete()
   {
      return new Executable()
      {
         public void execute()
         {
            new ConfirmationView("Project will be deleted, are you sure ?", new ConfirmationView.Action()
            {
               @Override
               public void execute()
               {
                  projectsService.call(new RemoteCallback<Boolean>()
                  {
                     @Override
                     public void callback(Boolean response)
                     {
                        BooleanMessenger
                              .getMessenger("Project Deleted",
                                    "Error deleting project").message(response);
                        if (response)
                           projectDisplay.asWindow().hide();
                     }
                  }).deleteProject(project.getId());
               }
            }).show();
         }
      };
   }

   private Executable publish()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ConfirmationView("This action is definitive, are you sure ?", new ConfirmationView.Action()
            {
               @Override
               public void execute()
               {
                  projectsService.call(new RemoteCallback<Boolean>()
                  {
                     @Override
                     public void callback(Boolean response)
                     {
                        BooleanMessenger
                              .getMessenger("Project publisht",
                                    "Error publishing project").message(response);
                     }
                  }).publishProject(project.getId());
               }
            }).show();

         }
      };
   }

   private Executable modify()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ModifyProjectPresenter(project,projectDisplay, projectsService, informationService)
                  .present();
         }
      };
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

   public void setProjectDisplay(ProjectPresenter.Display projectDisplay)
   {
      this.projectDisplay = projectDisplay;
   }

}