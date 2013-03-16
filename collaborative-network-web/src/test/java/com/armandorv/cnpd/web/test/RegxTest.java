package com.armandorv.cnpd.web.test;

import org.junit.Assert;
import org.junit.Test;

public class RegxTest
{

   @Test
   public void testRegx()
   {
      Assert.assertTrue(isInteger("99"));
      Assert.assertTrue(isInteger("995554"));
      Assert.assertTrue(isInteger("0122380"));
      Assert.assertTrue(isInteger("333"));
      Assert.assertTrue(isInteger("1"));
      Assert.assertTrue(isInteger("22222222"));
      Assert.assertTrue(isInteger("3333333"));
      Assert.assertTrue(isInteger("10"));
      
      Assert.assertFalse(isInteger("eeee"));
      Assert.assertFalse(isInteger("eeee990"));
      Assert.assertFalse(isInteger("900eeee"));
      Assert.assertFalse(isInteger("9.9"));
      Assert.assertFalse(isInteger("-99"));
   }
   
   private boolean isInteger(String value){
      return value.matches("[0-9]+");
   }

}
