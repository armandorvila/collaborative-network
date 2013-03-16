package com.armandorv.cnpd.web.shared.model;

import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * The info necessary to show about a message.
 * 
 * @author armandorv
 * 
 */
@Portable
public class MessageInfo
{

   private long id;

   private String sender;

   private String to;

   private String content;

   private Date date;

   private boolean read;

   public long getId()
   {
      return id;
   }

   public void setId(long id)
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

   public String getSender()
   {
      return sender;
   }

   public void setSender(String sender)
   {
      this.sender = sender;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public void setTo(String to)
   {
      this.to = to;
   }

   public String getTo()
   {
      return to;
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
      MessageInfo other = (MessageInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}
