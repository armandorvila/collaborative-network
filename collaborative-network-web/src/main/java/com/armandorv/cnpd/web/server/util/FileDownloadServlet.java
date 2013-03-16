package com.armandorv.cnpd.web.server.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.web.server.svn.SvnHelper;

/**
 * Servlet to download a resource.
 * 
 * @author armandorv
 *
 */
public class FileDownloadServlet extends HttpServlet
{
   private static final long serialVersionUID = 7162464653962992673L;

   private static Logger log = Logger.getLogger(FileDownloadServlet.class);

   private static String TEMP_FILES_PATH = ResourceBundle.getBundle("svn").getString("svn.temp_files.directory");

   @Inject
   private IResourcesManager resourcesManager;

   @Inject
   private SvnHelper svnHelper;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      String resourceIdString = request.getParameter("resource_id");

      log.debug("Tried download of " + resourceIdString);

      long resourceId = Long.parseLong(resourceIdString);

      Resource resource = resourcesManager.getResource(resourceId);

      if (resource.getKind() == ResourceKind.FOLDER)
      {
         downloadFolder(resource, response);
      }
      else
      {
         downloadSingle(resource, response);
      }

   }

   private void downloadSingle(Resource resource, HttpServletResponse response) throws IOException
   {
      String content = svnHelper.getFileContent(resource.getUrl());

      String extension = resource.getKind().equals(ResourceKind.DOCUMENT) ? "pdf" : "txt";

      InputStream binaryContent = IOUtils.toInputStream(content);
      File temp = File.createTempFile("cnpd", "cnpd");
      
      IOUtils.copy(binaryContent, new FileOutputStream(temp));

      response.setContentType(resource.getKind().equals(ResourceKind.DOCUMENT) ? "application/octet-strem" : "text/plain");
      response.setHeader("Content-Disposition", "attachment;filename=" + resource.getName() + "." + extension);
      response.setContentLength((int)temp.length());
      
      OutputStream out = new DataOutputStream(response.getOutputStream());
      
      IOUtils.copy(new FileInputStream(temp), out);
   }

   private void downloadFolder(Resource resource, HttpServletResponse response) throws FileNotFoundException,
         IOException
   {
      String[] fileNames = new String[resource.getChilds().size()];

      int i = 0;

      File temp = new File(TEMP_FILES_PATH + resource.getName());
      log.info("Downloading folder " + temp.getAbsolutePath());

      temp.mkdir();

      for (Resource child : resource.getChilds())
      {
         if (!child.getKind().equals(ResourceKind.FOLDER))
         {
            fileNames[i] = child.getName();
            File tempChild = new File(temp.getAbsolutePath() + "/" + child.getName());
            log.info("Creating file " + tempChild.getAbsolutePath());
            tempChild.createNewFile();
            
            String content = svnHelper.getFileContent(child.getUrl());
            
            FileOutputStream out = new FileOutputStream(tempChild);
            IOUtils.copy(IOUtils.toInputStream(content), out);
            
            i++;
         }
      }

      File zip = ZipGenerator.generateZip(fileNames, resource.getName());
      
      log.info("Zip" + ((zip != null) ? zip.getName() : "null"));
      
      //IOUtils.copy(new FileInputStream(zip), response.getOutputStream());

      response.setContentType("multipart/x-zip");
      response.setHeader("Content-Disposition", "attachment;filename=" + resource.getName() + ".zip");

      String content = svnHelper.getFileContent(resource.getUrl());

      InputStream binaryContent = IOUtils.toInputStream(content);
      
      IOUtils.copy(binaryContent, new FileOutputStream(temp));
      OutputStream out = new DataOutputStream(response.getOutputStream());
      
      IOUtils.copy(new FileInputStream(temp), out);

   }

}
