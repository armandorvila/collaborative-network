package com.armandorv.cnpd.web.client.util;

import com.armandorv.cnpd.web.client.event.EntryContactsEvent;
import com.armandorv.cnpd.web.client.event.EntryDiscussionsEvent;
import com.armandorv.cnpd.web.client.event.EntryMeetingsEvent;
import com.armandorv.cnpd.web.client.event.EntryMessagesEvent;
import com.armandorv.cnpd.web.client.event.EntryMilestonesEvent;
import com.armandorv.cnpd.web.client.event.EntryNotificationsEvent;
import com.armandorv.cnpd.web.client.event.EntryProjectEvent;
import com.armandorv.cnpd.web.client.event.EntryProjectsEvent;
import com.armandorv.cnpd.web.client.event.EntryReferencesEvent;
import com.armandorv.cnpd.web.client.event.EntryResourcesEvent;
import com.armandorv.cnpd.web.client.event.EntryTasksEvent;
import com.armandorv.cnpd.web.client.event.handlers.EntryContactsEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryDiscussionsEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryMeetingsEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryMessagesEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryMilestonesEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryNotificationsEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryProjectEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryProjectsEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryReferencesEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryResourcesEventHandler;
import com.armandorv.cnpd.web.client.event.handlers.EntryTasksEventHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

/**
 * Class to create and register event handlers for GWT events.
 * 
 * @author armandorv
 * 
 */
public class HistoryEventRegister
{
   private HandlerManager eventBus;

   public HistoryEventRegister(HandlerManager eventBus)
   {
      this.eventBus = eventBus;
   }

   /**
    * Set all event handler to event bus.
    */
   public void registerHistoryManagementEvents()
   {
      registerEntryNews(eventBus);
      registerEntryMessages(eventBus);
      registerEntryContacts(eventBus);
      registerEntryProjects(eventBus);
      registerEntryMeetings(eventBus);
      registerEntryProject(eventBus);
      registerEntryResources(eventBus);
      registerEntryReferences(eventBus);
      registerEntryDiscussions(eventBus);
      registerEntryMilestones(eventBus);
      registerEntryTasks(eventBus);
   }

   /* ****** Methods for register history event handlers******* */
   private void registerEntryNews(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryNotificationsEvent.TYPE, new EntryNotificationsEventHandler()
      {
         @Override
         public void entryOnNews(EntryNotificationsEvent event)
         {
            History.newItem(HistoryToken.NOTIFICATIONS.getTokenName());
         }
      });
   }
   
   private void registerEntryMessages(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryMessagesEvent.TYPE, new EntryMessagesEventHandler()
      {
         @Override
         public void entryOnMessages(EntryMessagesEvent event)
         {
            History.newItem(HistoryToken.MESSAGES.getTokenName());
         }
      });
   }
   
   private void registerEntryContacts(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryContactsEvent.TYPE, new EntryContactsEventHandler()
      {
         @Override
         public void entryOnContacts(EntryContactsEvent event)
         {
            History.newItem(HistoryToken.CONTACTS.getTokenName());
         }
      });
   }
   
   private void registerEntryProjects(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryProjectsEvent.TYPE, new EntryProjectsEventHandler()
      {
         @Override
         public void entryOnProjects(EntryProjectsEvent event)
         {
            History.newItem(HistoryToken.PROJECTS.getTokenName());
         }
      });
   }
   
   private void registerEntryMeetings(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryMeetingsEvent.TYPE, new EntryMeetingsEventHandler()
      {
         @Override
         public void onEntryMeetings(EntryMeetingsEvent event)
         {
            History.newItem(HistoryToken.MEETINGS.getTokenName());
         }
      });
   }

   private void registerEntryProject(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryProjectEvent.TYPE, new EntryProjectEventHandler()
      {
         @Override
         public void entryOnProject(EntryProjectEvent event)
         {
            History.newItem(HistoryToken.PROJECT.getTokenName());
         }
      });
   }

   private void registerEntryResources(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryResourcesEvent.TYPE, new EntryResourcesEventHandler()
      {
         @Override
         public void entryOnResources(EntryResourcesEvent event)
         {
            History.newItem(HistoryToken.RESOURCES.getTokenName());

         }
      });
   }
   
   private void registerEntryReferences(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryReferencesEvent.TYPE, new EntryReferencesEventHandler()
      {
         @Override
         public void entryOnReferences(EntryReferencesEvent event)
         {
            History.newItem(HistoryToken.REFERENCES.getTokenName());
         }
      });
   }
   
   private void registerEntryDiscussions(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryDiscussionsEvent.TYPE, new EntryDiscussionsEventHandler()
      {
         @Override
         public void entryOnDiscussions(EntryDiscussionsEvent event)
         {
            History.newItem(HistoryToken.DISCUSSIONS.getTokenName());
         }
      });
   }
   
   private void registerEntryMilestones(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryMilestonesEvent.TYPE, new EntryMilestonesEventHandler()
      {
         @Override
         public void entryOnSchedule(EntryMilestonesEvent event)
         {
            History.newItem(HistoryToken.MILESTONES.getTokenName());
         }
      });
   }
   
   private void registerEntryTasks(HandlerManager eventBus)
   {
      eventBus.addHandler(EntryTasksEvent.TYPE, new EntryTasksEventHandler()
      {
         @Override
         public void entryOnTasks(EntryTasksEvent event)
         {
            History.newItem(HistoryToken.TASKS.getTokenName());
         }
      });
   }
}