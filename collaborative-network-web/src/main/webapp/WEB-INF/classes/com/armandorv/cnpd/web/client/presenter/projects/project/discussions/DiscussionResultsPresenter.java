package com.armandorv.cnpd.web.client.presenter.projects.project.discussions;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.sencha.gxt.widget.core.client.Window;

@Singleton
public class DiscussionResultsPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setVotes(List<VoteInfo> votes);
   }

   @Inject
   private Display display;

   private DiscussionInfo discussion;

   @Override
   public void present()
   {
      if (discussion == null)
         throw new ClientsideException("A discussion must be set.");

      display.setVotes(discussion.getVotes());
      display.asWindow().show();
   }

   public void setDiscussion(DiscussionInfo discussion)
   {
      this.discussion = discussion;
   }

}