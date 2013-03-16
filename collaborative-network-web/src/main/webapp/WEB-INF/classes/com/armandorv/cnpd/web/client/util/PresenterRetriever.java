package com.armandorv.cnpd.web.client.util;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

import com.armandorv.cnpd.web.client.presenter.Presenter;

/**
 * Class to get generic presenter contextual instances programmatically.
 * 
 * @author armandorv
 *
 */
public class PresenterRetriever
{
   @Inject
   private IOCBeanManager manager;

   /**
    * Retrieve a presenter given one of their bean types.
    * 
    * @return a Presenter or null.
    */
   public Presenter retrievePresenter(Class<? extends Presenter> type)
   {
      return retrievePresenter(type, null);
   }

   /**
    * Retrieve a presenter given one of their bean types and one of theirs qualifiers.
    * 
    * @return a Presenter or null.
    */
   public Presenter retrievePresenter(Class<? extends Presenter> type, Annotation qualifier)
   {
      Presenter presenter = null;
      IOCBeanDef<? extends Presenter> bean = null;

      if (qualifier != null)
         bean = manager.lookupBean(type, qualifier);
      else
         bean = manager.lookupBean(type);
      if (bean != null)
      {
         presenter = bean.getInstance();
      }

      return presenter;
   }

   /**
    * Get the correct presenter for a history token.
    */
   public Presenter retrievePresenter(HistoryToken token)
   {
      return retrievePresenter(token, null);
   }

   /**
    * Get the correct presenter for a history token and a qualifier.
    */
   public Presenter retrievePresenter(HistoryToken token, Annotation qualifier)
   {
      Presenter presenter = null;

      IOCBeanDef<? extends Presenter> bean = null;
      if (qualifier != null)
         bean = manager.lookupBean(token.getPresenterType(), qualifier);

      else
         bean = manager.lookupBean(token.getPresenterType());

      if (bean != null)
      {
         presenter = (Presenter) bean.getInstance();
      }
      return presenter;
   }
}