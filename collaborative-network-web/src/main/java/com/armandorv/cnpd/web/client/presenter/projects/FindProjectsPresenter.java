package com.armandorv.cnpd.web.client.presenter.projects;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class FindProjectsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setKnowledgeAreas(List<IdNameGenericInfo> kas);

      HasSelectHandlers getSearchButton();

      IdNameGenericInfo getSearchKnowledgeArea();

      String getSearchTitle();

      void setFoundProjects(List<ProjectInfo> projects);
      
      HasSelectHandlers getOpenButton();
      
      ProjectInfo getSelected(int row);
   }

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Caller<InformationService> infoService;

   @Inject
   private Display display;
   
   @Inject
   private ContactProjectPresenter contactProjectPresenter;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getSearchButton().addSelectHandler(search());
      display.getOpenButton().addSelectHandler(open());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("A user must be set previously.");

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

   private SelectHandler search()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            projectsService.call(new RemoteCallback<List<ProjectInfo>>()
            {
               public void callback(List<ProjectInfo> response)
               {
                  display.setFoundProjects(response);
                  
                  if(response.isEmpty())
                     Info.display("Notification" , "No projects found.");
               }
            }).searchProjects(display.getSearchTitle(), display.getSearchKnowledgeArea());
         }
      };
   }
   
   private SelectHandler open()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            contactProjectPresenter.setProject(display.getSelected(event.getContext().getIndex()));
            contactProjectPresenter.present();
         }
      };
   }


   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}