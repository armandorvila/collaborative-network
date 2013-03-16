package com.armandorv.cnpd.model;

import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * Pojo which model a discussion of a project, a project may have a lot of
 * discussions, but a discussion belongs only to one project. A discussion has
 * several options and several votes.
 * 
 * @author armandorv
 * 
 */
@Entity
@Table(name = "discussion")
@NamedQueries(
{
      @NamedQuery(name = "Discussion.findAll", query = "select d from Discussion d"),
      @NamedQuery(name = "Discussion.findByProject", query = "select d from Discussion d")})
public class Discussion implements Serializable
{

   private static final long serialVersionUID = -5145688237266435984L;

   @Id
   @GeneratedValue
   private Long id;

   private String title;

   private String description;

   private Boolean open;

   @ManyToOne(cascade = REFRESH)
   private Project project;

   @ElementCollection(fetch = FetchType.EAGER)
   @OrderColumn(name = "option_id")
   private List<Option> options;

   @ElementCollection(fetch = FetchType.EAGER)
   @OrderColumn(name = "vote_id")
   private List<Vote> votes;

   protected void setId(Long id)
   {
      this.id = id;
   }

   public Long getId()
   {
      return id;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getTitle()
   {
      return title;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getDescription()
   {
      return description;
   }

   public List<Option> getOptions()
   {
      return options;
   }

   public void setOptions(List<Option> options)
   {
      this.options = options;
   }

   public List<Vote> getVotes()
   {
      return votes;
   }

   public void setVotes(List<Vote> votes)
   {
      this.votes = votes;
   }

   public Project getProject()
   {
      return project;
   }

   public void setProject(Project project)
   {
      this.project = project;
   }

   public Boolean getOpen()
   {
      return open;
   }

   public void setOpen(Boolean open)
   {
      this.open = open;
   }

   @Override
   public String toString()
   {
      return "Discussion [id=" + id + ", title=" + title + ", description="
            + description + ", project=" + project + ", options=" + options
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
      Discussion other = (Discussion) obj;
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
