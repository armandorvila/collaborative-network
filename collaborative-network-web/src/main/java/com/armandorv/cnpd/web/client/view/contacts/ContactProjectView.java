package com.armandorv.cnpd.web.client.view.contacts;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.info.ProjectInfoView;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesFilter;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesIconProvider;
import com.armandorv.cnpd.web.client.view.projects.project.resources.util.ResourcesStoreFitter;
import com.armandorv.cnpd.web.client.view.util.properties.ResourceInfoProperties;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * View that show a project for visitor user.
 * 
 * @author armandorv
 *
 */
public class ContactProjectView implements ContactProjectPresenter.Display
{
   private static ResourceInfo ROOT = new ResourceInfo(-1, "Project", ResourceInfo.Kind.ROOT);
   
   @UiTemplate("ContactProjectView.ui.xml")
   public interface ContactProjectViewUiBinder extends UiBinder<Widget, ContactProjectView>
   {
   }

   private ContactProjectViewUiBinder uiBinder = GWT.create(ContactProjectViewUiBinder.class);
   
   private ResourceInfoProperties props = GWT.create(ResourceInfoProperties.class);

   private TreeStore<ResourceInfo> store = new TreeStore<ResourceInfo>(props.id());

   @UiField
   ProjectInfoView projectInfo;

   @UiField(provided = true)
   StoreFilterField<ResourceInfo> filter = new ResourcesFilter();

   @UiField(provided = true)
   Tree<ResourceInfo, String> projectResourcesTree = new Tree<ResourceInfo, String>(store, props.name());
   
   @UiField
   Window window;

   public ContactProjectView()
   {
      filter.bind(store);
      uiBinder.createAndBindUi(this);
      projectResourcesTree.setIconProvider(new ResourcesIconProvider());
   }

   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void renderProjectInfo(ProjectInfo project)
   {
      projectInfo.renderProjectData(project);
      projectInfo.renderMembers(project.getMembers());
   }
   
   @Override
   public void setResources(List<ResourceInfo> resources)
   {
      store.clear();
      new ResourcesStoreFitter(ROOT, store).fitResources(resources);
      projectResourcesTree.setExpanded(store.getRootItems().get(0), true);
   }

}