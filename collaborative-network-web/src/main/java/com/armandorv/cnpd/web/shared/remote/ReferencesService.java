package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.ReferenceInfo;

@Remote
public interface ReferencesService
{
   List<ReferenceInfo> getReferences(long projectId);

   boolean addReference(long projectId, long referenceId);
   
   long addNewReference(long projectId, ReferenceInfo reference);
   
   boolean removeReference(long projectId, long referenceId);

   List<ReferenceInfo> searchReferences(long projectId, String keyWords);
}
