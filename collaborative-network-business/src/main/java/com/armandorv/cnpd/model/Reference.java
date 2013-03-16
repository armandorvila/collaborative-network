package com.armandorv.cnpd.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * A web reference of a project, internal reference are projects, so this entity model only web references.
 */
@Entity
@Table(name = "reference")
@NamedQueries(
{@NamedQuery(name = "Reference.findAll", query = "select r from Reference r")})
public class Reference implements Serializable
{

   private static final long serialVersionUID = -3945671798669430097L;

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   private String url;

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
      Reference other = (Reference) obj;
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
      return "Referencia [id=" + id + ", name=" + name + ", url=" + url + "]";
   }

}