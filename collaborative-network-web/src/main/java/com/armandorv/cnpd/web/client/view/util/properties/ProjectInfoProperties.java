package com.armandorv.cnpd.web.client.view.util.properties;

import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ProjectInfoProperties extends PropertyAccess<ProjectInfo>
{
   ModelKeyProvider<ProjectInfo> id();

   ValueProvider<ProjectInfo, String> title();

   @Path("knowledgeArea.name")
   ValueProvider<ProjectInfo, String> area();
}
