package com.armandorv.cnpd.web.client.view.util.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class IconCell extends AbstractCell<String>
{
   private String icon;

   public IconCell(ImageResource icon)
   {
      this.icon = AbstractImagePrototype.create(icon).getHTML();
   }

   @Override
   public void render(Context context, String value, SafeHtmlBuilder sb)
   {
      sb.appendHtmlConstant(icon);
   }
   
   public String getIcon(){
      return icon;
   }

}
