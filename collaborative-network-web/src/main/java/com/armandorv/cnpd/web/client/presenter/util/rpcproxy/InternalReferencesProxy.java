package com.armandorv.cnpd.web.client.presenter.util.rpcproxy;

import org.jboss.errai.ioc.client.api.Caller;

import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.remote.ProjectsService;
import com.google.gwt.core.client.Callback;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * Custom implementation of DataProxy to get references of a project from
 * server.
 * 
 * @author armandorv
 * 
 */
public class InternalReferencesProxy implements DataProxy<PagingLoadConfig, PagingLoadResult<ProjectInfo>>
{

   //private Caller<ProjectsService> service;
   //private ProjectInfo project;

   public InternalReferencesProxy(ProjectInfo project, Caller<ProjectsService> service)
   {

      //this.project = project;
      //this.service = service;

   }

   @Override
   public void load(PagingLoadConfig loadConfig, final Callback<PagingLoadResult<ProjectInfo>, Throwable> callback)
   {

   }

}
