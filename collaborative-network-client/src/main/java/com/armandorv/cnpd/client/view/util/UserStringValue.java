package com.armandorv.cnpd.client.view.util;

import com.armandorv.cnpd.model.User;

/**
 * String value for a cell of a users based list.
 * 
 * @author armandorv
 *
 */
public class UserStringValue extends GenericStringValue<User>
{
   private static final long serialVersionUID = -240248007345168771L;

   @Override
   public String getValue(User value)
   {
      return value.getPersonalData().getName() + " " + value.getPersonalData().getSurname1() + " "
            + value.getPersonalData().getSurname2();
   }

}
