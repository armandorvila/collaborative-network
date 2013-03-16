package com.armandorv.cnpd.web.server.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.armandorv.cnpd.model.User;

/**
 * Custom implementation of UserDetails which adapt an user of my business
 * domain to the UsersDetails interface.
 * 
 * @author armandorv
 * 
 */
public class UserDetailsAdaptor implements UserDetails
{
   private static final long serialVersionUID = -4388701527691863214L;

   private static final String ROLE_USER = "ROLE_USER";

   private User user;

   public UserDetailsAdaptor(User user)
   {
      this.user = user;
   }

   @Override
   public Collection<GrantedAuthority> getAuthorities()
   {
      return AuthorityUtils.createAuthorityList(ROLE_USER);
   }

   @Override
   public String getPassword()
   {

      return user.getGoogleAccount().getPassword();
   }

   @Override
   public String getUsername()
   {

      return user.getGoogleAccount().getAccount();
   }

   @Override
   public boolean isAccountNonExpired()
   {

      return user != null;
   }

   @Override
   public boolean isAccountNonLocked()
   {

      return user != null;
   }

   @Override
   public boolean isCredentialsNonExpired()
   {

      return user != null;
   }

   @Override
   public boolean isEnabled()
   {

      return user != null;
   }

}
