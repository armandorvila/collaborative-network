package com.armandorv.cnpd.web.server.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.web.shared.exception.ServersideException;

@Interceptor
@HandleServersideException
public class ServersideExceptionHandler extends AbstractExceptionHandler
{
   private static Logger log = Logger.getLogger(ServersideExceptionHandler.class);

   @AroundInvoke
   public Object handleException(InvocationContext invocationContext) throws Exception
   {
      try
      {
         return invocationContext.proceed();
      }
      catch (ServersideException e)
      {
         log.error(errorMessage(invocationContext, e));
         throw e;
      }

      catch (Exception e)
      {
         log.error(errorMessage(invocationContext, e));
         throw new ServersideException(errorMessage(invocationContext, e));
      }

   }
}