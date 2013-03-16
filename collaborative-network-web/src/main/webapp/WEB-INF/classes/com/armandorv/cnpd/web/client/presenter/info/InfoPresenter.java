package com.armandorv.cnpd.web.client.presenter.info;

import javax.inject.Inject;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.view.util.ConfirmationView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Presenter for info panel widget, it depends of MainPresenter.
 * 
 * @author armandorv
 * 
 */
public class InfoPresenter implements Presenter
{
   public interface Executable
   {
      public void execute();
   }

   public interface Display
   {
      Widget asWidget();

      void renderProfessionalInfo(UserInfo user);

      void renderAcademicInfo(UserInfo user);

      void renderPersonalInfo(UserInfo user);

      void setModifyPersonalInfo(Executable modifyPersonalInfo);

      void setModifyAcademicInfo(Executable modifyAcademicInfo);

      void setModifyProfessionalInfo(Executable modifyProfessionalInfo);

      void setModifyAccount(Executable modifyAccount);

      void setDeleteAccount(Executable deleteAccount);
   }

   private UserInfo user;

   @Inject
   private Display display;

   @Inject
   private Caller<UsersService> usersService;

   @AfterInitialization
   public void initiallize()
   {
      display.setModifyPersonalInfo(this.modifyPersonalInfo());
      display.setModifyAcademicInfo(this.modifyAcademicInformation());
      display.setModifyProfessionalInfo(this.modifyProfessionalInfo());
      display.setModifyAccount(this.modifyAccount());
      display.setDeleteAccount(this.deleteAccount());
   }

   @Override
   public void present()
   {
      if (user == null)
      {
         throw new ClientsideException("A Info presenter need a user preset.");
      }

      display.renderPersonalInfo(user);
      display.renderAcademicInfo(user);
      display.renderProfessionalInfo(user);
   }

   private Executable modifyAccount()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ModifyAccountInfoPresenter(usersService, user).present();
         }
      };
   }

   private Executable modifyProfessionalInfo()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ModifyProfessionalInfoPresenter(usersService, user, display).present();
         }
      };
   }

   private Executable modifyAcademicInformation()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ModifyAcademicInfoPresenter(usersService, user, display).present();
         }
      };
   }

   private Executable modifyPersonalInfo()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {

            new ModifyPersonalInfoPresenter(usersService, user, display).present();
         }
      };
   }

   private Executable deleteAccount()
   {
      return new Executable()
      {
         @Override
         public void execute()
         {
            new ConfirmationView("Your account will be deleted from the system, are you sure ?",
                  new ConfirmationView.Action()
                  {
                     @Override
                     public void execute()
                     {
                        usersService.call(new RemoteCallback<Boolean>()
                        {
                           @Override
                           public void callback(Boolean response)
                           {
                              if (response)
                              {
                                 Window.Location.assign(GWT.getHostPageBaseURL());
                              }
                              else
                              {
                                 Info.display("Error", "Error deleting your profile.");
                              }
                           }

                        }).deleteUser(user.getId());
                     }
                  }).show();
         }
      };
   }

   public void setUser(UserInfo user)
   {
      this.user = user;
   }

}