package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.NotificationKind;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Notifications;

@Notifications
@Dependent
public class NotificationMapper implements Mapper<Notification, NotificationInfo>
{

   public static final String NOTIFICATION_REQUEST_CONTACT_NAME = "Contact request";

   public static final String NOTIFICATION_CONTACT_ACCEPTED_NAME = "Contact Accpeted";

   @Override
   public NotificationInfo map(Notification object)
   {

      if (object.getId() == null)
         throw new MappingErrorException("Notification must has a identifier.");

      NotificationInfo notification = new NotificationInfo();

      notification.setId(object.getId());
      notification.setKind(parseKnid(object.getKind()));
      notification.setMessage(object.getMessage());
      notification.setDate(object.getDate());
      notification.setName(getName(object.getKind()));
      notification.setNew(!object.isNotified());
      notification.setObject(object.getThirdPartId());

      return notification;
   }

   private NotificationInfo.Kind parseKnid(NotificationKind businesKind)
   {
      if (businesKind.equals(NotificationKind.CONTACT_ACEPPTED))
         return NotificationInfo.Kind.CONTACT_ACEPPTED;

      if (businesKind.equals(NotificationKind.CONTACT_REQUEST))
         return NotificationInfo.Kind.CONTACT_REQUEST;

      if (businesKind.equals(NotificationKind.PROJECT_ACCEPTED))
         return NotificationInfo.Kind.PROJECT_ACCEPTED;

      if (businesKind.equals(NotificationKind.PROJECT_PUBLISHT))
         return NotificationInfo.Kind.PROJECT_PUBLISHT;

      if (businesKind.equals(NotificationKind.PROJECT_INVITATION))
         return NotificationInfo.Kind.PROJECT_REQUEST;

      throw new MappingErrorException("Error mapping notification.");
   }

   private String getName(NotificationKind businesKind)
   {
      if (businesKind.equals(NotificationKind.CONTACT_ACEPPTED))
         return "New Contact";

      if (businesKind.equals(NotificationKind.CONTACT_REQUEST))
         return "Contact Request";

      if (businesKind.equals(NotificationKind.PROJECT_ACCEPTED))
         return "New Project";

      if (businesKind.equals(NotificationKind.PROJECT_PUBLISHT))
         return "Project Publisht";

      if (businesKind.equals(NotificationKind.PROJECT_INVITATION))
         return "Project Invitation";

      throw new MappingErrorException("Error mapping notification.");
   }
}
