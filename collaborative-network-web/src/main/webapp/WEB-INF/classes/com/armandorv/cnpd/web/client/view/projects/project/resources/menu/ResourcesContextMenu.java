package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * Contextual menu for both menu resources areas.
 * 
 * @author armandorv
 * 
 */
public class ResourcesContextMenu extends Menu
{
   protected MenuItem delete = new MenuItem("Delete");

   protected MenuItem rename = new MenuItem("Rename");

   protected MenuItem download = new MenuItem("Download");

   protected MenuItem upload = new MenuItem("Upload");

   protected IconsBundle icons = GWT.create(IconsBundle.class);

   public ResourcesContextMenu()
   {
      super();
      this.addItems();
   }

   private void addItems()
   {
      this.add(rename);
      this.add(download);
      this.delete.setIcon(icons.delete16());
      this.add(delete);
      this.add(upload);
   }

   public HasSelectionHandlers<Item> getRename()
   {
      return rename;
   }

   public HasSelectionHandlers<Item> getDownlod()
   {
      return download;
   }

   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   public HasSelectionHandlers<Item> getUpload()
   {
      return upload;
   }

}