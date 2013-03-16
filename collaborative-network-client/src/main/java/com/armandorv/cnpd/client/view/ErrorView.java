package com.armandorv.cnpd.client.view;

import java.util.logging.Level;

import javax.swing.JComponent;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import com.armandorv.cnpd.client.presenter.ErrorPresenter;

/**
 * View that shows an error and is managed by a presenter.
 * 
 * @author armandorv
 *
 */
public class ErrorView extends JXErrorPane implements ErrorPresenter.View
{
   private static final long serialVersionUID = -2312671789707609104L;

   ErrorInfo info = null;

   private Exception exception = null;

   private String title = "";

   private String category = "";

   private String message = "";

   @Override
   public JComponent asComponenet()
   {
      return this;
   }

   @Override
   public void showMessage()
   {

      info = new ErrorInfo(title, message, null, category,
            exception, Level.ALL, null);

      showDialog(this, info);

   }

   @Override
   public void setException(Exception exception)
   {
      this.exception = exception;
   }

   @Override
   public void setTitile(String titile)
   {
      this.title = titile;
   }

   @Override
   public void setCategory(String category)
   {
      this.category = category;
   }

   @Override
   public void setMessage(String message)
   {
      this.message = message;
   }

}