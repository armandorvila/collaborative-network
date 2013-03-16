package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * Contextual menu for both menu resources areas.
 * This class extends Resources context menu and add it an item for resource edition.
 * 
 * @author armandorv
 * 
 */
public class EditableResourcesContextMenu extends ResourcesContextMenu
{

   protected MenuItem edit = new MenuItem("Edit");

   public EditableResourcesContextMenu()
   {
      super();
      this.addItems();
   }

   private void addItems()
   {
      this.edit.setIcon(icons.show16());
      this.add(edit);
   }

   public HasSelectionHandlers<Item> getEdit()
   {
      return edit;
   }

}