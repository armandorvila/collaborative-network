package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface DiscussionInfoProperties extends PropertyAccess<DiscussionInfo>
{

   ModelKeyProvider<DiscussionInfo> id();

   ValueProvider<DiscussionInfo, String> title();

   ValueProvider<DiscussionInfo, String> description();

   @Path("title")
   LabelProvider<DiscussionInfo> label();

   ValueProvider<DiscussionInfo, Boolean> open();

}
