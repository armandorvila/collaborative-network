package com.armandorv.cnpd.web.client.presenter.projects.project.milestones;

import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.armandorv.cnpd.web.shared.remote.MilestonesService;
import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.event.DeleteEvent;
import com.bradrydzewski.gwt.calendar.client.event.DeleteHandler;
import com.bradrydzewski.gwt.calendar.client.event.HasDeleteHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasTimeBlockClickHandlers;
import com.bradrydzewski.gwt.calendar.client.event.HasUpdateHandlers;
import com.bradrydzewski.gwt.calendar.client.event.UpdateEvent;
import com.bradrydzewski.gwt.calendar.client.event.UpdateHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class MilestonesPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      void addMilestone(MilestoneInfo milestone);

      boolean hasMilestoneAt(Date date);

      void setMilestones(List<MilestoneInfo> milesotnes);

      List<MilestoneInfo> getMilestones();

      HasSelectionHandlers<MilestoneInfo> getMilestonesCombo();

      HasTimeBlockClickHandlers<Date> getCLickable();

      HasDeleteHandlers<Appointment> getDeletable();

      HasUpdateHandlers<Appointment> getDraggable();

      void deleteMilestone(MilestoneInfo milestoneInfo);

      void setDesiredMilestone(MilestoneInfo lastMilestone);

      void setLastMilestone(String lastMilestone);
      
      void setLastMilestoneEnabled(boolean enabled);

      void updateMilestone(MilestoneInfo milestoneInfo);
   }

   @Inject
   private Display display;

   @Project
   @Inject
   private Event<Integer> tabSelectedEvent;

   @Inject
   private Caller<MilestonesService> milestonesService;

   @Inject
   private MilestoneCreator creator;

   private ProjectInfo project;
   
   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getDeletable().addDeleteHandler(this.delete());
      display.getDraggable().addUpdateHandler(this.moveMilestone());
      display.getMilestonesCombo().addSelectionHandler(this.setLastMilestone());
      display.getCLickable().addTimeBlockClickHandler(creator);
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be set.");

      creator.setProject(project);

      this.loadMilestones();

      display.setLastMilestone(project.getLastMilestone());
      display.setLastMilestoneEnabled(project.getManagerId() == user.getId());
      
      tabSelectedEvent.fire(display.getIndex());
   }

   private void loadMilestones()
   {
      milestonesService.call(new RemoteCallback<List<MilestoneInfo>>()
      {
         @Override
         public void callback(List<MilestoneInfo> response)
         {
            display.setMilestones(response);
            display.setDesiredMilestone(MilestonesUtils.calculateDesiredMilestone(response));
         }
      }).getMilestones(project.getId());
   }

   private SelectionHandler<MilestoneInfo> setLastMilestone()
   {
      return new SelectionHandler<MilestoneInfo>()
      {
         @Override
         public void onSelection(SelectionEvent<MilestoneInfo> event)
         {
            final MilestoneInfo milestone = event.getSelectedItem();

            milestonesService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Last milestone updated.", "Error selecting milestone.").message(
                        response);

                  if (response)
                  {
                     project.setLastMilestone(milestone.getName());
                     display.setLastMilestone(milestone.getName());
                  }

               }
            }).setLastMilestone(project.getId(), milestone.getId());
         }
      };
   }

   private UpdateHandler<Appointment> moveMilestone()
   {
      return new UpdateHandler<Appointment>()
      {
         @Override
         public void onUpdate(UpdateEvent<Appointment> event)
         {
            final Appointment appointment = event.getTarget();
            final long milestoneId = Long.parseLong(appointment.getId());
            final Date newDate = appointment.getStart();

            milestonesService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Moved " + appointment.getTitle() + " to "
                        + appointment.getStart(), "Error making movement.").message(response);

                  if (response)
                  {     
                     display.updateMilestone(new MilestoneInfo(milestoneId,appointment.getTitle(), newDate));
                     display.setDesiredMilestone(MilestonesUtils
                           .calculateDesiredMilestone(display.getMilestones()));
                  }
               }

            }).moveMilestone(milestoneId, newDate);
         }
      };
   }

   private DeleteHandler<Appointment> delete()
   {
      return new DeleteHandler<Appointment>()
      {
         @Override
         public void onDelete(DeleteEvent<Appointment> event)
         {
            final Appointment app = event.getTarget();
            final long milestone = Long.parseLong(app.getId());

            milestonesService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Milestone deleted", "Error deleting milestone.").message(response);

                  if (response)
                  {
                     display.deleteMilestone(new MilestoneInfo(milestone, app.getTitle(), app.getStart()));
                     display.setDesiredMilestone(MilestonesUtils
                           .calculateDesiredMilestone(display.getMilestones()));
                  }
               }
            }).deleteMilestone(project.getId(), milestone);
         }
      };
   }

   public Display getDisplay()
   {
      return display;
   }

   public void setProject(@Observes ProjectInfo project)
   {
      this.project = project;
   }
   
   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}