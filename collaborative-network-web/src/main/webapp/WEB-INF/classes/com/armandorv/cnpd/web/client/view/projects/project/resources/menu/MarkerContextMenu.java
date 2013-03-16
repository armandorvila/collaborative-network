package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

/**
 * Context menu for marker files.
 * 
 * @author armandorv
 *
 */
public class MarkerContextMenu extends EditableResourcesContextMenu
{
   public MarkerContextMenu()
   {
      super();
      delete.setEnabled(false);
      upload.setEnabled(false);
   }
}
