package com.armandorv.cnpd.web.client.view.projects.project.resources.util;

import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;

/**
 * GXT Tree filtering implementation.
 * 
 * @author armandorv
 *
 */
public class ResourcesFilter extends StoreFilterField<ResourceInfo>
{
   @Override
   protected boolean doSelect(Store<ResourceInfo> store, ResourceInfo parent, ResourceInfo item, String filter)
   {
      if (item.getId() == -1L)
         return true;

      else
         return item.getName().toLowerCase().contains(filter.toLowerCase());
   }

}