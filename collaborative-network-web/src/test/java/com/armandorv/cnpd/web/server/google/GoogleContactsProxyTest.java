package com.armandorv.cnpd.web.server.google;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.armandorv.cnpd.web.shared.model.ContactInfo;

/**
 * Testing class for GoogleContactsProxy, this test connect with GoogleDataAPI .
 * 
 * @author armandorv
 * 
 */
public class GoogleContactsProxyTest
{
   private static Logger log = Logger.getLogger(GoogleContactsProxyTest.class);

   private GoogleContactsProxy proxy = new GoogleContactsProxy();

   private static final String user = "armmandoo2@gmail.com";//"uo213731@gmail.com";

   private static final String password = "173048gmail"; //"pfc.uo213731";

   @Test
   public void testValidateUser()
   {
      Assert.assertNotNull(proxy);

      try
      {
         boolean valid = proxy.validate(user, password);

         Assert.assertTrue(valid);

         log.info("Test Passed :" + user + " is valid = " + valid);
      }
      catch (Exception e)
      {
         log.error("An error has ocurred:" + e.getMessage());
         e.printStackTrace();
         Assert.assertTrue(false);
      }
   }

   @Test
   public void testGetContacts()
   {
      Assert.assertNotNull(proxy);

      try
      {
         List<ContactInfo> contacts = proxy.getContacts(user, password);

         Assert.assertNotNull(contacts);

         Assert.assertTrue(contacts.size() > 0);

         log.info("Test Pased, contacts are:");
         
         for (ContactInfo contactInfo : contacts)
         {
            log.info(contactInfo.getGmailAddress() + " " + contactInfo.getFullName());
         }
      }
      catch (Exception e)
      {
         log.error("An error has ocurred:" + e.getMessage());
         e.printStackTrace();
         Assert.assertTrue(false);
      }
   }
}