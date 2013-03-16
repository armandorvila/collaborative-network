package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Exception throw when an unlogged user is into the application.
 * 
 * @author armandorv
 * 
 */
@Portable
public class UnAuthenticatedUserException extends ServersideException
{
   private static final long serialVersionUID = 7196369735777197731L;

   public UnAuthenticatedUserException()
   {
      super();
   }

   public UnAuthenticatedUserException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public UnAuthenticatedUserException(String message)
   {
      super(message);
   }

}