package com.armandorv.cnpd.business.exception;

/**
 * Thrown when a invalid reference is created or given for an incorrecto operation.
 * 
 * Examples : 
 * <ul>
 *      <li> Remove a reference from a project that hasn't that reference.</li>
 * </ul>
 * 
 * @author armandorv
 * 
 */
public class InvalidReferenceException extends BusinessException
{

   private static final long serialVersionUID = -7260795574627672646L;

   public InvalidReferenceException(String message)
   {
      super(message);
   }

}
