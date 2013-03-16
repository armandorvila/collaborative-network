package com.armandorv.cnpd.web.client.view.singup;

import java.util.List;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.DualListField.Mode;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

@Dependent
public class ContactsFormView extends Composite implements ContactsFormPresenter.Display
{
   public interface ContactsFormViewUiBinder extends UiBinder<Widget, ContactsFormView>
   {
   }

   private static ContactsFormViewUiBinder uiBinder = GWT.create(ContactsFormViewUiBinder.class);

   private MenuItem delete = new MenuItem("Delete");

   private IconsBundle icons = GWT.create(IconsBundle.class);
   
   private ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private ListStore<ContactInfo> allContactsStore = new ListStore<ContactInfo>(props.id());

   private ListStore<ContactInfo> addedContactsStore = new ListStore<ContactInfo>(props.id());

   @UiField(provided = true)
   DualListField<ContactInfo, String> contacts;

   @UiField
   TextButton finishButton;

   @UiField
   TextButton backButton;

   @UiField
   TextButton cancelButton;

   public ContactsFormView()
   {
      super();

      contacts = new DualListField<ContactInfo, String>(allContactsStore, addedContactsStore, props.fullName(), new TextCell());
      contacts.setEnableDnd(true);
      contacts.setMode(Mode.INSERT);
     
      delete.addSelectionHandler(delete());
      
      contacts.setContextMenu(new MenuBuilder()
      .build(delete, icons.delete16())
      .get());

      super.initWidget(new WizardView(uiBinder.createAndBindUi(this)));
   }
   
   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            ContactInfo leftSelected = contacts.getFromView().getSelectionModel().getSelectedItem();
            ContactInfo rigthSelected = contacts.getToView().getSelectionModel().getSelectedItem();

            if (leftSelected == null && rigthSelected == null)
            {
               Info.display("Error ", "Invalid selection.");
            }

            if (leftSelected != null && rigthSelected != null)
            {
               addedContactsStore.remove(rigthSelected);
               allContactsStore.remove(leftSelected);
            }

            if (leftSelected != null && rigthSelected == null)
            {
               allContactsStore.remove(leftSelected);
            }

            if (leftSelected == null && rigthSelected != null)
            {
               addedContactsStore.remove(rigthSelected);
            }
         }
      };
   }

   /* ************** Display complaint methods ************ */
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
   public HasSelectHandlers getContinue()
   {
      return finishButton;
   }

   @Override
   public void setContacts(List<ContactInfo> googleContacts)
   {
      this.allContactsStore.addAll(googleContacts);
   }

   @Override
   public List<ContactInfo> getAddedContacts()
   {
      return this.addedContactsStore.getAll();
   }

   @Override
   public void addErrorMessage(String string)
   {
      Info.display("Sing up fault", string);
   }

}