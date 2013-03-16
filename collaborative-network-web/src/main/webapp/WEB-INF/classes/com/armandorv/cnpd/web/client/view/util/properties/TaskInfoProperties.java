package com.armandorv.cnpd.web.client.view.util.properties;

import java.util.Date;

import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TaskInfoProperties extends PropertyAccess<TaskInfo>
{
   ModelKeyProvider<TaskInfo> id();

   ValueProvider<TaskInfo, String> name();

   ValueProvider<TaskInfo, Integer> hours();

   ValueProvider<TaskInfo, Integer> workedHours();
   
   ValueProvider<TaskInfo, Float> percent();

   ValueProvider<TaskInfo, Date> start();

   ValueProvider<TaskInfo, Boolean> completed();
}
