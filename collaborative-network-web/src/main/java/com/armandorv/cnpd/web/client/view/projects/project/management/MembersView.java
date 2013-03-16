package com.armandorv.cnpd.web.client.view.projects.project.management;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MembersView implements MembersPresenter.Display
{
   public interface MembersViewUiBinder extends UiBinder<Widget, MembersView>
   {
   }

   private static MembersViewUiBinder uiBinder = GWT.create(MembersViewUiBinder.class);

   private IconsBundle icons = GWT.create(IconsBundle.class);

   private ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private TextButtonCell actions = new TextButtonCell();

   private MenuItem manager = new MenuItem("Manager");

   private MenuItem exclude = new MenuItem("Exclude");

   @UiField
   ListStore<ContactInfo> store = new ListStore<ContactInfo>(props.id());

   @UiField
   ColumnModel<ContactInfo> columnModel;

   @UiField
   Grid<ContactInfo> grid;

   @UiField
   GridView<ContactInfo> view;

   @UiField
   Dialog dialog;

   public MembersView()
   {
      uiBinder.createAndBindUi(this);
      dialog.setPredefinedButtons(PredefinedButton.CLOSE);
   }
   
   @UiFactory
   ColumnModel<ContactInfo> buildColumnModel()
   {
      actions.setText("Select");

      actions.setMenu(new MenuBuilder()
            .build(exclude, icons.contact16())
            .build(manager, icons.contact16())
            .get());

      columnModel = new ColumnModel<ContactInfo>(new ColumnsBuilder<ContactInfo>()
            .build("Member", props.fullName(), 200, false)
            .build("Actions", props.name(), 200, false, actions)
            .get());
      
      return columnModel;
   }

   @UiFactory
   ListStore<ContactInfo> getStore()
   {
      return store;
   }

   @Override
   public Window asWindow()
   {
      return dialog;
   }

   @Override
   public void setMembers(List<ContactInfo> members)
   {
      store.clear();
      store.addAll(members);
   }

   @Override
   public HasSelectHandlers getActions()
   {
      return actions;
   }

   @Override
   public HasSelectionHandlers<Item> getManager()
   {
      return manager;
   }

   @Override
   public HasSelectionHandlers<Item> getExclude()
   {
      return exclude;
   }

   @Override
   public void setManagerEnabled(boolean enabled)
   {
      manager.setEnabled(enabled);
   }

   @Override
   public void setExcludeEnabled(boolean enabled)
   {
      exclude.setEnabled(enabled);
   }

   @Override
   public ContactInfo getMember(int row)
   {
      return store.get(row);
   }
}