package com.armandorv.cnpd.business.impl.util;

import java.util.ResourceBundle;

/**
 * Class to get static access to Strings required by business rules.
 * 
 * @author armandorv
 * 
 */
public class CnpdStrings
{

   /* **************** Properties names **************** */

   private static final String CNPD_MAIL_ADDRESS = "mail.cnpd.mail_address";

   private static final String USER_ELIMINATION_SUBJECT = "mail.user_elimination.subject";

   private static final String USER_ELIMINATION_TEXT = "mail.user_elimination.text";

   private static final String MESSAGE_SUBJECT = "mail.message.subject";

   private static final String NOTIFICATION_CONTACT_REQUEST = "notification.contact.request";

   private static final String NOTIFICATION_CONTACT_ACCEPTED = "notification.contact.accepted";

   private static final String NOTIFICATION_PROJECT_INVITATION = "notification.project.invitation";

   private static final String NOTIFICATION_PROJECT_ACCEPTED = "notification.project.accepted";

   /**
    * Bundle pointing to strings.properties
    */
   private static ResourceBundle bundle = ResourceBundle.getBundle("strings");

   /* ******** Methods to get properties ******** */

   public static String getCnpdMailAddress()
   {
      return bundle.getString(CNPD_MAIL_ADDRESS).trim();
   }

   public static String getUserElimintationSubject()
   {
      return bundle.getString(USER_ELIMINATION_SUBJECT);
   }

   public static String getUserEliminationText()
   {
      return bundle.getString(USER_ELIMINATION_TEXT);
   }

   public static String getContactRequestNotification()
   {
      return bundle.getString(NOTIFICATION_CONTACT_REQUEST);
   }

   public static String getProjectInvitationNotification()
   {
      return bundle.getString(NOTIFICATION_PROJECT_INVITATION);
   }

   public static String getMessageSubject()
   {
      return bundle.getString(MESSAGE_SUBJECT);
   }

   public static String getContactAcceptedNotification()
   {
      return bundle.getString(NOTIFICATION_CONTACT_ACCEPTED);
   }

   public static String getProjectAcceptedNotification()
   {
      return bundle.getString(NOTIFICATION_PROJECT_ACCEPTED);
   }

}
