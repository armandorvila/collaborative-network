package com.armandorv.cnpd.web.server.mapper;

import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.server.ejb.EJBProducer;
import com.armandorv.cnpd.web.shared.model.ContactInfo;

public class ContactMapperTest
{
   private static final String userId = ResourceBundle.getBundle("test").getString("test.user");

   private static Mapper<User, ContactInfo> contactMapper;

   private static User userToMap;

   @BeforeClass
   public static void beforeAll()
   {
      contactMapper = new ContactMapper();
      userToMap = new EJBProducer().produceUsersManager().getUserById(Long.parseLong(userId));
   }

   @Test
   public void testMap()
   {
      Assert.assertNotNull(contactMapper);
      Assert.assertNotNull(userToMap);

      ContactInfo info = contactMapper.map(userToMap);
      Assert.assertNotNull(info);

      Assert.assertNotNull(info.getFullName());
      Assert.assertNotNull(info.getGmailAddress());
      Assert.assertNotNull(info.getLastname2());
      Assert.assertNotNull(info.getLastname1());
      Assert.assertNotNull(info.getName());

      Assert.assertEquals(info.getName(), userToMap.getPersonalData().getName());
      Assert.assertEquals(info.getLastname1(), userToMap.getPersonalData().getSurname1());
      Assert.assertEquals(info.getLastname2(), userToMap.getPersonalData().getSurname2());
      Assert.assertEquals(info.getGmailAddress(), userToMap.getGoogleAccount().getAccount());
   }

}