package com.armandorv.cnpd.persistence;

import java.util.List;
import com.armandorv.cnpd.model.KnowledgeArea;
import com.armandorv.cnpd.model.Project;

/**
 * Interface for project entity
 * 
 * @author armandorv
 * 
 */
public interface IProjectDao extends IDao<Project>
{

   List<Project> getProjectsByrArea(KnowledgeArea area);

   List<Project> getProjectsByTtitle(String title);

}
