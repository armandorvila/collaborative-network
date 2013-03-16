package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.persistence.IDiscussionDao;

/**
 * Implementation of DiscussionDao.
 * 
 * @author armandorv
 * 
 */
public class DiscussionDao extends JpaDaoSupport implements IDiscussionDao
{

   private static Logger log = Logger.getLogger(DiscussionDao.class);

   @Override
   public Discussion findById(Long id)
   {
      return em.find(Discussion.class, id);
   }

   @Override
   public List<Discussion> findAll()
   {
      return em.createNamedQuery("Discussion.findAll", Discussion.class)
            .getResultList();
   }

   @Override
   public void persist(Discussion entity)
   {
      em.persist(entity);

   }

   @Override
   public void remove(Discussion entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Discussion entity)
   {
      em.refresh(entity);
   }

   @Override
   public List<Discussion> getDiscussionsByProject(Project project)
   {

      TypedQuery<Discussion> query = em.createNamedQuery(
            "Discussion.findByProject", Discussion.class);

      query.setParameter("project", project);

      List<Discussion> discussions = query.getResultList();

      log.debug("Found " + discussions.size() + " discussions for project "
            + project.getTitle());

      return discussions;
   }

}
