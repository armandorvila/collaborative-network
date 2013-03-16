package com.armandorv.cnpd.web.client.view.projects.project.tasks;

import java.util.List;

import com.armandorv.cnpd.web.client.presenter.projects.project.tasks.TasksSummaryPresenter;
import com.armandorv.cnpd.web.client.view.util.ProgressView;
import com.armandorv.cnpd.web.client.view.util.properties.TaskInfoProperties;
import com.armandorv.cnpd.web.shared.model.TaskInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.AxisToolTipConfig;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

/**
 * Render a chart with a summary of task completion percents.
 * 
 * @author armandorv
 *
 */
public class TasksSummaryView extends Composite implements TasksSummaryPresenter.Display
{
   private static TaskInfoProperties props = GWT.create(TaskInfoProperties.class);

   private ListStore<TaskInfo> store = null;

   private Chart<TaskInfo> chart = null;

   private NumericAxis<TaskInfo> verticalAxis = null;

   private CategoryAxis<TaskInfo, String> horizontalAxis = null;

   private Gradient gradient = null;

   private BarSeries<TaskInfo> barSeries = null;

   private LineSeries<TaskInfo> lineSeries = null;

   private ContentPanel panel = null;

   public TasksSummaryView()
   {
      store = new ListStore<TaskInfo>(props.id());

      initPanel();
      initChart();
      initVerticalAxis();
      initHorizontalAxis();
      initGradient();
      initBarSeries();
      initLineSeries();

      Resizable resize = new Resizable(panel);
      resize.setMinHeight(400);
      resize.setMinWidth(400);

      super.initWidget(panel);
   }

   private void initPanel()
   {
      panel = new ContentPanel();
      panel.getElement().getStyle().setMargin(10, Unit.PX);
      panel.setHeaderVisible(false);
      panel.setPixelSize(620, 500);
   }

   private void initChart()
   {
      chart = new Chart<TaskInfo>();
      chart.setStore(store);
      chart.setDefaultInsets(30);
      chart.setShadowChart(true);

      VerticalLayoutContainer layout = new VerticalLayoutContainer();
      panel.add(layout);

      chart.setLayoutData(new VerticalLayoutData(1, 1));

      layout.add(chart);
   }

   private void initVerticalAxis()
   {
      verticalAxis = new NumericAxis<TaskInfo>();
      verticalAxis.setPosition(Position.LEFT);
      verticalAxis.addField(props.percent());
      verticalAxis.setDisplayGrid(true);
      verticalAxis.setMinimum(0);
      verticalAxis.setMaximum(120);

      TextSprite text = new TextSprite();
      text.setFont("Arial");
      text.setFontSize(10);
      verticalAxis.setLabelConfig(text);

      verticalAxis.setLabelProvider(new LabelProvider<Number>()
      {
         @Override
         public String getLabel(Number item)
         {
            return NumberFormat.getFormat("0").format(item.doubleValue()) + " % ";
         }
      });

      chart.addAxis(verticalAxis);
   }

   private void initHorizontalAxis()
   {
      horizontalAxis = new CategoryAxis<TaskInfo, String>();
      horizontalAxis.setPosition(Position.BOTTOM);
      horizontalAxis.setField(props.name());

      TextSprite text = new TextSprite();
      text.setFont("Arial");
      text.setFontSize(11);

      horizontalAxis.setDisplayGrid(true);
      horizontalAxis.setLabelConfig(text);

      AxisToolTipConfig<TaskInfo> toolTipConf = new AxisToolTipConfig<TaskInfo>();
      toolTipConf.setValueProvider(props.name(), new LabelProvider<String>()
      {
         @Override
         public String getLabel(String item)
         {
            return item;
         }
      });

      horizontalAxis.setToolTipConfig(toolTipConf);

      chart.addAxis(horizontalAxis);
   }

   private void initGradient()
   {
      gradient = new Gradient("bar-gradient", 90);
      gradient.addStop(new Stop(0, new RGB("#99BBE8")));
      gradient.addStop(new Stop(70, new RGB("#77AECE")));
      gradient.addStop(new Stop(100, new RGB("#77AECE")));

      chart.addGradient(gradient);
   }

   private void initBarSeries()
   {
      barSeries = new BarSeries<TaskInfo>();
      barSeries.setYAxisPosition(Position.LEFT);
      barSeries.addYField(props.percent());
      barSeries.setColumn(true);
      barSeries.addColor(gradient);

      chart.addSeries(barSeries);
   }

   private void initLineSeries()
   {
      lineSeries = new LineSeries<TaskInfo>();
      lineSeries.setYAxisPosition(Position.LEFT);
      lineSeries.setYField(props.percent());
      lineSeries.setStroke(new RGB("#18428E"));
      lineSeries.setStrokeWidth(3);
      lineSeries.setShowMarkers(true);
     
      Sprite marker = Primitives.circle(0, 0, 4);
      marker.setFill(new RGB("#18428E"));
      
      lineSeries.setMarkerConfig(marker);
      
      SeriesToolTipConfig<TaskInfo> tooltip = new SeriesToolTipConfig<TaskInfo>();

      tooltip.setLabelProvider(new SeriesLabelProvider<TaskInfo>()
      {
         @Override
         public String getLabel(TaskInfo item, ValueProvider<? super TaskInfo, ? extends Number> valueProvider)
         {
            return NumberFormat.getFormat("0").format(props.percent().getValue(item)) + " percent completed of "
                  + props.name().getValue(item);
         }
      });

      tooltip.setDismissDelay(2000);
      lineSeries.setToolTipConfig(tooltip);
      
      chart.addSeries(lineSeries);
   }

   @Override
   public void setTasks(List<TaskInfo> tasks)
   {
      store.clear();
      store.addAll(tasks);
   }

   @Override
   public void refresh()
   {
      this.horizontalAxis.clear();
      barSeries.clear();
      lineSeries.clear();
      
      chart.redrawSurface();
      chart.redrawChart();
   }

   @Override
   public void showProgress()
   {    
       new ProgressView("Calculating tasks summary.", "Calculating ...", 3500).start();
   }

}