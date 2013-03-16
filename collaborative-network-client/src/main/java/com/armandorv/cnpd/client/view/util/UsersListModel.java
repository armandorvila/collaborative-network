package com.armandorv.cnpd.client.view.util;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.armandorv.cnpd.model.User;

/**
 * Swing list model for a list users.
 * 
 * @author armandorv
 *
 */
public class UsersListModel implements ListModel
{
   private List<User> users;
   
   public UsersListModel(List<User> users)
   {
      super();
      this.users = users;
   }

   @Override
   public int getSize()
   {
      return users.size();
   }

   @Override
   public Object getElementAt(int index)
   {
      return users.get(index);
   }

   @Override
   public void addListDataListener(ListDataListener l)
   {
     
   }

   @Override
   public void removeListDataListener(ListDataListener l)
   {
      
   }

}
