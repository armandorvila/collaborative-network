package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;

/**
 * Remote interfaz used by client side code to deal with projects.
 * 
 * @author armandorv
 * 
 */
@Remote
public interface ProjectsService
{
   /**
    * Retrieve a project given an identifier.
    * 
    * @param projectId identifier of desired project.
    * @return the project or null.
    */
   ProjectInfo getProject(long projectId);

   /**
    * Retrieve all projects for a given user identifier.
    * 
    * @param userId identifier of user.
    * @return a list of projects.
    */
   List<ProjectInfo> getProjects(long userId);

   /**
    * Get all contacts of a contact.
    * 
    * @param username username of the contact.
    * @return a list with all published projects of the contact.
    */
   List<ProjectInfo> getContactProjects(String username);

   /**
    * Create a new project.
    * 
    * @param userId
    * @param project
    * @return
    */
   boolean createProject(long userId, ProjectInfo project);

   /**
    * Get all invitations of a user.
    * 
    * @param id
    * @return
    */
   List<ProjectInfo> getProjectInvitations(long id);

   /**
    * Accept an invitations of a user to the given project.
    * 
    * @param userId
    * @param projectId
    * @return
    */
   boolean acceptProjectInvitation(long userId, long projectId);

   /**
    * Refuse an invitations of a user to the given project.
    * 
    * @param userId
    * @param projectId
    * @return
    */
   boolean refuseProjectInvitation(long userId, long projectId);

   /**
    * Get all members of a project.
    * 
    * @param projectId
    * @return
    */
   List<ContactInfo> getMembers(long projectId);
   
   /**
    * Get all connected members of the project excluding the user id.
    * 
    * @param projectId identifier of the project.
    * @param userId identifier of the user.
    * 
    * @return
    */
   List<ContactInfo> getConnectdMembers(long projectId , long userId);

   /**
    * Search projects.
    * 
    * @param searchTitle
    * @param searchKnowledgeArea
    * 
    * @return
    */
   List<ProjectInfo> searchProjects(String searchTitle, IdNameGenericInfo searchKnowledgeArea);

   /**
    * Delete a project.
    * 
    * @param projectId
    * @return
    */
   boolean deleteProject(long projectId);

   /**
    * Remove a user from a project.
    * 
    * @param userId
    * @param projectId
    * @return
    */
   boolean leaveProject(long userId , long projectId);

   /**
    * Allow publish a project.
    * 
    * @param projectId identifier of project to publish.
    * @return true if all OK.
    */
   boolean publishProject(long projectId);
   
   /**
    * Allow modify a project.
    * 
    * @param project project with identifier, title , description and area.
    * @return true if all OK.
    */
   boolean modifyProject(ProjectInfo project);

   /**
    * 
    * @param projectId
    * @param addedContacts
    * @return true if all OK.
    */
   boolean inviteContacts(long projectId, List<ContactInfo> addedContacts);

   /**
    * 
    * @param projectId
    * @param newManagerId
    * @return true if all OK.
    */
   boolean setProjectManager(long projectId, long newManagerId);

   /**
    * 
    * @param memberId
    * @param projectId
    * @return true if all OK.
    */
   boolean excludeMemberOfProject(long memberId, long projectId);
}