package com.armandorv.cnpd.web.client.presenter.projects.project.discussions;

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

/**
 * Presenter for Discussions view,deal with DiscussionsView widget as a unique
 * widget, ignore others childs of DiscussionsView.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class DiscussionsPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      Integer getIndex();

      HasSelectionHandlers<Widget> getTabPanel();

      void selectFirst();
   }

   @Inject
   @Project
   private Event<Integer> tabSelectedEvent;

   @Inject
   private Display display;

   @Inject
   private DiscussionsListPresenter discussionsListPresenter;

   @Inject
   private NewDiscussionPresenter newDiscussionPresenter;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getTabPanel().addSelectionHandler(selectPresenter());
   }

   private ProjectInfo projectInfo;

   private SelectionHandler<Widget> selectPresenter()
   {
      return new SelectionHandler<Widget>()
      {
         @Override
         public void onSelection(SelectionEvent<Widget> event)
         {
            Widget view = event.getSelectedItem();

            if (view instanceof DiscussionsListPresenter.Display)
            {
               discussionsListPresenter.setProjectInfo(projectInfo);
               discussionsListPresenter.present();
            }
            if (view instanceof NewDiscussionPresenter.Display)
            {
               newDiscussionPresenter.setProjectInfo(projectInfo);
               newDiscussionPresenter.present();
            }
         }
      };
   }

   @Override
   public void present()
   {
      if (projectInfo == null)
         throw new ClientsideException("A project must be setted.");

      discussionsListPresenter.setProjectInfo(projectInfo);
      discussionsListPresenter.present();
     
      display.selectFirst();
      
      tabSelectedEvent.fire(display.getIndex());
   }

   public Display getDisplay()
   {
      return display;
   }

   public void setProjectInfo(@Observes ProjectInfo projectInfo)
   {
      this.projectInfo = projectInfo;
   }

}