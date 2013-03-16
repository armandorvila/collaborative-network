package com.armandorv.cnpd.persistence.impl;

import java.util.List;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.persistence.INotificationDao;

/**
 * Implementation of INotificationDao.
 * 
 * @author armandorv
 * 
 */
public class NotificationDao extends JpaDaoSupport implements INotificationDao
{

   @Override
   public Notification findById(Long id)
   {
      return em.find(Notification.class, id);
   }

   @Override
   public List<Notification> findAll()
   {
      return em.createNamedQuery("Notification.findAll", Notification.class)
            .getResultList();
   }

   @Override
   public void persist(Notification entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Notification entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Notification entity)
   {
      em.refresh(entity);
   }

}
