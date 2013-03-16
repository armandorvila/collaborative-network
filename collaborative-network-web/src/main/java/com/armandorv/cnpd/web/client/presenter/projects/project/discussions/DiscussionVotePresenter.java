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
import com.armandorv.cnpd.web.shared.exception.ServersideException;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * 
 * @author armandorv
 *
 */
@Singleton
public class DiscussionVotePresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setOptions(List<IdNameGenericInfo> options);

      IdNameGenericInfo getOption();

      String getArgument();

      HasSelectHandlers getVote();

      void clear();
   }

   @Inject
   private Display display;

   @Inject
   private Caller<DiscussionsService> discussionsService;

   private UserInfo user;

   private DiscussionInfo discussion;

   private AlertMessageBox alert;

   @AfterInitialization
   public void afterInitialization()
   {
      if(display == null)
         Info.display("Injection error" , "");
         else 
      display.getVote().addSelectHandler(vote());
    
      alert = new AlertMessageBox("Inavlid Action", " You have already vote.");
   }

   @Override
   public void present()
   {
      if (user == null)
         throw new ServersideException("A user must be set.");

      if (discussion == null)
         throw new ServersideException("A discussion must be set.");

      if (userAlreadyVote())
      {
         alert.show();
      }
      else
      {  
         display.clear();
         display.setOptions(discussion.getOptions());
         display.asWindow().show();
      }
   }

   private boolean userAlreadyVote()
   {
      boolean already = false;

      for (VoteInfo vote : discussion.getVotes())
      {
         if (vote.getUserId() == user.getId())
            return true;
      }

      return already;
   }

   private SelectHandler vote()
   {

      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            VoteInfo vote = new VoteInfo();

            vote.setOption(display.getOption().getName());
            vote.setArgument(display.getArgument());
            vote.setUserId(user.getId());
            vote.setVoterName(user.getFullName());

            discussion.getVotes().add(vote);

            discussionsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  BooleanMessenger.getMessenger("Your vote has been proceed succesfullly.",
                        "Your vote has been proceed succesfullly.").message(response);
                  display.asWindow().hide();
               }
            }).vote(discussion.getId(), vote);
         }
      };
   }

   public void setDiscussion(DiscussionInfo discussion)
   {
      this.discussion = discussion;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }
}