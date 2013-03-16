package com.armandorv.cnpd.client.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTextField;

/**
 * Class with fields related with a user.
 * 
 * @author armandorv
 *
 */
public class UserFieldsView extends JXTaskPaneContainer
{
   private static final long serialVersionUID = -8681369064718984430L;

   protected Box usernameRow = new Box(BoxLayout.X_AXIS);

   protected Box passwordRow = new Box(BoxLayout.X_AXIS);

   protected Box nameRow = new Box(BoxLayout.X_AXIS);

   protected Box lastNameRow = new Box(BoxLayout.X_AXIS);

   protected Box lastName2Row = new Box(BoxLayout.X_AXIS);

   protected Box birthdayRow = new Box(BoxLayout.X_AXIS);

   protected Box cityRow = new Box(BoxLayout.X_AXIS);

   protected Box webRow = new Box(BoxLayout.X_AXIS);

   /* ******************* Form fields *************** */

   private JXLabel username = new JXLabel(get("Username :", 14));

   private JXTextField usernameField = new JXTextField();

   private JXLabel password = new JXLabel(get("Password :", 14));

   private JPasswordField passwordField = new JPasswordField();

   private JXLabel name = new JXLabel(get("Name :", 14));

   private JXTextField nameField = new JXTextField();

   private JXLabel lastname = new JXLabel(get("Lastname :", 14));

   private JXTextField lastnameField = new JXTextField();

   private JXLabel lastname2 = new JXLabel(get("Lastname2 :", 14));

   private JXTextField lastname2Field = new JXTextField();

   private JXLabel birthday = new JXLabel(get("Birthday :", 16));

   private JXTextField birthdayField = new JXTextField();

   private JXLabel city = new JXLabel(get("City :", 16));

   private JXTextField cityField = new JXTextField();

   private JXLabel web = new JXLabel(get("Web :", 14));

   private JXTextField webField = new JXTextField();

   public UserFieldsView()
   {
      super.add(usernameRow);
      super.add(passwordRow);
      super.add(nameRow);
      super.add(lastNameRow);
      super.add(lastName2Row);
      super.add(birthdayRow);
      super.add(cityRow);
      super.add(webRow);

      usernameRow.add(username);
      usernameRow.add(usernameField);

      passwordRow.add(password);
      passwordRow.add(passwordField);

      nameRow.add(name);
      nameRow.add(nameField);

      lastNameRow.add(lastname);
      lastNameRow.add(lastnameField);

      lastName2Row.add(lastname2);
      lastName2Row.add(lastname2Field);

      birthdayRow.add(birthday);
      birthdayRow.add(birthdayField);

      cityRow.add(city);
      cityRow.add(cityField);

      webRow.add(web);
      webRow.add(webField);

   }

   private String get(String string, int length)
   {

      String ret = string;
      for (int i = string.length(); i < length; i++)
      {
         ret += "  ";
      }
      return "  " + ret;
   }

   public void setUName(String name)
   {
      this.nameField.setText(name);
   }

   public String getUName()
   {
      return this.nameField.getText();
   }

   public void setLastname(String name)
   {
      this.lastnameField.setText(name);
   }

   public String getLastname()
   {
      return this.lastnameField.getText();
   }

   public void setLastname2(String name)
   {
      this.lastname2Field.setText(name);
   }

   public String getLastname2()
   {
      return this.lastname2Field.getText();
   }

   public void setBirthday(String name)
   {
      this.birthdayField.setText(name);
   }

   public String getBirthday()
   {
      return this.birthdayField.getText();
   }

   public void setCity(String name)
   {
      this.cityField.setText(name);
   }

   public String getCity()
   {
      return this.cityField.getText();
   }

   public void setWeb(String name)
   {
      this.webField.setText(name);
   }

   public String getWeb()
   {
      return this.webField.getText();
   }

   public String getUsername()
   {
      return usernameField.getText();
   }

   @SuppressWarnings("deprecation")
   public String getPassword()
   {
      return passwordField.getText();
   }

   public void setUsernmae(String string)
   {
      this.usernameField.setText("");
   }
   
   public void setPassword(String string)
   {
      this.passwordField.setText("");
   }

}