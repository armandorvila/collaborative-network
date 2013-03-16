package com.armandorv.cnpd.web.client.view.projects.project.resources.menubar;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.util.HandlerConfigurer;
import com.armandorv.cnpd.web.theme.client.icons.resources.ResourcesIcons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * Menu bar widget for both resources panel, project resources and free
 * resources.
 * 
 * @author armandorv
 * 
 */
public class ResourcesMenuBar extends Composite
{
   public interface ResourcesMenuBarUiBinder extends UiBinder<Widget, ResourcesMenuBar>
   {
   }

   private static ResourcesMenuBarUiBinder uiBinder = GWT.create(ResourcesMenuBarUiBinder.class);

   private static ResourcesIcons icons = GWT.create(ResourcesIcons.class);

   @UiField
   MenuItem createUntyped;

   @UiField
   MenuItem createFile;

   @UiField
   MenuItem createDocument;

   @UiField
   MenuItem createFoler;

   @UiField
   MenuItem uploadUntyped;

   @UiField
   MenuItem uploadFile;

   @UiField
   MenuItem uploadDocument;

   @UiField
   MenuItem uploadFoler;

   @UiField
   MenuItem importUntyped;

   @UiField
   MenuItem importFile;

   @UiField
   MenuItem importDocument;

   @UiField
   MenuItem importFoler;

   public ResourcesMenuBar()
   {
      super.initWidget(uiBinder.createAndBindUi(this));

      this.setUpMenuImportIcons();
      this.setUpMenuCreate();
      this.setUpMenuUploadIcons();

      importFile.setEnabled(false);
      uploadFoler.setEnabled(false);
      createDocument.setEnabled(false);
   }

   private void setUpMenuCreate()
   {
      this.createDocument.setIcon(icons.document());
      this.createFile.setIcon(icons.file());
      this.createFoler.setIcon(icons.folder());
      this.createUntyped.setIcon(icons.none());
   }

   private void setUpMenuUploadIcons()
   {
      this.uploadDocument.setIcon(icons.document());
      this.uploadFile.setIcon(icons.file());
      this.uploadFoler.setIcon(icons.folder());
      this.uploadUntyped.setIcon(icons.none());

   }

   private void setUpMenuImportIcons()
   {
      this.importDocument.setIcon(icons.document());
      this.importFile.setIcon(icons.file());
      this.importFoler.setIcon(icons.folder());
      this.importUntyped.setIcon(icons.none());
   }

   /* ************* Create access methods  **************** */
   public HasSelectionHandlers<Item> getCreateUntyped()
   {
      return createUntyped;
   }

   public HasSelectionHandlers<Item> getCreateFile()
   {
      return createFile;
   }

   public HasSelectionHandlers<Item> getCreateDocument()
   {
      return createDocument;
   }

   public HasSelectionHandlers<Item> getCreateFoler()
   {
      return createFoler;
   }

   /* ************* Upload access methods  **************** */
   public HasSelectionHandlers<Item> getUploadUntyped()
   {
      return uploadUntyped;
   }

   public HasSelectionHandlers<Item> getUploadFile()
   {
      return uploadFile;
   }

   public HasSelectionHandlers<Item> getUploadDocument()
   {
      return uploadDocument;
   }

   public HasSelectionHandlers<Item> getUploadFoler()
   {
      return uploadFoler;
   }

   /* ************* Import access methods  **************** */
   public HasSelectionHandlers<Item> getImportUntyped()
   {
      return importUntyped;
   }

   public HasSelectionHandlers<Item> getImportFile()
   {
      return importFile;
   }

   public HasSelectionHandlers<Item> getImportDocument()
   {
      return importDocument;
   }

   public HasSelectionHandlers<Item> getImportFoler()
   {
      return importFoler;
   }

   public void configure(HandlerConfigurer configurer)
   {
      configurer.configureCreateFileHandler(createFile);
      configurer.configureCreateFolderHandler(createFoler);
      configurer.configureCreateUntypedHandler(createUntyped);

      configurer.configureUploadFileHandler(uploadFile ,false);
      configurer.configureUploadDocumentHandler(uploadDocument , false);
      configurer.configureUploadUntypedHandler(uploadUntyped , false);

      configurer.configureImportDocument(importDocument);
      configurer.configureImportFolder(importFoler);
      configurer.configureImportUntyped(importUntyped);
   }

}
