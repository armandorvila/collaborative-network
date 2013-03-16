package com.armandorv.cnpd.web.client.view.meetings;

import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter;
import com.armandorv.cnpd.web.client.view.util.builder.FiltersBuilder;
import com.armandorv.cnpd.web.client.view.util.builder.MenuBuilder;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MeetingInvitationsView extends MeetingsGridSupport implements MeetingsInvitationsPresenter.Display
{
   public interface MeetingInvitationsViewUiBinder extends UiBinder<Widget, MeetingInvitationsView>
   {
   }

   private static MeetingInvitationsViewUiBinder uiBinder = GWT.create(MeetingInvitationsViewUiBinder.class);

   private MenuItem accept = new MenuItem("Accept");

   private MenuItem refuse = new MenuItem("Refuse");

   public MeetingInvitationsView()
   {
      actions.setMenu(new MenuBuilder()
            .build(accept , icons.tick16())
            .build(refuse , icons.close16())
            .get());

      super.initWidget(uiBinder.createAndBindUi(this));

      new FiltersBuilder<MeetingInfo>()
            .buildBoolean(props.celebrated())
            .build(props.title())
            .buildDate(props.date())
            .get(grid);
   }

   @Override
   public HasSelectionHandlers<Item> getAccept()
   {
      return accept;
   }

   @Override
   public HasSelectionHandlers<Item> getRefuse()
   {
      return refuse;
   }

   @Override
   public void removeInvitation(MeetingInfo selected)
   {
      store.remove(selected);
   }

}