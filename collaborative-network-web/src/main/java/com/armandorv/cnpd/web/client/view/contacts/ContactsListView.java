package com.armandorv.cnpd.web.client.view.contacts;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
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
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * View that shows a list of contacts. There is a row for each contact and for each row 3 actions.
 * 
 * @author armandorv
 *
 */
public class ContactsListView extends Composite implements ContactsListPresenter.Display, HasRowToolTip<ContactInfo>
{
   public interface ContactsListViewUiBinder extends UiBinder<Widget, ContactsListView>
   {
   }

   private static ContactsListViewUiBinder uiBinder = GWT.create(ContactsListViewUiBinder.class);

   private static ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem visit = new MenuItem("visit");

   private MenuItem message = new MenuItem("message");

   private MenuItem delete = new MenuItem("delete");

   @UiField(provided = true)
   ColumnModel<ContactInfo> contactsColumnModel;

   @UiField(provided = true)
   ListStore<ContactInfo> contactsStore = new ListStore<ContactInfo>(props.id());

   @UiField
   Grid<ContactInfo> contactsGrid;

   @UiField
   GridView<ContactInfo> contactsView;

   public ContactsListView()
   {
      this.buildGrid();

      super.initWidget(uiBinder.createAndBindUi(this));

      contactsGrid.setToolTipConfig(new ContactToolTipConfig(this));

      new FiltersBuilder<ContactInfo>()
            .build(props.name())
            .build(props.lastname1())
            .build(props.lastname2())
            .get(contactsGrid);
   }

   @UiFactory
   ColumnModel<ContactInfo> createColumnModel()
   {
      return contactsColumnModel;
   }

   /**
    * Initialize column model for contacts grid.
    */
   private void buildGrid()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder()
            .build(visit, icons.contact16())
            .build(delete, icons.delete16())
            .build(message, icons.mail16())
            .get());

      contactsColumnModel = new ColumnModel<ContactInfo>(new ColumnsBuilder<ContactInfo>()
            .build("", props.name(), 50, true, new IconCell(icons.contact16()))
            .build("Name", props.name(), 150, false)
            .build("Lastname1", props.lastname1(), 150, false)
            .build("Lastname2", props.lastname2(), 150, true)
            .build("Actions", props.name(), 50, false, actions)
            .get());
   }

   @UiFactory
   public ListStore<ContactInfo> getData()
   {
      return contactsStore;
   }

   @Override
   public void setContacts(List<ContactInfo> contacts)
   {
      contactsStore.clear();
      contactsStore.addAll(contacts);
   }

   @Override
   public void addContact(ContactInfo contact)
   {
      contactsStore.add(contact);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public HasSelectionHandlers<Item> getVisit()
   {
      return visit;
   }

   @Override
   public HasSelectionHandlers<Item> getMessage()
   {
      return message;
   }

   @Override
   public HasSelectionHandlers<Item> getDelete()
   {
      return delete;
   }

   @Override
   public ContactInfo getContact(int row)
   {
      return contactsStore.get(row);
   }

   @Override
   public void removeContact(ContactInfo selected)
   {
      contactsStore.remove(selected);
   }

   @Override
   public void showProgress()
   {
      new ProgressView("Loading contacts", "Loading ...", 2000).start();
   }

   @Override
   public ContactInfo getMouseOvered(Element eventTarget)
   {
      return contactsStore.get(contactsView.findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return contactsGrid;
   }

}