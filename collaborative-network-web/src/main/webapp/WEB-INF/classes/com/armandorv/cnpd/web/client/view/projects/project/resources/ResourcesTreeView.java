package com.armandorv.cnpd.web.client.view.projects.project.resources;

import java.util.List;

import com.armandorv.cnpd.web.client.view.projects.project.resources.menu.ContextMenuCustomizer;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menu.HasResourcesContextMenu;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menu.ContextMenuCustomizer.ContextMenuCustomized;
import com.armandorv.cnpd.web.client.view.projects.project.resources.menubar.ResourcesMenuBar;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesFilter;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesIconProvider;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesStoreFitter;
import com.armandorv.cnpd.web.client.view.util.properties.ResourceInfoProperties;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreAddEvent.HasStoreAddHandlers;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class ResourcesTreeView extends HasResourcesContextMenu implements ContextMenuCustomized
{
   private static ResourceInfo ROOT = new ResourceInfo(-1, "Project", ResourceInfo.Kind.ROOT);

   public interface ProjectResourcesViewUiBinder extends UiBinder<Widget, ResourcesTreeView>
   {
   }

   private static ProjectResourcesViewUiBinder uiBinder = GWT.create(ProjectResourcesViewUiBinder.class);

   private ResourceInfoProperties props = GWT.create(ResourceInfoProperties.class);

   private TreeStore<ResourceInfo> store = new TreeStore<ResourceInfo>(props.id());

   private TreeDragSource<ResourceInfo> dragSource = null;

   private TreeDropTarget<ResourceInfo> dropTarget = null;

   @SuppressWarnings("unused")
   private TreeDropTarget<ResourceInfo> externalDropTarget = null;

   @UiField(provided = true)
   StoreFilterField<ResourceInfo> filter = new ResourcesFilter();

   @UiField(provided = true)
   Tree<ResourceInfo, String> tree = new Tree<ResourceInfo, String>(store, props.name());

   @UiField(provided = true)
   ResourcesMenuBar menuBar = new ResourcesMenuBar();

   public ResourcesTreeView()
   {
      setUpDragAndDrop();

      tree.setContextMenu(super.getNoEditableContextMenu());
      filter.bind(store);

      super.initWidget(uiBinder.createAndBindUi(this));

      tree.setIconProvider(new ResourcesIconProvider());
      tree.addBeforeShowContextMenuHandler(new ContextMenuCustomizer(this));
   }

   private void setUpDragAndDrop()
   {
      dragSource = new TreeDragSource<ResourceInfo>(tree);
      dropTarget = new TreeDropTarget<ResourceInfo>(tree);
      dropTarget.setAllowSelfAsSource(true);
      dropTarget.setFeedback(Feedback.BOTH);
   }

   public Tree<ResourceInfo, String> getTree()
   {
      return tree;
   }

   public DragSource getProjectResourcesDraggable()
   {
      return dragSource;
   }

   public ResourceInfo getFirstItem()
   {
      return store.getRootItems().get(0);
   }

   public TreeStore<ResourceInfo> getStore()
   {
      return store;
   }

   public void setResources(List<ResourceInfo> resources)
   {
      store.clear();
      new ResourcesStoreFitter(ROOT, store).fitResources(resources);
      tree.setExpanded(getFirstItem(), true);
   }

   public ResourcesMenuBar getMenu()
   {
      return menuBar;
   }

   @Override
   public ResourceInfo getSelected()
   {
      return tree.getSelectionModel().getSelectedItem();
   }

   @Override
   public void enableEditableResourceMenu()
   {
      tree.setContextMenu(super.getEditableResourcesContextMenu());
   }

   @Override
   public void enableShowableResourceMenu()
   {
      tree.setContextMenu(super.getShowableResourcesContextMenu());
   }

   @Override
   public void enableNoEditableResourceMenu()
   {
      tree.setContextMenu(super.getNoEditableContextMenu());
   }

   @Override
   public void enableMarkerContextMenu()
   {
      tree.setContextMenu(super.getMarkerContextMenu());
   }

   @Override
   public void disableContextMenu()
   {
      tree.setContextMenu(super.getEmptyMenu());
   }

   public void removeResource(ResourceInfo resource)
   {
      store.remove(resource);
   }

   public void renameResource(ResourceInfo resource, String string)
   {
      this.store.getRecord(resource).getModel().setName(string);
      this.tree.refresh(resource);
   }

   public DropTarget getDropTarget()
   {
      return dropTarget;
   }

   public HasStoreAddHandlers<ResourceInfo> getOnStoreAdd()
   {
      return store;
   }

   public ResourceInfo getParentOf(ResourceInfo resource)
   {
      return store.getParent(resource);
   }
   
   public void addResource(ResourceInfo parent, ResourceInfo resource)
   {
      store.add(parent, resource);
   }

   @Override
   public void enableFolderContextMenu()
   {
      tree.setContextMenu(super.getFolderContextMenu());
   }

}