package com.armandorv.cnpd.web.client.view.util.properties;

import java.util.Date;

import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MeetingInfoProperties extends PropertyAccess<MeetingInfo>
{
   ModelKeyProvider<MeetingInfo> id();

   ValueProvider<MeetingInfo, String> title();

   ValueProvider<MeetingInfo, Date> date();

   ValueProvider<MeetingInfo, Boolean> celebrated();
}
