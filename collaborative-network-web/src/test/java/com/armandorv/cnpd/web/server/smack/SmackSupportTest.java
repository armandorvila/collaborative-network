package com.armandorv.cnpd.web.server.smack;

import org.jivesoftware.smack.Chat;
import org.junit.Assert;
import org.junit.Test;

import com.armandorv.cnpd.web.shared.exception.UnconnectedUserException;

public class SmackSupportTest
{
   private final String username = "julio.rodrigez.sanchiz@gmail.com";

   private final String password = "julio.rodrigez.sanchiz";

   private class SmackConcreteClass extends SmackSupport
   {
   }

   private SmackConcreteClass smackChild = new SmackConcreteClass();

   @Test
   public void testConnect()
   {
      smackChild.connect(username, password, new ChatListener(new IncomingMessageListener(null, "") , ""), null);
      
   }

   @Test
   public void testCreateChat()
   {
      smackChild.connect(username, password, new ChatListener(new IncomingMessageListener(null , "") , ""), null);

      Chat chat = smackChild.createNewChat(username, "armmandoo2@gmail.com");
      Assert.assertNotNull(chat);
      Assert.assertNotNull(chat.getParticipant());
      Assert.assertEquals("armmandoo2@gmail.com", chat.getParticipant());
   }

   @Test(expected = UnconnectedUserException.class)
   public void testDisconnect()
   {
      smackChild.connect(username, password, new ChatListener(new IncomingMessageListener(null , "") , ""), null);
      smackChild.disconnect(username);
      smackChild.createNewChat(username, "armmandoo2@gmail.com");
   }

}