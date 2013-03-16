package com.armandorv.cnpd.web.server.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

@Interceptor @HandleBooleanException
public class BooleanExceptionHandler extends AbstractExceptionHandler
{
   private static Logger log = Logger.getLogger(BooleanExceptionHandler.class);

   @AroundInvoke
   public Object handleException(InvocationContext invocationContext)
         throws Exception
   {
      try
      {
         return invocationContext.proceed();
      }
      catch (Exception e) {
         log.error(errorMessage(invocationContext, e), e);
         return false;
      }

   }
   
}