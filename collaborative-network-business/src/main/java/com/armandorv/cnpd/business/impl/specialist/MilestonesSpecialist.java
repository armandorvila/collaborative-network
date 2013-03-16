package com.armandorv.cnpd.business.impl.specialist;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.BusinessException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.persistence.IMilestoneDao;
import com.armandorv.cnpd.persistence.IProjectDao;

/**
 * Specialist on deal with milestones concerns, is used by projects manager and
 * must be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class MilestonesSpecialist
{
   @Inject
   private IProjectDao projectDao;

   @Inject
   private IMilestoneDao milestoneDao;

   private FindByIdExecutor<Project> findProjectById;

   private FindByIdExecutor<Milestone> findMilestoneById;

   @PostConstruct
   public void setUp()
   {
     findProjectById = new FindByIdExecutor<Project>(projectDao);
     findMilestoneById = new FindByIdExecutor<Milestone>(milestoneDao);
   }

   public Set<Milestone> getMilestones(long projectId)
   {
      Project project = findProjectById.findById(projectId);

      Set<Milestone> milestones = project.getMilestones();
      milestones.size();

      return milestones;
   }

   public Milestone addNewMilestone(long projectId, Milestone milestone)
   {
      Project project = findProjectById.findById(projectId);

      milestoneDao.persist(milestone);
      milestone.setProject(project);

      return milestone;
   }

   public void removeMilestone(long projectId, long milestoneId)
   {
      Milestone milestone = findMilestoneById.findById(milestoneId);

      Project project = milestone.getProject();
      project.setLastMilestone(null);

      milestoneDao.remove(milestone);
   }

   public void setLastMilestone(long projectId, long milestoneId)
   {
      Project project = findProjectById.findById(projectId);

      Milestone milestone = milestoneDao.findById(milestoneId);

      if (!project.getMilestones().contains(milestone))
         throw new BusinessException("The milestone " + milestoneId
               + "is not a milestone of project " + projectId);

      project.setLastMilestone(milestone);
   }

   public void modifyMilestoneDate(long milestoneId, Date newDate)
   {
      Milestone milestone = findMilestoneById.findById(milestoneId);
      milestone.setDate(newDate);
   }
}