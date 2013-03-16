package com.armandorv.cnpd.web.client.view.util;

import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

/**
 * Extends ConfirmMessageBox to execute an anonymous construction given as parameter
 * when Yes button is pressed.
 * 
 * @author armandorv
 *
 */
public class ConfirmationView extends ConfirmMessageBox implements HideHandler
{
   /**
    * Common interface to execute code on confirmation event. 
    * 
    * @author armandorv
    *
    */
   public interface Action
   {
      public void execute();
   }
   
   private Action executable;

   public ConfirmationView(String message, ConfirmationView.Action executable)
   {
      super("Confirmation", message);
      super.addHideHandler(this);
      
      this.executable = executable;
   }

   @Override
   public void onHide(HideEvent event)
   {
      if ("Yes".equals(getHideButton().getText()))
      {
         executable.execute();
      }
   }
}
