package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class DoughnutChart
  extends RoundChart
{
  private MultipleCategorySeries mDataset;
  private int mStep;
  
  public DoughnutChart(MultipleCategorySeries paramMultipleCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    super(null, paramDefaultRenderer);
    this.mDataset = paramMultipleCategorySeries;
  }
  
  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, 0.0F);
    int n = paramInt1 + paramInt3;
    int i1 = this.mDataset.getCategoriesCount();
    String[] arrayOfString = new String[i1];
    int i = 0;
    while (i < i1)
    {
      arrayOfString[i] = this.mDataset.getCategory(i);
      i += 1;
    }
    i = j;
    if (this.mRenderer.isFitLegend()) {
      i = drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, n, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
    }
    j = paramInt2 + paramInt4 - i;
    drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
    this.mStep = 7;
    int i2 = Math.min(Math.abs(n - paramInt1), Math.abs(j - paramInt2));
    double d1 = this.mRenderer.getScale();
    double d2 = 0.2D / i1;
    int k = (int)(i2 * (0.35D * d1));
    if (this.mCenterX == Integer.MAX_VALUE) {
      this.mCenterX = ((paramInt1 + n) / 2);
    }
    if (this.mCenterY == Integer.MAX_VALUE) {
      this.mCenterY = ((j + paramInt2) / 2);
    }
    float f1 = k * 0.9F;
    float f3 = k;
    ArrayList localArrayList = new ArrayList();
    j = 0;
    if (j < i1)
    {
      int i3 = this.mDataset.getItemCount(j);
      d1 = 0.0D;
      Object localObject = new String[i3];
      int m = 0;
      while (m < i3)
      {
        d1 += this.mDataset.getValues(j)[m];
        localObject[m] = this.mDataset.getTitles(j)[m];
        m += 1;
      }
      float f2 = this.mRenderer.getStartAngle();
      localObject = new RectF(this.mCenterX - k, this.mCenterY - k, this.mCenterX + k, this.mCenterY + k);
      m = 0;
      while (m < i3)
      {
        paramPaint.setColor(this.mRenderer.getSeriesRendererAt(m).getColor());
        float f4 = (float)((float)this.mDataset.getValues(j)[m] / d1 * 360.0D);
        paramCanvas.drawArc((RectF)localObject, f2, f4, true, paramPaint);
        drawLabel(paramCanvas, this.mDataset.getTitles(j)[m], this.mRenderer, localArrayList, this.mCenterX, this.mCenterY, f1, f3 * 1.1F, f2, f4, paramInt1, n, this.mRenderer.getLabelsColor(), paramPaint, true, false);
        f2 += f4;
        m += 1;
      }
      k = (int)(k - i2 * d2);
      f1 = (float)(f1 - (i2 * d2 - 2.0D));
      if (this.mRenderer.getBackgroundColor() != 0) {
        paramPaint.setColor(this.mRenderer.getBackgroundColor());
      }
      for (;;)
      {
        paramPaint.setStyle(Paint.Style.FILL);
        paramCanvas.drawArc(new RectF(this.mCenterX - k, this.mCenterY - k, this.mCenterX + k, this.mCenterY + k), 0.0F, 360.0F, true, paramPaint);
        k -= 1;
        j += 1;
        break;
        paramPaint.setColor(-1);
      }
    }
    localArrayList.clear();
    drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, n, paramInt2, paramInt3, paramInt4, i, paramPaint, false);
    drawTitle(paramCanvas, paramInt1, paramInt2, paramInt3, paramPaint);
  }
  
  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    this.mStep -= 1;
    paramCanvas.drawCircle(10.0F + paramFloat1 - this.mStep, paramFloat2, this.mStep, paramPaint);
  }
  
  public int getLegendShapeWidth(int paramInt)
  {
    return 10;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/DoughnutChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */