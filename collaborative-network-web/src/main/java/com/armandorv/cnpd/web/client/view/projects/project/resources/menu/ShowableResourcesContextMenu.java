package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * Add a menu item for show resources.
 * 
 * @author armandorv
 * 
 */
public class ShowableResourcesContextMenu extends ResourcesContextMenu
{

   protected MenuItem show = new MenuItem("Show");

   public ShowableResourcesContextMenu()
   {
      super();
      this.addItems();
   }

   private void addItems()
   {
      this.show.setIcon(icons.show16());
      this.add(show);
   }

   public HasSelectionHandlers<Item> getShow()
   {
      return show;
   }

}
