package com.armandorv.cnpd.persistence.exception;

/**
 * Higher level Exception for my persistence problems.
 * @author armandorv
 *
 */
public class PersistenceException extends RuntimeException
{

   private static final long serialVersionUID = 852157880888080264L;

   public PersistenceException(String message, Throwable cause)
   {
      super(message, cause);

   }

   public PersistenceException(String message)
   {
      super(message);
   }

}
