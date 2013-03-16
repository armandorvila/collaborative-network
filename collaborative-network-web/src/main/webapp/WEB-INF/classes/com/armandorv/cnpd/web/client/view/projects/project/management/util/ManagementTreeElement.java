package com.armandorv.cnpd.web.client.view.projects.project.management.util;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;

/**
 * Class which hold several constant and static instance of IdNameGenericInfo for magement options tree.
 * 
 * @author armandorv
 *
 */
public class ManagementTreeElement
{
   public static IdNameGenericInfo PROJECT = new IdNameGenericInfo(1, "Project");

   public static IdNameGenericInfo MANAGER = new IdNameGenericInfo(2, "Manager");

   public static IdNameGenericInfo MEMBERS = new IdNameGenericInfo(3, "Members");

   public static IdNameGenericInfo PUBLISH = new IdNameGenericInfo(4, "Publish");

   public static IdNameGenericInfo DELETE = new IdNameGenericInfo(5, "Delete");
   
   public static IdNameGenericInfo MODIFY = new IdNameGenericInfo(6, "Modify");

   public static IdNameGenericInfo CHANGE_MANAGER = new IdNameGenericInfo(7, "Change Manager");

   public static IdNameGenericInfo INVITE_CONTACTS = new IdNameGenericInfo(8, "Invite Contacts");

   public static IdNameGenericInfo EXCLUDE_CONTACTS = new IdNameGenericInfo(9, "Exclude Contacts");
}
