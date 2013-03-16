package com.armandorv.cnpd.web.server.security;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-security.xml")
public class EjbUserDetailsServiceTest
{
   private static Logger log = Logger.getLogger(EjbUserDetailsServiceTest.class);

   @Autowired
   private EJbUserDetailsService service;

   private final String username = ResourceBundle.getBundle("test").getString("test.username");

   @After
   public void setUp()
   {
      Assert.assertNotNull(service);
   }

   @Test
   //Do negative testing
   public void loadByUserNameTest()
   {
      UserDetails user = service.loadUserByUsername(username);
      Assert.assertNotNull("There is an user for google into db, check it.", user);

      Assert.assertEquals(user.getUsername(), username);
      Assert.assertNotNull(user.getPassword());

      log.info("User " + user.getUsername() + " pass = " + user.getPassword());
   }
}