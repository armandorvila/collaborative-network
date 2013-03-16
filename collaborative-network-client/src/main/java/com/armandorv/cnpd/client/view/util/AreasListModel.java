package com.armandorv.cnpd.client.view.util;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.armandorv.cnpd.model.KnowledgeArea;

/**
 * Swing List model for a list of Areas.
 * 
 * @author armandorv
 *
 */
public class AreasListModel implements ListModel
{
   private List<KnowledgeArea> users;
   
   public AreasListModel(List<KnowledgeArea> users)
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
