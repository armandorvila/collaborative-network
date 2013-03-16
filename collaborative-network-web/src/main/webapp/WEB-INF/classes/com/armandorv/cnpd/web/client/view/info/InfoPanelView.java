package com.armandorv.cnpd.web.client.view.info;

import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.info.InfoPresenter;
import com.armandorv.cnpd.web.client.presenter.info.InfoPresenter.Executable;
import com.armandorv.cnpd.web.client.view.info.tree.InfoIconProvider;
import com.armandorv.cnpd.web.client.view.info.tree.InfoTreeCell;
import com.armandorv.cnpd.web.client.view.info.tree.InfoTreeElement;
import com.armandorv.cnpd.web.client.view.util.properties.IdNameGenericInfoProperties;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.theme.client.templates.InfoPanelTemplate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * Widget to show info of current user into an accordion panel. This widget can
 * be used without management section by call disable management method.
 * 
 * @author armandorv
 * 
 */
@Singleton
public class InfoPanelView extends Composite implements InfoPresenter.Display
{
   public interface InfoPanelViewUiBinder extends UiBinder<Widget, InfoPanelView>
   {
   }

   private InfoPanelViewUiBinder uiBinder = GWT.create(InfoPanelViewUiBinder.class);

   private InfoPanelTemplate renderer = GWT.create(InfoPanelTemplate.class);

   private static IdNameGenericInfoProperties props = GWT.create(IdNameGenericInfoProperties.class);

   private TreeStore<IdNameGenericInfo> store = new TreeStore<IdNameGenericInfo>(props.id());

   private InfoTreeCell treeCell = new InfoTreeCell();

   @UiField(provided = true)
   Tree<IdNameGenericInfo, String> tree = new Tree<IdNameGenericInfo, String>(store, props.name());

   @UiField
   AccordionLayoutContainer accordion;

   @UiField
   ContentPanel personalInfoPanel;

   @UiField
   FieldSet personal;

   @UiField
   FieldSet degrees;

   @UiField
   FieldSet jobs;

   @UiField
   ContentPanel managment;

   public InfoPanelView()
   {
      fillStore();
      tree.setCell(treeCell);
      tree.setIconProvider(new InfoIconProvider());
      tree.expandAll();
      initWidget(this.uiBinder.createAndBindUi(this));
   }

   private void fillStore()
   {
      store.add(InfoTreeElement.PERSONAL);
      store.add(InfoTreeElement.ACADEMIC);
      store.add(InfoTreeElement.PROFESSIONAL);
      store.add(InfoTreeElement.ACCOUNT);

      store.add(InfoTreeElement.PERSONAL, InfoTreeElement.MODIFY_PERSONAL_INFO);
      store.add(InfoTreeElement.ACADEMIC, InfoTreeElement.MODIFY_ACADEMIC_INFO);
      store.add(InfoTreeElement.PROFESSIONAL, InfoTreeElement.MODIFY_PROFESSIONAL_INFO);
      store.add(InfoTreeElement.ACCOUNT, InfoTreeElement.MODIFY_ACCOUNT_INFO);
      store.add(InfoTreeElement.ACCOUNT, InfoTreeElement.DELETE_ACCOUNT);
   }

   /**
    * Call to suppress management section, only visually;
    */
   public void disableManagment()
   {
      managment.setEnabled(false);
      managment.setVisible(false);
   }

   /* ************** Display complaint methods ************** */

   @Override
   public void renderPersonalInfo(UserInfo user)
   {
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderPersonalInfo(user));

      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(0.8, 0.8));

      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();

      personal.add(rowLayoutContainer);
      accordion.setActiveWidget(personalInfoPanel);

   }

   @Override
   public void renderAcademicInfo(UserInfo user)
   {
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderAcademicInfo(user));
      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(0.8, 0.8));
      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();
      degrees.add(rowLayoutContainer);
   }

   @Override
   public void renderProfessionalInfo(UserInfo user)
   {
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderProfessionalInfo(user));

      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(0.8, 0.8));

      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();

      this.jobs.add(rowLayoutContainer);
   }

   @Override
   public void setModifyPersonalInfo(Executable modifyPersonalInfo)
   {
      treeCell.setModifyPersonalInfo(modifyPersonalInfo);
   }

   @Override
   public void setModifyAcademicInfo(Executable modifyAcademicInfo)
   {
      treeCell.setModifyAcademicInfo(modifyAcademicInfo);
   }

   @Override
   public void setModifyProfessionalInfo(Executable modifyProfessionalInfo)
   {
      treeCell.setModifyProfessionalInfo(modifyProfessionalInfo);
   }

   @Override
   public void setModifyAccount(Executable modifyAccountInfo)
   {
      treeCell.setModifyAcccountInfo(modifyAccountInfo);
   }
   
   @Override
   public void setDeleteAccount(InfoPresenter.Executable deleteAccount){
      treeCell.setDeleteAccount(deleteAccount);
   }

}