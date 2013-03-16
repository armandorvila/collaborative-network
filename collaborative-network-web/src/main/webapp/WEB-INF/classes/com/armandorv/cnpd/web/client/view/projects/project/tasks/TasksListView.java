package com.armandorv.cnpd.web.client.view.projects.project.tasks;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.BooleanIconCell;
import com.armandorv.cnpd.web.client.view.util.properties.TaskInfoProperties;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class TasksListView extends Composite implements TasksListPresenter.Display
{
   public interface TasksListViewUiBinder extends UiBinder<Widget, TasksListView>
   {
   }

   private static TasksListViewUiBinder uiBinder = GWT.create(TasksListViewUiBinder.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private static TaskInfoProperties props = GWT.create(TaskInfoProperties.class);

   private PromptMessageBox hoursMessageBox = new PromptMessageBox("Add hours to task", "Introduce hours.");

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem work = new MenuItem("work");

   private MenuItem delete = new MenuItem("Delete");

   private MenuItem done = new MenuItem("Done");

   @UiField(provided = true)
   ListStore<TaskInfo> store = new ListStore<TaskInfo>(props.id());

   @UiField(provided = true)
   ColumnModel<TaskInfo> columnModel;

   @UiField
   Grid<TaskInfo> grid;

   @UiField
   GridView<TaskInfo> view;

   public TasksListView()
   {
      actions.setText("Actions");

      actions.setMenu(new MenuBuilder()
            .build(work, icons.preferences16())
            .build(done, icons.show16())
            .build(delete, icons.delete16())
            .get());

      this.columnModel = new ColumnModel<TaskInfo>(new ColumnsBuilder<TaskInfo>()
            .buildBoolean("Completed", props.completed(), 100, false, new BooleanIconCell(icons.delete16()))
            .build("Name", props.name(), 300, false)
            .buildDate("Beguining", props.start(), 220, true,
                  new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM)))
            .buildInteger("Total Hours", props.hours(), 120, true)
            .buildInteger("Worked Hours", props.workedHours(), 120, true)
            .build("Actions", props.name(), 100, false, actions)
            .get());
      
      super.initWidget(uiBinder.createAndBindUi(this));
      
      view.setEmptyText("There is no tasks.");
   }

   @Override
   public void setTasks(List<TaskInfo> tasks)
   {
      store.clear();
      store.addAll(tasks);
   }

   @Override
   public TaskInfo getTask(int row)
   {
      return store.get(row);
   }

   @Override
   public void removeTask(TaskInfo task)
   {
      store.remove(task);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public HasSelectionHandlers<Item> getWork()
   {
      return work;
   }

   @Override
   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   @Override
   public HasSelectionHandlers<Item> getDone()
   {
      return done;
   }

   @Override
   public PromptMessageBox getHoursMessageBox()
   {
      hoursMessageBox.clear();
      return hoursMessageBox;
   }

   @Override
   public void refresh()
   {
      view.refresh(false);
   }

}