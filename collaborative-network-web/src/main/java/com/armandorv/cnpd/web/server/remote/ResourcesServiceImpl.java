package com.armandorv.cnpd.web.server.remote;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.bus.server.api.RpcContext;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.google.GoogleDocsProxy;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.server.svn.SvnHelper;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.qualifiers.Resources;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;

/**
 * 
 * @author armandorv
 * 
 */
@Service
@ApplicationScoped
public class ResourcesServiceImpl implements ResourcesService
{
   @Inject
   private IResourcesManager resouresManager;

   @Inject
   private IUsersManager usersManager;
   
   @Inject
   private IProjectsManager projectsManager;

   @Inject
   @Resources
   private Mapper<Resource, ResourceInfo> resourceMapper;

   @Inject
   private GoogleDocsProxy googleDocsProxy;

   @Inject
   private SvnHelper svnHelper;

   @Override
   @HandleServersideException
   public List<ResourceInfo> getResources(long projectId, boolean drafts)
   {
      List<ResourceInfo> resources = new ArrayList<ResourceInfo>();
      Set<Resource> businessResources = drafts ? resouresManager.getDraftResources(projectId) : resouresManager
            .getResources(projectId);

      for (Resource resource : businessResources)
      {
         resources.add(resourceMapper.map(resource));
      }
      return resources;
   }

   @Override
   @HandleServersideException
   public ResourceInfo createResource(long projectId, long parentId, ResourceInfo newResource, boolean draft)
   {
      Resource businessResource = new Resource();

      businessResource.setName(newResource.getName());
      businessResource.setKind(getBusinessKind(newResource.getKind()));
      if (draft)
         return resourceMapper.map(resouresManager.createResourceAsDraft(projectId, parentId, businessResource));
      else
         return resourceMapper.map(resouresManager.createResource(projectId, parentId, businessResource));
   }

   @Override
   @HandleBooleanException
   public boolean deleteResource(long projectId, long resourceId, boolean draft)
   {
      resouresManager.deleteResource(projectId, resourceId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean renameResource(long projectId, long resourceId, String name)
   {
      resouresManager.renameResource(resourceId, name);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean moveResource(long projectId, long resourceId, long parentId)
   {
      resouresManager.moveResource(projectId, resourceId, parentId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean moveResourceToRoot(long projectId, long resourceId, boolean toDrafts)
   {
      if (toDrafts)
         resouresManager.moveResourceToDrafts(projectId, resourceId);
      else
         resouresManager.moveResourceToResources(projectId, resourceId);

      return true;
   }

   @Override
   @HandleServersideException
   public String getFileContent(long idProject, ResourceInfo resource)
   {
      if (!resource.getKind().equals(Kind.FILE) && !resource.getKind().equals(Kind.MARKER))
         throw new ClientsideException("Resource to get content must be a file.");

      return svnHelper.getFileContent(resource.getUrl());
   }

   @Override
   @HandleBooleanException
   public boolean saveFileContent(String url, String parentUrl, String content)
   {
      svnHelper.modifyFileContent(url, parentUrl, content);
      return true;
   }

   private ResourceKind getBusinessKind(Kind kind)
   {
      if (ResourceInfo.Kind.FILE.equals(kind))
         return ResourceKind.FILE;

      if (ResourceInfo.Kind.DOCUMENT.equals(kind))
         return ResourceKind.DOCUMENT;

      if (ResourceInfo.Kind.FOLDER.equals(kind))
         return ResourceKind.FOLDER;

      if (ResourceInfo.Kind.NONE.equals(kind))
         return ResourceKind.UNTYPED;

      if (ResourceInfo.Kind.MEDIA.equals(kind))
         return ResourceKind.MEDIA;

      if (ResourceInfo.Kind.MARKER.equals(kind))
         return ResourceKind.MARKER;

      else
         throw new MappingErrorException("Invalid Resoure Kind :" + kind);
   }

   @Override
   @HandleServersideException
   public List<GDocsResource> getGoogleDocsResources(long userId, Kind kind)
   {
      User user = usersManager.getUserById(userId);

      if (user == null)
         throw new ServersideException("A invalid user are logued in.");

      return googleDocsProxy.getGoogleDocsResources(user.getGoogleAccount().getAccount(), user.getGoogleAccount()
            .getPassword(), kind);
   }

   @Override
   @HandleBooleanException
   public boolean importResource(long projectId , GDocsResource gdocResource, Kind kind, ResourceInfo parent)
   {
      InputStream content = googleDocsProxy.getResourceContent(gdocResource, kind);

      Resource resource = new Resource();
      resource.setName(gdocResource.getTitle());
      resource.setKind(getBusinessKind(kind));
      resource = resouresManager.createResource(projectId, parent.getId(), resource);
      
      String parentUrl = resource.getParent() == null ? projectsManager.getProject(projectId).getUrl()
            : resource.getParent().getUrl();
      
      try
      {
         svnHelper.modifyFileContent(resource.getUrl(), parentUrl, IOUtils.toString(content));
      }
      catch (IOException e)
      {
         return false;
      }
      
      return true;
   }

   @Override
   @HandleServersideException
   public String prepareResourceToShow(ResourceInfo resource)
   {
      return resource.getUrl();
   }

}