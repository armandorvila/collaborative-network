package com.armandorv.cnpd.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A object which model a project of business domain.
 * 
 * @author armandorv
 * 
 */
@NamedQueries(
{
      @NamedQuery(name = "Project.findAll", query = "select p from Project p"),
      @NamedQuery(name = "Project.findByArea", query = "select p from Project p where p.knowledgeArea = :knowledgeArea"),
      @NamedQuery(name = "Project.findByTitle", query = "select p from Project p where p.title = :title")})
@Table(name = "project")
@Entity
public class Project implements Serializable
{
   private static final long serialVersionUID = -6743202992210799015L;

   @Id
   @GeneratedValue
   private Long id;

   private String title;

   private String Description;

   private Boolean publisht;

   private String url;

   @Column(nullable = false)
   private Long managerId;

   @OneToOne
   private Milestone lastMilestone;

   @ManyToOne
   private KnowledgeArea knowledgeArea;

   @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
   private Set<Milestone> milestones = new HashSet<Milestone>();

   @OneToMany(cascade = CascadeType.REMOVE)
   @JoinTable(name = "project_resources")
   private Set<Resource> resources = new HashSet<Resource>();

   @OneToMany(cascade = CascadeType.REMOVE)
   @JoinTable(name = "project_drafts")
   private Set<Resource> drafts = new HashSet<Resource>();;

   @ManyToMany(cascade = CascadeType.REMOVE)
   @JoinTable(name = "project_references")
   private Set<Reference> references = new HashSet<Reference>();

   @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
   private Set<Discussion> discussions = new HashSet<Discussion>();

   @ManyToMany(mappedBy = "projects", cascade = CascadeType.REFRESH)
   private Set<User> members = new HashSet<User>();
   
   @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
   private Set<Task> tasks = new HashSet<Task>();

   /* *************** Add and Remove ******************** */

   public void addResource(Resource resource)
   {
      this.resources.add(resource);
   }

   public void removeResource(Resource resource)
   {
      this.resources.remove(resource);
   }

   public void addDraft(Resource draft)
   {
      this.drafts.add(draft);
   }

   public void removeDraft(Resource draft)
   {
      this.drafts.remove(draft);
   }

   public void addReference(Reference reference)
   {
      this.references.add(reference);
   }

   public void removeReference(Reference reference)
   {
      this.references.remove(reference);
   }
   
   public void removeReferences()
   {
      this.references.clear();
   }

   public void addMember(User user)
   {
      this.members.add(user);
   }

   public void removeMember(User user)
   {
      this.members.remove(user);
   }

   /* *************** Getters and setters ******************** */

   public Set<User> getMembers()
   {
      return Collections.unmodifiableSet(members);
   }

   public void setMembers(Set<User> members)
   {
      this.members = members;
   }

   public void setDiscussions(Set<Discussion> discussions)
   {
      this.discussions = discussions;
   }

   public Set<Discussion> getDiscussions()
   {
      return Collections.unmodifiableSet(discussions);
   }

   public Set<Task> getTasks()
   {
      return Collections.unmodifiableSet(tasks);
   }

   public void setTasks(Set<Task> tasks)
   {
      this.tasks = tasks;
   }

   public void setReferences(Set<Reference> references)
   {
      this.references = references;
   }

   public Set<Reference> getReferences()
   {
      return Collections.unmodifiableSet(references);
   }

   public void setDescription(String description)
   {
      Description = description;
   }

   public String getDescription()
   {
      return Description;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getTitle()
   {
      return title;
   }

   public Boolean isPublisht()
   {
      return publisht;
   }

   public void setPublisht(Boolean publisht)
   {
      this.publisht = publisht;
   }

   protected void setId(Long id)
   {
      this.id = id;
   }

   public Long getId()
   {
      return id;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public Set<Resource> getResources()
   {
      return Collections.unmodifiableSet(resources);
   }

   public void setResources(Set<Resource> resources)
   {
      this.resources = resources;
   }

   public Long getManagerId()
   {
      return managerId;
   }

   public void setManagerId(Long managerId)
   {
      this.managerId = managerId;
   }

   public Set<Milestone> getMilestones()
   {
      return Collections.unmodifiableSet(milestones);
   }

   public void setMilestones(Set<Milestone> milestones)
   {
      this.milestones = milestones;
   }

   public Milestone getLastMilestone()
   {
      return lastMilestone;
   }

   public void setLastMilestone(Milestone lastMilestone)
   {
      this.lastMilestone = lastMilestone;
   }

   public Set<Resource> getDrafts()
   {
      return Collections.unmodifiableSet(drafts);
   }

   public void setDrafts(Set<Resource> drafts)
   {
      this.drafts = drafts;
   }

   public KnowledgeArea getKnowledgeArea()
   {
      return knowledgeArea;
   }

   public void setKnowledgeArea(KnowledgeArea knowledgeArea)
   {
      this.knowledgeArea = knowledgeArea;
   }

   /* *************** Hash code, equals and toString.********************* */

   @Override
   public String toString()
   {
      return "Project [id=" + id + ", title=" + title + ", Description="
            + Description + ", externalReferences=" + references
            + ", discussions=" + discussions + ", members=" + members + "]";
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
      Project other = (Project) obj;
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