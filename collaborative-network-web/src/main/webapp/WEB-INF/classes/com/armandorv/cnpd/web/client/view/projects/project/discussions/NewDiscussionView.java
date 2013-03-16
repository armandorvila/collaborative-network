package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import java.util.List;

import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter;

/**
 * View fragment of discussion to create new discussions, it has widget to
 * introduce discussion info and accept it.
 * 
 * @author armandorv
 * 
 */
public class NewDiscussionView extends Composite implements NewDiscussionPresenter.Display
{
   public interface NewDiscussionViewUiBinder extends UiBinder<Widget, NewDiscussionView>
   {
   }
   
   private static final int MAX_OPTIONS = 5; // TODO quitar

   private static NewDiscussionViewUiBinder uiBinder = GWT.create(NewDiscussionViewUiBinder.class);

   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private ListStore<IdNameGenericInfo> store = new ListStore<IdNameGenericInfo>(props.id());

   @UiField(provided = true)
   ComboBox<IdNameGenericInfo> optionsCombo = new ComboBox<IdNameGenericInfo>(store, props.labelName());

   @UiField
   TextField title;

   @UiField
   TextArea description;

   @UiField
   TextField option;

   @UiField
   TextButton create;

   public NewDiscussionView()
   {   
      super.initWidget(uiBinder.createAndBindUi(this));

      title.addValidator(new EmptyValidator<String>());
      description.addValidator(new EmptyValidator<String>());
      option.addValidator(new EmptyValidator<String>());
      
      optionsCombo.setText("-- Options --");
   }

   @UiHandler("deleteOptionButton")
   public void removeOption(SelectEvent event)
   {
      store.remove(optionsCombo.getCurrentValue());
      optionsCombo.setText("");
   }

   @UiHandler("newOptionButton")
   public void addOption(SelectEvent event)
   {
      if (option.validate())
      {
         if (contains(option.getText()))
         {
            Info.display("Notification", "Option already exists.");
         }
         else
         {
            if(store.size() < MAX_OPTIONS ) {
            
            IdNameGenericInfo opt = new IdNameGenericInfo(store.size(), option.getText());
            store.add(opt);
            option.setText("");
            optionsCombo.select(opt);
            optionsCombo.setText(opt.getName());
            
            }
            else {
              Info.display("Error" , "Max options number reached."); 
            }
         }
      }
   }

   private boolean contains(String name)
   {
      for (IdNameGenericInfo option : store.getAll())
      {
         if (option.getName().equals(name))
            return true;
      }
      return false;
   }

   /* ********** Methods for parent widget *********** */
   public HasSelectHandlers getCreateButton()
   {
      return this.create;
   }

   @Override
   public String getDiscussionTitle()
   {
      return title.getText();
   }

   public TextArea getDiscussionDescription()
   {
      return description;
   }

   @Override
   public List<IdNameGenericInfo> getOptions()
   {
      return this.store.getAll();
   }

   @Override
   public HasSelectHandlers getCreate()
   {
      return create;
   }

   @Override
   public void showMessage(String message)
   {
     Info.display("Notification" , message); 
   }

   @Override
   public String getDescription()
   {
      return description.getText();
   }

   @Override
   public void clear()
   {
      title.setText("");
      description.setText("");
      optionsCombo.setText("-- Options --");
      store.clear();
   }
}