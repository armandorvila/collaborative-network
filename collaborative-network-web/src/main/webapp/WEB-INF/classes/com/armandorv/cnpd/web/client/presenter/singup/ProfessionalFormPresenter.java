package com.armandorv.cnpd.web.client.presenter.singup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for Professional View, of the Sign Up Wizard.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ProfessionalFormPresenter extends WizardPresenter implements Presenter
{
   public interface Display extends WizardPresenter.Display
   {
      List<IdNameGenericInfo> getJobs();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<SingupService> singUpService;

   @Inject
   private AcademicFormPresenter previousPresenter;

   @Inject
   private ContactsFormPresenter nextPresenter;
   
   @Inject
   private RootLayoutPanel container;

   private boolean loaded = false;

   @Override
   public void present()
   {
      if (!loaded)
      {
         /* Mandatory call*/
         super.setUpPresenter(container, previousPresenter, nextPresenter, display);

         display.getCancel().addSelectHandler(super.cancel());
         display.getBack().addSelectHandler(super.back());
         display.getContinue().addSelectHandler(this.continueWizard());

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
            List<String> jobs = new ArrayList<String>();

            for (IdNameGenericInfo degree : display.getJobs())
               jobs.add(degree.getName());

            singUpService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                     doContinue();
                  else
                     display.addErrorMessage("We have techincal problems, come back later.");

               }
            }).saveProfessionalInfo(jobs);
         }
      };
   }

}