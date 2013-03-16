package com.armandorv.cnpd.business;

import java.util.Set;

import com.armandorv.cnpd.model.Resource;

/**
 * Interface that deal with resources.
 * 
 * @author armandorv
 * 
 */
public interface IResourcesManager
{

   /**
    * Create a new resource in the given project.
    * 
    * @param project
    *            project owner of resource, a resource always belong to a
    *            project.
    * @param parentId
    *            id of parent resource, use idParent == -1 to append resource
    *            at the project root.
    * @param resource
    *            the new resource.
    */
   Resource createResource(long projectId, long parentId, Resource resource);

   /**
    * Create a resource and put it as free resource into project. This method
    * follow the same rules that createResource method.
    */
   Resource createResourceAsDraft(long projectId, long parentId,
         Resource resource);

   /**
    * @param id
    *            identifier of a desired resource
    * @return resource that match with given identifier or null.
    */
   Resource getResource(long id);

   /**
    * 
    * @param idProject
    * @param resource
    */
   void deleteResource(long projectId, long resourceId);

   /**
    * @param idProject
    *            owner of resources.
    * @return all resource of a project.
    */
   Set<Resource> getResources(long projectId);

   /**
    * 
    * @param idProject
    * @return
    */
   Set<Resource> getDraftResources(long projectId);

   /**
    * 
    * @param idResource
    * @param name
    */
   void renameResource(long resourceId, String name);

   /**
    * Move a resource from their parent to another resource.
    * Resource might hasn't parent , parent might to be a draft or to be a resource.
    * If resource is moved to any parent folder toResources = false and toDrafts = false.
    * booleans parameters are only to mark movements to roots, when there is not a parent. 
    * 
    * @param projectId identifier of the project, if there is no
    * @param resourceId identifier of the resource to move.
    * @param parentId identifier of the new parent.
    * @param toResources if resources must be moved to resources ROOT, not to a folder in resources.
    * @param toDrafts if resources must be moved to resources root.
    * 
    * @return the resource updated.
    */
   Resource moveResource(long projectId, long resourceId, long parentId);
   
   Resource moveResourceToDrafts(long projectId, long resourceId);
   
   Resource moveResourceToResources(long projectId, long resourceId);
}