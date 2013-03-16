package com.armandorv.cnpd.web.client.view.projects.project.management.util;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;

/**
 * Filter for management options tree.
 * 
 * @author armandorv
 *
 */
public class ManagementTreeFilter extends StoreFilterField<IdNameGenericInfo>
{
   @Override
   protected boolean doSelect(Store<IdNameGenericInfo> store, IdNameGenericInfo parent, IdNameGenericInfo item,
         String filter)
   {
      if (item.getId() == -1L)
      {
         return true;
      }
      else
      {
         return item.getName().toLowerCase().contains(filter.toLowerCase());
      }
   }

}