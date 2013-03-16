package com.armandorv.cnpd.web.client.view.info;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.info.ModifyProfessionalInfoPresenter;
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

/**
 * View for personal info modification, shows a panel that allow user set their personal information.
 * @author armandorv
 *
 */
public class ModifyProfessionalInfoView implements ModifyProfessionalInfoPresenter.Display
{
   public interface ModifyProfessionalInfoViewUiBinder extends UiBinder<Widget, ModifyProfessionalInfoView>
   {
   }

   private ModifyProfessionalInfoViewUiBinder uiBinder = GWT.create(ModifyProfessionalInfoViewUiBinder.class);

   private IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   @UiField
   Window window;

   @UiField
   TextField newJob;

   @UiField
   TextButton saveButton;

   ListStore<IdNameGenericInfo> jobsAll = new ListStore<IdNameGenericInfo>(props.id());

   ListStore<IdNameGenericInfo> jobsSelected = new ListStore<IdNameGenericInfo>(props.id());

   @UiField(provided = true)
   DualListField<IdNameGenericInfo, String> jobs;

   private MenuItem delete = new MenuItem("Delete");

   private IconsBundle icons = GWT.create(IconsBundle.class);

   public ModifyProfessionalInfoView()
   {
      jobs = new DualListField<IdNameGenericInfo, String>(jobsAll, jobsSelected, props.name(), new TextCell());
      jobs.setEnableDnd(true);
      jobs.setMode(Mode.INSERT);
      jobs.setContextMenu(buildJobsMenu());
     
      this.uiBinder.createAndBindUi(this);
   }

   private Menu buildJobsMenu()
   {
      return new MenuBuilder()
            .build(delete, icons.delete16())
            .get();
   }

   @UiHandler("newJobButton")
   public void newJob(SelectEvent event)
   {
      long id = jobsAll.size() + jobsSelected.size() + 1;
      this.jobsSelected.add(new IdNameGenericInfo(id, newJob.getText()));
   }

   @UiHandler("cancelButton")
   public void cancel(SelectEvent event)
   {
      window.hide();
   }

   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void setJobs(List<IdNameGenericInfo> jobs)
   {
      jobsSelected.addAll(jobs);
   }

   @Override
   public List<IdNameGenericInfo> getJobs()
   {
      return jobsSelected.getAll();
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
      return jobs.getFromView().getSelectionModel().getSelectedItem();
   }

   @Override
   public IdNameGenericInfo getSelectedOfRigth()
   {
      return jobs.getToView().getSelectionModel().getSelectedItem();
   }

   @Override
   public void showMessage(String string)
   {
      Info.display("Notification", string);
   }

   @Override
   public void deleteOfLeft(IdNameGenericInfo selected)
   {
      jobsAll.remove(selected);
   }

   @Override
   public void deleteOfRigth(IdNameGenericInfo selected)
   {
      jobsSelected.remove(selected);
   }

}