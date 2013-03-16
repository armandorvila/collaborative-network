package com.armandorv.cnpd.web.client.view.util.builder;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.BooleanFilter;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;

/**
 * Class to build grid filters. 
 * 
 * 
 * @author armandorv
 *
 * @param <T> type of model object of grid.
 */
public class FiltersBuilder<T>
{

   private GridFilters<T> filters = new GridFilters<T>();

   /**
    * 
    * @param valueProvider
    * @return
    */
   public FiltersBuilder<T> build(ValueProvider<T, String> valueProvider)
   {

      filters.addFilter(new StringFilter<T>(valueProvider));

      return this;
   }

   /**
    * 
    * @param valueProvider
    * @return
    */
   public FiltersBuilder<T> buildBoolean(ValueProvider<T, Boolean> valueProvider)
   {

      filters.addFilter(new BooleanFilter<T>(valueProvider));

      return this;
   }

   /**
    * 
    * @param valueProvider
    * @return
    */
   public FiltersBuilder<T> buildDate(ValueProvider<T, Date> valueProvider)
   {

      filters.addFilter(new DateFilter<T>(valueProvider));

      return this;
   }

   /**
    * 
    * @param grid
    * @return
    */
   public GridFilters<T> get(Grid<T> grid)
   {

      filters.initPlugin(grid);
      filters.setLocal(true);

      return filters;
   }

}