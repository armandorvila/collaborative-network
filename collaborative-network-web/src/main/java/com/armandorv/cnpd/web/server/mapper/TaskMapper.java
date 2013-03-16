package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Tasks;

@Dependent
@Tasks
public class TaskMapper implements Mapper<Task, TaskInfo>
{
   @Override
   public TaskInfo map(Task object)
   {
      TaskInfo taskInfo = new TaskInfo();

      taskInfo.setId(object.getId());
      taskInfo.setName(object.getName());

      taskInfo.setStart(object.getStart());
      taskInfo.setHours(object.getHours());
      taskInfo.setWorkedHours(object.getWorkedHours());

      taskInfo.setCompleted(object.isCompleted());

      return taskInfo;
   }

}