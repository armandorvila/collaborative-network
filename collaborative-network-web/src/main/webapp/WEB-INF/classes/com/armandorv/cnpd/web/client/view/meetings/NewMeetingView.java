package com.armandorv.cnpd.web.client.view.meetings;

import java.util.Date;

import com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

public class NewMeetingView extends Composite implements NewMeetingPresenter.Display
{
   public interface NewMeetingViewUiBinder extends UiBinder<Widget, NewMeetingView>
   {
   }

   private static NewMeetingViewUiBinder uiBinder = GWT.create(NewMeetingViewUiBinder.class);

   @UiField
   TextField title;
   
   @UiField
   TextField place;

   @UiField
   DateField date;

   @UiField
   TextArea description;

   @UiField
   TextButton create;

   public NewMeetingView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
      date.setValue(new Date());
   }

   @Override
   public void clear()
   {
      title.setText("");
      place.setText("");
      date.setValue(new Date());
      description.setValue("");
   }

   @Override
   public HasSelectHandlers getCreate()
   {
      return create;
   }

   @Override
   public String getMeetingTitle()
   {
      return title.getText();
   }

   @Override
   public String getDescription()
   {
      return description.getText();
   }

   @Override
   public Date getDate()
   {
      return date.getValue();
   }

   @Override
   public String getPlace()
   {
      return place.getText();
   }
}
