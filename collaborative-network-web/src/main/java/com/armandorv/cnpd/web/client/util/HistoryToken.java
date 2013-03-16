package com.armandorv.cnpd.web.client.util;

import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter;
import com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter;
import com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter;

/**
 * Class to set a relationship between tokens and
 * presenters.
 * 
 * @author armandorv
 * 
 */
public class HistoryToken
{
   /*Singleton instances by token-presenter couple */
   public static final HistoryToken NOTIFICATIONS = new HistoryToken("notifications", NotificationsPresenter.class);

   public static final HistoryToken MESSAGES = new HistoryToken("messages", MessagesPresenter.class);

   public static final HistoryToken CONTACTS = new HistoryToken("contacts", ContactsPresenter.class);

   public static final HistoryToken MEETINGS = new HistoryToken("meetings", MeetingsPresenter.class);

   public static final HistoryToken PROJECTS = new HistoryToken("projects", ProjectsPresenter.class);

   public static final HistoryToken PROJECT = new HistoryToken("project", ProjectPresenter.class);

   public static final HistoryToken RESOURCES = new HistoryToken("resources", ResourcesPresenter.class);

   public static final HistoryToken REFERENCES = new HistoryToken("references", ReferencesPresenter.class);

   public static final HistoryToken DISCUSSIONS = new HistoryToken("discussions", DiscussionsPresenter.class);

   public static final HistoryToken MILESTONES = new HistoryToken("milestones", MilestonesPresenter.class);

   public static final HistoryToken TASKS = new HistoryToken("tasks", TasksPresenter.class);

   /**
    * Name of an instance of HistoryToken
    */
   private String tokenName;

   /**
    * Presenter of an instance of HistoryToken
    */
   private Class<? extends Presenter> presenterType;

   private HistoryToken(String tokenName, Class<? extends Presenter> presenterType)
   {
      this.tokenName = tokenName;
      this.presenterType = presenterType;
   }

   /**
    * 
    * @return the name of the token.
    */
   public String getTokenName()
   {
      return this.tokenName;
   }

   /**
    * The presenter class to be executed on this token.
    * @return
    */
   public Class<? extends Presenter> getPresenterType()
   {
      return this.presenterType;
   }

   /**
    * Get the correct HistoryToken by name.
    * 
    * @param token the name of a desired token.
    * @return an instance of historyToken.
    */
   public static HistoryToken getFromString(String token)
   {
      if (NOTIFICATIONS.tokenName.equals(token))
      {
         return NOTIFICATIONS;
      }

      if (MESSAGES.tokenName.equals(token))
      {
         return MESSAGES;
      }

      if (CONTACTS.tokenName.equals(token))
      {
         return CONTACTS;
      }

      if (MEETINGS.tokenName.equals(token))
      {
         return MEETINGS;
      }

      if (PROJECTS.tokenName.equals(token))
      {
         return PROJECTS;
      }

      if (PROJECT.tokenName.equals(token))
      {
         return PROJECT;
      }

      if (RESOURCES.tokenName.equals(token))
      {
         return RESOURCES;
      }

      if (REFERENCES.tokenName.equals(token))
      {
         return REFERENCES;
      }

      if (DISCUSSIONS.tokenName.equals(token))
      {
         return DISCUSSIONS;
      }

      if (MILESTONES.tokenName.equals(token))
      {
         return MILESTONES;
      }

      if (TASKS.tokenName.equals(token))
      {
         return TASKS;
      }

      return NOTIFICATIONS;
   }

}