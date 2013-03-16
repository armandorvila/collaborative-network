package com.armandorv.cnpd.web.client.view.messages;

import java.util.List;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.BooleanIconCell;
import com.armandorv.cnpd.web.client.view.util.properties.MessageInfoProperties;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
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
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

/**
 * View for messages section of the main panel
 * 
 * @author armandorv
 * 
 */
@Singleton
public class MessagesView extends Composite implements MessagesPresenter.Display
{
   public interface MessagesViewUiBinder extends UiBinder<Widget, MessagesView>
   {
   }

   private static MessagesViewUiBinder uiBinder = GWT.create(MessagesViewUiBinder.class);

   private MessageInfoProperties props = GWT.create(MessageInfoProperties.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell button = new TextButtonCell();

   private MenuItem show = new MenuItem("Show");

   private MenuItem delete = new MenuItem("Delete");

   @UiField(provided = true)
   ColumnModel<MessageInfo> columnModel;

   @UiField
   ListStore<MessageInfo> store;

   @UiField
   Grid<MessageInfo> grid;

   @UiField
   GridView<MessageInfo> view;

   @UiField
   PagingToolBar toolBar;

   /* *********************************************** */
   public MessagesView()
   {
      button.setText("Select");

      button.setMenu(new MenuBuilder()
            .build(show, icons.contact16())
            .build(delete, icons.delete16())
            .get());

      Cell<Boolean> booleanCell = new BooleanIconCell(icons.mailOpen16(),icons.mail16());
      
      columnModel = new ColumnModel<MessageInfo>(new ColumnsBuilder<MessageInfo>()
            .buildBoolean("Read", props.read(), 60, false, booleanCell)
            .buildDate("Date", props.date(), 150, false,
                  new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)))
            .build("Sender", props.sender(), 200, true)
            .build("Actions", props.sender(), 70, false, button)
            .get());

      super.initWidget(uiBinder.createAndBindUi(this));
      
      new FiltersBuilder<MessageInfo>()
      .build(props.sender())
      .buildDate(props.date())
      .get(grid);

      this.grid.getView().setForceFit(true);
   }

   @UiFactory
   ColumnModel<MessageInfo> createColumnModel()
   {
      return columnModel;
   }

   @UiFactory
   ListStore<MessageInfo> createListStore()
   {
      store = new ListStore<MessageInfo>(props.id());
      return store;
   }

   /* ************ Display complaint methods *********** */
   @Override
   public void setMessages(List<MessageInfo> messages)
   {
      this.store.clear();
      this.store.addAll(messages);
   }

   @Override
   public void addMessage(MessageInfo message)
   {
      this.store.add(message);
   }

   @Override
   public HasSelectionHandlers<Item> getShowClickable()
   {
      return show;
   }

   @Override
   public HasSelectionHandlers<Item> getDeleteClickable()
   {
      return delete;
   }

   @Override
   public Integer getIndex()
   {
      return MainWindow.Indexes.MESSAGES_INDEX;
   }

   @Override
   public HasSelectHandlers getSelectable()
   {

      return button;
   }

   @Override
   public MessageInfo getSelected(int row)
   {
      return store.get(row);
   }

   @Override
   public void removeMessage(MessageInfo selected)
   {
      store.remove(selected);
   }
   
   @Override
   public void showProgress()
   {
      new ProgressView("Loading messages", "Loading ...", 2000).start();
   }

   @Override
   public void refresh()
   {
      view.refresh(false);
   }
}