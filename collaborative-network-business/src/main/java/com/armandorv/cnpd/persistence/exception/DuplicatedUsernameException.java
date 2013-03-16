package com.armandorv.cnpd.persistence.exception;

/**
 * Exception thrown when a duplicated username is detected.
 * @author armandorv
 *
 */
public class DuplicatedUsernameException extends PersistenceException
{

   private static final long serialVersionUID = -4208557127888940640L;

   public DuplicatedUsernameException(String message)
   {
      super(message);
   }

   public DuplicatedUsernameException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
