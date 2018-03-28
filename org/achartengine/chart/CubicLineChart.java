package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import java.util.List;
import org.achartengine.model.Point;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class CubicLineChart
  extends LineChart
{
  public static final String TYPE = "Cubic";
  private float mFirstMultiplier;
  private PathMeasure mPathMeasure;
  private float mSecondMultiplier;
  
  public CubicLineChart()
  {
    this.mFirstMultiplier = 0.33F;
    this.mSecondMultiplier = (1.0F - this.mFirstMultiplier);
  }
  
  public CubicLineChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, float paramFloat)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.mFirstMultiplier = paramFloat;
    this.mSecondMultiplier = (1.0F - this.mFirstMultiplier);
  }
  
  private void calc(List<Float> paramList, Point paramPoint, int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = ((Float)paramList.get(paramInt1)).floatValue();
    float f2 = ((Float)paramList.get(paramInt1 + 1)).floatValue();
    float f3 = ((Float)paramList.get(paramInt2)).floatValue();
    float f4 = ((Float)paramList.get(paramInt2 + 1)).floatValue();
    paramPoint.setX((f3 - f1) * paramFloat + f1);
    paramPoint.setY((f4 - f2) * paramFloat + f2);
  }
  
  protected void drawPath(Canvas paramCanvas, List<Float> paramList, Paint paramPaint, boolean paramBoolean)
  {
    Path localPath = new Path();
    localPath.moveTo(((Float)paramList.get(0)).floatValue(), ((Float)paramList.get(1)).floatValue());
    int j = paramList.size();
    int i = j;
    if (paramBoolean) {
      i = j - 4;
    }
    Point localPoint1 = new Point();
    Point localPoint2 = new Point();
    Point localPoint3 = new Point();
    j = 0;
    if (j < i)
    {
      int k;
      if (j + 2 < i)
      {
        k = j + 2;
        label115:
        if (j + 4 >= i) {
          break label251;
        }
      }
      label251:
      for (int m = j + 4;; m = k)
      {
        calc(paramList, localPoint1, j, k, this.mSecondMultiplier);
        localPoint2.setX(((Float)paramList.get(k)).floatValue());
        localPoint2.setY(((Float)paramList.get(k + 1)).floatValue());
        calc(paramList, localPoint3, k, m, this.mFirstMultiplier);
        localPath.cubicTo(localPoint1.getX(), localPoint1.getY(), localPoint2.getX(), localPoint2.getY(), localPoint3.getX(), localPoint3.getY());
        j += 2;
        break;
        k = j;
        break label115;
      }
    }
    this.mPathMeasure = new PathMeasure(localPath, false);
    if (paramBoolean)
    {
      j = i;
      while (j < i + 4)
      {
        localPath.lineTo(((Float)paramList.get(j)).floatValue(), ((Float)paramList.get(j + 1)).floatValue());
        j += 2;
      }
      localPath.lineTo(((Float)paramList.get(0)).floatValue(), ((Float)paramList.get(1)).floatValue());
    }
    paramCanvas.drawPath(localPath, paramPaint);
  }
  
  protected void drawPoints(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    if (isRenderPoints(paramXYSeriesRenderer))
    {
      ScatterChart localScatterChart = getPointsChart();
      if (localScatterChart != null)
      {
        int m = (int)this.mPathMeasure.getLength();
        int n = paramList.size();
        float[] arrayOfFloat = new float[2];
        int j = 0;
        while (j < m)
        {
          this.mPathMeasure.getPosTan(j, arrayOfFloat, null);
          double d1 = Double.MAX_VALUE;
          int i = 1;
          int k = 0;
          if ((k < n) && (i != 0))
          {
            double d2 = Math.abs(((Float)paramList.get(k)).floatValue() - arrayOfFloat[0]);
            if (d2 < 1.0D)
            {
              paramList.set(k + 1, Float.valueOf(arrayOfFloat[1]));
              d1 = d2;
            }
            if (d1 > d2) {}
            for (i = 1;; i = 0)
            {
              k += 2;
              break;
            }
          }
          j += 1;
        }
        localScatterChart.drawSeries(paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, paramInt1, paramInt2);
      }
    }
  }
  
  public String getChartType()
  {
    return "Cubic";
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/CubicLineChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */