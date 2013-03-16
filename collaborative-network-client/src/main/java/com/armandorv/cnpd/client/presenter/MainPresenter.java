package com.armandorv.cnpd.client.presenter;

import java.awt.Container;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JComponent;

/**
 * Presenter for global view of the application.
 * 
 * @author armandorv
 *
 */
@Singleton
public class MainPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponent();

      void setListUsersActionExecution(ActionExecution execution);

      void setNewUserActionExecution(ActionExecution execution);

      void setNewAreaActionExecution(ActionExecution execution);

      void setAreasActionExecution(ActionExecution execution);
   }

   @Inject
   private ListUsersPresenter listUsersPresenter;

   @Inject
   private NewUserPresenter newUserPresenter;

   @Inject
   private NewAreaPresenter newAreaPresenter;

   @Inject
   private AreasPresenter areasPresenter;

   @Inject
   private View view;

   @PostConstruct
   public void postConstruct()
   {
      view.setListUsersActionExecution(listUsers());

      view.setNewUserActionExecution(newUser());

      view.setAreasActionExecution(areas());

      view.setNewAreaActionExecution(newArea());
   }

   @Override
   public void present(Container container)
   {
      container.add(view.asComponent());
      listUsersPresenter.present(view.asComponent()); 
   }

   public ActionExecution listUsers()
   {
      return new ActionExecution()
      {
         @Override
         public void execute()
         {
            listUsersPresenter.present(view.asComponent());
         }
      };
   }

   private ActionExecution newArea()
   {
      return new ActionExecution()
      {
         @Override
         public void execute()
         {
            newAreaPresenter.present(view.asComponent());
         }
      };
   }

   private ActionExecution areas()
   {
      return new ActionExecution()
      {
         @Override
         public void execute()
         {
            areasPresenter.present(view.asComponent());
         }
      };
   }

   private ActionExecution newUser()
   {
      return new ActionExecution()
      {
         @Override
         public void execute()
         {
            newUserPresenter.present(view.asComponent());
         }
      };
   }

}