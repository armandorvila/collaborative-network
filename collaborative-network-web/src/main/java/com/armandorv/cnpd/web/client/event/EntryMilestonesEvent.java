package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryMilestonesEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryMilestonesEvent extends GwtEvent<EntryMilestonesEventHandler>
{
   public static Type<EntryMilestonesEventHandler> TYPE = new Type<EntryMilestonesEventHandler>();

   @Override
   public Type<EntryMilestonesEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryMilestonesEventHandler handler)
   {
      handler.entryOnSchedule(this);
   }

}