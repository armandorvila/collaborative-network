package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Tasks;
import com.armandorv.cnpd.web.shared.remote.TasksService;

@Service
@ApplicationScoped
public class TaskServiceImpl implements TasksService
{
   @Inject
   private IProjectsManager projectsManager;

   @Inject
   @Tasks
   private Mapper<Task, TaskInfo> taskMapper;

   @Override
   @HandleServersideException
   public List<TaskInfo> getTasks(long projectId)
   {
      List<TaskInfo> tasks = new ArrayList<TaskInfo>();

      Set<Task> businessTasks = projectsManager.getTasks(projectId);

      for (Task task : businessTasks)
      {
         tasks.add(taskMapper.map(task));
      }

      return tasks;
   }

   @Override
   @HandleBooleanException
   public boolean deleteTask(long taskId)
   {
      projectsManager.deleteTask(taskId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean imputeHoursToTask(long taskId, int hours)
   {
      projectsManager.imputeHoursToTask(taskId, hours);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean setTaskAsCompleted(long taskId)
   {
      projectsManager.setTaskAsDone(taskId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean createTask(long projectId, String name, int duration, Date beguining)
   {
      Task task = new Task();
     
      task.setCompleted(false);
      task.setHours(duration);
      task.setName(name);
      task.setStart(beguining);
      task.setWorkedHours(0);
      
      projectsManager.addNewTask(projectId, task);
      
      return true;
   }

}