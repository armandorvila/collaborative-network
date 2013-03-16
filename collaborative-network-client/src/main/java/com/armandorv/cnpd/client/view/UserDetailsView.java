package com.armandorv.cnpd.client.view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import com.armandorv.cnpd.model.User;

/**
 * View that hold details of any user.
 * 
 * @author armandorv
 *
 */
public class UserDetailsView extends JDialog
{
   private static final long serialVersionUID = -6388016265486842813L;

   private UserFieldsView fields = new UserFieldsView();

   public UserDetailsView()
   {
      super();
      super.add(new JScrollPane(fields));
      super.setModal(true);
      super.setSize(400, 300);
      super.setTitle("User Details");
   }

   public void setUser(User user)
   {
      fields.setUName(user.getPersonalData().getName());
      fields.setLastname(user.getPersonalData().getSurname1());
      fields.setLastname2(user.getPersonalData().getSurname2());
      fields.setBirthday(user.getPersonalData().getDateOfBirthday().toString());
      fields.setCity(user.getPersonalData().getCity());
      fields.setWeb(user.getPersonalData().getWebSite());
   }

}
