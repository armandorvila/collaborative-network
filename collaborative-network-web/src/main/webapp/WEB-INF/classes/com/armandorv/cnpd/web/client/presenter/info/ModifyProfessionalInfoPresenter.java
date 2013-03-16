package com.armandorv.cnpd.web.client.presenter.info;

import java.util.ArrayList;
import java.util.List;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.client.view.info.ModifyProfessionalInfoView;
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

public class ModifyProfessionalInfoPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setJobs(List<IdNameGenericInfo> jobs);

      List<IdNameGenericInfo> getJobs();

      HasSelectHandlers getSaveButton();

      HasSelectionHandlers<Item> getDelete();

      IdNameGenericInfo getSelectedOfLeft();

      IdNameGenericInfo getSelectedOfRigth();

      void showMessage(String string);

      void deleteOfLeft(IdNameGenericInfo selected);

      void deleteOfRigth(IdNameGenericInfo selected);
   }

   private Display display = new ModifyProfessionalInfoView();

   private Caller<UsersService> usersService;
   
   private InfoPresenter.Display infoPanel;

   private UserInfo user;

   public ModifyProfessionalInfoPresenter(Caller<UsersService> usersService, UserInfo user , InfoPresenter.Display infoPanel)
   {
      this.infoPanel = infoPanel;
      this.user = user;
      this.usersService = usersService;

      display.setJobs(user.getJobs());
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
               display.deleteOfLeft(leftSelected);
            display.deleteOfRigth(rigthSelected);

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
            List<String> jobs = new ArrayList<String>();

            for (IdNameGenericInfo degree : display.getJobs())
               jobs.add(degree.getName());

            usersService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Changes saved.", "We have techincal problems, come back later.")
                  .message(response);
                  
                  if(response){
                     user.setJobs(display.getJobs());
                     infoPanel.renderProfessionalInfo(user);
                  }

                  display.asWindow().hide();
               }
            }).setUserJobs(user.getId(), jobs);
         }
      };
   }

   @Override
   public void present()
   {
      display.asWindow().show();
   }

}