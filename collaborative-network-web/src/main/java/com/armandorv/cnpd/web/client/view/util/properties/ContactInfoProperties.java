package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ContactInfoProperties extends PropertyAccess<ContactInfo>
{
   ModelKeyProvider<ContactInfo> id();

   ValueProvider<ContactInfo, String> name();

   ValueProvider<ContactInfo, String> lastname1();

   ValueProvider<ContactInfo, String> lastname2();

   ValueProvider<ContactInfo, String> fullName();
}
