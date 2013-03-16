package com.armandorv.cnpd.web.client.presenter.singup;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Presenter for personal info form of sign up wizard.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class PersonalFormPresenter extends WizardPresenter implements Presenter
{
   public interface Display extends WizardPresenter.Display
   {
      String getName();

      String getLastname1();

      String getLastname2();

      String getCity();

      Date getBirthday();

      String getWebSite();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<SingupService> singupService;

   @Inject
   private GoogleFormPresenter previousPresenter;

   @Inject
   private AcademicFormPresenter nextPresenter;
   
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
            if (clientValidation())
               saveToServer();
         }
      };
   }

   public void saveToServer()
   {
      UserInfo userInfo = new UserInfo();
      userInfo.setName(display.getName());
      userInfo.setLastname1(display.getLastname1());
      userInfo.setLastname2(display.getLastname2());
      userInfo.setWebSite(display.getWebSite());
      userInfo.setBirthday(display.getBirthday());
      userInfo.setCity(display.getCity());

      this.singupService.call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            if (response)
               doContinue();
         }

      }).savePersonalData(userInfo);
   }

   public boolean clientValidation()
   {
      if ("".equals(display.getName()))
      {
         display.addErrorMessage("Invalid Name.");
         return false;
      }
      if ("".equals(display.getLastname1()))
      {
         display.addErrorMessage("Invalid First lastname.");
         return false;
      }
      if ("".equals(display.getLastname2()))
      {
         display.addErrorMessage("Invalid Second lastname.");
         return false;
      }

      if (display.getBirthday() == null)
      {
         display.addErrorMessage("Invalid Date.");
         return false;
      }

      if ("".equals(display.getCity()))
      {
         display.addErrorMessage("Invalid City.");
         return false;
      }

      if ("".equals(display.getWebSite()))
      {
         display.addErrorMessage("Invalid Web site.");
         return false;
      }

      return true;
   }

}