package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer.FillOutsideLine;
import org.achartengine.renderer.XYSeriesRenderer.FillOutsideLine.Type;

public class LineChart
  extends XYChart
{
  private static final int SHAPE_WIDTH = 30;
  public static final String TYPE = "Line";
  private ScatterChart pointsChart;
  
  LineChart() {}
  
  public LineChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.pointsChart = new ScatterChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }
  
  protected ClickableArea[] clickableAreasForPoints(List<Float> paramList, List<Double> paramList1, float paramFloat, int paramInt1, int paramInt2)
  {
    paramInt2 = paramList.size();
    ClickableArea[] arrayOfClickableArea = new ClickableArea[paramInt2 / 2];
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      int i = this.mRenderer.getSelectableBuffer();
      int j = paramInt1 / 2;
      paramFloat = ((Float)paramList.get(paramInt1)).floatValue();
      float f1 = i;
      float f2 = ((Float)paramList.get(paramInt1 + 1)).floatValue();
      float f3 = i;
      float f4 = ((Float)paramList.get(paramInt1)).floatValue();
      arrayOfClickableArea[j] = new ClickableArea(new RectF(paramFloat - f1, f2 - f3, i + f4, ((Float)paramList.get(paramInt1 + 1)).floatValue() + i), ((Double)paramList1.get(paramInt1)).doubleValue(), ((Double)paramList1.get(paramInt1 + 1)).doubleValue());
      paramInt1 += 2;
    }
    return arrayOfClickableArea;
  }
  
  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    float f = paramPaint.getStrokeWidth();
    paramPaint.setStrokeWidth(((XYSeriesRenderer)paramSimpleSeriesRenderer).getLineWidth());
    paramCanvas.drawLine(paramFloat1, paramFloat2, paramFloat1 + 30.0F, paramFloat2, paramPaint);
    paramPaint.setStrokeWidth(f);
    if (isRenderPoints(paramSimpleSeriesRenderer)) {
      this.pointsChart.drawLegendShape(paramCanvas, paramSimpleSeriesRenderer, paramFloat1 + 5.0F, paramFloat2, paramInt, paramPaint);
    }
  }
  
  public void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    float f2 = paramPaint.getStrokeWidth();
    paramPaint.setStrokeWidth(paramXYSeriesRenderer.getLineWidth());
    XYSeriesRenderer.FillOutsideLine[] arrayOfFillOutsideLine = paramXYSeriesRenderer.getFillOutsideLine();
    int m = arrayOfFillOutsideLine.length;
    int i = 0;
    while (i < m)
    {
      XYSeriesRenderer.FillOutsideLine localFillOutsideLine = arrayOfFillOutsideLine[i];
      if (localFillOutsideLine.getType() != XYSeriesRenderer.FillOutsideLine.Type.NONE)
      {
        paramPaint.setColor(localFillOutsideLine.getColor());
        ArrayList localArrayList = new ArrayList();
        Object localObject = localFillOutsideLine.getFillRange();
        if (localObject == null) {
          localArrayList.addAll(paramList);
        }
        for (;;)
        {
          switch (localFillOutsideLine.getType())
          {
          default: 
            throw new RuntimeException("You have added a new type of filling but have not implemented.");
            if ((paramList.size() > localObject[0] * 2) && (paramList.size() > localObject[1] * 2)) {
              localArrayList.addAll(paramList.subList(localObject[0] * 2, localObject[1] * 2));
            }
            break;
          }
        }
        float f1 = paramFloat;
        if ((localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_ABOVE) || (localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_BELOW))
        {
          localObject = new ArrayList();
          paramInt1 = 0;
          int n = localArrayList.size();
          if ((n <= 0) || (localFillOutsideLine.getType() != XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_ABOVE) || (((Float)localArrayList.get(1)).floatValue() >= f1))
          {
            paramInt2 = paramInt1;
            if (localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_BELOW)
            {
              paramInt2 = paramInt1;
              if (((Float)localArrayList.get(1)).floatValue() <= f1) {}
            }
          }
          else
          {
            ((List)localObject).add(localArrayList.get(0));
            ((List)localObject).add(localArrayList.get(1));
            paramInt2 = 1;
          }
          paramInt1 = 3;
          int j = paramInt2;
          label373:
          if (paramInt1 < n)
          {
            float f3 = ((Float)localArrayList.get(paramInt1 - 2)).floatValue();
            float f4 = ((Float)localArrayList.get(paramInt1)).floatValue();
            float f6;
            int k;
            if (((f3 < f1) && (f4 > f1)) || ((f3 > f1) && (f4 < f1)))
            {
              float f5 = ((Float)localArrayList.get(paramInt1 - 3)).floatValue();
              f6 = ((Float)localArrayList.get(paramInt1 - 1)).floatValue();
              ((List)localObject).add(Float.valueOf((f6 - f5) * (f1 - f3) / (f4 - f3) + f5));
              ((List)localObject).add(Float.valueOf(f1));
              if (((localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_ABOVE) && (f4 > f1)) || ((localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_BELOW) && (f4 < f1)))
              {
                k = paramInt1 + 2;
                paramInt2 = 0;
              }
            }
            for (;;)
            {
              paramInt1 = k + 2;
              j = paramInt2;
              break label373;
              f1 = paramFloat;
              break;
              f1 = paramFloat;
              break;
              f1 = paramCanvas.getHeight();
              break;
              f1 = 0.0F;
              break;
              ((List)localObject).add(Float.valueOf(f6));
              ((List)localObject).add(Float.valueOf(f4));
              paramInt2 = 1;
              k = paramInt1;
              continue;
              if ((j == 0) && ((localFillOutsideLine.getType() != XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_ABOVE) || (f4 >= f1)))
              {
                paramInt2 = j;
                k = paramInt1;
                if (localFillOutsideLine.getType() == XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_BELOW)
                {
                  paramInt2 = j;
                  k = paramInt1;
                  if (f4 <= f1) {}
                }
              }
              else
              {
                ((List)localObject).add(localArrayList.get(paramInt1 - 1));
                ((List)localObject).add(Float.valueOf(f4));
                paramInt2 = j;
                k = paramInt1;
              }
            }
          }
          localArrayList.clear();
          localArrayList.addAll((Collection)localObject);
        }
        paramInt2 = localArrayList.size();
        if (paramInt2 > 0)
        {
          localArrayList.set(0, Float.valueOf(((Float)localArrayList.get(0)).floatValue() + 1.0F));
          localArrayList.add(localArrayList.get(paramInt2 - 2));
          localArrayList.add(Float.valueOf(f1));
          localArrayList.add(localArrayList.get(0));
          localArrayList.add(localArrayList.get(paramInt2 + 1));
          paramInt1 = 0;
          while (paramInt1 < paramInt2 + 4)
          {
            if (((Float)localArrayList.get(paramInt1 + 1)).floatValue() < 0.0F) {
              localArrayList.set(paramInt1 + 1, Float.valueOf(0.0F));
            }
            paramInt1 += 2;
          }
          paramPaint.setStyle(Paint.Style.FILL);
          drawPath(paramCanvas, localArrayList, paramPaint, true);
        }
      }
      i += 1;
    }
    paramPaint.setColor(paramXYSeriesRenderer.getColor());
    paramPaint.setStyle(Paint.Style.STROKE);
    drawPath(paramCanvas, paramList, paramPaint, false);
    paramPaint.setStrokeWidth(f2);
  }
  
  public String getChartType()
  {
    return "Line";
  }
  
  public int getLegendShapeWidth(int paramInt)
  {
    return 30;
  }
  
  public ScatterChart getPointsChart()
  {
    return this.pointsChart;
  }
  
  public boolean isRenderPoints(SimpleSeriesRenderer paramSimpleSeriesRenderer)
  {
    return ((XYSeriesRenderer)paramSimpleSeriesRenderer).getPointStyle() != PointStyle.POINT;
  }
  
  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super.setDatasetRenderer(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.pointsChart = new ScatterChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/LineChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */