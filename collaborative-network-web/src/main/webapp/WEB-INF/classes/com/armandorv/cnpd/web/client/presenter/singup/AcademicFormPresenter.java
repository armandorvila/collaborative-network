package com.armandorv.cnpd.web.client.presenter.singup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Singleton
public class AcademicFormPresenter extends WizardPresenter implements Presenter
{
   public interface Display extends WizardPresenter.Display
   {
      List<IdNameGenericInfo> getDegrees();
   }

   @Inject
   private PersonalFormPresenter previousPresenter;

   @Inject
   private ProfessionalFormPresenter nextPresenter;

   @Inject
   private Display display;

   @Inject
   private Caller<SingupService> singUpService;
   
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

         display.getBack().addSelectHandler(super.back());
         display.getCancel().addSelectHandler(super.cancel());
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
            List<String> degrees = new ArrayList<String>();

            for (IdNameGenericInfo degree : display.getDegrees())
               degrees.add(degree.getName());

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
            }).saveAcademicInfo(degrees);
         }
      };
   }

}