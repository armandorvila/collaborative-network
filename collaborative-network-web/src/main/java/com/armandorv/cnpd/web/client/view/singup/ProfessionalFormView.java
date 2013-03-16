package com.armandorv.cnpd.web.client.view.singup;

import java.util.List;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter;
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
 * View for get professional information of user during sign up wizard.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class ProfessionalFormView extends Composite implements ProfessionalFormPresenter.Display
{

   public interface ProfessionalFormViewUiBinder extends UiBinder<Widget, ProfessionalFormView>
   {
   }

   private static ProfessionalFormViewUiBinder uiBinder = GWT.create(ProfessionalFormViewUiBinder.class);

   private IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private ListStore<IdNameGenericInfo> jobsAll = new ListStore<IdNameGenericInfo>(props.id());

   private ListStore<IdNameGenericInfo> jobsSelected = new ListStore<IdNameGenericInfo>(props.id());

   @UiField(provided = true)
   DualListField<IdNameGenericInfo, String> jobs;

   @UiField
   TextField newJob;

   @UiField
   TextButton continueButton;

   @UiField
   TextButton backButton;

   @UiField
   TextButton cancelButton;

   private MenuItem delete = new MenuItem("Delete");

   private IconsBundle icons = GWT.create(IconsBundle.class);

   public ProfessionalFormView()
   {
      jobs = new DualListField<IdNameGenericInfo, String>(jobsAll, jobsSelected, props.name(), new TextCell());
      jobs.setEnableDnd(true);
      jobs.setMode(Mode.INSERT);

      delete.addSelectionHandler(delete());

      jobs.setContextMenu(new MenuBuilder()
            .build(delete, icons.delete16())
            .get());

      super.initWidget(new WizardView(uiBinder.createAndBindUi(this)));
   }

   @UiHandler("newJobButton")
   public void newDegree(SelectEvent event)
   {
      long id = jobsAll.size() + jobsSelected.size() + 1;
      String name = this.newJob.getText();
      this.jobsSelected.add(new IdNameGenericInfo(id, name));
   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            IdNameGenericInfo leftSelected = jobs.getFromView().getSelectionModel().getSelectedItem();
            IdNameGenericInfo rigthSelected = jobs.getToView().getSelectionModel().getSelectedItem();

            if (leftSelected == null && rigthSelected == null)
            {
               Info.display("Error ", "Invalid selection.");
            }

            if (leftSelected != null && rigthSelected != null)
            {
               jobsSelected.remove(rigthSelected);
               jobsAll.remove(leftSelected);
            }

            if (leftSelected != null && rigthSelected == null)
            {
               jobsAll.remove(leftSelected);
            }

            if (leftSelected == null && rigthSelected != null)
            {
               jobsSelected.remove(rigthSelected);
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
   public List<IdNameGenericInfo> getJobs()
   {
      return jobsSelected.getAll();
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Singup Fault", string);
   }

}