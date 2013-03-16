package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryProjectEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryProjectEventHandler extends EventHandler
{
   public void entryOnProject(EntryProjectEvent event);
}
