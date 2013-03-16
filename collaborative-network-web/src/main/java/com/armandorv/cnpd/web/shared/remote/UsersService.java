package com.armandorv.cnpd.web.shared.remote;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

import com.armandorv.cnpd.web.shared.model.UserInfo;

@Remote
public interface UsersService
{
   UserInfo getUserByUsername(String username);

   boolean setUserInformation(UserInfo info);

   boolean setUserDegrees(long userId, List<String> degrees);

   boolean setUserJobs(long userId, List<String> jobs);

   boolean validate(String username , String password);
   
   boolean validateAgainstGoogle(String username , String password);
   
   boolean setUserAccount(long userId, String newUsername, String newPassword);

   boolean deleteUser(long userId);
}
