package com.armandorv.cnpd.business.exception;

/**
 * Get thrown when a invalid invitation is detected.
 * 
 * <ul>
 *    <li>A user is already into a project.</li>
 *    <li>A user is already invited to a project.</li>
 *    <li>A user is already into a meeting.</li>
 *    <li>A user is already invited to a meeting.</li>
 * </ul>
 * 
 * @author armandorv
 *
 */
public class InvalidInvitationException extends BusinessException
{

   private static final long serialVersionUID = -2664318550346192320L;

   public InvalidInvitationException(String message)
   {
      super(message);
      // TODO Auto-generated constructor stub
   }

}
