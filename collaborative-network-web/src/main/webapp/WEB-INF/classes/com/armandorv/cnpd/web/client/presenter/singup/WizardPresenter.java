package com.armandorv.cnpd.web.client.presenter.singup;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Base class for all presenters of a chain of presenter for a wizard.
 * 
 * @author armandorv
 * 
 */
public abstract class WizardPresenter
{
   private static final String cancelPage = "login.jsp";

   public interface Display
   {
      Widget asWidget();

      HasSelectHandlers getContinue();

      HasSelectHandlers getCancel();

      HasSelectHandlers getBack();

      void addErrorMessage(String string);
   }

   private RootLayoutPanel container;

   private Presenter previousPresenter;

   private Presenter nextPresenter;

   private Display display;

   protected void setUpPresenter(RootLayoutPanel container , Presenter previousPresenter, Presenter nextPresenter,
         Display display)
   {
      this.container = container;
      this.previousPresenter = previousPresenter;
      this.nextPresenter = nextPresenter;
      this.display = display;
   }

   protected SelectHandler cancel()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            doExit();
         }
      };
   }

   protected SelectHandler back()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            container.remove(display.asWidget());
            previousPresenter.present();
         }
      };
   }

   protected void doContinue()
   {
      container.remove(display.asWidget());
      nextPresenter.present();
   }

   protected void doExit()
   {
      container.remove(display.asWidget());
      Window.Location.assign(GWT.getHostPageBaseURL() + cancelPage);
   }

}