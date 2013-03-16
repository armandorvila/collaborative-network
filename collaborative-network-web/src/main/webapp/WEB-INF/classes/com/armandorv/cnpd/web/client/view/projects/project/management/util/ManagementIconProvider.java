package com.armandorv.cnpd.web.client.view.projects.project.management.util;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.armandorv.cnpd.web.theme.client.icons.resources.ResourcesIcons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;

/**
 * Render the correct icon for management options tree.
 * 
 * @author armandorv
 *
 */
public class ManagementIconProvider implements IconProvider<IdNameGenericInfo>
{
   private static ResourcesIcons resourcesIcons = GWT.create(ResourcesIcons.class);

   private static IconsBundle genericIcnos = GWT.create(IconsBundle.class);

   @Override
   public ImageResource getIcon(IdNameGenericInfo model)
   {
      if (model.equals(ManagementTreeElement.PROJECT) || model.equals(ManagementTreeElement.MANAGER)
            || model.equals(ManagementTreeElement.MEMBERS))
      {
         return resourcesIcons.folder();
      }
      else
      {
         return genericIcnos.preferences16();
      }
   }

}