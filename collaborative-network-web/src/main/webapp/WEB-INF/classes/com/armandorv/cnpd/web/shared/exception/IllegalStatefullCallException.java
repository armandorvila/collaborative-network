package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Exception thrown when a method of a presentation statefull service is called
 * illegally .
 * 
 * @author armandorv
 * 
 */
@Portable
public class IllegalStatefullCallException extends ServersideException
{
   private static final long serialVersionUID = 1997190220658427909L;

   public IllegalStatefullCallException()
   {
      super();
   }

   public IllegalStatefullCallException(String message)
   {
      super(message);
   }

   public IllegalStatefullCallException(String message, Throwable cause)
   {
      super(message, cause);
   }

}