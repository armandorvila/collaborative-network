package com.armandorv.cnpd.web.server.google;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;

public class GoogleDocsProxyTest
{
   private static Logger log = Logger.getLogger(GoogleDocsProxyTest.class);

   private GoogleDocsProxy proxy = new GoogleDocsProxy();

   private String username = "armmandoo2@gmail.com";

   private String password = "173048gmail";

   @Test
   public void testGetDocuments()
   {
      List<GDocsResource> documents = proxy.getGoogleDocsResources(username, password, Kind.DOCUMENT);
      Assert.assertFalse(documents.isEmpty());

      for (GDocsResource resourceInfo : documents)
      {
         Assert.assertNotNull(resourceInfo);
         log.info(" Document " + resourceInfo.getTitle());
      }
   }

   @Test
   public void testGetFolders()
   {
      List<GDocsResource> documents = proxy.getGoogleDocsResources(username, password, Kind.FOLDER);
      Assert.assertFalse(documents.isEmpty());

      for (GDocsResource resourceInfo : documents)
      {
         Assert.assertNotNull(resourceInfo);
         log.info(" Document " + resourceInfo.getTitle());
      }
   }

   @Test
   public void testGetNones()
   {
      List<GDocsResource> documents = proxy.getGoogleDocsResources(username, password, Kind.NONE);
      Assert.assertFalse(documents.isEmpty());

      for (GDocsResource resourceInfo : documents)
      {
         Assert.assertNotNull(resourceInfo);
         log.info(" Document " + resourceInfo.getTitle());
      }
   }

   @Test
   public void testGetDocument() throws IOException
   {
      List<GDocsResource> documents = proxy.getGoogleDocsResources(username, password, Kind.DOCUMENT);
      Assert.assertFalse(documents.isEmpty());
      
      GDocsResource res = documents.get(0);

      log.info("Resource " + res);
      
      InputStream is = proxy.getResourceContent(res , Kind.DOCUMENT);
      Assert.assertNotNull(is);
      
      File file = new File("D://gdocs.pdf");
      
      if (!file.exists())
         file.createNewFile();
      
      FileOutputStream os =new FileOutputStream(file);
      
      IOUtils.copy(is, os);
      
      GDocsResource untyped = documents.get(1);

      log.info("Resource " + res);
      
      InputStream is2 = proxy.getResourceContent(untyped , Kind.NONE);
      Assert.assertNotNull(is);
      
      File file2 = new File("D://gdocs.txt");
      
      if (!file.exists())
         file.createNewFile();
      
      FileOutputStream os2 =new FileOutputStream(file2);
      
      IOUtils.copy(is2, os2);
   }
}