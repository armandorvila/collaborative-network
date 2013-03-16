package com.armandorv.cnpd.web.shared.model;

import java.io.Serializable;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * A presentation model class which take the project info to client side GWT
 * view
 * 
 * @author armandorv
 * 
 */
@Portable
public class ProjectInfo implements Serializable
{
   private static final long serialVersionUID = -6063471719076814387L;

   private long id;
   
   private long managerId;

   private String title;

   private String Description;

   private boolean published;

   private String url;
   
   private String lastMilestone;
   
   private String manager;

   private IdNameGenericInfo knowledgeArea;

   private List<ContactInfo> members;

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public long getManagerId()
   {
      return managerId;
   }

   public void setManagerId(long managerId)
   {
      this.managerId = managerId;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getDescription()
   {
      return Description;
   }

   public void setDescription(String description)
   {
      Description = description;
   }

   public boolean isPublished()
   {
      return published;
   }

   public void setPublished(boolean published)
   {
      this.published = published;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public void setMembers(List<ContactInfo> members)
   {
      this.members = members;
   }

   public List<ContactInfo> getMembers()
   {
      return this.members;
   }

   public IdNameGenericInfo getKnowledgeArea()
   {
      return knowledgeArea;
   }

   public void setKnowledgeArea(IdNameGenericInfo knowledgeArea)
   {
      this.knowledgeArea = knowledgeArea;
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
      ProjectInfo other = (ProjectInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

   public String getLastMilestone()
   {
      return lastMilestone;
   }

   public void setLastMilestone(String lastMilestone)
   {
      this.lastMilestone = lastMilestone;
   }

   public String getManager()
   {
      return manager;
   }

   public void setManager(String manager)
   {
      this.manager = manager;
   }

}