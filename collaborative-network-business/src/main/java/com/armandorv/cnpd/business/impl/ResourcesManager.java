package com.armandorv.cnpd.business.impl;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.specialist.ResourcesSpecialist;
import com.armandorv.cnpd.business.local.IResourcesManagerLocal;
import com.armandorv.cnpd.business.remote.IResourcesManagerRemote;
import com.armandorv.cnpd.model.Resource;

@Stateless
public class ResourcesManager implements IResourcesManagerRemote,
      IResourcesManagerLocal
{
   @Inject
   private ResourcesSpecialist resourcesSpecialist;

   @Override
   public Resource getResource(long id)
   {
      return this.resourcesSpecialist.getResource(id);
   }

   @Override
   public void renameResource(long resourceId, String name)
   {
      this.resourcesSpecialist.renameResource(resourceId, name);
   }

   @Override
   public Resource createResource(long projectId, long idParent,
         Resource resource)
   {
      return this.resourcesSpecialist.createResource(projectId, idParent,
            resource, false);
   }

   @Override
   public Resource createResourceAsDraft(long projectId, long idParent,
         Resource resource)
   {
      return this.resourcesSpecialist.createResource(projectId,
            idParent, resource, true);
   }

   @Override
   public void deleteResource(long projectId, long resourceId)
   {
      this.resourcesSpecialist.deleteResource(projectId, resourceId);
   }

   @Override
   public Set<Resource> getResources(long projectId)
   {
      return this.resourcesSpecialist.getResources(projectId, false);
   }

   @Override
   public Set<Resource> getDraftResources(long projectId)
   {
      return this.resourcesSpecialist.getResources(projectId, true);
   }

   @Override
   public Resource moveResource(long projectId, long resourceId, long parentId)
   {
      return this.resourcesSpecialist.moveResource(projectId, resourceId, parentId);
   }

   @Override
   public Resource moveResourceToDrafts(long projectId, long resourceId)
   {
      return this.resourcesSpecialist.moveResourceToRoot(projectId, resourceId, true);
   }

   @Override
   public Resource moveResourceToResources(long projectId, long resourceId)
   {
      return this.resourcesSpecialist.moveResourceToRoot(projectId, resourceId, false);
   }

}