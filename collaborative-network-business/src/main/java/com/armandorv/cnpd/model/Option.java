package com.armandorv.cnpd.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * It is a option of a discussion.
 * 
 * @author armandorv
 * 
 */
@Embeddable
public class Option implements Serializable
{

   private static final long serialVersionUID = 2863335688505755042L;

   private String name;

   private String description;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
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
      Option other = (Option) obj;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      return true;
   }

}
