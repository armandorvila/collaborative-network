package com.armandorv.cnpd.web.client.view.notifications;

import com.armandorv.cnpd.web.client.presenter.notifications.NotificationPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 * View that show a notification.
 * 
 * @author armandorv
 * 
 */
public class NotificationView implements NotificationPresenter.Display
{
   public interface NotificationViewUiBinder extends UiBinder<Widget, NotificationView>
   {
   }

   private NotificationViewUiBinder uiBinder = GWT.create(NotificationViewUiBinder.class);

   @UiField
   TextArea notificationText;

   @UiField
   TextArea specificText;

   @UiField
   FieldLabel specificLabel;

   @UiField
   Window notification;

   @UiField
   TextButton acceptButton;

   @UiField
   TextButton cancelButton;

   public NotificationView()
   {
      this.uiBinder.createAndBindUi(this);
   }

   @Override
   public Window asWindow()
   {
      return notification;
   }

   @Override
   public HasSelectHandlers getAccept()
   {
      return acceptButton;
   }

   @Override
   public HasSelectHandlers getCancel()
   {
      return cancelButton;
   }

   @Override
   public void setNotificationText(String text)
   {
      notificationText.setText(text);
   }

   @Override
   public void setNotificationObject(String text)
   {
      this.specificText.setText(text);
   }

   @Override
   public void setSpecificLabel(String specificLabel)
   {
      this.specificLabel.setText(specificLabel);
   }

}