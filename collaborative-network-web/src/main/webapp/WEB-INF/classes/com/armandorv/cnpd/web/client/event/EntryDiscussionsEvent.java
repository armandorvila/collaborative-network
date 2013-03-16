package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryDiscussionsEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryDiscussionsEvent extends GwtEvent<EntryDiscussionsEventHandler>
{
   public static Type<EntryDiscussionsEventHandler> TYPE = new Type<EntryDiscussionsEventHandler>();

   @Override
   public Type<EntryDiscussionsEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryDiscussionsEventHandler handler)
   {
      handler.entryOnDiscussions(this);
   }

}