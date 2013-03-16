package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.Option;
import com.armandorv.cnpd.model.Vote;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Discussions;

/**
 * Map a discussion of business model to a discussion of presentation model.
 * Options required to be not null, and votes are not mapped.
 * 
 * @author armandorv
 * 
 */
@Discussions
@Dependent
public class DiscussionMapper implements Mapper<Discussion, DiscussionInfo>
{
   @Override
   public DiscussionInfo map(Discussion object)
   {
      if (object.getId() == null)
         throw new MappingErrorException("Message must has a identifier.");

      DiscussionInfo info = new DiscussionInfo();

      int i = 0;
      for (Option option : object.getOptions())
      {
         info.getOptions().add(new IdNameGenericInfo(new Long(i), option.getName()));
         i++;
      }

      for (Vote vote : object.getVotes())
         info.getVotes().add(mapVote(vote, object));

      info.setId(object.getId());
      info.setOpen(object.getOpen());
      info.setTitle(object.getTitle() != null ? object.getTitle() : "");
      info.setDescription(object.getDescription() != null ? object.getDescription() : "");

      return info;
   }

   private VoteInfo mapVote(Vote vote, Discussion object)
   {
      VoteInfo info = new VoteInfo();

      info.setUserId(vote.getVoterId());
      info.setArgument(vote.getArgument() != null ? vote.getArgument() : "");
      info.setVoterName(vote.getVoterName() != null ? vote.getVoterName() : "");
      info.setOption(vote.getOption() != null ? vote.getOption() : "");

      return info;
   }

}