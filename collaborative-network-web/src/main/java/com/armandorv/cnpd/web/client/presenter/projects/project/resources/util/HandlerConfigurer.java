package com.armandorv.cnpd.web.client.presenter.projects.project.resources.util;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.CreateResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.DeleteResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.DownloadResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.EditResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.ImportResourcesHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.RenameResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.ShowResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.UploadResourceHandler;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Configure concrete handlers for concrete operations.
 * 
 * <ul>
 * 
 *  <caption><b>Operations :</b></caption>
 *  
 *  <li> Create File </li>
 *  <li> Create Document </li>
 *  <li> Create Folder </li>
 *  <li> Create Untyped </li>
 *  <li> Delete </li>
 *  <li> Rename </li>
 *  <li> Edit </li>
 *  <li> Show </li>
 *  <li> Upload </li>
 *  <li> Download </li>
 * 
 * </ul>
 * 
 * There is one or more operations to configure handlers for each of 
 * previously listed operations.
 * 
 * @author armandorv
 * 
 */
public class HandlerConfigurer
{
   private ResourcesPresenter presenter;

   private boolean drafts;

   public HandlerConfigurer(ResourcesPresenter presenter, boolean drafts)
   {
      super();
      this.presenter = presenter;
      this.drafts = drafts;
   }

   /**
    * Add a handler for files creation to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureCreateFileHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new CreateResourceHandler(presenter, Kind.FILE, drafts));
   }

   /**
    * Add a handler for folders creation to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureCreateFolderHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new CreateResourceHandler(presenter, Kind.FOLDER, drafts));
   }

   /**
    * Add a handler for folders creation to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureCreateUntypedHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new CreateResourceHandler(presenter, Kind.NONE, drafts));
   }

   public void configureDeleteHandler(HasSelectionHandlers<Item> item1, HasSelectionHandlers<Item> item2,
         HasSelectionHandlers<Item> item3)
   {
      this.configureDeleteHandler(item1);
      this.configureDeleteHandler(item2);
      this.configureDeleteHandler(item3);
   }

   public void configureDeleteHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new DeleteResourceHandler(presenter, drafts));
   }

   public void configureRenameHandler(HasSelectionHandlers<Item> item1, HasSelectionHandlers<Item> item2,
         HasSelectionHandlers<Item> item3)
   {
      this.configureRenameHandler(item1);
      this.configureRenameHandler(item2);
      this.configureRenameHandler(item3);
   }

   public void configureRenameHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new RenameResourceHandler(presenter, drafts));
   }

   public void configureEditHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new EditResourceHandler(presenter, drafts));
   }

   public void configureShowHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new ShowResourceHandler(presenter, drafts));
   }

   /**
    * Add a handler with uploading capabilities to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureUploadFileHandler(HasSelectionHandlers<Item> item , boolean replacement)
   {
      item.addSelectionHandler(new UploadResourceHandler(presenter, drafts,replacement, Kind.FILE));
   }

   /**
    * Add a handler with uploading capabilities to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureUploadDocumentHandler(HasSelectionHandlers<Item> item , boolean replacement)
   {
      item.addSelectionHandler(new UploadResourceHandler(presenter, drafts,replacement, Kind.DOCUMENT));
   }

   /**
    * Add a handler with uploading capabilities to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureUploadUntypedHandler(HasSelectionHandlers<Item> item , boolean replacement)
   {
      item.addSelectionHandler(new UploadResourceHandler(presenter, drafts,replacement, Kind.NONE));
   }

   /**
    * Add a handler with downloading capabilities to several items.
    * 
    * @param item n handler receivers.
    */
   public void configureDownloadHandler(HasSelectionHandlers<Item> item1, HasSelectionHandlers<Item> item2,
         HasSelectionHandlers<Item> item3)
   {
      this.configureDownloadHandler(item1);
      this.configureDownloadHandler(item2);
      this.configureDownloadHandler(item3);
   }

   /**
    * Add a handler with downloading capabilities to the given item.
    * 
    * @param item handler receiver.
    */
   public void configureDownloadHandler(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new DownloadResourceHandler(presenter, drafts));
   }

   public void configureImportDocument(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new ImportResourcesHandler(drafts, presenter, Kind.DOCUMENT));
   }

   public void configureImportFolder(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new ImportResourcesHandler(drafts, presenter, Kind.FOLDER));
   }

   public void configureImportUntyped(HasSelectionHandlers<Item> item)
   {
      item.addSelectionHandler(new ImportResourcesHandler(drafts, presenter, Kind.NONE));
   }

}