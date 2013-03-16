package com.armandorv.cnpd.model;

import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * This is a user of the system domain, not of any authentication model.
 * 
 * @author armandorv
 * 
 */
@Entity
@Table(name = "users")
@NamedQueries(
{
      @NamedQuery(name = "User.findAll", query = "select u from User u"),
      @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.googleAccount.account=:account"),
      @NamedQuery(name = "User.findProjectInvited", query = "select u from User u join u.projectInvitations pi where :projectId in pi"),
      @NamedQuery(name = "User.findMeetingInvited", query = "select u from User u join u.meetingInvitations mi where :meetingId in mi")})
public class User implements Serializable
{
   private static final long serialVersionUID = 3980181225326991651L;

   @Id
   @GeneratedValue
   private Long id;

   private AccountData googleAccount;

   private PersonalData personalData;

   @ElementCollection(fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
   private List<String> degrees;

   @ElementCollection(fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
   private List<String> jobs;

   @ManyToMany
   @JoinTable(name = "contacts")
   private Set<User> contacts;

   @ManyToMany(cascade = REFRESH)
   @JoinTable(name = "contact_requests")
   private Set<User> contactRequests;

   @ManyToMany
   private Set<Project> projects = new HashSet<Project>();

   @ManyToMany(cascade = REFRESH)
   @JoinTable(name = "users_project_invitations")
   private Set<Project> projectInvitations = new HashSet<Project>();

   @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
   private Set<Notification> notifications;

   @OneToMany(mappedBy = "addressee", cascade = CascadeType.REMOVE)
   private Set<Message> messages;

   @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE)
   private Set<Message> sentMessages;

   @ManyToMany(cascade = REFRESH)
   @JoinTable(name = "users_meetings")
   private Set<Meeting> meetings;

   @ManyToMany(cascade = REFRESH)
   @JoinTable(name = "users_meeting_invitations")
   private Set<Meeting> meetingInvitations;

   /* ********************** Add and Remove methods ************************* */

   public void addContact(User contact)
   {
      this.contacts.add(contact);
   }

   public void removeContact(User contact)
   {
      this.contacts.remove(contact);
   }

   public void addContactRequest(User contactRequest)
   {
      this.contactRequests.add(contactRequest);
   }

   public void removeContactRequest(User contactRequest)
   {
      this.contactRequests.remove(contactRequest);
   }

   public void addProject(Project project)
   {
      this.projects.add(project);
   }

   public void removeProject(Project project)
   {
      this.projects.remove(project);
   }

   public void removeAllProjects()
   {
      this.projects.clear();
   }

   public void addProjectInvitation(Project projectInvitation)
   {
      this.projectInvitations.add(projectInvitation);
   }

   public void removeProjectInvitation(Project projectInvitation)
   {
      this.projectInvitations.remove(projectInvitation);
   }

   public void addMeeting(Meeting meeting)
   {
      this.meetings.add(meeting);
   }

   public void removeMeeting(Meeting meeting)
   {
      this.meetings.remove(meeting);
   }

   public void addMeetingInvitation(Meeting meeting)
   {
      this.meetingInvitations.add(meeting);
   }

   public void removeMeetingInvitations(Meeting meeting)
   {
      this.meetingInvitations.remove(meeting);
   }

   /* ********************** Getters y Setters ************************* */

   protected void setId(Long id)
   {
      this.id = id;
   }

   public Long getId()
   {
      return id;
   }

   public void setGoogleAccount(AccountData googleAccount)
   {
      this.googleAccount = googleAccount;
   }

   public AccountData getGoogleAccount()
   {
      return googleAccount;
   }

   public void setContacts(Set<User> contacts)
   {
      this.contacts = contacts;
   }

   public Set<User> getContacts()
   {
      return Collections.unmodifiableSet(contacts);
   }

   public void setProjects(Set<Project> projects)
   {
      this.projects = projects;
   }

   public Set<Project> getProjects()
   {
      return Collections.unmodifiableSet(projects);
   }

   public Set<Project> getProjectInvitations()
   {
      return Collections.unmodifiableSet(projectInvitations);
   }

   public void setProjectInvitations(Set<Project> projectInvitations)
   {
      this.projectInvitations = projectInvitations;
   }

   public Set<Message> getMessages()
   {
      return Collections.unmodifiableSet(messages);
   }

   public void setMessages(Set<Message> messages)
   {
      this.messages = messages;
   }

   public Set<Message> getSentMessages()
   {
      return Collections.unmodifiableSet(sentMessages);
   }

   public void setSentMessages(Set<Message> sentMessages)
   {
      this.sentMessages = sentMessages;
   }

   public Set<Notification> getNotifications()
   {
      return Collections.unmodifiableSet(notifications);
   }

   public void setNotifications(Set<Notification> notifications)
   {
      this.notifications = notifications;
   }

   public PersonalData getPersonalData()
   {
      return personalData;
   }

   public void setPersonalData(PersonalData personalData)
   {
      this.personalData = personalData;
   }

   public Set<User> getContactRequests()
   {
      return contactRequests;
   }

   public void setContactRequests(Set<User> contactRequests)
   {
      this.contactRequests = contactRequests;
   }

   public List<String> getDegrees()
   {
      return degrees;
   }

   public void setDegrees(List<String> degrees)
   {
      this.degrees = degrees;
   }

   public List<String> getJobs()
   {
      return jobs;
   }

   public void setJobs(List<String> jobs)
   {
      this.jobs = jobs;
   }

   public Set<Meeting> getMeetings()
   {
      return Collections.unmodifiableSet(meetings);
   }

   public void setMeetings(Set<Meeting> meetings)
   {
      this.meetings = meetings;
   }

   public Set<Meeting> getMeetingInvitations()
   {
      return Collections.unmodifiableSet(meetingInvitations);
   }

   public void setMeetingInvitations(Set<Meeting> meetingInvitations)
   {
      this.meetingInvitations = meetingInvitations;
   }

   /* ********************** Hash,Equals, toString ************************* */

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      User other = (User) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      return "User [id=" + id + ", personalData=" + personalData
            + googleAccount;
   }

}