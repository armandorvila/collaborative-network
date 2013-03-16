package com.armandorv.cnpd.web.client.view.projects;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter;
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
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ProjectInvitationsView extends Composite implements ProjectInvitationsPresenter.Display
{
   public interface ProjectInvitationsViewUiBinder extends UiBinder<Widget, ProjectInvitationsView>
   {
   }

   private static ProjectInvitationsViewUiBinder uiBinder = GWT.create(ProjectInvitationsViewUiBinder.class);
   
   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private static ProjectInfoProperties props = GWT.create(ProjectInfoProperties.class);

   private MenuItem accept = new MenuItem("accept");

   private MenuItem refuse = new MenuItem("refuse");

   private MenuItem details = new MenuItem("details");

   private TextButtonCell actions = new TextButtonCell();

   @UiField
   ListStore<ProjectInfo> store;

   @UiField
   ColumnModel<ProjectInfo> columnModel;

   @UiField
   Grid<ProjectInfo> grid;

   @UiField
   GridView<ProjectInfo> view;

   public ProjectInvitationsView()
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
            .build(accept)
            .build(refuse)
            .build(details)
            .get());

      return new ColumnModel<ProjectInfo>(new ColumnsBuilder<ProjectInfo>()
            .build("", props.title(), 50, true, new IconCell(icons.project16()))
            .build("Title", props.title(), 300, false)
            .build("Knowledge Area", props.area(), 250, true)
            .build("Actions", props.title(), 100, true, actions)
            .get());
   }

   @UiFactory
   ListStore<ProjectInfo> buildStore()
   {
      store = new ListStore<ProjectInfo>(props.id());
      return store;
   }

   @Override
   public void setInvitations(List<ProjectInfo> invitations)
   {
      store.clear();
      store.addAll(invitations);
   }

   @Override
   public ProjectInfo getInvitation(int row)
   {
      return store.get(row);
   }

   @Override
   public void removeInvitation(ProjectInfo selected)
   {
      store.remove(selected);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public HasSelectionHandlers<Item> getAccept()
   {
      return accept;
   }

   @Override
   public HasSelectionHandlers<Item> getRefuse()
   {
      return refuse;
   }

   @Override
   public HasSelectionHandlers<Item> getDetails()
   {
      return details;
   }

   @Override
   public void showDetails(ProjectInfo selected)
   {
      new Dialog().show();
   }

   @Override
   public void addSuccessMessage(String string)
   {
      Info.display("Success", string);
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Error", string);
   }
}