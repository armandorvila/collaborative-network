package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * Widget that hold all discussion related issues.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class DiscussionsView extends Composite implements DiscussionsPresenter.Display
{
   private static DiscussionsViewUiBinder uiBinder = GWT.create(DiscussionsViewUiBinder.class);

   interface DiscussionsViewUiBinder extends UiBinder<Widget, DiscussionsView>
   {
   }

   @UiField
   TabPanel tabPanel;

   @UiField
   NewDiscussionView newDiscussionView;

   @UiField
   DiscussionsListView discussionsListView;

   public DiscussionsView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.DISCUSSIONS_INDEX;
   }
   
   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }

 
   @Produces @Singleton
   public DiscussionsListPresenter.Display produceDiscussionsListView(){
      return discussionsListView;
   }

   @Produces @Singleton
   public NewDiscussionPresenter.Display produceNewDiscussionView(){
      return newDiscussionView;
   }

   @Override
   public void selectFirst()
   {
      tabPanel.setActiveWidget(tabPanel.getWidget(0));
   }

}