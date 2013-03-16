package com.armandorv.cnpd.web.server.interceptor;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ServersideExceptionHandlerTest extends ArquillianSupport
{

   @Inject
   private InterceptedContextualInstance contextualInstance;
   
   @Test(expected = ServersideException.class)
   public void testHAndleExeption(){
      contextualInstance.doSomethingServerside();
   }
}