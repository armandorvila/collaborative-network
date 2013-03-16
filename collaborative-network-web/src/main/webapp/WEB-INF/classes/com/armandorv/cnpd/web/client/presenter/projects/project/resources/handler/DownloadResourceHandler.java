package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.menu.Item;

public class DownloadResourceHandler implements SelectionHandler<Item>
{
   private static final String DOWNLOAD_SERVLET_URL = GWT.getHostPageBaseURL() + "Download";

   private boolean drafts;

   private ResourcesPresenter presenter;

   public DownloadResourceHandler(ResourcesPresenter presenter, boolean draft)
   {
      super();
      this.drafts = draft;
      this.presenter = presenter;
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      ResourceInfo resource = presenter.getDisplay().getSelected(drafts);
      Window.open(DOWNLOAD_SERVLET_URL + "?resource_id=" + resource.getId(), "", "");
   }

}
