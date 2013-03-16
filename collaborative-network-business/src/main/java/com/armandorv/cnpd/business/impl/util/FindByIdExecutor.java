package com.armandorv.cnpd.business.impl.util;

import com.armandorv.cnpd.business.exception.NonExisistentIdException;
import com.armandorv.cnpd.persistence.IDao;

/**
 * Class to find entities by id safely.
 * 
 * @author armandorv
 * 
 * @param <T>
 *            type of entity to find.
 */
public class FindByIdExecutor<T>
{

   private IDao<T> dao;

   public FindByIdExecutor(IDao<T> dao)
   {
      this.dao = dao;
   }

   /**
    * Execute dao.findById call and check if null.
    * 
    * @param dao
    *            concrete DAO implementation.
    * @param id
    *            identifier of entity to find.
    * @return the entity with given identifier.
    * 
    * @throws NonExisistentIdException
    *             if there is no entities instance with given identifier.
    */
   public T findById(long id)
   {

      T entity = dao.findById(id);

      if (entity == null)
         throw new NonExisistentIdException(
               "There is no entity instance for given identifier :" + id);

      return entity;
   }
}
