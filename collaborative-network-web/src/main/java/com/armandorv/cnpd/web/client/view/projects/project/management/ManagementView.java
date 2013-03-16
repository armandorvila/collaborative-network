package com.armandorv.cnpd.web.client.view.projects.project.management;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.view.projects.project.management.util.ManagementTreeElement;
import com.armandorv.cnpd.web.client.view.projects.project.management.util.ManagementIconProvider;
import com.armandorv.cnpd.web.client.view.projects.project.management.util.ManagementTreeFilter;
import com.armandorv.cnpd.web.client.view.projects.project.management.util.ManagementTreeCell;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter.Executable;

/**
 * View for project management. 
 * Show a tree with all management options.
 * 
 * @author armandorv
 *
 */
@Singleton
public class ManagementView extends Composite implements ManagementPresenter.Display
{
   public interface ManagementViewUiBinder extends UiBinder<Widget, ManagementView>
   {
   }

   private static ManagementViewUiBinder uiBinder = GWT.create(ManagementViewUiBinder.class);

   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private TreeStore<IdNameGenericInfo> store = new TreeStore<IdNameGenericInfo>(props.id());

   private ManagementTreeCell treeCell = new ManagementTreeCell();

   @UiField(provided = true)
   StoreFilterField<IdNameGenericInfo> filter = new ManagementTreeFilter();

   @UiField(provided = true)
   Tree<IdNameGenericInfo, String> tree = new Tree<IdNameGenericInfo, String>(store, props.name());

   public ManagementView()
   {
      fillStore();
      filter.bind(store);

      tree.setCell(treeCell);
      tree.setIconProvider(new ManagementIconProvider());
      tree.expandAll();

      super.initWidget(uiBinder.createAndBindUi(this));
   }

   private void fillStore()
   {
      store.add(ManagementTreeElement.PROJECT);
      store.add(ManagementTreeElement.MANAGER);
      store.add(ManagementTreeElement.MEMBERS);

      store.add(ManagementTreeElement.PROJECT, ManagementTreeElement.PUBLISH);
      store.add(ManagementTreeElement.PROJECT, ManagementTreeElement.DELETE);
      store.add(ManagementTreeElement.PROJECT, ManagementTreeElement.MODIFY);

      store.add(ManagementTreeElement.MANAGER, ManagementTreeElement.CHANGE_MANAGER);

      store.add(ManagementTreeElement.MEMBERS, ManagementTreeElement.INVITE_CONTACTS);
      store.add(ManagementTreeElement.MEMBERS, ManagementTreeElement.EXCLUDE_CONTACTS);
   }

   @Override
   public void setPublish(Executable publish)
   {
      treeCell.setPublish(publish);
   }

   @Override
   public void setDelete(Executable delete)
   {
      treeCell.setDelete(delete);
   }
   
   @Override
   public void setModify(Executable modify)
   {
      treeCell.setModify(modify);
   }

   @Override
   public void setChangeManager(Executable changeManager)
   {
      treeCell.setChangeManager(changeManager);
   }

   @Override
   public void setInviteContacts(Executable inviteContacts)
   {
      treeCell.setInviteContacts(inviteContacts);
   }

   @Override
   public void setExcludeContacts(Executable excludeContacts)
   {
      treeCell.setExcludeContacts(excludeContacts);
   }

   @Override
   public void setEnabled(boolean enabled)
   {
      super.setEnabled(enabled);
      tree.setEnabled(enabled);
      filter.setEnabled(enabled);

      if (!enabled)
      {
         filter.hide();
         tree.hide();
      }
      else
      {
         tree.show();
         filter.show();
      }

   }

}