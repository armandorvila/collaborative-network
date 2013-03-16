package com.armandorv.cnpd.business.impl.specialist;

import java.util.List;

import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.persistence.IKnowledgeAreaDao;

/**
 * Specialist on deal with knowledge areas, is used by projects manager and must
 * be within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class KnowledgeAreasSpecialist
{

   @Inject
   private IKnowledgeAreaDao knowledgeAreaDao;

   public KnowledgeArea createKnowledgeArea(KnowledgeArea knowledgeArea)
   {

      knowledgeAreaDao.persist(knowledgeArea);

      return knowledgeArea;

   }

   public List<KnowledgeArea> getAllKnowledgeAreas()
   {

      List<KnowledgeArea> all = knowledgeAreaDao.findAll();
      return all;

   }

   public void deleteKnowledgeArea(long knowledgeAreaId)
   {
      KnowledgeArea area = new FindByIdExecutor<KnowledgeArea>(knowledgeAreaDao)
            .findById(knowledgeAreaId);
      knowledgeAreaDao.remove(area);
   }

}