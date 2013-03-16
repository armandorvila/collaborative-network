package com.armandorv.cnpd.business.impl.interceptor;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.exception.BusinessException;
import com.armandorv.cnpd.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class BusinessExceptionHandlerTest extends ArquillianSupport
{
   @Inject
   private InterceptedManagedBean bean;
   
   @Test(expected = BusinessException.class)
   public void testHandling()
   {
      bean.doSomething();
   }

   @Test(expected = BusinessException.class)
   public void testHandlingWithParameters()
   {
      bean.doSomething("testing pram.");
   }
}