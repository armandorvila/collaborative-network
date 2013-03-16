package com.armandorv.cnpd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Is the object value for the authentication model.
 * 
 * @author armandorv
 * 
 */
@Embeddable
public class AccountData implements Serializable
{

   private static final long serialVersionUID = -617034882284657214L;

   @Size(min = 4, max = 32)
   @Column(nullable = false, unique = true)
   private String account;

   @Size(min = 4, max = 32)
   @Column(nullable = false)
   private String password;

   private boolean expired;

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getPassword()
   {
      return password;
   }

   public void setAccount(String account)
   {
      this.account = account;
   }

   public String getAccount()
   {
      return account;
   }

   public boolean isExpired()
   {
      return expired;
   }

   public void setExpired(boolean expired)
   {
      this.expired = expired;
   }

   @Override
   public String toString()
   {
      return "GoogleAccount [account=" + account + ", password=" + password
            + ", expired=" + expired + "]";
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((account == null) ? 0 : account.hashCode());
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
      AccountData other = (AccountData) obj;
      if (account == null)
      {
         if (other.account != null)
            return false;
      }
      else if (!account.equals(other.account))
         return false;
      return true;
   }

}
