package com.armandorv.cnpd.web.client.view.projects.project.info;

import java.util.List;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.theme.client.templates.ProjectInfoTemplate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldSet;

/**
 * View that shows info about the project.
 * 
 * @author armandorv
 *
 */
public class ProjectInfoView extends Composite
{
   private static ProjectInfoViewUiBinder uiBinder = GWT.create(ProjectInfoViewUiBinder.class);

   interface ProjectInfoViewUiBinder extends UiBinder<Widget, ProjectInfoView>
   {
   }

   @UiField
   FieldSet data;

   @UiField
   FieldSet members;

   private ProjectInfoTemplate renderer = GWT.create(ProjectInfoTemplate.class);

   public ProjectInfoView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   /**
    * Render a HTML template with project info.
    * 
    * @param project project to be render, manager name will be set.
    */
   public void renderProjectData(ProjectInfo project)
   { 
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderProjectInfo(project));
     
      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(1, -1));
     
      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();
     
      data.add(rowLayoutContainer);
   }

   /**
    * Render a template for project members.
    * 
    * @param members list of project members.
    */
   public void renderMembers(List<ContactInfo> members2)
   {
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderProjectMembers(members2));
     
      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(1, -1));
     
      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();
      
      members.add(rowLayoutContainer);
   }

}