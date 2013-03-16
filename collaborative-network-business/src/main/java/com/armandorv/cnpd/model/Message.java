package com.armandorv.cnpd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * An internal message of an concrete user to one of his contacts.
 * 
 * @author armandorv
 * 
 */
@Entity
@Table(name = "message")
@NamedQueries(
{
      @NamedQuery(name = "Message.findAll", query = "select m from Message m"),
      @NamedQuery(name = "Message.findBySender", query = "select m from Message m where m.sender = :sender")})
public class Message implements Serializable
{

   private static final long serialVersionUID = 8310575812029679174L;

   @Id
   @GeneratedValue
   private Long id;

   private String content;

   private Date date;

   private boolean mail;

   private boolean read;

   @ManyToOne(cascade = CascadeType.REFRESH)
   private User sender;

   @ManyToOne(cascade = CascadeType.REFRESH)
   private User addressee;

   public Long getId()
   {
      return id;
   }

   protected void setId(Long id)
   {
      this.id = id;
   }

   public String getContent()
   {
      return content;
   }

   public void setContent(String content)
   {
      this.content = content;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public User getSender()
   {
      return sender;
   }

   public void setSender(User sender)
   {
      this.sender = sender;
   }

   public User getAddressee()
   {
      return addressee;
   }

   public void setAddressee(User addressee)
   {
      this.addressee = addressee;
   }

   public boolean isMail()
   {
      return mail;
   }

   public void setMail(boolean mail)
   {
      this.mail = mail;
   }

   public boolean isRead()
   {
      return read;
   }

   public void setRead(boolean read)
   {
      this.read = read;
   }

   @Override
   public String toString()
   {
      return "Message [id=" + id + ", content=" + content + ", mail=" + mail
            + ", read=" + read + "]";
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
      Message other = (Message) obj;
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