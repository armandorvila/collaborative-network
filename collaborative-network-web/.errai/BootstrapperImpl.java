package org.jboss.errai.ioc.client;

import com.armandorv.cnpd.web.client.AppController;
import com.armandorv.cnpd.web.client.AppLoader;
import com.armandorv.cnpd.web.client.presenter.MainPresenter;
import com.armandorv.cnpd.web.client.presenter.Presenter;
import com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter;
import com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter;
import com.armandorv.cnpd.web.client.presenter.info.InfoPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter;
import com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter;
import com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter;
import com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionResultsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsDetailsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestoneCreator;
import com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter.Display;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.MoveLockerHandler;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter;
import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter;
import com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter;
import com.armandorv.cnpd.web.client.util.PresenterRetriever;
import com.armandorv.cnpd.web.client.view.MainWindow;
import com.armandorv.cnpd.web.client.view.chat.ChatView;
import com.armandorv.cnpd.web.client.view.chat.ConversationsView;
import com.armandorv.cnpd.web.client.view.contacts.ContactProjectView;
import com.armandorv.cnpd.web.client.view.contacts.ContactView;
import com.armandorv.cnpd.web.client.view.contacts.ContactsView;
import com.armandorv.cnpd.web.client.view.info.InfoPanelView;
import com.armandorv.cnpd.web.client.view.meetings.MeetingDetailsView;
import com.armandorv.cnpd.web.client.view.meetings.MeetingsView;
import com.armandorv.cnpd.web.client.view.messages.MessagesView;
import com.armandorv.cnpd.web.client.view.notifications.NotificationsView;
import com.armandorv.cnpd.web.client.view.projects.ProjectsView;
import com.armandorv.cnpd.web.client.view.projects.project.ProjectView;
import com.armandorv.cnpd.web.client.view.projects.project.discussions.DiscussionDetailsView;
import com.armandorv.cnpd.web.client.view.projects.project.discussions.DiscussionResultsView;
import com.armandorv.cnpd.web.client.view.projects.project.discussions.DiscussionVoteView;
import com.armandorv.cnpd.web.client.view.projects.project.discussions.DiscussionsView;
import com.armandorv.cnpd.web.client.view.projects.project.management.ManagementView;
import com.armandorv.cnpd.web.client.view.projects.project.management.MembersView;
import com.armandorv.cnpd.web.client.view.projects.project.milestones.MilestonesView;
import com.armandorv.cnpd.web.client.view.projects.project.references.ReferencesView;
import com.armandorv.cnpd.web.client.view.projects.project.resources.ResourcesView;
import com.armandorv.cnpd.web.client.view.projects.project.tasks.TasksView;
import com.armandorv.cnpd.web.client.view.singup.AcademicFormView;
import com.armandorv.cnpd.web.client.view.singup.ContactsFormView;
import com.armandorv.cnpd.web.client.view.singup.GoogleFormView;
import com.armandorv.cnpd.web.client.view.singup.PersonalFormView;
import com.armandorv.cnpd.web.client.view.singup.ProfessionalFormView;
import com.armandorv.cnpd.web.client.view.util.tooltip.RowToolTipConfig.HasRowToolTip;
import com.armandorv.cnpd.web.shared.model.ChatMessage;
import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Contacts;
import com.armandorv.cnpd.web.shared.qualifiers.Main;
import com.armandorv.cnpd.web.shared.qualifiers.Project;
import com.armandorv.cnpd.web.shared.remote.ChatService;
import com.armandorv.cnpd.web.shared.remote.ContactsService;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;
import com.armandorv.cnpd.web.shared.remote.InformationService;
import com.armandorv.cnpd.web.shared.remote.LoadingService;
import com.armandorv.cnpd.web.shared.remote.MeetingsService;
import com.armandorv.cnpd.web.shared.remote.MilestonesService;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.armandorv.cnpd.web.shared.remote.ReferencesService;
import com.armandorv.cnpd.web.shared.remote.ResourcesService;
import com.armandorv.cnpd.web.shared.remote.SingupService;
import com.armandorv.cnpd.web.shared.remote.TasksService;
import com.armandorv.cnpd.web.shared.remote.UsersService;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent.DndDragStartHandler;
import java.lang.annotation.Annotation;
import java.util.Set;
import javax.enterprise.event.Event;
import javax.inject.Provider;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.framework.Subscription;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.enterprise.client.cdi.AbstractCDIEventCallback;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.CDIProtocol;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.api.qualifiers.Any;
import org.jboss.errai.ioc.client.api.qualifiers.BuiltInQualifiers;
import org.jboss.errai.ioc.client.container.BeanRef;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.InitializationCallback;
import org.jboss.errai.ioc.client.container.ProxyResolver;

public class BootstrapperImpl implements Bootstrapper {
  static class com_armandorv_cnpd_web_client_AppController_inj2257_proxy extends AppController {
    private AppController $$_proxy_$$;
    public void startApp() {
      $$_proxy_$$.startApp();
    }

    public void onValueChange(ValueChangeEvent a0) {
      $$_proxy_$$.onValueChange(a0);
    }

