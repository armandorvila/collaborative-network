package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryContactsEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryContactsEvent extends GwtEvent<EntryContactsEventHandler>
{
   public static Type<EntryContactsEventHandler> TYPE = new Type<EntryContactsEventHandler>();

   @Override
   public Type<EntryContactsEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryContactsEventHandler handler)
   {
      handler.entryOnContacts(this);
   }

}