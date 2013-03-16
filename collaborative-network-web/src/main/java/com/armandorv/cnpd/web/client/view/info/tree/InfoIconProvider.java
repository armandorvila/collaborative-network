package com.armandorv.cnpd.web.client.view.info.tree;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.armandorv.cnpd.web.theme.client.icons.resources.ResourcesIcons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;

/**
 * Render the correct icon.
 * 
 * @author armandorv
 *
 */
public class InfoIconProvider implements IconProvider<IdNameGenericInfo>
{
   private static ResourcesIcons resourcesIcons = GWT.create(ResourcesIcons.class);

   private static IconsBundle genericIcnos = GWT.create(IconsBundle.class);

   @Override
   public ImageResource getIcon(IdNameGenericInfo model)
   { 
      if (model.equals(InfoTreeElement.ACADEMIC) 
            || model.equals(InfoTreeElement.PERSONAL)
            || model.equals(InfoTreeElement.ACCOUNT)
            || model.equals(InfoTreeElement.PROFESSIONAL))
      {
         return resourcesIcons.folder();
      }
      else
      {
         return genericIcnos.preferences16();
      }
   }

}