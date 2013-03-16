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
import org.jdesktop.swingx.renderer.DefaultListRenderer;

import com.armandorv.cnpd.client.presenter.AreasPresenter;
import com.armandorv.cnpd.client.view.util.AreaStringValue;
import com.armandorv.cnpd.client.view.util.AreasListModel;
import com.armandorv.cnpd.model.KnowledgeArea;

/**
 * View with a list of areas and options over areas into the list.
 * 
 * @author armandorv
 *
 */
@Singleton
public class AreasView extends JXTitledPanel implements AreasPresenter.View
{
   private static final long serialVersionUID = 5340137532801321456L;
   
   private static final String TITLE = "Knowledge areas";

   private JXPanel centralPanel = new JXPanel(new HorizontalLayout());

   private JXList list = new JXList(true);

   private JXPanel deletePanel = new JXPanel();

   private JXButton delete = new JXButton("Delete");
   
   public AreasView(){
      
      super(TITLE);
      
      list.setCellRenderer(new DefaultListRenderer(new AreaStringValue()));

      deletePanel.add(new JXLabel("Delete area :"));
      deletePanel.add(delete);
      
      centralPanel.add(new JScrollPane(list));
      centralPanel.add(deletePanel);

      super.add(centralPanel);
   }

   @Override
   public JComponent asComponent()
   {
      return this;
   }
   
   @Override
   public void setAreas(List<KnowledgeArea> areas)
   {
      list.setModel(new AreasListModel(areas));
   }

   @Override
   public void setDelete(ActionListener delete)
   {
     this.delete.addActionListener(delete);
   }


   @Override
   public KnowledgeArea getSelected()
   {
      return (KnowledgeArea) list.getModel().getElementAt(list.getSelectedIndex());
   }

}