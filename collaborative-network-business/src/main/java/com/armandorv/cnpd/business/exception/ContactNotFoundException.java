package com.armandorv.cnpd.business.exception;

/**
 * When a contact is not found in any contact list.
 * 
 * @author armandorv
 * 
 */
public class ContactNotFoundException extends BusinessException
{
   private static final long serialVersionUID = 117331190250706915L;

   public ContactNotFoundException(String message)
   {
      super(message);
   }

}