package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryMilestonesEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryMilestonesEventHandler extends EventHandler
{
   public void entryOnSchedule(EntryMilestonesEvent event);
}
