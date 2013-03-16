package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Higher level exception for exceptions thrown from client side code.
 */
@Portable
public class ClientsideException extends PresentationException
{
   private static final long serialVersionUID = 5043746367569275291L;

   public ClientsideException()
   {
      super();
   }

   public ClientsideException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public ClientsideException(String message)
   {
      super(message);
   }

}