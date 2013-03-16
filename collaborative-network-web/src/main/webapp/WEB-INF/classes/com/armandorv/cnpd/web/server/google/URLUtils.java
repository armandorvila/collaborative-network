package com.armandorv.cnpd.web.server.google;

import java.net.MalformedURLException;
import java.net.URL;

import com.armandorv.cnpd.web.shared.exception.GoogleAccessException;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;

public class URLUtils
{
   public static URL url(Kind kind) throws MalformedURLException
   {
      if (kind.equals(Kind.DOCUMENT))
      {
         return url(Constants.URL_DEFAULT + Constants.URL_DOCLIST_FEED + Constants.URL_CATEGORY_DOCUMENT);
      }
      else if (kind.equals(Kind.FOLDER))
      {
         String[] parameters = {Constants.PARAMETER_SHOW_FOLDERS};
         return url(Constants.URL_DEFAULT + Constants.URL_DOCLIST_FEED + Constants.URL_CATEGORY_FOLDER , parameters);
      }
      else if (kind.equals(Kind.NONE))
      {
         return url(Constants.URL_DEFAULT + Constants.URL_DOCLIST_FEED);
      }

      else
      {
         throw new GoogleAccessException("Invalid resource kind.");
      }

   }

   /**
    * Builds a URL from a patch.
    *
    * @param path the path to add to the protocol/host
    *
    * @throws MalformedURLException
    * @throws DocumentListException
    */
   public static URL url(String path) throws MalformedURLException
   {
      return url(path, null);
   }

   /**
    * Builds a URL with parameters.
    *
    * @param path the path to add to the protocol/host
    * @param parameters parameters to be added to the URL.
    *
    * @throws MalformedURLException
    * @throws DocumentListException
    */
   public static URL url(String path, String[] parameters)
         throws MalformedURLException
   {
      return url(Constants.DEFAULT_HOST, path, parameters);
   }

   /**
    * Builds a URL with parameters.
    *
    * @param domain the domain of the server
    * @param path the path to add to the protocol/host
    * @param parameters parameters to be added to the URL.
    *
    * @throws MalformedURLException
    * @throws DocumentListException
    */
   public static URL url(String domain, String path, String[] parameters)
         throws MalformedURLException
   {
      StringBuffer url = new StringBuffer();
      url.append("https://" + domain + Constants.URL_FEED + path);

      if (parameters != null && parameters.length > 0)
      {
         url.append("?");
         for (int i = 0; i < parameters.length; i++)
         {
            url.append(parameters[i]);
            if (i != (parameters.length - 1))
            {
               url.append("&");
            }
         }
      }

      return new URL(url.toString());
   }

}