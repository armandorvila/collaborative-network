package com.armandorv.cnpd.web.client.presenter.projects;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.ioc.client.api.AfterInitialization;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the ProjectsView select the correct presenter for the projects
 * view subsection selected.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class ProjectsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();

      void selectFirstTab();
   }

   @Inject
   private ProjectsListPresenter projectsListPresenter;

   @Inject
   private FindProjectsPresenter findProjectsPresenter;

   @Inject
   private NewProjectPresenter newProjectPresenter;

   @Inject
   private ProjectInvitationsPresenter projectInvitationsPresenter;

   @Inject
   private Display display;

   @Inject
   @Main
   private Event<Integer> tabSelectedEvent;

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

            if (widget instanceof ProjectsListPresenter.Display)
               projectsListPresenter.present();

            if (widget instanceof FindProjectsPresenter.Display)
               findProjectsPresenter.present();

            if (widget instanceof NewProjectPresenter.Display)
               newProjectPresenter.present();

            if (widget instanceof ProjectInvitationsPresenter.Display)
               projectInvitationsPresenter.present();
         }
      };
   }

   @Override
   public void present()
   {
      projectsListPresenter.present();
      display.selectFirstTab();
      tabSelectedEvent.fire(display.getIndex());
   }

   public Display getDisplay()
   {
      return display;
   }

}