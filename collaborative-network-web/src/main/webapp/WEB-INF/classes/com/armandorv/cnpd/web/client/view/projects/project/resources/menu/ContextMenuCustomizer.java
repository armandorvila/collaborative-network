package com.armandorv.cnpd.web.client.view.projects.project.resources.menu;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;

/**
 * Customize resources context menu in runtime.
 * 
 * @author armandorv
 * 
 */
public class ContextMenuCustomizer implements BeforeShowContextMenuHandler
{

   public interface ContextMenuCustomized
   {
      public ResourceInfo getSelected();

      public void enableEditableResourceMenu();

      public void enableShowableResourceMenu();

      public void enableNoEditableResourceMenu();
      
      public void enableMarkerContextMenu();
      
      public void enableFolderContextMenu();

      public void disableContextMenu();
   }

   private ContextMenuCustomized customized;

   public ContextMenuCustomizer(ContextMenuCustomized customized)
   {
      this.customized = customized;
   }

   /**
    * Check the kind of selected resource and set the contextual menu.
    */
   @Override
   public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event)
   {
      ResourceInfo selected = customized.getSelected();

      if (selected.getKind().equals(Kind.ROOT))
         customized.disableContextMenu();

      if (selected.getKind().equals(Kind.FOLDER))
         customized.enableFolderContextMenu();
      

      if (selected.getKind().equals(Kind.DOCUMENT))
         customized.enableShowableResourceMenu();

      if (selected.getKind().equals(Kind.FILE))
         customized.enableEditableResourceMenu();

      if (selected.getKind().equals(Kind.MARKER))
         customized.enableMarkerContextMenu();

      if (selected.getKind().equals(Kind.MEDIA))
         customized.enableNoEditableResourceMenu();

      if (selected.getKind().equals(Kind.NONE))
         customized.enableNoEditableResourceMenu();
   }

}