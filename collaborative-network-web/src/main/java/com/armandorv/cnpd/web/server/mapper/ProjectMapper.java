package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Project;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.ProjectInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Projects;

/**
 * Map a business project to a presentation model project. Only basic fields,
 * members mapping is not a concern of this mapper.
 * 
 * @author armandorv
 * 
 */
@Projects
@Dependent
public class ProjectMapper implements Mapper<Project, ProjectInfo>
{
   @Override
   public ProjectInfo map(Project object)
   {

      if (object.getId() == null)
         throw new MappingErrorException("Project must has a identifier.");

      ProjectInfo info = new ProjectInfo();

      info.setId(object.getId());

      info.setTitle(object.getTitle());

      info.setPublished(object.isPublisht());

      info.setManagerId(object.getManagerId());

      info.setDescription(object.getDescription());

      info.setUrl(object.getUrl());

      info.setKnowledgeArea(new IdNameGenericInfo(object.getKnowledgeArea().getId(), object.getKnowledgeArea()
            .getName()));

      info.setLastMilestone(object.getLastMilestone() == null ? "None" : object.getLastMilestone().getName());

      return info;
   }

}