package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.callback.FolderCreationCallback;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.callback.SingleCreationCallback;
import com.armandorv.cnpd.web.client.view.util.PromptView;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Selection handler (executed on menu item selection) for resources top menus.
 * 
 * @author armandorv
 * 
 */
public class CreateResourceHandler implements SelectionHandler<Item>
{
   private ResourcesPresenter presenter;

   private ResourceInfo.Kind kind;

   private boolean free;

   public CreateResourceHandler(ResourcesPresenter presenter, Kind kind, boolean free)
   {
      this.kind = kind;
      this.free = free;
      this.presenter = presenter;
   }

   /**
    * -Ask a name, if correct call to server, and if correct modify display.
    */
   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      if (this.validateParent())
      {
         PromptView view = new PromptView("New resource", "Please enter a name :");
         view.addAction(createResource(presenter.getDisplay().getSelected(free)));
         view.show();
      }
   }

   /**
    * Check if parent of selected resource is correct, that means parent is not
    * null and parent.kind = folder
    * 
    * @return true if parent correct.
    */
   private boolean validateParent()
   {
      ResourceInfo parent = presenter.getDisplay().getSelected(free);

      if (parent == null)
      {
         Info.display("Error", "you have to select a parent.");
         return false;
      }

      if (!parent.getKind().equals(Kind.FOLDER) && !parent.getKind().equals(Kind.ROOT))
      {
         Info.display("Error", "Parent must be a folder.");
         return false;
      }
      return true;
   }

   /**
    * Create a resource for given name, by server invoking using custom calls.
    * 
    * @param name
    *            name of resource, it 's supposed to be already validated.
    */
   private PromptView.Action createResource(final ResourceInfo parent)
   {
      return new PromptView.Action()
      {
         public void execute(String value)
         {
            if (value == null)
            {
               Info.display("Error", "you have to introduce a name.");
            }
            else
            {
               ResourceInfo newResource = new ResourceInfo();
               newResource.setName(value);
               newResource.setKind(kind);

               Caller<ResourcesService> service = presenter.getResourcesService();
               Display display = presenter.getDisplay();
               long projectId = presenter.getProject().getId();

               if (kind.equals(Kind.FOLDER))
               {
                  service.call(
                        new FolderCreationCallback(display, free, service, projectId))
                        .createResource(projectId, parent.getId(),
                              newResource, free);
               }
               else
               {
                  service.call(new SingleCreationCallback(display, free))
                        .createResource(projectId, parent.getId(), newResource, free);
               }
            }
         }
      };
   }

}