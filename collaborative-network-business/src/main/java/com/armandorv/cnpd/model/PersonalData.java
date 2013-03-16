package com.armandorv.cnpd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalData implements Serializable
{
   private static final long serialVersionUID = -7472666052288158319L;

   private String name;

   private String surname1;

   private String surname2;

   private Date dateOfBirthday;

   private String webSite;

   private String city;

   public void setName(String name)
   {
      this.name = name;
   }

   public String getName()
   {
      return name;
   }

   public void setSurname1(String surname1)
   {
      this.surname1 = surname1;
   }

   public String getSurname1()
   {
      return surname1;
   }

   public void setSurname2(String surname2)
   {
      this.surname2 = surname2;
   }

   public String getSurname2()
   {
      return surname2;
   }

   public Date getDateOfBirthday()
   {
      return dateOfBirthday;
   }

   public void setDateOfBirthday(Date dateOfBirthday)
   {
      this.dateOfBirthday = dateOfBirthday;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getWebSite()
   {
      return webSite;
   }

   public void setWebSite(String webSite)
   {
      this.webSite = webSite;
   }

   @Override
   public String toString()
   {
      return "PersonalData [name=" + name + ", surname1=" + surname1
            + ", surname2=" + surname2 + ", dateOfBirthday="
            + dateOfBirthday + "]";
   }

}