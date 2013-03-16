package com.armandorv.cnpd.client.presenter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.client.view.UserDetailsView;
import com.armandorv.cnpd.model.User;

/*
 * Presenter for the ListUsersView, get all users and set it into the view.
 */
public class ListUsersPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponent();

      void setUsers(List<User> users);

      void setDeleteUser(ActionListener deleteUser);

      void setUserDetails(ActionListener showDetails);

      User getSelected();
   }

   @Inject
   private View view;

   @Inject
   private IUsersManager manager;

   @PostConstruct
   public void postConstruct()
   {
      view.setUserDetails(details());
      view.setDeleteUser(delete());
   }

   @Override
   public void present(Container container)
   {
      view.setUsers(manager.ListAllUser());
      container.add(view.asComponent());
   }

   private ActionListener delete()
   {
      return new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent event)
         {
            if (JOptionPane.showConfirmDialog(view.asComponent(), "are you sure") == JOptionPane.YES_OPTION)
            {
               try {
               
               manager.deleteUser(view.getSelected().getId());
                
               JOptionPane.showMessageDialog(view.asComponent(), "User " + view.getSelected().getPersonalData().getName() + " deleted.",
                     "Success",JOptionPane.INFORMATION_MESSAGE);
               
               view.setUsers(manager.ListAllUser());
               }
               catch (Exception e) {
                  new ErrorPresenter(e , "Error deleting user." , "Error").present(view.asComponent());
               }
            }
         }
      };
   }

   private ActionListener details()
   {
      return new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            UserDetailsView details = new UserDetailsView();
            details.setUser(view.getSelected());
            details.setVisible(true);
         }
      };
   }
}
