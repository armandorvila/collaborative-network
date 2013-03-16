package com.armandorv.cnpd.persistence;

import java.util.List;

/**
 * Generic interface for all entities
 * 
 * @author armandorv
 * 
 */
public interface IDao<T>
{

   /**
    * Obtain entities by their primary key
    */
   T findById(Long id);

   /**
    * Get all entities
    */
   List<T> findAll();

   /**
    * Persist a new entity
    */
   void persist(T entity);

   /**
    * Remove a entity
    */
   void remove(T entity);

   /**
    * Reload the value of an entity from the data base overwriting the changes
    * over the entity.
    */
   public void refresh(T entity);

   /**
    * Take entity manager cache to database.
    */
   public void flush();

   /**
    * Clear the persistence context.
    */
   public void clear();

}
