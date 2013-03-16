package com.armandorv.cnpd.test.util;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IUserDao;
import com.armandorv.cnpd.test.TransactionalTestSupport;

@Ignore
@RunWith(Arquillian.class)
public class RanodmCodeExecutor extends TransactionalTestSupport
{
   @Inject
   private IUserDao dao;

   @Test
   public void execute()
   {
      this.code();
   }

   public void code()
   {
      User user = dao.findById(288L);
      dao.remove(user);
   }
}
