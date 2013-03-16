package com.armandorv.cnpd.web.client.presenter.projects.project.resources.callback;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Crea un recurso de tipo folder, en la respuesta crea uno de tipo file, y en
 * la respuesta de este añade el folder y le file dentro de este al árbol.
 * 
 * @author armandorv
 * 
 */
public class FolderCreationCallback implements RemoteCallback<ResourceInfo>
{
   private ResourcesPresenter.Display display;

   private boolean isFree = true;

   private ResourceInfo folder;

   private Caller<ResourcesService> service;

   private long projectId;

   public FolderCreationCallback(Display display, boolean free, Caller<ResourcesService> service, long projectId)
   {
      this.display = display;
      this.isFree = free;
      this.service = service;
      this.projectId = projectId;
   }

   @Override
   public void callback(ResourceInfo response)
   {
      if (response == null)
      {
         Info.display("Notificaton", "Error creating resource.");
      }
      else
      {
         folder = response;
         service.call(createExampleRemoteCallback()).createResource(projectId, folder.getId(),
               new ResourceInfo(0, "description", Kind.MARKER), true);
      }
   }

   private RemoteCallback<ResourceInfo> createExampleRemoteCallback()
   {
      return new RemoteCallback<ResourceInfo>()
      {
         @Override
         public void callback(ResourceInfo response)
         {
            if (response != null)
            {
               ResourceInfo parent = display.getSelected(isFree);

               display.addResource(parent, folder, isFree);
               display.addResource(folder, response, isFree);

               Info.display("Notificaton", "Resurce created succesfully on " + parent.getName());
            }
            else
            {
               Info.display("Notificaton", "Error creating folder description, you have to create it manually.");
            }
         }
      };
   }

}