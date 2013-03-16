package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsDetailsPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

/**
 * View that hold a dialog to show details of a discussions.
 * 
 * @author armandorv
 * 
 */
public class DiscussionDetailsView implements DiscussionsDetailsPresenter.Display
{
   public interface DiscussionDetailsViewUiBinder extends UiBinder<Widget, DiscussionDetailsView>
   {
   }

   private static DiscussionDetailsViewUiBinder uiBinder = GWT.create(DiscussionDetailsViewUiBinder.class);
   
   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   @UiField
   Dialog detailsDialog;

   @UiField
   TextField title;

   @UiField
   TextArea description;
   
   @UiField
   ListStore<IdNameGenericInfo> store = new ListStore<IdNameGenericInfo>(props.id());
   
   @UiField
   ColumnModel<IdNameGenericInfo> columnModel;

   @UiField
   Grid<IdNameGenericInfo> grid;

   @UiField
   GridView<IdNameGenericInfo> view;
   
   @UiFactory
   ColumnModel<IdNameGenericInfo> buildColumnModel()
   {
      return new ColumnModel<IdNameGenericInfo>(new ColumnsBuilder<IdNameGenericInfo>()
            .build("Name", props.name(), 300, false)
            .get());
   }

   @UiFactory
   ListStore<IdNameGenericInfo> getStore()
   {
      return store;
   }

   public DiscussionDetailsView()
   {
      uiBinder.createAndBindUi(this);
      detailsDialog.setPredefinedButtons(PredefinedButton.CLOSE);
   }

   @Override
   public Window asWindow()
   {
      return detailsDialog;
   }

   @Override
   public void setTitle(String title)
   {
      this.title.setText(title);
   }

   @Override
   public void setDescription(String description)
   {
      this.description.setText(description);
   }

   @Override
   public void setOptions(List<IdNameGenericInfo> options)
   {
      store.clear();
      store.addAll(options);
   }

}