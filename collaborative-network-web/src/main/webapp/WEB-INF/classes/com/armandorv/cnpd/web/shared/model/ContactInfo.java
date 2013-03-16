package com.armandorv.cnpd.web.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Presentation object which model an user contact of another user. It has less
 * information than @UserInfo class.
 * 
 * @author armandorv
 * 
 */
@Portable
public class ContactInfo
{
   private long id;

   private String name;

   private String lastname1;

   private String lastname2;

   private String fullName;

   private String gmailAddress;

   private boolean isContact;

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String name)
   {
      this.fullName = name;
   }

   public String getGmailAddress()
   {
      return gmailAddress;
   }

   public void setGmailAddress(String gmailAddress)
   {
      this.gmailAddress = gmailAddress;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getLastname1()
   {
      return lastname1;
   }

   public void setLastname1(String lastname1)
   {
      this.lastname1 = lastname1;
   }

   public String getLastname2()
   {
      return lastname2;
   }

   public void setLastname2(String lastname2)
   {
      this.lastname2 = lastname2;
   }

   public boolean isContact()
   {
      return isContact;
   }

   public void setContact(boolean isWithMe)
   {
      this.isContact = isWithMe;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((gmailAddress == null) ? 0 : gmailAddress.hashCode());
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
      ContactInfo other = (ContactInfo) obj;
      if (gmailAddress == null)
      {
         if (other.gmailAddress != null)
            return false;
      }
      else if (!gmailAddress.equals(other.gmailAddress))
         return false;
      return true;
   }

}