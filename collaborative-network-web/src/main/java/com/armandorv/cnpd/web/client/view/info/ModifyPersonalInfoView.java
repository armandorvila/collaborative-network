package com.armandorv.cnpd.web.client.view.info;

import java.util.Date;

import com.armandorv.cnpd.web.client.presenter.info.ModifyPersonalInfoPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class ModifyPersonalInfoView implements ModifyPersonalInfoPresenter.Display
{
   public interface ModifyPersonalInfoViewUiBinder extends UiBinder<Widget, ModifyPersonalInfoView>
   {
   }

   private ModifyPersonalInfoViewUiBinder uiBinder = GWT.create(ModifyPersonalInfoViewUiBinder.class);

   @UiField
   Window window;

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
   TextButton saveButton;

   @UiField
   TextButton cancelButton;

   public ModifyPersonalInfoView()
   {
      uiBinder.createAndBindUi(this);
   }

   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void setName(String name)
   {
      this.name.setText(name);
   }

   @Override
   public void setLastname1(String lastname1)
   {
      this.firstLastname.setText(lastname1);
   }

   @Override
   public void setLastname2(String lastname2)
   {
      this.secondLastname.setText(lastname2);
   }

   @Override
   public void setBirthday(Date birthday)
   {
      this.birthday.setValue(birthday);
   }

   @Override
   public void setWeb(String webSite)
   {
      this.website.setText(webSite);
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
   public Date getBirthday()
   {
      return birthday.getValue();
   }

   @Override
   public void addMessage(String string)
   {
      Info.display("Notificaton.", string);
   }

   @Override
   public void setCity(String city)
   {
      this.city.setText(city);
   }

   @Override
   public String getCity()
   {
      return this.city.getText();
   }

}