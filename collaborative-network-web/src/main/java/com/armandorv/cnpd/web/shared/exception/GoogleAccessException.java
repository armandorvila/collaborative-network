package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Exception thrown when there 's a problem consuming a google service.
 * 
 * @author armandorv
 * 
 */
@Portable
public class GoogleAccessException extends ServersideException
{
   private static final long serialVersionUID = -2576466996021871008L;

   public GoogleAccessException()
   {
      super();
   }

   public GoogleAccessException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public GoogleAccessException(String string)
   {
    super(string);
   }

}