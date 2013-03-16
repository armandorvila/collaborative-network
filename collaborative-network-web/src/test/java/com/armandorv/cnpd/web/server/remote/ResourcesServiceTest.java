package com.armandorv.cnpd.web.server.remote;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ResourcesServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(ResourcesServiceTest.class);

   @Inject
   private ResourcesService resourceService;

   private final Long projectId = Long.parseLong(ResourceBundle.getBundle("test").getString("test.project"));

   private String url = ResourceBundle.getBundle("test").getString("test.resource.url");

   private final String NEW_RES_NAME = "web tier res";

   @Test
   public void testGetResources()
   {
      List<ResourceInfo> resources = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resources);

      log.info("Project " + projectId + " has " + resources.size() + " resources.");

      List<ResourceInfo> drafts = resourceService.getResources(projectId, true);
      Assert.assertNotNull(drafts);

      log.info("Project " + projectId + " has " + drafts.size() + " drafts.");
   }

   @Test
   public void testCreateResource()
   {
      ResourceInfo res = new ResourceInfo();
      res.setName(NEW_RES_NAME);
      res.setKind(ResourceInfo.Kind.FOLDER);

      res = resourceService.createResource(projectId, -1 , res, false);
      Assert.assertNotNull(res);

      Assert.assertNotNull(res.getId());
      Assert.assertNotNull(res.getUrl());
      Assert.assertNotNull(res.getKind());
   }

   @Test
   public void testDeleteResource()
   {
      List<ResourceInfo> resources = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resources);

      if (resources.isEmpty())
         fail("Check data");

      ResourceInfo res = resources.get(0);
      Assert.assertNotNull(res);

      Assert.assertTrue(resourceService.deleteResource(projectId, res.getId(), false));

      List<ResourceInfo> resourcesNow = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resourcesNow);

      Assert.assertFalse(resourcesNow.contains(res));
   }

   @Test
   public void testRenameResource()
   {
      List<ResourceInfo> resources = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resources);

      if (resources.isEmpty())
         fail("Check data");

      ResourceInfo res = resources.get(0);
      Assert.assertNotNull(res);

      String rename = "web-tier-renamig";

      Assert.assertTrue(resourceService.renameResource(projectId, res.getId(), rename));

      List<ResourceInfo> resourcesNow = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resourcesNow);

      res = null;

      for (ResourceInfo resourceInfo : resourcesNow)
      {
         if (resourceInfo.getName().equals(rename))
            res = resourceInfo;
      }
      Assert.assertNotNull(res);
      Assert.assertEquals(rename, res.getName());
   }

   @Test
   public void testGetFileContent()
   {
      List<ResourceInfo> resources = resourceService.getResources(projectId, false);
      Assert.assertNotNull(resources);

      Assert.assertFalse(resources.isEmpty());

      ResourceInfo res = new ResourceInfo();
      res.setUrl(url);
      res.setKind(ResourceInfo.Kind.FILE);

      String content = resourceService.getFileContent(projectId, res);

      Assert.assertNotNull(content);

      log.info("Content of " + res.getId() + " is " + content);
   }

}