package com.armandorv.cnpd.web.client.view.util.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * Class to build a columns config list, very useful for widget
 * with Grids. For build a columns config list 
 * 
 * @author armandorv
 * 
 * @param <T>
 *            model object type, i.e @ContactInfo
 */
public class ColumnsBuilder<T>
{

   private List<ColumnConfig<T, ?>> columnsList = new ArrayList<ColumnConfig<T, ?>>();

   /**
    * Create a Strings based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> build(String name, ValueProvider<? super T, String> valueProvider, int width,
         boolean hideable)
   {
      return build(name, valueProvider, width, hideable, null);
   }

   /**
    * Create a Strings based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * @param cell the cell for column.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> build(String name, ValueProvider<? super T, String> valueProvider, int width,
         boolean hideable, Cell<String> cell)
   {
      columnsList.add(buildColumn(name, valueProvider, width, hideable, cell));
      return this;
   }

   /**
    * Create a Strings based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> buildBoolean(String name, ValueProvider<? super T, Boolean> valueProvider, int width,
         boolean hideable)
   {
      return buildBoolean(name, valueProvider, width, hideable, null);
   }

   /**
    * Create a Strings based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * @param cell the cell for column.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> buildBoolean(String name, ValueProvider<? super T, Boolean> valueProvider, int width,
         boolean hideable, Cell<Boolean> cell)
   {
      columnsList.add(buildBooleanColumn(name, valueProvider, width, hideable, cell));
      return this;
   }

   /**
    * Create a Strings based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> buildDate(String name, ValueProvider<? super T, Date> valueProvider, int width,
         boolean hideable)
   {
      return buildDate(name, valueProvider, width, hideable, null);
   }

   /**
    * Create a Date based column config.
    *
    * @param name the name of the column.
    * @param valueProvider the value provider of the column.
    * @param width integer value of column width.
    * @param hideable if column must be hideable.
    * @param cell the cell for column.
    * 
    * @return the current instance of column builder to continue the building process.
    */
   public ColumnsBuilder<T> buildDate(String name, ValueProvider<? super T, Date> valueProvider, int width,
         boolean hideable, Cell<Date> cell)
   {
      columnsList.add(buildDateColumn(name, valueProvider, width, hideable, cell));
      return this;
   }

   /**
    * Method to get the pre built column config list.
    * <ul> 
    *    <li> If no any build method is called previously, a empty list is returned.</li>
    *    <li>When this method is called the list the building process is reset.</li>
    * </ul>
    * 
    * @return a not null List with pre built columns.
    */
   public List<ColumnConfig<T, ?>> get()
   {
      return columnsList;
   }

   public ColumnsBuilder<T> buildInteger(String name, ValueProvider<? super T, Integer> valueProvider, int width,
         boolean hideable)
   {
      ColumnConfig<T, Integer> column = new ColumnConfig<T, Integer>(valueProvider, width, name);
      column.setHideable(hideable);

      columnsList.add(column);

      return this;
   }

   /* *************** Private method **************** */
   private ColumnConfig<T, ?> buildColumn(String name, ValueProvider<? super T, String> valueProvider, int width,
         boolean hideable, Cell<String> cell)
   {
      ColumnConfig<T, String> column = new ColumnConfig<T, String>(valueProvider, width, name);

      if (cell != null)
         column.setCell(cell);

      column.setHideable(hideable);

      return column;
   }

   private ColumnConfig<T, ?> buildBooleanColumn(String name, ValueProvider<? super T, Boolean> valueProvider,
         int width, boolean hideable, Cell<Boolean> cell)
   {
      ColumnConfig<T, Boolean> column = new ColumnConfig<T, Boolean>(valueProvider, width, name);

      if (cell != null)
         column.setCell(cell);

      column.setHideable(hideable);

      return column;
   }

   private ColumnConfig<T, ?> buildDateColumn(String name, ValueProvider<? super T, Date> valueProvider, int width,
         boolean hideable, Cell<Date> cell)
   {
      ColumnConfig<T, Date> column = new ColumnConfig<T, Date>(valueProvider, width, name);

      if (cell != null)
         column.setCell(cell);

      column.setHideable(hideable);

      return column;
   }

   /* *************** End of private method **************** */
}