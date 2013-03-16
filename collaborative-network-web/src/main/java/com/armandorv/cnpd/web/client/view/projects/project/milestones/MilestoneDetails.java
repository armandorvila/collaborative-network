package com.armandorv.cnpd.web.client.view.projects.project.milestones;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.Dialog;

/**
 * Dialog that show milestone name and date.
 * 
 * @author armandorv
 * 
 */
public class MilestoneDetails extends Dialog
{
   private VerticalPanel panel = new VerticalPanel();

   public MilestoneDetails(Appointment milestone)
   {
      super.add(panel);
      super.setPredefinedButtons(PredefinedButton.CLOSE);

      DateTimeFormat dtf = DateTimeFormat.getFormat("MM/dd/yyyy");

      panel.add(new Label(dtf.format(milestone.getStart()) + " :"));
      panel.add(new Label(milestone.getTitle()));
      panel.setSpacing(10);
   }

}