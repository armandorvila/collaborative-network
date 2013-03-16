package com.armandorv.cnpd.business.impl.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.business.exception.BusinessException;
import com.armandorv.cnpd.persistence.exception.PersistenceException;

/**
 * AOP approach of Exception handling based on method intercepting, a subset of
 * Aspect oriented programming that is covered by JEE6 specifications set.
 * 
 * AOP Definitions : The interceptor operation is the advice, all interceptors
 * annotated with HandleBusinessException compound the aspect of Business
 * Exceptions Handling, and code points annotated with HandleBusinessException
 * are join points. JEE6 has'nt yet a mechanism to define point cuts with regx
 * or similar.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
@Interceptor
public class BusinessExceptionHandler
{
   private static Logger log = Logger.getLogger(BusinessExceptionHandler.class);

   @AroundInvoke
   public Object handleException(InvocationContext invocationContext)
         throws Exception
   {
      try
      {
         return invocationContext.proceed();
      }
      catch (BusinessException e)
      {
         throw e;
      }
      catch (PersistenceException e)
      {
         log.error(errorMessage(invocationContext, e));
         throw new BusinessException(errorMessage(invocationContext, e), e);
      }
      catch (Exception e)
      {
         log.error(errorMessage(invocationContext, e));
         throw new BusinessException(errorMessage(invocationContext, e), e);
      }
   }

   private String errorMessage(InvocationContext invocationContext, Exception e)
   {
      String message = "\n -- Error :" + e.getClass().getSimpleName() + " \n"
            + " -- Executing method :"
            + invocationContext.getMethod().getName() + " at "
            + invocationContext.getMethod().getDeclaringClass().getName()
            + " \n";

      message += " -- Parameters :";

      for (Object parm : invocationContext.getParameters())
         message += "Parameter [Tpye:" + parm.getClass().getSimpleName()
               + " Value :" + parm + "]";

      message += "\n -- Error message :" + e.getMessage();

      return message;
   }

}