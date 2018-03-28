package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.Point;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class PieChart
  extends RoundChart
{
  private PieMapper mPieMapper = new PieMapper();
  
  public PieChart(CategorySeries paramCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    super(paramCategorySeries, paramDefaultRenderer);
  }
  
  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, 0.0F);
    int m = paramInt1 + paramInt3;
    int n = this.mDataset.getItemCount();
    double d1 = 0.0D;
    String[] arrayOfString = new String[n];
    int i = 0;
    while (i < n)
    {
      d1 += this.mDataset.getValue(i);
      arrayOfString[i] = this.mDataset.getCategory(i);
      i += 1;
    }
    i = j;
    if (this.mRenderer.isFitLegend()) {
      i = drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, m, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
    }
    j = paramInt2 + paramInt4 - i;
    drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
    float f1 = this.mRenderer.getStartAngle();
    int i1 = (int)(Math.min(Math.abs(m - paramInt1), Math.abs(j - paramInt2)) * 0.35D * this.mRenderer.getScale());
    if (this.mCenterX == Integer.MAX_VALUE) {
      this.mCenterX = ((paramInt1 + m) / 2);
    }
    if (this.mCenterY == Integer.MAX_VALUE) {
      this.mCenterY = ((j + paramInt2) / 2);
    }
    this.mPieMapper.setDimensions(i1, this.mCenterX, this.mCenterY);
    float f2;
    float f3;
    RectF localRectF;
    ArrayList localArrayList;
    int k;
    label381:
    SimpleSeriesRenderer localSimpleSeriesRenderer;
    label445:
    float f4;
    float f5;
    if (!this.mPieMapper.areAllSegmentPresent(n))
    {
      j = 1;
      if (j != 0) {
        this.mPieMapper.clearPieSegments();
      }
      f2 = i1 * 0.9F;
      f3 = i1 * 1.1F;
      localRectF = new RectF(this.mCenterX - i1, this.mCenterY - i1, this.mCenterX + i1, this.mCenterY + i1);
      localArrayList = new ArrayList();
      k = 0;
      if (k >= n) {
        break label777;
      }
      localSimpleSeriesRenderer = this.mRenderer.getSeriesRendererAt(k);
      if (!localSimpleSeriesRenderer.isGradientEnabled()) {
        break label748;
      }
      paramPaint.setShader(new RadialGradient(this.mCenterX, this.mCenterY, f3, localSimpleSeriesRenderer.getGradientStartColor(), localSimpleSeriesRenderer.getGradientStopColor(), Shader.TileMode.MIRROR));
      f4 = (float)this.mDataset.getValue(k);
      f5 = (float)(f4 / d1 * 360.0D);
      if (!localSimpleSeriesRenderer.isHighlighted()) {
        break label761;
      }
      double d2 = Math.toRadians(90.0F - (f5 / 2.0F + f1));
      float f6 = (float)(i1 * 0.1D * Math.sin(d2));
      float f7 = (float)(i1 * 0.1D * Math.cos(d2));
      localRectF.offset(f6, f7);
      paramCanvas.drawArc(localRectF, f1, f5, true, paramPaint);
      localRectF.offset(-f6, -f7);
    }
    for (;;)
    {
      paramPaint.setColor(localSimpleSeriesRenderer.getColor());
      paramPaint.setShader(null);
      drawLabel(paramCanvas, this.mDataset.getCategory(k), this.mRenderer, localArrayList, this.mCenterX, this.mCenterY, f2, f3, f1, f5, paramInt1, m, this.mRenderer.getLabelsColor(), paramPaint, true, false);
      if (this.mRenderer.isDisplayValues()) {
        drawLabel(paramCanvas, getLabel(this.mRenderer.getSeriesRendererAt(k).getChartValuesFormat(), this.mDataset.getValue(k)), this.mRenderer, localArrayList, this.mCenterX, this.mCenterY, f2 / 2.0F, f3 / 2.0F, f1, f5, paramInt1, m, this.mRenderer.getLabelsColor(), paramPaint, false, true);
      }
      if (j != 0) {
        this.mPieMapper.addPieSegment(k, f4, f1, f5);
      }
      f1 += f5;
      k += 1;
      break label381;
      j = 0;
      break;
      label748:
      paramPaint.setColor(localSimpleSeriesRenderer.getColor());
      break label445;
      label761:
      paramCanvas.drawArc(localRectF, f1, f5, true, paramPaint);
    }
    label777:
    localArrayList.clear();
    drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, m, paramInt2, paramInt3, paramInt4, i, paramPaint, false);
    drawTitle(paramCanvas, paramInt1, paramInt2, paramInt3, paramPaint);
  }
  
  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    return this.mPieMapper.getSeriesAndPointForScreenCoordinate(paramPoint);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/PieChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */