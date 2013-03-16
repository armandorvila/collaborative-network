package com.armandorv.cnpd.web.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Presentation class to model a reference.
 * 
 * @author armandorv
 * 
 */
@Portable
public class ReferenceInfo
{

   private long id;

   private String name;

   private String url;

   public ReferenceInfo()
   {
   }

   public ReferenceInfo(String name, String url)
   {
      this.name = name;
      this.url = url;
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ReferenceInfo other = (ReferenceInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}
