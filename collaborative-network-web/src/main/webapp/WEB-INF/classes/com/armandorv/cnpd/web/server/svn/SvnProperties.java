package com.armandorv.cnpd.web.server.svn;

import java.util.ResourceBundle;

/**
 * Class that expose properties for SVN.
 * 
 * @author armandorv
 * 
 */
public class SvnProperties
{

   private static final String CHECKOUTS_DIRECTORY = "svn.checkouts.base_path";

   private static final String TEMP_FILES_DIRECTORY = "svn.temp_files.directory";

   private static final String USERNAME = "svn.username";

   private static final String PASSWORD = "svn.password";

   /**
    * Resources bundle point to svn.properties
    */
   private static ResourceBundle bundle = ResourceBundle.getBundle("svn");

   /* *************** Statics methods to resources access **************** */

   public static String CHECKOUTS_DIRECTORY()
   {
      return bundle.getString(CHECKOUTS_DIRECTORY);
   }

   public static String TEMP_FILES_DIRECTORY()
   {
      return bundle.getString(TEMP_FILES_DIRECTORY);
   }

   public static String USERNAME()
   {
      return bundle.getString(USERNAME);
   }

   public static String PASSWORD()
   {
      return bundle.getString(PASSWORD);
   }

}
