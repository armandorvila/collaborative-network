package com.armandorv.cnpd.web.client.presenter.singup;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for ContactsForm of SignUp wizard, give contacts to server and save
 * added.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ContactsFormPresenter extends WizardPresenter implements Presenter
{
   public interface Display extends WizardPresenter.Display
   {
      void setContacts(List<ContactInfo> googleContacts);

      List<ContactInfo> getAddedContacts();

      void addErrorMessage(String string);
   }

   @Inject
   private Display display;

   @Inject
   private Caller<SingupService> singUpService;

   @Inject
   private ProfessionalFormPresenter previousPresenter;
   
   @Inject 
   private RootLayoutPanel container;

   private boolean loaded = false;

   @Override
   public void present()
   {
      if (!loaded)
      {
         /* Mandatory call*/
         super.setUpPresenter(container, previousPresenter, null, display);

         display.getBack().addSelectHandler(this.back());
         display.getCancel().addSelectHandler(this.cancel());
         display.getContinue().addSelectHandler(this.finalizeWizard());

         loaded = true;
      }
      
      this.loadContactsFromGoogle();

      container.add(display.asWidget());
   }

   private void loadContactsFromGoogle()
   {
      singUpService.call(new RemoteCallback<List<ContactInfo>>()
      {
         @Override
         public void callback(List<ContactInfo> response)
         {
            display.setContacts(response);
         }
      }).getHypotheticalContacts();
   }

   private SelectHandler finalizeWizard()
   {
      return new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            singUpService.call(new RemoteCallback<Boolean>()
            {
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     singUpService.call(new RemoteCallback<ValidationResponse>()
                     {
                        @Override
                        public void callback(ValidationResponse response)
                        {
                           if (response.isPositive())
                              doExit();
                           else
                              display.addErrorMessage("We have techincal problems, come back later.");

                        }
                     }).commitUser();
                  }
                  else
                  {
                     display.addErrorMessage("We have techincal problems, come back later.");
                  }
               }

            }).saveContactsInfo(display.getAddedContacts());

         }
      };
   }

}