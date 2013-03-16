package com.armandorv.cnpd.web.theme.client.templates;

import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface MeetingInfoTemplate extends XTemplates
{
   @XTemplate(source = "meetinginfo.html")
   SafeHtml renderMeetingInfo(MeetingInfo data);
}
