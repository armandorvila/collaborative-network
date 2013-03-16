package com.armandorv.cnpd.web.client.presenter;

/**
 * <p> Base interfaz for all presenter of the Model view Presenter architecture, used for this web application. </p>
 * <p> The presenter define the display interface to deal with their view. </p>
 * <p> Presenter deal with service layer , prepare their display and pent it. </p>
 * <p> Presenters can execute other presenter when necessary. </p>
 * 
 * @author armandorv
 *
 */
public interface Presenter
{
   /**
    * Main method of all presenter, prepare their display and present it.
    */
   public void present();
}
