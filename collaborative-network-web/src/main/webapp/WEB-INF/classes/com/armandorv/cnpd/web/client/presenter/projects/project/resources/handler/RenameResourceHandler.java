package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.view.util.PromptView;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 *
 * @author armandorv
 *
 */
public class RenameResourceHandler implements SelectionHandler<Item>
{
   private ResourcesPresenter presenter;

   private boolean draft;

   public RenameResourceHandler(ResourcesPresenter presenter, boolean draft)
   {
      this.draft = draft;
      this.presenter = presenter;
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      ResourceInfo resource = presenter.getDisplay().getSelected(draft);
      if (resource == null)
      {
         Info.display("Error", "You have to select a resource");
      }
      else
      {
         PromptView view = new PromptView("Rename resource", "Please enter a name :");
         view.addAction( renameResource(resource));
         view.show();
      }
   }

   private PromptView.Action renameResource(final ResourceInfo resource)
   {
      return new PromptView.Action()
      {
         @Override
         public void execute(String value)
         {
            if (value == null)
            {
               Info.display("Error", "you have to introduce a name.");
            }
            else
            {
               final String name = Format.ellipse(value, 80);
               presenter.getResourcesService().call(new RemoteCallback<Boolean>()
               {
                  @Override
                  public void callback(Boolean response)
                  {
                     if (response)
                     {
                        presenter.getDisplay().rename(resource, name, draft);
                     }
                     else
                     {
                        Info.display("Error", "Error renaming resourcce.");
                     }
                  }
               }).renameResource(presenter.getProject().getId(), resource.getId(), value);
            }
         }
      };
   }

}