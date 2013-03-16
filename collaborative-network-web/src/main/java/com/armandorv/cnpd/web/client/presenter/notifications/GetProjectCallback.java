package com.armandorv.cnpd.web.client.presenter.notifications;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.shared.model.ProjectInfo;

public class GetProjectCallback implements RemoteCallback<ProjectInfo>
{
   private NotificationPresenter presenter;

   public GetProjectCallback(NotificationPresenter presenter)
   {
      this.presenter = presenter;
   }

   @Override
   public void callback(ProjectInfo response)
   {
      presenter.setNotificationObjectName(response.getTitle());
      presenter.present();
   }

}