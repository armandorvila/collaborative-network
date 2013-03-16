package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.MessageInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Messages;

@Messages
@Dependent
public class MessageMapper implements Mapper<Message, MessageInfo>
{
   @Override
   public MessageInfo map(Message object)
   {

      if (object.getId() == null)
         throw new MappingErrorException("Message must has a identifier.");

      MessageInfo info = new MessageInfo();

      info.setContent(object.getContent());
      info.setDate(object.getDate());
      info.setId(object.getId());
      info.setRead(object.isRead());
      info.setSender(object.getSender().getGoogleAccount().getAccount());
      info.setTo(object.getAddressee().getGoogleAccount().getAccount());

      return info;
   }

}