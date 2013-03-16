package com.armandorv.cnpd.web.client.view.info;

import com.armandorv.cnpd.web.client.presenter.info.ModifyAccountInfoPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ModifyAccountInfoView implements ModifyAccountInfoPresenter.Display
{
   public interface ModifyAccountViewUiBinder extends UiBinder<Widget ,ModifyAccountInfoView> {}
   
   private ModifyAccountViewUiBinder uiBinder = GWT.create(ModifyAccountViewUiBinder.class);
   
   @UiField
   Window window;
   
   @UiField
   TextField username;
   
   @UiField
   PasswordField currentPassword;
   
   @UiField
   PasswordField newPassword;
   
   @UiField
   PasswordField newPasswordConfirmation;
   
   @UiField
   TextButton saveButton;
   
   public ModifyAccountInfoView(){
      uiBinder.createAndBindUi(this);
   }
   
   @UiHandler("cancelButton")
   public void cancel(SelectEvent event)
   {
      window.hide();
   }

   @Override
   public HasSelectHandlers getSave()
   {
      return saveButton;
   }
   
   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void setUsername(String username)
   {
      this.username.setText(username);
   }

   @Override
   public String getUsername()
   {
      return username.getText();
   }

   @Override
   public String getCurrentPassword()
   {
      return currentPassword.getText();
   }

   @Override
   public String getNewPassword()
   {
      return newPassword.getText();
   }

   @Override
   public String getNewPasswordConfirmation()
   {
      return newPasswordConfirmation.getText();
   }

}