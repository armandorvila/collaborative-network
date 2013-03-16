package com.armandorv.cnpd.web.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.cnpd.web.server.ejb.EJBProducerTest;
import com.armandorv.cnpd.web.server.ejb.JNDIHelperTest;
import com.armandorv.cnpd.web.server.google.GoogleContactsProxyTest;
import com.armandorv.cnpd.web.server.google.GoogleDocsProxyTest;
import com.armandorv.cnpd.web.server.interceptor.BooleanExceptionHandlerTest;
import com.armandorv.cnpd.web.server.interceptor.ServersideExceptionHandlerTest;
import com.armandorv.cnpd.web.server.security.EjbUserDetailsServiceTest;
import com.armandorv.cnpd.web.server.security.UserDetailsAdaptorTest;
import com.armandorv.cnpd.web.server.smack.SmackSupportTest;
import com.armandorv.cnpd.web.server.svn.SvnHelperTest;

@RunWith(Suite.class)
@SuiteClasses(
{EJBProducerTest.class, JNDIHelperTest.class,
      GoogleContactsProxyTest.class, GoogleDocsProxyTest.class, BooleanExceptionHandlerTest.class,
      ServersideExceptionHandlerTest.class
      , UserDetailsAdaptorTest.class, EjbUserDetailsServiceTest.class, SmackSupportTest.class, SvnHelperTest.class})
public class PresentationUtilsTests
{

}
