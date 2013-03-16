package com.armandorv.cnpd.web.client.view.projects.project.references;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.HrefCell;
import com.armandorv.cnpd.web.client.view.util.properties.ReferenceInfoProperties;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
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

/**
 * View that show a list of references layout in a table.
 * 
 * @author armandorv
 *
 */
public class ReferencesListView extends Composite implements ReferencesListPresenter.Display
{
   public interface ReferencesListViewUiBInder extends UiBinder<Widget, ReferencesListView>
   {
   }

   private static ReferencesListViewUiBInder uiBinder = GWT.create(ReferencesListViewUiBInder.class);

   private ReferenceInfoProperties props = GWT.create(ReferenceInfoProperties.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem delete = new MenuItem("Remove");

   private MenuItem open = new MenuItem("Open");

   @UiField(provided = true)
   ColumnModel<ReferenceInfo> columnModel;

   @UiField
   ListStore<ReferenceInfo> store = new ListStore<ReferenceInfo>(props.id());

   @UiField
   Grid<ReferenceInfo> references;

   @UiField
   GridView<ReferenceInfo> view;

   public ReferencesListView()
   {
      this.columnModel = buildColumnModel();
     
      super.initWidget(uiBinder.createAndBindUi(this));
     
      new FiltersBuilder<ReferenceInfo>()
            .build(props.name()).build(props.url())
            .get(references);
   }

   private ColumnModel<ReferenceInfo> buildColumnModel()
   {
      actions.setText("Select");
      actions.setMenu(new MenuBuilder()
            .build(open, icons.mailOpen16())
            .build(delete, icons.delete16())
            .get());

      return new ColumnModel<ReferenceInfo>(new ColumnsBuilder<ReferenceInfo>()
            .build("Name", props.name(), 400, false)
            .build("URL", props.url(), 700, false, new HrefCell())
            .build("Actions", props.name(), 150, false, actions)
            .get());
   }

   @UiFactory
   ColumnModel<ReferenceInfo> createColumnModel()
   {
      return columnModel;
   }

   @UiFactory
   ListStore<ReferenceInfo> createListStore()
   {
      return store;
   }

   /* ****************** Display methods ************* */
   @Override
   public void setReferences(List<ReferenceInfo> references)
   {
      store.clear();
      store.addAll(references);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   @Override
   public HasSelectionHandlers<Item> getOpen()
   {
      return open;
   }

   @Override
   public ReferenceInfo getReference(int index)
   {
      return store.get(index);
   }

   @Override
   public void removeReference(ReferenceInfo reference)
   {
     store.remove(reference);
   }
}