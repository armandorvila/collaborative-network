package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.resources.file.FileUploadView;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Deal with resources uploading by listen selection of upload menu item and select upload button.
 * 
 * @author armandorv
 *
 */
public class UploadResourceHandler implements SelectionHandler<Item>, SelectHandler
{
   private static String UPLOAD_CREATE_SERVLET_URL = GWT.getHostPageBaseURL()
         + "Upload?prid=xx&rname=yy&paid=zz&kind=kk";

   private static String UPLOAD_REPLACE_SERVLET_URL = GWT.getHostPageBaseURL()
         + "Upload?prid=xx&reid=yy";

   private ResourcesPresenter presenter;

   private boolean free;

   private Kind kind;

   private boolean replacement;

   private FileUploadView fileUploadView;

   public UploadResourceHandler(ResourcesPresenter presenter, boolean free, boolean replacement, Kind kind)
   {
      super();
      this.free = free;
      this.replacement = replacement;
      this.kind = kind;
      this.presenter = presenter;
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      final ResourceInfo selected = presenter.getDisplay().getSelected(free);

      if (selected == null)
      {
         Info.display("Invalid operation.", "You have to select a resource.");
      }
      else
      {
         fileUploadView = new FileUploadView();
         fileUploadView.getUploadButton().addSelectHandler(this);

         if (replacement)
            fileUploadView.setName(selected.getName());
        
         fileUploadView.asWindow().show();
      }
   }

   @Override
   public void onSelect(SelectEvent event)
   {
      final ResourceInfo selected = presenter.getDisplay().getSelected(free);

      if (replacement)
      {
         if (!selected.getKind().equals(Kind.FILE) && !selected.getKind().equals(Kind.DOCUMENT)
               && !selected.getKind().equals(Kind.NONE))
         {
            Info.display("Invalid operation.", "Invalid resource selected.");
         }
         else
         {
            this.uploadAndReplace(selected);
         }
      }
      else
      {
         if (selected.getKind().equals(Kind.FOLDER) || selected.getKind().equals(Kind.ROOT))
            this.uploadAndCreate(selected);
         else
            Info.display("Invalid operation.", "Invalid parent selected.");
      }
   }

   private void uploadAndCreate(ResourceInfo selected)
   {
      UPLOAD_CREATE_SERVLET_URL = UPLOAD_CREATE_SERVLET_URL.replace("xx",
            presenter.getProject().getId() + "");

      UPLOAD_CREATE_SERVLET_URL = UPLOAD_CREATE_SERVLET_URL
            .replace("yy", fileUploadView.getName());

      UPLOAD_CREATE_SERVLET_URL = UPLOAD_CREATE_SERVLET_URL
            .replace("zz", selected.getId() + "");

      UPLOAD_CREATE_SERVLET_URL = UPLOAD_CREATE_SERVLET_URL
            .replace("kk", kind.name());

      fileUploadView.getForm().setAction(UPLOAD_CREATE_SERVLET_URL);
      fileUploadView.getForm().submit();

      presenter.present();
      fileUploadView.asWindow().hide();
   }

   private void uploadAndReplace(ResourceInfo selected)
   {
      UPLOAD_REPLACE_SERVLET_URL = UPLOAD_REPLACE_SERVLET_URL.replace("xx",
            presenter.getProject().getId() + "");

      UPLOAD_REPLACE_SERVLET_URL = UPLOAD_REPLACE_SERVLET_URL
            .replace("yy", selected.getId() + "");

      fileUploadView.getForm().setAction(UPLOAD_REPLACE_SERVLET_URL);
      fileUploadView.getForm().submit();

      presenter.present();
      fileUploadView.asWindow().hide();
   }

}
