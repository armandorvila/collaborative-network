package com.armandorv.cnpd.business;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.model.Vote;

/**
 * Session Facade for deal with projects and all projects related concerns.
 * 
 * @author armandorv
 * 
 */
public interface IProjectsManager
{
   /**
    * Create a new project.
    * 
    * @param userId
    *            id of user who are creating the project.
    * @param idKnowldgeArea
    *            id of selected knowledge area for the project.
    * @param project
    *            project info.
    * @param idMembers
    *            variable array of hypothetical members identifiers.
    * @return the new project full filled.
    */
   Project createProject(long userId, long idKnowldgeArea, Project project,
         long... idMembers);

   /**
    * Retrieve a project for a given identifier.
    * 
    * @param id
    *            identifier of desired project.
    * @return the project which their identifier match with given identifier.
    */
   Project getProject(long id);

   /**
    * Get all projects of a user.
    * 
    * @param user
    *            user to get their projects.
    * @return all projects of given user.
    */
   Set<Project> getProjectsByUser(long userId);

   /**
    * @return All projects in the system.
    */
   List<Project> getAllProjects();

   /**
    * A user leave a project, if user is the manager other manager will be
    * reassigned and if there is no more users, project will disappear
    * 
    * @param userId
    *            identifier of user who are leaving the project.
    * @param projectId
    *            identifier of project.
    */
   void leaveProject(long userId, long projectId);

   /**
    * Get all invitations to project that a user has.
    * 
    * @param userId
    *            identifier of user.
    * @return project invitations.
    */
   Set<Project> getProjectInvitations(long userId);

   /**
    * Send a invitation for a project to a user.
    * 
    * @param userId
    *            identifier of user to send the invitation.
    * @param projectId
    *            identifier of the project.
    */
   void sendInvitationToProject(long userId, long projectId);

   /**
    * Resolve a invitations accepting of refusing it.
    * 
    * @param userId
    *            id of invited user.
    * @param projectId
    *            id of project.
    * @param accept
    *            if user accept is true.
    */
   void resolveProjectInvitation(long userId, long projectId, boolean accept);

   /**
    * Method to get all members of a project.
    * 
    * @param projectId
    * @return
    */
   Set<User> getProjectMemebers(long projectId);

   /**
    * Remove a project of system.
    * 
    * @param projectId
    *            identifier of the project to remove.
    */
   void deleteProject(long projectId);

   /**
    * Set the project scope to public. 
    *
    * @param projctId
    */
   void publishProject(long projctId);

   /**
    * Modify a project given their identifier.
    * 
    * @param projectId identifier of project to be modified.
    * @param knowledgeArea a correct Knowledge Area identifier. 
    * @param title the new title, is supposed to be a valid String.
    * @param description the new description, is supposed to be a valid String.
    */
   void modifyProject(long projectId, long knowledgeArea, String title, String description);

   /**
    * Set a user as manager of a project.
    * 
    * @param projectId identifier of project added.
    * @param newManagerId identifier of the new manager.
    */
   void setProjectManager(long projectId, long newManagerId);

   /**
    * Method to obtain all references of a project.
    * 
    * @param projectId
    *            identifier of a project.
    * @return All external references of given project.
    */
   Set<Reference> getReferences(long projectId);

   /**
    * @return All references into the system.
    */
   List<Reference> getAllReferences();

   /**
    * Add a existent reference to project.
    * @param referenceId identifier of reference. 
    */
   void addReference(long projectId, long referenceId);

   /**
    * Create a reference and add it to the project.
    * 
    * @param reference new reference.
    * 
    * @return the new reference.
    */
   Reference addNewReference(long projectId, Reference reference);

   /**
    * Remove a reference from the given project.
    * 
    * @param projectId
    *            identifier of project.
    *            
    * @param referenceId
    *            identifier of reference to remove.
    */
   void removeReference(long projectId, long referenceId);

   /**
    * Method to obtain all discussion of a given project.
    * 
    * @param projectId
    *            identifier of project.
    * @return a list with all discussions of given project.
    */
   Set<Discussion> getDiscussions(long projectId);

   /**
    * Create a new discussion and add it to given identifier project.
    * 
    * @param projectId identifier of project.
    * 
    * @param discussion information of new discussion.
    * 
    * @return the new discussion full filled.
    */
   Discussion addDiscussion(long projectId, Discussion discussion);

   /**
    * Add a new vote to an open discussion.
    * 
    * @param discussionId identifier of discussion to add the vote.
    * 
    * @param vote new vote to add. 
    */
   void addVoteToDiscussion(long discussionId, Vote vote);

   /**
    * Close set a discussion.open to false.
    * 
    * @param discussionId identifier of discussion to close.
    */
   void closeDiscussion(long discussionId);

   /**
    * Delete a discussion.
    * 
    * @param discussionId identifier of the discussion.
    */
   void deleteDiscussion(long discussionId);

   /**
    * Remove a discussion from system, and therefore from their project.
    * 
    * @param idDiscussion identifier of discussion to remove.
    */
   void removeDiscussion(long idDiscussion);

   /**
    * Method to obtain all milestones of project.
    * 
    * @param projectId identifier of project.
    * 
    * @return milestone of given identifier project.
    */
   Set<Milestone> getMilestones(long projectId);

   /**
    * Create a new milestone.
    * 
    * @param milestone new milestone.
    * 
    * @return the new milestone full filled.
    */
   Milestone addNewMilestone(long projectId, Milestone milestone);

   /**
    * Remove a milestone of a project.
    * 
    * @param projectId identifier of project that own the milestone.
    * 
    * @param milestoneId identifier of milestone to remove.
    */
   void removeMilestone(long projectId, long milestoneId);

   /**
    * Change the date of a milestone.
    * 
    * @param milestoneId identifier of milestone to change.
    * 
    * @param newDate new date.
    */
   void modifyMilestoneDate(long milestoneId, Date newDate);

   /**
    * Set the last reached milestone.
    * 
    * @param projectId project.
    * 
    * @param milestone milestone information.
    */
   void setLastMilestone(long projectId, long milestoneId);

   /**
    * 
    * @param projectId
    * @return
    */
   Set<Task> getTasks(long projectId);

   /**
    * 
    * @param projectId
    * @param task
    * @return
    */
   Task addNewTask(long projectId, Task task);

   /**
    * 
    * @param taskId
    */
   void setTaskAsDone(long taskId);

   /**
    * 
    * @param taskId
    * @param hours
    */
   void imputeHoursToTask(long taskId, int hours);

   /**
    * 
    * @param projectId
    * @param taskId
    */
   void deleteTask(long taskId);

   /**
    * Define a new knowledge area.
    * 
    * @param knowledgeArea new knowledge area.
    * 
    * @return the new knowledge area full filled.
    */
   KnowledgeArea createKnowledgeArea(KnowledgeArea knowledgeArea);
   
   /**
    * Delete a knowledge area.
    * 
    * @param knowledgeAreaId identifier.
    */
   void deleteKnowledgeArea(long knowledgeAreaId);

   /**
    * @return all available knowledge areas.
    */
   List<KnowledgeArea> getAllKnowledgeAreas();

}