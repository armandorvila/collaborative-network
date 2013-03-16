package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * @author armandorv
 *
 */
public class DeleteResourceHandler implements SelectionHandler<Item>
{
   private ResourcesPresenter presenter;

   private boolean draft;

   public DeleteResourceHandler(ResourcesPresenter presenter, boolean draft)
   {
      this.draft = draft;
      this.presenter = presenter;
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      final ResourceInfo resource = presenter.getDisplay().getSelected(draft);

      presenter.getResourcesService().call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            BooleanMessenger.getMessenger(resource.getName() + " removed.", "Error removing resource.")
                  .message(response);

            if (response)
               presenter.getDisplay().remove(resource, draft);
         }
      }).deleteResource(presenter.getProject().getId(), resource.getId(), draft);
   }
}