package com.armandorv.cnpd.web.client.presenter.projects.project.references;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.util.BooleanMessenger;
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
 * Presenter for new reference view. 
 * Deal with reference creation.
 * 
 * @author armandorv
 *
 */
@Singleton
public class NewReferencePresenter implements Presenter
{
   public interface Display
   {
      Widget asWidget();

      void setReferences(List<ReferenceInfo> references);

      HasSelectHandlers getActions();

      HasSelectionHandlers<Item> getOpen();

      HasSelectionHandlers<Item> getAdd();

      HasSelectHandlers getSearch();

      HasSelectHandlers getCreate();

      String getName();

      String getURL();

      String getKeyWords();

      void clear();

      ReferenceInfo getReference(int index);
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
      display.getCreate().addSelectHandler(create());
      display.getSearch().addSelectHandler(search());
      display.getActions().addSelectHandler(select());
      display.getAdd().addSelectionHandler(addSelected());
      display.getOpen().addSelectionHandler(open());
   }

   private SelectHandler create()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            if (validateFields())
            {
               referencesService.call(new RemoteCallback<Long>()
               {
                  @Override
                  public void callback(Long response)
                  {
                     Info.display("Success", "Reference created.");
                     display.clear();
                  }
               }).addNewReference(project.getId(), new ReferenceInfo(display.getName(), display.getURL()));
            }
         }
      };
   }

   boolean validateFields()
   {
      if ("".equals(display.getName()))
      {
         Info.display("Error", "You have to give a name.");
         return false;
      }
      if ("".equals(display.getURL()))
      {
         Info.display("Error", "You have to give a url.");
         return false;
      }
      
      if(!isValidURL(display.getURL())){
         Info.display("Error", "URL is invalid, use http://restofurl.anything");
         return false;
      }

      return true;
   }

   private boolean isValidURL(String url)
   {
      if(url.startsWith("http://")) return true;
      
      if(url.startsWith("https://")) return true;
      
      return false;
   }

   private SelectHandler search()
   {
      return new SelectHandler()
      {
         @Override
         public void onSelect(SelectEvent event)
         {
            referencesService.call(new RemoteCallback<List<ReferenceInfo>>()
            {
               @Override
               public void callback(List<ReferenceInfo> response)
               {
                  if (response.isEmpty())
                     Info.display("Notification", "No references found.");

                  display.setReferences(response);
               }
            }).searchReferences(project.getId(), display.getKeyWords());
         }
      };
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

   private SelectionHandler<Item> addSelected()
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
                     display.clear();

                  BooleanMessenger.getMessenger("Reference added.", "Reference no added.").message(response);
               }
            }).addReference(project.getId(), selected.getId());
         }
      };
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be setted.");

      display.clear();
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

   public void setProject(ProjectInfo project)
   {
      this.project = project;
   }

}