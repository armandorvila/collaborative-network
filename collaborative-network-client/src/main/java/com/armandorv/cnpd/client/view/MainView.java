package com.armandorv.cnpd.client.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.inject.Singleton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.swingx.JXDialog;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import com.armandorv.cnpd.client.presenter.ActionExecution;
import com.armandorv.cnpd.client.presenter.MainPresenter;

/**
 * View that show a task panel for users an another for project, and change the central content 
 * dependes on selected task.
 * 
 * @author armandorv
 *
 */
@Singleton
public class MainView extends JXPanel implements MainPresenter.View
{
   private static final long serialVersionUID = -2194252038604033610L;

   private JXTaskPaneContainer taskPaneContainer;

   private JXTaskPane usersTaskPane;

   private JXTaskPane areasTaskPane;

   private ActionExecution listUsers;

   private ActionExecution newUser;

   private ActionExecution newArea;

   private ActionExecution areas;

   public MainView()
   {
      super(new BorderLayout());

      taskPaneContainer = new JXTaskPaneContainer();

      usersTaskPane = new JXTaskPane();
      usersTaskPane.setTitle("Users");

      areasTaskPane = new JXTaskPane();
      areasTaskPane.setTitle("Knowledge areas");

      taskPaneContainer.add(usersTaskPane);
      taskPaneContainer.add(areasTaskPane);

      this.bind();
      
      areasTaskPane.setCollapsed(true);
      
      super.add(new JScrollPane(taskPaneContainer));
   }

   public Component add(Component componenet)
   {
      if (taskPaneContainer.getComponentCount() == 3)
      {
         if (!(componenet instanceof JXDialog))
            taskPaneContainer.remove(2);
      }
      taskPaneContainer.add(componenet);
      taskPaneContainer.updateUI();

      return this;
   }

   private void bind()
   {
      ApplicationContext context = Application.getInstance().getContext();
      context.getResourceMap(getClass()).injectComponents(this);

      ApplicationActionMap map = context.getActionMap(this);
      
      
      usersTaskPane.add(map.get("Users List"));
      usersTaskPane.add(map.get("New User"));

      areasTaskPane.add(map.get("Areas List"));
      areasTaskPane.add(map.get("New area"));
   }

   @Action(name="Users List")
   public void listUsers()
   {
      listUsers.execute();
   }

   @Action(name="New User")
   public void newUser()
   {
      newUser.execute();
   }

   @Action(name="Areas List")
   public void areas()
   {
      areas.execute();
   }
   
   @Action(name="New area") 
   public void newArea()
   {
      newArea.execute();
   }

   @Override
   public JComponent asComponent()
   {
      return this;
   }

   @Override
   public void setListUsersActionExecution(ActionExecution listUsers)
   {
      this.listUsers = listUsers;
   }

   @Override
   public void setNewUserActionExecution(ActionExecution newUser)
   {
      this.newUser = newUser;
   }

   @Override
   public void setNewAreaActionExecution(ActionExecution newArea)
   {
      this.newArea = newArea;
   }

   @Override
   public void setAreasActionExecution(ActionExecution areas)
   {
      this.areas = areas;
   }

}