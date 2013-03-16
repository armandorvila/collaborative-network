package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryProjectsEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryProjectsEventHandler extends EventHandler
{
   public void entryOnProjects(EntryProjectsEvent event);
}
