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

import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.shared.remote.ReferencesService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ReferencesServiceTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(ReferencesServiceTest.class);

   private final String userId = ResourceBundle.getBundle("test").getString("test.user");
   
   @Inject
   private ProjectsService projectsService;
   
   @Inject
   private ReferencesService referencesService;

   @Test
   public void testGetReferences()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<ReferenceInfo> references = referencesService.getReferences(info.getId());
      Assert.assertNotNull(references);
      Assert.assertFalse(references.isEmpty());
   }

   @Test
   public void testAddNewReference()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());

      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);

      List<ReferenceInfo> references = referencesService.getReferences(info.getId());
      Assert.assertNotNull(references);

      ReferenceInfo ref = new ReferenceInfo();
      ref.setName("Name");
      ref.setUrl("name.com");

      ref.setId(referencesService.addNewReference(info.getId(), ref));

      List<ReferenceInfo> referencesNow = referencesService.getReferences(info.getId());
      Assert.assertNotNull(referencesNow);
      Assert.assertFalse(referencesNow.isEmpty());

      Assert.assertEquals(references.size() + 1, referencesNow.size());
      Assert.assertTrue(referencesNow.contains(ref));
   }

   @Test
   public void testAddReference()
   {
  
   }

   @Test
   public void testRemoveReference()
   {
      fail("Not yet implemented");
   }

   @Test
   public void testSearchReferences()
   {
      List<ProjectInfo> projects = projectsService.getProjects(Long.parseLong(userId));
      Assert.assertNotNull(projects);

      Assert.assertFalse(projects.isEmpty());
      
      ProjectInfo info = projects.get(0);
      Assert.assertNotNull(info);
      
      log.info("Testing search references project :" + info.getId());
      
      List<ReferenceInfo> references = referencesService.searchReferences(info.getId(), "Testing");
      Assert.assertNotNull(references);
      Assert.assertEquals(1 ,references.size());
      
      references = referencesService.searchReferences(info.getId(), "Testing Testing");
      Assert.assertNotNull(references);
      Assert.assertEquals(1 ,references.size());
      
      references = referencesService.searchReferences(info.getId(), "edsdsd");
      Assert.assertNotNull(references);
      Assert.assertEquals(1 ,references.size());
      
      references = referencesService.searchReferences(info.getId(), "edsd");
      Assert.assertNotNull(references);
      Assert.assertEquals(1 ,references.size());
      
      references = referencesService.searchReferences(info.getId(), "Test");
      Assert.assertNotNull(references);
      
      references = referencesService.searchReferences(info.getId(), "Test ed");
      Assert.assertNotNull(references);
      Assert.assertEquals(2 ,references.size());
      
      log.info("Found " + references);
      
      references = referencesService.searchReferences(info.getId(), "");
      Assert.assertTrue(references.isEmpty());
      
      references = referencesService.searchReferences(info.getId(), "Name");
      Assert.assertTrue(references.isEmpty());
   }

}