package com.armandorv.cnpd.business.impl;

import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.business.IResourcesManager;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.test.ArquillianSupport;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.pojo.Business;

@RunWith(Arquillian.class)
public class ResourcesManagerTest extends ArquillianSupport
{
   private static Logger log = Logger.getLogger(ResourcesManagerTest.class);

   @Inject
   private IResourcesManager resourcesManager;

   @Inject
   private IProjectsManager projectsManager;

   @Business
   @Inject
   private IPojoBuilder builder;

   private List<Project> projects;

   @Before
   public void before()
   {
      Assert.assertNotNull(projectsManager);
      Assert.assertNotNull(resourcesManager);

      projects = projectsManager.getAllProjects();
      Assert.assertNotNull(projects);
   }

   /* *********************** testGetResource **************************** */

   @Test
   public void testGetResource()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource resoure = all.iterator().next();
         Assert.assertNotNull(resoure);

         Resource retrieved = resourcesManager.getResource(resoure.getId());

         Assert.assertNotNull(retrieved);
         Assert.assertEquals(resoure, retrieved);
      }
      else
      {
         fail("There is not projects.");
      }
   }

   /* *********************** testGetResources **************************** */

   @Test
   public void testGetResources()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         log.info("Retrieves " + all.size() + " resources for project "
               + project.getTitle());
      }
      else
      {
         fail("There is not projects.");
      }
   }

   /* *********************** getDraftResources **************************** */

   @Test
   public void getDraftResources()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> drafts = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(drafts);

         log.info("Retrieves " + drafts.size() + " drafts for project "
               + project.getTitle());
      }
      else
      {
         fail("There is not projects.");
      }
   }

   /* *********************** testCreateResource **************************** */

   @Test
   public void testCreateResource()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         Resource newResource = builder.buildResource(ResourceKind.FOLDER);
         Assert.assertNotNull(newResource);

         Resource created = resourcesManager.createResource(project.getId(),
               -1L, newResource);

         Assert.assertNotNull(created);
         Assert.assertNotNull(created.getId());
         Assert.assertNotNull(created.getUrl());

         Set<Resource> allNow = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size(), allNow.size() - 1);

         log.info("Check subversion url :" + created.getUrl());
      }
      else
      {
         fail("There is not projects.");
      }
   }

   /* *********************** testCreateResource with parent******************* */

   @Test
   public void testCreateResourceWithParent()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource parent = all.iterator().next();
         Assert.assertNotNull(parent);

         if (parent.getKind().equals(ResourceKind.FOLDER))
         {
            Set<Resource> childs = parent.getChilds();
            Assert.assertNotNull(childs);

            Resource newResource = builder.buildResource(ResourceKind.FILE);
            Assert.assertNotNull(newResource);

            Resource created = resourcesManager.createResource(
                  project.getId(), parent.getId(), newResource);

            Assert.assertNotNull(created);
            Assert.assertNotNull(created.getId());
            Assert.assertNotNull(created.getUrl());

            Set<Resource> allNow = resourcesManager.getResources(project
                  .getId());

            Assert.assertNotNull(allNow);
            Assert.assertEquals(all.size(), allNow.size());

            parent = created.getParent();
            Assert.assertNotNull(parent);

            Assert.assertEquals(childs.size() + 1, parent.getChilds()
                  .size());

            log.info("Check subversion url :" + created.getUrl());
         }

      }
      else
      {
         fail("There is not projects.");
      }
   }

   /*
    * ****************** testCreateResourceAsDraft ***********
    */

   @Test
   public void testCreateResourceAsDraft()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(all);

         Resource newResource = builder.buildResource(ResourceKind.FOLDER);
         Assert.assertNotNull(newResource);

         Resource created = resourcesManager.createResourceAsDraft(
               project.getId(), -1L, newResource);

         Assert.assertNotNull(created);
         Assert.assertNotNull(created.getId());
         Assert.assertNotNull(created.getUrl());

         Set<Resource> allNow = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size(), allNow.size() - 1);

         log.info("Check subversion url :" + created.getUrl());
      }
      else
      {
         fail("There is not projects.");
      }

   }

   /* *********************** testCreateDraft with parent******************* */

   @Test
   public void testCreateDraftWithParent()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource parent = all.iterator().next();
         Assert.assertNotNull(parent);

         if (parent.getKind().equals(ResourceKind.FOLDER))
         {
            Set<Resource> childs = parent.getChilds();
            Assert.assertNotNull(childs);

            Resource newResource = builder.buildResource(ResourceKind.FILE);
            Assert.assertNotNull(newResource);

            Resource created = resourcesManager.createResource(
                  project.getId(), parent.getId(), newResource);

            Assert.assertNotNull(created);
            Assert.assertNotNull(created.getId());
            Assert.assertNotNull(created.getUrl());

            Set<Resource> allNow = resourcesManager
                  .getDraftResources(project.getId());

            Assert.assertNotNull(allNow);
            Assert.assertEquals(all.size(), allNow.size());

            parent = created.getParent();
            Assert.assertNotNull(parent);

            Assert.assertEquals(childs.size() + 1, parent.getChilds()
                  .size());

            log.info("Check subversion url :" + created.getUrl());
         }
      }
      else
      {
         fail("There is not projects.");
      }

   }

   /* *********************** renameResource **************************** */

   @Test
   public void renameResource()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getDraftResources(project
               .getId());
         Assert.assertNotNull(all);

         if (all.size() > 0)
         {
            Resource first = all.iterator().next();
            Assert.assertNotNull(first);

            String name = "changed";
            resourcesManager.renameResource(first.getId(), name);

            Resource changed = resourcesManager.getResource(first.getId());
            Assert.assertNotNull(changed);

            Assert.assertNotSame(changed.getName(), first.getName());
            Assert.assertEquals(changed.getName(), name);

         }
      }
      else
      {
         fail("There is not projects.");
      }
   }

   /* *********************** testDeleteResource **************************** */

   @Test
   public void testDeleteResource()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1L,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource toDelete = all.iterator().next();
         Assert.assertNotNull(toDelete);

         resourcesManager.deleteResource(project.getId(), toDelete.getId());

         Set<Resource> allNow = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(allNow);
         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteResourceWithParent()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1L,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource parent = all.iterator().next();
         Assert.assertNotNull(parent);

         Resource child = null;

         if (parent.getParent() == null)
         {
            child = resourcesManager.createResource(project.getId(),
                  parent.getId(),
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
            parent = resourcesManager.getResource(parent.getId());
         }

         int childs = parent.getChilds().size();

         resourcesManager.deleteResource(project.getId(), child.getId());

         parent = resourcesManager.getResource(parent.getId());

         int childsNow = parent.getChilds().size();
         Assert.assertEquals(childs - 1, childsNow);

      }
      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testDeleteResourceWithChilds()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project.getId());
         Assert.assertNotNull(all);

         if (all.isEmpty())
         {
            resourcesManager.createResource(project.getId(), -1L,
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         Resource toDelete = all.iterator().next();
         Assert.assertNotNull(toDelete);

         if (toDelete.getChilds().isEmpty())
         {
            resourcesManager.createResource(project.getId(),
                  toDelete.getId(),
                  builder.buildResource(ResourceKind.FOLDER));
            all = resourcesManager.getResources(project.getId());
         }

         resourcesManager.deleteResource(project.getId(), toDelete.getId());

         Set<Resource> allNow = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(allNow);
         Assert.assertEquals(all.size() - 1, allNow.size());

      }

      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testMoveResource()
   {
      if (projects.size() > 0)
      {
         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(all);

         if (all.size() > 1)
         {
            Resource first = all.iterator().next();
            Assert.assertNotNull(first);

            Resource newParent = null;

            Iterator<Resource> resources = all.iterator();

            while (newParent == null && resources.hasNext())
            {
               Resource temp = resources.next();
               if (temp.getKind().equals(ResourceKind.FOLDER) && !temp.equals(first))
               {
                  newParent = temp;
               }
            }

            Assert.assertNotNull(newParent);

            Resource moved = resourcesManager.moveResource(project.getId(), first.getId(), newParent.getId());
            Assert.assertNotNull(moved);

            Assert.assertEquals(moved, first);
            Assert.assertNotSame(moved.getUrl(), first.getUrl());
            Assert.assertNotSame(moved.getParent(), first.getParent());
            Assert.assertEquals(moved.getParent(), newParent);

            log.info("Old url " + first.getUrl());
            log.info("Current url " + moved.getUrl());
         }
         else
         {
           // fail("There is no resources.");
         }
      }
      else
      {
         fail("There is not projects.");
      }
   }

   @Test
   public void testMoveResourceToResources()
   {
      if (projects.size() > 0)
      {
         Project project = projectsManager.getProject(100);
         Assert.assertNotNull(project);

         Set<Resource> all = resourcesManager.getResources(project
               .getId());
         Assert.assertNotNull(all);

         if (all.size() > 0)
         {
            Resource oldParent = all.iterator().next();
            Assert.assertNotNull(oldParent);

            if (oldParent.getChilds().size() > 0)
            {
               Resource toMove = oldParent.getChilds().iterator().next();
               Assert.assertNotNull(toMove);

               Resource moved = resourcesManager.moveResourceToResources(project.getId(), toMove.getId());
               Assert.assertNotNull(moved);

               Assert.assertEquals(moved, toMove);
               Assert.assertNotSame(moved.getUrl(), toMove.getUrl());
               Assert.assertNotSame(moved.getParent(), toMove.getParent());
               Assert.assertEquals(moved.getParent(), null);

               log.info("Old url " + toMove.getUrl());
               log.info("Current url " + moved.getUrl());

            }
            else
            {
               fail("There is not childs." + oldParent.getId());
            }
         }
         else
         {
            fail("There is not projects.");
         }
      }

   }

}
