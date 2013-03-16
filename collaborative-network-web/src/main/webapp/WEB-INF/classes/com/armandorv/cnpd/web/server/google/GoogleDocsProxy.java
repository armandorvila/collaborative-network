package com.armandorv.cnpd.web.server.google;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.web.shared.exception.GoogleAccessException;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class GoogleDocsProxy
{
   private static Logger log = Logger.getLogger(GoogleDocsProxy.class);

   private DocsService docsService = new DocsService("CNPD_Docs_Service");

   public List<GDocsResource> getGoogleDocsResources(String username, String password, Kind kind)
   {
      if (!login(username, password))
      {
         throw new GoogleAccessException("Error login user against GDocs service : " + username + " " + password);
      }
      try
      {
         return getResources(kind);
      }
      catch (MalformedURLException e)
      {
         log.error("Error getting resources.", e);
         throw new GoogleAccessException("Error getting resources.", e);
      }
      catch (IOException e)
      {
         log.error("Error getting resources.", e);
         throw new GoogleAccessException("Error getting resources.", e);
      }
      catch (ServiceException e)
      {
         log.error("Error getting resources.", e);
         throw new GoogleAccessException("Error getting resources.", e);
      }
   }

   private boolean login(String username, String password)
   {
      try
      {
         docsService.setUserCredentials(username, password);
         return true;
      }
      catch (AuthenticationException e)
      {
         log.error("Error login against GDocsService", e);
         return false;
      }

   }

   private List<GDocsResource> getResources(Kind kind) throws IOException, ServiceException
   {
      URL url = URLUtils.url(kind);

      List<GDocsResource> resources = new ArrayList<GDocsResource>();

      DocumentListFeed docs = docsService.getFeed(url, DocumentListFeed.class);
      for (DocumentListEntry listEntry : docs.getEntries())
      {
         resources.add(new GDocsResource(listEntry.getResourceId(), listEntry.getTitle().getPlainText()));
      }
      return resources;
   }

   public InputStream getResourceContent(GDocsResource res, Kind kind)
   {
      try
      {
         String stringUrl = ((MediaContent) this.getDocsListEntry(res.getId()).getContent()).getUri();

         if (kind.equals(Kind.DOCUMENT))
            stringUrl += "&exportFormat=pdf";

         URL url = new URL(stringUrl);

         log.info("URL " + url.toString());

         return getFile(url);
      }
      catch (Exception e)
      {
         throw new GoogleAccessException("Errro getting resource " + res.getTitle() + "from Google Docs", e);
      }
   }

   private InputStream getFile(URL url) throws IOException, ServiceException
   {
      MediaContent mediaContent = new MediaContent();
      mediaContent.setUri(url.toString());
      MediaSource source = docsService.getMedia(mediaContent);

      return source.getInputStream();
   }

   public DocumentListEntry getDocsListEntry(String resourceId) throws IOException,
         MalformedURLException, ServiceException
   {
      URL url = URLUtils.url(Constants.URL_DEFAULT + Constants.URL_DOCLIST_FEED + "/" + resourceId);

      return docsService.getEntry(url, DocumentListEntry.class);
   }

}