    public Annotation getQualifierAsAnnotation(Class a0) {
      return $$_proxy_$$.getQualifierAsAnnotation(a0);
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(AppController proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  static class com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_inj2341_proxy extends AcademicFormPresenter {
    private AcademicFormPresenter $$_proxy_$$;
    public void present() {
      $$_proxy_$$.present();
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(AcademicFormPresenter proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  static class com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_inj2342_proxy extends ContactsFormPresenter {
    private ContactsFormPresenter $$_proxy_$$;
    public void present() {
      $$_proxy_$$.present();
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(ContactsFormPresenter proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  static class com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_inj2349_proxy extends PersonalFormPresenter {
    private PersonalFormPresenter $$_proxy_$$;
    public void present() {
      $$_proxy_$$.present();
    }

    public void saveToServer() {
      $$_proxy_$$.saveToServer();
    }

    public boolean clientValidation() {
      return $$_proxy_$$.clientValidation();
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(PersonalFormPresenter proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_tabSelectedEvent(DiscussionsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_NewReferencePresenter_display(NewReferencePresenter instance, Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_signupService(GoogleFormPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter::signupService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_nextPresenter(PersonalFormPresenter instance, AcademicFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter::nextPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_projectsListPresenter(ProjectsPresenter instance, ProjectsListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::projectsListPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_contactRequestsPresenter(ContactsPresenter instance, ContactRequestsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter::contactRequestsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_contactService(ContactPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter::contactService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_addCotnactEvent(ContactRequestsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter::addCotnactEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_meetingService(NewMeetingPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter::meetingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_resources_handler_MoveLockerHandler_display(MoveLockerHandler instance, com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.resources.handler.MoveLockerHandler::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_display(ContactProjectPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_display(ContactsFormPresenter instance, com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_previousPresenter(ProfessionalFormPresenter instance, AcademicFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter::previousPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_projectPresenter(ProjectsListPresenter instance, ProjectPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter::projectPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_discussionsService(DiscussionsListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter::discussionsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_NewTaskPresenter_display(NewTaskPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_info_InfoPresenter_usersService(InfoPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.info.InfoPresenter::usersService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_referencesListPresenter(ReferencesPresenter instance, ReferencesListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter::referencesListPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_previousPresenter(PersonalFormPresenter instance, GoogleFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter::previousPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_resourcesView(ProjectView instance, ResourcesView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::resourcesView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_locker(ResourcesPresenter instance, MoveLockerHandler value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter::locker = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_chat(MainWindow instance, ChatView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::chat = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_contactsService(ContactRequestsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_eventBus(ProjectPresenter instance, HandlerManager value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter::eventBus = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionVotePresenter_display(DiscussionVotePresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_display(ProjectsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_container(MainPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_newReferencePresenter(ReferencesPresenter instance, NewReferencePresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter::newReferencePresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_newTaskPresenter(TasksPresenter instance, NewTaskPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter::newTaskPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_tabSelectedEvent(NotificationsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_container(PersonalFormPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_display(DiscussionsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_util_PresenterRetriever_manager(PresenterRetriever instance, IOCBeanManager value) /*-{
    instance.@com.armandorv.cnpd.web.client.util.PresenterRetriever::manager = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_votePresenter(DiscussionsListPresenter instance, DiscussionVotePresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter::votePresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_projectsService(NotificationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_nextPresenter(AcademicFormPresenter instance, ProfessionalFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter::nextPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_informationService(ManagementPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter::informationService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_MembersPresenter_display(MembersPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_discussionsView(ProjectView instance, DiscussionsView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::discussionsView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_display(ContactsPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_previousPresenter(ContactsFormPresenter instance, ProfessionalFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter::previousPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_display(MessagesPresenter instance, com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_tabSelectedEvent(ContactsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_display(ContactsListPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_NewDiscussionPresenter_display(NewDiscussionPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_display(TasksPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_display(ProfessionalFormPresenter instance, com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_projectInvitationsPresenter(ProjectsPresenter instance, ProjectInvitationsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::projectInvitationsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksListPresenter_display(TasksListPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_findProjectsPresenter(ProjectsPresenter instance, FindProjectsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::findProjectsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_contacts(MainWindow instance, ContactsView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::contacts = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_meetingService(MeetingDetailsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter::meetingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_projectsService(NewProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_display(FindProjectsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_conversationsPresenter(MainPresenter instance, ConversationsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::conversationsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_display(DiscussionsListPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_AppController_presenterRetriever(AppController instance, PresenterRetriever value) /*-{
    instance.@com.armandorv.cnpd.web.client.AppController::presenterRetriever = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_display(PersonalFormPresenter instance, com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksSummaryPresenter_display(TasksSummaryPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_tabSelecteedEvent(ReferencesPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter::tabSelecteedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_tabSelectedEvent(MessagesPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_display(ResourcesPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_projectsService(ProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_nextPresenter(GoogleFormPresenter instance, PersonalFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter::nextPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_contactsService(MeetingDetailsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_display(ProjectsListPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_display(ManagementPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_display(NewMeetingPresenter instance, com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_display(NewProjectPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_conversationsPresenter(ProjectPresenter instance, ConversationsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter::conversationsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_infoPresenter(MainPresenter instance, InfoPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::infoPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_messages(MainWindow instance, MessagesView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::messages = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_newProjectPresenter(ProjectsPresenter instance, NewProjectPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::newProjectPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_resourcesService(ResourcesPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter::resourcesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_infoService(NotificationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter::infoService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_chat_ConversationsPresenter_chatService(ConversationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter::chatService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_display(ContactPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_container(GoogleFormPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_newMeetingPresenter(MeetingsPresenter instance, NewMeetingPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter::newMeetingPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_display(MeetingsPresenter instance, com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_previousPresenter(AcademicFormPresenter instance, PersonalFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter::previousPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_meetingService(MeetingsListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter::meetingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_display(AcademicFormPresenter instance, com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_creator(MilestonesPresenter instance, MilestoneCreator value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter::creator = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_tabSelectedEvent(MeetingsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_managementView(ProjectView instance, ManagementView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::managementView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestoneCreator_milestonesService(MilestoneCreator instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestoneCreator::milestonesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_display(ContactRequestsPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_newDiscussionPresenter(DiscussionsPresenter instance, NewDiscussionPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter::newDiscussionPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_display(MilestonesPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_eventBus(MainPresenter instance, HandlerManager value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::eventBus = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_display(MeetingsListPresenter instance, com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_MembersPresenter_projectsService(MembersPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_resourcesService(ContactProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter::resourcesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_display(ProjectPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_info_InfoPresenter_display(InfoPresenter instance, com.armandorv.cnpd.web.client.presenter.info.InfoPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.info.InfoPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tabSelectedEvent(TasksPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_contactsService(NewProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_contactsService(ManagementPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_NewDiscussionPresenter_discussionsService(NewDiscussionPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter::discussionsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_singUpService(ProfessionalFormPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter::singUpService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_contactPresenter(ContactsListPresenter instance, ContactPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter::contactPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_informationService(ContactsListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter::informationService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_tabSelectedEvent(ResourcesPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionResultsPresenter_display(DiscussionResultsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionResultsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionResultsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_projects(MainWindow instance, ProjectsView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::projects = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_meetingDetailsPresenter(MeetingsListPresenter instance, MeetingDetailsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter::meetingDetailsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_container(AcademicFormPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_tabSelectedEvent(MilestonesPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_contactsListPresenter(ContactsPresenter instance, ContactsListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter::contactsListPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_membersPresenter(ManagementPresenter instance, MembersPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter::membersPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_container(ProfessionalFormPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsInvitationsPresenter_meetingService(MeetingsInvitationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter::meetingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_nextPresenter(ProfessionalFormPresenter instance, ContactsFormPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter::nextPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestoneCreator_display(MilestoneCreator instance, com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestoneCreator::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_scheduleView(ProjectView instance, MilestonesView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::scheduleView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_NewTaskPresenter_tasksService(NewTaskPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter::tasksService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_findUsersPresenter(ContactsPresenter instance, FindUsersPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter::findUsersPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_display(NotificationsPresenter instance, com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_selectProject(ProjectsListPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter::selectProject = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@com.armandorv.cnpd.web.client.AppController::eventBus = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsDetailsPresenter_display(DiscussionsDetailsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsDetailsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsDetailsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_AppController_loadCurrentUser(AppController instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.AppController::loadCurrentUser = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_contactProjectPresenter(FindProjectsPresenter instance, ContactProjectPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter::contactProjectPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksListPresenter_tasksService(TasksListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter::tasksService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_tasksView(ProjectView instance, TasksView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::tasksView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_projectsService(ManagementPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_AppController_loadingService(AppController instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.AppController::loadingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_display(GoogleFormPresenter instance, com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_infoService(MessagesPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter::infoService = value;
  }-*/;

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_tabSelectedEvent(ProjectsPresenter instance, Event value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter::tabSelectedEvent = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_contactsService(FindUsersPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_AppLoader_appController(AppLoader instance, AppController value) /*-{
    instance.@com.armandorv.cnpd.web.client.AppLoader::appController = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionVotePresenter_discussionsService(DiscussionVotePresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter::discussionsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_projectsService(ContactProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesListPresenter_display(ReferencesListPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_display(MeetingDetailsPresenter instance, com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_managementPresenter(ProjectPresenter instance, ManagementPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter::managementPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_contactProjectPresenter(ContactPresenter instance, ContactProjectPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter::contactProjectPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_contactsService(ContactsListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_display(MainPresenter instance, com.armandorv.cnpd.web.client.presenter.MainPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_projectsService(ContactPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_projectsService(ProjectsListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_discussionsListPresenter(DiscussionsPresenter instance, DiscussionsListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter::discussionsListPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_contactPresenter(ContactRequestsPresenter instance, ContactPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter::contactPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_usersService(ContactPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter::usersService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_detailsPresenter(DiscussionsListPresenter instance, DiscussionsDetailsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter::detailsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_display(ReferencesPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_container(ContactsFormPresenter instance, RootLayoutPanel value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter::container = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_infoPanel(MainWindow instance, InfoPanelView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::infoPanel = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_contactsService(NewMeetingPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_singUpService(AcademicFormPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter::singUpService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_contactPresenter(FindUsersPresenter instance, ContactPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter::contactPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_singupService(PersonalFormPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter::singupService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_chat_ConversationsPresenter_display(ConversationsPresenter instance, com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectInvitationsPresenter_display(ProjectInvitationsPresenter instance, com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_infoService(NewProjectPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter::infoService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tasksListPresenter(TasksPresenter instance, TasksListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter::tasksListPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_discussionsResultsPresenter(DiscussionsListPresenter instance, DiscussionResultsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter::discussionsResultsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_NewReferencePresenter_referencesService(NewReferencePresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.NewReferencePresenter::referencesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_contactsService(NotificationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter::contactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_infoService(FindProjectsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter::infoService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesListPresenter_referencesService(ReferencesListPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter::referencesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_cotnactsService(MainPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::cotnactsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tasksSummaryPresenter(TasksPresenter instance, TasksSummaryPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter::tasksSummaryPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_singUpService(ContactsFormPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter::singUpService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsInvitationsPresenter_display(MeetingsInvitationsPresenter instance, com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_meetings(MainWindow instance, MeetingsView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::meetings = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_display(FindUsersPresenter instance, com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter::display = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_MainWindow_news(MainWindow instance, NotificationsView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.MainWindow::news = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_milestonesService(MilestonesPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter::milestonesService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_MainPresenter_loadingService(MainPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.MainPresenter::loadingService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksSummaryPresenter_tasksService(TasksSummaryPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter::tasksService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_ProjectInvitationsPresenter_projectsService(ProjectInvitationsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_view_projects_project_ProjectView_referencesView(ProjectView instance, ReferencesView value) /*-{
    instance.@com.armandorv.cnpd.web.client.view.projects.project.ProjectView::referencesView = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_meetingsInvitationsPresenter(MeetingsPresenter instance, MeetingsInvitationsPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter::meetingsInvitationsPresenter = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_projectsService(FindProjectsPresenter instance, Caller value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter::projectsService = value;
  }-*/;

  private native static void com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_meetingsListPresenter(MeetingsPresenter instance, MeetingsListPresenter value) /*-{
    instance.@com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter::meetingsListPresenter = value;
  }-*/;

  public native static RootLayoutPanel com_armandorv_cnpd_web_client_AppLoader_produceContainer(AppLoader instance) /*-{
    return instance.@com.armandorv.cnpd.web.client.AppLoader::produceContainer()();
  }-*/;

  public native static HandlerManager com_armandorv_cnpd_web_client_AppLoader_produceEventBus(AppLoader instance) /*-{
    return instance.@com.armandorv.cnpd.web.client.AppLoader::produceEventBus()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    CDIEventTypeLookup.get().addLookup("com.armandorv.cnpd.web.shared.model.UserInfo", "java.lang.Object");
    new CDI().__resetSubsystem();
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
    CreationalContext context = injContext.getRootContext();
    final CreationalCallback<MembersView> inj2249_MembersView_creationalCallback = new CreationalCallback<MembersView>() {
      public MembersView getInstance(final CreationalContext context) {
        Class beanType = MembersView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MembersView inj148_MembersView = new MembersView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj148_MembersView);
        return inj148_MembersView;
      }
    };
    final CreationalCallback<CallerProvider> inj2250_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
      public CallerProvider getInstance(final CreationalContext context) {
        Class beanType = CallerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final CallerProvider inj2232_CallerProvider = new CallerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2232_CallerProvider);
        return inj2232_CallerProvider;
      }
    };
    final CallerProvider inj2232_CallerProvider = inj2250_CallerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MembersPresenter> inj2248_MembersPresenter_creationalCallback = new CreationalCallback<MembersPresenter>() {
      public MembersPresenter getInstance(final CreationalContext context) {
        Class beanType = MembersPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MembersPresenter inj2247_MembersPresenter = new MembersPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2247_MembersPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_management_MembersPresenter_display(inj2247_MembersPresenter, inj2249_MembersView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_projects_project_management_MembersPresenter_projectsService(inj2247_MembersPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2247_MembersPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2247_MembersPresenter;
      }
    };
    final MembersPresenter inj2247_MembersPresenter = inj2248_MembersPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ProjectsView> inj2252_ProjectsView_creationalCallback = new CreationalCallback<ProjectsView>() {
      public ProjectsView getInstance(final CreationalContext context) {
        Class beanType = ProjectsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectsView inj2251_ProjectsView = new ProjectsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2251_ProjectsView);
        return inj2251_ProjectsView;
      }
    };
    final ProjectsView inj2251_ProjectsView = inj2252_ProjectsView_creationalCallback.getInstance(context);
    final CreationalCallback<MessageBusProvider> inj2255_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
      public MessageBusProvider getInstance(final CreationalContext context) {
        Class beanType = MessageBusProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessageBusProvider inj2238_MessageBusProvider = new MessageBusProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2238_MessageBusProvider);
        return inj2238_MessageBusProvider;
      }
    };
    final MessageBusProvider inj2238_MessageBusProvider = inj2255_MessageBusProvider_creationalCallback.getInstance(context);
    final CreationalCallback<ProjectInvitationsPresenter> inj2254_ProjectInvitationsPresenter_creationalCallback = new CreationalCallback<ProjectInvitationsPresenter>() {
      public ProjectInvitationsPresenter getInstance(final CreationalContext context) {
        Class beanType = ProjectInvitationsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectInvitationsPresenter inj2253_ProjectInvitationsPresenter = new ProjectInvitationsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2253_ProjectInvitationsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectInvitationsPresenter_projectsService(inj2253_ProjectInvitationsPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display> var1 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display var1 = inj2251_ProjectsView.produceProjectInvitationsView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var1);
            return var1;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_ProjectInvitationsPresenter_display(inj2253_ProjectInvitationsPresenter, context.getSingletonInstanceOrNew(injContext, var1, com.armandorv.cnpd.web.client.presenter.projects.ProjectInvitationsPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2253_ProjectInvitationsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var2 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2253_ProjectInvitationsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var3 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2253_ProjectInvitationsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var2.remove();
            var3.remove();
          }
        });
        return inj2253_ProjectInvitationsPresenter;
      }
    };
    final ProjectInvitationsPresenter inj2253_ProjectInvitationsPresenter = inj2254_ProjectInvitationsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<AppLoader> inj2256_AppLoader_creationalCallback = new CreationalCallback<AppLoader>() {
      public AppLoader getInstance(final CreationalContext context) {
        Class beanType = AppLoader.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AppLoader inj185_AppLoader = new AppLoader();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj185_AppLoader);
        final com_armandorv_cnpd_web_client_AppController_inj2257_proxy inj2257_proxy = new com_armandorv_cnpd_web_client_AppController_inj2257_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AppController>() {
          public void resolve(AppController obj) {
            inj2257_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2257_proxy, obj);
          }
        }, AppController.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_armandorv_cnpd_web_client_AppLoader_appController(inj185_AppLoader, inj2257_proxy);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj185_AppLoader.loadApp();
              }
            });
          }
        });
        return inj185_AppLoader;
      }
    };
    final AppLoader inj185_AppLoader = inj2256_AppLoader_creationalCallback.getInstance(context);
    final CreationalCallback<IOCBeanManagerProvider> inj2261_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
      public IOCBeanManagerProvider getInstance(final CreationalContext context) {
        Class beanType = IOCBeanManagerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final IOCBeanManagerProvider inj2246_IOCBeanManagerProvider = new IOCBeanManagerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2246_IOCBeanManagerProvider);
        return inj2246_IOCBeanManagerProvider;
      }
    };
    final IOCBeanManagerProvider inj2246_IOCBeanManagerProvider = inj2261_IOCBeanManagerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<PresenterRetriever> inj2260_PresenterRetriever_creationalCallback = new CreationalCallback<PresenterRetriever>() {
      public PresenterRetriever getInstance(final CreationalContext context) {
        Class beanType = PresenterRetriever.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final PresenterRetriever inj1110_PresenterRetriever = new PresenterRetriever();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1110_PresenterRetriever);
        com_armandorv_cnpd_web_client_util_PresenterRetriever_manager(inj1110_PresenterRetriever, inj2246_IOCBeanManagerProvider.get());
        return inj1110_PresenterRetriever;
      }
    };
    final CreationalCallback<EventProvider> inj2262_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
      public EventProvider getInstance(final CreationalContext context) {
        Class beanType = EventProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final EventProvider inj2230_EventProvider = new EventProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2230_EventProvider);
        return inj2230_EventProvider;
      }
    };
    final EventProvider inj2230_EventProvider = inj2262_EventProvider_creationalCallback.getInstance(context);
    final CreationalCallback<AppController> inj2259_AppController_creationalCallback = new CreationalCallback<AppController>() {
      public AppController getInstance(final CreationalContext context) {
        Class beanType = AppController.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AppController inj2258_AppController = new AppController();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2258_AppController);
        com_armandorv_cnpd_web_client_AppController_eventBus(inj2258_AppController, com_armandorv_cnpd_web_client_AppLoader_produceEventBus(inj185_AppLoader));
        com_armandorv_cnpd_web_client_AppController_presenterRetriever(inj2258_AppController, inj2260_PresenterRetriever_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_AppController_loadingService(inj2258_AppController, inj2232_CallerProvider.provide(new Class[] { LoadingService.class }, null));
        com_armandorv_cnpd_web_client_AppController_loadCurrentUser(inj2258_AppController, inj2230_EventProvider.provide(new Class[] { UserInfo.class }, null));
        return inj2258_AppController;
      }
    };
    final AppController inj2258_AppController = inj2259_AppController_creationalCallback.getInstance(context);
    final CreationalCallback<ChatView> inj2264_ChatView_creationalCallback = new CreationalCallback<ChatView>() {
      public ChatView getInstance(final CreationalContext context) {
        Class beanType = ChatView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ChatView inj2263_ChatView = new ChatView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2263_ChatView);
        return inj2263_ChatView;
      }
    };
    final CreationalCallback<ContactsView> inj2266_ContactsView_creationalCallback = new CreationalCallback<ContactsView>() {
      public ContactsView getInstance(final CreationalContext context) {
        Class beanType = ContactsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactsView inj2265_ContactsView = new ContactsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2265_ContactsView);
        return inj2265_ContactsView;
      }
    };
    final ContactsView inj2265_ContactsView = inj2266_ContactsView_creationalCallback.getInstance(context);
    final CreationalCallback<ContactProjectView> inj2269_ContactProjectView_creationalCallback = new CreationalCallback<ContactProjectView>() {
      public ContactProjectView getInstance(final CreationalContext context) {
        Class beanType = ContactProjectView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactProjectView inj2186_ContactProjectView = new ContactProjectView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2186_ContactProjectView);
        return inj2186_ContactProjectView;
      }
    };
    final CreationalCallback<ContactProjectPresenter> inj2268_ContactProjectPresenter_creationalCallback = new CreationalCallback<ContactProjectPresenter>() {
      public ContactProjectPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactProjectPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactProjectPresenter inj2267_ContactProjectPresenter = new ContactProjectPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2267_ContactProjectPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_display(inj2267_ContactProjectPresenter, inj2269_ContactProjectView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_projectsService(inj2267_ContactProjectPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactProjectPresenter_resourcesService(inj2267_ContactProjectPresenter, inj2232_CallerProvider.provide(new Class[] { ResourcesService.class }, null));
        return inj2267_ContactProjectPresenter;
      }
    };
    final ContactProjectPresenter inj2267_ContactProjectPresenter = inj2268_ContactProjectPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ContactView> inj2272_ContactView_creationalCallback = new CreationalCallback<ContactView>() {
      public ContactView getInstance(final CreationalContext context) {
        Class beanType = ContactView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactView inj2188_ContactView = new ContactView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2188_ContactView);
        return inj2188_ContactView;
      }
    };
    final CreationalCallback<ContactPresenter> inj2271_ContactPresenter_creationalCallback = new CreationalCallback<ContactPresenter>() {
      public ContactPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactPresenter inj2270_ContactPresenter = new ContactPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2270_ContactPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_display(inj2270_ContactPresenter, inj2272_ContactView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_contactService(inj2270_ContactPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_projectsService(inj2270_ContactPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_usersService(inj2270_ContactPresenter, inj2232_CallerProvider.provide(new Class[] { UsersService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactPresenter_contactProjectPresenter(inj2270_ContactPresenter, inj2267_ContactProjectPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2270_ContactPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2270_ContactPresenter;
      }
    };
    final ContactPresenter inj2270_ContactPresenter = inj2271_ContactPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ContactsListPresenter> inj2274_ContactsListPresenter_creationalCallback = new CreationalCallback<ContactsListPresenter>() {
      public ContactsListPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactsListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactsListPresenter inj2273_ContactsListPresenter = new ContactsListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2273_ContactsListPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_contactsService(inj2273_ContactsListPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_informationService(inj2273_ContactsListPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_contactPresenter(inj2273_ContactsListPresenter, inj2270_ContactPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display> var4 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display var4 = inj2265_ContactsView.produceContactList();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var4);
            return var4;
          }
        };
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsListPresenter_display(inj2273_ContactsListPresenter, context.getSingletonInstanceOrNew(injContext, var4, com.armandorv.cnpd.web.client.presenter.contacts.ContactsListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2273_ContactsListPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var5 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ContactInfo", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.Contacts");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2273_ContactsListPresenter.addNewContact(message.get(ContactInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ContactInfo [@com.armandorv.cnpd.web.shared.qualifiers.Contacts()]";
          }
        });
        final Subscription var6 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ContactInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2273_ContactsListPresenter, new DestructionCallback<ContactInfo>() {
          public void destroy(final ContactInfo obj) {
            var5.remove();
            var6.remove();
          }
        });
        final Subscription var7 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2273_ContactsListPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var8 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2273_ContactsListPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var7.remove();
            var8.remove();
          }
        });
        return inj2273_ContactsListPresenter;
      }
    };
    final ContactsListPresenter inj2273_ContactsListPresenter = inj2274_ContactsListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ContactRequestsPresenter> inj2276_ContactRequestsPresenter_creationalCallback = new CreationalCallback<ContactRequestsPresenter>() {
      public ContactRequestsPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactRequestsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactRequestsPresenter inj2275_ContactRequestsPresenter = new ContactRequestsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2275_ContactRequestsPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display> var9 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display var9 = inj2265_ContactsView.produceContactRequests();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var9);
            return var9;
          }
        };
        com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_display(inj2275_ContactRequestsPresenter, context.getSingletonInstanceOrNew(injContext, var9, com.armandorv.cnpd.web.client.presenter.contacts.ContactRequestsPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_addCotnactEvent(inj2275_ContactRequestsPresenter, inj2230_EventProvider.provide(new Class[] { ContactInfo.class }, new Annotation[] { new Contacts() {
            public Class annotationType() {
              return Contacts.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Contacts()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_contactsService(inj2275_ContactRequestsPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactRequestsPresenter_contactPresenter(inj2275_ContactRequestsPresenter, inj2270_ContactPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2275_ContactRequestsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var10 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2275_ContactRequestsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var11 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2275_ContactRequestsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var10.remove();
            var11.remove();
          }
        });
        return inj2275_ContactRequestsPresenter;
      }
    };
    final ContactRequestsPresenter inj2275_ContactRequestsPresenter = inj2276_ContactRequestsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<FindUsersPresenter> inj2278_FindUsersPresenter_creationalCallback = new CreationalCallback<FindUsersPresenter>() {
      public FindUsersPresenter getInstance(final CreationalContext context) {
        Class beanType = FindUsersPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final FindUsersPresenter inj2277_FindUsersPresenter = new FindUsersPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2277_FindUsersPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_contactsService(inj2277_FindUsersPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_contactPresenter(inj2277_FindUsersPresenter, inj2270_ContactPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display> var12 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display var12 = inj2265_ContactsView.produceFindUsers();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var12);
            return var12;
          }
        };
        com_armandorv_cnpd_web_client_presenter_contacts_FindUsersPresenter_display(inj2277_FindUsersPresenter, context.getSingletonInstanceOrNew(injContext, var12, com.armandorv.cnpd.web.client.presenter.contacts.FindUsersPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2277_FindUsersPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var13 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2277_FindUsersPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var14 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2277_FindUsersPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var13.remove();
            var14.remove();
          }
        });
        return inj2277_FindUsersPresenter;
      }
    };
    final FindUsersPresenter inj2277_FindUsersPresenter = inj2278_FindUsersPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ContactsPresenter> inj2280_ContactsPresenter_creationalCallback = new CreationalCallback<ContactsPresenter>() {
      public ContactsPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactsPresenter inj2279_ContactsPresenter = new ContactsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2279_ContactsPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_display(inj2279_ContactsPresenter, inj2265_ContactsView);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_tabSelectedEvent(inj2279_ContactsPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Main() {
            public Class annotationType() {
              return Main.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Main()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_contactsListPresenter(inj2279_ContactsPresenter, inj2273_ContactsListPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_contactRequestsPresenter(inj2279_ContactsPresenter, inj2275_ContactRequestsPresenter);
        com_armandorv_cnpd_web_client_presenter_contacts_ContactsPresenter_findUsersPresenter(inj2279_ContactsPresenter, inj2277_FindUsersPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2279_ContactsPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2279_ContactsPresenter;
      }
    };
    final ContactsPresenter inj2279_ContactsPresenter = inj2280_ContactsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ConversationsView> inj2282_ConversationsView_creationalCallback = new CreationalCallback<ConversationsView>() {
      public ConversationsView getInstance(final CreationalContext context) {
        Class beanType = ConversationsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ConversationsView inj2281_ConversationsView = new ConversationsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2281_ConversationsView);
        return inj2281_ConversationsView;
      }
    };
    final CreationalCallback<ConversationsPresenter> inj2284_ConversationsPresenter_creationalCallback = new CreationalCallback<ConversationsPresenter>() {
      public ConversationsPresenter getInstance(final CreationalContext context) {
        Class beanType = ConversationsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ConversationsPresenter inj2283_ConversationsPresenter = new ConversationsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2283_ConversationsPresenter);
        com_armandorv_cnpd_web_client_presenter_chat_ConversationsPresenter_display(inj2283_ConversationsPresenter, inj2282_ConversationsView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_chat_ConversationsPresenter_chatService(inj2283_ConversationsPresenter, inj2232_CallerProvider.provide(new Class[] { ChatService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2283_ConversationsPresenter.initialize();
              }
            });
          }
        });
        final Subscription var15 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2283_ConversationsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var16 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2283_ConversationsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var15.remove();
            var16.remove();
          }
        });
        return inj2283_ConversationsPresenter;
      }
    };
    final ConversationsPresenter inj2283_ConversationsPresenter = inj2284_ConversationsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MeetingsView> inj2286_MeetingsView_creationalCallback = new CreationalCallback<MeetingsView>() {
      public MeetingsView getInstance(final CreationalContext context) {
        Class beanType = MeetingsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingsView inj2285_MeetingsView = new MeetingsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2285_MeetingsView);
        return inj2285_MeetingsView;
      }
    };
    final MeetingsView inj2285_MeetingsView = inj2286_MeetingsView_creationalCallback.getInstance(context);
    final CreationalCallback<MeetingsInvitationsPresenter> inj2288_MeetingsInvitationsPresenter_creationalCallback = new CreationalCallback<MeetingsInvitationsPresenter>() {
      public MeetingsInvitationsPresenter getInstance(final CreationalContext context) {
        Class beanType = MeetingsInvitationsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingsInvitationsPresenter inj2287_MeetingsInvitationsPresenter = new MeetingsInvitationsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2287_MeetingsInvitationsPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display> var17 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display var17 = inj2285_MeetingsView.produceMeetingInvitationsview();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var17);
            return var17;
          }
        };
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsInvitationsPresenter_display(inj2287_MeetingsInvitationsPresenter, context.getSingletonInstanceOrNew(injContext, var17, com.armandorv.cnpd.web.client.presenter.meetings.MeetingsInvitationsPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsInvitationsPresenter_meetingService(inj2287_MeetingsInvitationsPresenter, inj2232_CallerProvider.provide(new Class[] { MeetingsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2287_MeetingsInvitationsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var18 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2287_MeetingsInvitationsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var19 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2287_MeetingsInvitationsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var18.remove();
            var19.remove();
          }
        });
        return inj2287_MeetingsInvitationsPresenter;
      }
    };
    final MeetingsInvitationsPresenter inj2287_MeetingsInvitationsPresenter = inj2288_MeetingsInvitationsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionsView> inj2290_DiscussionsView_creationalCallback = new CreationalCallback<DiscussionsView>() {
      public DiscussionsView getInstance(final CreationalContext context) {
        Class beanType = DiscussionsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionsView inj2289_DiscussionsView = new DiscussionsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2289_DiscussionsView);
        return inj2289_DiscussionsView;
      }
    };
    final DiscussionsView inj2289_DiscussionsView = inj2290_DiscussionsView_creationalCallback.getInstance(context);
    final CreationalCallback<NewProjectPresenter> inj2292_NewProjectPresenter_creationalCallback = new CreationalCallback<NewProjectPresenter>() {
      public NewProjectPresenter getInstance(final CreationalContext context) {
        Class beanType = NewProjectPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NewProjectPresenter inj2291_NewProjectPresenter = new NewProjectPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2291_NewProjectPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_projectsService(inj2291_NewProjectPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_infoService(inj2291_NewProjectPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_contactsService(inj2291_NewProjectPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display> var20 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display var20 = inj2251_ProjectsView.produceNewProjectView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var20);
            return var20;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_NewProjectPresenter_display(inj2291_NewProjectPresenter, context.getSingletonInstanceOrNew(injContext, var20, com.armandorv.cnpd.web.client.presenter.projects.NewProjectPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2291_NewProjectPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var21 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2291_NewProjectPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var22 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2291_NewProjectPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var21.remove();
            var22.remove();
          }
        });
        return inj2291_NewProjectPresenter;
      }
    };
    final NewProjectPresenter inj2291_NewProjectPresenter = inj2292_NewProjectPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ManagementView> inj2294_ManagementView_creationalCallback = new CreationalCallback<ManagementView>() {
      public ManagementView getInstance(final CreationalContext context) {
        Class beanType = ManagementView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ManagementView inj2293_ManagementView = new ManagementView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2293_ManagementView);
        return inj2293_ManagementView;
      }
    };
    final ManagementView inj2293_ManagementView = inj2294_ManagementView_creationalCallback.getInstance(context);
    final CreationalCallback<ManagementPresenter> inj2296_ManagementPresenter_creationalCallback = new CreationalCallback<ManagementPresenter>() {
      public ManagementPresenter getInstance(final CreationalContext context) {
        Class beanType = ManagementPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ManagementPresenter inj2295_ManagementPresenter = new ManagementPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2295_ManagementPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_display(inj2295_ManagementPresenter, inj2293_ManagementView);
        com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_projectsService(inj2295_ManagementPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_contactsService(inj2295_ManagementPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_informationService(inj2295_ManagementPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_management_ManagementPresenter_membersPresenter(inj2295_ManagementPresenter, inj2247_MembersPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2295_ManagementPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var23 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2295_ManagementPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var24 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2295_ManagementPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var23.remove();
            var24.remove();
          }
        });
        return inj2295_ManagementPresenter;
      }
    };
    final ManagementPresenter inj2295_ManagementPresenter = inj2296_ManagementPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ResourcesView> inj2298_ResourcesView_creationalCallback = new CreationalCallback<ResourcesView>() {
      public ResourcesView getInstance(final CreationalContext context) {
        Class beanType = ResourcesView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ResourcesView inj2297_ResourcesView = new ResourcesView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2297_ResourcesView);
        return inj2297_ResourcesView;
      }
    };
    final ResourcesView inj2297_ResourcesView = inj2298_ResourcesView_creationalCallback.getInstance(context);
    final CreationalCallback<ReferencesView> inj2300_ReferencesView_creationalCallback = new CreationalCallback<ReferencesView>() {
      public ReferencesView getInstance(final CreationalContext context) {
        Class beanType = ReferencesView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ReferencesView inj2299_ReferencesView = new ReferencesView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2299_ReferencesView);
        return inj2299_ReferencesView;
      }
    };
    final ReferencesView inj2299_ReferencesView = inj2300_ReferencesView_creationalCallback.getInstance(context);
    final CreationalCallback<MilestonesView> inj2302_MilestonesView_creationalCallback = new CreationalCallback<MilestonesView>() {
      public MilestonesView getInstance(final CreationalContext context) {
        Class beanType = MilestonesView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MilestonesView inj2301_MilestonesView = new MilestonesView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2301_MilestonesView);
        return inj2301_MilestonesView;
      }
    };
    final MilestonesView inj2301_MilestonesView = inj2302_MilestonesView_creationalCallback.getInstance(context);
    final CreationalCallback<TasksView> inj2304_TasksView_creationalCallback = new CreationalCallback<TasksView>() {
      public TasksView getInstance(final CreationalContext context) {
        Class beanType = TasksView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TasksView inj2303_TasksView = new TasksView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2303_TasksView);
        return inj2303_TasksView;
      }
    };
    final InitializationCallback<ProjectView> init_inj2305_ProjectView = new InitializationCallback<ProjectView>() {
      public void init(final ProjectView obj) {
        obj.initView();
      }
    };
    final TasksView inj2303_TasksView = inj2304_TasksView_creationalCallback.getInstance(context);
    final CreationalCallback<ProjectView> inj2306_ProjectView_creationalCallback = new CreationalCallback<ProjectView>() {
      public ProjectView getInstance(final CreationalContext context) {
        Class beanType = ProjectView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectView inj2305_ProjectView = new ProjectView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2305_ProjectView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_managementView(inj2305_ProjectView, inj2293_ManagementView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_resourcesView(inj2305_ProjectView, inj2297_ResourcesView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_referencesView(inj2305_ProjectView, inj2299_ReferencesView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_discussionsView(inj2305_ProjectView, inj2289_DiscussionsView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_scheduleView(inj2305_ProjectView, inj2301_MilestonesView);
        com_armandorv_cnpd_web_client_view_projects_project_ProjectView_tasksView(inj2305_ProjectView, inj2303_TasksView);
        context.addInitializationCallback(inj2305_ProjectView, init_inj2305_ProjectView);
        return inj2305_ProjectView;
      }
    };
    final ProjectView inj2305_ProjectView = inj2306_ProjectView_creationalCallback.getInstance(context);
    final CreationalCallback<ProjectPresenter> inj2308_ProjectPresenter_creationalCallback = new CreationalCallback<ProjectPresenter>() {
      public ProjectPresenter getInstance(final CreationalContext context) {
        Class beanType = ProjectPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectPresenter inj2307_ProjectPresenter = new ProjectPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2307_ProjectPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_display(inj2307_ProjectPresenter, inj2305_ProjectView);
        com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_conversationsPresenter(inj2307_ProjectPresenter, inj2283_ConversationsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_managementPresenter(inj2307_ProjectPresenter, inj2295_ManagementPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_eventBus(inj2307_ProjectPresenter, com_armandorv_cnpd_web_client_AppLoader_produceEventBus(inj185_AppLoader));
        com_armandorv_cnpd_web_client_presenter_projects_project_ProjectPresenter_projectsService(inj2307_ProjectPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2307_ProjectPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var25 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2307_ProjectPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var26 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2307_ProjectPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var25.remove();
            var26.remove();
          }
        });
        final Subscription var27 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ContactInfo", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.ContactPlus");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2307_ProjectPresenter.contactConnected(message.get(ContactInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ContactInfo [@com.armandorv.cnpd.web.shared.qualifiers.ContactPlus()]";
          }
        });
        final Subscription var28 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ContactInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2307_ProjectPresenter, new DestructionCallback<ContactInfo>() {
          public void destroy(final ContactInfo obj) {
            var27.remove();
            var28.remove();
          }
        });
        final Subscription var29 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ContactInfo", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.ContactMinus");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2307_ProjectPresenter.contactDisconnected(message.get(ContactInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ContactInfo [@com.armandorv.cnpd.web.shared.qualifiers.ContactMinus()]";
          }
        });
        final Subscription var30 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ContactInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2307_ProjectPresenter, new DestructionCallback<ContactInfo>() {
          public void destroy(final ContactInfo obj) {
            var29.remove();
            var30.remove();
          }
        });
        final Subscription var31 = CDI.subscribe("java.lang.Integer", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.Project");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2307_ProjectPresenter.listenHistorySelection(message.get(Integer.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: java.lang.Integer [@com.armandorv.cnpd.web.shared.qualifiers.Project()]";
          }
        });
        final Subscription var32 = inj2238_MessageBusProvider.get().subscribe("cdi.event:java.lang.Integer", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2307_ProjectPresenter, new DestructionCallback<Integer>() {
          public void destroy(final Integer obj) {
            var31.remove();
            var32.remove();
          }
        });
        return inj2307_ProjectPresenter;
      }
    };
    final ProjectPresenter inj2307_ProjectPresenter = inj2308_ProjectPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ProjectsListPresenter> inj2310_ProjectsListPresenter_creationalCallback = new CreationalCallback<ProjectsListPresenter>() {
      public ProjectsListPresenter getInstance(final CreationalContext context) {
        Class beanType = ProjectsListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectsListPresenter inj2309_ProjectsListPresenter = new ProjectsListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2309_ProjectsListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_projectsService(inj2309_ProjectsListPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_selectProject(inj2309_ProjectsListPresenter, inj2230_EventProvider.provide(new Class[] { ProjectInfo.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_projectPresenter(inj2309_ProjectsListPresenter, inj2307_ProjectPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display> var33 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display var33 = inj2251_ProjectsView.produceListProjectsView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var33);
            return var33;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsListPresenter_display(inj2309_ProjectsListPresenter, context.getSingletonInstanceOrNew(injContext, var33, com.armandorv.cnpd.web.client.presenter.projects.ProjectsListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2309_ProjectsListPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var34 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2309_ProjectsListPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var35 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2309_ProjectsListPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var34.remove();
            var35.remove();
          }
        });
        return inj2309_ProjectsListPresenter;
      }
    };
    final ProjectsListPresenter inj2309_ProjectsListPresenter = inj2310_ProjectsListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<FindProjectsPresenter> inj2313_FindProjectsPresenter_creationalCallback = new CreationalCallback<FindProjectsPresenter>() {
      public FindProjectsPresenter getInstance(final CreationalContext context) {
        Class beanType = FindProjectsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final FindProjectsPresenter inj152_FindProjectsPresenter = new FindProjectsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj152_FindProjectsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_projectsService(inj152_FindProjectsPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_infoService(inj152_FindProjectsPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display> var36 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display var36 = inj2251_ProjectsView.produceFindProjectsView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var36);
            return var36;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_display(inj152_FindProjectsPresenter, context.getSingletonInstanceOrNew(injContext, var36, com.armandorv.cnpd.web.client.presenter.projects.FindProjectsPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_FindProjectsPresenter_contactProjectPresenter(inj152_FindProjectsPresenter, inj2267_ContactProjectPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj152_FindProjectsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var37 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj152_FindProjectsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var38 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj152_FindProjectsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var37.remove();
            var38.remove();
          }
        });
        return inj152_FindProjectsPresenter;
      }
    };
    final CreationalCallback<ProjectsPresenter> inj2312_ProjectsPresenter_creationalCallback = new CreationalCallback<ProjectsPresenter>() {
      public ProjectsPresenter getInstance(final CreationalContext context) {
        Class beanType = ProjectsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProjectsPresenter inj2311_ProjectsPresenter = new ProjectsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2311_ProjectsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_projectsListPresenter(inj2311_ProjectsPresenter, inj2309_ProjectsListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_findProjectsPresenter(inj2311_ProjectsPresenter, inj2313_FindProjectsPresenter_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_newProjectPresenter(inj2311_ProjectsPresenter, inj2291_NewProjectPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_projectInvitationsPresenter(inj2311_ProjectsPresenter, inj2253_ProjectInvitationsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_display(inj2311_ProjectsPresenter, inj2251_ProjectsView);
        com_armandorv_cnpd_web_client_presenter_projects_ProjectsPresenter_tabSelectedEvent(inj2311_ProjectsPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Main() {
            public Class annotationType() {
              return Main.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Main()";
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2311_ProjectsPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2311_ProjectsPresenter;
      }
    };
    final ProjectsPresenter inj2311_ProjectsPresenter = inj2312_ProjectsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ContactsFormView> inj2315_ContactsFormView_creationalCallback = new CreationalCallback<ContactsFormView>() {
      public ContactsFormView getInstance(final CreationalContext context) {
        Class beanType = ContactsFormView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactsFormView inj2314_ContactsFormView = new ContactsFormView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2314_ContactsFormView);
        return inj2314_ContactsFormView;
      }
    };
    final CreationalCallback<NewDiscussionPresenter> inj2317_NewDiscussionPresenter_creationalCallback = new CreationalCallback<NewDiscussionPresenter>() {
      public NewDiscussionPresenter getInstance(final CreationalContext context) {
        Class beanType = NewDiscussionPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NewDiscussionPresenter inj2316_NewDiscussionPresenter = new NewDiscussionPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2316_NewDiscussionPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display> var39 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display var39 = inj2289_DiscussionsView.produceNewDiscussionView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var39);
            return var39;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_NewDiscussionPresenter_display(inj2316_NewDiscussionPresenter, context.getSingletonInstanceOrNew(injContext, var39, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.NewDiscussionPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_NewDiscussionPresenter_discussionsService(inj2316_NewDiscussionPresenter, inj2232_CallerProvider.provide(new Class[] { DiscussionsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2316_NewDiscussionPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2316_NewDiscussionPresenter;
      }
    };
    final NewDiscussionPresenter inj2316_NewDiscussionPresenter = inj2317_NewDiscussionPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionDetailsView> inj2320_DiscussionDetailsView_creationalCallback = new CreationalCallback<DiscussionDetailsView>() {
      public DiscussionDetailsView getInstance(final CreationalContext context) {
        Class beanType = DiscussionDetailsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionDetailsView inj682_DiscussionDetailsView = new DiscussionDetailsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj682_DiscussionDetailsView);
        return inj682_DiscussionDetailsView;
      }
    };
    final CreationalCallback<DiscussionsDetailsPresenter> inj2319_DiscussionsDetailsPresenter_creationalCallback = new CreationalCallback<DiscussionsDetailsPresenter>() {
      public DiscussionsDetailsPresenter getInstance(final CreationalContext context) {
        Class beanType = DiscussionsDetailsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionsDetailsPresenter inj2318_DiscussionsDetailsPresenter = new DiscussionsDetailsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2318_DiscussionsDetailsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsDetailsPresenter_display(inj2318_DiscussionsDetailsPresenter, inj2320_DiscussionDetailsView_creationalCallback.getInstance(context));
        return inj2318_DiscussionsDetailsPresenter;
      }
    };
    final DiscussionsDetailsPresenter inj2318_DiscussionsDetailsPresenter = inj2319_DiscussionsDetailsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionVoteView> inj2322_DiscussionVoteView_creationalCallback = new CreationalCallback<DiscussionVoteView>() {
      public DiscussionVoteView getInstance(final CreationalContext context) {
        Class beanType = DiscussionVoteView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionVoteView inj2321_DiscussionVoteView = new DiscussionVoteView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2321_DiscussionVoteView);
        return inj2321_DiscussionVoteView;
      }
    };
    final DiscussionVoteView inj2321_DiscussionVoteView = inj2322_DiscussionVoteView_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionVotePresenter> inj2324_DiscussionVotePresenter_creationalCallback = new CreationalCallback<DiscussionVotePresenter>() {
      public DiscussionVotePresenter getInstance(final CreationalContext context) {
        Class beanType = DiscussionVotePresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionVotePresenter inj2323_DiscussionVotePresenter = new DiscussionVotePresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2323_DiscussionVotePresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionVotePresenter_display(inj2323_DiscussionVotePresenter, inj2321_DiscussionVoteView);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionVotePresenter_discussionsService(inj2323_DiscussionVotePresenter, inj2232_CallerProvider.provide(new Class[] { DiscussionsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2323_DiscussionVotePresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var40 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2323_DiscussionVotePresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var41 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2323_DiscussionVotePresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var40.remove();
            var41.remove();
          }
        });
        return inj2323_DiscussionVotePresenter;
      }
    };
    final DiscussionVotePresenter inj2323_DiscussionVotePresenter = inj2324_DiscussionVotePresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionResultsView> inj2327_DiscussionResultsView_creationalCallback = new CreationalCallback<DiscussionResultsView>() {
      public DiscussionResultsView getInstance(final CreationalContext context) {
        Class beanType = DiscussionResultsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionResultsView inj683_DiscussionResultsView = new DiscussionResultsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj683_DiscussionResultsView);
        return inj683_DiscussionResultsView;
      }
    };
    final CreationalCallback<DiscussionResultsPresenter> inj2326_DiscussionResultsPresenter_creationalCallback = new CreationalCallback<DiscussionResultsPresenter>() {
      public DiscussionResultsPresenter getInstance(final CreationalContext context) {
        Class beanType = DiscussionResultsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionResultsPresenter inj2325_DiscussionResultsPresenter = new DiscussionResultsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2325_DiscussionResultsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionResultsPresenter_display(inj2325_DiscussionResultsPresenter, inj2327_DiscussionResultsView_creationalCallback.getInstance(context));
        return inj2325_DiscussionResultsPresenter;
      }
    };
    final DiscussionResultsPresenter inj2325_DiscussionResultsPresenter = inj2326_DiscussionResultsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionsListPresenter> inj2329_DiscussionsListPresenter_creationalCallback = new CreationalCallback<DiscussionsListPresenter>() {
      public DiscussionsListPresenter getInstance(final CreationalContext context) {
        Class beanType = DiscussionsListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionsListPresenter inj2328_DiscussionsListPresenter = new DiscussionsListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2328_DiscussionsListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_discussionsService(inj2328_DiscussionsListPresenter, inj2232_CallerProvider.provide(new Class[] { DiscussionsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_detailsPresenter(inj2328_DiscussionsListPresenter, inj2318_DiscussionsDetailsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_votePresenter(inj2328_DiscussionsListPresenter, inj2323_DiscussionVotePresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_discussionsResultsPresenter(inj2328_DiscussionsListPresenter, inj2325_DiscussionResultsPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display> var42 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display var42 = inj2289_DiscussionsView.produceDiscussionsListView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var42);
            return var42;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsListPresenter_display(inj2328_DiscussionsListPresenter, context.getSingletonInstanceOrNew(injContext, var42, com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2328_DiscussionsListPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var43 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2328_DiscussionsListPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var44 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2328_DiscussionsListPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var43.remove();
            var44.remove();
          }
        });
        return inj2328_DiscussionsListPresenter;
      }
    };
    final DiscussionsListPresenter inj2328_DiscussionsListPresenter = inj2329_DiscussionsListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<DiscussionsPresenter> inj2331_DiscussionsPresenter_creationalCallback = new CreationalCallback<DiscussionsPresenter>() {
      public DiscussionsPresenter getInstance(final CreationalContext context) {
        Class beanType = DiscussionsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DiscussionsPresenter inj2330_DiscussionsPresenter = new DiscussionsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2330_DiscussionsPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_tabSelectedEvent(inj2330_DiscussionsPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Project() {
            public Class annotationType() {
              return Project.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Project()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_display(inj2330_DiscussionsPresenter, inj2289_DiscussionsView);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_discussionsListPresenter(inj2330_DiscussionsPresenter, inj2328_DiscussionsListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_discussions_DiscussionsPresenter_newDiscussionPresenter(inj2330_DiscussionsPresenter, inj2316_NewDiscussionPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2330_DiscussionsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var45 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ProjectInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2330_DiscussionsPresenter.setProjectInfo(message.get(ProjectInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ProjectInfo []";
          }
        });
        final Subscription var46 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ProjectInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2330_DiscussionsPresenter, new DestructionCallback<ProjectInfo>() {
          public void destroy(final ProjectInfo obj) {
            var45.remove();
            var46.remove();
          }
        });
        return inj2330_DiscussionsPresenter;
      }
    };
    final DiscussionsPresenter inj2330_DiscussionsPresenter = inj2331_DiscussionsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<InfoPanelView> inj2333_InfoPanelView_creationalCallback = new CreationalCallback<InfoPanelView>() {
      public InfoPanelView getInstance(final CreationalContext context) {
        Class beanType = InfoPanelView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InfoPanelView inj2332_InfoPanelView = new InfoPanelView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2332_InfoPanelView);
        return inj2332_InfoPanelView;
      }
    };
    final InfoPanelView inj2332_InfoPanelView = inj2333_InfoPanelView_creationalCallback.getInstance(context);
    final CreationalCallback<ReferencesListPresenter> inj2335_ReferencesListPresenter_creationalCallback = new CreationalCallback<ReferencesListPresenter>() {
      public ReferencesListPresenter getInstance(final CreationalContext context) {
        Class beanType = ReferencesListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ReferencesListPresenter inj2334_ReferencesListPresenter = new ReferencesListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2334_ReferencesListPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display> var47 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display var47 = inj2299_ReferencesView.produceReferencesListView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var47);
            return var47;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesListPresenter_display(inj2334_ReferencesListPresenter, context.getSingletonInstanceOrNew(injContext, var47, com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesListPresenter_referencesService(inj2334_ReferencesListPresenter, inj2232_CallerProvider.provide(new Class[] { ReferencesService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2334_ReferencesListPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2334_ReferencesListPresenter;
      }
    };
    final ReferencesListPresenter inj2334_ReferencesListPresenter = inj2335_ReferencesListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ProfessionalFormView> inj2337_ProfessionalFormView_creationalCallback = new CreationalCallback<ProfessionalFormView>() {
      public ProfessionalFormView getInstance(final CreationalContext context) {
        Class beanType = ProfessionalFormView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProfessionalFormView inj2336_ProfessionalFormView = new ProfessionalFormView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2336_ProfessionalFormView);
        return inj2336_ProfessionalFormView;
      }
    };
    final CreationalCallback<ProfessionalFormPresenter> inj2340_ProfessionalFormPresenter_creationalCallback = new CreationalCallback<ProfessionalFormPresenter>() {
      public ProfessionalFormPresenter getInstance(final CreationalContext context) {
        Class beanType = ProfessionalFormPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ProfessionalFormPresenter inj324_ProfessionalFormPresenter = new ProfessionalFormPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj324_ProfessionalFormPresenter);
        com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_display(inj324_ProfessionalFormPresenter, inj2337_ProfessionalFormView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_singUpService(inj324_ProfessionalFormPresenter, inj2232_CallerProvider.provide(new Class[] { SingupService.class }, null));
        final com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_inj2341_proxy inj2341_proxy = new com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_inj2341_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AcademicFormPresenter>() {
          public void resolve(AcademicFormPresenter obj) {
            inj2341_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2341_proxy, obj);
          }
        }, AcademicFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_previousPresenter(inj324_ProfessionalFormPresenter, inj2341_proxy);
        final com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_inj2342_proxy inj2342_proxy = new com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_inj2342_proxy();
        context.addUnresolvedProxy(new ProxyResolver<ContactsFormPresenter>() {
          public void resolve(ContactsFormPresenter obj) {
            inj2342_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2342_proxy, obj);
          }
        }, ContactsFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_nextPresenter(inj324_ProfessionalFormPresenter, inj2342_proxy);
        com_armandorv_cnpd_web_client_presenter_singup_ProfessionalFormPresenter_container(inj324_ProfessionalFormPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        return inj324_ProfessionalFormPresenter;
      }
    };
    final CreationalCallback<ContactsFormPresenter> inj2339_ContactsFormPresenter_creationalCallback = new CreationalCallback<ContactsFormPresenter>() {
      public ContactsFormPresenter getInstance(final CreationalContext context) {
        Class beanType = ContactsFormPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ContactsFormPresenter inj2338_ContactsFormPresenter = new ContactsFormPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2338_ContactsFormPresenter);
        com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_display(inj2338_ContactsFormPresenter, inj2315_ContactsFormView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_singUpService(inj2338_ContactsFormPresenter, inj2232_CallerProvider.provide(new Class[] { SingupService.class }, null));
        com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_previousPresenter(inj2338_ContactsFormPresenter, inj2340_ProfessionalFormPresenter_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_singup_ContactsFormPresenter_container(inj2338_ContactsFormPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        return inj2338_ContactsFormPresenter;
      }
    };
    final ContactsFormPresenter inj2338_ContactsFormPresenter = inj2339_ContactsFormPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<PersonalFormView> inj2344_PersonalFormView_creationalCallback = new CreationalCallback<PersonalFormView>() {
      public PersonalFormView getInstance(final CreationalContext context) {
        Class beanType = PersonalFormView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final PersonalFormView inj2343_PersonalFormView = new PersonalFormView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2343_PersonalFormView);
        return inj2343_PersonalFormView;
      }
    };
    final CreationalCallback<GoogleFormView> inj2346_GoogleFormView_creationalCallback = new CreationalCallback<GoogleFormView>() {
      public GoogleFormView getInstance(final CreationalContext context) {
        Class beanType = GoogleFormView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final GoogleFormView inj2345_GoogleFormView = new GoogleFormView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2345_GoogleFormView);
        return inj2345_GoogleFormView;
      }
    };
    final CreationalCallback<GoogleFormPresenter> inj2348_GoogleFormPresenter_creationalCallback = new CreationalCallback<GoogleFormPresenter>() {
      public GoogleFormPresenter getInstance(final CreationalContext context) {
        Class beanType = GoogleFormPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final GoogleFormPresenter inj2347_GoogleFormPresenter = new GoogleFormPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2347_GoogleFormPresenter);
        com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_display(inj2347_GoogleFormPresenter, inj2346_GoogleFormView_creationalCallback.getInstance(context));
        final com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_inj2349_proxy inj2349_proxy = new com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_inj2349_proxy();
        context.addUnresolvedProxy(new ProxyResolver<PersonalFormPresenter>() {
          public void resolve(PersonalFormPresenter obj) {
            inj2349_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2349_proxy, obj);
          }
        }, PersonalFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_nextPresenter(inj2347_GoogleFormPresenter, inj2349_proxy);
        com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_signupService(inj2347_GoogleFormPresenter, inj2232_CallerProvider.provide(new Class[] { SingupService.class }, null));
        com_armandorv_cnpd_web_client_presenter_singup_GoogleFormPresenter_container(inj2347_GoogleFormPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        return inj2347_GoogleFormPresenter;
      }
    };
    final GoogleFormPresenter inj2347_GoogleFormPresenter = inj2348_GoogleFormPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<PersonalFormPresenter> inj2351_PersonalFormPresenter_creationalCallback = new CreationalCallback<PersonalFormPresenter>() {
      public PersonalFormPresenter getInstance(final CreationalContext context) {
        Class beanType = PersonalFormPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final PersonalFormPresenter inj2350_PersonalFormPresenter = new PersonalFormPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2350_PersonalFormPresenter);
        com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_display(inj2350_PersonalFormPresenter, inj2344_PersonalFormView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_singupService(inj2350_PersonalFormPresenter, inj2232_CallerProvider.provide(new Class[] { SingupService.class }, null));
        com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_previousPresenter(inj2350_PersonalFormPresenter, context.getInstanceOrNew(inj2348_GoogleFormPresenter_creationalCallback, GoogleFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        final com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_inj2341_proxy inj2341_proxy = new com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_inj2341_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AcademicFormPresenter>() {
          public void resolve(AcademicFormPresenter obj) {
            inj2341_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2341_proxy, obj);
          }
        }, AcademicFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_nextPresenter(inj2350_PersonalFormPresenter, inj2341_proxy);
        com_armandorv_cnpd_web_client_presenter_singup_PersonalFormPresenter_container(inj2350_PersonalFormPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        return inj2350_PersonalFormPresenter;
      }
    };
    final PersonalFormPresenter inj2350_PersonalFormPresenter = inj2351_PersonalFormPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<AcademicFormView> inj2353_AcademicFormView_creationalCallback = new CreationalCallback<AcademicFormView>() {
      public AcademicFormView getInstance(final CreationalContext context) {
        Class beanType = AcademicFormView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AcademicFormView inj2352_AcademicFormView = new AcademicFormView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2352_AcademicFormView);
        return inj2352_AcademicFormView;
      }
    };
    final CreationalCallback<AcademicFormPresenter> inj2355_AcademicFormPresenter_creationalCallback = new CreationalCallback<AcademicFormPresenter>() {
      public AcademicFormPresenter getInstance(final CreationalContext context) {
        Class beanType = AcademicFormPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AcademicFormPresenter inj2354_AcademicFormPresenter = new AcademicFormPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2354_AcademicFormPresenter);
        com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_previousPresenter(inj2354_AcademicFormPresenter, context.getInstanceOrNew(inj2351_PersonalFormPresenter_creationalCallback, PersonalFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_nextPresenter(inj2354_AcademicFormPresenter, context.getInstanceOrNew(inj2340_ProfessionalFormPresenter_creationalCallback, ProfessionalFormPresenter.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_display(inj2354_AcademicFormPresenter, inj2353_AcademicFormView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_singUpService(inj2354_AcademicFormPresenter, inj2232_CallerProvider.provide(new Class[] { SingupService.class }, null));
        com_armandorv_cnpd_web_client_presenter_singup_AcademicFormPresenter_container(inj2354_AcademicFormPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        return inj2354_AcademicFormPresenter;
      }
    };
    final AcademicFormPresenter inj2354_AcademicFormPresenter = inj2355_AcademicFormPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<TasksListPresenter> inj2357_TasksListPresenter_creationalCallback = new CreationalCallback<TasksListPresenter>() {
      public TasksListPresenter getInstance(final CreationalContext context) {
        Class beanType = TasksListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TasksListPresenter inj2356_TasksListPresenter = new TasksListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2356_TasksListPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display> var48 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display var48 = inj2303_TasksView.produceTasksListView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var48);
            return var48;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksListPresenter_display(inj2356_TasksListPresenter, context.getSingletonInstanceOrNew(injContext, var48, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksListPresenter_tasksService(inj2356_TasksListPresenter, inj2232_CallerProvider.provide(new Class[] { TasksService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2356_TasksListPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2356_TasksListPresenter;
      }
    };
    final TasksListPresenter inj2356_TasksListPresenter = inj2357_TasksListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<NewTaskPresenter> inj2359_NewTaskPresenter_creationalCallback = new CreationalCallback<NewTaskPresenter>() {
      public NewTaskPresenter getInstance(final CreationalContext context) {
        Class beanType = NewTaskPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NewTaskPresenter inj2358_NewTaskPresenter = new NewTaskPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2358_NewTaskPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display> var49 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display var49 = inj2303_TasksView.produceNewTaskView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var49);
            return var49;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_NewTaskPresenter_display(inj2358_NewTaskPresenter, context.getSingletonInstanceOrNew(injContext, var49, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.NewTaskPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_NewTaskPresenter_tasksService(inj2358_NewTaskPresenter, inj2232_CallerProvider.provide(new Class[] { TasksService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2358_NewTaskPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2358_NewTaskPresenter;
      }
    };
    final NewTaskPresenter inj2358_NewTaskPresenter = inj2359_NewTaskPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<TasksSummaryPresenter> inj2361_TasksSummaryPresenter_creationalCallback = new CreationalCallback<TasksSummaryPresenter>() {
      public TasksSummaryPresenter getInstance(final CreationalContext context) {
        Class beanType = TasksSummaryPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TasksSummaryPresenter inj2360_TasksSummaryPresenter = new TasksSummaryPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2360_TasksSummaryPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display> var50 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display var50 = inj2303_TasksView.produceTasksSummaryView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var50);
            return var50;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksSummaryPresenter_display(inj2360_TasksSummaryPresenter, context.getSingletonInstanceOrNew(injContext, var50, com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksSummaryPresenter_tasksService(inj2360_TasksSummaryPresenter, inj2232_CallerProvider.provide(new Class[] { TasksService.class }, null));
        return inj2360_TasksSummaryPresenter;
      }
    };
    final TasksSummaryPresenter inj2360_TasksSummaryPresenter = inj2361_TasksSummaryPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<TasksPresenter> inj2363_TasksPresenter_creationalCallback = new CreationalCallback<TasksPresenter>() {
      public TasksPresenter getInstance(final CreationalContext context) {
        Class beanType = TasksPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TasksPresenter inj2362_TasksPresenter = new TasksPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2362_TasksPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tabSelectedEvent(inj2362_TasksPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Project() {
            public Class annotationType() {
              return Project.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Project()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_display(inj2362_TasksPresenter, inj2303_TasksView);
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tasksListPresenter(inj2362_TasksPresenter, inj2356_TasksListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_newTaskPresenter(inj2362_TasksPresenter, inj2358_NewTaskPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_tasks_TasksPresenter_tasksSummaryPresenter(inj2362_TasksPresenter, inj2360_TasksSummaryPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2362_TasksPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var51 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ProjectInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2362_TasksPresenter.setProject(message.get(ProjectInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ProjectInfo []";
          }
        });
        final Subscription var52 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ProjectInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2362_TasksPresenter, new DestructionCallback<ProjectInfo>() {
          public void destroy(final ProjectInfo obj) {
            var51.remove();
            var52.remove();
          }
        });
        return inj2362_TasksPresenter;
      }
    };
    final TasksPresenter inj2362_TasksPresenter = inj2363_TasksPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<InstanceProvider> inj2364_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
      public InstanceProvider getInstance(final CreationalContext context) {
        Class beanType = InstanceProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InstanceProvider inj2234_InstanceProvider = new InstanceProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2234_InstanceProvider);
        return inj2234_InstanceProvider;
      }
    };
    final InstanceProvider inj2234_InstanceProvider = inj2364_InstanceProvider_creationalCallback.getInstance(context);
    final CreationalCallback<DisposerProvider> inj2365_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
      public DisposerProvider getInstance(final CreationalContext context) {
        Class beanType = DisposerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DisposerProvider inj2228_DisposerProvider = new DisposerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2228_DisposerProvider);
        org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj2228_DisposerProvider, inj2246_IOCBeanManagerProvider.get());
        return inj2228_DisposerProvider;
      }
    };
    final DisposerProvider inj2228_DisposerProvider = inj2365_DisposerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<NotificationsView> inj2367_NotificationsView_creationalCallback = new CreationalCallback<NotificationsView>() {
      public NotificationsView getInstance(final CreationalContext context) {
        Class beanType = NotificationsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NotificationsView inj2366_NotificationsView = new NotificationsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2366_NotificationsView);
        return inj2366_NotificationsView;
      }
    };
    final NotificationsView inj2366_NotificationsView = inj2367_NotificationsView_creationalCallback.getInstance(context);
    final CreationalCallback<NotificationsPresenter> inj2369_NotificationsPresenter_creationalCallback = new CreationalCallback<NotificationsPresenter>() {
      public NotificationsPresenter getInstance(final CreationalContext context) {
        Class beanType = NotificationsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NotificationsPresenter inj2368_NotificationsPresenter = new NotificationsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2368_NotificationsPresenter);
        com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_display(inj2368_NotificationsPresenter, inj2366_NotificationsView);
        com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_infoService(inj2368_NotificationsPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_contactsService(inj2368_NotificationsPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_projectsService(inj2368_NotificationsPresenter, inj2232_CallerProvider.provide(new Class[] { ProjectsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_notifications_NotificationsPresenter_tabSelectedEvent(inj2368_NotificationsPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Main() {
            public Class annotationType() {
              return Main.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Main()";
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2368_NotificationsPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var53 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.NotificationInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2368_NotificationsPresenter.notification(message.get(NotificationInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.NotificationInfo []";
          }
        });
        final Subscription var54 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.NotificationInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2368_NotificationsPresenter, new DestructionCallback<NotificationInfo>() {
          public void destroy(final NotificationInfo obj) {
            var53.remove();
            var54.remove();
          }
        });
        final Subscription var55 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2368_NotificationsPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var56 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2368_NotificationsPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var55.remove();
            var56.remove();
          }
        });
        return inj2368_NotificationsPresenter;
      }
    };
    final NotificationsPresenter inj2368_NotificationsPresenter = inj2369_NotificationsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MoveLockerHandler> inj2371_MoveLockerHandler_creationalCallback = new CreationalCallback<MoveLockerHandler>() {
      public MoveLockerHandler getInstance(final CreationalContext context) {
        Class beanType = MoveLockerHandler.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MoveLockerHandler inj2370_MoveLockerHandler = new MoveLockerHandler();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2370_MoveLockerHandler);
        com_armandorv_cnpd_web_client_presenter_projects_project_resources_handler_MoveLockerHandler_display(inj2370_MoveLockerHandler, inj2297_ResourcesView);
        return inj2370_MoveLockerHandler;
      }
    };
    final CreationalCallback<RequestDispatcherProvider> inj2372_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
      public RequestDispatcherProvider getInstance(final CreationalContext context) {
        Class beanType = RequestDispatcherProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RequestDispatcherProvider inj2240_RequestDispatcherProvider = new RequestDispatcherProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2240_RequestDispatcherProvider);
        return inj2240_RequestDispatcherProvider;
      }
    };
    final RequestDispatcherProvider inj2240_RequestDispatcherProvider = inj2372_RequestDispatcherProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MessagesView> inj2374_MessagesView_creationalCallback = new CreationalCallback<MessagesView>() {
      public MessagesView getInstance(final CreationalContext context) {
        Class beanType = MessagesView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessagesView inj2373_MessagesView = new MessagesView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2373_MessagesView);
        return inj2373_MessagesView;
      }
    };
    final MessagesView inj2373_MessagesView = inj2374_MessagesView_creationalCallback.getInstance(context);
    final CreationalCallback<NewReferencePresenter> inj2376_NewReferencePresenter_creationalCallback = new CreationalCallback<NewReferencePresenter>() {
      public NewReferencePresenter getInstance(final CreationalContext context) {
        Class beanType = NewReferencePresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NewReferencePresenter inj2375_NewReferencePresenter = new NewReferencePresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2375_NewReferencePresenter);
        final CreationalCallback<Display> var57 = new CreationalCallback<Display>() {
          public Display getInstance(CreationalContext pContext) {
            Display var57 = inj2299_ReferencesView.newReferencesView();
            context.addBean(context.getBeanReference(Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var57);
            return var57;
          }
        };
        com_armandorv_cnpd_web_client_presenter_projects_project_references_NewReferencePresenter_display(inj2375_NewReferencePresenter, context.getSingletonInstanceOrNew(injContext, var57, Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_references_NewReferencePresenter_referencesService(inj2375_NewReferencePresenter, inj2232_CallerProvider.provide(new Class[] { ReferencesService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2375_NewReferencePresenter.afterInitialization();
              }
            });
          }
        });
        return inj2375_NewReferencePresenter;
      }
    };
    final NewReferencePresenter inj2375_NewReferencePresenter = inj2376_NewReferencePresenter_creationalCallback.getInstance(context);
    final CreationalCallback<NewMeetingPresenter> inj2378_NewMeetingPresenter_creationalCallback = new CreationalCallback<NewMeetingPresenter>() {
      public NewMeetingPresenter getInstance(final CreationalContext context) {
        Class beanType = NewMeetingPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final NewMeetingPresenter inj2377_NewMeetingPresenter = new NewMeetingPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2377_NewMeetingPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display> var58 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display var58 = inj2285_MeetingsView.produceNewMeetingView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var58);
            return var58;
          }
        };
        com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_display(inj2377_NewMeetingPresenter, context.getSingletonInstanceOrNew(injContext, var58, com.armandorv.cnpd.web.client.presenter.meetings.NewMeetingPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_meetingService(inj2377_NewMeetingPresenter, inj2232_CallerProvider.provide(new Class[] { MeetingsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_meetings_NewMeetingPresenter_contactsService(inj2377_NewMeetingPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2377_NewMeetingPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var59 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2377_NewMeetingPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var60 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2377_NewMeetingPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var59.remove();
            var60.remove();
          }
        });
        return inj2377_NewMeetingPresenter;
      }
    };
    final NewMeetingPresenter inj2377_NewMeetingPresenter = inj2378_NewMeetingPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<InitBallotProvider> inj2379_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
      public InitBallotProvider getInstance(final CreationalContext context) {
        Class beanType = InitBallotProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InitBallotProvider inj2244_InitBallotProvider = new InitBallotProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2244_InitBallotProvider);
        return inj2244_InitBallotProvider;
      }
    };
    final InitBallotProvider inj2244_InitBallotProvider = inj2379_InitBallotProvider_creationalCallback.getInstance(context);
    final CreationalCallback<SenderProvider> inj2380_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
      public SenderProvider getInstance(final CreationalContext context) {
        Class beanType = SenderProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final SenderProvider inj2236_SenderProvider = new SenderProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2236_SenderProvider);
        return inj2236_SenderProvider;
      }
    };
    final SenderProvider inj2236_SenderProvider = inj2380_SenderProvider_creationalCallback.getInstance(context);
    final CreationalCallback<ReferencesPresenter> inj2382_ReferencesPresenter_creationalCallback = new CreationalCallback<ReferencesPresenter>() {
      public ReferencesPresenter getInstance(final CreationalContext context) {
        Class beanType = ReferencesPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ReferencesPresenter inj2381_ReferencesPresenter = new ReferencesPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2381_ReferencesPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_display(inj2381_ReferencesPresenter, inj2299_ReferencesView);
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_tabSelecteedEvent(inj2381_ReferencesPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Project() {
            public Class annotationType() {
              return Project.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Project()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_referencesListPresenter(inj2381_ReferencesPresenter, inj2334_ReferencesListPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_references_ReferencesPresenter_newReferencePresenter(inj2381_ReferencesPresenter, inj2375_NewReferencePresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2381_ReferencesPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var61 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ProjectInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2381_ReferencesPresenter.setProject(message.get(ProjectInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ProjectInfo []";
          }
        });
        final Subscription var62 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ProjectInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2381_ReferencesPresenter, new DestructionCallback<ProjectInfo>() {
          public void destroy(final ProjectInfo obj) {
            var61.remove();
            var62.remove();
          }
        });
        return inj2381_ReferencesPresenter;
      }
    };
    final ReferencesPresenter inj2381_ReferencesPresenter = inj2382_ReferencesPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<RootPanelProvider> inj2383_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
      public RootPanelProvider getInstance(final CreationalContext context) {
        Class beanType = RootPanelProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RootPanelProvider inj2242_RootPanelProvider = new RootPanelProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2242_RootPanelProvider);
        return inj2242_RootPanelProvider;
      }
    };
    final RootPanelProvider inj2242_RootPanelProvider = inj2383_RootPanelProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MeetingDetailsView> inj2386_MeetingDetailsView_creationalCallback = new CreationalCallback<MeetingDetailsView>() {
      public MeetingDetailsView getInstance(final CreationalContext context) {
        Class beanType = MeetingDetailsView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingDetailsView inj154_MeetingDetailsView = new MeetingDetailsView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj154_MeetingDetailsView);
        return inj154_MeetingDetailsView;
      }
    };
    final CreationalCallback<MeetingDetailsPresenter> inj2385_MeetingDetailsPresenter_creationalCallback = new CreationalCallback<MeetingDetailsPresenter>() {
      public MeetingDetailsPresenter getInstance(final CreationalContext context) {
        Class beanType = MeetingDetailsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingDetailsPresenter inj2384_MeetingDetailsPresenter = new MeetingDetailsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2384_MeetingDetailsPresenter);
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_display(inj2384_MeetingDetailsPresenter, inj2386_MeetingDetailsView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_meetingService(inj2384_MeetingDetailsPresenter, inj2232_CallerProvider.provide(new Class[] { MeetingsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingDetailsPresenter_contactsService(inj2384_MeetingDetailsPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2384_MeetingDetailsPresenter.afterInitizlization();
              }
            });
          }
        });
        return inj2384_MeetingDetailsPresenter;
      }
    };
    final MeetingDetailsPresenter inj2384_MeetingDetailsPresenter = inj2385_MeetingDetailsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MeetingsListPresenter> inj2388_MeetingsListPresenter_creationalCallback = new CreationalCallback<MeetingsListPresenter>() {
      public MeetingsListPresenter getInstance(final CreationalContext context) {
        Class beanType = MeetingsListPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingsListPresenter inj2387_MeetingsListPresenter = new MeetingsListPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2387_MeetingsListPresenter);
        final CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display> var63 = new CreationalCallback<com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display>() {
          public com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display getInstance(CreationalContext pContext) {
            com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display var63 = inj2285_MeetingsView.produceMeetingsListView();
            context.addBean(context.getBeanReference(com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display.class, new Annotation[] { new Any() {
                public Class annotationType() {
                  return Any.class;
                }
            } }), var63);
            return var63;
          }
        };
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_display(inj2387_MeetingsListPresenter, context.getSingletonInstanceOrNew(injContext, var63, com.armandorv.cnpd.web.client.presenter.meetings.MeetingsListPresenter.Display.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_meetingService(inj2387_MeetingsListPresenter, inj2232_CallerProvider.provide(new Class[] { MeetingsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsListPresenter_meetingDetailsPresenter(inj2387_MeetingsListPresenter, inj2384_MeetingDetailsPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2387_MeetingsListPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var64 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2387_MeetingsListPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var65 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2387_MeetingsListPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var64.remove();
            var65.remove();
          }
        });
        return inj2387_MeetingsListPresenter;
      }
    };
    final MeetingsListPresenter inj2387_MeetingsListPresenter = inj2388_MeetingsListPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MeetingsPresenter> inj2390_MeetingsPresenter_creationalCallback = new CreationalCallback<MeetingsPresenter>() {
      public MeetingsPresenter getInstance(final CreationalContext context) {
        Class beanType = MeetingsPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MeetingsPresenter inj2389_MeetingsPresenter = new MeetingsPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2389_MeetingsPresenter);
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_tabSelectedEvent(inj2389_MeetingsPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Main() {
            public Class annotationType() {
              return Main.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Main()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_display(inj2389_MeetingsPresenter, inj2285_MeetingsView);
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_meetingsListPresenter(inj2389_MeetingsPresenter, inj2387_MeetingsListPresenter);
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_meetingsInvitationsPresenter(inj2389_MeetingsPresenter, inj2287_MeetingsInvitationsPresenter);
        com_armandorv_cnpd_web_client_presenter_meetings_MeetingsPresenter_newMeetingPresenter(inj2389_MeetingsPresenter, inj2377_NewMeetingPresenter);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2389_MeetingsPresenter.afterInitialization();
              }
            });
          }
        });
        return inj2389_MeetingsPresenter;
      }
    };
    final InitializationCallback<MainWindow> init_inj119_MainWindow = new InitializationCallback<MainWindow>() {
      public void init(final MainWindow obj) {
        obj.initMainView();
      }
    };
    final MeetingsPresenter inj2389_MeetingsPresenter = inj2390_MeetingsPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MainWindow> inj2393_MainWindow_creationalCallback = new CreationalCallback<MainWindow>() {
      public MainWindow getInstance(final CreationalContext context) {
        Class beanType = MainWindow.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MainWindow inj119_MainWindow = new MainWindow();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj119_MainWindow);
        com_armandorv_cnpd_web_client_view_MainWindow_infoPanel(inj119_MainWindow, inj2332_InfoPanelView);
        com_armandorv_cnpd_web_client_view_MainWindow_chat(inj119_MainWindow, inj2264_ChatView_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_view_MainWindow_news(inj119_MainWindow, inj2366_NotificationsView);
        com_armandorv_cnpd_web_client_view_MainWindow_messages(inj119_MainWindow, inj2373_MessagesView);
        com_armandorv_cnpd_web_client_view_MainWindow_contacts(inj119_MainWindow, inj2265_ContactsView);
        com_armandorv_cnpd_web_client_view_MainWindow_meetings(inj119_MainWindow, inj2285_MeetingsView);
        com_armandorv_cnpd_web_client_view_MainWindow_projects(inj119_MainWindow, inj2251_ProjectsView);
        context.addInitializationCallback(inj119_MainWindow, init_inj119_MainWindow);
        return inj119_MainWindow;
      }
    };
    final CreationalCallback<InfoPresenter> inj2394_InfoPresenter_creationalCallback = new CreationalCallback<InfoPresenter>() {
      public InfoPresenter getInstance(final CreationalContext context) {
        Class beanType = InfoPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InfoPresenter inj158_InfoPresenter = new InfoPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj158_InfoPresenter);
        com_armandorv_cnpd_web_client_presenter_info_InfoPresenter_display(inj158_InfoPresenter, inj2332_InfoPanelView);
        com_armandorv_cnpd_web_client_presenter_info_InfoPresenter_usersService(inj158_InfoPresenter, inj2232_CallerProvider.provide(new Class[] { UsersService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj158_InfoPresenter.initiallize();
              }
            });
          }
        });
        return inj158_InfoPresenter;
      }
    };
    final CreationalCallback<MainPresenter> inj2392_MainPresenter_creationalCallback = new CreationalCallback<MainPresenter>() {
      public MainPresenter getInstance(final CreationalContext context) {
        Class beanType = MainPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MainPresenter inj2391_MainPresenter = new MainPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2391_MainPresenter);
        com_armandorv_cnpd_web_client_presenter_MainPresenter_loadingService(inj2391_MainPresenter, inj2232_CallerProvider.provide(new Class[] { LoadingService.class }, null));
        com_armandorv_cnpd_web_client_presenter_MainPresenter_cotnactsService(inj2391_MainPresenter, inj2232_CallerProvider.provide(new Class[] { ContactsService.class }, null));
        com_armandorv_cnpd_web_client_presenter_MainPresenter_display(inj2391_MainPresenter, inj2393_MainWindow_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_MainPresenter_conversationsPresenter(inj2391_MainPresenter, inj2283_ConversationsPresenter);
        com_armandorv_cnpd_web_client_presenter_MainPresenter_infoPresenter(inj2391_MainPresenter, inj2394_InfoPresenter_creationalCallback.getInstance(context));
        com_armandorv_cnpd_web_client_presenter_MainPresenter_eventBus(inj2391_MainPresenter, com_armandorv_cnpd_web_client_AppLoader_produceEventBus(inj185_AppLoader));
        com_armandorv_cnpd_web_client_presenter_MainPresenter_container(inj2391_MainPresenter, com_armandorv_cnpd_web_client_AppLoader_produceContainer(inj185_AppLoader));
        final Subscription var66 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2391_MainPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var67 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2391_MainPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var66.remove();
            var67.remove();
          }
        });
        final Subscription var68 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ContactInfo", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.ContactPlus");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2391_MainPresenter.contactConnected(message.get(ContactInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ContactInfo [@com.armandorv.cnpd.web.shared.qualifiers.ContactPlus()]";
          }
        });
        final Subscription var69 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ContactInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2391_MainPresenter, new DestructionCallback<ContactInfo>() {
          public void destroy(final ContactInfo obj) {
            var68.remove();
            var69.remove();
          }
        });
        final Subscription var70 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ContactInfo", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.ContactMinus");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2391_MainPresenter.contactDisconnected(message.get(ContactInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ContactInfo [@com.armandorv.cnpd.web.shared.qualifiers.ContactMinus()]";
          }
        });
        final Subscription var71 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ContactInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2391_MainPresenter, new DestructionCallback<ContactInfo>() {
          public void destroy(final ContactInfo obj) {
            var70.remove();
            var71.remove();
          }
        });
        final Subscription var72 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ChatMessage", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2391_MainPresenter.newChatMessage(message.get(ChatMessage.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ChatMessage []";
          }
        });
        final Subscription var73 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ChatMessage", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2391_MainPresenter, new DestructionCallback<ChatMessage>() {
          public void destroy(final ChatMessage obj) {
            var72.remove();
            var73.remove();
          }
        });
        final Subscription var74 = CDI.subscribe("java.lang.Integer", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.armandorv.cnpd.web.shared.qualifiers.Main");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2391_MainPresenter.listenHistorySelection(message.get(Integer.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: java.lang.Integer [@com.armandorv.cnpd.web.shared.qualifiers.Main()]";
          }
        });
        final Subscription var75 = inj2238_MessageBusProvider.get().subscribe("cdi.event:java.lang.Integer", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2391_MainPresenter, new DestructionCallback<Integer>() {
          public void destroy(final Integer obj) {
            var74.remove();
            var75.remove();
          }
        });
        return inj2391_MainPresenter;
      }
    };
    final MainPresenter inj2391_MainPresenter = inj2392_MainPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MilestoneCreator> inj2397_MilestoneCreator_creationalCallback = new CreationalCallback<MilestoneCreator>() {
      public MilestoneCreator getInstance(final CreationalContext context) {
        Class beanType = MilestoneCreator.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MilestoneCreator inj1235_MilestoneCreator = new MilestoneCreator();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1235_MilestoneCreator);
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestoneCreator_display(inj1235_MilestoneCreator, inj2301_MilestonesView);
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestoneCreator_milestonesService(inj1235_MilestoneCreator, inj2232_CallerProvider.provide(new Class[] { MilestonesService.class }, null));
        return inj1235_MilestoneCreator;
      }
    };
    final CreationalCallback<MilestonesPresenter> inj2396_MilestonesPresenter_creationalCallback = new CreationalCallback<MilestonesPresenter>() {
      public MilestonesPresenter getInstance(final CreationalContext context) {
        Class beanType = MilestonesPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MilestonesPresenter inj2395_MilestonesPresenter = new MilestonesPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2395_MilestonesPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_display(inj2395_MilestonesPresenter, inj2301_MilestonesView);
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_tabSelectedEvent(inj2395_MilestonesPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Project() {
            public Class annotationType() {
              return Project.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Project()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_milestonesService(inj2395_MilestonesPresenter, inj2232_CallerProvider.provide(new Class[] { MilestonesService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_milestones_MilestonesPresenter_creator(inj2395_MilestonesPresenter, inj2397_MilestoneCreator_creationalCallback.getInstance(context));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2395_MilestonesPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var76 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ProjectInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2395_MilestonesPresenter.setProject(message.get(ProjectInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ProjectInfo []";
          }
        });
        final Subscription var77 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ProjectInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2395_MilestonesPresenter, new DestructionCallback<ProjectInfo>() {
          public void destroy(final ProjectInfo obj) {
            var76.remove();
            var77.remove();
          }
        });
        final Subscription var78 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2395_MilestonesPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var79 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2395_MilestonesPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var78.remove();
            var79.remove();
          }
        });
        return inj2395_MilestonesPresenter;
      }
    };
    final MilestonesPresenter inj2395_MilestonesPresenter = inj2396_MilestonesPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<ResourcesPresenter> inj2399_ResourcesPresenter_creationalCallback = new CreationalCallback<ResourcesPresenter>() {
      public ResourcesPresenter getInstance(final CreationalContext context) {
        Class beanType = ResourcesPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final ResourcesPresenter inj2398_ResourcesPresenter = new ResourcesPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2398_ResourcesPresenter);
        com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_tabSelectedEvent(inj2398_ResourcesPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Project() {
            public Class annotationType() {
              return Project.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Project()";
            }
        } }));
        com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_display(inj2398_ResourcesPresenter, inj2297_ResourcesView);
        com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_resourcesService(inj2398_ResourcesPresenter, inj2232_CallerProvider.provide(new Class[] { ResourcesService.class }, null));
        com_armandorv_cnpd_web_client_presenter_projects_project_resources_ResourcesPresenter_locker(inj2398_ResourcesPresenter, inj2371_MoveLockerHandler_creationalCallback.getInstance(context));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2398_ResourcesPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var80 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.ProjectInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2398_ResourcesPresenter.setProject(message.get(ProjectInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.ProjectInfo []";
          }
        });
        final Subscription var81 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.ProjectInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2398_ResourcesPresenter, new DestructionCallback<ProjectInfo>() {
          public void destroy(final ProjectInfo obj) {
            var80.remove();
            var81.remove();
          }
        });
        final Subscription var82 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2398_ResourcesPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var83 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2398_ResourcesPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var82.remove();
            var83.remove();
          }
        });
        return inj2398_ResourcesPresenter;
      }
    };
    final ResourcesPresenter inj2398_ResourcesPresenter = inj2399_ResourcesPresenter_creationalCallback.getInstance(context);
    final CreationalCallback<MessagesPresenter> inj2401_MessagesPresenter_creationalCallback = new CreationalCallback<MessagesPresenter>() {
      public MessagesPresenter getInstance(final CreationalContext context) {
        Class beanType = MessagesPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessagesPresenter inj2400_MessagesPresenter = new MessagesPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2400_MessagesPresenter);
        com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_infoService(inj2400_MessagesPresenter, inj2232_CallerProvider.provide(new Class[] { InformationService.class }, null));
        com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_display(inj2400_MessagesPresenter, inj2373_MessagesView);
        com_armandorv_cnpd_web_client_presenter_messages_MessagesPresenter_tabSelectedEvent(inj2400_MessagesPresenter, inj2230_EventProvider.provide(new Class[] { Integer.class }, new Annotation[] { new Main() {
            public Class annotationType() {
              return Main.class;
            }
            public String toString() {
              return "@com.armandorv.cnpd.web.shared.qualifiers.Main()";
            }
        } }));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj2400_MessagesPresenter.afterInitialization();
              }
            });
          }
        });
        final Subscription var84 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.MessageInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2400_MessagesPresenter.newMessage(message.get(MessageInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.MessageInfo []";
          }
        });
        final Subscription var85 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.MessageInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2400_MessagesPresenter, new DestructionCallback<MessageInfo>() {
          public void destroy(final MessageInfo obj) {
            var84.remove();
            var85.remove();
          }
        });
        final Subscription var86 = CDI.subscribe("com.armandorv.cnpd.web.shared.model.UserInfo", new AbstractCDIEventCallback() {
          {

          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj2400_MessagesPresenter.setUser(message.get(UserInfo.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.armandorv.cnpd.web.shared.model.UserInfo []";
          }
        });
        final Subscription var87 = inj2238_MessageBusProvider.get().subscribe("cdi.event:com.armandorv.cnpd.web.shared.model.UserInfo", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj2400_MessagesPresenter, new DestructionCallback<UserInfo>() {
          public void destroy(final UserInfo obj) {
            var86.remove();
            var87.remove();
          }
        });
        return inj2400_MessagesPresenter;
      }
    };
    final MessagesPresenter inj2400_MessagesPresenter = inj2401_MessagesPresenter_creationalCallback.getInstance(context);
    injContext.addBean(MembersView.class, inj2249_MembersView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.management.MembersPresenter.Display.class, inj2249_MembersView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(CallerProvider.class, inj2250_CallerProvider_creationalCallback, inj2232_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2250_CallerProvider_creationalCallback, inj2232_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MembersPresenter.class, inj2248_MembersPresenter_creationalCallback, inj2247_MembersPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2248_MembersPresenter_creationalCallback, inj2247_MembersPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectsView.class, inj2252_ProjectsView_creationalCallback, inj2251_ProjectsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.ProjectsPresenter.Display.class, inj2252_ProjectsView_creationalCallback, inj2251_ProjectsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessageBusProvider.class, inj2255_MessageBusProvider_creationalCallback, inj2238_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2255_MessageBusProvider_creationalCallback, inj2238_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectInvitationsPresenter.class, inj2254_ProjectInvitationsPresenter_creationalCallback, inj2253_ProjectInvitationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2254_ProjectInvitationsPresenter_creationalCallback, inj2253_ProjectInvitationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AppLoader.class, inj2256_AppLoader_creationalCallback, inj185_AppLoader, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IOCBeanManagerProvider.class, inj2261_IOCBeanManagerProvider_creationalCallback, inj2246_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2261_IOCBeanManagerProvider_creationalCallback, inj2246_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(PresenterRetriever.class, inj2260_PresenterRetriever_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventProvider.class, inj2262_EventProvider_creationalCallback, inj2230_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2262_EventProvider_creationalCallback, inj2230_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AppController.class, inj2259_AppController_creationalCallback, inj2258_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ValueChangeHandler.class, inj2259_AppController_creationalCallback, inj2258_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ChatView.class, inj2264_ChatView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2264_ChatView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasRowToolTip.class, inj2264_ChatView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactsView.class, inj2266_ContactsView_creationalCallback, inj2265_ContactsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.contacts.ContactsPresenter.Display.class, inj2266_ContactsView_creationalCallback, inj2265_ContactsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactProjectView.class, inj2269_ContactProjectView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.contacts.ContactProjectPresenter.Display.class, inj2269_ContactProjectView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactProjectPresenter.class, inj2268_ContactProjectPresenter_creationalCallback, inj2267_ContactProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2268_ContactProjectPresenter_creationalCallback, inj2267_ContactProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactView.class, inj2272_ContactView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.contacts.ContactPresenter.Display.class, inj2272_ContactView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasRowToolTip.class, inj2272_ContactView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactPresenter.class, inj2271_ContactPresenter_creationalCallback, inj2270_ContactPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2271_ContactPresenter_creationalCallback, inj2270_ContactPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactsListPresenter.class, inj2274_ContactsListPresenter_creationalCallback, inj2273_ContactsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2274_ContactsListPresenter_creationalCallback, inj2273_ContactsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactRequestsPresenter.class, inj2276_ContactRequestsPresenter_creationalCallback, inj2275_ContactRequestsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2276_ContactRequestsPresenter_creationalCallback, inj2275_ContactRequestsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(FindUsersPresenter.class, inj2278_FindUsersPresenter_creationalCallback, inj2277_FindUsersPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2278_FindUsersPresenter_creationalCallback, inj2277_FindUsersPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactsPresenter.class, inj2280_ContactsPresenter_creationalCallback, inj2279_ContactsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2280_ContactsPresenter_creationalCallback, inj2279_ContactsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ConversationsView.class, inj2282_ConversationsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.chat.ConversationsPresenter.Display.class, inj2282_ConversationsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ConversationsPresenter.class, inj2284_ConversationsPresenter_creationalCallback, inj2283_ConversationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2284_ConversationsPresenter_creationalCallback, inj2283_ConversationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingsView.class, inj2286_MeetingsView_creationalCallback, inj2285_MeetingsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.meetings.MeetingsPresenter.Display.class, inj2286_MeetingsView_creationalCallback, inj2285_MeetingsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingsInvitationsPresenter.class, inj2288_MeetingsInvitationsPresenter_creationalCallback, inj2287_MeetingsInvitationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2288_MeetingsInvitationsPresenter_creationalCallback, inj2287_MeetingsInvitationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionsView.class, inj2290_DiscussionsView_creationalCallback, inj2289_DiscussionsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsPresenter.Display.class, inj2290_DiscussionsView_creationalCallback, inj2289_DiscussionsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NewProjectPresenter.class, inj2292_NewProjectPresenter_creationalCallback, inj2291_NewProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2292_NewProjectPresenter_creationalCallback, inj2291_NewProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ManagementView.class, inj2294_ManagementView_creationalCallback, inj2293_ManagementView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter.Display.class, inj2294_ManagementView_creationalCallback, inj2293_ManagementView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ManagementPresenter.class, inj2296_ManagementPresenter_creationalCallback, inj2295_ManagementPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2296_ManagementPresenter_creationalCallback, inj2295_ManagementPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ResourcesView.class, inj2298_ResourcesView_creationalCallback, inj2297_ResourcesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.resources.ResourcesPresenter.Display.class, inj2298_ResourcesView_creationalCallback, inj2297_ResourcesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ReferencesView.class, inj2300_ReferencesView_creationalCallback, inj2299_ReferencesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.references.ReferencesPresenter.Display.class, inj2300_ReferencesView_creationalCallback, inj2299_ReferencesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MilestonesView.class, inj2302_MilestonesView_creationalCallback, inj2301_MilestonesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.milestones.MilestonesPresenter.Display.class, inj2302_MilestonesView_creationalCallback, inj2301_MilestonesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TasksView.class, inj2304_TasksView_creationalCallback, inj2303_TasksView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksPresenter.Display.class, inj2304_TasksView_creationalCallback, inj2303_TasksView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectView.class, inj2306_ProjectView_creationalCallback, inj2305_ProjectView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.ProjectPresenter.Display.class, inj2306_ProjectView_creationalCallback, inj2305_ProjectView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectPresenter.class, inj2308_ProjectPresenter_creationalCallback, inj2307_ProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2308_ProjectPresenter_creationalCallback, inj2307_ProjectPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectsListPresenter.class, inj2310_ProjectsListPresenter_creationalCallback, inj2309_ProjectsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2310_ProjectsListPresenter_creationalCallback, inj2309_ProjectsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(FindProjectsPresenter.class, inj2313_FindProjectsPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2313_FindProjectsPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProjectsPresenter.class, inj2312_ProjectsPresenter_creationalCallback, inj2311_ProjectsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2312_ProjectsPresenter_creationalCallback, inj2311_ProjectsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactsFormView.class, inj2315_ContactsFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.singup.ContactsFormPresenter.Display.class, inj2315_ContactsFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NewDiscussionPresenter.class, inj2317_NewDiscussionPresenter_creationalCallback, inj2316_NewDiscussionPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2317_NewDiscussionPresenter_creationalCallback, inj2316_NewDiscussionPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionDetailsView.class, inj2320_DiscussionDetailsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionsDetailsPresenter.Display.class, inj2320_DiscussionDetailsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionsDetailsPresenter.class, inj2319_DiscussionsDetailsPresenter_creationalCallback, inj2318_DiscussionsDetailsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2319_DiscussionsDetailsPresenter_creationalCallback, inj2318_DiscussionsDetailsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionVoteView.class, inj2322_DiscussionVoteView_creationalCallback, inj2321_DiscussionVoteView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionVotePresenter.Display.class, inj2322_DiscussionVoteView_creationalCallback, inj2321_DiscussionVoteView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionVotePresenter.class, inj2324_DiscussionVotePresenter_creationalCallback, inj2323_DiscussionVotePresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2324_DiscussionVotePresenter_creationalCallback, inj2323_DiscussionVotePresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionResultsView.class, inj2327_DiscussionResultsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.projects.project.discussions.DiscussionResultsPresenter.Display.class, inj2327_DiscussionResultsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionResultsPresenter.class, inj2326_DiscussionResultsPresenter_creationalCallback, inj2325_DiscussionResultsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2326_DiscussionResultsPresenter_creationalCallback, inj2325_DiscussionResultsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionsListPresenter.class, inj2329_DiscussionsListPresenter_creationalCallback, inj2328_DiscussionsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2329_DiscussionsListPresenter_creationalCallback, inj2328_DiscussionsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DiscussionsPresenter.class, inj2331_DiscussionsPresenter_creationalCallback, inj2330_DiscussionsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2331_DiscussionsPresenter_creationalCallback, inj2330_DiscussionsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InfoPanelView.class, inj2333_InfoPanelView_creationalCallback, inj2332_InfoPanelView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.info.InfoPresenter.Display.class, inj2333_InfoPanelView_creationalCallback, inj2332_InfoPanelView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ReferencesListPresenter.class, inj2335_ReferencesListPresenter_creationalCallback, inj2334_ReferencesListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2335_ReferencesListPresenter_creationalCallback, inj2334_ReferencesListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProfessionalFormView.class, inj2337_ProfessionalFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.singup.ProfessionalFormPresenter.Display.class, inj2337_ProfessionalFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ProfessionalFormPresenter.class, inj2340_ProfessionalFormPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2340_ProfessionalFormPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContactsFormPresenter.class, inj2339_ContactsFormPresenter_creationalCallback, inj2338_ContactsFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2339_ContactsFormPresenter_creationalCallback, inj2338_ContactsFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(PersonalFormView.class, inj2344_PersonalFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.singup.PersonalFormPresenter.Display.class, inj2344_PersonalFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(GoogleFormView.class, inj2346_GoogleFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.singup.GoogleFormPresenter.Display.class, inj2346_GoogleFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(GoogleFormPresenter.class, inj2348_GoogleFormPresenter_creationalCallback, inj2347_GoogleFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2348_GoogleFormPresenter_creationalCallback, inj2347_GoogleFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(PersonalFormPresenter.class, inj2351_PersonalFormPresenter_creationalCallback, inj2350_PersonalFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2351_PersonalFormPresenter_creationalCallback, inj2350_PersonalFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AcademicFormView.class, inj2353_AcademicFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.singup.AcademicFormPresenter.Display.class, inj2353_AcademicFormView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AcademicFormPresenter.class, inj2355_AcademicFormPresenter_creationalCallback, inj2354_AcademicFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2355_AcademicFormPresenter_creationalCallback, inj2354_AcademicFormPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TasksListPresenter.class, inj2357_TasksListPresenter_creationalCallback, inj2356_TasksListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2357_TasksListPresenter_creationalCallback, inj2356_TasksListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NewTaskPresenter.class, inj2359_NewTaskPresenter_creationalCallback, inj2358_NewTaskPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2359_NewTaskPresenter_creationalCallback, inj2358_NewTaskPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TasksSummaryPresenter.class, inj2361_TasksSummaryPresenter_creationalCallback, inj2360_TasksSummaryPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2361_TasksSummaryPresenter_creationalCallback, inj2360_TasksSummaryPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TasksPresenter.class, inj2363_TasksPresenter_creationalCallback, inj2362_TasksPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2363_TasksPresenter_creationalCallback, inj2362_TasksPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InstanceProvider.class, inj2364_InstanceProvider_creationalCallback, inj2234_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2364_InstanceProvider_creationalCallback, inj2234_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DisposerProvider.class, inj2365_DisposerProvider_creationalCallback, inj2228_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2365_DisposerProvider_creationalCallback, inj2228_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NotificationsView.class, inj2367_NotificationsView_creationalCallback, inj2366_NotificationsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.notifications.NotificationsPresenter.Display.class, inj2367_NotificationsView_creationalCallback, inj2366_NotificationsView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NotificationsPresenter.class, inj2369_NotificationsPresenter_creationalCallback, inj2368_NotificationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2369_NotificationsPresenter_creationalCallback, inj2368_NotificationsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MoveLockerHandler.class, inj2371_MoveLockerHandler_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DndDragStartHandler.class, inj2371_MoveLockerHandler_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RequestDispatcherProvider.class, inj2372_RequestDispatcherProvider_creationalCallback, inj2240_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2372_RequestDispatcherProvider_creationalCallback, inj2240_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessagesView.class, inj2374_MessagesView_creationalCallback, inj2373_MessagesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.messages.MessagesPresenter.Display.class, inj2374_MessagesView_creationalCallback, inj2373_MessagesView, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NewReferencePresenter.class, inj2376_NewReferencePresenter_creationalCallback, inj2375_NewReferencePresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2376_NewReferencePresenter_creationalCallback, inj2375_NewReferencePresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(NewMeetingPresenter.class, inj2378_NewMeetingPresenter_creationalCallback, inj2377_NewMeetingPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2378_NewMeetingPresenter_creationalCallback, inj2377_NewMeetingPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InitBallotProvider.class, inj2379_InitBallotProvider_creationalCallback, inj2244_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2379_InitBallotProvider_creationalCallback, inj2244_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(SenderProvider.class, inj2380_SenderProvider_creationalCallback, inj2236_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2380_SenderProvider_creationalCallback, inj2236_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ReferencesPresenter.class, inj2382_ReferencesPresenter_creationalCallback, inj2381_ReferencesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2382_ReferencesPresenter_creationalCallback, inj2381_ReferencesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RootPanelProvider.class, inj2383_RootPanelProvider_creationalCallback, inj2242_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2383_RootPanelProvider_creationalCallback, inj2242_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingDetailsView.class, inj2386_MeetingDetailsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.meetings.MeetingDetailsPresenter.Display.class, inj2386_MeetingDetailsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasRowToolTip.class, inj2386_MeetingDetailsView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingDetailsPresenter.class, inj2385_MeetingDetailsPresenter_creationalCallback, inj2384_MeetingDetailsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2385_MeetingDetailsPresenter_creationalCallback, inj2384_MeetingDetailsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingsListPresenter.class, inj2388_MeetingsListPresenter_creationalCallback, inj2387_MeetingsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2388_MeetingsListPresenter_creationalCallback, inj2387_MeetingsListPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MeetingsPresenter.class, inj2390_MeetingsPresenter_creationalCallback, inj2389_MeetingsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2390_MeetingsPresenter_creationalCallback, inj2389_MeetingsPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MainWindow.class, inj2393_MainWindow_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.armandorv.cnpd.web.client.presenter.MainPresenter.Display.class, inj2393_MainWindow_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2393_MainWindow_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InfoPresenter.class, inj2394_InfoPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2394_InfoPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MainPresenter.class, inj2392_MainPresenter_creationalCallback, inj2391_MainPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2392_MainPresenter_creationalCallback, inj2391_MainPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MilestoneCreator.class, inj2397_MilestoneCreator_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TimeBlockClickHandler.class, inj2397_MilestoneCreator_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MilestonesPresenter.class, inj2396_MilestonesPresenter_creationalCallback, inj2395_MilestonesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2396_MilestonesPresenter_creationalCallback, inj2395_MilestonesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ResourcesPresenter.class, inj2399_ResourcesPresenter_creationalCallback, inj2398_ResourcesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2399_ResourcesPresenter_creationalCallback, inj2398_ResourcesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessagesPresenter.class, inj2401_MessagesPresenter_creationalCallback, inj2400_MessagesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2401_MessagesPresenter_creationalCallback, inj2400_MessagesPresenter, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    return injContext;
  }
}