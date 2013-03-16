package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Reference;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.ReferenceInfo;
import com.armandorv.cnpd.web.shared.qualifiers.References;

/**
 * Translate a business model reference in a presentation odel reference.
 * 
 * @author armandorv
 * 
 */
@References
@Dependent
public class ReferenceMapper implements Mapper<Reference, ReferenceInfo>
{

   @Override
   public ReferenceInfo map(Reference object)
   {

      if (object.getId() == null)
         throw new MappingErrorException("Reference must has a identifier.");

      ReferenceInfo ref = new ReferenceInfo();

      ref.setId(object.getId());
      ref.setName(object.getName());
      ref.setUrl(object.getUrl());

      return ref;
   }

}
