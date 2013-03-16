package com.armandorv.cnpd.web.server.svn;

public class StringsUtil
{

   public static String getResourceName(String url)
   {

      String[] segments = url.split("/");
      return segments[segments.length - 1];
   }

   public static String replacedInvalidChars(String parentUrl)
   {

      String res = parentUrl.replace('\\', '.');
      res = res.replace('/', '.');
      res = res.replace(':', '.');
      res = res.replace('*', '.');
      res = res.replace('?', '.');
      res = res.replace('"', '.');
      res = res.replace('<', '.');
      res = res.replace('>', '.');
      res = res.replace('<', '.');
      res = res.replace('|', '.');

      return res;
   }
}
