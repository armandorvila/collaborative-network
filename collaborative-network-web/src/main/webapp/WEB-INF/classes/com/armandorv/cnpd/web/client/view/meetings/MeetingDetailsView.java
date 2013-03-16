package com.armandorv.cnpd.web.client.view.meetings;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.IconCell;
import com.armandorv.cnpd.web.client.view.util.properties.ContactInfoProperties;
import com.armandorv.cnpd.web.client.view.util.tooltip.ContactToolTipConfig;
import com.armandorv.cnpd.web.client.view.util.tooltip.RowToolTipConfig.HasRowToolTip;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.armandorv.cnpd.web.theme.client.templates.MeetingInfoTemplate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class MeetingDetailsView implements MeetingDetailsPresenter.Display, HasRowToolTip<ContactInfo>
{
   public interface MeetingDetailsViewUiBinder extends UiBinder<Widget, MeetingDetailsView>
   {
   }

   private static MeetingDetailsViewUiBinder uiBinder = GWT.create(MeetingDetailsViewUiBinder.class);

   private static MeetingInfoTemplate renderer = GWT.create(MeetingInfoTemplate.class);

   private static ContactInfoProperties props = GWT.create(ContactInfoProperties.class);

   private static IconsBundle icons = GWT.create(IconsBundle.class);

   private ListStore<ContactInfo> store = new ListStore<ContactInfo>(props.id());

   @UiField
   Window window;

   @UiField
   Grid<ContactInfo> grid;

   @UiField
   GridView<ContactInfo> view;

   @UiField
   ColumnModel<ContactInfo> columnModel;

   @UiField
   TextButton invite;

   @UiField
   TextButton clausure;

   @UiField
   TextButton delete;

   @UiField
   FieldSet data;

   public MeetingDetailsView()
   {
      uiBinder.createAndBindUi(this);
      grid.setToolTipConfig(new ContactToolTipConfig(this));
   }

   @UiFactory
   ColumnModel<ContactInfo> createColumnModel()
   {
      return new ColumnModel<ContactInfo>(new ColumnsBuilder<ContactInfo>()
            .build("", props.name(), 50, true, new IconCell(icons.contact16()))
            .build("Name", props.name(), 150, false)
            .build("First Lastname", props.lastname1(), 150, false)
            .build("Second Lastname", props.lastname2(), 150, true)
            .get());
   }

   @UiFactory
   ListStore<ContactInfo> createListStore()
   {
      return store;
   }

   @Override
   public void setMeeting(MeetingInfo meeting)
   {
      if(!meeting.isCelebrated()){
         meeting.setConclusions("It hasn't been celebrated.");
      }
      
      final VerticalLayoutContainer rowLayoutContainer = new VerticalLayoutContainer();
      final HTML text = new HTML(renderer.renderMeetingInfo(meeting));

      text.addStyleName("pad-text");
      text.setLayoutData(new VerticalLayoutData(1, -1));

      rowLayoutContainer.add(text);
      rowLayoutContainer.forceLayout();

      data.add(rowLayoutContainer);
   }

   @Override
   public Window asWindow()
   {
      return window;
   }

   @Override
   public void setParticipants(List<ContactInfo> participants)
   {
      store.clear();
      store.addAll(participants);
   }

   @Override
   public HasSelectHandlers invite()
   {
      return invite;
   }

   @Override
   public HasSelectHandlers delete()
   {
      return delete;
   }

   @Override
   public void setManagementEnabled(boolean enabled)
   {
      delete.setEnabled(enabled);
      delete.setVisible(enabled);

      invite.setEnabled(enabled);
      invite.setVisible(enabled);
   }

   @Override
   public List<ContactInfo> getParticipants()
   {
      return store.getAll();
   }

   @Override
   public ContactInfo getMouseOvered(Element eventTarget)
   {
      return store.get(view.findRowIndex(eventTarget));
   }

   @Override
   public Grid<ContactInfo> getHasHandlers()
   {
      return grid;
   }

   @Override
   public HasSelectHandlers clausure()
   {
      return clausure;
   }

   @Override
   public void setInviteEnabled(boolean enabled)
   {
      invite.setEnabled(enabled);
      invite.setVisible(enabled);
   }

}