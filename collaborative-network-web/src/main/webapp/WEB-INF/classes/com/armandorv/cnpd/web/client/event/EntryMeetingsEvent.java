package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryMeetingsEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryMeetingsEvent extends GwtEvent<EntryMeetingsEventHandler>
{
   public static Type<EntryMeetingsEventHandler>  TYPE = new Type<EntryMeetingsEventHandler>(); 
   
   @Override
   public Type<EntryMeetingsEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryMeetingsEventHandler handler)
   {
      handler.onEntryMeetings(this);
   }

}
