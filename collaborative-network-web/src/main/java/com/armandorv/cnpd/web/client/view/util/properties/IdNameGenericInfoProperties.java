package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface IdNameGenericInfoProperties extends PropertyAccess<IdNameGenericInfo>
{

   ModelKeyProvider<IdNameGenericInfo> id();

   ValueProvider<IdNameGenericInfo, String> name();

   @Path("name")
   LabelProvider<IdNameGenericInfo> labelName();

}
