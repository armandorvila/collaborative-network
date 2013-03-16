package com.armandorv.cnpd.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.specialist.DiscussionsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.KnowledgeAreasSpecialist;
import com.armandorv.cnpd.business.impl.specialist.MilestonesSpecialist;
import com.armandorv.cnpd.business.impl.specialist.ProjectInvitationsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.ProjectsSpecialist;
import com.armandorv.cnpd.business.impl.specialist.ReferencesSpecialist;
import com.armandorv.cnpd.business.impl.specialist.TasksSpecialist;
import com.armandorv.cnpd.business.local.IProjectsManagerLocal;
import com.armandorv.cnpd.business.remote.IProjectsManagerRemote;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.model.Vote;

/**
 * Implementation of the Session facade IProjectsManager which delegate all
 * methods executions on more specialist objects.
 * 
 * @author armandorv
 * 
 */
@Stateless
public class ProjectsManager implements IProjectsManagerLocal,
      IProjectsManagerRemote
{
   @Inject
   private ProjectsSpecialist projectsSpecialist;

   @Inject
   private ProjectInvitationsSpecialist invitationsSpecialist;

   @Inject
   private ReferencesSpecialist referencesSpecialist;

   @Inject
   private DiscussionsSpecialist discussionsSpecialist;

   @Inject
   private MilestonesSpecialist milestonesSpecialist;

   @Inject
   private KnowledgeAreasSpecialist knowledgeAreasSpecialist;
   
   @Inject
   private TasksSpecialist  tasksSpecialist;

   @Override
   public Project createProject(long userId, long idKnowldgeArea,
         Project project, long... idMembers)
   {
      return projectsSpecialist.createProject(userId, idKnowldgeArea,
            project, idMembers);
   }

   @Override
   public Project getProject(long id)
   {
      return projectsSpecialist.getProject(id);
   }

   @Override
   public Set<Project> getProjectsByUser(long userId)
   {
      return projectsSpecialist.getProjectsByUser(userId);
   }

   @Override
   public Set<Reference> getReferences(long projectId)
   {
      return referencesSpecialist.getReferences(projectId);
   }

   @Override
   public Set<Discussion> getDiscussions(long projectId)
   {
      return this.discussionsSpecialist.getDiscussions(projectId);
   }

   @Override
   public Set<Milestone> getMilestones(long projectId)
   {
      return this.milestonesSpecialist.getMilestones(projectId);
   }

   @Override
   public Milestone addNewMilestone(long projectId, Milestone milestone)
   {
      return milestonesSpecialist.addNewMilestone(projectId, milestone);
   }

   @Override
   public void removeMilestone(long projectId, long milestoneId)
   {
      milestonesSpecialist.removeMilestone(projectId, milestoneId);
   }

   @Override
   public void setLastMilestone(long projectId, long milestoneId)
   {
      milestonesSpecialist.setLastMilestone(projectId, milestoneId);
   }
   

   @Override
   public void modifyMilestoneDate(long milestoneId, Date newDate)
   {
      milestonesSpecialist.modifyMilestoneDate(milestoneId, newDate);
   }

   @Override
   public Discussion addDiscussion(long projectId, Discussion discussion)
   {
      return discussionsSpecialist.addDiscussion(projectId, discussion);
   }

   @Override
   public List<Project> getAllProjects()
   {
      return projectsSpecialist.getAllProjects();
   }

   @Override
   public KnowledgeArea createKnowledgeArea(KnowledgeArea knowledgeArea)
   {
      return knowledgeAreasSpecialist.createKnowledgeArea(knowledgeArea);
   }

   @Override
   public List<KnowledgeArea> getAllKnowledgeAreas()
   {
      return knowledgeAreasSpecialist.getAllKnowledgeAreas();
   }

   @Override
   public void addReference(long projectId, long referenceId)
   {
      this.referencesSpecialist.addReference(projectId, referenceId);
   }

   @Override
   public Reference addNewReference(long projectId, Reference reference)
   {
      return referencesSpecialist.addNewReference(projectId,
            reference);
   }

   @Override
   public void removeDiscussion(long discussionId)
   {
      this.discussionsSpecialist.removeDiscussion(discussionId);
   }

   @Override
   public Set<Project> getProjectInvitations(long userId)
   {
      return invitationsSpecialist.getProjectInvitations(userId);
   }

   @Override
   public void sendInvitationToProject(long userId, long projectId)
   {
      this.invitationsSpecialist.sendInvitationToProject(userId, projectId);
   }

   @Override
   public void resolveProjectInvitation(long userId, long projectId, boolean accept)
   {
      this.invitationsSpecialist.resolveInvitation(userId, projectId, accept);
   }

   @Override
   public void deleteProject(long projectId)
   {
      this.projectsSpecialist.deleteProject(projectId);
   }

   @Override
   public void removeReference(long projectId, long referenceId)
   {
      this.referencesSpecialist.removeReference(projectId, referenceId);
   }

   @Override
   public Set<User> getProjectMemebers(long projectId)
   {
      return projectsSpecialist.getProjectMembers(projectId);
   }

   @Override
   public void leaveProject(long userId, long projectId)
   {
      projectsSpecialist.leaveProject(userId, projectId);
   }

   @Override
   public List<Reference> getAllReferences()
   {
      return referencesSpecialist.getAllReferences();
   }

   @Override
   public void addVoteToDiscussion(long discussionId, Vote vote)
   {
      discussionsSpecialist.addVoteToDiscussion(discussionId , vote);
   }

   @Override
   public void closeDiscussion(long discussionId)
   {
      discussionsSpecialist.closeDiscussion(discussionId);
   }

   @Override
   public void deleteDiscussion(long discussionId)
   {
      discussionsSpecialist.deleteDiscussion(discussionId);
   }

   @Override
   public Set<Task> getTasks(long projectId)
   {
      return tasksSpecialist.getTasks(projectId);
   }

   @Override
   public Task addNewTask(long projectId, Task task)
   {
      return tasksSpecialist.addNewTask(projectId , task);
   }

   @Override
   public void setTaskAsDone(long taskId)
   {
      tasksSpecialist.setTaskAsDone(taskId);
   }

   @Override
   public void imputeHoursToTask(long taskId, int hours)
   {
      tasksSpecialist.imputeHoursToTask(taskId , hours);
   }

   @Override
   public void deleteTask(long taskId)
   {
      tasksSpecialist.deleteTask(taskId);
   }

   @Override
   public void publishProject(long projectId)
   {
      projectsSpecialist.publishProject(projectId);
   }

   @Override
   public void setProjectManager(long projectId, long newManagerId)
   {
      projectsSpecialist.setManagerId(projectId , newManagerId); 
   }

   @Override
   public void modifyProject(long projectId, long knowledgeArea, String title, String description)
   {
      projectsSpecialist.modifyProject(projectId,knowledgeArea,title,description);
   }

   @Override
   public void deleteKnowledgeArea(long knowledgeAreaId)
   {
      knowledgeAreasSpecialist.deleteKnowledgeArea(knowledgeAreaId);
   }

}