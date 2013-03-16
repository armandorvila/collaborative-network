package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;
import com.armandorv.cnpd.model.Resource;
import com.armandorv.cnpd.model.ResourceKind;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.ResourceInfo;
import com.armandorv.cnpd.web.shared.model.ResourceInfo.Kind;
import com.armandorv.cnpd.web.shared.qualifiers.Resources;

@Resources
@Dependent
public class ResourceMapper implements Mapper<Resource, ResourceInfo>
{
   @Override
   public ResourceInfo map(Resource object)
   {
      if (object.getId() == null)
         throw new MappingErrorException("Resource must has an identifier.");

      ResourceInfo info = new ResourceInfo();

      info.setId(object.getId());
      info.setName(object.getName());
      info.setKind(this.getKind(object.getKind()));
      info.setUrl(object.getUrl());

      for (Resource child : object.getChilds())
      {
         info.addChild(map(child));
      }

      return info;
   }

   private Kind getKind(ResourceKind resourceKind)
   {
      if (ResourceKind.DOCUMENT.equals(resourceKind))
         return Kind.DOCUMENT;

      if (ResourceKind.FILE.equals(resourceKind))
         return Kind.FILE;

      if (ResourceKind.FOLDER.equals(resourceKind))
         return Kind.FOLDER;

      if (ResourceKind.MEDIA.equals(resourceKind))
         return Kind.MEDIA;

      if (ResourceKind.UNTYPED.equals(resourceKind))
         return Kind.NONE;

      if (ResourceKind.MARKER.equals(resourceKind))
         return Kind.MARKER;

      return null;
   }

}