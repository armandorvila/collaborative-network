package com.armandorv.cnpd.web.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class DiscussionInfo
{
   private long id;

   private String title;

   private String description;

   private boolean open;

   private List<IdNameGenericInfo> options = new ArrayList<IdNameGenericInfo>();
   
   private List<VoteInfo> votes = new ArrayList<VoteInfo>();

   public DiscussionInfo()
   {
   }

   public DiscussionInfo(long id, String title, String description)
   {
      this.id = id;
      this.title = title;
      this.description = description;
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
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public boolean isOpen()
   {
      return open;
   }

   public void setOpen(boolean open)
   {
      this.open = open;
   }

   public List<IdNameGenericInfo> getOptions()
   {
      return options;
   }

   public void setOptions(List<IdNameGenericInfo> options)
   {
      this.options = options;
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
      DiscussionInfo other = (DiscussionInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

   public List<VoteInfo> getVotes()
   {
      return votes;
   }

   public void setVotes(List<VoteInfo> votes)
   {
      this.votes = votes;
   }

}