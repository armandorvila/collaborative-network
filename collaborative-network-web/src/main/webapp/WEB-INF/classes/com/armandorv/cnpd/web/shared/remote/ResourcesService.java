package com.armandorv.cnpd.web.shared.remote;

import java.util.List;
import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;

/**
 * Resources remote service for client side resources presenter.
 * 
 * @author armandorv
 * 
 */
@Remote
public interface ResourcesService
{
   /**
    * Retrieve all resources of a given project.
    * 
    * @param idProject
    *            identifier of project.
    * @return all top level resources of project.
    */
   List<ResourceInfo> getResources(long projectId, boolean draft);

   /**
    * Create a new resource on business model and on files server.
    * 
    * @param id
    *            id of project owner.
    * @param resource
    *            info to create new resource.
    * @param free
    *            if true resource is added to free resources list.
    * @return
    */
   ResourceInfo createResource(long projectId, long parentId, ResourceInfo resource, boolean draft);

   /**
    * Delete a resource for a concrete project.
    * 
    * @param idProject
    *            identifier of owner project.
    * @param idResource
    *            identifier of resource to remove.
    * @param free
    *            true if resource is true.
    * @return true if all OK.
    */
   boolean deleteResource(long projectId, long resourceId, boolean draft);

   /**
    * Rename a resource of a project.
    * 
    * @param idProject
    *            indicate project where is resource to rename.
    * @param idResource
    *            indicate resource to rename.
    * @param name
    *            new name.
    * @param free
    *            true if resource is a free resource.
    * @return true if all OK.
    */
   boolean renameResource(long projectId, long resourceId, String name);

   /**
    * Get the content of a file.
    * 
    * @param projectId identifier of the project.
    * @param resource resource to get their content.
    * 
    * @return a String with the file content.
    */
   String getFileContent(long projectId, ResourceInfo resource);

   /**
    * Save the content of a file.
    * 
    * @param url URL of resource in subversion.
    * @param parentUrl URL of resource parent in subversion.
    * @param content new content of the file.
    * 
    * @return true if all ok.
    */
   boolean saveFileContent(String url, String parentUrl, String content);

   /**
    * Move a resource to another resource.
    * 
    * @param projectId identifier of the project.
    * @param resourceId identifier of the resource.
    * @param parentId identifier of the resource parent.
    * 
    * @return true if all OK.
    */
   boolean moveResource(long projectId, long resourceId, long parentId);

   /**
    * Move a resource to the project root.
    * 
    * @param projectId identifier of the project.
    * @param resourceId identifier of the resource.
    * @param toDrafts if the resources are drafts.
    * 
    * @return true if all OK.
    */
   boolean moveResourceToRoot(long projectId, long resourceId, boolean toDrafts);

   /**
    * Retrieve all resource with a concrete kind from GoogleDocs.
    * 
    * @param userId identifier of the user.
    * @param kind kind of resources to get.
    * 
    * @return a List of resources.
    */
   List<GDocsResource> getGoogleDocsResources(long userId, Kind kind);

   /**
    * Import a resource from GoogleDocs.
    */
   boolean importResource(long projectId, GDocsResource resource, Kind kind, ResourceInfo parent);

   String prepareResourceToShow(ResourceInfo selected);
}
