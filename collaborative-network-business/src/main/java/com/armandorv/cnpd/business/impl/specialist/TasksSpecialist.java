package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.persistence.ITaskDao;

@HandleBusinessException
public class TasksSpecialist
{
   @Inject
   private IProjectDao projectDao;

   @Inject
   private ITaskDao taskDao;

   private FindByIdExecutor<Project> findProjectById;

   private FindByIdExecutor<Task> findTaskById;

   @PostConstruct
   public void postConstruct()
   {
      findProjectById = new FindByIdExecutor<Project>(projectDao);
      findTaskById = new FindByIdExecutor<Task>(taskDao);
   }

   public void deleteTask(long taskId)
   {
      Task task = findTaskById.findById(taskId);
      taskDao.remove(task);
   }

   public void imputeHoursToTask(long taskId, int hours)
   {
      Task task = findTaskById.findById(taskId);
      int workedHours = task.getWorkedHours();
      workedHours += hours;
      task.setWorkedHours(workedHours);
   }

   public void setTaskAsDone(long taskId)
   {
      Task task = findTaskById.findById(taskId);
      task.setCompleted(true);
   }

   public Task addNewTask(long projectId, Task task)
   {
      Project project = findProjectById.findById(projectId);

      taskDao.persist(task);
      task.setProject(project);

      return task;
   }

   public Set<Task> getTasks(long projectId)
   {
      Project project = findProjectById.findById(projectId);
      project.getTasks().size();
      return project.getTasks();
   }

}