package com.armandorv.cnpd.web.client.presenter.util;

import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Class to show messages depends on boolean states.
 * 
 * Messages are Info.display(Label,text) 
 * 
 * @author armandorv
 *
 */
public class BooleanMessenger
{
   private static BooleanMessenger messenger = new BooleanMessenger();

   /**
    * Label of Info.display when value is true.
    */
   private String trueLabel = "Success";

   /**
    * Message of Info.display when value is true.
    */
   private String trueMessage;

   /**
    * Label of Info.display when value is false.
    */
   private String falseLabel = "Fail";

   /**
    * Message of Info.display when value is false.
    */
   private String falseMessage;

   private BooleanMessenger()
   {
   }

   public static BooleanMessenger getMessenger(String trueMessage, String falseMessage)
   {
      messenger.trueMessage = trueMessage != null ? trueMessage : "";
      messenger.falseMessage = falseMessage != null ? falseMessage : "";

      return messenger;
   }

   public static BooleanMessenger getMessenger(String trueLabel, String trueMessage, String falseLabel, String falseMessage)
   {
      messenger.trueLabel = trueLabel != null ? trueLabel : "";
      messenger.trueMessage = trueMessage != null ? trueMessage : "";
      messenger.falseLabel = falseLabel != null ? falseLabel : "";
      messenger.falseMessage = falseMessage != null ? falseMessage : "";

      return messenger;
   }

   public void message(Boolean response)
   {
      if (response)
      {
         Info.display(trueLabel, trueMessage);
      }
      else
      {
         Info.display(falseLabel, falseMessage);
      }
   }
}