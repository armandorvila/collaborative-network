package com.armandorv.cnpd.business.exception;

/**
 * Exception thrown when a illegal resolution has occurred. A illegal resolution
 * could be :
 * 
 * <ul>
 * <li>Try to resolve a contact request for a user who hasn't a request of that
 * contact.</li>
 * <li>Try to resolve a project invitation for a user who hasn't a invitation
 * for that project.</li>
 * <li>Try to resolve a meeting invitation for a user who hasn't a invitation
 * for that meeting.</li>
 * </ul>
 * 
 * A projectId could match with a legal project but not with a legal project
 * invitation.
 * 
 * @author armandorv
 * 
 */
public class IllegalResolutionException extends BusinessException
{

   private static final long serialVersionUID = -5771738087546782203L;

   public IllegalResolutionException(String message)
   {
      super(message);
   }

}
