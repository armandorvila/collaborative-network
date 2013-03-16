package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

@RunWith(Arquillian.class)
public class ProjectDaoTest extends TransactionalTestSupport
{

   private static Logger log = Logger.getLogger(ProjectDaoTest.class);

   @Inject
   private IProjectDao projectDao;

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Test
   public void testPersist()
   {

      List<Project> all = projectDao.findAll();
      Assert.assertNotNull(all);

      Project newProject = builder.buildProject();
      Assert.assertNotNull(newProject);

      projectDao.persist(newProject);
      Assert.assertNotNull(newProject);
      Assert.assertNotNull(newProject.getId());

      List<Project> allNow = projectDao.findAll();
      Assert.assertNotNull(allNow);

      Assert.assertEquals(all.size() + 1, allNow.size());
   }

   @Test
   public void testFindAll()
   {

      List<Project> projects = projectDao.findAll();
      Assert.assertNotNull(projects);
      log.info(" Found " + projects.size() + " projects");
   }

   @Test
   public void testFindById()
   {

      List<Project> projects = projectDao.findAll();
      Assert.assertNotNull(projects);

      if (projects.size() > 0)
      {

         Project project = projects.get(0);
         Assert.assertNotNull(project);

         Project found = projectDao.findById(project.getId());
         Assert.assertNotNull(found);
         Assert.assertNotNull(found.getId());

         Assert.assertEquals(project, found);

      }
      else
      {
         fail("No projects found");
      }
   }

   @Test
   public void testUpdatePersistent()
   {

      List<Project> all = projectDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         final String title = "Changed";

         Project project = all.get(0);
         Assert.assertNotNull(project);

         project.setTitle(title);

         Project found = projectDao.findById(project.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(title, found.getTitle());

      }
      else
      {
         fail("There is no projects.");
      }
   }

   @Test
   public void testRemove()
   {
      List<Project> all = projectDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Project project = all.get(0);
         Assert.assertNotNull(project);

         projectDao.remove(project);

         List<Project> allNow = projectDao.findAll();
         Assert.assertNotNull(allNow);

         Assert.assertEquals(all.size() - 1, allNow.size());

      }
      else
      {
         fail("There is no milestones.");
      }
   }

   @Test
   public void testRefresh()
   {

      List<Project> all = projectDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {

         Project first = all.get(0);
         Assert.assertNotNull(first);

         String oldTitle = first.getTitle();
         String newtitle = "Temporary name";

         first.setTitle(newtitle);
         projectDao.refresh(first);

         Assert.assertNotSame(newtitle, first.getTitle());
         Assert.assertEquals(oldTitle, first.getTitle());

         log.info("Project after refresh Test :" + first);

      }
      else
      {
         fail(" !!! There is not projects into the bbdd !!!");
      }
   }

}