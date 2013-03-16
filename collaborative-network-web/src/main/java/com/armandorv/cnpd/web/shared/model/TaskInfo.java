package com.armandorv.cnpd.web.shared.model;

import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class TaskInfo
{
   private long id;

   private String name;

   private int hours;

   private int workedHours;

   private Date start;

   private boolean completed;

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

   public int getHours()
   {
      return hours;
   }

   public void setHours(int hours)
   {
      this.hours = hours;
   }

   public int getWorkedHours()
   {
      return workedHours;
   }

   public void setWorkedHours(int workedHours)
   {
      this.workedHours = workedHours;
   }

   public Date getStart()
   {
      return start;
   }

   public void setStart(Date start)
   {
      this.start = start;
   }

   public boolean isCompleted()
   {
      return completed;
   }

   public void setCompleted(boolean completed)
   {
      this.completed = completed;
   }

   public float getPercent()
   {
      return ((float)workedHours / (float)hours) * 100;
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
      TaskInfo other = (TaskInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}