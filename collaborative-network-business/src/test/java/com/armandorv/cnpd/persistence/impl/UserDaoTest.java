package com.armandorv.cnpd.persistence.impl;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.persistence.IUserDao;
import com.armandorv.cnpd.test.IPojoBuilder;
import com.armandorv.cnpd.test.TransactionalTestSupport;
import com.armandorv.cnpd.test.pojo.Persistence;

/**
 * The parent class open and close a transaction for each test.
 * 
 * @author armandorv
 * 
 */
@RunWith(Arquillian.class)
public class UserDaoTest extends TransactionalTestSupport
{
   private static Logger log = Logger.getLogger(UserDaoTest.class);

   @Persistence
   @Inject
   private IPojoBuilder builder;

   @Inject
   private IUserDao userDao;

   @Test
   public void testPersist()
   {
      List<User> users = userDao.findAll();
      Assert.assertNotNull(users);

      User user = builder.buildUser("Any username"
            + UUID.randomUUID().getLeastSignificantBits());
      Assert.assertNotNull(user);

      userDao.persist(user);
      Assert.assertNotNull(user);
      Assert.assertNotNull(user.getId());

      User found = userDao.findById(user.getId());
      Assert.assertNotNull(found);

      List<User> usersNow = userDao.findAll();
      Assert.assertEquals(users.size() + 1, usersNow.size());
   }

   @Test
   public void testFindAll()
   {
      List<User> users = userDao.findAll();
      Assert.assertNotNull(users);

      if (users.size() > 0)
      {
         log.info("Retrived" + users.size() + " users.");

         Assert.assertNotNull("No deben haber usuario nulos ",
               users.get(users.size() - 1));

         log.info(users);
      }
      else
      {
         fail(" !!! There is not users into the bbdd !!!");
      }
   }

   @Test
   public void testFindById()
   {
      List<User> users = userDao.findAll();
      Assert.assertNotNull(users);

      if (users.size() >= 1)
      {
         User user = users.get(users.size() - 1);
         log.info("Testiong get id for :" + user);

         User retrived = userDao.findById(user.getId());
         Assert.assertNotNull("El objeto recuperado es null", retrived);
         Assert.assertEquals("Ids must be the same.", user.getId(),
               retrived.getId());

         log.info("Retrived :" + retrived);
      }
      else
         fail(" !!! There is not users into the bbdd !!!");
   }

   @Test
   public void testFindByUsername()
   {
      List<User> users = userDao.findAll();
      Assert.assertNotNull(users);

      if (!users.isEmpty())
      {
         User user = users.get(0);

         User found = userDao.findById(user.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(found, user);
      }
      else
      {
         fail("ther is no users");
      }
   }

   @Test
   public void testUpdate()
   {
      List<User> all = userDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         final String name = "Changed";

         User user = all.get(0);
         Assert.assertNotNull(user);

         user.getPersonalData().setName(name);

         User found = userDao.findById(user.getId());
         Assert.assertNotNull(found);

         Assert.assertEquals(name, found.getPersonalData().getName());
      }
      else
      {
         fail("There is no users.");
      }
   }

   @Test
   public void testRemove()
   {
      List<User> all = userDao.findAll();
      Assert.assertNotNull(all);

      if (all.size() > 0)
      {
         User user = all.get(0);
         Assert.assertNotNull(user);

         userDao.remove(all.get(0));

         List<User> allNow = userDao.findAll();

         Assert.assertEquals(all.size() - 1, allNow.size());
         Assert.assertNotNull(allNow);
      }
      else
         fail(" !!! There is not users into the bbdd !!!");
   }

   @Test
   public void testRefresh()
   {
      List<User> users = userDao.findAll();
      Assert.assertNotNull(users);

      if (users.size() > 0)
      {
         User firstUser = users.get(0);
         Assert.assertNotNull(firstUser);

         String oldName = firstUser.getPersonalData().getName();
         String newName = "This name will desapear.";

         firstUser.getPersonalData().setName(newName);
         userDao.refresh(firstUser);

         Assert.assertNotSame(newName, firstUser.getPersonalData().getName());
         Assert.assertEquals(oldName, firstUser.getPersonalData().getName());

         log.info("User after refresh Test :" + firstUser);
      }
      else
         fail(" !!! There is not users into the bbdd !!!");
   }

}