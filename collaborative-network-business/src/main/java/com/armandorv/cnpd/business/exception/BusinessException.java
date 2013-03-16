package com.armandorv.cnpd.business.exception;

import javax.ejb.ApplicationException;

/**
 * Higher level exception of Business Tier.
 */
@ApplicationException(inherited = true, rollback = true)
public class BusinessException extends RuntimeException
{

   private static final long serialVersionUID = 4713001811392116908L;

   public BusinessException(String message)
   {
      super(message);
   }

   public BusinessException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
