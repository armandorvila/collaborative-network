package com.armandorv.cnpd.business.exception;

/**
 * Exception thrown when a invalid message is sent. A invalid message could be :
 * <ul>
 * <li>A message with a invalid addressee.</li>
 * <li>A message with a invalid sender.</li>
 * <li>A message which sender.equals(addressee) is true.</li>
 * </ul>
 * 
 * @author armandorv
 * 
 */
public class InvalidMessageException extends BusinessException
{

   private static final long serialVersionUID = -6798135633061322599L;

   public InvalidMessageException(String message)
   {
      super(message);
   }

   public InvalidMessageException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
