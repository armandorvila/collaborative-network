package com.armandorv.cnpd.client.view;

import java.awt.event.ActionListener;

import javax.inject.Singleton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXTitledPanel;

import com.armandorv.cnpd.client.presenter.NewUserPresenter;

/**
 * View for new users creation.
 * 
 * @author armandorv
 *
 */
@Singleton
public class NewUserView extends JXTitledPanel implements NewUserPresenter.View
{
   private static final long serialVersionUID = -6556915990176239794L;
  
   private static final String TITLE = "New User";

   private UserFieldsView fields = new UserFieldsView();
   
   private JXButton createButton = new JXButton("Create");
   
   public NewUserView()
   {
      super(TITLE);
      super.add(fields);
      
      Box horizontal = new Box(BoxLayout.X_AXIS);
      horizontal.add(createButton);
      fields.add(horizontal);
      fields.setBirthday("YYYY/MM/DD");
   }

   @Override
   public JComponent asComponent()
   {
      return this;
   }

   @Override
   public void setNewUser(ActionListener newUser)
   {
      createButton.addActionListener(newUser);
   }
   
   @Override
   public String getName()
   {
      return fields.getUName();
   }

   @Override
   public String getLastname1()
   {
      return fields.getLastname();
   }

   @Override
   public String getLastname2()
   {
      return fields.getLastname2();
   }

   @Override
   public String getBirthday()
   {
      return fields.getBirthday();
   }

   @Override
   public String getCity()
   {
      return fields.getCity();
   }

   @Override
   public String getWeb()
   {
      return fields.getWeb();
   }

   @Override
   public String getUserName()
   {
      return fields.getUsername();
   }

   @Override
   public String getPassword()
   {
      return fields.getPassword();
   }

   @Override
   public void clear()
   {
      fields.setUsernmae("");
      fields.setPassword("");
      fields.setUName("");
      fields.setLastname("");
      fields.setLastname2("");
      fields.setBirthday("yyyy/MM/dd");
      fields.setWeb("");
      fields.setCity("");
   }

}
