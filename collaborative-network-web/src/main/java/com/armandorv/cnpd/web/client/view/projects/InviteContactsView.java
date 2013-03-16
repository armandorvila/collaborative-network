package com.armandorv.cnpd.web.client.view.projects;

import java.util.ArrayList;
import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.InvitationsOnCreationPresenter;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * 
 * @author armandorv
 * 
 */
public class InviteContactsView implements InvitationsOnCreationPresenter.Display
{
   public interface InviteContactsViewUiBinder extends UiBinder<Widget, InviteContactsView>
   {
   }

   private InviteContactsViewUiBinder uiBinder = GWT.create(InviteContactsViewUiBinder.class);

   private ContactInfoProperties contactsProperties = GWT.create(ContactInfoProperties.class);
   
   private ListStore<ContactInfo> allContactsStore = new ListStore<ContactInfo>(contactsProperties.id());

   private ListStore<ContactInfo> addedContactsStore = new ListStore<ContactInfo>(contactsProperties.id());

   @UiField
   Window addWindow;

   @UiField(provided = true)
   Grid<ContactInfo> allContactsGrid = null;

   @UiField(provided = true)
   Grid<ContactInfo> addedContactsGrid = null;

   @UiField
   TextButton acceptButton;

   @UiField
   TextButton cancelButton;

   public InviteContactsView()
   {
      buildAllContactsGrid();
      buildAddedContactsGrid();
      uiBinder.createAndBindUi(this);
   }

   /* ******************** Ui Building methods ******************** */
   private void buildAddedContactsGrid()
   {
      addedContactsGrid = new Grid<ContactInfo>(addedContactsStore, new ColumnModel<ContactInfo>(buildColumns()));
      this.addedContactsGrid.getView().setForceFit(true);
      this.addedContactsGrid.setBorders(true);

      new GridDragSource<ContactInfo>(addedContactsGrid);

      GridDropTarget<ContactInfo> target2 = new GridDropTarget<ContactInfo>(addedContactsGrid);
      target2.setFeedback(Feedback.INSERT);
   }

   private void buildAllContactsGrid()
   {
      allContactsGrid = new Grid<ContactInfo>(allContactsStore, new ColumnModel<ContactInfo>(buildColumns()));

      this.allContactsGrid.getView().setForceFit(true);
      this.allContactsGrid.setBorders(true);

      new GridDragSource<ContactInfo>(allContactsGrid);
      GridDropTarget<ContactInfo> target1 = new GridDropTarget<ContactInfo>(allContactsGrid);
      target1.setFeedback(Feedback.INSERT);
   }

   private List<ColumnConfig<ContactInfo, ?>> buildColumns()
   {

      List<ColumnConfig<ContactInfo, ?>> columnConfigList = new ArrayList<ColumnConfig<ContactInfo, ?>>();

      ColumnConfig<ContactInfo, String> name = new ColumnConfig<ContactInfo, String>(contactsProperties.name());

      name.setHeader(SafeHtmlUtils.fromString("Contact name"));

      columnConfigList.add(name);

      return columnConfigList;
   }
   
   /* ******************** Display complaint methods **************** */
   @Override
   public Window asWindow()
   {
      return addWindow;
   }

   @Override
   public HasSelectHandlers getAcceptButton()
   {
      return acceptButton;
   }

   @Override
   public HasSelectHandlers getCancelButton()
   {
      return cancelButton;
   }

   @Override
   public void setContacts(List<ContactInfo> contacts)
   {
      this.allContactsStore.clear();
      this.allContactsStore.addAll(contacts);
   }

   @Override
   public List<ContactInfo> getAddedContacts()
   {
      return this.addedContactsStore.getAll();
   }

   @Override
   public void showProgress()
   {
      new ProgressView("Creating project", "creating ...", 3000).start();
   }

   @Override
   public List<ContactInfo> getToAddContacts()
   {
      return allContactsStore.getAll();
   }

}