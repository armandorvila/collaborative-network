package com.armandorv.cnpd.web.client.view.projects.project.resources;

import java.util.List;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menu.HasResourcesContextMenu;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menubar.ResourcesMenuBar;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.event.StoreAddEvent.HasStoreAddHandlers;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.widget.core.client.Composite;

@Singleton
public class ResourcesView extends Composite implements ResourcesPresenter.Display
{
   private static ResourcesViewUiBinder uiBinder = GWT.create(ResourcesViewUiBinder.class);

   interface ResourcesViewUiBinder extends UiBinder<Widget, ResourcesView>
   {
   }

   @UiField
   ResourcesTreeView resourcesTreeView;

   @UiField
   DraftsTreeView draftsTreeView;

   public ResourcesView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.RESOURCES_INDEX;
   }

   @Override
   public DragSource getDraggable(boolean draft)
   {
      if (draft)
         return draftsTreeView.getDraggable();
      else
         return resourcesTreeView.getProjectResourcesDraggable();
   }

   @Override
   public ResourceInfo getSelected(boolean draft)
   {
      if (draft)
         return draftsTreeView.getSelected();
      else
         return resourcesTreeView.getSelected();
   }

   @Override
   public ResourceInfo getFirst(boolean draft)
   {
      if (draft)
         return draftsTreeView.getFirstItem();
      else
         return resourcesTreeView.getFirstItem();
   }

   @Override
   public void setResources(List<ResourceInfo> resources, boolean draft)
   {
      if (draft)
         draftsTreeView.setResources(resources);
      else
         resourcesTreeView.setResources(resources);
   }

   @Override
   public void remove(ResourceInfo resource, boolean draft)
   {
      if (draft)
         draftsTreeView.removeResource(resource);
      else
         resourcesTreeView.removeResource(resource);
   }

   @Override
   public void rename(ResourceInfo resource, String string, boolean draft)
   {
      if (draft)
         draftsTreeView.renameResource(resource, string);
      else
         resourcesTreeView.renameResource(resource, string);
   }

   @Override
   public ResourcesMenuBar getMenuBar(boolean draft)
   {
      if (draft)
         return draftsTreeView.getMenu();
      else
         return resourcesTreeView.getMenu();
   }

   public HasResourcesContextMenu getContextMenuHolder(boolean draft)
   {
      if (draft)
         return draftsTreeView;
      else
         return resourcesTreeView;
   }

   @Override
   public HasStoreAddHandlers<ResourceInfo> getOnStoreAdd(boolean draft)
   {
      if (draft)
         return draftsTreeView.getOnStoreAdd();
      else
         return resourcesTreeView.getOnStoreAdd();
   }

   @Override
   public ResourceInfo getParent(ResourceInfo resource, boolean draft)
   {
      if (draft)
         return draftsTreeView.getParentOf(resource);
      else
         return resourcesTreeView.getParentOf(resource);
   }

   @Override
   public void addResource(ResourceInfo parent, ResourceInfo resource, boolean draft)
   {
      if(draft){
         draftsTreeView.addResource(parent , resource);
      }
      else {
         resourcesTreeView.addResource(parent, resource);
      }
   }

}