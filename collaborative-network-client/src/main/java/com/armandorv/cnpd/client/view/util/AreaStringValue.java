package com.armandorv.cnpd.client.view.util;

import com.armandorv.cnpd.model.KnowledgeArea;

/**
 * Get a String value for a cell of an areas list.
 * 
 * @author armandorv
 *
 */
public class AreaStringValue extends GenericStringValue<KnowledgeArea>
{
   private static final long serialVersionUID = -7235570651178809970L;

   @Override
   public String getValue(KnowledgeArea value)
   {
      return value.getName();
   }

}