package com.armandorv.cnpd.web.client.view.meetings;

import java.util.List;

import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.builder.ColumnsBuilder;
import com.armandorv.cnpd.web.client.view.util.cell.BooleanIconCell;
import com.armandorv.cnpd.web.client.view.util.properties.MeetingInfoProperties;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.theme.client.icons.IconsBundle;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

/**
 * Base class for all grid of meetings.
 * 
 * @author armandorv
 *
 */
public class MeetingsGridSupport extends Composite
{
   protected static MeetingInfoProperties props = GWT.create(MeetingInfoProperties.class);

   protected static IconsBundle icons = GWT.create(IconsBundle.class);

   protected static ListStore<MeetingInfo> store = new ListStore<MeetingInfo>(props.id());

   protected TextButtonCell actions = new TextButtonCell();
   
   @UiField
   Grid<MeetingInfo> grid;

   @UiField
   GridView<MeetingInfo> view;

   @UiField
   ColumnModel<MeetingInfo> columnModel;

   @UiFactory
   ColumnModel<MeetingInfo> createColumnModel()
   {
      BooleanIconCell booleanIconCell = new BooleanIconCell(icons.tick16(),icons.waiting16());
      DateCell dateCell = new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM));
      
      actions.setText("Select");
      
      return new ColumnModel<MeetingInfo>(new ColumnsBuilder<MeetingInfo>()
            .buildBoolean("Celebrated", props.celebrated(), 150, false, booleanIconCell)
            .build("Title", props.title(), 150, false)
            .buildDate("Date", props.date(), 150, true, dateCell)
            .build("Actions", props.title(), 150, false, actions)
            .get());
   }

   @UiFactory
   ListStore<MeetingInfo> createListStore()
   {
      return store;
   }
   
   public void setMeetings(List<MeetingInfo> ivitations)
   {
      store.clear();
      store.addAll(ivitations);
   }
   
   public MeetingInfo getSelected(int index){
      return store.get(index);
   }
   
   public void removeMeeting(MeetingInfo meeting){
      store.remove(meeting);
   }
   
   public  HasSelectHandlers getActions(){
      return actions;
   }

   
   public void refresh(){
      view.refresh(false);
   }

   public void showProgress(){
      new ProgressView("Loading meetings", "loading", 2000).show();
   }
   
}