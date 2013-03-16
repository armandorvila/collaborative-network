package com.armandorv.cnpd.web.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.armandorv.cnpd.web.shared.exception.ServersideException;


/** class to generate a ZIP from a list of files.
 * 
 * @author armandorv
 *
 */
public class ZipGenerator
{
   private static final int BUFFER_SIZE = 1024 * 4;

   public static File generateZip(String[] fileNames,
         String zipName)
   {
      File zip = null;
      
      try
      {
         if (fileNames == null || fileNames.length < 1
               || zipName == null)
         {
            throw new IllegalArgumentException("Illegeal file name, neither '' no null are allowed.");
         }

         List<File> files = new ArrayList<File>();

         for (String nombre : fileNames)
         {
            File file = new File(nombre);
            
            if (!file.exists())
               throw new RuntimeException("File does no exist. " + nombre);
            
            files.add(file);
         }

         zip = File.createTempFile(zipName + "_", ".zip");
         
         ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
         out.setLevel(Deflater.DEFAULT_COMPRESSION);

         for (File file : files)
         {
            byte[] buffer = new byte[BUFFER_SIZE];

            FileInputStream in = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
           
            int len;
            
            while ((len = in.read(buffer)) > 0)
            {
               out.write(buffer, 0, len);
            }
            
            out.closeEntry();
            in.close();

         }
         out.close();
         eliminarFicheros(files);
      }
      catch (Exception e)
      {
         new ServersideException("Error  creating zip.", e);
      }
      
      return zip;
   }

   /**
    * Delete a list of files.
    * 
    * @param files
    */
   private static void eliminarFicheros(List<File> files)
   {
      if (files == null || files.isEmpty())
         throw new IllegalArgumentException("files es null o está vacio");
      for (File f : files)
      {
         f.delete();
      }
   }
}
