package com.armandorv.cnpd.web.theme.client.templates;

import java.util.List;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ProjectInfoTemplate extends XTemplates
{
   @XTemplate(source = "projectinfo.html")
   SafeHtml renderProjectInfo(ProjectInfo data);

   @XTemplate(source = "projectmembers.html")
   SafeHtml renderProjectMembers(List<ContactInfo> members);
}
