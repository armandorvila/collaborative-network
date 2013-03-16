package com.armandorv.cnpd.web.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * Base class for all arquillian based tests.
 * 
 * @author armandorv
 * 
 */
public class ArquillianSupport
{
   /* ************** Packages to deploy *************** */
   private static String TEST = "com.armandorv.cnpd.web.test";

   private static String SHARED = "com.armandorv.cnpd.web.shared";

   private static String SERVER_GOOGLE = "com.armandorv.cnpd.web.server.google";

   private static String SERVER_REMOTE = "com.armandorv.cnpd.web.server.remote";

   private static String SERVER_MAPPER = "com.armandorv.cnpd.web.server.mapper";

   private static String SERVER_SMACK = "com.armandorv.cnpd.web.server.smack";

   private static String SERVER_EJB = "com.armandorv.cnpd.web.server.ejb";

   private static String SERVER_UTIL = "com.armandorv.cnpd.web.server.util";

   private static String SERVER_SVN = "com.armandorv.cnpd.web.server.svn";

   private static String SERVER_INTERCEPTOR = "com.armandorv.cnpd.web.server.interceptor";

   @Deployment
   public static JavaArchive createDeployment()
   {
      JavaArchive test = ShrinkWrap
            .create(JavaArchive.class, "cnpd-web-test.jar")
            .addPackages(true, TEST, SERVER_GOOGLE, SERVER_REMOTE, SERVER_MAPPER, SERVER_SMACK, SERVER_EJB,
                  SERVER_UTIL, SERVER_SVN, SHARED, SERVER_INTERCEPTOR)
            .addAsManifestResource("META-INF/jboss-deployment-structure.xml", "jboss-deployment-structure.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml")
            .addAsResource("ejb-names.properties", "ejb-names.properties")
            .addAsResource("gtalk.properties", "gtalk.properties").addAsResource("svn.properties", "svn.properties")
            .addAsResource("test.properties", "test.properties");

      return test;
   }
}