package com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler;

import org.jboss.errai.bus.client.api.RemoteCallback;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.resources.ShowableView;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Deal with click on showable resources and show it in a modal window. Resource
 * is supposed be showable.
 * 
 * @author armandorv
 * 
 */
public class ShowResourceHandler implements SelectionHandler<Item>
{

   private ResourcesPresenter presenter;

   private boolean free;

   public ShowResourceHandler(ResourcesPresenter presenter, boolean free)
   {
      this.presenter = presenter;
      this.free = free;
   }

   @Override
   public void onSelection(SelectionEvent<Item> event)
   {
      ResourceInfo selected = this.presenter.getDisplay().getSelected(free);
      
      presenter.getResourcesService().call(new RemoteCallback<String>()
      {
         @Override
         public void callback(String response)
         {
            ShowableView viewer = new ShowableView(response);
            viewer.asWindow().show();
         }
      }).prepareResourceToShow(selected);
      
     

   }

}
