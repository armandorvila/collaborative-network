package com.armandorv.cnpd.client.view.util;

import org.jdesktop.swingx.renderer.StringValue;

/**
 * Define a templates based String value implementation.
 * 
 * @author armandorv
 *
 * @param <T> type of object behind the list.
 */
public abstract class GenericStringValue <T> implements StringValue
{
   private static final long serialVersionUID = 7014345135486396314L;
   
   @Override
   @SuppressWarnings("unchecked")
   public String getString(Object value)
   {
      return get(getValue((T)value) , 40);
   }
   
   private String get(String string , int length){
      
      String ret = string;
      for (int i = string.length(); i < length; i++)
      {
         ret += "  ";
      }
      return "  " + ret;
   }
   
   public abstract String getValue(T value);

}
