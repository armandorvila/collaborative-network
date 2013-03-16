package com.armandorv.cnpd.web.client.presenter.singup;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for registration form widget.
 * 
 * @author armandorv
 * 
 */

@Singleton
public class GoogleFormPresenter extends WizardPresenter implements Presenter
{
   public interface Display extends WizardPresenter.Display
   {
      String getGoogleAccount();

      String getGooglePassword();

      String getGoogleConfirmPassword();
   }

   private boolean loaded = false;

   @Inject
   private Display display;

   @Inject
   private PersonalFormPresenter nextPresenter;

   @Inject
   private Caller<SingupService> signupService;
   
   @Inject  
   private RootLayoutPanel container;

   public void present()
   {
      if (!loaded)
      {
         /* Mandatory call*/
         super.setUpPresenter(container, null, nextPresenter, display);

         this.display.getCancel().addSelectHandler(this.cancel());
         this.display.getContinue().addSelectHandler(this.continueWizard());

         loaded = true;
      }
      container.add(display.asWidget());
   }

   private SelectHandler continueWizard()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            if (clientValidation())
               serverValidation();
         }
      };
   }

   /**
    * Call saveAndVallidateGoogleAccount and go ahead if true.
    */
   public void serverValidation()
   {
      this.signupService.call(new RemoteCallback<ValidationResponse>()
      {
         @Override
         public void callback(ValidationResponse response)
         {
            if (response.isPositive())
               doContinue();

            else
               display.addErrorMessage("Invalid Google Credentials:" + response.getMessage());
         }

      }).saveAndValidateGoogleAccount(display.getGoogleAccount(), display.getGooglePassword());

   }

   public boolean clientValidation()
   {
      if ("".equals(display.getGoogleAccount()))
      {
         display.addErrorMessage("Invalid Username.");
         return false;
      }
      
      if (!display.getGoogleAccount().contains("@gmail.com"))
      {
         display.addErrorMessage("Invalid Username.");
         return false;
      }
      
      if ("".equals(display.getGooglePassword()))
      {
         display.addErrorMessage("Invalid Password.");
         return false;
      }

      if ("".equals(display.getGoogleConfirmPassword()))
      {
         display.addErrorMessage("Invalid Confirmation Password.");
         return false;
      }

      if (!display.getGooglePassword().equals(display.getGoogleConfirmPassword()))
      {
         display.addErrorMessage("Passwords must be equals.");
         return false;
      }

      return true;
   }

}