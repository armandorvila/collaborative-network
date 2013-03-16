package com.armandorv.cnpd.web.client.presenter.projects.project.discussions;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

@Singleton
public class NewDiscussionPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      HasSelectHandlers getCreate();

      void showMessage(String message);

      String getDiscussionTitle();

      String getDescription();

      List<IdNameGenericInfo> getOptions();

      void clear();
   }

   private ProjectInfo projectInfo;

   @Inject
   private Display display;

   @Inject
   private Caller<DiscussionsService> discussionsService;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getCreate().addSelectHandler(create());
   }

   @Override
   public void present()
   {
   }

   public void setProjectInfo(ProjectInfo projectInfo)
   {
      this.projectInfo = projectInfo;
   }

   private SelectHandler create()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            DiscussionInfo discussion = new DiscussionInfo(-1, display.getDiscussionTitle(), display.getDescription());
            discussion.setOptions(display.getOptions());

            discussionsService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                     display.clear();

                  BooleanMessenger.getMessenger("Discussion created.", "Error creating discussion.")
                        .message(response);
               }
            }).addDiscussion(projectInfo.getId(), discussion);
         }
      };
   }

}