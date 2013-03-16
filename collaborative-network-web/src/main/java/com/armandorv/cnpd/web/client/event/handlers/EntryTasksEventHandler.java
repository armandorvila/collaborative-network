package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryTasksEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryTasksEventHandler extends EventHandler
{
   public void entryOnTasks(EntryTasksEvent event);
}
