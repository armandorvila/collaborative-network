package com.armandorv.cnpd.web.client.presenter.projects;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

@Singleton
public class NewProjectPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setKnowledgeAreas(List<IdNameGenericInfo> kas);

      IdNameGenericInfo getNewProjectKnowledegeArea();

      String getProjectTitle();

      String getDescription();

      HasSelectHandlers getCreateButton();

      void clear();
   }

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Caller<InformationService> infoService;

   @Inject
   private Caller<ContactsService> contactsService;

   @Inject
   private Display display;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getCreateButton().addSelectHandler(this.createProject());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

      display.clear();
      this.loadKas();
   }

   private void loadKas()
   {
      this.infoService.call(new RemoteCallback<List<IdNameGenericInfo>>()
      {
         public void callback(List<IdNameGenericInfo> response)
         {
            display.setKnowledgeAreas(response);
         }

      }).retrieveAllKnowledgeAreas();

   }

   private SelectHandler createProject()
   {
      final NewProjectPresenter me = this;

      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {

            if (validateNewProjectFields())
            {
               ProjectInfo project = new ProjectInfo();
               project.setTitle(display.getProjectTitle());
               project.setDescription(display.getDescription());
               project.setPublished(false);
               project.setKnowledgeArea(display.getNewProjectKnowledegeArea());

               new InvitationsOnCreationPresenter(me, contactsService, projectsService, user, project).present();
            }

         }
      };
   }

   private boolean validateNewProjectFields()
   {
      if ("".equals(display.getProjectTitle()))
      {
         Info.display("Invalid field :", "You have to select a title.");
         return false;
      }

      if (display.getNewProjectKnowledegeArea() == null)
      {
         Info.display("Invalid field :", "You have to select a knowledge area.");
         return false;
      }
      return true;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}