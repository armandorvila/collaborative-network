package com.armandorv.cnpd.web.shared.model;

import java.util.Date;
import java.util.List;
import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Model the info of an user of my domain, it is a presentation class extract
 * from User class.
 * 
 * @author armandorv
 * 
 */
@Portable
public class UserInfo
{
   private String name;

   private long id;

   private String lastname1;

   private String lastname2;

   private String fullName;

   private String username;

   private Date birthday;

   private String city;

   private List<IdNameGenericInfo> degrees;

   private List<IdNameGenericInfo> jobs;

   private String webSite;

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

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getWebSite()
   {
      return webSite;
   }

   public void setWebSite(String webSite)
   {
      this.webSite = webSite;
   }

   public Date getBirthday()
   {
      return birthday;
   }

   public void setBirthday(Date birthday)
   {
      this.birthday = birthday;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public List<IdNameGenericInfo> getDegrees()
   {
      return degrees;
   }

   public void setDegrees(List<IdNameGenericInfo> degrees)
   {
      this.degrees = degrees;
   }

   public List<IdNameGenericInfo> getJobs()
   {
      return jobs;
   }

   public void setJobs(List<IdNameGenericInfo> jobs)
   {
      this.jobs = jobs;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
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
      UserInfo other = (UserInfo) obj;
      if (id != other.id)
         return false;
      return true;
   }

}