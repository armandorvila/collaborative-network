package com.armandorv.cnpd.web.client.view.meetings;

import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MeetingsListView extends MeetingsGridSupport implements MeetingsListPresenter.Display
{
   public interface MeetingsListViewUiBinder extends UiBinder<Widget, MeetingsListView>
   {
   }

   private static MeetingsListViewUiBinder uiBinder = GWT.create(MeetingsListViewUiBinder.class);

   private MenuItem details = new MenuItem("Details");

   private MenuItem leave = new MenuItem("Leave");

   public MeetingsListView()
   {
      actions.setMenu(
            new MenuBuilder()
                  .build(details, icons.show16())
                  .build(leave, icons.exit16())
                  .get());

      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<MeetingInfo>()
            .buildBoolean(props.celebrated())
            .build(props.title())
            .buildDate(props.date())
            .get(grid);
   }

   @Override
   public HasSelectionHandlers<Item> getLeave()
   {
      return leave;
   }

   @Override
   public HasSelectionHandlers<Item> getDetails()
   {
      return details;
   }

   @Override
   public void setLeaveEnabled(boolean enabled)
   {
      leave.setEnabled(enabled);
   }

}