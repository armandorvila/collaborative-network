package com.armandorv.cnpd.web.client.presenter.projects.project.management;

import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.project.management.ModifyProjectView;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class ModifyProjectPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setTitle(String title);

      String getTitle();

      void setDescription(String title);

      String getDescription();

      void setKnowledgeAreas(List<IdNameGenericInfo> kas);

      IdNameGenericInfo getKnowledegeArea();

      HasSelectHandlers getSaveButton();

      void showMessage(String string);

      void setKnowledgeArea(IdNameGenericInfo knowledgeArea);
   }

   private Display display = new ModifyProjectView();

   private Caller<ProjectsService> projectsService;

   private Caller<InformationService> infoService;
   
   private ProjectInfo project;

   private ProjectPresenter.Display projectDisplay;

   public ModifyProjectPresenter(ProjectInfo project, ProjectPresenter.Display projectDisplay, Caller<ProjectsService> projectsService,
         Caller<InformationService> infoService)
   {
      super();
      this.projectsService = projectsService;
      this.infoService = infoService;
      this.project = project;
      this.projectDisplay = projectDisplay;
     
      loadAreas();
     
      display.setTitle(project.getTitle());
      display.setDescription(project.getDescription());
      display.setKnowledgeArea(project.getKnowledgeArea());

      display.getSaveButton().addSelectHandler(save());
   }

   private void loadAreas()
   {
      infoService.call(new RemoteCallback<List<IdNameGenericInfo>>()
      {
         @Override
         public void callback(List<IdNameGenericInfo> response)
         {
            display.setKnowledgeAreas(response);
         }
      }).retrieveAllKnowledgeAreas();
   }

   private SelectHandler save()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            ProjectInfo modified = new ProjectInfo();
            modified.setId(project.getId());
            modified.setTitle(display.getTitle());
            modified.setDescription(display.getDescription());
            modified.setKnowledgeArea(display.getKnowledegeArea());
            
            projectsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Project modified.", "Error modifing project.").message(response);
                  
                  if(response){
                     project.setDescription(display.getDescription());
                     project.setTitle(display.getTitle());
                     project.setKnowledgeArea(display.getKnowledegeArea());
                     projectDisplay.renderProjectData(project);
                  }
                  
                  display.asWindow().hide();
                     
               }
            }).modifyProject(modified);
         }
      };
   }

   @Override
   public void present()
   {
      display.asWindow().show();
   }
}