package com.armandorv.cnpd.persistence;

import java.util.List;

import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.User;

/**
 * Extend IDao functionality for message entity and add other functionality like
 * getMessagesBySnder(User sender)
 * 
 * @author armandorv
 * 
 */
public interface IMessageDao extends IDao<Message>
{

   public List<Message> findAllBySender(User sender);

}
