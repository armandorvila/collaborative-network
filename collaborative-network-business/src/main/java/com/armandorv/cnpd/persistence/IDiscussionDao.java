package com.armandorv.cnpd.persistence;

import java.util.List;

import com.armandorv.cnpd.model.Discussion;
import com.armandorv.cnpd.model.Project;

/**
 * Interface for Discussion entity which extends the base IDAO interface.
 * 
 * @author armandorv
 * 
 */
public interface IDiscussionDao extends IDao<Discussion>
{

   List<Discussion> getDiscussionsByProject(Project project);

}
