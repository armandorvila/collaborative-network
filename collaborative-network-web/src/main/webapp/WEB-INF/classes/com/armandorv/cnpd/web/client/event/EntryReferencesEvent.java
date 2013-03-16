package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryReferencesEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryReferencesEvent extends GwtEvent<EntryReferencesEventHandler>
{
   public static Type<EntryReferencesEventHandler> TYPE = new Type<EntryReferencesEventHandler>();

   @Override
   public Type<EntryReferencesEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryReferencesEventHandler handler)
   {
      handler.entryOnReferences(this);
   }

}