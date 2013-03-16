package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import com.armandorv.cnpd.model.Task;
import com.armandorv.cnpd.persistence.ITaskDao;

public class TaskDao extends JpaDaoSupport implements ITaskDao
{
   @Override
   public Task findById(Long id)
   {
      return em.find(Task.class, id);
   }

   @Override
   public List<Task> findAll()
   {
      return em.createNamedQuery("Task.findAll", Task.class).getResultList();
   }

   @Override
   public void persist(Task entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Task entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Task entity)
   {
      em.refresh(entity);
   }
}