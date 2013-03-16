package com.armandorv.cnpd.business.impl.util;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.armandorv.cnpd.business.exception.NonExisistentIdException;
import com.armandorv.cnpd.persistence.IDao;

/**
 * Check if Find by id executor has the desired behavior.
 * @author armandorv
 *
 */
public class FindByExecutorTest
{

   /**
    * IDao implementation which findById return null if id == 0 and else new Object().
    */
   private class MockDao implements IDao<Object>
   {

      public Object findById(Long id)
      {
         return id != 0 ? new Object() : null;
      }

      public List<Object> findAll()
      {
         return null;
      }

      public void persist(Object entity)
      {
      }

      public void remove(Object entity)
      {
      }

      public void refresh(Object entity)
      {
      }

      public void flush()
      {
      }

      public void clear()
      {
      }
   }

   /**
    * Instance for test.
    */
   private FindByIdExecutor<Object> findById = new FindByIdExecutor<Object>(new MockDao());

   @Test
   public void testFindExixtentId()
   {

      try
      {

         Object found = findById.findById(10L);
         Assert.assertNotNull(found);
      }

      catch (NonExisistentIdException e)
      {

         Assert.assertTrue(e.getMessage(), false);
      }

   }

   @Test
   public void testFindNonExixtentId()
   {

      try
      {

         findById.findById(0L);
         Assert.assertTrue(false);
      }

      catch (NonExisistentIdException e)
      {
         Assert.assertTrue(true);
      }

   }

}
