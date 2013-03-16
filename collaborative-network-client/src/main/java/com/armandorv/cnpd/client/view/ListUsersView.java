package com.armandorv.cnpd.client.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.inject.Singleton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.VerticalLayout;
import org.jdesktop.swingx.renderer.DefaultListRenderer;

import com.armandorv.cnpd.client.presenter.ListUsersPresenter;
import com.armandorv.cnpd.client.view.util.UserStringValue;
import com.armandorv.cnpd.client.view.util.UsersListModel;
import com.armandorv.cnpd.model.User;

/**
 * View with a list of users and options over that list.
 * 
 * @author armandorv
 *
 */
@Singleton
public class ListUsersView extends JXTitledPanel implements ListUsersPresenter.View
{
   private static final long serialVersionUID = -7062327738949347078L;

   private static final String TITLE = "Users ";

   private JXPanel centralPanel = new JXPanel(new HorizontalLayout());

   private JXPanel optionsPanel = new JXPanel(new VerticalLayout());

   private JXList list = new JXList(true);

   private JXPanel userDetails = new JXPanel();

   private JXPanel deleteUsers = new JXPanel();

   private JXButton delete = new JXButton("Delete");

   private JXButton details = new JXButton("Detail");

   public ListUsersView()
   {
      super(TITLE);

      list.setCellRenderer(new DefaultListRenderer(new UserStringValue()));

      userDetails.add(new JXLabel("User detail : "));
      userDetails.add(details);

      deleteUsers.add(new JXLabel("Delete user :"));
      deleteUsers.add(delete);

      optionsPanel.add(userDetails);
      optionsPanel.add(deleteUsers);

      centralPanel.add(new JScrollPane(list));
      centralPanel.add(optionsPanel);

      super.add(centralPanel);
   }

   @Override
   public JComponent asComponent()
   {
      return this;
   }

   @Override
   public void setUsers(List<User> users)
   {
      list.setModel(new UsersListModel(users));
   }

   @Override
   public void setDeleteUser(ActionListener deleteUser)
   {
     this.delete.addActionListener(deleteUser);
   }

   @Override
   public void setUserDetails(ActionListener showDetails)
   {
      this.details.addActionListener(showDetails);
   }

   @Override
   public User getSelected()
   {
      return (User) list.getModel().getElementAt(list.getSelectedIndex());
   }
  
}