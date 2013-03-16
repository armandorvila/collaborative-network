package com.armandorv.cnpd.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class ArquillianSupport
{

   private static String PACKAGE = "com.armandorv.cnpd";

   @Deployment
   public static JavaArchive createDeployment()
   {

      JavaArchive test = ShrinkWrap
            .create(JavaArchive.class, "cnpd-business-test.jar")
            .addPackages(true, PACKAGE)
            .addAsManifestResource("META-INF/persistence.xml",
                  "persistence.xml")
            .addAsManifestResource(
                  "META-INF/jboss-deployment-structure.xml",
                  "jboss-deployment-structure.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml")
            .addAsResource("log4j.xml", "log4j.xml")
            .addAsResource("strings.properties", "strings.properties")
            .addAsResource("svn.properties", "svn.properties")
            .addAsResource("mail.properties", "mail.properties");

      return test;

   }
}
