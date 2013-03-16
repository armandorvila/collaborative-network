package com.armandorv.cnpd.web.client.presenter.projects.project.resources.callback;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Deal with server response for single resources creation. Check returned id,
 * if correct set into the new resource, new resource must be a reference to
 * resource object of creation process.
 * 
 * @author armandorv
 * 
 */
public class SingleCreationCallback implements RemoteCallback<ResourceInfo>
{

   private ResourcesPresenter.Display display;

   private boolean free = true;

   public SingleCreationCallback(Display display, boolean free)
   {
      super();
      this.display = display;
      this.free = free;
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
         ResourceInfo parent = display.getSelected(free);
         display.addResource(parent, response, free);
         Info.display("Notificaton", "Resurce created succesfully on " + parent.getName());
      }
   }

}
