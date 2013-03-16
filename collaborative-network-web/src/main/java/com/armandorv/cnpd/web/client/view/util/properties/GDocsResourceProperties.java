package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface GDocsResourceProperties extends PropertyAccess<GDocsResource>
{
   ModelKeyProvider<GDocsResource> id();

   ValueProvider<GDocsResource, String> title();

}
