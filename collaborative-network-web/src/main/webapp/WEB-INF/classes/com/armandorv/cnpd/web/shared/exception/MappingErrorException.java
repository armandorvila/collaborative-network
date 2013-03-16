package com.armandorv.cnpd.web.shared.exception;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Thrown when an error is produced by a mapper.
 * 
 * @author armandorv
 * 
 */
@Portable
public class MappingErrorException extends ServersideException
{
   private static final long serialVersionUID = 8508700285680462455L;

   public MappingErrorException()
   {
      super();
   }

   public MappingErrorException(String message)
   {
      super(message);
   }

}