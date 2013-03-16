package com.armandorv.cnpd.web.client.view.util;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

/**
 * View for ask a text value and execute any given action when OK is pressed.
 * 
 * @author armandorv
 *
 */
public class PromptView extends PromptMessageBox implements HideHandler
{
   public interface Action
   {
      public void execute(String value);
   }

   private final String OK = "OK";

   private List<Action> actions = new ArrayList<Action>();

   public PromptView(String title, String message)
   {
      super(title, message);
      super.addHideHandler(this);
   }

   @Override
   public void onHide(HideEvent event)
   {
      if (OK.equals(getHideButton().getText()))
      {
         String value = Format.ellipse(getValue(), 80);

         for (Action action : actions)
         {
            action.execute(value);
         }
      }
   }

   public void addAction(Action action)
   {
      actions.add(action);
   }

}