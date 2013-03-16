package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.persistence.IReferenceDao;

/**
 * Implementation of IReferenceDao.
 * 
 * @author armandorv
 * 
 */
public class ReferenceDao extends JpaDaoSupport implements IReferenceDao
{

   @Override
   public Reference findById(Long id)
   {
      return em.find(Reference.class, id);
   }

   @Override
   public List<Reference> findAll()
   {
      return em.createNamedQuery("Reference.findAll", Reference.class)
            .getResultList();
   }

   @Override
   public void persist(Reference entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Reference entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Reference entity)
   {
      em.refresh(entity);

   }

}
