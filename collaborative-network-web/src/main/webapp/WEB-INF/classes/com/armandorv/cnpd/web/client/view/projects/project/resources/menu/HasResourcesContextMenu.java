package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.util.HandlerConfigurer;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public abstract class HasResourcesContextMenu extends Composite
{
   private ResourcesContextMenu noEditableMenu = new ResourcesContextMenu();

   private ShowableResourcesContextMenu showableMenu = new ShowableResourcesContextMenu();

   private EditableResourcesContextMenu editableMenu = new EditableResourcesContextMenu();

   private MarkerContextMenu markerMenu = new MarkerContextMenu();

   private FolderContextMenu folderContextMenu = new FolderContextMenu();

   private Menu emptyMenu = new Menu();

   public HasResourcesContextMenu()
   {
      MenuItem item = new MenuItem("No actions");
      item.setEnabled(false);
      emptyMenu.add(item);
   }

   public ResourcesContextMenu getNoEditableContextMenu()
   {
      return noEditableMenu;
   }

   public EditableResourcesContextMenu getEditableResourcesContextMenu()
   {
      return editableMenu;
   }

   public ShowableResourcesContextMenu getShowableResourcesContextMenu()
   {
      return showableMenu;
   }

   public MarkerContextMenu getMarkerContextMenu()
   {
      return markerMenu;
   }

   public Menu getEmptyMenu()
   {
      return emptyMenu;
   }

   public Menu getFolderContextMenu()
   {
      return folderContextMenu;
   }

   public void configureFolderMenu(HandlerConfigurer configurer)
   {
      configurer.configureDeleteHandler(folderContextMenu.getDelete());
      configurer.configureRenameHandler(folderContextMenu.getRename());
      configurer.configureDownloadHandler(folderContextMenu.getDownlod());
   }

   public void configureNoEditableMenu(HandlerConfigurer configurer)
   {
      configureCommons(configurer, noEditableMenu);
      configurer.configureUploadUntypedHandler(noEditableMenu.getUpload(), true);
   }

   public void configureEditableMenu(HandlerConfigurer configurer)
   {
      configureCommons(configurer, editableMenu);
      configurer.configureEditHandler(editableMenu.getEdit());
      configurer.configureUploadFileHandler(editableMenu.getUpload(), true);
   }

   public void configureMarkerMenu(HandlerConfigurer configurer)
   {
      configurer.configureEditHandler(markerMenu.getEdit());
      configurer.configureDownloadHandler(markerMenu.getDownlod());
      configurer.configureRenameHandler(markerMenu.getRename());
      configurer.configureUploadFileHandler(markerMenu.getUpload(), true);
   }

   public void configureShowableMenu(HandlerConfigurer configurer)
   {
      configureCommons(configurer, showableMenu);
      configurer.configureUploadDocumentHandler(showableMenu.getUpload(), true);
      configurer.configureShowHandler(showableMenu.getShow());
   }

   private void configureCommons(HandlerConfigurer configurer, ResourcesContextMenu menu)
   {
      configurer.configureDeleteHandler(menu.getDelete());
      configurer.configureDownloadHandler(menu.getDownlod());
      configurer.configureRenameHandler(menu.getRename());
   }

}