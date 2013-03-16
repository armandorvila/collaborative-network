package com.armandorv.cnpd.model;

import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class to wrap a draft resource of an project, a draft could be container of
 * another resources.
 * 
 * @author armandorv
 * 
 */
@NamedQueries(
{@NamedQuery(name = "Resource.findAll", query = "Select r from Resource r")})
@Entity
@Table(name = "resource")
public class Resource implements Serializable
{

   private static final long serialVersionUID = 8848320902367481695L;

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   @ManyToOne(cascade = REFRESH)
   private Resource parent;

   private String url;

   @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "parent")
   private Set<Resource> childs = new HashSet<Resource>();

   @Enumerated(EnumType.STRING)
   private ResourceKind kind;

   /* *************** Add and Remove Methods***************** */

   public void addChild(Resource child)
   {
      this.childs.add(child);
   }

   public void removeChild(Resource child)
   {
      this.childs.remove(child);
   }

   /* *************** Getters and Setters ***************** */

   public Long getId()
   {
      return id;
   }

   protected void setId(long id)
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

   public Resource getParent()
   {
      return parent;
   }

   public void setParent(Resource container)
   {
      this.parent = container;
   }

   public ResourceKind getKind()
   {
      return kind;
   }

   public void setKind(ResourceKind kind)
   {
      this.kind = kind;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public Set<Resource> getChilds()
   {
      return Collections.unmodifiableSet(childs);
   }

   public void setChilds(Set<Resource> childs)
   {
      this.childs = childs;
   }

   /* ********* HashCode, equals and toString ******* */

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
      Resource other = (Resource) obj;
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
      return "Resource [id=" + id + ", name=" + name + ", parent=" + parent
            + ", url=" + url + ", kind=" + kind + "]";
   }

}