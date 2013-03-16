package com.armandorv.cnpd.web.server.svn;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class SvnHelperTest
{
   private static Logger log = Logger.getLogger(SvnHelperTest.class);

   private final String resourceUrl = ResourceBundle.getBundle("test").getString("test.resource.url");

   private final String resourceParenturl = ResourceBundle.getBundle("test").getString("test.resource.parent.url");

   private SvnHelper helper = new SvnHelper();

   @Test
   public void testGetFileContent()
   {
      String content = helper.getFileContent(resourceUrl);
      Assert.assertNotNull(content);

      log.info("Content are :" + content);
   }

   @Test
   public void testModifyFileContent()
   {
      String content = helper.getFileContent(resourceUrl);
      Assert.assertNotNull(content);

      log.info("Content are :" + content);

      content += "\n added content";

      log.info("Content before commit :" + content);

      helper.modifyFileContent(resourceUrl, resourceParenturl, content);

      content = helper.getFileContent(resourceUrl);
      Assert.assertNotNull(content);

      log.info("Content are :" + content);
   }

}