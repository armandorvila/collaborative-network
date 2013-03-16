package com.armandorv.cnpd.test;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.model.Message;
import com.armandorv.cnpd.model.Milestone;
import com.armandorv.cnpd.model.Notification;
import com.armandorv.cnpd.model.Option;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.model.User;

/**
 * Interface that define a common contract to get testing pojos.
 * 
 * @author armandorv
 * 
 */
public interface IPojoBuilder
{

   User buildUser(String username);

   Project buildProject();

   Message buildMessage();

   Notification buildNotification();

   Reference buildReference();

   Option buildOption();

   Meeting buildMeeting();

   Discussion buildDiscussion();

   Milestone buildMilestone();

   KnowledgeArea buildKnowledgeArea();

   Resource buildResource(ResourceKind folder);

}