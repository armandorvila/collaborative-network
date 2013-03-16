package com.armandorv.cnpd.web.shared.remote;

import java.util.Date;
import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.MilestoneInfo;

/**
 * Remote interfaz to deal with milestones from client side.
 * 
 * @author armandorv
 *
 */
@Remote
public interface MilestonesService
{
   /**
    * Add a new milestone to the project.
    * 
    * @param projectId identifier of project.
    * @param name name of new Milestone.
    * @param date date of new milestone.
    * 
    * @return the identifier of the milestone.
    */
   long addMilestone(long projectId, String name, Date date);

   /**
    * Retrieve all milestone for a project.
    * 
    * @param projectId identifier of project.
    * 
    * @return a list of milestones.
    */
   List<MilestoneInfo> getMilestones(long projectId);

   /**
    * Remove a milestone of a project.
    * 
    * @param projectId identifier of the project.
    * @param milestoneId identifier of the milestone.
    * 
    * @return true if OK.
    */
   boolean deleteMilestone(long projectId, long milestoneId);

   /**
    * Set a milestone as the last reached milestone of a project.
    * 
    * @param projectId identifier of the new project.
    * 
    * @param milestoneId identifier of the new milestone.
    * 
    * @return 
    */
   boolean setLastMilestone(long projectId, long milestoneId);

   /**
    * Change a milestone of date.
    * 
    * @param milestoneId identifier of the milestone.
    * @param newDate new date.
    * @return true if OK.
    */
   boolean moveMilestone(long milestoneId, Date newDate);
}
