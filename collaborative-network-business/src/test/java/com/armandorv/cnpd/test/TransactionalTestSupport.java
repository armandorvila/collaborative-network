package com.armandorv.cnpd.test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.Before;

/**
 * Class with elements to all DAO tests
 * 
 * @author armandorv
 * 
 */
public class TransactionalTestSupport extends ArquillianSupport
{

   @Inject
   UserTransaction utx;

   @Before
   public void preparePersistenceTest() throws Exception
   {
      utx.begin();
   }

   @After
   public void commitTransaction() throws Exception
   {
      utx.commit();
   }

}
