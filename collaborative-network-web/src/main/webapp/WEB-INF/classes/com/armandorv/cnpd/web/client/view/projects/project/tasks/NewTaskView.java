package com.armandorv.cnpd.web.client.view.projects.project.tasks;

import java.util.Date;

import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class NewTaskView extends Composite implements NewTaskPresenter.Display
{
   public interface NewTaskViewUIBinder extends UiBinder<Widget, NewTaskView>
   {
   }

   private static NewTaskViewUIBinder uiBinder = GWT.create(NewTaskViewUIBinder.class);
   
   @UiField
   TextField name;
   
   @UiField
   DateField beguining;
   
   @UiField
   Slider duration;
   
   @UiField
   TextButton create;

   public NewTaskView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public String getName()
   {
      return name.getText();
   }

   @Override
   public Date getBeguining()
   {
      return beguining.getCurrentValue();
   }

   @Override
   public Integer getDuration()
   {
      return duration.getValue();
   }

   @Override
   public HasSelectHandlers getCreate()
   {
      return create;
   }

   @Override
   public void clear()
   {
      name.reset();
      beguining.setValue(new Date());
      duration.setValue(3);
   }

}
