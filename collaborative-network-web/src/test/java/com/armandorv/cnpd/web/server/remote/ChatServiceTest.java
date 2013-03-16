package com.armandorv.cnpd.web.server.remote;

import java.util.ResourceBundle;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.armandorv.cnpd.web.shared.remote.ChatService;
import com.armandorv.cnpd.web.test.ArquillianSupport;

@RunWith(Arquillian.class)
public class ChatServiceTest extends ArquillianSupport
{
   private final String username = ResourceBundle.getBundle("test").getString("test.username");

   private final String to = "armmandoo2@gmail.com";

   @Inject
   private ChatService chatService;

   @Test
   public void testConnect()
   {
      Assert.assertTrue(chatService.connect(username));
   }

   @Test
   public void testSendMessage()
   {
      Assert.assertTrue(chatService.connect(username));
      Assert.assertTrue(chatService.sendMessage(username, to, "testing."));
   }

   @Test
   public void testDisconnectt()
   {
      Assert.assertTrue(chatService.disconnectt(username));
   }

}