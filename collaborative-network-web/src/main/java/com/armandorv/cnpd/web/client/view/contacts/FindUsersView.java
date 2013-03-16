package com.armandorv.cnpd.web.client.view.contacts;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.client.view.util.tooltip.ContactToolTipConfig;
import com.armandorv.cnpd.web.client.view.util.tooltip.RowToolTipConfig.HasRowToolTip;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class FindUsersView extends Composite implements FindUsersPresenter.Display, HasRowToolTip<ContactInfo>
{
   public interface FindUsersViewUiBinder extends UiBinder<Widget, FindUsersView>
   {
   }

   private FindUsersViewUiBinder uiBinder = GWT.create(FindUsersViewUiBinder.class);

   private ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private MenuItem visit = new MenuItem("visit");

   private MenuItem request = new MenuItem("request");

   private TextButtonCell actions = new TextButtonCell();

   @UiField(provided = true)
   ColumnModel<ContactInfo> searchColumnModel;

   @UiField(provided = true)
   ListStore<ContactInfo> searchStore = new ListStore<ContactInfo>(props.id());

   @UiField
   Grid<ContactInfo> searchGrid;

   @UiField
   GridView<ContactInfo> searchView;

   @UiField
   TextField name;

   @UiField
   TextField lastname;

   @UiField
   TextField lastname2;

   @UiField
   TextButton searchButton;

   public FindUsersView()
   {
      this.build();
      super.initWidget(uiBinder.createAndBindUi(this));
    
      searchButton.setIcon(icons.magnifing18());
      searchGrid.setToolTipConfig(new ContactToolTipConfig(this));
   }

   @UiFactory
   public ColumnModel<ContactInfo> getColumnModel()
   {
      return this.searchColumnModel;
   }

   /**
    * Initialize column model for contacts grid.
    */
   private void build()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder().build(visit, icons.contact16(), false).build(request, icons.new16(), true)
            .get());

      actions.addBeforeSelectHandler(beforeSelection());

      List<ColumnConfig<ContactInfo, ?>> contactsColumnList = new ColumnsBuilder<ContactInfo>()
            .build("", props.name(), 50, true, new IconCell(icons.contact16()))
            .build("Name", props.name(), 140, false)
            .build("Lastname1", props.lastname1(), 140, false)
            .build("Lastname2", props.lastname2(), 140, true)
            .build("Actions", props.name(), 50, false, actions)
            .get();

      searchColumnModel = new ColumnModel<ContactInfo>(contactsColumnList);
   }

   private BeforeSelectHandler beforeSelection()
   {
      return new BeforeSelectHandler()
      {
         public void onBeforeSelect(BeforeSelectEvent event)
         {
            ContactInfo sel = searchStore.get(event.getContext().getIndex());
            enableDisableItems(sel.isContact(), !sel.isContact());
         }
      };
   }

   private void enableDisableItems(boolean visit, boolean request)
   {
      this.visit.setEnabled(visit);
      this.request.setEnabled(request);
   }

   @UiFactory
   public ListStore<ContactInfo> getData()
   {
      return this.searchStore;
   }

   /* *************** Display complaint methods *************** */
   @Override
   public HasSelectionHandlers<Item> getVisit()
   {
      return visit;
   }

   @Override
   public HasSelectionHandlers<Item> getRequest()
   {
      return request;
   }

   @Override
   public String getSearchName()
   {
      return name.getText();
   }

   @Override
   public String getSearchLastname1()
   {
      return lastname.getText();
   }

   @Override
   public String getSearchLastname2()
   {
      return lastname2.getText();
   }

   @Override
   public HasSelectHandlers getSearchButton()
   {
      return searchButton;
   }

   @Override
   public ContactInfo getFoundContact(int row)
   {
      return searchStore.get(row);
   }

   @Override
   public void setFoundContacts(List<ContactInfo> contacts)
   {
      searchStore.clear();
      searchStore.addAll(contacts);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Error", string);
   }

   @Override
   public void addSuccessMessage(String string)
   {
      Info.display("Success", string);
   }

   @Override
   public ContactInfo getMouseOvered(Element eventTarget)
   {
      return searchStore.get(searchView.findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return searchGrid;
   }
}