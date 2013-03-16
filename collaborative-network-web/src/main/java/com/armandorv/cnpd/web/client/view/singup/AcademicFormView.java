package com.armandorv.cnpd.web.client.view.singup;

import java.util.List;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * View for introduce academic information, is part of Sing up wizard.
 * 
 * @author armandorv
 *
 */
@Dependent
public class AcademicFormView extends Composite implements AcademicFormPresenter.Display
{

   public interface AcademicFormViewUiBinder extends UiBinder<Widget, AcademicFormView>
   {
   }

   private static AcademicFormViewUiBinder uiBinder = GWT.create(AcademicFormViewUiBinder.class);

   private IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   @UiField
   TextField newDegree;

   ListStore<IdNameGenericInfo> degreesAll = new ListStore<IdNameGenericInfo>(props.id());

   ListStore<IdNameGenericInfo> degreesSelected = new ListStore<IdNameGenericInfo>(props.id());

   @UiField(provided = true)
   DualListField<IdNameGenericInfo, String> degrees;

   @UiField
   TextButton continueButton;

   @UiField
   TextButton backButton;

   @UiField
   TextButton cancelButton;

   private MenuItem delete = new MenuItem("Delete");

   private IconsBundle icons = GWT.create(IconsBundle.class);

   public AcademicFormView()
   {
      super();

      degrees = new DualListField<IdNameGenericInfo, String>(degreesAll, degreesSelected, props.name(), new TextCell());
      degrees.setEnableDnd(true);
      degrees.setMode(Mode.INSERT);
      
      delete.addSelectionHandler(delete());
      
      degrees.setContextMenu(new MenuBuilder()
            .build(delete, icons.delete16())
            .get());

      super.initWidget(new WizardView(uiBinder.createAndBindUi(this)));
   }

   @UiHandler("newDegreeButton")
   public void newDegree(SelectEvent event)
   {
      long id = degreesAll.size() + degreesSelected.size() + 1;
      this.degreesSelected.add(new IdNameGenericInfo(id, newDegree.getText()));

   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            IdNameGenericInfo leftSelected = degrees.getFromView().getSelectionModel().getSelectedItem();
            IdNameGenericInfo rigthSelected = degrees.getToView().getSelectionModel().getSelectedItem();

            if (leftSelected == null && rigthSelected == null)
            {
               Info.display("Error ", "Invalid selection.");
            }

            if (leftSelected != null && rigthSelected != null)
            {
               degreesSelected.remove(rigthSelected);
               degreesAll.remove(leftSelected);
            }

            if (leftSelected != null && rigthSelected == null)
            {
               degreesAll.remove(leftSelected);
            }

            if (leftSelected == null && rigthSelected != null)
            {
               degreesSelected.remove(rigthSelected);
            }
         }
      };
   }

   @Override
   public HasSelectHandlers getContinue()
   {
      return continueButton;
   }

   @Override
   public HasSelectHandlers getCancel()
   {
      return cancelButton;
   }

   @Override
   public HasSelectHandlers getBack()
   {
      return backButton;
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Error", string);
   }

   @Override
   public List<IdNameGenericInfo> getDegrees()
   {
      return degreesSelected.getAll();
   }

}