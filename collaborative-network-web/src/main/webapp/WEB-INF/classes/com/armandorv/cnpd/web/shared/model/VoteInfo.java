package com.armandorv.cnpd.web.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class VoteInfo
{
   private long userId;
   
   private String voterName;
   
   private String option;
   
   private String argument;

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(long userId)
   {
      this.userId = userId;
   }

   public String getOption()
   {
      return option;
   }

   public void setOption(String option)
   {
      this.option = option;
   }

   public String getArgument()
   {
      return argument;
   }

   public void setArgument(String argument)
   {
      this.argument = argument;
   }

   public String getVoterName()
   {
      return voterName;
   }

   public void setVoterName(String voterName)
   {
      this.voterName = voterName;
   }
   
   
}