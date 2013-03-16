package com.armandorv.cnpd.web.client.presenter.projects.project.discussions;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.sencha.gxt.widget.core.client.Window;

@Singleton
public class DiscussionsDetailsPresenter implements Presenter
{
   public interface Display
   {
      Window asWindow();

      void setTitle(String title);

      void setDescription(String description);
      
      void setOptions(List<IdNameGenericInfo> options);
   }

   private DiscussionInfo discussion;

   @Inject
   private Display display;

   @Override
   public void present()
   {
      if (discussion == null)
         throw new ClientsideException("A discussion must be set.");

      display.setTitle(discussion.getTitle());
      display.setDescription(discussion.getDescription());
      display.setOptions(discussion.getOptions());
      
      display.asWindow().show();
   }

   public void setDiscussion(DiscussionInfo discussion)
   {
      this.discussion = discussion;
   }
}