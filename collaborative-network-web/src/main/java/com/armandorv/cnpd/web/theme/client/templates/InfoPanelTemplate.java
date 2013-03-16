package com.armandorv.cnpd.web.theme.client.templates;

import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface InfoPanelTemplate extends XTemplates
{
   @XTemplate(source = "personalinfo.html")
   SafeHtml renderPersonalInfo(UserInfo data);

   @XTemplate(source = "academicinfo.html")
   SafeHtml renderAcademicInfo(UserInfo data);

   @XTemplate(source = "professionalinfo.html")
   SafeHtml renderProfessionalInfo(UserInfo data);
}
