package com.armandorv.cnpd.web.client.view.util.tooltip;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig;
import com.sencha.gxt.widget.core.client.tips.ToolTipConfig.ToolTipRenderer;

/**
 * Class to provide Custom tool tip for rows of a grid.
 * 
 * @author armandorv
 *
 */
public abstract class RowToolTipConfig<T> extends ToolTipConfig implements ToolTipRenderer<T>, MouseOverHandler
{
   /**
    * Must be implemented by widget that own the grid.
    * 
    * @author armandorv
    *
    */
   public interface HasRowToolTip<T>
   {
      /**
       * @return store.get(contactsView.findRowIndex(eventTarget))
       */
      ContactInfo getMouseOvered(Element eventTarget);

      /**
       * @return Grid to add on mouse over handler.
       */
      Grid<T> getHasHandlers();
   }

   private HasRowToolTip<T> toolTipped;

   public RowToolTipConfig(HasRowToolTip<T> toolTipped)
   {
      this.toolTipped = toolTipped;
      this.toolTipped.getHasHandlers().addHandler(this, MouseOverEvent.getType());

      super.setRenderer(this);
   }
   
   @Override
   public void onMouseOver(MouseOverEvent event)
   {
      setData(toolTipped.getMouseOvered(Element.as(event.getNativeEvent().getEventTarget())));
   }

   @Override
   public abstract SafeHtml renderToolTip(T data);

}