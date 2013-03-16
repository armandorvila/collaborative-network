package com.armandorv.cnpd.web.client.presenter.projects.project.references;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.ioc.client.api.AfterInitialization;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class ReferencesPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();

      void selectFirst();
   }

   private ProjectInfo project;
   
   @Inject
   private Display display;

   @Inject
   @Project
   private Event<Integer> tabSelecteedEvent;

   @Inject
   private ReferencesListPresenter referencesListPresenter;

   @Inject
   private NewReferencePresenter newReferencePresenter; 

   @AfterInitialization
   public void afterInitialization()
   {
      display.getTabPanel().addSelectionHandler(this.selectPrsenter());
   }

   private SelectionHandler<Widget> selectPrsenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            Widget widget = event.getSelectedItem();

            if (widget instanceof ReferencesListPresenter.Display)
            {
               referencesListPresenter.setProject(project);
               referencesListPresenter.present();
            }
            if (widget instanceof NewReferencePresenter.Display)
            {
               newReferencePresenter.setProject(project);
               newReferencePresenter.present();
            }
         }
      };
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be setted");

      referencesListPresenter.setProject(project);
      referencesListPresenter.present();
      
      display.selectFirst();
      
      tabSelecteedEvent.fire(display.getIndex());
   }

   public Display getDisplay()
   {
      return display;
   }
   
   public void setProject(@Observes ProjectInfo project)
   {
      this.project = project;
   }

}