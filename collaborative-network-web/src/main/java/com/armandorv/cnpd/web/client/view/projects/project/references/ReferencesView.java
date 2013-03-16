package com.armandorv.cnpd.web.client.view.projects.project.references;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * View for reference section of project view.
 * 
 * @author armandorv
 *
 */
@Singleton
public class ReferencesView extends Composite implements ReferencesPresenter.Display
{
   private static ReferencesViewUiBinder uiBinder = GWT.create(ReferencesViewUiBinder.class);

   interface ReferencesViewUiBinder extends UiBinder<Widget, ReferencesView>
   {
   }

   @UiField
   ReferencesListView referencesListView;

   @UiField
   NewReferenceView newReferenceView;

   @UiField
   TabPanel tabPanel;

   public ReferencesView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return ProjectView.Indexes.REFERENCES_INDEX;
   }

   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }

   @Produces
   @Singleton
   public ReferencesListPresenter.Display produceReferencesListView()
   {
      return referencesListView;
   }

   @Produces
   @Singleton
   public NewReferencePresenter.Display newReferencesView()
   {
      return newReferenceView;
   }

   @Override
   public void selectFirst()
   {
      tabPanel.setActiveWidget(tabPanel.getWidget(0));
   }

}