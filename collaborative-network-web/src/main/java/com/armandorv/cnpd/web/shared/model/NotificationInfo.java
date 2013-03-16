package com.armandorv.cnpd.web.shared.model;

import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Model a notification in the presentation model
 * 
 * @author armandorv
 * 
 */
@Portable
public class NotificationInfo
{

   public enum Kind {
      CONTACT_REQUEST, CONTACT_ACEPPTED, PROJECT_REQUEST, PROJECT_ACCEPTED, PROJECT_PUBLISHT,
   }

   private long id;

   private String name;

   private String message;

   private long object;

   private Kind kind;

   private Date date;

   private boolean isNew;

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public boolean isNew()
   {
      return isNew;
   }

   public void setNew(boolean isNew)
   {
      this.isNew = isNew;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public long getObject()
   {
      return object;
   }

   public void setObject(long object)
   {
      this.object = object;
   }

   public Kind getKind()
   {
      return kind;
   }

   public void setKind(Kind kind)
   {
      this.kind = kind;
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
      NotificationInfo other = (NotificationInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}
