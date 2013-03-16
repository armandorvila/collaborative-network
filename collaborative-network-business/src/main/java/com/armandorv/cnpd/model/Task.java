package com.armandorv.cnpd.model;

import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "task")
@NamedQueries(
{@NamedQuery(name = "Task.findAll", query = "select t from Task t")})
public class Task implements Serializable
{
   private static final long serialVersionUID = 1559106570848063618L;

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   private Date start;

   private int hours;

   private int workedHours;

   private boolean completed;
   
   @ManyToOne(cascade = REFRESH)
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

   public Date getStart()
   {
      return start;
   }

   public void setStart(Date start)
   {
      this.start = start;
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

   public boolean isCompleted()
   {
      return completed;
   }

   public void setCompleted(boolean completed)
   {
      this.completed = completed;
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
      Task other = (Task) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      return "Task [id=" + id + ", name=" + name + ", start=" + start
            + ", hours=" + hours + ", workedHours=" + workedHours
            + ", completed=" + completed + "]";
   }

   public Project getProject()
   {
      return project;
   }

   public void setProject(Project project)
   {
      this.project = project;
   }

}