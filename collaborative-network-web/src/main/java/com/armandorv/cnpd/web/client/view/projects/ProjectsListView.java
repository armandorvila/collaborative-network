package com.armandorv.cnpd.web.client.view.projects;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.ProjectInfoProperties;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
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
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

/**
 * Partial view of projects section, this widget is supposed to be inside a tab of
 * projects view section.
 * 
 * @author armandorv
 * 
 */
public class ProjectsListView extends Composite implements ProjectsListPresenter.Display
{
   public interface ProjectsListViewUiBinder extends UiBinder<Widget, ProjectsListView>
   {
   }

   private static ProjectsListViewUiBinder uiBinder = GWT.create(ProjectsListViewUiBinder.class);

   private static ProjectInfoProperties props = GWT.create(ProjectInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem open = new MenuItem("Open");

   private MenuItem leave = new MenuItem("Leave");

   @UiField
   ColumnModel<ProjectInfo> columnModel;

   @UiField
   ListStore<ProjectInfo> store;

   @UiField
   Grid<ProjectInfo> grid;

   @UiField
   GridView<ProjectInfo> view;

   public ProjectsListView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<ProjectInfo>()
            .build(props.title())
            .build(props.area())
            .get(grid);
   }

   @UiFactory
   ColumnModel<ProjectInfo> buildColumnModel()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder()
            .build(open, icons.show16())
            .build(leave, icons.exit16())
            .get());

      return new ColumnModel<ProjectInfo>(new ColumnsBuilder<ProjectInfo>()
            .build("", props.title(), 50, true, new IconCell(icons.project16()))
            .build("Title", props.title(), 300, false)
            .build("Knowledge Area", props.area(), 250, true)
            .build("Actions", props.title(), 100, false, actions)
            .get());
   }

   @UiFactory
   ListStore<ProjectInfo> buildStore()
   {
      store = new ListStore<ProjectInfo>(props.id());
      return store;
   }

   @Override
   public ProjectInfo getSelected(int row)
   {
      return store.get(row);
   }

   @Override
   public void addProject(ProjectInfo project)
   {
      store.add(project);
   }

   @Override
   public void setProjects(List<ProjectInfo> projects)
   {
      store.clear();
      store.addAll(projects);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return this.actions;
   }

   @Override
   public void showProgress()
   {
      new ProgressView("Loading projects", "Loading ...", 3000).start();
   }

   @Override
   public void refresh()
   {
      view.refresh(false);
   }

   @Override
   public HasSelectionHandlers<Item> getOpen()
   {
      return open;
   }

   @Override
   public HasSelectionHandlers<Item> getLeave()
   {
      return leave;
   }

   @Override
   public void remove(ProjectInfo selected)
   {
      store.remove(selected);
   }
}