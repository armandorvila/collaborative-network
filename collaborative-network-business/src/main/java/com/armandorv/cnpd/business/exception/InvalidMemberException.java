package com.armandorv.cnpd.business.exception;

/**
 * Exception that get thrown when a invalid member is detected.
 * 
 * <ul>
 *     <li>When project manager is set and the given user is not a member of the project.</li>
 * </ul>
 * @author armandorv
 *
 */
public class InvalidMemberException extends BusinessException
{
   private static final long serialVersionUID = -5256758224265875145L;

   public InvalidMemberException(String message)
   {
      super(message);
   }

}
