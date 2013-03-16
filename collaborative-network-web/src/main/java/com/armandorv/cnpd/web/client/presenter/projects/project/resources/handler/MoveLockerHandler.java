package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent.DndDragStartHandler;

/**
 * Handler to avoid empty elements, empty elements are absolutely useless so we have to avoid it.
 * If you try to move a marker or a root resource this handler lock the event.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class MoveLockerHandler implements DndDragStartHandler
{
   @Inject
   private ResourcesPresenter.Display display;

   @Override
   public void onDragStart(DndDragStartEvent event)
   {
      ResourceInfo freeSel = display.getSelected(true);
      ResourceInfo proSel = display.getSelected(false);

      if (freeSel != null)
         check(event, freeSel);

      if (proSel != null)
         check(event, proSel);
   }

   /**
    * Lock the event if resource is ROOT or MARKER.
    */
   private void check(DndDragStartEvent event, ResourceInfo sel)
   {
      if (sel.getKind().equals(Kind.ROOT))
         lock(event);

      else if (sel.getKind().equals(Kind.MARKER))
         lock(event);
   }

   /**
    * Lock a DndDragStartEvent event.
    */
   private void lock(DndDragStartEvent event)
   {
      event.setCancelled(true);
      event.getStatusProxy().setStatus(false);
   }

}
