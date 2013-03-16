package com.armandorv.cnpd.client.presenter;

import java.awt.Container;

import javax.swing.JComponent;

import com.armandorv.cnpd.client.view.ErrorView;

/**
 * Presenter for error messages.
 * 
 * @author armandorv
 * 
 */
public class ErrorPresenter implements Presenter
{
   public interface View
   {
      JComponent asComponenet();

      void setException(Exception e);

      void showMessage();

      void setTitile(String titile);

      void setCategory(String category);

      void setMessage(String message);
   }

   public ErrorPresenter(Exception exception)
   {
      view = new ErrorView();
      view.setException(exception);
   }
   
   public ErrorPresenter(Exception exception, String message)
   {
      view = new ErrorView();
      view.setException(exception);
      view.setMessage(message);
   }
   
   public ErrorPresenter(Exception exception, String titile , String message)
   {
      view = new ErrorView();
      view.setException(exception);
      view.setMessage(message);
      view.setTitile(titile);
   }
   
   /*asdasdas
    * asdasdas
    * asdasd*/
   
   public ErrorPresenter(Exception exception, String titile ,String category, String message)
   {
      view = new ErrorView();
      view.setException(exception);
      view.setMessage(message);
      view.setTitile(titile);
      view.setCategory(category);
   }

   /*
    * CDI may be uninitialized.
    */
   private View view;

   @Override
   public void present(Container container)
   {
      view.showMessage();
   }

}
