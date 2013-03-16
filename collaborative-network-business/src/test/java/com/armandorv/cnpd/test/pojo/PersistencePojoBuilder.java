package com.armandorv.cnpd.test.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.AccountData;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.Option;
import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.model.Vote;
import com.armandorv.cnpd.test.IPojoBuilder;

/**
 * Class to create simple instance of my model
 * 
 * @author armandorv
 * 
 */
@Persistence
@Dependent
public class PersistencePojoBuilder implements IPojoBuilder
{

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getUser()
    */
   @Override
   public User buildUser(String username)
   {

      User user = new User();

      PersonalData personalData = new PersonalData();

      personalData.setName("Armando");
      personalData.setSurname1("Ramírez");
      personalData.setSurname2("Vila");
      personalData.setDateOfBirthday(new Date());
      personalData.setWebSite("www.armandorv.com");
      personalData.setCity("Guadalajara");

      user.setPersonalData(personalData);

      List<String> degrees = new ArrayList<String>();
      degrees.add("Technical Enginering at University of Oviedo.");
      degrees.add("Master on Web Enginering at University of Oviedo.");
      user.setDegrees(degrees);

      List<String> jobs = new ArrayList<String>();
      jobs.add("Software developer at University of Oviedo.");
      jobs.add("Software Developer at Red Hat - Jboss.");
      user.setJobs(jobs);

      AccountData googleAccount = new AccountData();
      googleAccount.setAccount(username);
      googleAccount.setExpired(false);
      googleAccount.setPassword("my pass te voy decir :).");
      user.setGoogleAccount(googleAccount);

      user.setProjects(new HashSet<Project>());
      user.setContacts(new HashSet<User>());

      return user;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getProject()
    */
   @Override
   public Project buildProject()
   {

      Project project = new Project();
      project.setDescription("Simple Project to Test");
      project.setDiscussions(new HashSet<Discussion>());
      project.setReferences(new HashSet<Reference>());
      project.setMembers(new HashSet<User>());
      project.setTitle("Simple Prolject");
      project.setPublisht(true);
      project.setManagerId(0L);

      return project;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getMessage()
    */
   @Override
   public Message buildMessage()
   {
      Message message = new Message();

      message.setMail(false);
      message.setDate(new Date());
      message.setContent("Message");
      return message;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getNotification()
    */
   @Override
   public Notification buildNotification()
   {
      Notification notification = new Notification();
      notification.setMessage("Message");
      notification.setNotified(false);

      return notification;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getReference()
    */
   @Override
   public Reference buildReference()
   {

      Reference reference = new Reference();

      reference.setName("Testing");
      reference.setUrl("http://www.armandorv.com");

      return reference;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.armandorv.cnpd.common.IPojoBuilder#getOption()
    */
   @Override
   public Option buildOption()
   {
      Option option = new Option();

      option.setDescription("Ujna mierda");
      option.setName("Option");
      /* Todo falta la discussion */

      return option;
   }

   @Override
   public Meeting buildMeeting()
   {

      Meeting meeting = new Meeting();

      meeting.setTitle("Test meeting");
      meeting.setCelebrated(false);
      meeting.setConclusion("No yet");
      meeting.setDescription("A meeting for testing.");
      meeting.setParticipants(new HashSet<User>());
      meeting.setInstigatorId(0L);

      return meeting;
   }

   @Override
   public Discussion buildDiscussion()
   {
      Discussion discuss = new Discussion();
      discuss.setDescription("A persistence leve testing discussion.");
      discuss.setOpen(true);
      discuss.setProject(null);
      discuss.setTitle("Testing Discuss");
      discuss.setVotes(new ArrayList<Vote>());

      List<Option> options = new ArrayList<Option>();

      Option option = new Option();
      option.setName("Option a");
      option.setDescription("Option B");

      options.add(option);

      discuss.setOptions(options);

      return discuss;
   }

   @Override
   public Milestone buildMilestone()
   {

      Milestone milestone = new Milestone();
      milestone.setName("Testing with arquillian.");
      milestone.setDate(new Date());

      return milestone;
   }

   @Override
   public KnowledgeArea buildKnowledgeArea()
   {

      KnowledgeArea area = new KnowledgeArea();

      area.setName("Sofware Development.");

      return area;
   }

   @Override
   public Resource buildResource(ResourceKind kind)
   {

      Resource resource = new Resource();

      resource.setName("description.txt");
      resource.setKind(kind);
      resource.setParent(null);
      resource.setUrl("http://127.0.0.1/dsds");
      resource.setChilds(new HashSet<Resource>());

      return resource;
   }

}
