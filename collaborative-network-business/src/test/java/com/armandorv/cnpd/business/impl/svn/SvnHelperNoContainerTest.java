package com.armandorv.cnpd.business.impl.svn;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.armandorv.cnpd.business.exception.InfrastructureException;

public class SvnHelperNoContainerTest
{
   private static Logger log = Logger.getLogger(SvnHelperNoContainerTest.class);

   private final String ROOT_FOLDER_NAME = "test_create_root_folder";

   private final String ROOT_FOLDER_URL = "https://localhost/svn/cnpd_test/test_create_root_folder/";

   private final String FILE_URL = "https://localhost/svn/cnpd_test/test_create_root_folder/test_create_file";

   private final String FOLDER_URL = "https://localhost/svn/cnpd_test/test_create_root_folder/test_create_folder";

   private final String INEXISTENT_FOLDER_URL = "https://localhost/svn/cnpd_test/test_create_root_folder/inexistent";

   private SvnHelper helper = null;

   @Before
   public void before()
   {
      try
      {
         helper = new SvnHelper();
         Assert.assertNotNull(helper);
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }
   
   @Test
   public void testCreateFolderAtRoot()
   {
      try
      {
         String url = helper.createFolderAtRoot(ROOT_FOLDER_NAME);
         Assert.assertNotNull(url);

         log.info("Created " + url);
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }

   @Test
   public void testCreateFile()
   {
      try
      {
         helper.createFile(FILE_URL);
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }

   @Test
   public void testCreateFolder()
   {
      try
      {
         helper.createFolder(FOLDER_URL);
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }

   @Test
   public void testMove()
   {
      try
      {
         helper.move(FILE_URL, FOLDER_URL);
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }

   @Test
   public void testDelete()
   {
      try
      {
         helper.delete(FOLDER_URL);
         helper.delete(ROOT_FOLDER_URL);
         //helper.delete(FILE_URL); it was moved to folder url
      }
      catch (InfrastructureException e)
      {
         fail(e.getMessage());
      }
   }

   /* **************** Negative testing ******************** */

   @Test(expected = InfrastructureException.class)
   public void createFileMalformedUrl()
   {
      helper.createFile("throw new InfrastructureException()");
   }

   @Test(expected = InfrastructureException.class)
   public void createFolderMalformedUrl()
   {
      helper.createFolder("throw new InfrastructureException()");
   }

   @Test(expected = InfrastructureException.class)
   public void deleteMalformedUrl()
   {
      helper.delete("throw new InfrastructureException()");
   }

   @Test(expected = InfrastructureException.class)
   public void deleteInexistent()
   {
      helper.delete(INEXISTENT_FOLDER_URL);
   }

}