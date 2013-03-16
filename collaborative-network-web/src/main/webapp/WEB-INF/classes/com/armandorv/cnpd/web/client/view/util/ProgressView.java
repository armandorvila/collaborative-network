package com.armandorv.cnpd.web.client.view.util;

import com.google.gwt.user.client.Timer;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;

/**
 * Show a progress message box and start a timer.
 * 
 * @author armandorv
 *
 */
public class ProgressView extends AutoProgressMessageBox
{
   /**
    * Milliseconds to run timer.
    */
   private int milliseconds;

   /**
    * Create a instance of ProgressView.
    * 
    * @param text text of the box.
    * @param progressText text of the progress bar.
    * @param miliseconds milliseconds for timer.
    */
   public ProgressView(String text, String progressText, int milliseconds)
   {
      super("Progress", text);
      super.setProgressText(progressText);
      super.auto();

      this.milliseconds = milliseconds;
   }

   public void start()
   {
      show();

      Timer timer = new Timer()
      {
         @Override
         public void run()
         {
            hide();
         }
      };
      timer.schedule(milliseconds);
   }

}