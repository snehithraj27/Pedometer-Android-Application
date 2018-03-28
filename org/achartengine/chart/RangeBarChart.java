package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class RangeBarChart
  extends BarChart
{
  public static final String TYPE = "RangeBar";
  
  RangeBarChart() {}
  
  RangeBarChart(BarChart.Type paramType)
  {
    super(paramType);
  }
  
  public RangeBarChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, BarChart.Type paramType)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer, paramType);
  }
  
  protected void drawChartValuesText(Canvas paramCanvas, XYSeries paramXYSeries, XYSeriesRenderer paramXYSeriesRenderer, Paint paramPaint, List<Float> paramList, int paramInt1, int paramInt2)
  {
    int j = this.mDataset.getSeriesCount();
    float f3 = getHalfDiffX(paramList, paramList.size(), j);
    int i = 0;
    if (paramInt2 > 0) {
      i = 2;
    }
    while (i < paramList.size())
    {
      int k = paramInt2 + i / 2;
      float f2 = ((Float)paramList.get(i)).floatValue();
      float f1 = f2;
      if (this.mType == BarChart.Type.DEFAULT) {
        f1 = f2 + (paramInt1 * 2 * f3 - (j - 1.5F) * f3);
      }
      if ((!isNullValue(paramXYSeries.getY(k + 1))) && (paramList.size() > i + 3)) {
        drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(k + 1)), f1, ((Float)paramList.get(i + 3)).floatValue() - paramXYSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
      }
      if ((!isNullValue(paramXYSeries.getY(k))) && (paramList.size() > i + 1)) {
        drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(k)), f1, ((Float)paramList.get(i + 1)).floatValue() + paramXYSeriesRenderer.getChartValuesTextSize() + paramXYSeriesRenderer.getChartValuesSpacing() - 3.0F, paramPaint, 0.0F);
      }
      i += 4;
    }
  }
  
  public void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    int j = this.mDataset.getSeriesCount();
    int k = paramList.size();
    paramPaint.setColor(paramXYSeriesRenderer.getColor());
    paramPaint.setStyle(Paint.Style.FILL);
    paramFloat = getHalfDiffX(paramList, k, j);
    int i = 0;
    if (paramInt2 > 0) {
      i = 2;
    }
    while (i < k)
    {
      if (paramList.size() > i + 3) {
        drawBar(paramCanvas, ((Float)paramList.get(i)).floatValue(), ((Float)paramList.get(i + 1)).floatValue(), ((Float)paramList.get(i + 2)).floatValue(), ((Float)paramList.get(i + 3)).floatValue(), paramFloat, j, paramInt1, paramPaint);
      }
      i += 4;
    }
    paramPaint.setColor(paramXYSeriesRenderer.getColor());
  }
  
  public String getChartType()
  {
    return "RangeBar";
  }
  
  protected float getCoeficient()
  {
    return 0.5F;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/RangeBarChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */