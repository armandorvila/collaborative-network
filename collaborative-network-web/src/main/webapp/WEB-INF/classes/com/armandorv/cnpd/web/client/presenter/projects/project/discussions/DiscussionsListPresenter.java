package com.armandorv.cnpd.web.client.presenter.projects.project.discussions;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

@Singleton
public class DiscussionsListPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setDiscussions(List<DiscussionInfo> discussions);

      DiscussionInfo getDiscussion(int row);

      HasSelectionHandlers<Item> getDetails();

      HasSelectionHandlers<Item> getVote();

      HasSelectionHandlers<Item> getResults();

      HasSelectionHandlers<Item> getDelete();

      HasSelectionHandlers<Item> getClose();

      HasSelectHandlers getActions();

      void setDeleteEnabled(boolean enabled);

      void setCloseEnabled(boolean enabled);

      void setVoteEnabled(boolean enabled);

      void setResultsEnabled(boolean enabled);

      void removeDiscussion(DiscussionInfo selected);
   }

   @Inject
   private Caller<DiscussionsService> discussionsService;

   @Inject
   private DiscussionsDetailsPresenter detailsPresenter;

   @Inject
   private DiscussionVotePresenter votePresenter;

   @Inject
   private DiscussionResultsPresenter discussionsResultsPresenter;

   @Inject
   private Display display;

   private ProjectInfo project;

   private DiscussionInfo selected;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());

      display.getDetails().addSelectionHandler(details());
      display.getVote().addSelectionHandler(vote());
      display.getResults().addSelectionHandler(results());
      display.getClose().addSelectionHandler(close());
      display.getDelete().addSelectionHandler(delete());
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be setted.");

      display.setDeleteEnabled(user.getId() == project.getManagerId());
      display.setCloseEnabled(user.getId() == project.getManagerId());

      loadDiscussions();
   }

   /**
    * Load all discussion of this project.
    */
   private void loadDiscussions()
   {
      discussionsService.call(new RemoteCallback<List<DiscussionInfo>>()
      {
         @Override
         public void callback(List<DiscussionInfo> response)
         {
            display.setDiscussions(response);
         }
      }).getDiscussions(project.getId());
   }

   /**
    * Set the select discussion.
    */
   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getDiscussion(event.getContext().getIndex());

            display.setResultsEnabled(!selected.isOpen());
            display.setResultsEnabled(user.getId() == project.getManagerId());
            display.setVoteEnabled(selected.isOpen());
         }
      };
   }

   private SelectionHandler<Item> details()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            detailsPresenter.setDiscussion(selected);
            detailsPresenter.present();
         }
      };
   }

   private SelectionHandler<Item> vote()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            votePresenter.setDiscussion(selected);
            votePresenter.present();
         }
      };
   }

   private SelectionHandler<Item> results()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            discussionsResultsPresenter.setDiscussion(selected);
            discussionsResultsPresenter.present();
         }
      };
   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            discussionsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Discussion deleted successfully", "Error deleting discussion.")
                        .message(response);
                  
                  if (response)
                     display.removeDiscussion(selected);
               }
            }).deleteDiscussion(selected.getId());
         }
      };
   }

   private SelectionHandler<Item> close()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            discussionsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Discussion closed successfully", "Error closing discussion.")
                        .message(response);
                 
                  if (response)
                     selected.setOpen(false);

               }
            }).closeDiscussion(selected.getId());
         }
      };
   }

   public void setProjectInfo(ProjectInfo project)
   {
      this.project = project;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }

}