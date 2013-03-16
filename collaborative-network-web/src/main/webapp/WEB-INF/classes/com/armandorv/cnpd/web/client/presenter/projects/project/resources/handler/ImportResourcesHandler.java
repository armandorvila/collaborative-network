package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.project.resources.GDocsResourcesView;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

public class ImportResourcesHandler implements SelectionHandler<Item>, SelectHandler
{
   private boolean drafts;

   private ResourcesPresenter presenter;

   private Kind kind;

   private GDocsResourcesView gdocsView;

   public ImportResourcesHandler(boolean drafts, ResourcesPresenter presenter, Kind kind)
   {
      super();
      this.drafts = drafts;
      this.presenter = presenter;
      this.kind = kind;

      this.gdocsView = new GDocsResourcesView(kind);
      this.gdocsView.getImportButton().addSelectHandler(this);
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      if (presenter.getDisplay().getSelected(drafts) != null)
      {
         presenter.getResourcesService().call(new RemoteCallback<List<GDocsResource>>()
         {
            @Override
            public void callback(List<GDocsResource> response)
            {
               gdocsView.setResources(response);
               gdocsView.asWindow().show();
            }
         }).getGoogleDocsResources(presenter.getUser().getId(), kind);

         gdocsView.showProgress();
      }
      else
      {
         Info.display("Error", "You have to select a destiny.");
      }
   }

   @Override
   public void onSelect(SelectEvent event)
   {
      GDocsResource resource = gdocsView.getSelected(event.getContext().getIndex());

      if (presenter.getDisplay().getSelected(drafts) == null)
      {
         Info.display("Invalid Operation", "You have to select any folder.");
      }
      else if (!presenter.getDisplay().getSelected(drafts).getKind().equals(Kind.FOLDER)
            && !presenter.getDisplay().getSelected(drafts).getKind().equals(Kind.ROOT))
      {
         Info.display("Invalid Operation", "You have to select a folder.");
      }
      else
      {
         presenter.getResourcesService().call(new RemoteCallback<Boolean>()
         {
            @Override
            public void callback(Boolean response)
            {
               BooleanMessenger.getMessenger("Resource Imported.", "Error imporintg resource.").message(response);

               if (response)
                  presenter.present();
            }

         }).importResource(presenter.getProject().getId(), resource, kind, presenter.getDisplay().getSelected(drafts));

      }
   }

}
