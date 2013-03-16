package com.armandorv.cnpd.web.server.interceptor;

import javax.interceptor.InvocationContext;

public class AbstractExceptionHandler
{
   protected String errorMessage(InvocationContext invocationContext, Exception e)
   {
      String message = "Error :" + e.getClass().getSimpleName() + " "
            + "executing method :"
            + invocationContext.getMethod().getName() + " at "
            + invocationContext.getMethod().getDeclaringClass().getName()
            + " \n";

      message += "Parameters :";

      for (Object parm : invocationContext.getParameters())
         message += "Parameter [Tpye:" + parm.getClass().getSimpleName()
               + " Value :" + parm + "]";

      message += "\nError message :" + e.getMessage();

      return message;
   }
}
