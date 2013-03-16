package com.armandorv.cnpd.web.client.presenter.projects.project.resources;

import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.MoveLockerHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.MoveResourceHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.util.HandlerConfigurer;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menu.HasResourcesContextMenu;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menubar.ResourcesMenuBar;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.sencha.gxt.data.shared.event.StoreAddEvent.HasStoreAddHandlers;
import com.sencha.gxt.dnd.core.client.DragSource;

@Singleton
public class ResourcesPresenter implements Presenter
{
   private static final boolean DRAFT = true;

   private static final boolean NO_DRAFT = false;

   public interface Display
   {
      Integer getIndex();

      void setResources(List<ResourceInfo> data, boolean draft);
      
      void addResource(ResourceInfo parent, ResourceInfo resource, boolean free);

      void remove(ResourceInfo selectedResource, boolean draft);

      void rename(ResourceInfo resource, String string, boolean draft);

      ResourceInfo getSelected(boolean draft);

      ResourceInfo getFirst(boolean draft);

      ResourceInfo getParent(ResourceInfo resource, boolean draft);

      DragSource getDraggable(boolean draft);

      HasStoreAddHandlers<ResourceInfo> getOnStoreAdd(boolean draft);

      ResourcesMenuBar getMenuBar(boolean draft);

      HasResourcesContextMenu getContextMenuHolder(boolean draft);
   }

   @Inject
   @Project
   private Event<Integer> tabSelectedEvent;

   @Inject
   private Display display;

   @Inject
   private Caller<ResourcesService> resourcesService;

   @Inject
   private MoveLockerHandler locker;

   private MoveResourceHandler draftsMoveHanlder = new MoveResourceHandler(this, DRAFT);

   private MoveResourceHandler resourcesMoveHanlder = new MoveResourceHandler(this, NO_DRAFT);;

   private ProjectInfo project;

   private UserInfo user;

   @AfterInitialization
   public void afterInitialization()
   {
      display.getDraggable(NO_DRAFT).addDragStartHandler(locker);
      display.getDraggable(DRAFT).addDragStartHandler(locker);

      display.getOnStoreAdd(NO_DRAFT).addStoreAddHandler(resourcesMoveHanlder);
      display.getDraggable(NO_DRAFT).addDragStartHandler(resourcesMoveHanlder);

      display.getOnStoreAdd(DRAFT).addStoreAddHandler(draftsMoveHanlder);
      display.getDraggable(DRAFT).addDragStartHandler(draftsMoveHanlder);

      display.getMenuBar(DRAFT).configure(new HandlerConfigurer(this, DRAFT));
      display.getMenuBar(NO_DRAFT).configure(new HandlerConfigurer(this, NO_DRAFT));
      
      configureContextMenuHandlers(DRAFT);
      configureContextMenuHandlers(NO_DRAFT);
   }

   @Override
   public void present()
   {
      if (project == null)
         throw new ClientsideException("A project must be setted befor go call.");

      this.loadResources(NO_DRAFT);
      this.loadResources(DRAFT);

      tabSelectedEvent.fire(display.getIndex());
   }

   private void loadResources(final boolean draft)
   {
      resourcesService.call(new RemoteCallback<List<ResourceInfo>>()
      {
         @Override
         public void callback(List<ResourceInfo> response)
         {
            display.setResources(response, draft);
         }
      }).getResources(project.getId(), draft);
   }

   /**
    * Prepare handlers for context menu of project resources.
    */
   private void configureContextMenuHandlers(boolean draft)
   {
      HandlerConfigurer configurer = new HandlerConfigurer(this, draft);
     
      display.getContextMenuHolder(draft).configureEditableMenu(configurer);
      display.getContextMenuHolder(draft).configureNoEditableMenu(configurer);
      display.getContextMenuHolder(draft).configureShowableMenu(configurer);
      display.getContextMenuHolder(draft).configureMarkerMenu(configurer);
      display.getContextMenuHolder(draft).configureFolderMenu(configurer);
   }

   /* ********* Getters y setters ************* */
   public Caller<ResourcesService> getResourcesService()
   {
      return this.resourcesService;
   }

   public Display getDisplay()
   {
      return this.display;
   }

   public void setProject(@Observes ProjectInfo project)
   {
      this.project = project;
   }

   public ProjectInfo getProject()
   {
      return this.project;
   }

   public void setUser(@Observes UserInfo user)
   {
      this.user = user;
   }
   
   public UserInfo getUser(){
      return this.user;
   }
}