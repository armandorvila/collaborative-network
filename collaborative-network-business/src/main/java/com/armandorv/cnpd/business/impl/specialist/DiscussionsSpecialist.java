package com.armandorv.cnpd.business.impl.specialist;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.armandorv.cnpd.business.impl.interceptor.HandleBusinessException;
import com.armandorv.cnpd.business.impl.util.FindByIdExecutor;
import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.model.Vote;
import com.armandorv.cnpd.persistence.IDiscussionDao;
import com.armandorv.cnpd.persistence.IProjectDao;

/**
 * Specialist on deal with discussions, is used by projects manager and must be
 * within persistent context and be injected with @Inject.
 * 
 * @author armandorv
 * 
 */
@HandleBusinessException
public class DiscussionsSpecialist
{
   @Inject
   private IDiscussionDao discussionDao;

   @Inject
   private IProjectDao projectDao;

   private FindByIdExecutor<Project> findProjectById;

   private FindByIdExecutor<Discussion> findDiscussionById;

   @PostConstruct
   public void setUp()
   {
      findProjectById = new FindByIdExecutor<Project>(projectDao);
      findDiscussionById = new FindByIdExecutor<Discussion>(discussionDao);
   }

   public Set<Discussion> getDiscussions(long projectId)
   {
      Project project = findProjectById.findById(projectId);

      Set<Discussion> discussions = project.getDiscussions();
      discussions.size();

      return discussions;
   }

   public Discussion addDiscussion(long projectId, Discussion discussion)
   {
      Project project = findProjectById.findById(projectId);

      discussionDao.persist(discussion);
      discussion.setProject(project);

      return discussion;
   }

   public void removeDiscussion(long discussionId)
   {
      Discussion discussion = findDiscussionById.findById(discussionId);
      discussionDao.remove(discussion);
   }

   public void addVoteToDiscussion(long discussionId, Vote vote)
   {
      Discussion discussion = findDiscussionById.findById(discussionId);
      discussion.getVotes().add(vote);
   }

   public void closeDiscussion(long discussionId)
   {
      Discussion discussion = findDiscussionById.findById(discussionId);
      discussion.setOpen(false);
   }

   public void deleteDiscussion(long discussionId)
   {
      Discussion discussion = findDiscussionById.findById(discussionId);
      discussionDao.remove(discussion);
   }

}