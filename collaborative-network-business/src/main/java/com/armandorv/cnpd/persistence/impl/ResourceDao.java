package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.persistence.IResourceDao;

/**
 * Basic CRUD operation for Resources, built on Entity manager functionality
 * requires to be into a persistence context.
 * 
 * @author armandorv
 * 
 */
public class ResourceDao extends JpaDaoSupport implements IResourceDao
{

   @Override
   public Resource findById(Long id)
   {
      return em.find(Resource.class, id);
   }

   @Override
   public List<Resource> findAll()
   {

      return em.createNamedQuery("Resource.findAll", Resource.class)
            .getResultList();
   }

   @Override
   public void persist(Resource entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Resource entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Resource entity)
   {
      em.refresh(entity);
   }

}