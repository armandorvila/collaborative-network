package com.armandorv.cnpd.web.client.presenter.info;

import java.util.ArrayList;
import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.info.ModifyAcademicInfoView;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

public class ModifyAcademicInfoPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setDegrees(List<IdNameGenericInfo> degrees);

      List<IdNameGenericInfo> getDegrees();

      HasSelectHandlers getSaveButton();

      HasSelectionHandlers<Item> getDelete();

      IdNameGenericInfo getSelectedOfLeft();

      IdNameGenericInfo getSelectedOfRigth();

      void showMessage(String string);

      void deleteOfLeft(IdNameGenericInfo selected);

      void deleteOfRigth(IdNameGenericInfo selected);
   }

   private Caller<UsersService> usersService;

   private Display display = new ModifyAcademicInfoView();

   private UserInfo user;

   private InfoPresenter.Display infoPanel;

   public ModifyAcademicInfoPresenter(Caller<UsersService> usersService, UserInfo user, InfoPresenter.Display infoPanel)
   {
      this.user = user;
      this.usersService = usersService;
      this.infoPanel = infoPanel;

      display.setDegrees(user.getDegrees());
      display.getSaveButton().addSelectHandler(this.save());
      display.getDelete().addSelectionHandler(this.delete());
   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            IdNameGenericInfo leftSelected = display.getSelectedOfLeft();
            IdNameGenericInfo rigthSelected = display.getSelectedOfRigth();

            if (leftSelected == null && rigthSelected == null)
               display.showMessage("Invalid selection.");

            if (leftSelected != null && rigthSelected != null)
            {
               display.deleteOfLeft(leftSelected);
               display.deleteOfRigth(rigthSelected);
            }

            if (leftSelected != null && rigthSelected == null)
               display.deleteOfLeft(leftSelected);

            if (leftSelected == null && rigthSelected != null)
               display.deleteOfRigth(rigthSelected);
         }
      };
   }

   private SelectHandler save()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            List<String> degrees = new ArrayList<String>();

            for (IdNameGenericInfo degree : display.getDegrees())
               degrees.add(degree.getName());

            usersService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Changes saved.", "We have techincal problems, come back later.")
                        .message(response);

                  if (response)
                  {
                     user.setDegrees(display.getDegrees());
                     infoPanel.renderAcademicInfo(user);
                  }

                  display.asWindow().hide();
               }
            }).setUserDegrees(user.getId(), degrees);
         }
      };
   }

   @Override
   public void present()
   {
      display.asWindow().show();
   }

}