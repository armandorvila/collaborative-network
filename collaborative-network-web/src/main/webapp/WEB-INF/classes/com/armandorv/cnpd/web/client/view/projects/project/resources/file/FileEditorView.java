package com.armandorv.cnpd.web.client.view.projects.project.resources.file;

import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 * View to edit a file.
 * 
 * @author armandorv
 *
 */
public class FileEditorView
{
   public interface FileEditorViewUiBinder extends UiBinder<Widget, FileEditorView>
   {
   }

   private static FileEditorViewUiBinder uiBinder = GWT.create(FileEditorViewUiBinder.class);

   @UiField
   Window window;

   @UiField
   TextArea text;

   @UiField
   TextButton save;

   public FileEditorView()
   {
      uiBinder.createAndBindUi(this);
   }

   @UiHandler("cancel")
   public void cancel(SelectEvent event)
   {
      window.hide();
   }

   public Window asWindow()
   {
      return window;
   }

   public void setFileText(String response)
   {
      text.setText(response);
   }
   
   public String getFileText(){
      return text.getText();
   }

   public HasSelectHandlers getSaveButton()
   {
      return save;
   }

   public void showProgress()
   {
      new ProgressView("Saving file", "saving ...", 3000).start();
   }

}