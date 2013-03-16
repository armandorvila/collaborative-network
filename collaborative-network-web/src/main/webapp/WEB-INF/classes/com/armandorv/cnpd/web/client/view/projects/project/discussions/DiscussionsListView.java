package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.BooleanIconCell;
import com.armandorv.cnpd.web.client.view.util.properties.DiscussionInfoProperties;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * View that show a list of discussion and offer several options for them.
 * 
 * @author armandorv
 *
 */
public class DiscussionsListView extends Composite implements DiscussionsListPresenter.Display
{
   public interface DiscussionsListViewUiBinder extends UiBinder<Widget, DiscussionsListView>
   {
   }

   private static DiscussionsListViewUiBinder uiBinder = GWT.create(DiscussionsListViewUiBinder.class);

   private static DiscussionInfoProperties props = GWT.create(DiscussionInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem details = new MenuItem("Details");

   private MenuItem vote = new MenuItem("Vote");

   private MenuItem results = new MenuItem("Results");

   private MenuItem close = new MenuItem("Close");

   private MenuItem delete = new MenuItem("Delete");

   @UiField
   ColumnModel<DiscussionInfo> columnModel;

   @UiField
   ListStore<DiscussionInfo> store = new ListStore<DiscussionInfo>(props.id());

   @UiField
   Grid<DiscussionInfo> grid;

   @UiField
   GridView<DiscussionInfo> view;

   public DiscussionsListView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<DiscussionInfo>()
            .buildBoolean(props.open())
            .build(props.title())
            .get(grid);
   }

   @UiFactory
   ColumnModel<DiscussionInfo> buildColumnModel()
   {
      Cell<Boolean> booleanIconCell = new BooleanIconCell(icons.open16(), icons.closed16());

      actions.setText("Select");
      actions.setMenu(new MenuBuilder()
            .build(details, icons.show16())
            .build(vote, icons.mail16())
            .build(results, icons.mail16())
            .build(close, icons.closed16())
            .build(delete, icons.delete16())
            .get());

      return new ColumnModel<DiscussionInfo>(new ColumnsBuilder<DiscussionInfo>()
            .buildBoolean("Open", props.open(), 50, false, booleanIconCell)
            .build("Title", props.title(), 300, false)
            .build("Actions", props.title(), 50, false, actions)
            .get());
   }

   @UiFactory
   ListStore<DiscussionInfo> getStore()
   {
      return store;
   }

   @Override
   public void setDiscussions(List<DiscussionInfo> discussions)
   {
      store.clear();
      store.addAll(discussions);
   }

   @Override
   public DiscussionInfo getDiscussion(int row)
   {
      return store.get(row);
   }

   @Override
   public HasSelectionHandlers<Item> getDetails()
   {
      return details;
   }

   @Override
   public HasSelectionHandlers<Item> getVote()
   {
      return vote;
   }
   
   @Override
   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   @Override
   public HasSelectionHandlers<Item> getClose()
   {
      return close;
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public void setDeleteEnabled(boolean enabled)
   {
      delete.setEnabled(enabled);
   }

   @Override
   public void setVoteEnabled(boolean enabled)
   {
      vote.setEnabled(enabled);
   }

   @Override
   public void setResultsEnabled(boolean enabled)
   {
      results.setEnabled(enabled);
   }

   @Override
   public void setCloseEnabled(boolean enabled)
   {
      close.setEnabled(enabled);
   }

   @Override
   public HasSelectionHandlers<Item> getResults()
   {
      return results;
   }

   @Override
   public void removeDiscussion(DiscussionInfo selected)
   {
      store.remove(selected);
   }

}