package com.armandorv.cnpd.business.impl.interceptor;

@HandleBusinessException
public class InterceptedManagedBean
{
   public void doSomething()
   {
      throw new RuntimeException("Testing exception.");
   }

   public void doSomething(String param)
   {
      throw new RuntimeException("Testing exception.");
   }

}
