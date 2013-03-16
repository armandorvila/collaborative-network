package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IMessageDao;

/**
 * Implementation of IMessageDao.
 * 
 * @author armandorv
 * 
 */
public class MessageDao extends JpaDaoSupport implements IMessageDao
{

   private static Logger log = Logger.getLogger(MessageDao.class);

   @Override
   public Message findById(Long id)
   {
      return em.find(Message.class, id);
   }

   @Override
   public List<Message> findAll()
   {
      return em.createNamedQuery("Message.findAll", Message.class)
            .getResultList();
   }

   @Override
   public void persist(Message entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Message entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Message entity)
   {
      em.refresh(entity);
   }

   @Override
   public List<Message> findAllBySender(User sender)
   {

      TypedQuery<Message> query = em.createNamedQuery("Message.findBySender",
            Message.class);

      query.setParameter("sender", sender);

      List<Message> messages = query.getResultList();

      log.debug("Found " + messages.size() + " messages.");

      return messages;
   }

}
