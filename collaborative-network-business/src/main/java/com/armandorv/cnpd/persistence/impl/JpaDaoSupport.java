package com.armandorv.cnpd.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class which give a entity manager instance to their sub classes and commons
 * operations
 * 
 * @author armandorv
 * 
 */
public abstract class JpaDaoSupport
{

   @PersistenceContext
   protected EntityManager em;

   /**
    * Take changes from entity manager to database.
    */
   public void flush()
   {
      em.flush();
   }

   /**
    * Clear the persistence context.
    */
   public void clear()
   {
      em.clear();
   }

}
