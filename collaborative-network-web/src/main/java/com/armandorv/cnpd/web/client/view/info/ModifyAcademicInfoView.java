package com.armandorv.cnpd.web.client.view.info;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.info.ModifyAcademicInfoPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ModifyAcademicInfoView implements ModifyAcademicInfoPresenter.Display
{

   public interface ModifyAcademicInfoViewUiBinder extends UiBinder<Widget, ModifyAcademicInfoView>
   {
   }

   private static ModifyAcademicInfoViewUiBinder uiBinder = GWT.create(ModifyAcademicInfoViewUiBinder.class);

   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private ListStore<IdNameGenericInfo> degreesAll = new ListStore<IdNameGenericInfo>(props.id());

   private ListStore<IdNameGenericInfo> degreesSelected = new ListStore<IdNameGenericInfo>(props.id());

   private MenuItem delete = new MenuItem("Delete");

   @UiField
   Window window;

   @UiField
   TextField newDegree;

   @UiField(provided = true)
   DualListField<IdNameGenericInfo, String> degrees;

   @UiField
   TextButton saveButton;

   public ModifyAcademicInfoView()
   {
      degrees = new DualListField<IdNameGenericInfo, String>(degreesAll, degreesSelected, props.name(),
            new TextCell());
      degrees.setEnableDnd(true);
      degrees.setMode(Mode.INSERT);
      degrees.setContextMenu(this.buildDegreesMenu());
     
      uiBinder.createAndBindUi(this);
   }

   private Menu buildDegreesMenu()
   {
      return new MenuBuilder()
      .build(delete, icons.delete16())
      .get();
   }

   @UiHandler("newDegreeButton")
   public void newDegree(SelectEvent event)
   {
      long id = degreesAll.size() + degreesSelected.size() + 1;
      this.degreesSelected.add(new IdNameGenericInfo(id , newDegree.getText()));
   }

   @UiHandler("cancelButton")
   public void cancel(SelectEvent event)
   {
      window.hide();
   }

   /* *********************** Display complaint methods ********************** */
   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void setDegrees(List<IdNameGenericInfo> degrees)
   {
      this.degreesSelected.addAll(degrees);
   }

   @Override
   public List<IdNameGenericInfo> getDegrees()
   {
      return this.degreesSelected.getAll();
   }

   @Override
   public HasSelectHandlers getSaveButton()
   {
      return saveButton;
   }

   @Override
   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   @Override
   public IdNameGenericInfo getSelectedOfLeft()
   {
      return degrees.getFromView().getSelectionModel().getSelectedItem();
   }

   @Override
   public IdNameGenericInfo getSelectedOfRigth()
   {
      return degrees.getToView().getSelectionModel().getSelectedItem();
   }

   @Override
   public void showMessage(String string)
   {
      Info.display("Notificaation", string);
   }

   @Override
   public void deleteOfRigth(IdNameGenericInfo selected)
   {
      degreesSelected.remove(selected);
   }

   @Override
   public void deleteOfLeft(IdNameGenericInfo selected)
   {
      degreesAll.remove(selected);
   }

}