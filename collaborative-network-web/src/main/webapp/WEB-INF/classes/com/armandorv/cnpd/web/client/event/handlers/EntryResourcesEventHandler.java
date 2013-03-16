package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryResourcesEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryResourcesEventHandler extends EventHandler
{
   public void entryOnResources(EntryResourcesEvent event);
}
