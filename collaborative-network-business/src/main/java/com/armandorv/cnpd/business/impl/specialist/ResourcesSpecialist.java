package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.BusinessException;
import com.armandorv.cnpd.business.exception.IllegalResourceMovementException;
import com.armandorv.cnpd.business.exception.NonExisistentIdException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.svn.SvnHelper;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.persistence.IResourceDao;

/**
 * Specialist on deal with resources, is used by resources manager, it must be
 * within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class ResourcesSpecialist
{
   @Inject
   private IResourceDao resourceDao;

   @Inject
   private IProjectDao projectDao;

   @Inject
   private SvnHelper svnHelper;

   private FindByIdExecutor<Resource> findResourceById;

   private FindByIdExecutor<Project> findProjectById;

   @PostConstruct
   public void setUp()
   {
      this.findResourceById = new FindByIdExecutor<Resource>(resourceDao);
      this.findProjectById = new FindByIdExecutor<Project>(projectDao);
   }

   public Resource getResource(long id)
   {
      return resourceDao.findById(id);
   }

   public void renameResource(long resourceId, String name)
   {
      Resource resource = findResourceById.findById(resourceId);
      resource.setName(name);
   }

   public Resource createResource(long projectId, long idParent,
         Resource resource, boolean drafts)
   {
      Project project = findProjectById.findById(projectId);

      resourceDao.persist(resource);

      if (idParent == -1)
      {
         if (drafts)
            project.addDraft(resource);

         else
            project.addResource(resource);
      }
      else
      {
         Resource parent = findResourceById.findById(idParent);
         resource.setParent(parent);
         parent.addChild(resource);
      }

      this.createResourceInSvn(project, resource);

      return resource;
   }

   public void deleteResource(long projectId, long resourceId)
   {
      Resource resource = findResourceById.findById(resourceId);
      Project project = findProjectById.findById(projectId);

      if (resource.getParent() == null)
      {
         if (project.getResources().contains(resource))
            project.removeResource(resource);

         else if (project.getDrafts().contains(resource))
            project.removeDraft(resource);

         else
            throw new BusinessException("Invalid resource, project :"
                  + project.getId() + " resource :" + resourceId);
      }

      else
      {
         resource.getParent().removeChild(resource);
      }

      resourceDao.remove(resource);

      svnHelper.delete(resource.getUrl());
   }

   public Set<Resource> getResources(long projectId, boolean drafts)
   {
      Project project = findProjectById.findById(projectId);

      Set<Resource> resources = drafts ? project.getDrafts() : project
            .getResources();
      resources.size();

      return resources;
   }

   public Resource moveResource(long projectId, long resourceId, long parentId)
   {
      Resource resource = findResourceById.findById(resourceId);
      Resource newParent = getNewParent(parentId);

      if (!newParent.equals(resource.getParent()))
      {
         Project project = findProjectById.findById(projectId);
         removeFromParent(resource, project);

         resource.setParent(newParent);
         newParent.addChild(resource);

         svnHelper.move(resource.getUrl(), newParent.getUrl());
         String url = newParent.getUrl() + "/resource" + "-" + resource.getId();
         resource.setUrl(url);

         updateChildsUrls(resource);
      }
      return resource;
   }

   /**
    * Move a resource to resources root or to drafts root , depends on draft parameter.
    * 
    * @param projectId identifier of the project.
    * @param resourceId identifier of the resource to move.
    * @param draft if true move to drafts root else move to resources root.
    * 
    * @return the resource updated (URL and parent).
    */
   public Resource moveResourceToRoot(long projectId, long resourceId, boolean draft)
   {
      Resource resource = findResourceById.findById(resourceId);
      Project project = findProjectById.findById(projectId);

      if (draft)
         project.addDraft(resource);
      else
         project.addResource(resource);

      removeFromParent(resource, project);
      
      if (resource.getParent() != null)
      {
         resource.setParent(null);

         svnHelper.move(resource.getUrl(), project.getUrl());
         String url = project.getUrl() + "/resource" + "-" + resource.getId();
         resource.setUrl(url);

         updateChildsUrls(resource);
      }
      return resource;
   }

   private void updateChildsUrls(Resource resource)
   {
      for (Resource child : resource.getChilds())
      {
         child.setUrl(resource.getUrl() + "/resource" + "-" + child.getId());

         if (!child.getChilds().isEmpty())
            updateChildsUrls(child);
      }
   }

   private Resource getNewParent(long parentId)
   {
      try
      {
         return findResourceById.findById(parentId);
      }
      catch (NonExisistentIdException e)
      {
         throw new IllegalResourceMovementException("The parent id is incorrect (non exists) :" + parentId);
      }
   }

   private void removeFromParent(Resource resource, Project project)
   {
      if (resource.getParent() != null)
      {
         resource.getParent().removeChild(resource);
      }

      else if (project.getResources().contains(resource))
      {
         project.removeResource(resource);
      }
      else if (project.getDrafts().contains(resource))
      {
         project.removeDraft(resource);
      }
      else
      {
         throw new IllegalResourceMovementException(
               "Resource " + resource.getId()
                     + " hasn't parent and it is not contained neither by resources nor by drafts of project "
                     + project.getId());
      }
   }

   /**
    * Create a resource in subversion repository.
    */
   private void createResourceInSvn(Project project, Resource resource)
   {
      String url = resource.getParent() == null ? project.getUrl() : resource
            .getParent().getUrl();

      url += "/resource" + "-" + resource.getId();

      if (resource.getKind().equals(ResourceKind.FOLDER))
         svnHelper.createFolder(url);
      else
         svnHelper.createFile(url);

      resource.setUrl(url);
   }

}