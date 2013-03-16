package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Higher level exception for exceptions thrown from server side code;
 * @author armandorv
 *
 */
@Portable
public class ServersideException extends PresentationException
{
   private static final long serialVersionUID = 8938302192496843181L;

   public ServersideException()
   {
      super();
   }

   public ServersideException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public ServersideException(String message)
   {
      super(message);
   }

}