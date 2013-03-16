package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.projects.project.resources.file.FileEditorView;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Handler for edit resource item selection events.
 * 
 * @author armandorv
 *
 */
public class EditResourceHandler implements SelectionHandler<Item>, SelectHandler
{
   private boolean drafts;

   private ResourcesPresenter presenter;

   private FileEditorView fileEditor = new FileEditorView();

   public EditResourceHandler(ResourcesPresenter presenter, boolean free)
   {
      super();
      this.drafts = free;
      this.presenter = presenter;
      fileEditor.getSaveButton().addSelectHandler(this);
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      ResourceInfo resource = presenter.getDisplay().getSelected(drafts);

      presenter.getResourcesService().call(new RemoteCallback<String>()
      {
         @Override
         public void callback(String response)
         {
            fileEditor.setFileText(response);
            fileEditor.asWindow().show();
         }
      }).getFileContent(presenter.getProject().getId(), resource);
   }

   @Override
   public void onSelect(SelectEvent event)
   {
      ResourceInfo resource = presenter.getDisplay().getSelected(drafts);

      String parentUrl = presenter.getDisplay().getParent(resource, drafts).getUrl();

      if (parentUrl == null)
         parentUrl = presenter.getProject().getUrl();

      presenter.getResourcesService().call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            BooleanMessenger.getMessenger("File modified.", "Error modifying file.")
                  .message(response);

            if (response)
            {
               fileEditor.asWindow().hide();
            }

         }
      }).saveFileContent(resource.getUrl(), parentUrl, fileEditor.getFileText());
      
      fileEditor.showProgress();
   }

}