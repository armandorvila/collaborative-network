package com.armandorv.cnpd.web.client.view.info.tree;

import com.armandorv.cnpd.web.shared.model.IdNameGenericInfo;

/**
 * Class which hold several constant and static instance of IdNameGenericInfo 
 *  for profile management options tree.
 * 
 * @author armandorv
 *
 */
public class InfoTreeElement
{
   public static IdNameGenericInfo PERSONAL = new IdNameGenericInfo(1, "Personal");

   public static IdNameGenericInfo ACADEMIC = new IdNameGenericInfo(2, "Academic");

   public static IdNameGenericInfo PROFESSIONAL = new IdNameGenericInfo(3, "Professional");

   public static IdNameGenericInfo ACCOUNT = new IdNameGenericInfo(4, "Account");

   public static IdNameGenericInfo MODIFY_PERSONAL_INFO = new IdNameGenericInfo(5, "Modify personal info");

   public static IdNameGenericInfo MODIFY_ACADEMIC_INFO = new IdNameGenericInfo(6, "Modify academic info");

   public static IdNameGenericInfo MODIFY_PROFESSIONAL_INFO = new IdNameGenericInfo(7, "Modify professional info");

   public static IdNameGenericInfo MODIFY_ACCOUNT_INFO = new IdNameGenericInfo(8, "Modify account");

   public static IdNameGenericInfo DELETE_ACCOUNT = new IdNameGenericInfo(9, "Delete account");
}
