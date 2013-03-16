package com.armandorv.cnpd.web.client.view.projects;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * View for new projects creation.
 * 
 * @author armandorv
 * 
 */
public class NewProjectView extends Composite implements NewProjectPresenter.Display
{
   public interface NewProjectViewUibinder extends UiBinder<Widget, NewProjectView>
   {
   }

   private NewProjectViewUibinder uiBinder = GWT.create(NewProjectViewUibinder.class);

   private IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private ListStore<IdNameGenericInfo> store = new ListStore<IdNameGenericInfo>(props.id());

   @UiField(provided = true)
   ComboBox<IdNameGenericInfo> combo = new ComboBox<IdNameGenericInfo>(store, props.labelName());

   @UiField
   TextArea description;

   @UiField
   TextField title;

   @UiField
   TextButton createButton;

   public NewProjectView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
      combo.setText("--Selet a Knowledge area --");
   }

   @Override
   public void setKnowledgeAreas(List<IdNameGenericInfo> kas)
   {
      this.store.clear();
      this.store.addAll(kas);
      this.combo.select(store.get(0));
   }

   @Override
   public String getProjectTitle()
   {
      return title.getText();
   }

   @Override
   public String getDescription()
   {
      return description.getText();
   }

   @Override
   public HasSelectHandlers getCreateButton()
   {
      return createButton;
   }

   @Override
   public IdNameGenericInfo getNewProjectKnowledegeArea()
   {
      return combo.getCurrentValue();
   }

   @Override
   public void clear()
   {
      title.setText("");
      description.setText("");
      combo.setText("--Selet a Knowledge area --");

   }

}