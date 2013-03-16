package com.armandorv.cnpd.business.impl.specialist;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.InvalidMemberException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.svn.SvnHelper;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IKnowledgeAreaDao;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.persistence.IUserDao;

/**
 * Specialist on deal with projects, is used by projects manager, It must be
 * within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class ProjectsSpecialist
{
   @Inject
   private IUserDao userDao;

   @Inject
   private IProjectDao projectDao;

   @Inject
   private IKnowledgeAreaDao knowledgeAreaDao;

   @Inject
   private SvnHelper svnHelper;

   private FindByIdExecutor<User> findUserById;

   private FindByIdExecutor<KnowledgeArea> findAreaById;

   private FindByIdExecutor<Project> findProjectById;

   @PostConstruct
   public void setUp()
   {
      findUserById = new FindByIdExecutor<User>(userDao);
      findAreaById = new FindByIdExecutor<KnowledgeArea>(knowledgeAreaDao);
      findProjectById = new FindByIdExecutor<Project>(projectDao);
   }

   public Project getProject(long id)
   {
      return projectDao.findById(id);
   }

   public Set<Project> getProjectsByUser(long userId)
   {
      User user = findUserById.findById(userId);

      Set<Project> projects = user.getProjects();
      projects.size();

      return projects;
   }

   public Project createProject(long userId, long idKnowldgeArea,
         Project project, long... idMembers)
   {
      User user = findUserById.findById(userId);

      KnowledgeArea area = findAreaById.findById(idKnowldgeArea);

      project.setManagerId(userId);
      project.addMember(user);
      project.setKnowledgeArea(area);

      projectDao.persist(project);

      String url = svnHelper.createFolderAtRoot("project" + "-"
            + project.getId());

      project.setUrl(url);

      user.addProject(project);

      return project;
   }

   public void deleteProject(long projectId)
   {
      Project project = findProjectById.findById(projectId);

      for (User user : project.getMembers())
      {
         user = userDao.findById(user.getId());
         user.removeProject(project);
      }

      project.removeReferences();

      List<User> invited = userDao.findProjectInvitedUsers(projectId);

      for (User user : invited)
         user.removeProjectInvitation(project);

      projectDao.remove(project);
      svnHelper.delete(project.getUrl());
   }

   public List<Project> getAllProjects()
   {
      List<Project> all = projectDao.findAll();
      return all;
   }

   public Set<User> getProjectMembers(long projectId)
   {
      Project project = findProjectById.findById(projectId);

      project.getMembers().size();

      return project.getMembers();
   }

   public void leaveProject(long userId, long projectId)
   {
      Project project = findProjectById.findById(projectId);
      User user = findUserById.findById(userId);

      if (project.getMembers().size() == 1)
      {
         user.removeProject(project);
         projectDao.remove(project);
      }
      else
      {
         project.removeMember(user);
         user.removeProject(project);
         this.reasignManagerStatus(project);
      }
   }

   private void reasignManagerStatus(Project project)
   {
      int size = project.getMembers().size();
      int index = new Random().nextInt(size);

      User manager = project.getMembers().iterator().next();

      int i = 0;
      while (i < index)
         manager = project.getMembers().iterator().next();

      project.setManagerId(manager.getId());
   }

   public void publishProject(long projectId)
   {
      Project project = findProjectById.findById(projectId);
      project.setPublisht(true);
   }

   public void setManagerId(long projectId, long newManagerId)
   {
      Project project = findProjectById.findById(projectId);
      User member = findUserById.findById(newManagerId);

      if (!project.getMembers().contains(member))
      {
         throw new InvalidMemberException("The user must be a member of the new project.");
      }

      project.setManagerId(member.getId());
   }

   public void modifyProject(long projectId, long knowledgeArea, String title, String description)
   {
      Project project = findProjectById.findById(projectId);

      KnowledgeArea area = project.getKnowledgeArea();

      if (area.getId() != knowledgeArea)
         area = findAreaById.findById(knowledgeArea);

      project.setKnowledgeArea(area);
      project.setTitle(title);
      project.setDescription(description);
   }
}