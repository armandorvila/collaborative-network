package com.armandorv.cnpd.web.server.ejbremote;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.User;

/**
 * Class to test remote EJB functionalities, for UserManager as a client.
 * 
 * @author armandorv
 * 
 */
public class UsersManagerTest {

	private static Logger log = Logger.getLogger(UsersManagerTest.class);

	private static final String ejbName = (ResourceBundle
			.getBundle("ejb-names").getString("ejb.users.name"));

	private static IUsersManager remoteUsersManager;

	@BeforeClass
	public static void setUpAll() {
		Assert.assertTrue(ResourceBundle.getBundle("jboss-ejb-client")
				.containsKey("remote.connections"));
		String prop = ResourceBundle.getBundle("jboss-ejb-client").getString(
				"remote.connections");
		log.info(prop);
		remoteUsersManager = new JNDIHelper<IUsersManager>().doLookup(ejbName);

	}

	@Before
	public void setUp() {
		Assert.assertNotNull(remoteUsersManager);
	}

	@Test
	public void testRemoteUserManagerGetAll() {

		log.info(remoteUsersManager.ListAllUser());
		User user = remoteUsersManager.getUserByUsername("google");
		Assert.assertNotNull(user);
		log.info(user);
	}

}
