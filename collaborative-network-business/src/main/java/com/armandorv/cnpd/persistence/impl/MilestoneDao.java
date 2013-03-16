package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.persistence.IMilestoneDao;

/**
 * Implementation of IMilestoneDao.
 * 
 * @author armandorv
 * 
 */
public class MilestoneDao extends JpaDaoSupport implements IMilestoneDao
{

   @Override
   public Milestone findById(Long id)
   {
      return em.find(Milestone.class, id);
   }

   @Override
   public List<Milestone> findAll()
   {

      return em.createNamedQuery("Milestone.findAll", Milestone.class)
            .getResultList();
   }

   @Override
   public void persist(Milestone entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Milestone entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Milestone entity)
   {
      em.refresh(entity);
   }

}