package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DialRenderer;
import org.achartengine.renderer.DialRenderer.Type;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class DialChart
  extends RoundChart
{
  private static final int NEEDLE_RADIUS = 10;
  private DialRenderer mRenderer;
  
  public DialChart(CategorySeries paramCategorySeries, DialRenderer paramDialRenderer)
  {
    super(paramCategorySeries, paramDialRenderer);
    this.mRenderer = paramDialRenderer;
  }
  
  private void drawNeedle(Canvas paramCanvas, double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, boolean paramBoolean, Paint paramPaint)
  {
    double d = Math.toRadians(90.0D);
    int i = (int)(10.0D * Math.sin(paramDouble1 - d));
    int j = (int)(10.0D * Math.cos(paramDouble1 - d));
    int k = (int)(Math.sin(paramDouble1) * paramDouble2);
    int m = (int)(Math.cos(paramDouble1) * paramDouble2);
    k = paramInt1 + k;
    m = paramInt2 + m;
    float[] arrayOfFloat;
    if (paramBoolean)
    {
      int n = paramInt1 + (int)(0.85D * paramDouble2 * Math.sin(paramDouble1));
      int i1 = paramInt2 + (int)(0.85D * paramDouble2 * Math.cos(paramDouble1));
      arrayOfFloat = new float[6];
      arrayOfFloat[0] = (n - i);
      arrayOfFloat[1] = (i1 - j);
      arrayOfFloat[2] = k;
      arrayOfFloat[3] = m;
      arrayOfFloat[4] = (n + i);
      arrayOfFloat[5] = (i1 + j);
      float f = paramPaint.getStrokeWidth();
      paramPaint.setStrokeWidth(5.0F);
      paramCanvas.drawLine(paramInt1, paramInt2, k, m, paramPaint);
      paramPaint.setStrokeWidth(f);
    }
    for (;;)
    {
      drawPath(paramCanvas, arrayOfFloat, paramPaint, true);
      return;
      arrayOfFloat = new float[6];
      arrayOfFloat[0] = (paramInt1 - i);
      arrayOfFloat[1] = (paramInt2 - j);
      arrayOfFloat[2] = k;
      arrayOfFloat[3] = m;
      arrayOfFloat[4] = (paramInt1 + i);
      arrayOfFloat[5] = (paramInt2 + j);
    }
  }
  
  private void drawTicks(Canvas paramCanvas, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2, double paramDouble5, double paramDouble6, double paramDouble7, Paint paramPaint, boolean paramBoolean)
  {
    for (double d1 = paramDouble1; d1 <= paramDouble2; d1 += paramDouble7)
    {
      double d3 = getAngleForValue(d1, paramDouble3, paramDouble4, paramDouble1, paramDouble2);
      double d2 = Math.sin(d3);
      d3 = Math.cos(d3);
      int i = Math.round(paramInt1 + (float)(paramDouble6 * d2));
      int j = Math.round(paramInt2 + (float)(paramDouble6 * d3));
      int k = Math.round(paramInt1 + (float)(paramDouble5 * d2));
      int m = Math.round(paramInt2 + (float)(paramDouble5 * d3));
      paramCanvas.drawLine(i, j, k, m, paramPaint);
      if (paramBoolean)
      {
        paramPaint.setTextAlign(Paint.Align.LEFT);
        if (i <= k) {
          paramPaint.setTextAlign(Paint.Align.RIGHT);
        }
        String str = d1 + "";
        if (Math.round(d1) == d1) {
          str = d1 + "";
        }
        paramCanvas.drawText(str, i, j, paramPaint);
      }
    }
  }
  
  private double getAngleForValue(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5)
  {
    return Math.toRadians((paramDouble1 - paramDouble4) * (paramDouble3 - paramDouble2) / (paramDouble5 - paramDouble4) + paramDouble2);
  }
  
  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, 0.0F);
    int k = paramInt1 + paramInt3;
    int m = this.mDataset.getItemCount();
    String[] arrayOfString = new String[m];
    int i = 0;
    while (i < m)
    {
      arrayOfString[i] = this.mDataset.getCategory(i);
      i += 1;
    }
    i = j;
    if (this.mRenderer.isFitLegend()) {
      i = drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, k, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
    }
    j = paramInt2 + paramInt4 - i;
    drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
    m = (int)(Math.min(Math.abs(k - paramInt1), Math.abs(j - paramInt2)) * 0.35D * this.mRenderer.getScale());
    if (this.mCenterX == Integer.MAX_VALUE) {
      this.mCenterX = ((paramInt1 + k) / 2);
    }
    if (this.mCenterY == Integer.MAX_VALUE) {
      this.mCenterY = ((j + paramInt2) / 2);
    }
    float f1 = m * 0.9F;
    float f2 = m * 1.1F;
    double d4 = this.mRenderer.getMinValue();
    double d1 = this.mRenderer.getMaxValue();
    double d6 = this.mRenderer.getAngleMin();
    double d7 = this.mRenderer.getAngleMax();
    if (this.mRenderer.isMinValueSet())
    {
      d3 = d4;
      d2 = d1;
      if (this.mRenderer.isMaxValueSet()) {}
    }
    else
    {
      int n = this.mRenderer.getSeriesRendererCount();
      j = 0;
      for (;;)
      {
        d3 = d4;
        d2 = d1;
        if (j >= n) {
          break;
        }
        d5 = this.mDataset.getValue(j);
        d2 = d4;
        if (!this.mRenderer.isMinValueSet()) {
          d2 = Math.min(d4, d5);
        }
        d3 = d1;
        if (!this.mRenderer.isMaxValueSet()) {
          d3 = Math.max(d1, d5);
        }
        j += 1;
        d4 = d2;
        d1 = d3;
      }
    }
    d4 = d3;
    d1 = d2;
    if (d3 == d2)
    {
      d4 = d3 * 0.5D;
      d1 = d2 * 1.5D;
    }
    paramPaint.setColor(this.mRenderer.getLabelsColor());
    double d3 = this.mRenderer.getMinorTicksSpacing();
    double d5 = this.mRenderer.getMajorTicksSpacing();
    double d2 = d3;
    if (d3 == Double.MAX_VALUE) {
      d2 = (d1 - d4) / 30.0D;
    }
    d3 = d5;
    if (d5 == Double.MAX_VALUE) {
      d3 = (d1 - d4) / 10.0D;
    }
    drawTicks(paramCanvas, d4, d1, d6, d7, this.mCenterX, this.mCenterY, f2, m, d2, paramPaint, false);
    drawTicks(paramCanvas, d4, d1, d6, d7, this.mCenterX, this.mCenterY, f2, f1, d3, paramPaint, true);
    m = this.mRenderer.getSeriesRendererCount();
    j = 0;
    if (j < m)
    {
      d2 = getAngleForValue(this.mDataset.getValue(j), d6, d7, d4, d1);
      paramPaint.setColor(this.mRenderer.getSeriesRendererAt(j).getColor());
      if (this.mRenderer.getVisualTypeForIndex(j) == DialRenderer.Type.ARROW) {}
      for (boolean bool = true;; bool = false)
      {
        drawNeedle(paramCanvas, d2, this.mCenterX, this.mCenterY, f1, bool, paramPaint);
        j += 1;
        break;
      }
    }
    drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, k, paramInt2, paramInt3, paramInt4, i, paramPaint, false);
    drawTitle(paramCanvas, paramInt1, paramInt2, paramInt3, paramPaint);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/DialChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */