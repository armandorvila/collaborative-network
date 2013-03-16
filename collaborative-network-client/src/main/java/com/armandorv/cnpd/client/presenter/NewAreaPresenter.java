package com.armandorv.cnpd.client.presenter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.KnowledgeArea;

/**
 * Presenter for new areas creation view.
 * 
 * @author armandorv
 *
 */
public class NewAreaPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponent();

      void setNewArea(ActionListener newArea);

      String getAreaName();

      void clear();
   }

   @Inject
   private View view;

   @Inject
   private IProjectsManager projectsManager;

   @PostConstruct
   public void postConstruct()
   {
      view.setNewArea(newArea());
   }

   @Override
   public void present(Container container)
   {
      container.add(view.asComponent());
      view.clear();
   }

   private ActionListener newArea()
   {
      return new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            
            if("".equals(view.getAreaName())){
               JOptionPane.showMessageDialog(view.asComponent(), "You have to give a name.", "Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else {
            
            KnowledgeArea area = new KnowledgeArea();
            area.setName(view.getAreaName());

            try
            {
               projectsManager.createKnowledgeArea(area);
               
               view.clear();
               
               JOptionPane.showMessageDialog(view.asComponent(), "Knowledge area" + view.getAreaName() + " created.",
                     "Success",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e)
            {
               new ErrorPresenter(e, "Error creating area", "ERROR",
                     "Error creating area, send all messagee to cnpd team.").present(view.asComponent());
            }
            }
         }
      };
   }

}