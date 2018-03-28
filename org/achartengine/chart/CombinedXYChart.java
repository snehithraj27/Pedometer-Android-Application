package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.io.Serializable;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
import org.achartengine.renderer.XYSeriesRenderer;

public class CombinedXYChart
  extends XYChart
{
  private XYCombinedChartDef[] chartDefinitions;
  private XYChart[] mCharts;
  private Class<?>[] xyChartTypes = { TimeChart.class, LineChart.class, CubicLineChart.class, BarChart.class, BubbleChart.class, ScatterChart.class, RangeBarChart.class, RangeStackedBarChart.class };
  
  public CombinedXYChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, XYCombinedChartDef[] paramArrayOfXYCombinedChartDef)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.chartDefinitions = paramArrayOfXYCombinedChartDef;
    int k = paramArrayOfXYCombinedChartDef.length;
    this.mCharts = new XYChart[k];
    int i = 0;
    for (;;)
    {
      if (i < k) {}
      try
      {
        this.mCharts[i] = getXYChart(paramArrayOfXYCombinedChartDef[i].getType());
        if (this.mCharts[i] == null) {
          throw new IllegalArgumentException("Unknown chart type " + paramArrayOfXYCombinedChartDef[i].getType());
        }
        XYMultipleSeriesDataset localXYMultipleSeriesDataset = new XYMultipleSeriesDataset();
        XYMultipleSeriesRenderer localXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer();
        int[] arrayOfInt = paramArrayOfXYCombinedChartDef[i].getSeriesIndex();
        int m = arrayOfInt.length;
        int j = 0;
        while (j < m)
        {
          int n = arrayOfInt[j];
          localXYMultipleSeriesDataset.addSeries(paramXYMultipleSeriesDataset.getSeriesAt(n));
          localXYMultipleSeriesRenderer.addSeriesRenderer(paramXYMultipleSeriesRenderer.getSeriesRendererAt(n));
          j += 1;
        }
        localXYMultipleSeriesRenderer.setBarSpacing(paramXYMultipleSeriesRenderer.getBarSpacing());
        localXYMultipleSeriesRenderer.setPointSize(paramXYMultipleSeriesRenderer.getPointSize());
        this.mCharts[i].setDatasetRenderer(localXYMultipleSeriesDataset, localXYMultipleSeriesRenderer);
        i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public CombinedXYChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, XYCombinedChartDef[] paramArrayOfXYCombinedChartDef, XYChart[] paramArrayOfXYChart)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.chartDefinitions = paramArrayOfXYCombinedChartDef;
    this.mCharts = paramArrayOfXYChart;
  }
  
  private int getChartSeriesIndex(int paramInt)
  {
    int i = 0;
    while (i < this.chartDefinitions.length)
    {
      if (this.chartDefinitions[i].containsSeries(paramInt)) {
        return this.chartDefinitions[i].getChartSeriesIndex(paramInt);
      }
      i += 1;
    }
    throw new IllegalArgumentException("Unknown series with index " + paramInt);
  }
  
  private XYChart getXYChart(int paramInt)
  {
    int i = 0;
    while (i < this.chartDefinitions.length)
    {
      if (this.chartDefinitions[i].containsSeries(paramInt)) {
        return this.mCharts[i];
      }
      i += 1;
    }
    throw new IllegalArgumentException("Unknown series with index " + paramInt);
  }
  
  private XYChart getXYChart(String paramString)
    throws IllegalAccessException, InstantiationException
  {
    Object localObject = null;
    int j = this.xyChartTypes.length;
    int i = 0;
    while ((i < j) && (localObject == null))
    {
      XYChart localXYChart = (XYChart)this.xyChartTypes[i].newInstance();
      if (paramString.equals(localXYChart.getChartType())) {
        localObject = localXYChart;
      }
      i += 1;
    }
    return (XYChart)localObject;
  }
  
  protected ClickableArea[] clickableAreasForPoints(List<Float> paramList, List<Double> paramList1, float paramFloat, int paramInt1, int paramInt2)
  {
    return getXYChart(paramInt1).clickableAreasForPoints(paramList, paramList1, paramFloat, getChartSeriesIndex(paramInt1), paramInt2);
  }
  
  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    getXYChart(paramInt).drawLegendShape(paramCanvas, paramSimpleSeriesRenderer, paramFloat1, paramFloat2, getChartSeriesIndex(paramInt), paramPaint);
  }
  
  public void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    XYChart localXYChart = getXYChart(paramInt1);
    localXYChart.setScreenR(getScreenR());
    localXYChart.setCalcRange(getCalcRange(this.mDataset.getSeriesAt(paramInt1).getScaleNumber()), 0);
    localXYChart.drawSeries(paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, getChartSeriesIndex(paramInt1), paramInt2);
  }
  
  protected void drawSeries(XYSeries paramXYSeries, Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, XYMultipleSeriesRenderer.Orientation paramOrientation, int paramInt2)
  {
    XYChart localXYChart = getXYChart(paramInt1);
    localXYChart.setScreenR(getScreenR());
    localXYChart.setCalcRange(getCalcRange(this.mDataset.getSeriesAt(paramInt1).getScaleNumber()), 0);
    localXYChart.drawSeries(paramXYSeries, paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, getChartSeriesIndex(paramInt1), paramOrientation, paramInt2);
  }
  
  public String getChartType()
  {
    return "Combined";
  }
  
  public int getLegendShapeWidth(int paramInt)
  {
    return getXYChart(paramInt).getLegendShapeWidth(getChartSeriesIndex(paramInt));
  }
  
  public static class XYCombinedChartDef
    implements Serializable
  {
    private int[] seriesIndex;
    private String type;
    
    public XYCombinedChartDef(String paramString, int... paramVarArgs)
    {
      this.type = paramString;
      this.seriesIndex = paramVarArgs;
    }
    
    public boolean containsSeries(int paramInt)
    {
      return getChartSeriesIndex(paramInt) >= 0;
    }
    
    public int getChartSeriesIndex(int paramInt)
    {
      int i = 0;
      while (i < getSeriesIndex().length)
      {
        if (this.seriesIndex[i] == paramInt) {
          return i;
        }
        i += 1;
      }
      return -1;
    }
    
    public int[] getSeriesIndex()
    {
      return this.seriesIndex;
    }
    
    public String getType()
    {
      return this.type;
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/CombinedXYChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */