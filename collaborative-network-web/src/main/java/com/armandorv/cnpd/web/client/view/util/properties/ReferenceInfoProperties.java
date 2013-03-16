package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ReferenceInfoProperties extends PropertyAccess<ReferenceInfo>
{

   ModelKeyProvider<ReferenceInfo> id();

   ValueProvider<ReferenceInfo, String> name();

   ValueProvider<ReferenceInfo, String> url();
}
