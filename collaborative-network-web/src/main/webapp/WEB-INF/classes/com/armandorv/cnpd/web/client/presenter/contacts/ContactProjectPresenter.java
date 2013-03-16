package com.armandorv.cnpd.web.client.presenter.contacts;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.sencha.gxt.widget.core.client.Window;

@Singleton
public class ContactProjectPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void renderProjectInfo(ProjectInfo project);

      void setResources(List<ResourceInfo> resources);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<ProjectsService> projectsService;

   @Inject
   private Caller<ResourcesService> resourcesService;

   private ProjectInfo project;

   @Override
   public void present()
   {
      if (!project.isPublished())
         throw new ClientsideException("A private project can't be visited.");

      loadProjectMembers();
      loadProjectResources();

      display.asWindow().show();
   }

   private void loadProjectMembers()
   {
      projectsService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            project.setMembers(response);

            for (ContactInfo member : project.getMembers())
            {
               if (member.getId() == project.getManagerId())
                  project.setManager(member.getName());
            }

            display.renderProjectInfo(project);
         }
      }).getMembers(project.getId());
   }

   private void loadProjectResources()
   {
      resourcesService.call(new RemoteCallback<List<ResourceInfo>>()
      {
         @Override
         public void callback(List<ResourceInfo> response)
         {
            display.setResources(response);
         }
      }).getResources(project.getId(), false);
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}