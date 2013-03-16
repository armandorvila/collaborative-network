package com.armandorv.cnpd.web.client.view.util.tooltip;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ContactToolTipConfig extends RowToolTipConfig<ContactInfo>
{
   public ContactToolTipConfig(HasRowToolTip<ContactInfo> toolTipped)
   {
      super(toolTipped);
   }

   @Override
   public SafeHtml renderToolTip(ContactInfo data)
   {
      String text = data == null ? "" : data.getGmailAddress();
      return new SafeHtmlBuilder().appendHtmlConstant(text).toSafeHtml();
   }

}
