package com.armandorv.cnpd.business.impl.svn;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.enterprise.context.Dependent;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNCopySource;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.armandorv.cnpd.business.exception.InfrastructureException;

/**
 * Deal with subversion infrastructure, CNPD resources system is built on a
 * subversion repository called CNPD, repository has a directory for every
 * project.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class SvnHelper
{
   private static Logger log = Logger.getLogger(SvnHelper.class);

   private SVNClientManager clientlientManager;

   private SVNURL repo_url;

   public SvnHelper()
   {
      try
      {
         DAVRepositoryFactory.setup();

         repo_url = SVNURL.parseURIDecoded(SvnProperties.URL());

         ISVNAuthenticationManager authManager = SVNWCUtil
               .createDefaultAuthenticationManager(
                     SvnProperties.USERNAME(), SvnProperties.PASSWORD());

         ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

         clientlientManager = SVNClientManager.newInstance(options,
               authManager);
      }
      catch (SVNException e)
      {
         log.error("Error initializng svn access infraestructure.");

         throw new InfrastructureException(
               "Error initializng svn access infraestructure.");
      }
   }

   /**
    * Create a directory into CNPD subversion repository for a given name.
    * 
    * @param svnProjectName
    *            name of new folder in subversion.
    * @return the absolute URL of project in subversion.
    * 
    * @throws InfrastructureException
    *             when there is SVN problems.
    */
   public String createFolderAtRoot(String folderName)
   {
      try
      {
         SVNURL[] urls = new SVNURL[1];
         urls[0] = repo_url.appendPath(folderName, true);

         clientlientManager.getCommitClient().doMkDir(urls,
               "New Porject dirctory " + folderName);

         log.debug("Dir " + folderName + " was created.");

         return urls[0].toDecodedString();
      }
      catch (SVNException e)
      {
         log.error("Error creating svn  folder " + folderName + "-> "
               + e.getMessage());

         throw new InfrastructureException("Error creating svn  folder "
               + folderName, e);
      }

   }
   
   /**
    * Create a file at given URL.
    * 
    * @param url
    * @throws InfrastructureException
    *             when SVN or server file system problem is occurred.
    */
   public void createFile(String url)
   {
      try
      {
         SVNURL svnUrl = SVNURL.parseURIDecoded(url);
         SVNCommitClient client = clientlientManager.getCommitClient();

         File file = File.createTempFile(UUID.randomUUID().toString(),
               "cnpd");

         client.doImport(file, svnUrl, "new File", null, true, true,
               SVNDepth.INFINITY);

         log.debug("File " + url + " was created.");
      }
      catch (IOException e)
      {
         log.error("Error creating temp file for " + url + "-> "
               + e.getMessage());
         throw new InfrastructureException("Error creating temp file for "
               + url, e);
      }
      catch (SVNException e)
      {
         log.error("Error creating svn  folder " + url + "-> "
               + e.getMessage());
         throw new InfrastructureException("Error creating svn  folder "
               + url, e);
      }
   }

   /**
    * Create a folder at given URL.
    * 
    * @param url
    * @throws InfrastructureException
    *             when there is SVN problems.
    */
   public void createFolder(String url)
   {
      try
      {
         SVNURL[] urls = new SVNURL[1];
         urls[0] = SVNURL.parseURIDecoded(url);

         clientlientManager.getCommitClient().doMkDir(urls, "");

         log.debug("Resource " + url + " was created.");
      }
      catch (SVNException e)
      {
         log.error("Error creating svn  folder " + url + "-> "
               + e.getMessage());
         throw new InfrastructureException("Error creating svn  folder "
               + url, e);
      }
   }

   /**
    * Delete a file or folder from a given URL.
    * 
    * @param url
    *            URL of file or folder to delete.
    * @throws InfrastructureException
    *             when there is SVN problems.
    */
   public void delete(String url)
   {
      try
      {
         SVNURL[] urls = new SVNURL[1];
         urls[0] = SVNURL.parseURIDecoded(url);

         clientlientManager.getCommitClient().doDelete(urls,
               "Removing resource " + url);
      }
      catch (SVNException e)
      {
         log.error("Error deleting svn file or folder " + url + "-> "
               + e.getMessage());
         /* Cause is not serializable. */
         throw new InfrastructureException(
               "Error creating svn file or folder " + url
                     + " , cause message :" + e.getMessage());
      }

   }

   public void move(String resourceUrl, String newParentUrl)
   {
      try
      {
         log.info("Moving " + resourceUrl + " to " + newParentUrl);
         
         SVNCopySource[] sources = new SVNCopySource[1];
         sources[0] = new SVNCopySource(SVNRevision.HEAD, SVNRevision.HEAD, SVNURL.parseURIDecoded(resourceUrl));

         clientlientManager.getCopyClient()
               .doCopy(sources, SVNURL.parseURIDecoded(newParentUrl), true, false, false, "", null);
      }
      catch (SVNException e)
      {
         log.error("Error moving svn file or folder " + resourceUrl + "-> "
               + e.getMessage());
         /* Cause is not serializable. */
         throw new InfrastructureException(
               "Error moving svn file or folder " + resourceUrl
                     + " , cause message :" + e.getMessage());
      }
   }
}