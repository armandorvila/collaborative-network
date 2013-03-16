package com.armandorv.cnpd.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.swing.JFrame;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import com.armandorv.cnpd.client.presenter.MainPresenter;
import com.armandorv.cnpd.client.view.BaseFrame;

@ApplicationScoped
public class AppLoader
{
   private JFrame baseFrame;

   @Inject
   private MainPresenter mainPresenter;

   public void load(@Observes ContainerInitialized container)
   {
      baseFrame = new BaseFrame("CNPD Manager");
      mainPresenter.present(baseFrame.getContentPane());
   }

   @Produces
   public JFrame produceBaseFrame()
   {
      return baseFrame;
   }

}