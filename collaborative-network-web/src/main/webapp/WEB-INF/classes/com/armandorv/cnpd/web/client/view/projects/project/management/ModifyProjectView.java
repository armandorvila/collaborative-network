package com.armandorv.cnpd.web.client.view.projects.project.management;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.management.ModifyProjectPresenter;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class ModifyProjectView implements ModifyProjectPresenter.Display
{
   public interface ModifyProjectViewUibinder extends UiBinder<Widget, ModifyProjectView>
   {
   }

   private ModifyProjectViewUibinder uiBinder = GWT.create(ModifyProjectViewUibinder.class);
   
   private IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private ListStore<IdNameGenericInfo> store = new ListStore<IdNameGenericInfo>(props.id());

   @UiField
   Window window;

   @UiField
   TextField title;

   @UiField(provided = true)
   ComboBox<IdNameGenericInfo> combo = new ComboBox<IdNameGenericInfo>(store, props.labelName());
   
   @UiField
   TextArea description;

   @UiField
   TextButton save;

   @UiField
   TextButton cancel;
   
   public ModifyProjectView(){
      uiBinder.createAndBindUi(this);
   }
   
   @UiHandler("cancel")
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
   public void setTitle(String title)
   {
      this.title.setText(title);
   }

   @Override
   public String getTitle()
   {
      return title.getText();
   }

   @Override
   public void setDescription(String title)
   {
      description.setText(title); 
   }

   @Override
   public String getDescription()
   {
      return description.getText();
   }

   @Override
   public void setKnowledgeAreas(List<IdNameGenericInfo> kas)
   {
      this.store.clear();
      this.store.addAll(kas);
      this.combo.select(store.get(0));
      this.combo.setText(store.get(0).getName());
   }

   @Override
   public IdNameGenericInfo getKnowledegeArea()
   {
      return  combo.getCurrentValue();
   }

   @Override
   public HasSelectHandlers getSaveButton()
   {
      return save;
   }

   @Override
   public void showMessage(String string)
   {
      Info.display("Notification" , string);
   }

   @Override
   public void setKnowledgeArea(IdNameGenericInfo knowledgeArea)
   {
      this.combo.select(knowledgeArea);
   }

}
