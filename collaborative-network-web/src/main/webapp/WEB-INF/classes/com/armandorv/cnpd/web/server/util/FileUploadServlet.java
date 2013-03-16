package com.armandorv.cnpd.web.server.util;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.web.server.svn.SvnHelper;
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;

public class FileUploadServlet extends HttpServlet
{
   private static final long serialVersionUID = 4725195331301125087L;

   private static final Logger log = Logger.getLogger(FileUploadServlet.class);

   @Inject
   private IResourcesManager resourceManager;

   @Inject
   private IProjectsManager projectsManager;

   @Inject
   private SvnHelper svnHelper;

   @Override
   protected void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException
   {
      String projectId = request.getParameter("prid");
      String parentId = request.getParameter("paid");

      String resourceName = request.getParameter("rname");
      String resourceId = request.getParameter("reid");
      String kind = request.getParameter("kind");

      log.info("File Upload Request [ Project :" + projectId + " Resource Name "
            + resourceName + " Kind " + kind);

      if (ServletFileUpload.isMultipartContent(request))
      {
         ServletFileUpload upload = new ServletFileUpload();
         try
         {
            FileItemIterator iter = upload.getItemIterator(request);

            for (FileItemIterator iterator = iter; iterator.hasNext();)
            {
               FileItemStream itemStream = iterator.next();

               if (!itemStream.isFormField())
               {
                  if (resourceId == null)
                  {
                     this.createFromUploaded(projectId, resourceName, parentId, kind, itemStream);
                  }
                  else
                  {
                     this.replaceResource(projectId, resourceId, itemStream);
                  }
               }
            }

         }
         catch (FileUploadException e)
         {
            log.error("Error uploading file", e);
            throw new ServersideException("Error processing file.", e);
         }
      }
      else
      {
         log.error("Invalid request , it must be multipart.");
      }

   }

   private void replaceResource(String projectId, String resourceId,
         FileItemStream itemStream)
   {
      log.info("Starting resource replacement ...");

      try
      {
         long projectIdLong = Long.parseLong(projectId);
         long resourceIdLong = Long.parseLong(resourceId);

         Resource resource = resourceManager.getResource(resourceIdLong);

         String parentUrl = resource.getParent() == null ? projectsManager.getProject(projectIdLong).getUrl()
               : resource.getParent().getUrl();

         svnHelper.modifyFileContent(resource.getUrl(), parentUrl, IOUtils.toString(itemStream.openStream()));
      }
      catch (IOException e)
      {
         throw new ServersideException("I/O error", e);
      }
   }

   private void createFromUploaded(String projectId, String resourceName, String parentId, String kind,
         FileItemStream itemStream)
   {
      log.info("Starting resource uploading from 0.");

      try
      {
         Resource resource = new Resource();
         resource.setName(resourceName);

         if (kind.equals(Kind.FILE.name()))
         {
            resource.setKind(ResourceKind.FILE);
         }
         else if (kind.equals(Kind.NONE.name()))
         {
            resource.setKind(ResourceKind.UNTYPED);
         }
         else
         {
            resource.setKind(ResourceKind.DOCUMENT);
         }

         long projectIdLong = Long.parseLong(projectId);
         long parentIdLong = Long.parseLong(parentId);

         resource = resourceManager.createResource(projectIdLong, parentIdLong, resource);

         String parentUrl = resource.getParent() == null ? projectsManager.getProject(projectIdLong).getUrl()
               : resource.getParent().getUrl();

         svnHelper.modifyFileContent(resource.getUrl(), parentUrl, IOUtils.toString(itemStream.openStream()));
      }
      catch (IOException e)
      {
         throw new ServersideException("I/O error", e);
      }
   }
}