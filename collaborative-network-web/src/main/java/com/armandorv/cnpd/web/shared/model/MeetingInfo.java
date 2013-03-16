package com.armandorv.cnpd.web.shared.model;

import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class MeetingInfo
{
   private long id;
   
   private String title;
   
   private String place;
   
   private String description;
   
   private Date date;
   
   private boolean celebrated;
   
   private String conclusions;
   
   private long instigatorId;

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getPlace()
   {
      return place;
   }

   public void setPlace(String place)
   {
      this.place = place;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public boolean isCelebrated()
   {
      return celebrated;
   }

   public void setCelebrated(boolean celebrated)
   {
      this.celebrated = celebrated;
   }

   public String getConclusions()
   {
      return conclusions;
   }

   public void setConclusions(String conclusions)
   {
      this.conclusions = conclusions;
   }

   public long getInstigatorId()
   {
      return instigatorId;
   }

   public void setInstigatorId(long instigatorId)
   {
      this.instigatorId = instigatorId;
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
      MeetingInfo other = (MeetingInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }
   
   
}