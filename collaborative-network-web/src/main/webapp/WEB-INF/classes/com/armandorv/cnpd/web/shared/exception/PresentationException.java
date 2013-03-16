package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Base exception for all exceptions thrown at the Presentation level. It 's
 * recommended use more specific exceptions. No parameters constructor was
 * included for errai marshaling by is not recommended, therefore it was
 * deprecated.
 * 
 * @author armandorv
 * 
 */
@Portable
public class PresentationException extends RuntimeException
{
   private static final long serialVersionUID = -354453383366713499L;

   public PresentationException()
   {
      super();
   }

   public PresentationException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public PresentationException(String message)
   {
      super(message);
   }

}