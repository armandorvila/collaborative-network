package com.armandorv.cnpd.web.server.mapper;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.Meeting;
import com.armandorv.cnpd.web.shared.model.MeetingInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Meetings;

/**
 * Map a meeting of the business model to a meeting of the presentation model.
 * 
 * @author armandorv
 *
 */
@Meetings
@Dependent
public class MeetingMapper implements Mapper<Meeting, MeetingInfo>
{
   @Override
   public MeetingInfo map(Meeting object)
   {
      MeetingInfo info = new MeetingInfo();

      info.setId(object.getId());
      info.setTitle(object.getTitle());
      info.setPlace(object.getPlace());
      info.setDescription(object.getDescription());
      info.setConclusions(object.getConclusion() != null ? object.getConclusion() : "");
      info.setDate(object.getDate());
      info.setInstigatorId(object.getInstigatorId());
      info.setCelebrated(object.isCelebrated());

      return info;
   }

}
