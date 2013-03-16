package com.armandorv.cnpd.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Model a Knowledge area like Computer Sciences or Biology, is more generic
 * than a subject.
 * 
 * @author armandorv
 * 
 */
@NamedQueries(
{@NamedQuery(name = "KnowledgeArea.findAll", query = "select ka from KnowledgeArea ka")})
@Entity
@Table(name = "knowledge_area")
public class KnowledgeArea implements Serializable
{

   private static final long serialVersionUID = 4679710587558402991L;

   @Id
   @GeneratedValue
   private Long id;

   private String name;

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

   @Override
   public String toString()
   {
      return "KnowledgeArea [id=" + id + ", name=" + name + "]";
   }

}
