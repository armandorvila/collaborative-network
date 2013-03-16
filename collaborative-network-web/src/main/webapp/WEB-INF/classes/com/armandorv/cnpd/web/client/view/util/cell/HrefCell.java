package com.armandorv.cnpd.web.client.view.util.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Implementation of Cell<String> that wrap the value into a HTML link.
 * 
 * @author armandorv
 *
 */
public class HrefCell extends AbstractCell<String>
{
   
   @Override
   public void render(Context context, String value, SafeHtmlBuilder htmlBuilder)
   {
      htmlBuilder.appendHtmlConstant("<a href='" + value+ "'>" + value + "</a>");
   }

}