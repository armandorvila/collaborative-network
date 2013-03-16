package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.UserInfo;

public class UserMapperTest
{
   private static final String userId = ResourceBundle.getBundle("test").getString("test.user");

   private static Mapper<User, UserInfo> userMapper;

   private static User userToMap;

   @BeforeClass
   public static void beforeClass()
   {
      userMapper = new UserMapper();
      userToMap = new EJBProducer().produceUsersManager().getUserById(Long.parseLong(userId));
   }

   @Before
   public void before()
   {
      Assert.assertNotNull(userMapper);
      Assert.assertNotNull(userToMap);
   }

   @Test
   public void testMap()
   {
      UserInfo info = userMapper.map(userToMap);
      Assert.assertNotNull(info);

      Assert.assertEquals(userToMap.getPersonalData().getName(), info.getName());
      Assert.assertEquals(userToMap.getPersonalData().getSurname1(), info.getLastname1());
      Assert.assertEquals(userToMap.getPersonalData().getSurname2(), info.getLastname2());
      Assert.assertEquals(userToMap.getPersonalData().getCity(), info.getCity());
      Assert.assertEquals(userToMap.getPersonalData().getDateOfBirthday(), info.getBirthday());
      Assert.assertEquals(userToMap.getGoogleAccount().getAccount(), info.getUsername());
      Assert.assertEquals(userToMap.getJobs().size(), info.getJobs().size());
      Assert.assertEquals(userToMap.getDegrees().size(), info.getDegrees().size());
      Assert.assertEquals(userToMap.getPersonalData().getWebSite(), info.getWebSite());

   }

}