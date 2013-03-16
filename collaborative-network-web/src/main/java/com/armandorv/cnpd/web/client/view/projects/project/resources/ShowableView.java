package com.armandorv.cnpd.web.client.view.projects.project.resources;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;

/**
 * View to show resources showable : Documents.
 * 
 * @author armandorv
 *
 */
public class ShowableView
{
   public interface ShowableViewUiBinder extends UiBinder<Widget, ShowableView>
   {
   }

   private static ShowableViewUiBinder uiBinder = GWT.create(ShowableViewUiBinder.class);

   @UiField
   Window window;

   @UiField(provided = true)
   Frame resource = null;

   public ShowableView(String url)
   {
      this.resource = new Frame(url);
      uiBinder.createAndBindUi(this);
   }

   public Window asWindow()
   {
      return window;
   }

}