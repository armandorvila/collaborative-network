package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.qualifiers.References;
import com.armandorv.cnpd.web.shared.remote.ReferencesService;

@Service
@ApplicationScoped
public class ReferencesServiceImpl implements ReferencesService
{
   private static Logger log = Logger.getLogger(ReferencesServiceImpl.class);

   @Inject
   @References
   private Mapper<Reference, ReferenceInfo> referenceMapper;

   @Inject
   private IProjectsManager projectsManager;

   @Override
   @HandleServersideException
   public List<ReferenceInfo> getReferences(long projectId)
   {
      List<ReferenceInfo> references = new ArrayList<ReferenceInfo>();
      Set<Reference> businessReferences = projectsManager.getReferences(projectId);

      for (Reference reference : businessReferences)
         references.add(referenceMapper.map(reference));

      return references;
   }

   @Override
   @HandleServersideException
   public long addNewReference(long projectId, ReferenceInfo reference)
   {
      Reference businessReference = new Reference();
      businessReference.setName(reference.getName());
      businessReference.setUrl(reference.getUrl());

      businessReference = projectsManager.addNewReference(projectId, businessReference);

      return businessReference.getId();
   }

   @Override
   @HandleBooleanException
   public boolean addReference(long projectId, long referenceId)
   {
      projectsManager.addReference(projectId, referenceId);
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean removeReference(long projectId, long referenceId)
   {
      projectsManager.removeReference(projectId, referenceId);
      return true;
   }

   @Override
   @HandleServersideException
   public List<ReferenceInfo> searchReferences(long projectId, String keyWords)
   {
      List<ReferenceInfo> references = new ArrayList<ReferenceInfo>();

      List<Reference> businessReferences = projectsManager.getAllReferences();
      Set<Reference> projectReferences = projectsManager.getReferences(projectId);

      if ("".equals(keyWords))
         return references;

      keyWords = keyWords.toLowerCase();

      String[] keys = keyWords.split(" ");

      log.info("Key words are :" + keys);

      if (keys.length == 0)
         return references;

      for (Reference reference : businessReferences)
      {
         if (!projectReferences.contains(reference))
         {
            if (keyWords.contains(reference.getName().toLowerCase())
                  || keyWords.contains(reference.getUrl().toLowerCase()))
            {
               references.add(referenceMapper.map(reference));
            }
            else
            {
               for (String key : keys)
               {
                  if (reference.getName().toLowerCase().contains(key))
                  {
                     references.add(referenceMapper.map(reference));
                     break;
                  }
                  else if (reference.getUrl().toLowerCase().contains(keyWords))
                  {
                     references.add(referenceMapper.map(reference));
                     break;
                  }
               }
            }
         }
      }
      return references;
   }

}