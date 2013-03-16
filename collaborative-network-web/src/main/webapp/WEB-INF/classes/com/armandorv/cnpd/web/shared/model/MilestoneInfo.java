package com.armandorv.cnpd.web.shared.model;

import java.util.Date;
import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class MilestoneInfo
{

   private long id;

   private String name;

   private Date date;

   public MilestoneInfo()
   {

   }

   public MilestoneInfo(long id, String name, Date date)
   {
      super();
      this.id = id;
      this.name = name;
      this.date = date;
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

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
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
      MilestoneInfo other = (MilestoneInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}
