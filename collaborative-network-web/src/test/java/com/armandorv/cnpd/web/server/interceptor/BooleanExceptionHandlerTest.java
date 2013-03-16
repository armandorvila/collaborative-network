package com.armandorv.cnpd.web.server.interceptor;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class BooleanExceptionHandlerTest extends ArquillianSupport
{
   
   @Inject
   private InterceptedContextualInstance contextualInstance;
   
   @Test
   public void testHandleBooleanException(){
      Assert.assertFalse(contextualInstance.doSomethingBoolean());
   }

}