package com.armandorv.cnpd.model;

import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Model a meeting among a several users.
 * 
 * @author armandorv
 * 
 */
@Entity
@Table(name = "meeting")
@NamedQueries(
{
      @NamedQuery(name = "Meeting.findAll", query = "select m from Meeting m"),
      @NamedQuery(name = "Meeting.findByTitle", query = "select m from Meeting m where m.title = :title")})
public class Meeting implements Serializable
{

   private static final long serialVersionUID = -1109180456814644052L;

   @Id
   @GeneratedValue
   private Long id;

   private Boolean celebrated;

   private String title;

   private String description;

   private String place;

   private Date date;

   private String conclusion;

   private Long instigatorId;

   @ManyToMany(mappedBy = "meetings", cascade = REFRESH)
   private Set<User> participants;

   /* ******************** Add and Remove Methods ***************** */

   public void addParticipant(User participant)
   {
      this.participants.add(participant);
   }

   public void removeParticipant(User participant)
   {
      this.participants.remove(participant);
   }

   /* ******************** Getters and Setters ****************** */

   public Long getId()
   {
      return id;
   }

   protected void setId(Long id)
   {
      this.id = id;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public Set<User> getParticipants()
   {
      return Collections.unmodifiableSet(participants);
   }

   public void setParticipants(Set<User> participants)
   {
      this.participants = participants;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Boolean isCelebrated()
   {
      return celebrated;
   }

   public void setCelebrated(Boolean celebrated)
   {
      this.celebrated = celebrated;
   }

   public String getConclusion()
   {
      return conclusion;
   }

   public void setConclusion(String conclusion)
   {
      this.conclusion = conclusion;
   }

   public String getPlace()
   {
      return place;
   }

   public void setPlace(String place)
   {
      this.place = place;
   }

   public Long getInstigatorId()
   {
      return instigatorId;
   }

   public void setInstigatorId(Long instigatorId)
   {
      this.instigatorId = instigatorId;
   }

   /* **************** toString, HashCode and equeals.******************** */

   @Override
   public String toString()
   {
      return "Meeting [id=" + id + ", title=" + title + ", description="
            + description + ", date=" + date
            + ", participants=" + participants + "]";
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
      Meeting other = (Meeting) obj;
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