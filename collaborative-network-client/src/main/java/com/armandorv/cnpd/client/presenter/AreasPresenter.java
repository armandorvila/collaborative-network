package com.armandorv.cnpd.client.presenter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.KnowledgeArea;

/**
 * Presenter for areas section.
 * 
 * @author armandorv
 *
 */
public class AreasPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponent();

      void setAreas(List<KnowledgeArea> areas);

      void setDelete(ActionListener delete);

      KnowledgeArea getSelected();
   }

   @Inject
   private View view;

   @Inject
   private IProjectsManager projectsManager;

   @PostConstruct
   public void postConstruct()
   {
      view.setDelete(delete());
   }

   @Override
   public void present(Container container)
   {
      container.add(view.asComponent());
      view.setAreas(projectsManager.getAllKnowledgeAreas());
   }

   /**
    * Create an action listener that delete an area.
    */
   private ActionListener delete()
   {
      return new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent event)
         {
            KnowledgeArea area = view.getSelected();
            if (area == null)
            {
               JOptionPane.showMessageDialog(view.asComponent(), "You have to select one area.", "Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               try
               {
                  projectsManager.deleteKnowledgeArea(area.getId());
                 
                  view.setAreas(projectsManager.getAllKnowledgeAreas());
                
                  JOptionPane.showMessageDialog(view.asComponent(), "Knowledge area " + area.getName() + " deleted.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
               }
               catch (Exception e)
               {
                  new ErrorPresenter(e, "Error deleting area", "ERROR",
                        "May be there is any project for this area.").present(view.asComponent());
               }
            }
         }
      };
   }

}