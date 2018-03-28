package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class ScatterChart
  extends XYChart
{
  private static final int SHAPE_WIDTH = 10;
  private static final float SIZE = 3.0F;
  public static final String TYPE = "Scatter";
  private float size = 3.0F;
  
  ScatterChart() {}
  
  public ScatterChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.size = paramXYMultipleSeriesRenderer.getPointSize();
  }
  
  private void drawCircle(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawCircle(paramFloat1, paramFloat2, this.size, paramPaint);
  }
  
  private void drawDiamond(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2)
  {
    paramArrayOfFloat[0] = paramFloat1;
    paramArrayOfFloat[1] = (paramFloat2 - this.size);
    paramArrayOfFloat[2] = (paramFloat1 - this.size);
    paramArrayOfFloat[3] = paramFloat2;
    paramArrayOfFloat[4] = paramFloat1;
    paramArrayOfFloat[5] = (this.size + paramFloat2);
    paramArrayOfFloat[6] = (this.size + paramFloat1);
    paramArrayOfFloat[7] = paramFloat2;
    drawPath(paramCanvas, paramArrayOfFloat, paramPaint, true);
  }
  
  private void drawSquare(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawRect(paramFloat1 - this.size, paramFloat2 - this.size, paramFloat1 + this.size, paramFloat2 + this.size, paramPaint);
  }
  
  private void drawTriangle(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2)
  {
    paramArrayOfFloat[0] = paramFloat1;
    paramArrayOfFloat[1] = (paramFloat2 - this.size - this.size / 2.0F);
    paramArrayOfFloat[2] = (paramFloat1 - this.size);
    paramArrayOfFloat[3] = (this.size + paramFloat2);
    paramArrayOfFloat[4] = (this.size + paramFloat1);
    paramArrayOfFloat[5] = paramArrayOfFloat[3];
    drawPath(paramCanvas, paramArrayOfFloat, paramPaint, true);
  }
  
  private void drawX(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawLine(paramFloat1 - this.size, paramFloat2 - this.size, paramFloat1 + this.size, paramFloat2 + this.size, paramPaint);
    paramCanvas.drawLine(paramFloat1 + this.size, paramFloat2 - this.size, paramFloat1 - this.size, paramFloat2 + this.size, paramPaint);
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
    if (((XYSeriesRenderer)paramSimpleSeriesRenderer).isFillPoints()) {
      paramPaint.setStyle(Paint.Style.FILL);
    }
    for (;;)
    {
      switch (((XYSeriesRenderer)paramSimpleSeriesRenderer).getPointStyle())
      {
      default: 
        return;
        paramPaint.setStyle(Paint.Style.STROKE);
      }
    }
    drawX(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawCircle(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawTriangle(paramCanvas, paramPaint, new float[6], paramFloat1 + 10.0F, paramFloat2);
    return;
    drawSquare(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawDiamond(paramCanvas, paramPaint, new float[8], paramFloat1 + 10.0F, paramFloat2);
    return;
    paramCanvas.drawPoint(paramFloat1 + 10.0F, paramFloat2, paramPaint);
  }
  
  public void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    paramPaint.setColor(paramXYSeriesRenderer.getColor());
    paramFloat = paramPaint.getStrokeWidth();
    if (paramXYSeriesRenderer.isFillPoints())
    {
      paramPaint.setStyle(Paint.Style.FILL);
      paramInt2 = paramList.size();
      switch (paramXYSeriesRenderer.getPointStyle())
      {
      }
    }
    for (;;)
    {
      paramPaint.setStrokeWidth(paramFloat);
      return;
      paramPaint.setStrokeWidth(paramXYSeriesRenderer.getPointStrokeWidth());
      paramPaint.setStyle(Paint.Style.STROKE);
      break;
      paramPaint.setStrokeWidth(paramXYSeriesRenderer.getPointStrokeWidth());
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        drawX(paramCanvas, paramPaint, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue());
        paramInt1 += 2;
      }
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        drawCircle(paramCanvas, paramPaint, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue());
        paramInt1 += 2;
      }
      paramXYSeriesRenderer = new float[6];
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        drawTriangle(paramCanvas, paramPaint, paramXYSeriesRenderer, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue());
        paramInt1 += 2;
      }
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        drawSquare(paramCanvas, paramPaint, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue());
        paramInt1 += 2;
      }
      paramXYSeriesRenderer = new float[8];
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        drawDiamond(paramCanvas, paramPaint, paramXYSeriesRenderer, ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue());
        paramInt1 += 2;
      }
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        paramCanvas.drawPoint(((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue(), paramPaint);
        paramInt1 += 2;
      }
    }
  }
  
  public String getChartType()
  {
    return "Scatter";
  }
  
  public int getLegendShapeWidth(int paramInt)
  {
    return 10;
  }
  
  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super.setDatasetRenderer(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.size = paramXYMultipleSeriesRenderer.getPointSize();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/ScatterChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */