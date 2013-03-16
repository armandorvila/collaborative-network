package com.armandorv.cnpd.web.client.event.handlers;

import com.armandorv.cnpd.web.client.event.EntryNotificationsEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * Define the operation for an entry news event.
 * 
 * @author armandorv
 * 
 */
public interface EntryNotificationsEventHandler extends EventHandler
{
   void entryOnNews(EntryNotificationsEvent event);
}
