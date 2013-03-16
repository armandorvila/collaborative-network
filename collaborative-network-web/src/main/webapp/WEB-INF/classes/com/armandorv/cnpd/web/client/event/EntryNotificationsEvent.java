package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryNotificationsEventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Definition of an event for entry on news section.
 * 
 * @author armandorv
 * 
 */
public class EntryNotificationsEvent extends GwtEvent<EntryNotificationsEventHandler>
{

   public static Type<EntryNotificationsEventHandler> TYPE = new Type<EntryNotificationsEventHandler>();

   @Override
   public Type<EntryNotificationsEventHandler> getAssociatedType()
   {

      return TYPE;
   }

   @Override
   protected void dispatch(EntryNotificationsEventHandler handler)
   {
      handler.entryOnNews(this);

   }

}