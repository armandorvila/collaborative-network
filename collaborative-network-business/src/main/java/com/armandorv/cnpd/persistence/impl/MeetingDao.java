package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.persistence.IMeetingDao;

/**
 * Implementation of IMeetingDao.
 * 
 * @author armandorv
 * 
 */
public class MeetingDao extends JpaDaoSupport implements IMeetingDao
{

   private static Logger log = Logger.getLogger(MeetingDao.class);

   @Override
   public Meeting findById(Long id)
   {
      return em.find(Meeting.class, id);
   }

   @Override
   public List<Meeting> findAll()
   {
      return em.createNamedQuery("Meeting.findAll", Meeting.class)
            .getResultList();
   }

   @Override
   public void persist(Meeting entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Meeting entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Meeting entity)
   {
      em.refresh(entity);
   }

   @Override
   public List<Meeting> findByTitle(String title)
   {

      TypedQuery<Meeting> query = em.createNamedQuery("Meeting.findByTitle",
            Meeting.class);

      query.setParameter("title", title);

      List<Meeting> meetings = query.getResultList();

      log.debug("Found " + meetings.size() + " for title " + title);

      return meetings;
   }

}
