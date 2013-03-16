package com.armandorv.cnpd.web.client.view.info.tree;

import com.armandorv.cnpd.web.client.presenter.info.InfoPresenter;
import com.armandorv.cnpd.web.shared.exception.ClientsideException;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;

/**
 * Management tree cell that give support to double click.
 * 
 * @author armandorv
 *
 */
public class InfoTreeCell extends SimpleSafeHtmlCell<String>
{
   private InfoPresenter.Executable modifyAcademicInfo;

   private InfoPresenter.Executable modifyProfessionalInfo;

   private InfoPresenter.Executable modifyPersonalInfo;

   private InfoPresenter.Executable modifyAcccountInfo;
   
   private InfoPresenter.Executable deleteAcccount;

   private boolean enabled = true;

   public InfoTreeCell()
   {
      super(SimpleSafeHtmlRenderer.getInstance(), DoubleClickEvent.getType().getName());
   }

   @Override
   public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
         ValueUpdater<String> valueUpdater)
   {
      if (modifyAcademicInfo == null || modifyProfessionalInfo == null || modifyPersonalInfo == null
            || modifyAcccountInfo == null)
      {
         throw new ClientsideException("Executables must be set previously.");
      }

      if (enabled)
      {
         super.onBrowserEvent(context, parent, value, event, valueUpdater);

         if (DoubleClickEvent.getType().getName().equals(event.getType()))
         {
            if (value.equals(InfoTreeElement.MODIFY_PERSONAL_INFO.getName()))
            {
               modifyPersonalInfo.execute();
            }
            if (value.equals(InfoTreeElement.MODIFY_ACADEMIC_INFO.getName()))
            {
               modifyAcademicInfo.execute();
            }
            if (value.equals(InfoTreeElement.MODIFY_PROFESSIONAL_INFO.getName()))
            {
               modifyProfessionalInfo.execute();
            }
            if (value.equals(InfoTreeElement.MODIFY_ACCOUNT_INFO.getName()))
            {
               modifyAcccountInfo.execute();
            }
            if (value.equals(InfoTreeElement.DELETE_ACCOUNT.getName()))
            {
               deleteAcccount.execute();
            }
         }
      }
   }

   public void setModifyAcademicInfo(InfoPresenter.Executable modifyAcademicInfo)
   {
      this.modifyAcademicInfo = modifyAcademicInfo;
   }

   public void setModifyProfessionalInfo(InfoPresenter.Executable modifyProfessionalInfo)
   {
      this.modifyProfessionalInfo = modifyProfessionalInfo;
   }

   public void setModifyPersonalInfo(InfoPresenter.Executable modifyPersonalInfo)
   {
      this.modifyPersonalInfo = modifyPersonalInfo;
   }

   public void setModifyAcccountInfo(InfoPresenter.Executable modifyAcccountInfo)
   {
      this.modifyAcccountInfo = modifyAcccountInfo;
   }

   public void setDeleteAccount(InfoPresenter.Executable deleteAccount){
      this.deleteAcccount = deleteAccount;
   }
   
   public void setEnabled(boolean enabled)
   {
      this.enabled = enabled;
   }
  
}