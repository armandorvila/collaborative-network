package com.armandorv.cnpd.web.client.view.notifications;

import java.util.List;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.BooleanIconCell;
import com.armandorv.cnpd.web.client.view.util.properties.NotificationInfoProperties;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
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

/**
 * View for news section of central panel, show a table with all notifications of logged in user.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class NotificationsView extends Composite implements NotificationsPresenter.Display
{
   public interface NotificationsViewUiBinder extends UiBinder<Widget, NotificationsView>
   {
   }

   private NotificationsViewUiBinder uiBinder = GWT.create(NotificationsViewUiBinder.class);

   private NotificationInfoProperties props = GWT.create(NotificationInfoProperties.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   @UiField(provided = true)
   ColumnModel<NotificationInfo> columnModel;

   @UiField
   ListStore<NotificationInfo> store;

   @UiField
   Grid<NotificationInfo> grid;

   @UiField
   GridView<NotificationInfo> view;

   public NotificationsView()
   {
      actions.setText("Details");
      actions.setIcon(icons.show16());

      columnModel = new ColumnModel<NotificationInfo>(new ColumnsBuilder<NotificationInfo>()
            .buildBoolean("New", props.isNew(), 50, true, new BooleanIconCell(icons.new16()))
            .build("Notification", props.name(), 50, true)
            .buildDate("Date", props.date(), 50, true,
                  new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM)))
            .build("", props.name(), 50, true, actions)
            .get());

      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<NotificationInfo>()
            .buildBoolean(props.isNew())
            .build(props.name())
            .buildDate(props.date())
            .get(grid);
   }

   @UiFactory
   ColumnModel<NotificationInfo> createColumnModel()
   {
      return columnModel;
   }

   @UiFactory
   ListStore<NotificationInfo> createListStore()
   {
      store = new ListStore<NotificationInfo>(props.id());
      return store;
   }

   /* ****************** Display complaint methods ************* */
   @Override
   public void setNotifications(List<NotificationInfo> notifications)
   {
      this.store.clear();
      this.store.addAll(notifications);
   }

   @Override
   public void addNotification(NotificationInfo notification)
   {
      this.store.add(notification);
   }

   @Override
   public Integer getIndex()
   {
      return MainWindow.Indexes.NEWS_INDEX;
   }

   @Override
   public HasSelectHandlers getSelectable()
   {
      return actions;
   }

   @Override
   public NotificationInfo getSelected(int row)
   {
      return store.get(row);
   }

   @Override
   public void setAsNotified(NotificationInfo notification)
   {
      store.remove(notification);
      store.add(notification);
   }
   
   @Override
   public void showProgress()
   {
      new ProgressView("Loading notifications", "Loading ...", 2000).start();
   }
}