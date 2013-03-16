package com.armandorv.cnpd.web.shared.remote;

import java.util.Date;
import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.TaskInfo;

@Remote
public interface TasksService
{
   /**
    * Retrieve all tasks of a project.
    * 
    * @param projectId identifier of the project.
    * 
    * @return a list of tasks. 
    */
   List<TaskInfo> getTasks(long projectId);

   /**
    * Delete a task from the system.
    * 
    * @param taskId identifier of the task.
    */
   boolean deleteTask(long taskId);

   /**
    * Add worked hours to a task.
    * 
    * @param taskId identifier of the task.
    * 
    * @param hours amount of hours to add.
    */
   boolean imputeHoursToTask(long taskId, int hours);

   /**
    * Set a task as completed.
    * 
    * @param taskId identifier of the task.
    */
   boolean setTaskAsCompleted(long taskId);
   
   /**
    * Create a new task in a project.
    */
   boolean createTask(long projectId , String name , int duration , Date beguining);
}
