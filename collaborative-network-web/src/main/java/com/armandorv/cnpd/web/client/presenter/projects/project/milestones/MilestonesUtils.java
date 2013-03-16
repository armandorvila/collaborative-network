package com.armandorv.cnpd.web.client.presenter.projects.project.milestones;

import java.util.Date;
import java.util.List;

import com.armandorv.cnpd.web.shared.model.MilestoneInfo;

public abstract class MilestonesUtils
{
   public static MilestoneInfo calculateDesiredMilestone(List<MilestoneInfo> response)
   {
      Date currentDate = new Date();
      MilestoneInfo last = new MilestoneInfo(-1L, "None", new Date(Long.MIN_VALUE));

      for (MilestoneInfo milestoneInfo : response)
      {
         if (milestoneInfo.getDate().getTime() < currentDate.getTime())
         {
            if (milestoneInfo.getDate().getTime() > last.getDate().getTime())
               last = milestoneInfo;
         }
      }
      return last;
   }
}
