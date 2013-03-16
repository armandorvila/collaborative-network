package com.armandorv.cnpd.web.client.presenter.projects.project.references;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.remote.ReferencesService;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * Presenter for ReferencesList view, load the references list 
 * and execute user actions over list elements.
 * 
 * @author armandorv
 *
 */
@Singleton
public class ReferencesListPresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setReferences(List<ReferenceInfo> references);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getDelete();

      HasSelectionHandlers<Item> getOpen();

      ReferenceInfo getReference(int index);
      
      void removeReference(ReferenceInfo reference);
   }

   private ProjectInfo project;

   private ReferenceInfo selected;

   @Inject
   private Display display;

   @Inject
   private Caller<ReferencesService> referencesService;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getActions().addSelectHandler(select());
      display.getDelete().addSelectionHandler(delete());
      display.getOpen().addSelectionHandler(open());
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be setted");

      this.loadReferences();
   }

   private void loadReferences()
   {
      this.referencesService.call(new RemoteCallback<List<ReferenceInfo>>()
      {
         @Override
         public void callback(List<ReferenceInfo> response)
         {
            display.setReferences(response);
         }

      }).getReferences(project.getId());
   }

   private SelectionHandler<Item> open()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            Window.open(selected.getUrl(), selected.getName(), "");
         }
      };
   }

   private SelectHandler select()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            selected = display.getReference(event.getContext().getIndex());
         }
      };
   }

   private SelectionHandler<Item> delete()
   {
      return new SelectionHandler<Item>()
      {
         @Override
         public void onSelection(SelectionEvent<Item> event)
         {
            referencesService.call(new RemoteCallback<Boolean>()
            {
               @Override
               public void callback(Boolean response)
               {
                  if (response)
                  {
                     display.removeReference(selected);
                     Info.display("Success", "Reference deleted");
                  }
                  else
                  {
                     Info.display("Error", "Error removing reference");
                  }
               }
            }).removeReference(project.getId() , selected.getId());
         }
      };
   }

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}