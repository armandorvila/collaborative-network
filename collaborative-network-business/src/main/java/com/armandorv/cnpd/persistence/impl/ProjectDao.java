package com.armandorv.cnpd.persistence.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.persistence.IProjectDao;

/**
 * Implementation of IProjectDao.
 * 
 * @author armandorv
 * 
 */
public class ProjectDao extends JpaDaoSupport implements IProjectDao
{

   private static Logger log = Logger.getLogger(ProjectDao.class);

   @Override
   public Project findById(Long id)
   {
      return em.find(Project.class, id);
   }

   @Override
   public List<Project> findAll()
   {
      return em.createNamedQuery("Project.findAll", Project.class)
            .getResultList();
   }

   @Override
   public void persist(Project entity)
   {
      em.persist(entity);
   }

   @Override
   public void remove(Project entity)
   {
      em.remove(entity);
   }

   @Override
   public void refresh(Project entity)
   {
      em.refresh(entity);
   }

   @Override
   public List<Project> getProjectsByrArea(KnowledgeArea area)
   {

      TypedQuery<Project> query = em.createNamedQuery("Project.findByArea",
            Project.class);

      query.setParameter("area", area);

      List<Project> projects = query.getResultList();

      log.debug("Found " + projects.size() + " projects for area " + area);

      return projects;
   }

   @Override
   public List<Project> getProjectsByTtitle(String title)
   {

      TypedQuery<Project> query = em.createNamedQuery("Project.findByTitle",
            Project.class);

      query.setParameter("title", title);

      List<Project> projects = query.getResultList();

      log.debug("Found " + projects.size() + " projects for title " + title);

      return projects;
   }

}
