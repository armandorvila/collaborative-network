package com.armandorv.cnpd.web.client.view.projects.project.management.util;

import com.armandorv.cnpd.web.client.presenter.projects.project.management.ManagementPresenter;
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
public class ManagementTreeCell extends SimpleSafeHtmlCell<String>
{
   private ManagementPresenter.Executable publish;

   private ManagementPresenter.Executable delete;
   
   private ManagementPresenter.Executable modify;

   private ManagementPresenter.Executable changeManager;

   private ManagementPresenter.Executable inviteContacts;

   private ManagementPresenter.Executable excludeContacts;

   private boolean enabled = true;

   public ManagementTreeCell()
   {
      super(SimpleSafeHtmlRenderer.getInstance(), DoubleClickEvent.getType().getName());
   }

   @Override
   public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
         ValueUpdater<String> valueUpdater)
   {

      if (publish == null || delete == null || changeManager == null|| modify == null || inviteContacts == null
            || excludeContacts == null)
      {
         throw new ClientsideException("Executables must be set previously.");
      }

      if (enabled)
      {
         super.onBrowserEvent(context, parent, value, event, valueUpdater);

         if (DoubleClickEvent.getType().getName().equals(event.getType()))
         {
            if (value.equals(ManagementTreeElement.PUBLISH.getName()))
            {
               publish.execute();
            }
            if (value.equals(ManagementTreeElement.DELETE.getName()))
            {
               delete.execute();
            }
            if (value.equals(ManagementTreeElement.MODIFY.getName()))
            {
               modify.execute();
            }
            if (value.equals(ManagementTreeElement.CHANGE_MANAGER.getName()))
            {
               changeManager.execute();
            }
            if (value.equals(ManagementTreeElement.INVITE_CONTACTS.getName()))
            {
               inviteContacts.execute();
            }
            if (value.equals(ManagementTreeElement.EXCLUDE_CONTACTS.getName()))
            {
               excludeContacts.execute();
            }
         }

      }
   }

   public void setPublish(ManagementPresenter.Executable publish)
   {
      this.publish = publish;
   }

   public void setDelete(ManagementPresenter.Executable delete)
   {
      this.delete = delete;
   }

   public void setChangeManager(ManagementPresenter.Executable changeManager)
   {
      this.changeManager = changeManager;
   }

   public void setInviteContacts(ManagementPresenter.Executable inviteContacts)
   {
      this.inviteContacts = inviteContacts;
   }

   public void setExcludeContacts(ManagementPresenter.Executable excludeContacts)
   {
      this.excludeContacts = excludeContacts;
   }

   public void setEnabled(boolean enabled)
   {
      this.enabled = enabled;
   }

   public void setModify(ManagementPresenter.Executable modify)
   {
      this.modify = modify;
   }
}