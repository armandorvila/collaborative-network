package com.armandorv.cnpd.web.client.event;

import com.armandorv.cnpd.web.client.event.handlers.EntryProjectsEventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class EntryProjectsEvent extends GwtEvent<EntryProjectsEventHandler>
{
   public static Type<EntryProjectsEventHandler> TYPE = new Type<EntryProjectsEventHandler>();

   @Override
   public Type<EntryProjectsEventHandler> getAssociatedType()
   {

      return TYPE;
   }

   @Override
   protected void dispatch(EntryProjectsEventHandler handler)
   {
      handler.entryOnProjects(this);
   }

}