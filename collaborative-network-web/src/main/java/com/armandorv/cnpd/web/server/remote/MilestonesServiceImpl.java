package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.shared.model.MilestoneInfo;
import com.armandorv.cnpd.web.shared.remote.MilestonesService;

@Service
@ApplicationScoped
public class MilestonesServiceImpl implements MilestonesService
{
   @Inject
   private IProjectsManager projectsManager;

   @Override
   @HandleServersideException
   public long addMilestone(long projectId, String name, Date date)
   {
      Milestone milestone = new Milestone();
      milestone.setName(name);
      milestone.setDate(date);

      return projectsManager.addNewMilestone(projectId, milestone).getId();
   }

   @Override
   @HandleServersideException
   public List<MilestoneInfo> getMilestones(long projectId)
   {
      List<MilestoneInfo> milestones = new ArrayList<MilestoneInfo>();

      Set<Milestone> businessMilestones = projectsManager.getMilestones(projectId);

      for (Milestone milestone : businessMilestones)
      {
         milestones.add(new MilestoneInfo(milestone.getId(), milestone.getName(), milestone.getDate()));
      }
      return milestones;
   }

   @Override
   @HandleBooleanException
   public boolean deleteMilestone(long projectId, long milestoneId)
   {
      projectsManager.removeMilestone(projectId, milestoneId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setLastMilestone(long projectId, long milestoneId)
   {
      projectsManager.setLastMilestone(projectId, milestoneId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean moveMilestone(long milestoneId, Date newDate)
   {
      projectsManager.modifyMilestoneDate(milestoneId, newDate);
      return true;
   }

}