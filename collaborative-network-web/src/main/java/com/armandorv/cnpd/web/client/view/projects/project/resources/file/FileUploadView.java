package com.armandorv.cnpd.web.client.view.projects.project.resources.file;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * View to upload a file.
 * 
 * @author armandorv
 *
 */
public class FileUploadView extends Composite implements SubmitCompleteHandler, HasWidgets
{

   public interface FileUploadViewUiBinder extends UiBinder<Widget, FileUploadView>
   {
   }

   public FileUploadViewUiBinder uiBinder = GWT.create(FileUploadViewUiBinder.class);

   @UiField
   Window window;

   @UiField
   FormPanel form;

   @UiField
   FileUploadField fileUpload;

   @UiField
   TextButton upload;

   @UiField
   TextField name;
   
   public FileUploadView()
   {
      initWidget(uiBinder.createAndBindUi(this));
      form.setEncoding(Encoding.MULTIPART);
      form.setMethod(Method.POST);
      RootPanel.get().add(this.asWidget());
   }

   @UiHandler("cancel")
   public void cancel(SelectEvent event)
   {
      if (window.isVisible()){
         RootPanel.get().remove(this.asWidget());
         window.setVisible(false);
      }
   }

   public HasSelectHandlers getUploadButton()
   {
      return upload;
   }

   public Window asWindow()
   {
      return window;
   }

   public FormPanel getForm()
   {
      return form;
   }

   public String getName()
   {
      return name.getText();
   }
   
   public void setName(String name){
      this.name.setText(name);
      this.name.setReadOnly(true);
   }

   @Override
   public void onSubmitComplete(SubmitCompleteEvent event)
   {
      RootPanel.get().remove(this.asWidget());
   }

   @Override
   public void add(Widget w)
   {
   }

   @Override
   public void clear()
   {
   }

   @Override
   public Iterator<Widget> iterator()
   {
      return null;
   }

   @Override
   public boolean remove(Widget w)
   {
      return true;
   }

}