package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionResultsPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.VoteInfoProperties;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class DiscussionResultsView implements DiscussionResultsPresenter.Display
{
   public interface DiscussionResultsViewUiBinder extends UiBinder<Widget, DiscussionResultsView>
   {
   }

   private DiscussionResultsViewUiBinder uiBinder = GWT.create(DiscussionResultsViewUiBinder.class);

   private VoteInfoProperties props = GWT.create(VoteInfoProperties.class);

   @UiField
   ListStore<VoteInfo> store = new ListStore<VoteInfo>(props.userId());

   @UiField
   ColumnModel<VoteInfo> columnModel;

   @UiField
   Grid<VoteInfo> grid;

   @UiField
   GridView<VoteInfo> view;

   @UiField
   Dialog dialog;

   public DiscussionResultsView()
   {
      uiBinder.createAndBindUi(this);
      dialog.setPredefinedButtons(PredefinedButton.CLOSE);
   }
   
   @UiFactory
   ColumnModel<VoteInfo> buildColumnModel()
   {
      return new ColumnModel<VoteInfo>(new ColumnsBuilder<VoteInfo>()
            .build("Option", props.option(), 200, false)
            .build("User", props.voterName(), 200, false)
            .build("Argument", props.argument(), 400, false)
            .get());
   }

   @UiFactory
   ListStore<VoteInfo> getStore()
   {
      return store;
   }

   @Override
   public Window asWindow()
   {
      return dialog;
   }

   @Override
   public void setVotes(List<VoteInfo> votes)
   {
      store.clear();
      store.addAll(votes);
   }

}