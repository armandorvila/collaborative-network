package com.armandorv.cnpd.web.client.view.util.properties;

import java.util.Date;

import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface NotificationInfoProperties extends PropertyAccess<NotificationInfo>
{

   ModelKeyProvider<NotificationInfo> id();

   ValueProvider<NotificationInfo, String> name();

   ValueProvider<NotificationInfo, Date> date();

   ValueProvider<NotificationInfo, Boolean> isNew();

}
