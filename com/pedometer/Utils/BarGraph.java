package com.pedometer.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import com.pedometer.Pedometer;
import java.util.ArrayList;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class BarGraph
{
  GoogleAnalyticsHelper mGoogleHelper;
  
  public Intent getIntent(Context paramContext)
  {
    Object localObject1 = (Integer[])Pedometer.arrSteps.toArray(new Integer[Pedometer.arrSteps.size()]);
    Object localObject2 = new CategorySeries("Color");
    int i = 0;
    while (i < localObject1.length)
    {
      ((CategorySeries)localObject2).add("Bar" + (i + 1), localObject1[i].intValue());
      i += 1;
    }
    localObject1 = new XYMultipleSeriesDataset();
    ((XYMultipleSeriesDataset)localObject1).addSeries(((CategorySeries)localObject2).toXYSeries());
    localObject2 = new XYSeriesRenderer();
    ((XYSeriesRenderer)localObject2).setColor(Color.parseColor("#FFA500"));
    ((XYSeriesRenderer)localObject2).setDisplayChartValues(true);
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer();
    localXYMultipleSeriesRenderer.addSeriesRenderer((SimpleSeriesRenderer)localObject2);
    localXYMultipleSeriesRenderer.setChartTitle("Statistics");
    localXYMultipleSeriesRenderer.setChartTitleTextSize(25.0F);
    localXYMultipleSeriesRenderer.setXTitle("Daily");
    localXYMultipleSeriesRenderer.setYTitle("Steps");
    localXYMultipleSeriesRenderer.setAxisTitleTextSize(20.0F);
    localXYMultipleSeriesRenderer.setZoomButtonsVisible(false);
    localXYMultipleSeriesRenderer.setShowLegend(false);
    localXYMultipleSeriesRenderer.setShowGridX(false);
    localXYMultipleSeriesRenderer.setShowGridY(false);
    localXYMultipleSeriesRenderer.setBarSpacing(0.5D);
    localXYMultipleSeriesRenderer.setApplyBackgroundColor(true);
    localXYMultipleSeriesRenderer.setBackgroundColor(-16777216);
    localXYMultipleSeriesRenderer.setXAxisMin(0.0D);
    localXYMultipleSeriesRenderer.setYAxisMin(0.5D);
    localXYMultipleSeriesRenderer.setXAxisMax(7.0D);
    localXYMultipleSeriesRenderer.setYAxisMax(150.0D);
    localXYMultipleSeriesRenderer.setBarWidth(35.0F);
    localXYMultipleSeriesRenderer.setBarSpacing(0.0D);
    localXYMultipleSeriesRenderer.setZoomEnabled(false);
    localXYMultipleSeriesRenderer.setZoomEnabled(false, false);
    localXYMultipleSeriesRenderer.setXLabels(0);
    localXYMultipleSeriesRenderer.addXTextLabel(1.0D, "Day 1");
    localXYMultipleSeriesRenderer.addXTextLabel(2.0D, "Day 2");
    localXYMultipleSeriesRenderer.addXTextLabel(3.0D, "Day 3");
    localXYMultipleSeriesRenderer.addXTextLabel(4.0D, "Day 4");
    localXYMultipleSeriesRenderer.addXTextLabel(5.0D, "Day 5");
    localXYMultipleSeriesRenderer.addXTextLabel(6.0D, "Day 6");
    localXYMultipleSeriesRenderer.addXTextLabel(7.0D, "Day 7");
    localXYMultipleSeriesRenderer.setPanEnabled(true, true);
    return ChartFactory.getBarChartIntent(paramContext, (XYMultipleSeriesDataset)localObject1, localXYMultipleSeriesRenderer, BarChart.Type.DEFAULT);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/BarGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */