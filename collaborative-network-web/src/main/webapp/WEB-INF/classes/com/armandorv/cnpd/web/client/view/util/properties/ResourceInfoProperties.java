package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ResourceInfoProperties extends PropertyAccess<ResourceInfo>
{

   ModelKeyProvider<ResourceInfo> id();

   ValueProvider<ResourceInfo, String> name();

   ValueProvider<ResourceInfo, ResourceInfo.Kind> kind();

}
