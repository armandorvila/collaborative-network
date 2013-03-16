package com.armandorv.cnpd.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Model a vote of a concrete discussion. A vote is a weak entity, that means
 * votes hasn't identity by themselves.
 * 
 * @author armandorv
 * 
 */
@Embeddable
public class Vote implements Serializable
{
   private static final long serialVersionUID = 7717487525770238741L;

   private Long voterId;
   
   private String voterName;

   private String option;

   private String argument;
   
   public String getArgument()
   {
      return argument;
   }

   public void setArgument(String argument)
   {
      this.argument = argument;
   }

   public Long getVoterId()
   {
      return voterId;
   }

   public void setVoterId(Long voterId)
   {
      this.voterId = voterId;
   }

   public String getVoterName()
   {
      return voterName;
   }

   public void setVoterName(String voterName)
   {
      this.voterName = voterName;
   }

   public String getOption()
   {
      return option;
   }

   public void setOption(String option)
   {
      this.option = option;
   }
}