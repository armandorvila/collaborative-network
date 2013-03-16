package com.armandorv.cnpd.web.client.presenter.notifications;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.shared.model.ContactInfo;

public class GetContactCallback implements RemoteCallback<ContactInfo>
{
   private NotificationPresenter presenter;

   public GetContactCallback(NotificationPresenter presenter)
   {
      this.presenter = presenter;
   }

   @Override
   public void callback(ContactInfo response)
   {
      presenter.setNotificationObjectName(response.getFullName());
      presenter.present();
   }

}
