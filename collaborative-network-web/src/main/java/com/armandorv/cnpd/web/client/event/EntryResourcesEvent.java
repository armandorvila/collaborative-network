package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryResourcesEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryResourcesEvent extends GwtEvent<EntryResourcesEventHandler>
{
   public static Type<EntryResourcesEventHandler> TYPE = new Type<EntryResourcesEventHandler>();

   @Override
   public Type<EntryResourcesEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryResourcesEventHandler handler)
   {
      handler.entryOnResources(this);
   }

}