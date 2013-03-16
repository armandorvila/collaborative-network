package com.armandorv.cnpd.web.client.view.meetings;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabPanel;

@Singleton
public class MeetingsView extends Composite implements MeetingsPresenter.Display
{
   public interface MeetingsViewUiBinder extends UiBinder<Widget, MeetingsView>
   {
   }

   private static MeetingsViewUiBinder uiBinder = GWT.create(MeetingsViewUiBinder.class);

   @UiField
   MeetingsListView meetingsListView;

   @UiField
   MeetingInvitationsView meetingInvitationsView;

   @UiField
   NewMeetingView newMeetingView;
   
   @UiField
   TabPanel tabPanel;

   public MeetingsView()
   {
      super.initWidget(uiBinder.createAndBindUi(this));
   }

   @Override
   public Integer getIndex()
   {
      return MainWindow.Indexes.MEETINGS_INDEX;
   }

   @Override
   public HasSelectionHandlers<Widget> getTabPanel()
   {
      return tabPanel;
   }
   
   @Singleton
   @Produces
   public MeetingsListPresenter.Display produceMeetingsListView(){
      return meetingsListView;
   }
   
   @Singleton
   @Produces
   public MeetingsInvitationsPresenter.Display produceMeetingInvitationsview(){
      return meetingInvitationsView;
   }
   
   @Singleton
   @Produces
   public NewMeetingPresenter.Display produceNewMeetingView(){
      return newMeetingView;
   }

   @Override
   public void showProgress()
   {
      new ProgressView("Loading meetings" , "loading ..." , 2000).show();
   }
}
