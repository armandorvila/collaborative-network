package com.armandorv.cnpd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "notifiaction")
@NamedQueries(
{
      @NamedQuery(name = "Notification.findAll", query = "select n from Notification n"),
      @NamedQuery(name = "Notification.findByUser", query = "select n from Notification n where n.user=:user")})
public class Notification implements Serializable
{

   private static final long serialVersionUID = 1934510367303781619L;

   @Id
   @GeneratedValue
   private Long id;

   private Date date;

   private String message;

   private Long thirdPartId;

   private Boolean notified;

   @Enumerated(EnumType.STRING)
   private NotificationKind kind;

   @ManyToOne
   private User user;

   public Long getId()
   {
      return id;
   }

   protected void setId(Long id)
   {
      this.id = id;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public Boolean isNotified()
   {
      return notified;
   }

   public void setNotified(Boolean notified)
   {
      this.notified = notified;
   }

   public Long getThirdPartId()
   {
      return thirdPartId;
   }

   public void setThirdPartId(Long thirdPartId)
   {
      this.thirdPartId = thirdPartId;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public NotificationKind getKind()
   {
      return kind;
   }

   public void setKind(NotificationKind kind)
   {
      this.kind = kind;
   }

   @Override
   public String toString()
   {
      return "Notification [id=" + id + ", time=" + date + ", message="
            + message + ", notified=" + notified + ", user=" + user + "]";
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
      Notification other = (Notification) obj;
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
