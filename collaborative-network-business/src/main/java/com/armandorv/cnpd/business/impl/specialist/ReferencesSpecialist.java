package com.armandorv.cnpd.business.impl.specialist;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.exception.InvalidReferenceException;
import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.persistence.IProjectDao;
import com.armandorv.cnpd.persistence.IReferenceDao;

/**
 * Specialist on deal with references concerns, is used by projects manager, it
 * must be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class ReferencesSpecialist
{
   @Inject
   private IReferenceDao referenceDao;

   @Inject
   private IProjectDao projectDao;

   private FindByIdExecutor<Project> findProjectById;

   private FindByIdExecutor<Reference> findReferenceById;

   @PostConstruct
   public void setUp()
   {
      findProjectById = new FindByIdExecutor<Project>(projectDao);
      findReferenceById = new FindByIdExecutor<Reference>(referenceDao);
   }

   public Set<Reference> getReferences(long projectId)
   {
      Project project = findProjectById.findById(projectId);

      Set<Reference> references = project.getReferences();
      references.size();

      return references;
   }
   
   public Reference addReference(long projectId, long referenceId)
   {
      Project project = findProjectById.findById(projectId);
      Reference reference = findReferenceById.findById(referenceId);
      
      if(project.getReferences().contains(reference))
         throw new InvalidReferenceException("The given project already cotnains the given reference.");
      
      project.addReference(reference);

      return reference;
   }

   public Reference addNewReference(long projectId, Reference reference)
   {
      Project project = findProjectById.findById(projectId);

      referenceDao.persist(reference);
      project.addReference(reference);

      return reference;
   }

   public void removeReference(long projectId, long referenceId)
   {
      Reference reference = findReferenceById.findById(referenceId);
      Project project = findProjectById.findById(projectId);
      
      if(!project.getReferences().contains(reference))
         throw new InvalidReferenceException("The given project hasn't the given reference.");
      
      project.removeReference(reference);
   }

   public List<Reference> getAllReferences()
   {
      return referenceDao.findAll();
   }

}