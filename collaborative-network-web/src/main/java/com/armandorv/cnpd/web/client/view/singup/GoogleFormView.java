package com.armandorv.cnpd.web.client.view.singup;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Form of Sign Up Wizard to add contacts from google contacts.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class GoogleFormView extends Composite implements GoogleFormPresenter.Display
{
   public interface GoogleFormViewUiBinder extends UiBinder<Widget, GoogleFormView>
   {
   }

   private static GoogleFormViewUiBinder uiBinder = GWT.create(GoogleFormViewUiBinder.class);

   @UiField
   TextField googleAccount;

   @UiField
   PasswordField googlePassword;

   @UiField
   PasswordField googlePasswordConfirmation;

   @UiField
   TextButton continueButton;

   @UiField
   TextButton cancelButton;

   public GoogleFormView()
   {
      super.initWidget(new WizardView(uiBinder.createAndBindUi(this)));
   }

   @Override
   public HasSelectHandlers getContinue()
   {
      return continueButton;
   }

   @Override
   public HasSelectHandlers getCancel()
   {
      return cancelButton;
   }

   @Override
   public String getGoogleAccount()
   {
      return this.googleAccount.getText();
   }

   @Override
   public String getGooglePassword()
   {
      return this.googlePassword.getText();
   }

   @Override
   public String getGoogleConfirmPassword()
   {
      return this.googlePasswordConfirmation.getText();
   }

   @Override
   public void addErrorMessage(String text)
   {
      Info.display("Validation Fail", text);
   }

   @Override
   @Deprecated
   public HasSelectHandlers getBack()
   {
      return null;
   }

}