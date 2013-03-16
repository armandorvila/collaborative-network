package com.armandorv.cnpd.web.server.interceptor;

import javax.enterprise.context.Dependent;

@Dependent
public class InterceptedContextualInstance
{
   @HandleBooleanException
   public boolean doSomethingBoolean()
   {
      throw new RuntimeException();
   }

   @HandleServersideException
   public void doSomethingServerside()
   {
      throw new RuntimeException();
   }

}