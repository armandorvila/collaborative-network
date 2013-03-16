package com.armandorv.cnpd.business.exception;

/**
 * Exception thrown when a request has been already done or when user requested
 * is already in requester contacts list.
 * 
 * @author armandorv
 * 
 */

public class InvalidRequestException extends BusinessException
{

   private static final long serialVersionUID = -3205187120683612844L;

   public InvalidRequestException(String message)
   {
      super(message);
   }

}
