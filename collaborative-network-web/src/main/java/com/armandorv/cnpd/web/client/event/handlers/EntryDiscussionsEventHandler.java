package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryDiscussionsEvent;
import com.google.gwt.event.shared.EventHandler;

public interface EntryDiscussionsEventHandler extends EventHandler
{
   public void entryOnDiscussions(EntryDiscussionsEvent event);
}
