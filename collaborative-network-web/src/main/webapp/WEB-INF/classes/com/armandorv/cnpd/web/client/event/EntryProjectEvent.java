package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryProjectEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryProjectEvent extends GwtEvent<EntryProjectEventHandler>
{
   public static Type<EntryProjectEventHandler> TYPE = new Type<EntryProjectEventHandler>();

   @Override
   public Type<EntryProjectEventHandler> getAssociatedType()
   {

      return TYPE;
   }

   @Override
   protected void dispatch(EntryProjectEventHandler handler)
   {
      handler.entryOnProject(this);
   }

}