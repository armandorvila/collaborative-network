package com.armandorv.cnpd.web.server.svn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

import javax.enterprise.context.Dependent;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNCommitPacket;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.armandorv.cnpd.web.shared.exception.ServersideException;

/**
 * Deal with subversion infrastructure, CNPD resources system is built on a
 * subversion repository called CNPD, the repository has a directory for every
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

   private String BASE_FOLDER = "D:/temp";

   private File tempFilesDirectory;

   public SvnHelper()
   {
      DAVRepositoryFactory.setup();

      ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(SvnProperties.USERNAME(),
            SvnProperties.PASSWORD());

      ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

      BASE_FOLDER = SvnProperties.CHECKOUTS_DIRECTORY();

      tempFilesDirectory = new File(SvnProperties.TEMP_FILES_DIRECTORY());

      clientlientManager = SVNClientManager.newInstance(options, authManager);
   }

   /**
    * Export a resource from SVN and parse their content to a String. If
    * resource if a folder an exception is thrown.
    * 
    * @param url
    *            file location at subversion repository.
    * @return the String with the content.
    */
   public String getFileContent(String url)
   {
      try
      {
         SVNURL svnUrl = SVNURL.parseURIDecoded(url);

         File temp = File.createTempFile("cnpd.web.", ".tmp", tempFilesDirectory);

         SVNRevision pegRevision = SVNRevision.HEAD;
         SVNRevision revision = SVNRevision.HEAD;

         clientlientManager.getUpdateClient().doExport(svnUrl, temp, pegRevision, revision, "native", true,
               SVNDepth.INFINITY);

         return IOUtils.toString(new FileInputStream(temp));
      }
      catch (IOException e)
      {
         log.error("Error procesing svn files locally : " + e.getMessage(), e);
         throw new ServersideException("Error procesing svn files : " + e.getMessage());
      }
      catch (SVNException e)
      {
         log.error("Error accesing to svn files : " + e.getMessage());
         throw new ServersideException();
      }

   }

   /**
    * Modify the content of a given file URL with the given String. The parent
    * URL is required to make the SVN commit.
    * 
    * @param url
    *            URL of resource.
    * @param parentUrl
    *            URL of parent resource of the project if their is not parent.
    * @param content
    *            the new content to of the resource.
    */
   public void modifyFileContent(String url, String parentUrl, String content)
   {
      try
      {
         SVNURL svnUrl = SVNURL.parseURIDecoded(url);

         SVNURL svnParentUrl = SVNURL.parseURIDecoded(parentUrl);

         File workingCopy = new File(BASE_FOLDER + StringsUtil.replacedInvalidChars(parentUrl));

         this.doUpdate(svnParentUrl, workingCopy);

         File toCommit = new File(BASE_FOLDER + StringsUtil.replacedInvalidChars(parentUrl) + "/"
               + StringsUtil.getResourceName(url));

         int tries = 10;
         boolean upload = false;

         while (!upload && tries > 0)
         {
            upload = this.doCommit(svnUrl, svnParentUrl, toCommit, content);
            tries--;
         }

         if (!upload)
            throw new ServersideException("Modification was imposible");
      }
      catch (IOException e)
      {
         throw new ServersideException();
      }
      catch (SVNException e)
      {
         log.error("Error accesing to svn files, probably URLs are invalid : " + e.getErrorMessage());
         throw new ServersideException();
      }
   }

   private void doUpdate(SVNURL svnParentUrl, File workingCopy)
   {
      try
      {
         clientlientManager.getUpdateClient().doCheckout(svnParentUrl, workingCopy, SVNRevision.HEAD, SVNRevision.HEAD,
               SVNDepth.INFINITY, false);

      }
      catch (SVNException e)
      {
         throw new ServersideException(e.getErrorMessage().getFullMessage());
      }
   }

   /**
    * Try commit over a working copy, if i out of date return false, if commit
    * is done successfully return true; out of date means that i have a lower
    * version than repository version. It 's posible that any user has done
    * commit at the same time.
    * 
    * @throws IOException
    *             if there is problems deal with local IO
    */
   private boolean doCommit(SVNURL svnUrl, SVNURL svnParentUrl, File toCommit, String content) throws IOException
   {
      try
      {
         SVNCommitClient commiter = clientlientManager.getCommitClient();

         SVNCommitPacket packet;

         InputStream in = new SequenceInputStream(new FileInputStream(toCommit), IOUtils.toInputStream(content));
         OutputStream out = new FileOutputStream(toCommit);
         IOUtils.copy(in, out);

         packet = commiter.doCollectCommitItems(new File[]
         {toCommit}, true, true, SVNDepth.INFINITY, null);

         SVNCommitInfo info = commiter.doCommit(packet, false, "subiendo ");

         log.debug("File commited :" + info);

         return true;
      }
      catch (SVNException e)
      {
         if (e.getErrorMessage().getErrorCode().getCode() == 160024)
            return false;

         else
            log.error("Error accesing to svn files, probably URLs are invalid : " + e.getErrorMessage());

         throw new ServersideException("Error accesing to svn files, probably URLs are invalid : "
               + e.getErrorMessage(), e);
      }

   }
}