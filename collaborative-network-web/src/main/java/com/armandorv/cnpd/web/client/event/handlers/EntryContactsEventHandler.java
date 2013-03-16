package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryContactsEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryContactsEventHandler extends EventHandler
{
   public void entryOnContacts(EntryContactsEvent event);
}
