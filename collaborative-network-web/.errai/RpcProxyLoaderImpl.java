package org.jboss.errai.bus.client.framework;

import com.armandorv.cnpd.web.shared.model.ContactInfo;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.GDocsResource;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.model.NotificationInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.model.ValidationResponse;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
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
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.api.base.MessageBuilder;

public class RpcProxyLoaderImpl implements RpcProxyLoader {
  public void loadProxies(final MessageBus bus) {
    class SingupServiceImpl implements SingupService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public ValidationResponse saveAndValidateGoogleAccount(String a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveAndValidateGoogleAccount:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(ValidationResponse.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveAndValidateGoogleAccount:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(ValidationResponse.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean saveAcademicInfo(List a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveAcademicInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveAcademicInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean savePersonalData(UserInfo a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("savePersonalData:com.armandorv.cnpd.web.shared.model.UserInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("savePersonalData:com.armandorv.cnpd.web.shared.model.UserInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean saveProfessionalInfo(List a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveProfessionalInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveProfessionalInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getHypotheticalContacts() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("getHypotheticalContacts:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("getHypotheticalContacts:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean saveContactsInfo(List a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveContactsInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("saveContactsInfo:java.util.List:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public ValidationResponse commitUser() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("commitUser:", qualifiers, new Object[] { }).respondTo(ValidationResponse.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.SingupService").endpoint("commitUser:", qualifiers, new Object[] { }).respondTo(ValidationResponse.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(SingupService.class, new ProxyProvider() {
      public Object getProxy() {
        return new SingupServiceImpl();
      }
    });
    class ReferencesServiceImpl implements ReferencesService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getReferences(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("getReferences:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("getReferences:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean addReference(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("addReference:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("addReference:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public long addNewReference(long a0, ReferenceInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("addNewReference:long:com.armandorv.cnpd.web.shared.model.ReferenceInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Long.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("addNewReference:long:com.armandorv.cnpd.web.shared.model.ReferenceInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Long.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return 0L;
      }

      public boolean removeReference(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("removeReference:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("removeReference:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List searchReferences(long a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("searchReferences:long:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ReferencesService").endpoint("searchReferences:long:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(ReferencesService.class, new ProxyProvider() {
      public Object getProxy() {
        return new ReferencesServiceImpl();
      }
    });
    class ContactsServiceImpl implements ContactsService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getContacts(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContacts:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContacts:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getContactsByUser(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContactsByUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContactsByUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean addAsContact(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("addAsContact:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("addAsContact:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean deniedCotnactRequest(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("deniedCotnactRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("deniedCotnactRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean removeContact(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("removeContact:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("removeContact:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public ContactInfo getContact(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContact:long:", qualifiers, new Object[] { a0 }).respondTo(ContactInfo.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContact:long:", qualifiers, new Object[] { a0 }).respondTo(ContactInfo.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List searchContacts(long a0, String a1, String a2, String a3) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("searchContacts:long:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("searchContacts:long:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getContactRequests(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContactRequests:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("getContactRequests:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean sendContactRequest(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("sendContactRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("sendContactRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean thereIsRequest(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("thereIsRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("thereIsRequest:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean isContactOf(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("isContactOf:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ContactsService").endpoint("isContactOf:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(ContactsService.class, new ProxyProvider() {
      public Object getProxy() {
        return new ContactsServiceImpl();
      }
    });
    class LoadingServiceImpl implements LoadingService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public UserInfo loadCurrentUser() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("loadCurrentUser:", qualifiers, new Object[] { }).respondTo(UserInfo.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("loadCurrentUser:", qualifiers, new Object[] { }).respondTo(UserInfo.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean connectCurrentUser(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("connectCurrentUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("connectCurrentUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getConnectedContacts(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("getConnectedContacts:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("getConnectedContacts:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public Set getConnectedUsers() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("getConnectedUsers:", qualifiers, new Object[] { }).respondTo(Set.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("getConnectedUsers:", qualifiers, new Object[] { }).respondTo(Set.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean disconnectCurrentUser(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("disconnectCurrentUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.LoadingService").endpoint("disconnectCurrentUser:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(LoadingService.class, new ProxyProvider() {
      public Object getProxy() {
        return new LoadingServiceImpl();
      }
    });
    class ProjectsServiceImpl implements ProjectsService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public ProjectInfo getProject(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProject:long:", qualifiers, new Object[] { a0 }).respondTo(ProjectInfo.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProject:long:", qualifiers, new Object[] { a0 }).respondTo(ProjectInfo.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getProjects(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProjects:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProjects:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getContactProjects(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getContactProjects:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getContactProjects:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean createProject(long a0, ProjectInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("createProject:long:com.armandorv.cnpd.web.shared.model.ProjectInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("createProject:long:com.armandorv.cnpd.web.shared.model.ProjectInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getProjectInvitations(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProjectInvitations:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getProjectInvitations:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean acceptProjectInvitation(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("acceptProjectInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("acceptProjectInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean refuseProjectInvitation(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("refuseProjectInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("refuseProjectInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getMembers(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getMembers:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getMembers:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getConnectdMembers(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getConnectdMembers:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("getConnectdMembers:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List searchProjects(String a0, IdNameGenericInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("searchProjects:java.lang.String:com.armandorv.cnpd.web.shared.model.IdNameGenericInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("searchProjects:java.lang.String:com.armandorv.cnpd.web.shared.model.IdNameGenericInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean deleteProject(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("deleteProject:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("deleteProject:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean leaveProject(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("leaveProject:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("leaveProject:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean publishProject(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("publishProject:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("publishProject:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean modifyProject(ProjectInfo a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("modifyProject:com.armandorv.cnpd.web.shared.model.ProjectInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("modifyProject:com.armandorv.cnpd.web.shared.model.ProjectInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean inviteContacts(long a0, List a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("inviteContacts:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("inviteContacts:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setProjectManager(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("setProjectManager:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("setProjectManager:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean excludeMemberOfProject(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("excludeMemberOfProject:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ProjectsService").endpoint("excludeMemberOfProject:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(ProjectsService.class, new ProxyProvider() {
      public Object getProxy() {
        return new ProjectsServiceImpl();
      }
    });
    class ResourcesServiceImpl implements ResourcesService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getResources(long a0, boolean a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getResources:long:boolean:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getResources:long:boolean:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public ResourceInfo createResource(long a0, long a1, ResourceInfo a2, boolean a3) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("createResource:long:long:com.armandorv.cnpd.web.shared.model.ResourceInfo:boolean:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(ResourceInfo.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("createResource:long:long:com.armandorv.cnpd.web.shared.model.ResourceInfo:boolean:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(ResourceInfo.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean deleteResource(long a0, long a1, boolean a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("deleteResource:long:long:boolean:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("deleteResource:long:long:boolean:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean renameResource(long a0, long a1, String a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("renameResource:long:long:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("renameResource:long:long:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public String getFileContent(long a0, ResourceInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getFileContent:long:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(String.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getFileContent:long:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(String.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean saveFileContent(String a0, String a1, String a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("saveFileContent:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("saveFileContent:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean moveResource(long a0, long a1, long a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("moveResource:long:long:long:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("moveResource:long:long:long:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean moveResourceToRoot(long a0, long a1, boolean a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("moveResourceToRoot:long:long:boolean:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("moveResourceToRoot:long:long:boolean:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getGoogleDocsResources(long a0, Kind a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getGoogleDocsResources:long:com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("getGoogleDocsResources:long:com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind:", qualifiers, new Object[] { a0, a1 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean importResource(long a0, GDocsResource a1, Kind a2, ResourceInfo a3) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("importResource:long:com.armandorv.cnpd.web.shared.model.GDocsResource:com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("importResource:long:com.armandorv.cnpd.web.shared.model.GDocsResource:com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public String prepareResourceToShow(ResourceInfo a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("prepareResourceToShow:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0 }).respondTo(String.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ResourcesService").endpoint("prepareResourceToShow:com.armandorv.cnpd.web.shared.model.ResourceInfo:", qualifiers, new Object[] { a0 }).respondTo(String.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(ResourcesService.class, new ProxyProvider() {
      public Object getProxy() {
        return new ResourcesServiceImpl();
      }
    });
    class MilestonesServiceImpl implements MilestonesService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public long addMilestone(long a0, String a1, Date a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("addMilestone:long:java.lang.String:java.util.Date:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Long.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("addMilestone:long:java.lang.String:java.util.Date:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Long.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return 0L;
      }

      public List getMilestones(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("getMilestones:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("getMilestones:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean deleteMilestone(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("deleteMilestone:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("deleteMilestone:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setLastMilestone(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("setLastMilestone:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("setLastMilestone:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean moveMilestone(long a0, Date a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("moveMilestone:long:java.util.Date:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MilestonesService").endpoint("moveMilestone:long:java.util.Date:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(MilestonesService.class, new ProxyProvider() {
      public Object getProxy() {
        return new MilestonesServiceImpl();
      }
    });
    class MeetingsServiceImpl implements MeetingsService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getMeetings(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getMeetings:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getMeetings:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List getInvitations(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getInvitations:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getInvitations:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean createMeeting(long a0, MeetingInfo a1, List a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("createMeeting:long:com.armandorv.cnpd.web.shared.model.MeetingInfo:java.util.List:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("createMeeting:long:com.armandorv.cnpd.web.shared.model.MeetingInfo:java.util.List:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean acceptInvitation(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("acceptInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("acceptInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean refuseInvitation(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("refuseInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("refuseInvitation:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean leaveMeeting(long a0, long a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("leaveMeeting:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("leaveMeeting:long:long:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean deleteMeeting(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("deleteMeeting:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("deleteMeeting:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getMeetingParticipants(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getMeetingParticipants:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("getMeetingParticipants:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean sendInvitations(long a0, List a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("sendInvitations:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("sendInvitations:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean clausureMeeting(long a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("clausureMeeting:long:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.MeetingsService").endpoint("clausureMeeting:long:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(MeetingsService.class, new ProxyProvider() {
      public Object getProxy() {
        return new MeetingsServiceImpl();
      }
    });
    class InformationServiceImpl implements InformationService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getNotifications(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("getNotifications:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("getNotifications:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean markNotified(NotificationInfo a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("markNotified:com.armandorv.cnpd.web.shared.model.NotificationInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("markNotified:com.armandorv.cnpd.web.shared.model.NotificationInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public List getMessages(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("getMessages:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("getMessages:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public List retrieveAllKnowledgeAreas() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("retrieveAllKnowledgeAreas:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("retrieveAllKnowledgeAreas:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean sendMessage(String a0, String a1, String a2, boolean a3) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("sendMessage:java.lang.String:java.lang.String:java.lang.String:boolean:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("sendMessage:java.lang.String:java.lang.String:java.lang.String:boolean:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean deleteMessage(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("deleteMessage:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("deleteMessage:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean markMessageAsRead(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("markMessageAsRead:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.InformationService").endpoint("markMessageAsRead:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(InformationService.class, new ProxyProvider() {
      public Object getProxy() {
        return new InformationServiceImpl();
      }
    });
    class ChatServiceImpl implements ChatService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public boolean connect(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("connect:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("connect:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean sendMessage(String a0, String a1, String a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("sendMessage:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("sendMessage:java.lang.String:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean disconnectt(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("disconnectt:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("disconnectt:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean addContactToChat(String a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("addContactToChat:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.ChatService").endpoint("addContactToChat:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(ChatService.class, new ProxyProvider() {
      public Object getProxy() {
        return new ChatServiceImpl();
      }
    });
    class UsersServiceImpl implements UsersService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public UserInfo getUserByUsername(String a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("getUserByUsername:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(UserInfo.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("getUserByUsername:java.lang.String:", qualifiers, new Object[] { a0 }).respondTo(UserInfo.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean setUserInformation(UserInfo a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserInformation:com.armandorv.cnpd.web.shared.model.UserInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserInformation:com.armandorv.cnpd.web.shared.model.UserInfo:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setUserDegrees(long a0, List a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserDegrees:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserDegrees:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setUserJobs(long a0, List a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserJobs:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserJobs:long:java.util.List:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean validate(String a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("validate:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("validate:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean validateAgainstGoogle(String a0, String a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("validateAgainstGoogle:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("validateAgainstGoogle:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setUserAccount(long a0, String a1, String a2) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserAccount:long:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("setUserAccount:long:java.lang.String:java.lang.String:", qualifiers, new Object[] { a0, a1, a2 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean deleteUser(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("deleteUser:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.UsersService").endpoint("deleteUser:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(UsersService.class, new ProxyProvider() {
      public Object getProxy() {
        return new UsersServiceImpl();
      }
    });
    class DiscussionsServiceImpl implements DiscussionsService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getDiscussions(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("getDiscussions:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("getDiscussions:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean addDiscussion(long a0, DiscussionInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("addDiscussion:long:com.armandorv.cnpd.web.shared.model.DiscussionInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("addDiscussion:long:com.armandorv.cnpd.web.shared.model.DiscussionInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean vote(long a0, VoteInfo a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("vote:long:com.armandorv.cnpd.web.shared.model.VoteInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("vote:long:com.armandorv.cnpd.web.shared.model.VoteInfo:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean deleteDiscussion(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("deleteDiscussion:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("deleteDiscussion:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean closeDiscussion(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("closeDiscussion:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.DiscussionsService").endpoint("closeDiscussion:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(DiscussionsService.class, new ProxyProvider() {
      public Object getProxy() {
        return new DiscussionsServiceImpl();
      }
    });
    class TasksServiceImpl implements TasksService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public List getTasks(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("getTasks:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("getTasks:long:", qualifiers, new Object[] { a0 }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }

      public boolean deleteTask(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("deleteTask:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("deleteTask:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean imputeHoursToTask(long a0, int a1) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("imputeHoursToTask:long:int:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("imputeHoursToTask:long:int:", qualifiers, new Object[] { a0, a1 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean setTaskAsCompleted(long a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("setTaskAsCompleted:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("setTaskAsCompleted:long:", qualifiers, new Object[] { a0 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }

      public boolean createTask(long a0, String a1, int a2, Date a3) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("createTask:long:java.lang.String:int:java.util.Date:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.armandorv.cnpd.web.shared.remote.TasksService").endpoint("createTask:long:java.lang.String:int:java.util.Date:", qualifiers, new Object[] { a0, a1, a2, a3 }).respondTo(Boolean.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(TasksService.class, new ProxyProvider() {
      public Object getProxy() {
        return new TasksServiceImpl();
      }
    });
  }
}