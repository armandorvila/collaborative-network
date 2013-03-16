package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryMessagesEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryMessagesEventHandler extends EventHandler
{
   void entryOnMessages(EntryMessagesEvent event);
}
