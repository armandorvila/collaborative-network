package com.armandorv.cnpd.web.client.view.contacts;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter;
import com.armandorv.cnpd.web.client.view.info.InfoPanelView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.client.view.util.properties.ProjectInfoProperties;
import com.armandorv.cnpd.web.client.view.util.tooltip.ContactToolTipConfig;
import com.armandorv.cnpd.web.client.view.util.tooltip.RowToolTipConfig.HasRowToolTip;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class ContactView implements ContactPresenter.Display, HasRowToolTip<ContactInfo>
{
   @UiTemplate("ContactView.ui.xml")
   public interface ContactViewUiBinder extends UiBinder<Widget, ContactView>
   {
   }

   private ContactViewUiBinder uiBinder = GWT.create(ContactViewUiBinder.class);

   private ProjectInfoProperties projectProperties = GWT.create(ProjectInfoProperties.class);

   private ContactInfoProperties contactProperties = GWT.create(ContactInfoProperties.class);
   
   private static IconsBundle icons = GWT.create(IconsBundle.class);
   
   private TextButtonCell showProjectButton = new TextButtonCell();
   
   @UiField
   Window contactWindow;

   @UiField(provided = true)
   InfoPanelView personalInfo = new InfoPanelView();

   @UiField(provided = true)
   ColumnModel<ContactInfo> contactsColumnModel;

   @UiField(provided = true)
   ListStore<ContactInfo> contactsStore = new ListStore<ContactInfo>(contactProperties.id());

   @UiField
   Grid<ContactInfo> contactsGrid;

   @UiField
   GridView<ContactInfo> contactsView;

   @UiField(provided = true)
   ColumnModel<ProjectInfo> projectsColumnModel;

   @UiField(provided = true)
   ListStore<ProjectInfo> projectsStore = new ListStore<ProjectInfo>(projectProperties.id());

   @UiField
   Grid<ProjectInfo> projectsGrid;

   @UiField
   GridView<ProjectInfo> projectsView;

   public ContactView()
   {
      this.initContactsGrid();
      this.initProjectsGrid();

      uiBinder.createAndBindUi(this);
      
      contactsGrid.setToolTipConfig(new ContactToolTipConfig(this));

      this.initFilters();
   }

   /* ******************** UI building methods ********************* */

   private void initProjectsGrid()
   {
      showProjectButton.setText("Open");

      projectsColumnModel = new ColumnModel<ProjectInfo>(new ColumnsBuilder<ProjectInfo>()
            .build("Title", projectProperties.title(), 300, false)
            .build("Knowledge area", projectProperties.title(), 300, true)
            .build("", projectProperties.title(), 50, true, showProjectButton)
            .get());
   }

   private void initContactsGrid()
   {
      contactsColumnModel = new ColumnModel<ContactInfo>(new ColumnsBuilder<ContactInfo>()
            .build("", contactProperties.name(), 50, true, new IconCell(icons.contact16()))
            .build("Name", contactProperties.name(), 150, false)
            .build("Lastname1", contactProperties.lastname1(), 150, true)
            .build("Lastname2", contactProperties.lastname2(), 150, true)
            .get());
   }

   private void initFilters()
   {
      new FiltersBuilder<ContactInfo>().build(contactProperties.name()).build(contactProperties.lastname1())
            .build(contactProperties.lastname2()).get(contactsGrid);

      new FiltersBuilder<ProjectInfo>().build(projectProperties.title()).build(projectProperties.area())
            .get(projectsGrid);
   }

   /* ***************** Display complaint methods ************** */
   @Override
   public Window asWindow()
   {
      return contactWindow;
   }

   @Override
   public void setContacts(List<ContactInfo> contacts)
   {
      contactsStore.clear();
      contactsStore.addAll(contacts);
   }

   @Override
   public void setProjects(List<ProjectInfo> projects)
   {
      projectsStore.clear();
      projectsStore.addAll(projects);
   }

   @Override
   public HasSelectHandlers getOpen()
   {
      return showProjectButton;
   }

   @Override
   public ProjectInfo getProject(int row)
   {
      return projectsStore.get(row);
   }

   @Override
   public void setUser(UserInfo user)
   {
      personalInfo.renderPersonalInfo(user);
      personalInfo.renderAcademicInfo(user);
      personalInfo.renderProfessionalInfo(user);
      personalInfo.disableManagment();
   }

   @Override
   public ContactInfo getMouseOvered(Element eventTarget)
   {
      return contactsStore.get(contactsView.findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return contactsGrid;
   }
}