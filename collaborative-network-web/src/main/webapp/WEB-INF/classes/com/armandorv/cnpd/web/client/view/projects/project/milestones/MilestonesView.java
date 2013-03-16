package com.armandorv.cnpd.web.client.view.projects.project.milestones;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.armandorv.cnpd.web.client.view.util.properties.MilestoneInfoProperties;
import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.HasDeleteHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasTimeBlockClickHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasUpdateHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;

@Singleton
public class MilestonesView extends Composite implements MilestonesPresenter.Display
{
   private static MilestonesViewUiBinder uiBinder = GWT.create(MilestonesViewUiBinder.class);

   interface MilestonesViewUiBinder extends UiBinder<Widget, MilestonesView>
   {
   }

   private static MilestoneInfoProperties props = GWT.create(MilestoneInfoProperties.class);
   
   private ListStore<MilestoneInfo> store = new ListStore<MilestoneInfo>(props.id());

   @UiField(provided = true)
   Calendar calendar = new Calendar(CalendarViews.MONTH);

   @UiField(provided = true)
   ComboBox<MilestoneInfo> milestonesCombo = new ComboBox<MilestoneInfo>(store, props.label());

   @UiField
   TextField desiredMilestone;

   public MilestonesView()
   {
      calendar.setDate(new Date());
      calendar.addOpenHandler(this.createOpenHandler());
      
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   private OpenHandler<Appointment> createOpenHandler()
   {
      return new OpenHandler<Appointment>()
      {
         @Override
         public void onOpen(OpenEvent<Appointment> event)
         {
            new MilestoneDetails(calendar.getSelectedAppointment()).show();
         }
      };
   }

   @UiHandler("datepicker")
   void datePickerSelection(ValueChangeEvent<Date> event)
   {
      calendar.setDate(event.getValue());
   }

   @Override
   public HasSelectionHandlers<MilestoneInfo> getMilestonesCombo()
   {
      return milestonesCombo;
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.MILESTONES_INDEX;
   }

   @Override
   public void addMilestone(MilestoneInfo milestone)
   {
      calendar.addAppointment(this.milestoneToAppoiment(milestone));
      store.add(milestone);
   }

   @Override
   public HasTimeBlockClickHandlers<Date> getCLickable()
   {
      return calendar;
   }

   @Override
   public HasDeleteHandlers<Appointment> getDeletable()
   {
      return calendar;
   }

   @Override
   public boolean hasMilestoneAt(Date date)
   {
      for (Appointment milestone : calendar.getAppointments())
         if (milestone.getStart().equals(date))
            return false;

      return true;
   }

   @Override
   public void setMilestones(List<MilestoneInfo> milesotnes)
   {
      store.clear();
      calendar.clearAppointments();
     
      store.addAll(milesotnes);

      for (MilestoneInfo milestoneInfo : milesotnes)
         calendar.addAppointment(this.milestoneToAppoiment(milestoneInfo));
      
   }

   private Appointment milestoneToAppoiment(MilestoneInfo milestone)
   {
      Appointment app = new Appointment();
      
      app.setId(milestone.getId() + "");
      app.setTitle(milestone.getName());
      app.setStart(milestone.getDate());
      app.setEnd(milestone.getDate());
      app.setAllDay(true);

      return app;
   }

   @Override
   public HasUpdateHandlers<Appointment> getDraggable()
   {
      return calendar;
   }

   @Override
   public void deleteMilestone(MilestoneInfo target)
   {
      store.remove(target);
   }

   @Override
   public void setDesiredMilestone(MilestoneInfo lastMilestone)
   {
      desiredMilestone.setText(lastMilestone.getName());
   }

   @Override
   public void setLastMilestone(String lastMilestone)
   {
      milestonesCombo.setText(lastMilestone);
   }

   @Override
   public List<MilestoneInfo> getMilestones()
   {
      return store.getAll();
   }

   @Override
   public void setLastMilestoneEnabled(boolean enabled)
   {
      milestonesCombo.setEnabled(enabled); 
   }

   @Override
   public void updateMilestone(MilestoneInfo milestoneInfo)
   {
      store.update(milestoneInfo);
   }
}