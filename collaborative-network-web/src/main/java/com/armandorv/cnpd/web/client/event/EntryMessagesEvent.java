package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryMessagesEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryMessagesEvent extends GwtEvent<EntryMessagesEventHandler>
{
   public static Type<EntryMessagesEventHandler> TYPE = new Type<EntryMessagesEventHandler>();

   @Override
   public Type<EntryMessagesEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryMessagesEventHandler handler)
   {
      handler.entryOnMessages(this);

   }

}