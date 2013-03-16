package com.armandorv.cnpd.web.client.view.projects.project.resources;

import java.util.List;

import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.GDocsResourceProperties;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.theme.client.icons.resources.ResourcesIcons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class GDocsResourcesView
{
   public interface GDocsResourcesViewUiBinder extends UiBinder<Widget, GDocsResourcesView>
   {
   }

   private static GDocsResourcesViewUiBinder uiBinder = GWT.create(GDocsResourcesViewUiBinder.class);

   private static GDocsResourceProperties props = GWT.create(GDocsResourceProperties.class);
   
   private IconCell iconCell;

   private ResourcesIcons icons = GWT.create(ResourcesIcons.class);

   private TextButtonCell importButton = new TextButtonCell();

   @UiField
   ListStore<GDocsResource> store = new ListStore<GDocsResource>(props.id());

   @UiField
   ColumnModel<GDocsResource> columnModel;

   @UiField
   Grid<GDocsResource> grid;

   @UiField
   GridView<GDocsResource> view;

   @UiField
   Dialog dialog;

   public GDocsResourcesView(Kind kind)
   {
      if(kind.equals(Kind.FOLDER))
         iconCell = new IconCell(icons.folder());
     
      if(kind.equals(Kind.DOCUMENT))
         iconCell = new IconCell(icons.document());
      
      if(kind.equals(Kind.NONE))
         iconCell = new IconCell(icons.none());
      
      uiBinder.createAndBindUi(this);
      dialog.setPredefinedButtons(PredefinedButton.CLOSE);
      
      view.setEmptyText("You haven't got resources in Google.");
   }
   
   @UiFactory
   ColumnModel<GDocsResource> buildColumnModel()
   {
      importButton.setText("Import");

      columnModel = new ColumnModel<GDocsResource>(new ColumnsBuilder<GDocsResource>()
            .build("", props.title(), 50, true , iconCell)
            .build("Title", props.title(), 300, false)
            .build("Actions", props.title(), 100, false, importButton)
            .get());
      
      return columnModel;
   }

   @UiFactory
   ListStore<GDocsResource> getStore()
   {
      return store;
   }

   public Window asWindow()
   {
      return dialog;
   }
   
   public HasSelectHandlers getImportButton(){
      return importButton;
   }
   
   public void setResources(List<GDocsResource> resoures){
      store.clear();
      store.addAll(resoures);
   }
   
   public GDocsResource getSelected(int index){
      return store.get(index);
   }

   public void showProgress()
   {
      new ProgressView("Loading gdocs Resoures", "loading ... ", 2000).start();
   }
}