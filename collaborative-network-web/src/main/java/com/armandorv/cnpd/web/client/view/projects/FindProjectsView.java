package com.armandorv.cnpd.web.client.view.projects;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.client.view.util.properties.ProjectInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class FindProjectsView extends Composite implements FindProjectsPresenter.Display
{
   public interface FindProjectsViewUiBinder extends UiBinder<Widget, FindProjectsView>
   {
   }

   private static FindProjectsViewUiBinder uiBinder = GWT.create(FindProjectsViewUiBinder.class);

   private static IdNameGenericInfoProperties kaProps = GWT.create(IdNameGenericInfoProperties.class);

   private static ProjectInfoProperties props = GWT.create(ProjectInfoProperties.class);
   
   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private  ListStore<IdNameGenericInfo> kaStore = new ListStore<IdNameGenericInfo>(kaProps.id());

   private TextButtonCell open = new TextButtonCell();

   @UiField
   ColumnModel<ProjectInfo> columnModel;

   @UiField
   ListStore<ProjectInfo> store;

   @UiField
   Grid<ProjectInfo> grid;

   @UiField
   GridView<ProjectInfo> view;

   @UiField(provided = true)
   ComboBox<IdNameGenericInfo> combo = new ComboBox<IdNameGenericInfo>(kaStore, kaProps.labelName());

   @UiField
   TextField title;

   @UiField
   TextButton searchButton;

   public FindProjectsView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
      new FiltersBuilder<ProjectInfo>()
            .build(props.title())
            .build(props.area())
            .get(grid);

      combo.setText("--Selet a Knowledge area --");
      searchButton.setIcon(icons.magnifing18());
   }

   @UiFactory
   ColumnModel<ProjectInfo> buildColumnModel()
   {
      open.setText("Open");
      open.setIcon(icons.show16());

      return new ColumnModel<ProjectInfo>(new ColumnsBuilder<ProjectInfo>()
            .build("", props.title(), 50, true, new IconCell(icons.project16()))
            .build("title", props.title(), 260, false)
            .build("Knowledge area", props.area(), 260, true)
            .build("Actions", props.title(), 80, true, open)
            .get());
   }

   @UiFactory
   ListStore<ProjectInfo> buildStore()
   {
      store = new ListStore<ProjectInfo>(props.id());
      return store;
   }

   @Override
   public void setKnowledgeAreas(List<IdNameGenericInfo> kas)
   {
      this.kaStore.clear();
      this.kaStore.addAll(kas);
   }

   @Override
   public String getSearchTitle()
   {
      return title.getText();
   }

   @Override
   public HasSelectHandlers getSearchButton()
   {
      return searchButton;
   }

   @Override
   public IdNameGenericInfo getSearchKnowledgeArea()
   {
      return combo.getCurrentValue();
   }

   @Override
   public void setFoundProjects(List<ProjectInfo> projects)
   {
      store.clear();
      store.addAll(projects);
   }

   @Override
   public HasSelectHandlers getOpenButton()
   {
      return open;
   }

   @Override
   public ProjectInfo getSelected(int row)
   {
      return store.get(row);
   }

}