package com.armandorv.cnpd.web.server.security;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for my custom implementation of UserDetailsService, it 's necessary business tier deployed.
 * @author armandorv
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-security.xml")
public class EjbUserDetailsServiceTest {

	private static Logger log = Logger.getLogger(EjbUserDetailsServiceTest.class);
	
	@Autowired
	private EJbUserDetailsService service;

	private String username = "google";

	@After
	public void setUp() {
		Assert.assertNotNull("Service Null", service);
	}

	@Test
	public void loadByUserNameTest() {

		UserDetails user = service.loadUserByUsername(username);
		Assert.assertNotNull("There is an user for google into db, check it.",
				user);
		
		Assert.assertEquals("usernames must be equals.", user.getUsername(), username);
		
		Assert.assertEquals("password must be xxx (checkit).", user.getPassword(), "xxx");
		
		Assert.assertNotSame("passwords must be equals.", user.getPassword(), null);
		
		log.info("user= " + user.getUsername() + " pass= " + user.getPassword());
	}
}
