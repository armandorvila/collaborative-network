package com.armandorv.cnpd.web.client.view.util.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * AbstractCell implementation that render a icon when the cell value is true,
 * and another icon when is false.
 * 
 * @author armandorv
 *
 */
public class BooleanIconCell extends AbstractCell<Boolean>
{
   /**
    * Icon for true cell values.
    */
   private IconCell trueIcon;

   /**
    * Icon for false cell values.
    */
   private IconCell falseIcon;

   /**
    * Create a new immutable cell for 2 given icons.
    * 
    * @param trueIcon icon to be render on true values.
    * @param falseIcon icon to be render on false values.
    */
   public BooleanIconCell(ImageResource trueIcon, ImageResource falseIcon)
   {
      this.trueIcon = new IconCell(trueIcon);
      this.falseIcon = new IconCell(falseIcon);
   }

   /**
    * Create a Boolean cell which the false icon is "".
    */
   public BooleanIconCell(ImageResource trueIcon)
   {
      this.trueIcon = new IconCell(trueIcon);
      this.falseIcon = null;
   }

   @Override
   public void render(Context context, Boolean value, SafeHtmlBuilder sb)
   {
      IconCell icon = value ? trueIcon : falseIcon;
      sb.appendHtmlConstant(icon != null ? icon.getIcon() : "");
   }
}
