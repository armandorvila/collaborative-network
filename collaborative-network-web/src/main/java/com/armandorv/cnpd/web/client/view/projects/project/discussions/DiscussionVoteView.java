package com.armandorv.cnpd.web.client.view.projects.project.discussions;

import java.util.List;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 * View that shows all options and let user select one of them.
 * The same user mustn't see this view twice. 
 * 
 * @author armandorv
 *
 */
@Singleton
public class DiscussionVoteView implements DiscussionVotePresenter.Display
{
   public interface DiscussionVoteViewUiBinder extends UiBinder<Widget, DiscussionVoteView>
   {
   }

   private static DiscussionVoteViewUiBinder uiBinder = GWT.create(DiscussionVoteViewUiBinder.class);

   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private ListStore<IdNameGenericInfo> store = new ListStore<IdNameGenericInfo>(props.id());
   
   private TextButton cancel = new TextButton("Cancel");

   @UiField
   Dialog dialog;
   
   @UiField
   TextArea argument;

   @UiField
   TextButton vote;
   
   @UiField(provided = true)
   ComboBox<IdNameGenericInfo> options = new ComboBox<IdNameGenericInfo>(store, props.labelName());

   public DiscussionVoteView()
   {
      uiBinder.createAndBindUi(this);
      
      cancel.addSelectHandler(new SelectHandler()
      {
         public void onSelect(SelectEvent event)
         {
            dialog.hide();
         }
      });
     
      dialog.setPredefinedButtons();
      dialog.addButton(cancel);
      
      options.setText("-- Select an option --");
   }

   @Override
   public Window asWindow()
   {
      return dialog;
   }

   @Override
   public void setOptions(List<IdNameGenericInfo> options)
   {
      store.clear();
      store.addAll(options);
   }

   @Override
   public IdNameGenericInfo getOption()
   {
      return options.getCurrentValue();
   }

   @Override
   public String getArgument()
   {
      return argument.getText();
   }

   @Override
   public HasSelectHandlers getVote()
   {
      return vote;
   }

   @Override
   public void clear()
   {
      argument.setText("");
      options.setText("-- Select an option --");
   }
   
   public void string(){
      
   }

}