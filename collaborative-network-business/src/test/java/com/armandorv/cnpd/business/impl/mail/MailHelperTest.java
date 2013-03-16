package com.armandorv.cnpd.business.impl.mail;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.business.impl.util.CnpdStrings;
import com.armandorv.cnpd.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class MailHelperTest extends ArquillianSupport
{

   private static Logger log = Logger.getLogger(MailHelperTest.class);

   private static final String from = "armmandoo2@gmail.com";

   private static final String to = "armando.ramirez.vila@gmail.com";

   private static final String text = "A testing mail, delete it.";

   @Inject
   private MailHelper mailHelper;

   @Test
   public void testSendMail()
   {
      try
      {
         mailHelper.sendMail(from, to, CnpdStrings.getMessageSubject() + " " + from, text);
         log.info("Mail Sent, check it.");

      }
      catch (Exception e)
      {
         log.info("Error sending mail", e);
         fail(e.getMessage());

      }
   }
}
