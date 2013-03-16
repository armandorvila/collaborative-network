package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.persistence.IKnowledgeAreaDao;

/**
 * Implementation of IKnowledegeAraDao.
 * 
 * @author armandorv
 * 
 */
public class KnowledgeAreaDao extends JpaDaoSupport implements
      IKnowledgeAreaDao
{

   @Override
   public KnowledgeArea findById(Long id)
   {
      return em.find(KnowledgeArea.class, id);
   }

   @Override
   public List<KnowledgeArea> findAll()
   {
      return em
            .createNamedQuery("KnowledgeArea.findAll", KnowledgeArea.class)
            .getResultList();
   }

   @Override
   public void persist(KnowledgeArea entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(KnowledgeArea entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(KnowledgeArea entity)
   {
      em.refresh(entity);
   }

}