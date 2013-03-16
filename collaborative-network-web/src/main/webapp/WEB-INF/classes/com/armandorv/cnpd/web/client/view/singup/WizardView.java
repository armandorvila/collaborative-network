package com.armandorv.cnpd.web.client.view.singup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Base class for all widget of Sing up wizard.
 * 
 * @author armandorv
 * 
 */
public class WizardView extends Composite
{

   public interface WizardViewUiBinder extends UiBinder<Widget, WizardView>
   {
   }

   private static WizardViewUiBinder uiBinder = GWT.create(WizardViewUiBinder.class);

   @UiField
   ContentPanel form;

   public WizardView(Widget formContent)
   {
      Viewport viewPort = new Viewport();
      viewPort.add(uiBinder.createAndBindUi(this));
      form.add(formContent);

      super.initWidget(viewPort);
   }

}