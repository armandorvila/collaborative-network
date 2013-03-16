package com.armandorv.cnpd.web.server.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;

import com.armandorv.cnpd.business.IProjectsManager;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.Option;
import com.armandorv.cnpd.model.Vote;
import com.armandorv.cnpd.web.server.interceptor.HandleBooleanException;
import com.armandorv.cnpd.web.server.interceptor.HandleServersideException;
import com.armandorv.cnpd.web.server.mapper.Mapper;
import com.armandorv.cnpd.web.shared.model.DiscussionInfo;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.VoteInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Discussions;
import com.armandorv.cnpd.web.shared.remote.DiscussionsService;

@Service
@ApplicationScoped
public class DiscussionsServiceImpl implements DiscussionsService
{
   @Inject
   private IProjectsManager projectsManager;

   @Inject
   @Discussions
   private Mapper<Discussion, DiscussionInfo> discussionMapper;

   @Override
   @HandleServersideException
   public List<DiscussionInfo> getDiscussions(long projectId)
   {
      List<DiscussionInfo> discussions = new ArrayList<DiscussionInfo>();

      Set<Discussion> businessDiscussions = projectsManager.getDiscussions(projectId);

      for (Discussion discussion : businessDiscussions)
         discussions.add(discussionMapper.map(discussion));

      return discussions;
   }

   @Override
   @HandleBooleanException
   public boolean addDiscussion(long projectId, DiscussionInfo discussion)
   {
      Discussion businessDiscussion = new Discussion();

      businessDiscussion.setTitle(discussion.getTitle());
      businessDiscussion.setDescription(discussion.getDescription());
      businessDiscussion.setOpen(true);

      List<Option> options = new ArrayList<Option>();

      for (IdNameGenericInfo option : discussion.getOptions())
      {
         Option opt = new Option();
         opt.setName(option.getName());
         options.add(opt);
      }

      businessDiscussion.setOptions(options);

      projectsManager.addDiscussion(projectId, businessDiscussion);

      return true;
   }

   @Override
   @HandleBooleanException
   public boolean vote(long discussionId, VoteInfo vote)
   {
      Vote businessVote = new Vote();
      businessVote.setVoterId(vote.getUserId());
      businessVote.setVoterName(vote.getVoterName());
      businessVote.setOption(vote.getOption());
      businessVote.setArgument(vote.getArgument());
      
      projectsManager.addVoteToDiscussion(discussionId, businessVote);
      
      return true;
   }

   @Override
   @HandleBooleanException
   public boolean deleteDiscussion(long discussionId)
   {
      projectsManager.deleteDiscussion(discussionId);
      return true;
   }

   @Override
   public boolean closeDiscussion(long discussionId)
   {
      projectsManager.closeDiscussion(discussionId);
      return true;
   }

}