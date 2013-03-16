package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.qualifiers.Projects;
import com.armandorv.cnpd.web.shared.remote.LoadingService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;

@Service
@ApplicationScoped
public class ProjectsServiceImpl implements ProjectsService
{
   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private IUsersManager usersManager;

   @Inject
   private IResourcesManager resouresManager;

   @Inject
   @Projects
   private Mapper<Project, ProjectInfo> projectMapper;

   @Inject
   @Contacts
   private Mapper<User, ContactInfo> contactMapper;

   @Inject
   private LoadingService loadingService;

   @Override
   @HandleServersideException
   public List<ProjectInfo> getProjects(long userId)
   {
      return getProjectsInfo(projectsManager.getProjectsByUser(userId), true);
   }

   @Override
   @HandleServersideException
   public ProjectInfo getProject(long projectId)
   {
      return projectMapper.map(projectsManager.getProject(projectId));
   }

   @Override
   @HandleServersideException
   public List<ProjectInfo> getContactProjects(String username)
   {
      User user = usersManager.getUserByUsername(username);

      return getProjectsInfo(projectsManager.getProjectsByUser(user.getId()), false);
   }

   @Override
   @HandleBooleanException
   public boolean createProject(long userId, ProjectInfo project)
   {
      Project businessProject = new Project();
      businessProject.setDescription(project.getDescription());
      businessProject.setTitle(project.getTitle());
      businessProject.setPublisht(false);

      long areaId = project.getKnowledgeArea().getId();

      long[] members = new long[project.getMembers().size()];

      for (int i = 0; i < members.length; i++)
         members[i] = project.getMembers().get(i).getId();

      Project created = projectsManager.createProject(userId, areaId, businessProject, members);

      Resource resource = new Resource();
      resource.setName("description");
      resource.setKind(ResourceKind.MARKER);

      resouresManager.createResource(created.getId(), -1, resource);
      resouresManager.createResourceAsDraft(created.getId(), -1, resource);

      return true;
   }

   @Override
   @HandleServersideException
   public List<ProjectInfo> getProjectInvitations(long userId)
   {
      return getProjectsInfo(projectsManager.getProjectInvitations(userId), true);
   }

   @Override
   public boolean acceptProjectInvitation(long userId, long projectId)
   {
      return resolveProjectInvitation(userId, projectId, true);
   }

   @Override
   public boolean refuseProjectInvitation(long userId, long projectId)
   {
      return resolveProjectInvitation(userId, projectId, false);
   }

   @HandleBooleanException
   private boolean resolveProjectInvitation(long userId, long projectId, boolean accept)
   {
      projectsManager.resolveProjectInvitation(userId, projectId, accept);
      return true;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getMembers(long projectId)
   {
      List<ContactInfo> members = new ArrayList<ContactInfo>();

      Set<User> businessMembers = projectsManager.getProjectMemebers(projectId);

      for (User user : businessMembers)
      {
         members.add(contactMapper.map(user));
      }

      return members;
   }

   @Override
   @HandleServersideException
   public List<ProjectInfo> searchProjects(String searchTitle, IdNameGenericInfo searchKnowledgeArea)
   {
      List<ProjectInfo> projects = new ArrayList<ProjectInfo>();

      List<Project> businessProjects = projectsManager.getAllProjects();

      for (Project project : businessProjects)
      {
         if (project.isPublisht())
         {
            if (project.getTitle().contains(searchTitle)
                  && project.getKnowledgeArea().getId() == searchKnowledgeArea.getId())

               projects.add(projectMapper.map(project));
         }
      }
      return projects;
   }

   private List<ProjectInfo> getProjectsInfo(Set<Project> businessProjects, boolean publicsToo)
   {
      List<ProjectInfo> projects = new ArrayList<ProjectInfo>();

      for (Project project : businessProjects)
      {
         if (publicsToo || project.isPublisht())
            projects.add(projectMapper.map(project));
      }

      return projects;
   }

   @Override
   @HandleBooleanException
   public boolean deleteProject(long projectId)
   {
      projectsManager.deleteProject(projectId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean leaveProject(long userId, long projectId)
   {
      projectsManager.leaveProject(userId, projectId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean publishProject(long projectId)
   {
      projectsManager.publishProject(projectId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean inviteContacts(long projectId, List<ContactInfo> addedContacts)
   {
      for (ContactInfo contact : addedContacts)
      {
         projectsManager.sendInvitationToProject(contact.getId(), projectId);
      }
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setProjectManager(long projectId, long newManagerId)
   {
      projectsManager.setProjectManager(projectId, newManagerId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean excludeMemberOfProject(long memberId, long projectId)
   {
      projectsManager.leaveProject(memberId, projectId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean modifyProject(ProjectInfo project)
   {
      projectsManager.modifyProject(project.getId(), project.getKnowledgeArea().getId(), project.getTitle(),
            project.getDescription());
    
      return true;
   }

   @Override
   @HandleServersideException
   public List<ContactInfo> getConnectdMembers(long projectId, long userId)
   {
      List<ContactInfo> members = new ArrayList<ContactInfo>();

      Set<User> businessMembers = projectsManager.getProjectMemebers(projectId);

      Set<String> connectedUsernames = loadingService.getConnectedUsers();

      for (User businessMember : businessMembers)
      {
         String username = businessMember.getGoogleAccount().getAccount();

         if (userId != businessMember.getId() && connectedUsernames.contains(username))
            members.add(contactMapper.map(businessMember));
      }

      return members;
   }
}