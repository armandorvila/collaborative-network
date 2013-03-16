package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreAddEvent.StoreAddHandler;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent.DndDragStartHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class MoveResourceHandler implements StoreAddHandler<ResourceInfo>, DndDragStartHandler
{
   /* It 's shared by all instance of this class.*/
   private static boolean onDragEvent;

   private ResourcesPresenter resourcesPresenter;

   private RemoteCallback<Boolean> callback = new RemoteCallback<Boolean>()
   {
      @Override
      public void callback(Boolean response)
      {
         BooleanMessenger.getMessenger("Resource moved.", "Error Moving resource")
               .message(response);
      }
   };

   private boolean draftsStore;
   
   public MoveResourceHandler (ResourcesPresenter resourcesPresenter , boolean draftsStore) {
      this.draftsStore = draftsStore;
      this.resourcesPresenter = resourcesPresenter;
   }

   @Override
   public void onAdd(StoreAddEvent<ResourceInfo> event)
   {
      if (onDragEvent)
      {
         for (ResourceInfo item : event.getItems())
         {
            ResourceInfo parent = resourcesPresenter.getDisplay().getParent(item, draftsStore);

            if (parent != null)
            {
               if (parent.getKind().equals(Kind.ROOT))
               {
                  resourcesPresenter.getResourcesService().call(callback)
                        .moveResourceToRoot(resourcesPresenter.getProject().getId(), item.getId(),
                              draftsStore);
               }
               else
               {
                  resourcesPresenter.getResourcesService().call(callback)
                        .moveResource(resourcesPresenter.getProject().getId(), item.getId(),
                              parent.getId());
               }

            }
            else
            {
               Info.display("Error", "Ilegall movement.");
               resourcesPresenter.present();
            }
         }
         onDragEvent = false;
      }
   }

   @Override
   public void onDragStart(DndDragStartEvent event)
   {
      onDragEvent = true;
   }

}