package com.armandorv.cnpd.web.client.view.singup;

import java.util.Date;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

@Dependent
public class PersonalFormView extends Composite implements PersonalFormPresenter.Display
{
   public interface PersonalFormViewUiBinder extends UiBinder<Widget, PersonalFormView>
   {
   }

   private static PersonalFormViewUiBinder uiBinder = GWT.create(PersonalFormViewUiBinder.class);

   @UiField
   TextField name;

   @UiField
   TextField firstLastname;

   @UiField
   TextField secondLastname;

   @UiField
   TextField city;

   @UiField
   TextField website;

   @UiField
   DateField birthday;

   @UiField
   TextButton continueButton;

   @UiField
   TextButton backButton;

   @UiField
   TextButton cancelButton;

   public PersonalFormView()
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
   public HasSelectHandlers getBack()
   {
      return backButton;
   }

   @Override
   public Date getBirthday()
   {
      return birthday.getValue();
   }

   @Override
   public String getName()
   {
      return this.name.getText();
   }

   @Override
   public String getLastname1()
   {
      return this.firstLastname.getText();
   }

   @Override
   public String getLastname2()
   {
      return secondLastname.getText();
   }

   @Override
   public String getCity()
   {
      return city.getText();
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Validation Fail", string);
   }

   @Override
   public String getWebSite()
   {
      return this.website.getText();
   }

}