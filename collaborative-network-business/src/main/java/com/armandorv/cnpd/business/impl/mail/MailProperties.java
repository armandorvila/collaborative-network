package com.armandorv.cnpd.business.impl.mail;

import java.util.ResourceBundle;

public class MailProperties
{

   private static MailProperties properties = new MailProperties();

   private final String MAIL_ENABLED_KEY = "mail.enabled";

   private final String MAIL_MANAGER_KEY = "mail.manager.address";

   private ResourceBundle bundle = ResourceBundle.getBundle("mail");

   public static MailProperties Properties()
   {
      return properties;
   }

   public boolean isEnabled()
   {
      return "true".equals(bundle.getString(MAIL_ENABLED_KEY));
   }

   public String getManagerMail()
   {
      return bundle.getString(MAIL_MANAGER_KEY);
   }
}
