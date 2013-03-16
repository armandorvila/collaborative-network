package com.armandorv.cnpd.business.exception;

/**
 * Thrown when there is infrastructure problems, like SVN connection problems.
 * 
 * @author armandorv
 * 
 */
public class InfrastructureException extends BusinessException
{

   private static final long serialVersionUID = -3540304599891835524L;

   public InfrastructureException(String message)
   {
      super(message);
   }

   public InfrastructureException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
