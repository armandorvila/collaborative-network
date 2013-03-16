package com.armandorv.cnpd.web.client.presenter.info;

import java.util.Date;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.info.ModifyPersonalInfoView;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class ModifyPersonalInfoPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setName(String name);

      void setLastname1(String lastname1);

      void setLastname2(String lastname2);

      void setBirthday(Date birthday);
      
      void setCity(String city);

      void setWeb(String webSite);

      HasSelectHandlers getSave();

      String getName();

      String getLastname1();

      String getLastname2();

      Date getBirthday();
      
      String getCity();

      void addMessage(String string);
   }

   private Display display = new ModifyPersonalInfoView();

   private Caller<UsersService> usersService;

   private InfoPresenter.Display infoPanel;

   private UserInfo user;

   public ModifyPersonalInfoPresenter(Caller<UsersService> usersService, UserInfo user, InfoPresenter.Display infoPanel)
   {
      this.usersService = usersService;
      this.user = user;
      this.infoPanel = infoPanel;

      display.getSave().addSelectHandler(this.save());
   }

   private SelectHandler save()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {       
            user.setId(user.getId());
            user.setName(display.getName());
            user.setLastname1(display.getLastname1());
            user.setLastname2(display.getLastname2());
            user.setBirthday(display.getBirthday());
            user.setCity(display.getCity());
     
            usersService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Personal info modified.", "Error modifing personal info.").message(
                        response);

                  if (response)
                     infoPanel.renderPersonalInfo(user);
                  
                  display.asWindow().hide();
               }
            }).setUserInformation(user);
         }
      };
   }

   @Override
   public void present()
   {
      display.setName(user.getName());
      display.setLastname1(user.getLastname1());
      display.setLastname2(user.getLastname2());
      display.setBirthday(user.getBirthday());
      display.setWeb(user.getWebSite());
      display.setCity(user.getCity());

      display.asWindow().show();
   }

}