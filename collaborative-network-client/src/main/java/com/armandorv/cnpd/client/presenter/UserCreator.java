package com.armandorv.cnpd.client.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import com.armandorv.cnpd.business.IUsersManager;
import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;

/**
 * Create a user getting info from a pre set view.
 * 
 * @author armandorv
 *
 */
public class UserCreator implements ActionListener
{
   @Inject
   private NewUserPresenter.View view;

   @Inject
   private IUsersManager usersManager;

   @Override
   public void actionPerformed(ActionEvent event)
   {
      if (validate())
      {
         Date date = getDate();

         if (date != null)
         {
            User user = new User();
            AccountData account = new AccountData();
            PersonalData data = new PersonalData();
            user.setGoogleAccount(account);
            user.setPersonalData(data);

            account.setAccount(view.getUserName());
            account.setPassword(view.getPassword());

            data.setName(view.getName());
            data.setSurname1(view.getLastname1());
            data.setSurname2(view.getLastname2());
            data.setDateOfBirthday(date);
            data.setWebSite(view.getWeb());
            data.setCity(view.getCity());

            try
            {
               usersManager.createUser(user);

               view.clear();

               JOptionPane.showMessageDialog(view.asComponent(), "User  created.",
                     "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e)
            {
               new ErrorPresenter(e, "Error creating user", "ERROR", "May be user is already into the system.")
                     .present(view
                           .asComponent());
            }
         }
      }
      else
      {
         JOptionPane.showMessageDialog(view.asComponent(), "All fields are required.", "Error",
               JOptionPane.ERROR_MESSAGE);
      }
   }

   private boolean validate()
   {

      if ("".equals(view.getUserName()))
      {
         return false;
      }

      if ("".equals(view.getPassword()))
      {
         return false;
      }

      if ("".equals(view.getLastname1()))
      {
         return false;
      }
      if ("".equals(view.getName()))
      {
         return true;
      }

      if ("".equals(view.getCity()))
      {
         return false;
      }

      if ("".equals(view.getWeb()))
      {

         return false;
      }

      return true;
   }

   private Date getDate()
   {
      try
      {
         return new SimpleDateFormat("yyyy/MM/dd").parse(view.getBirthday());
      }
      catch (Exception e)
      {
         JOptionPane.showMessageDialog(view.asComponent(), "Invalid Date Format.", "Error",
               JOptionPane.ERROR_MESSAGE);
         return null;
      }
   }

}