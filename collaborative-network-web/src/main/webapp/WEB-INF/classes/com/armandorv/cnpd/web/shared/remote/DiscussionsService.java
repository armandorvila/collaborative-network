package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.VoteInfo;

@Remote
public interface DiscussionsService
{
   List<DiscussionInfo> getDiscussions(long projectId);

   boolean addDiscussion(long projectId, DiscussionInfo discussion);

   boolean vote(long idDiscussion, VoteInfo vote);

   boolean deleteDiscussion(long discussionId);

   boolean closeDiscussion(long discussionId);
}
