package com.armandorv.cnpd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries(
{@NamedQuery(name = "Milestone.findAll", query = "Select m from Milestone m")})
@Entity
@Table(name = "milestone")
public class Milestone implements Serializable
{

   private static final long serialVersionUID = -4292808737283277520L;

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   private Date date;

   @ManyToOne
   private Project project;

   public Long getId()
   {
      return id;
   }

   protected void setId(Long id)
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

   public Project getProject()
   {
      return project;
   }

   public void setProject(Project project)
   {
      this.project = project;
   }

   @Override
   public String toString()
   {
      return "Milestone [id=" + id + ", name=" + name + ", date=" + date
            + "]";
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
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
      Milestone other = (Milestone) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

}
