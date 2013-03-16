package com.armandorv.cnpd.client.view;

import java.awt.event.ActionListener;

import javax.inject.Singleton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.JXTitledPanel;

import com.armandorv.cnpd.client.presenter.NewAreaPresenter;

/**
 * View for the new Area Creation.
 * 
 * @author armandorv
 *
 */
@Singleton
public class NewAreaView extends JXTitledPanel implements NewAreaPresenter.View
{
   private static final long serialVersionUID = 3799300831722745610L;

   private static final String TITLE = "New Knowledge area";

   private JXTaskPaneContainer container = new JXTaskPaneContainer();

   private JXButton create = new JXButton("Create");

   private JXLabel nameLabel = new JXLabel(get("Name :", 14));

   private JXTextField nameField = new JXTextField();

   public NewAreaView()
   {
      super(TITLE);

      Box nameRow = new Box(BoxLayout.X_AXIS);
      nameRow.add(nameLabel);
      nameRow.add(nameField);

      Box createRow = new Box(BoxLayout.X_AXIS);
      createRow.setAlignmentX(RIGHT_ALIGNMENT);
      createRow.add(create);
      create.setAlignmentX(RIGHT_ALIGNMENT);

      container.add(nameRow);
      container.add(createRow);

      super.add(container);
   }

   public JComponent asComponent()
   {
      return this;
   }

   private String get(String string, int length)
   {
      String ret = string;
      for (int i = string.length(); i < length; i++)
      {
         ret += "  ";
      }
      return ret;
   }

   public void setNewArea(ActionListener newArea)
   {
      create.addActionListener(newArea);
   }

   public String getAreaName()
   {
      return nameField.getText();
   }

   @Override
   public void clear()
   {
      nameField.setText("");
   }

}
