package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class BubbleChart
  extends XYChart
{
  private static final int MAX_BUBBLE_SIZE = 20;
  private static final int MIN_BUBBLE_SIZE = 2;
  private static final int SHAPE_WIDTH = 10;
  public static final String TYPE = "Bubble";
  
  BubbleChart() {}
  
  public BubbleChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }
  
  private void drawCircle(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramCanvas.drawCircle(paramFloat1, paramFloat2, paramFloat3, paramPaint);
  }
  
  protected ClickableArea[] clickableAreasForPoints(List<Float> paramList, List<Double> paramList1, float paramFloat, int paramInt1, int paramInt2)
  {
    int i = paramList.size();
    XYValueSeries localXYValueSeries = (XYValueSeries)this.mDataset.getSeriesAt(paramInt1);
    double d1 = 20.0D / localXYValueSeries.getMaxValue();
    ClickableArea[] arrayOfClickableArea = new ClickableArea[i / 2];
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      double d2 = localXYValueSeries.getValue(paramInt1 / 2 + paramInt2) * d1 + 2.0D;
      int j = paramInt1 / 2;
      paramFloat = ((Float)paramList.get(paramInt1)).floatValue();
      float f1 = (float)d2;
      float f2 = ((Float)paramList.get(paramInt1 + 1)).floatValue();
      float f3 = (float)d2;
      float f4 = ((Float)paramList.get(paramInt1)).floatValue();
      arrayOfClickableArea[j] = new ClickableArea(new RectF(paramFloat - f1, f2 - f3, (float)d2 + f4, ((Float)paramList.get(paramInt1 + 1)).floatValue() + (float)d2), ((Double)paramList1.get(paramInt1)).doubleValue(), ((Double)paramList1.get(paramInt1 + 1)).doubleValue());
      paramInt1 += 2;
    }
    return arrayOfClickableArea;
  }
  
  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    paramPaint.setStyle(Paint.Style.FILL);
    drawCircle(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2, 3.0F);
  }
  
  public void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    paramPaint.setColor(paramXYSeriesRenderer.getColor());
    paramPaint.setStyle(Paint.Style.FILL);
    int i = paramList.size();
    paramXYSeriesRenderer = (XYValueSeries)this.mDataset.getSeriesAt(paramInt1);
    double d1 = 20.0D / paramXYSeriesRenderer.getMaxValue();
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      double d2 = paramXYSeriesRenderer.getValue(paramInt1 / 2 + paramInt2);
      drawCircle(paramCanvas, paramPaint, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue(), (float)(d2 * d1 + 2.0D));
      paramInt1 += 2;
    }
  }
  
  public String getChartType()
  {
    return "Bubble";
  }
  
  public int getLegendShapeWidth(int paramInt)
  {
    return 10;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/BubbleChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */