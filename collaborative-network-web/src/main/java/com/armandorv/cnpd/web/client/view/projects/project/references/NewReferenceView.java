package com.armandorv.cnpd.web.client.view.projects.project.references;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter;
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
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * View that allow the addition of new references to project.
 * 
 * @author armandorv
 *
 */
public class NewReferenceView extends Composite implements NewReferencePresenter.Display
{
   public interface NewReferenceUiBinder extends UiBinder<Widget, NewReferenceView>
   {
   }

   private static NewReferenceUiBinder uiBinder = GWT.create(NewReferenceUiBinder.class);

   private static ReferenceInfoProperties props = GWT.create(ReferenceInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem open = new MenuItem("Open");

   private MenuItem add = new MenuItem("Add");

   @UiField
   TextField name;

   @UiField
   TextField url;

   @UiField
   TextField keyWords;

   @UiField
   TextButton createButton;

   @UiField
   TextButton searchButton;

   @UiField(provided = true)
   ColumnModel<ReferenceInfo> columnModel;

   @UiField(provided = true)
   ListStore<ReferenceInfo> store = new ListStore<ReferenceInfo>(props.id());

   @UiField
   Grid<ReferenceInfo> grid;

   @UiField
   GridView<ReferenceInfo> view;

   public NewReferenceView()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder()
            .build(open, icons.magnifing18())
            .build(add, icons.new16())
            .get());

      columnModel = new ColumnModel<ReferenceInfo>(new ColumnsBuilder<ReferenceInfo>()
            .build("Name", props.name(), 400, false)
            .build("URL", props.url(), 700, false, new HrefCell())
            .build("Actions", props.name(), 150, false, actions)
            .get());

      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<ReferenceInfo>()
            .build(props.name())
            .build(props.url())
            .get(grid);
   }

   /* ******************** Display complaint methods ********** */

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
   public HasSelectionHandlers<Item> getOpen()
   {
      return open;
   }

   @Override
   public HasSelectionHandlers<Item> getAdd()
   {
      return add;
   }

   @Override
   public HasSelectHandlers getSearch()
   {
      return searchButton;
   }

   @Override
   public HasSelectHandlers getCreate()
   {
      return createButton;
   }

   @Override
   public String getName()
   {
      return name.getText();
   }

   @Override
   public String getURL()
   {
      return url.getText();
   }

   @Override
   public String getKeyWords()
   {
      return keyWords.getText();
   }

   @Override
   public void clear()
   {
      store.clear();
      name.setText("");
      url.setText("");
   }

   @Override
   public ReferenceInfo getReference(int index)
   {
      return store.get(index);
   }

}