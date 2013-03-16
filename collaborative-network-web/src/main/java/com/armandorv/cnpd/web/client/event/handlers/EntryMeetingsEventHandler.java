package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryMeetingsEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryMeetingsEventHandler extends EventHandler
{
   public void onEntryMeetings(EntryMeetingsEvent event);
}
