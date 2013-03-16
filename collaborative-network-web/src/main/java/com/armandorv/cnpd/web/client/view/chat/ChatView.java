package com.armandorv.cnpd.web.client.view.chat;

import java.util.List;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.client.view.util.tooltip.ContactToolTipConfig;
import com.armandorv.cnpd.web.client.view.util.tooltip.RowToolTipConfig.HasRowToolTip;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * Widget with the chat list content.
 * 
 * @author armandorv
 * 
 */
@Dependent
public class ChatView implements IsWidget, HasRowToolTip<ContactInfo>
{
   private static ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

 private static IconsBundle icons = GWT.create(IconsBundle.class);
   
   private TextButtonCell button = new TextButtonCell();

   private ListStore<ContactInfo> store = new ListStore<ContactInfo>(props.id());

   private ColumnModel<ContactInfo> columnModel = null;

   private Grid<ContactInfo> grid = null;

   public ChatView()
   {
      button.setText("chat");
      button.setWidth(50);

      columnModel = new ColumnModel<ContactInfo>(new ColumnsBuilder<ContactInfo>()
            .build("", props.name(), 30, true, new IconCell(icons.contact16()))
            .build("Contacts online", props.fullName(), 202, false)
            .build("", props.fullName(), 60, false, button)
            .get());

      grid = new Grid<ContactInfo>(store, columnModel);
      grid.setBorders(false);
      grid.setToolTipConfig(new ContactToolTipConfig(this));
      
      grid.getView().setEmptyText("There is no contacts online.");

      new FiltersBuilder<ContactInfo>()
            .build(props.fullName())
            .get(grid);
     
   }

   public HasSelectHandlers getClickable()
   {
      return button;
   }

   @Override
   public Widget asWidget()
   {
      VerticalLayoutContainer container = new VerticalLayoutContainer();
      container.add(grid);
      container.setScrollMode(ScrollMode.AUTOY);

      return container;
   }

   public void addContact(ContactInfo contact)
   {
      store.add(contact);
   }

   public void addContacts(List<ContactInfo> contacts)
   {
      store.clear();
      store.addAll(contacts);
   }

   public void removeContact(ContactInfo contact)
   {
      store.remove(contact);
   }

   public void setEnabled(boolean enabled)
   {
      grid.setVisible(enabled);
      if (!enabled)
         this.store.clear();
   }

   @Override
   public ContactInfo getMouseOvered(Element eventTarget)
   {
      return store.get(grid.getView().findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return grid;
   }

}