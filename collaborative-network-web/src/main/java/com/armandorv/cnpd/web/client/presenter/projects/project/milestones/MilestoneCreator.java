package com.armandorv.cnpd.web.client.presenter.projects.project.milestones;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.MilestonesService;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * It execute the method onTimeBlockClick when a cell of calendar is clicked.
 * Check that click was double and execute logic to create the new milestones, 
 * update display milestones and update display desired milestone. 
 * 
 * @author armandorv
 *
 */
public class MilestoneCreator implements TimeBlockClickHandler<Date>
{
   @Inject
   private MilestonesPresenter.Display display;

   @Inject
   private Caller<MilestonesService> milestonesService;

   private ProjectInfo project = null;

   private Date lastClicked = null;

   @Override
   public void onTimeBlockClick(TimeBlockClickEvent<Date> event)
   {
      if (project == null)
         throw new ClientsideException("roject and milestones must be set.");

      if (lastClicked != null)
      {
         if (lastClicked.equals(event.getTarget()))
         {
            /* Stay here means that user did 2 clicks */
            if (display.hasMilestoneAt(event.getTarget()))
            {
               this.askName(event.getTarget());
            }
            else
            {
               Info.display("Invalid action", "You have already a milestone at selcted date");
               lastClicked = null;
            }
         }
         else
         {
            lastClicked = event.getTarget();
         }
      }
      else
      {
         lastClicked = event.getTarget();
      }
   }

   /**
    * Ask a name and if correct call server to create resource.
    */
   protected void askName(final Date date)
   {
      final PromptMessageBox box = new PromptMessageBox("New milestone", "Please enter a name :");

      box.addHideHandler(new HideHandler()
      {
         @Override
         public void onHide(HideEvent event)
         {
            if ("OK".equals(box.getHideButton().getText()))
            {
               String name = Format.ellipse(box.getValue(), 80);

               if (name != null)
               {
                  addMilestone(name, date);
               }
               else
               {
                  Info.display("Error", "you have to introduce a name.");
               }
            }
         }
      });
      box.show();
   }

   private void addMilestone(final String name, final Date date)
   {
      milestonesService.call(new RemoteCallback<Long>()
      {
         @Override
         public void callback(Long response)
         {
            if (response == -1)
            {
               Info.display("Fail", "Error creating milestone, try again.");
            }
            else
            {
               display.addMilestone(new MilestoneInfo(response, name, date));
               display.setDesiredMilestone(MilestonesUtils.calculateDesiredMilestone(display.getMilestones()));

               Info.display("Success", "Milestone created.");
            }
         }
      }).addMilestone(project.getId(), name, date);
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }
}