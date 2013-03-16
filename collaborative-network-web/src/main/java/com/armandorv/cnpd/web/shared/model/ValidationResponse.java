package com.armandorv.cnpd.web.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Class to respond client side remote validation questions.
 * 
 * @author armandorv
 * 
 */
@Portable
public class ValidationResponse
{

   private boolean positive;

   private String message;

   public ValidationResponse()
   {
      positive = false;
      message = "";
   }

   public ValidationResponse(boolean positive, String message)
   {
      super();
      this.positive = positive;
      this.message = message;
   }

   public boolean isPositive()
   {
      return positive;
   }

   public void setPositive(boolean positive)
   {
      this.positive = positive;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   @Override
   public String toString()
   {
      return "ValidationResponse [positive=" + positive + ", message=" + message + "]";
   }

}
