package com.armandorv.cnpd.web.client.presenter.info;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.info.ModifyAccountInfoView;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class ModifyAccountInfoPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setUsername(String username);

      String getUsername();

      String getCurrentPassword();

      String getNewPassword();

      String getNewPasswordConfirmation();

      HasSelectHandlers getSave();
   }

   private Display display = new ModifyAccountInfoView();

   private Caller<UsersService> usersService;

   private UserInfo user;

   public ModifyAccountInfoPresenter(Caller<UsersService> usersService, UserInfo user)
   {
      this.user = user;
      this.usersService = usersService;

      display.getSave().addSelectHandler(chngeAccount());
      display.setUsername(user.getUsername());
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ClientsideException("User must be set previously.");

      if (usersService == null)
         throw new ClientsideException("usersService must be set previously.");

      display.asWindow().show();
   }

   private SelectHandler chngeAccount()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            if (validateFields())
               callValidate();
         }
      };
   }

   private void callValidate()
   {
      usersService.call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            if (response)
            {
               callCalidateAgainstGoogle();
            }
            else
            {
               Info.display("Error", "Inavlid current password.");
            }
         }

      }).validate(user.getUsername(), display.getCurrentPassword());
   }

   private void callCalidateAgainstGoogle()
   {
      usersService.call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            if (response)
            {
               callSetAccount();
            }
            else
            {
               Info.display("Error", "New account is not a valid google account.");
            }
         }
      }).validateAgainstGoogle(display.getUsername(), display.getNewPassword());
   }

   private void callSetAccount()
   {
      usersService.call(new RemoteCallback<Boolean>()
      {
         @Override
         public void callback(Boolean response)
         {
            BooleanMessenger
                  .getMessenger("Account changed succesfully.",
                        "Error saving new account info.");

            display.asWindow().hide();
         }
      }).setUserAccount(user.getId(), display.getUsername(), display.getNewPassword());
   }

   private boolean validateFields()
   {
      if ("".equals(display.getUsername()))
      {
         Info.display("Error", "Username are required.");
         return false;
      }

      if ("".equals(display.getCurrentPassword()))
      {
         Info.display("Error", "Current password are required.");
         return false;
      }

      if ("".equals(display.getNewPassword()))
      {
         Info.display("Error", "New password are required.");
         return false;
      }

      if ("".equals(display.getNewPasswordConfirmation()))
      {
         Info.display("Error", "New passwrd confirmation are required.");
         return false;
      }

      if (!display.getNewPassword().equals(display.getNewPasswordConfirmation()))
      {
         Info.display("Error", "Incorrect new passwrd confirmation.");
         return false;
      }

      return true;
   }
}