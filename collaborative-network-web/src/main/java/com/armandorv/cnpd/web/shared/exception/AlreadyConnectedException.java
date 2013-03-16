package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class AlreadyConnectedException extends ServersideException
{
   private static final long serialVersionUID = -7685690512169415678L;

   public AlreadyConnectedException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public AlreadyConnectedException(String message)
   {
      super(message);
   }

   public AlreadyConnectedException()
   {
      super();
   }

}