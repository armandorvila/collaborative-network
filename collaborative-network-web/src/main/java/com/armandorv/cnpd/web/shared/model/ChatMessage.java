package com.armandorv.cnpd.web.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

/*
 * Model a chat message, is used to send events between server and client.
 */
@Portable
public class ChatMessage
{
   private String from;

   private String to;

   private String text;

   public ChatMessage()
   {
   }

   public ChatMessage(String from, String to, String text)
   {
      super();
      this.from = from;
      this.to = to;
      this.text = text;
   }

   public String getFrom()
   {
      return from;
   }

   public void setFrom(String from)
   {
      this.from = from;
   }

   public String getText()
   {
      return text;
   }

   public void setText(String text)
   {
      this.text = text;
   }

   @Override
   public String toString()
   {
      return "ChatMessage [senderMail=" + from + ", text=" + text + "]";
   }

   public String getTo()
   {
      return to;
   }

   public void setTo(String to)
   {
      this.to = to;
   }

}