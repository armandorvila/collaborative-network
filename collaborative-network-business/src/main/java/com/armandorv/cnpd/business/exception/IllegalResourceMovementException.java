package com.armandorv.cnpd.business.exception;

/**
 * Get thrown when a resource is moved incorrectly.
 * A resource can be moved :
 * <ul>
 *      <li>From a parent to another parent.</li>
 *      <li>From a resources root to another parent.</li>
 *      <li>From a drafts root to another parent.</li>
 *      <li>From a parent to resources root.</li>
 *      <li>From a parent to drafts root.</li>
 * </ul>
 * 
 * 
 * @author armandorv
 *
 */
public class IllegalResourceMovementException extends BusinessException
{
   private static final long serialVersionUID = -8808027632727677768L;

   public IllegalResourceMovementException(String message)
   {
      super(message);
   }
}
