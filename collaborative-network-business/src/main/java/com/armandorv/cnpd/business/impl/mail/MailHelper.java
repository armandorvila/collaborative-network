package com.armandorv.cnpd.business.impl.mail;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.business.exception.InfrastructureException;

/**
 * 
 * @author armandorv
 * 
 */
public class MailHelper
{
   private static Logger log = Logger.getLogger(MailHelper.class);

   @Resource(mappedName = "java:jboss/mail/gmail")
   private Session session;

   public void sendMail(String from, String to, String subject, String text)
   {
      try
      {
         if (MailProperties.Properties().isEnabled())
         {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            Address toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(text, "text/plain");

            Transport.send(message);
         }
         else
         {
            log.warn("Mail sending are disabled, check mail.properties");
         }

      }
      catch (AddressException e)
      {
         log.error("Address error ,from :" + from + " to :" + to);
         throw new InfrastructureException("Address error ,from :" + from
               + " to :" + to, e);
      }
      catch (MessagingException e)
      {
         log.error("Messageing error , from :" + from + " to :" + to);
         throw new InfrastructureException("Messging  error ,from :" + from
               + " to :" + to, e);
      }

   }
}
