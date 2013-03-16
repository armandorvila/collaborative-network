package com.armandorv.cnpd.business.exception;

/**
 * <p>
 * At business level there is a lot of operations which requieres an identifier
 * to get a real object, if the given identifier for any of that operations is
 * incorrect an instance of this exception is thrown.
 * </p>
 * 
 * <p>
 * For example:</br> usersManager.getUser(-23L) provoques an
 * NonExisistentIdException.
 * </p>
 * 
 * @author armandorv
 * 
 */
public class NonExisistentIdException extends BusinessException
{

   private static final long serialVersionUID = -9029779524762261526L;

   public NonExisistentIdException(String message)
   {
      super(message);
   }

}
