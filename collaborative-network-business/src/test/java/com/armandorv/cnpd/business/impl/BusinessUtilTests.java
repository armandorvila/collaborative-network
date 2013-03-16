package com.armandorv.cnpd.business.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.cnpd.business.impl.interceptor.BusinessExceptionHandlerTest;
import com.armandorv.cnpd.business.impl.mail.MailHelperTest;
import com.armandorv.cnpd.business.impl.svn.SvnHelperNoContainerTest;
import com.armandorv.cnpd.business.impl.svn.SvnHelperTest;
import com.armandorv.cnpd.business.impl.util.FindByExecutorTest;

@RunWith(Suite.class)
@SuiteClasses(
{BusinessExceptionHandlerTest.class, MailHelperTest.class, SvnHelperNoContainerTest.class, SvnHelperTest.class,
      FindByExecutorTest.class})
public class BusinessUtilTests
{

}
