package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryTasksEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryTasksEvent extends GwtEvent<EntryTasksEventHandler>
{
   public static Type<EntryTasksEventHandler> TYPE = new Type<EntryTasksEventHandler>();

   @Override
   public Type<EntryTasksEventHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(EntryTasksEventHandler handler)
   {
      handler.entryOnTasks(this);
   }

}