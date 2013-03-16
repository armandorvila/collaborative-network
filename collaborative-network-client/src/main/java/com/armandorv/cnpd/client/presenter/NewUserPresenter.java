package com.armandorv.cnpd.client.presenter;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;

/**
 * Presenter for the new user creation view.
 * 
 * @author armandorv
 *
 */
public class NewUserPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponent();

      void setNewUser(ActionListener newUser);

      String getUserName();

      String getPassword();

      String getName();

      String getLastname1();

      String getLastname2();

      String getBirthday();

      String getCity();

      String getWeb();

      void clear();
   }

   @Inject
   private View view;

   @Inject
   private UserCreator userCreator;

   @PostConstruct
   public void postConstruct()
   {
      view.setNewUser(userCreator);
   }

   @Override
   public void present(Container container)
   {
      container.add(view.asComponent());
      view.clear();
   }

}