package com.armandorv.cnpd.web.client.view.util.builder;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * Class to build GXT Menu instances, offer methods to execute an step by step building process.
 * 
 * @author armandorv
 * 
 */
public class MenuBuilder
{
   private Menu menu = new Menu();

   /**
    * Build a menu item and add it to menu.
    * 
    * @param item item to add.
    * @param icon icon of the item.
    * @param enabled if the item must be enabled.
    * 
    * @return the builder instance to continue the process.
    */
   public MenuBuilder build(MenuItem item, ImageResource icon, boolean enabled)
   {
      item.setEnabled(enabled);

      if (icon != null)
         item.setIcon(icon);

      menu.add(item);

      return this;
   }

   /**
    * Build a menu item and add it to menu.
    * 
    * @param item item to add.
    * @param icon icon of the item.
    * 
    * @return the builder instance to continue the process.
    */
   public MenuBuilder build(MenuItem item, ImageResource icon)
   {
      return build(item, icon, true);
   }

   public MenuBuilder build(MenuItem item)
   {
      return build(item, null);
   }

   /**
    * @return Menu built during the building process.
    */
   public Menu get()
   {
      return menu;
   }

}