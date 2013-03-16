package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class UsersServiceTest extends ArquillianSupport
{
   private final String username = ResourceBundle.getBundle("test").getString("test.username");

   @Inject
   private UsersService usersService;
   
   @Test
   public void testGetUserByUsername()
   {
      UserInfo user = usersService.getUserByUsername(username);
      Assert.assertNotNull(user);
      Assert.assertEquals(username, user.getUsername());
   }

   @Test
   public void testSetUserInformation()
   {
      UserInfo user = usersService.getUserByUsername(username);
      Assert.assertNotNull(user);

      String newName = "webTierChanged";

      user.setName(newName);

      usersService.setUserInformation(user);

      UserInfo found = usersService.getUserByUsername(username);
      Assert.assertNotNull(found);

      Assert.assertEquals(newName, found.getName());
   }

   @Test
   public void testSetUserDegrees()
   {
      UserInfo user = usersService.getUserByUsername(username);
      Assert.assertNotNull(user);

      usersService.setUserDegrees(user.getId(), new ArrayList<String>());

      UserInfo found = usersService.getUserByUsername(username);
      Assert.assertNotNull(found);

      Assert.assertEquals(0, found.getDegrees().size());
   }

   @Test
   public void testSetUserJobs()
   {
      UserInfo user = usersService.getUserByUsername(username);
      Assert.assertNotNull(user);

      List<String> jobs = new ArrayList<String>();
      jobs.add("web tier changer");

      usersService.setUserJobs(user.getId(), jobs);

      UserInfo found = usersService.getUserByUsername(username);
      Assert.assertNotNull(found);

      Assert.assertEquals(jobs.size(), found.getJobs().size());
   }

   @Test
   public void testSetUserAccount()
   {
      UserInfo user = usersService.getUserByUsername(username);
      Assert.assertNotNull(user);

      String newAccount = "web_tier_changed";

      usersService.setUserAccount(user.getId(), newAccount, "web_tier_changed");

      UserInfo found = usersService.getUserByUsername(newAccount);
      Assert.assertNotNull(found);

      Assert.assertEquals(newAccount, found.getUsername());

      usersService.setUserAccount(user.getId(), username, "web_tier_changed");

      found = usersService.getUserByUsername(username);
      Assert.assertNotNull(found);

      Assert.assertEquals(username, found.getUsername());
   }

}