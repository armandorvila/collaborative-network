package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class UnconnectedUserException extends ServersideException
{
   private static final long serialVersionUID = -7755006516199647316L;

   public UnconnectedUserException()
   {
      super();
   }

   public UnconnectedUserException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public UnconnectedUserException(String message)
   {
      super(message);
   }

}