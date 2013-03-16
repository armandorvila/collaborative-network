package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryReferencesEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryReferencesEventHandler extends EventHandler
{
   public void entryOnReferences(EntryReferencesEvent event);
}
