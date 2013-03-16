package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Exception thrown when problems are found getting the EJB proxies.
 * 
 * @author armandorv
 * 
 */
@Portable
public class EjbLockupException extends ServersideException
{
   private static final long serialVersionUID = 7333615969451898795L;

   public EjbLockupException()
   {
      super();
   }

   public EjbLockupException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public EjbLockupException(String message)
   {
      super(message);
   }

}