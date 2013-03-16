package com.armandorv.cnpd.web.server.mapper;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;

import com.armandorv.cnpd.model.PersonalData;
import com.armandorv.cnpd.model.User;
import com.armandorv.cnpd.web.shared.exception.MappingErrorException;
import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;
import com.armandorv.cnpd.web.shared.model.UserInfo;
import com.armandorv.cnpd.web.shared.qualifiers.Users;

/**
 * Map a business user to a presentation user info.
 * 
 * @author armandorv
 * 
 */
@Users
@Dependent
public class UserMapper implements Mapper<User, UserInfo>
{

   @Override
   public UserInfo map(User object)
   {

      if (object.getId() == null)
         throw new MappingErrorException("User must has a identifier.");

      UserInfo userInfo = new UserInfo();
      PersonalData data = object.getPersonalData();

      userInfo.setId(object.getId());

      userInfo.setName(data.getName());
      userInfo.setLastname1(data.getSurname1());
      userInfo.setLastname2(data.getSurname2());
      userInfo.setFullName(data.getName() + " " + data.getSurname1());
      userInfo.setBirthday(data.getDateOfBirthday());

      userInfo.setCity(data.getCity());
      userInfo.setWebSite(data.getWebSite());

      userInfo.setUsername(object.getGoogleAccount().getAccount());

      userInfo.setDegrees(new ArrayList<IdNameGenericInfo>());
      userInfo.setJobs(new ArrayList<IdNameGenericInfo>());

      for (int i = 0; i < object.getDegrees().size(); i++)
         userInfo.getDegrees().add(new IdNameGenericInfo(i, object.getDegrees().get(i)));

      for (int i = 0; i < object.getJobs().size(); i++)
         userInfo.getJobs().add(new IdNameGenericInfo(i, object.getJobs().get(i)));

      return userInfo;
   }

}
