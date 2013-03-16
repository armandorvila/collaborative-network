package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@Ignore
@RunWith(Arquillian.class)
public class SingupServiceTest extends ArquillianSupport
{
   @Inject
   private SingupService singUpService;

   @Inject
   private static UsersService usersService;

   private static String newUser = "cnpd.manager@gmail.com";

   private static String newUserPass = "cnpd";

   @Test
   public void testSaveAndValidateGoogleAccount()
   {
      ValidationResponse response = singUpService.saveAndValidateGoogleAccount(newUser, newUserPass);
      Assert.assertNotNull(response);
      Assert.assertTrue(response.isPositive());
   }

   @Test
   public void testSavePersonalData()
   {
      UserInfo info = new UserInfo();

      info.setName("Test");
      info.setName("test");
      info.setFullName("Test");
      info.setLastname1("Test");
      info.setLastname2("Test");
      info.setUsername(newUser);
      info.setWebSite("Test");

      Assert.assertTrue(singUpService.savePersonalData(info));
   }

   @Test
   public void testSaveAcademicInfo()
   {
      List<String> list = new ArrayList<String>();

      list.add("Testing degree");

      Assert.assertTrue(singUpService.saveAcademicInfo(list));
   }

   @Test
   public void testSaveProfessionalInfo()
   {
      List<String> list = new ArrayList<String>();

      list.add("Testing degree");

      Assert.assertTrue(singUpService.saveAcademicInfo(list));
   }

   @Test
   public void testGetHypotheticalContacts()
   {
      List<ContactInfo> contacts = singUpService.getHypotheticalContacts();
      Assert.assertNotNull(contacts);
   }

   @Test
   public void testSaveContactsInfo()
   {
      Assert.assertTrue(singUpService.saveContactsInfo(new ArrayList<ContactInfo>()));
   }

   @Test
   public void testCommitUser()
   {
      Assert.assertTrue(singUpService.commitUser().isPositive());
   }

   @AfterClass
   public static void afterClass()
   {
      usersService.deleteUser(usersService.getUserByUsername(newUser).getId());
   }

}