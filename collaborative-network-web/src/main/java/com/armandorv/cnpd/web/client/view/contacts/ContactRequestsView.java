package com.armandorv.cnpd.web.client.view.contacts;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter;
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
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ContactRequestsView extends Composite implements ContactRequestsPresenter.Display, HasRowToolTip<ContactInfo>
{
   public interface ContactRequestsViewUiBinder extends UiBinder<Widget, ContactRequestsView>
   {
   }

   private ContactRequestsViewUiBinder uiBinder = GWT.create(ContactRequestsViewUiBinder.class);

   private ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem visit = new MenuItem("visit");

   private MenuItem accept = new MenuItem("accept");

   private MenuItem denied = new MenuItem("denied");

   @UiField(provided = true)
   ColumnModel<ContactInfo> requestsColumnModel;

   @UiField(provided = true)
   ListStore<ContactInfo> requestsStore = new ListStore<ContactInfo>(props.id());

   @UiField
   Grid<ContactInfo> requestsGrid;

   @UiField
   GridView<ContactInfo> requestsView;

   public ContactRequestsView()
   {
      this.buildColumnModel();

      this.actions.addBeforeSelectHandler(this.beforeSelection());

      super.initWidget(uiBinder.createAndBindUi(this));
      
      requestsGrid.setToolTipConfig(new ContactToolTipConfig(this));
   }

   private BeforeSelectHandler beforeSelection()
   {
      return new BeforeSelectHandler()
      {
         public void onBeforeSelect(BeforeSelectEvent event)
         {
            ContactInfo selected = requestsStore.get(event.getContext().getIndex());
            enableDisableItems(!selected.isContact(), !selected.isContact(), selected.isContact());
         }
      };
   }

   private void enableDisableItems(boolean accept, boolean denied, boolean visit)
   {
      this.accept.setEnabled(accept);
      this.denied.setEnabled(denied);
      this.visit.setEnabled(visit);
   }

   @UiFactory
   public ColumnModel<ContactInfo> getColumnModel()
   {
      return this.requestsColumnModel;
   }

   /**
    * Initialize column model for contacts grid.
    */
   private void buildColumnModel()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder()
      .build(visit, icons.contact16(), false)
      .build(accept, icons.tick16())
      .build(denied, icons.close16())
      .get());

      List<ColumnConfig<ContactInfo, ?>> contactsColumnList = new ColumnsBuilder<ContactInfo>()
            .build("", props.name(), 50, true, new IconCell(icons.contact16()))
            .build("Name", props.name(), 150, false)
            .build("Lastname1", props.lastname1(), 150, false)
            .build("Lastname2", props.lastname2(), 150, false)
            .build("Actions", props.name(), 50, false, actions)
            .get();

      requestsColumnModel = new ColumnModel<ContactInfo>(contactsColumnList);
   }

   @UiFactory
   public ListStore<ContactInfo> getData()
   {
      return requestsStore;
   }

   @Override
   public ContactInfo getRequest(int row)
   {
      return requestsStore.get(row);
   }

   @Override
   public void removeRequest(ContactInfo request)
   {
      this.requestsStore.remove(request);
   }

   @Override
   public void setRequests(List<ContactInfo> requests)
   {
      this.requestsStore.clear();
      this.requestsStore.addAll(requests);
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
   public HasSelectionHandlers<Item> getAccept()
   {
      return accept;
   }

   @Override
   public HasSelectionHandlers<Item> getDenied()
   {
      return denied;
   }

   @Override
   public void addErrorMessagee(String string)
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
      return requestsStore.get(requestsView.findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return requestsGrid;
   }
}