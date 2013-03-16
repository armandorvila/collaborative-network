package com.armandorv.cnpd.web.client.view.projects.project.resources.util;

import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.theme.client.icons.resources.ResourcesIcons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;

/**
 * Select correct icon for a concrete resource.
 * 
 * @author armandorv
 * 
 */
public class ResourcesIconProvider implements IconProvider<ResourceInfo>
{

   private static ResourcesIcons icons = GWT.create(ResourcesIcons.class);

   @Override
   public ImageResource getIcon(ResourceInfo model)
   {
      if (model.getKind().equals(ResourceInfo.Kind.DOCUMENT))
         return icons.document();

      else if (model.getKind().equals(ResourceInfo.Kind.FILE))
         return icons.file();

      else if (model.getKind().equals(ResourceInfo.Kind.MARKER))
         return icons.file();

      else if (model.getKind().equals(ResourceInfo.Kind.FOLDER))
         return icons.folder();

      else if (model.getKind().equals(ResourceInfo.Kind.ROOT))
         return icons.folder();

      else if (model.getKind().equals(ResourceInfo.Kind.MEDIA))
         return icons.none();

      else if (model.getKind().equals(ResourceInfo.Kind.NONE))
         return icons.none();

      else
         throw new ClientsideException("Invalid resource kind.");
   }
}