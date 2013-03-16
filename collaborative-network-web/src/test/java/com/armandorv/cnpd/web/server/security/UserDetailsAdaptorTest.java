package com.armandorv.cnpd.web.server.security;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.User;

public class UserDetailsAdaptorTest
{
   private static final String username = "chito";

   private static final String password = "chito";

   private static UserDetailsAdaptor target = null;

   @BeforeClass
   public static void setuAll()
   {
      User user = new User();
      AccountData googleAccount = new AccountData();
      googleAccount.setAccount(username);
      googleAccount.setPassword(password);
      user.setGoogleAccount(googleAccount);
      target = new UserDetailsAdaptor(user);
   }

   @Test
   public void testGetUserName()
   {
      Assert.assertEquals("Username must be:" + username, username, target.getUsername());
   }

   @Test
   public void testGetPassword()
   {
      Assert.assertEquals("Password must be:" + password, password, target.getPassword());
   }

}