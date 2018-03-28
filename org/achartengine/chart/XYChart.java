package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import org.achartengine.model.Point;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.MathHelper;

public abstract class XYChart
  extends AbstractChart
{
  private Map<Integer, List<ClickableArea>> clickableAreas = new HashMap();
  private final Map<Integer, double[]> mCalcRange = new HashMap();
  private Point mCenter;
  protected XYMultipleSeriesDataset mDataset;
  protected transient Paint mGridPaint;
  protected XYMultipleSeriesRenderer mRenderer;
  private float mScale;
  private Rect mScreenR;
  private float mTranslate;
  
  protected XYChart() {}
  
  public XYChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    this.mDataset = paramXYMultipleSeriesDataset;
    this.mRenderer = paramXYMultipleSeriesRenderer;
  }
  
  private int getLabelLinePos(Paint.Align paramAlign)
  {
    int i = 4;
    if (paramAlign == Paint.Align.LEFT) {
      i = -4;
    }
    return i;
  }
  
  private List<Double> getValidLabels(List<Double> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Double localDouble = (Double)paramList.next();
      if (localDouble.isNaN()) {
        localArrayList.remove(localDouble);
      }
    }
    return localArrayList;
  }
  
  private void setStroke(Paint.Cap paramCap, Paint.Join paramJoin, float paramFloat, Paint.Style paramStyle, PathEffect paramPathEffect, Paint paramPaint)
  {
    paramPaint.setStrokeCap(paramCap);
    paramPaint.setStrokeJoin(paramJoin);
    paramPaint.setStrokeMiter(paramFloat);
    paramPaint.setPathEffect(paramPathEffect);
    paramPaint.setStyle(paramStyle);
  }
  
  private void transform(Canvas paramCanvas, float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramCanvas.scale(1.0F / this.mScale, this.mScale);
      paramCanvas.translate(this.mTranslate, -this.mTranslate);
      paramCanvas.rotate(-paramFloat, this.mCenter.getX(), this.mCenter.getY());
      return;
    }
    paramCanvas.rotate(paramFloat, this.mCenter.getX(), this.mCenter.getY());
    paramCanvas.translate(-this.mTranslate, this.mTranslate);
    paramCanvas.scale(this.mScale, 1.0F / this.mScale);
  }
  
  protected abstract ClickableArea[] clickableAreasForPoints(List<Float> paramList, List<Double> paramList1, float paramFloat, int paramInt1, int paramInt2);
  
  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, this.mRenderer.getAxisTitleTextSize());
    Object localObject4 = this.mRenderer.getMargins();
    int i4 = paramInt1 + localObject4[1];
    int i5 = paramInt2 + localObject4[0];
    int k = paramInt1 + paramInt3 - localObject4[3];
    int i7 = this.mDataset.getSeriesCount();
    String[] arrayOfString = new String[i7];
    int i = 0;
    while (i < i7)
    {
      arrayOfString[i] = this.mDataset.getSeriesAt(i).getTitle();
      i += 1;
    }
    int n = j;
    if (this.mRenderer.isFitLegend())
    {
      n = j;
      if (this.mRenderer.isShowLegend()) {
        n = drawLegend(paramCanvas, this.mRenderer, arrayOfString, i4, k, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
      }
    }
    int m = paramInt2 + paramInt4 - localObject4[2] - n;
    if (this.mScreenR == null) {
      this.mScreenR = new Rect();
    }
    this.mScreenR.set(i4, i5, k, m);
    drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
    XYMultipleSeriesRenderer.Orientation localOrientation;
    int i6;
    if ((paramPaint.getTypeface() == null) || ((this.mRenderer.getTextTypeface() != null) && (paramPaint.getTypeface().equals(this.mRenderer.getTextTypeface()))) || (!paramPaint.getTypeface().toString().equals(this.mRenderer.getTextTypefaceName())) || (paramPaint.getTypeface().getStyle() != this.mRenderer.getTextTypefaceStyle()))
    {
      if (this.mRenderer.getTextTypeface() != null) {
        paramPaint.setTypeface(this.mRenderer.getTextTypeface());
      }
    }
    else
    {
      localOrientation = this.mRenderer.getOrientation();
      if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL) {
        break label3711;
      }
      k -= n;
      m += n - 20;
      i6 = localOrientation.getAngle();
      if (i6 != 90) {
        break label545;
      }
    }
    label545:
    for (int i1 = 1;; i1 = 0)
    {
      this.mScale = (paramInt4 / paramInt3);
      this.mTranslate = (Math.abs(paramInt3 - paramInt4) / 2);
      if (this.mScale < 1.0F) {
        this.mTranslate *= -1.0F;
      }
      this.mCenter = new Point((paramInt1 + paramInt3) / 2, (paramInt2 + paramInt4) / 2);
      if (i1 != 0) {
        transform(paramCanvas, i6, false);
      }
      j = -2147483647;
      i = 0;
      while (i < i7)
      {
        j = Math.max(j, this.mDataset.getSeriesAt(i).getScaleNumber());
        i += 1;
      }
      paramPaint.setTypeface(Typeface.create(this.mRenderer.getTextTypefaceName(), this.mRenderer.getTextTypefaceStyle()));
      break;
    }
    int i8 = j + 1;
    if (i8 < 0) {
      label562:
      return;
    }
    Object localObject1 = new double[i8];
    Object localObject2 = new double[i8];
    double[] arrayOfDouble1 = new double[i8];
    double[] arrayOfDouble2 = new double[i8];
    Object localObject5 = new boolean[i8];
    Object localObject6 = new boolean[i8];
    Object localObject7 = new boolean[i8];
    Object localObject8 = new boolean[i8];
    i = 0;
    while (i < i8)
    {
      localObject1[i] = this.mRenderer.getXAxisMin(i);
      localObject2[i] = this.mRenderer.getXAxisMax(i);
      arrayOfDouble1[i] = this.mRenderer.getYAxisMin(i);
      arrayOfDouble2[i] = this.mRenderer.getYAxisMax(i);
      localObject5[i] = this.mRenderer.isMinXSet(i);
      localObject6[i] = this.mRenderer.isMaxXSet(i);
      localObject7[i] = this.mRenderer.isMinYSet(i);
      localObject8[i] = this.mRenderer.isMaxYSet(i);
      if (this.mCalcRange.get(Integer.valueOf(i)) == null) {
        this.mCalcRange.put(Integer.valueOf(i), new double[4]);
      }
      i += 1;
    }
    Object localObject3 = new double[i8];
    double[] arrayOfDouble3 = new double[i8];
    i = 0;
    double d1;
    if (i < i7)
    {
      localObject9 = this.mDataset.getSeriesAt(i);
      j = ((XYSeries)localObject9).getScaleNumber();
      if (((XYSeries)localObject9).getItemCount() == 0) {}
      for (;;)
      {
        i += 1;
        break;
        if (localObject5[j] == 0)
        {
          d1 = ((XYSeries)localObject9).getMinX();
          localObject1[j] = Math.min(localObject1[j], d1);
          ((double[])this.mCalcRange.get(Integer.valueOf(j)))[0] = localObject1[j];
        }
        if (localObject6[j] == 0)
        {
          d1 = ((XYSeries)localObject9).getMaxX();
          localObject2[j] = Math.max(localObject2[j], d1);
          ((double[])this.mCalcRange.get(Integer.valueOf(j)))[1] = localObject2[j];
        }
        if (localObject7[j] == 0)
        {
          d1 = ((XYSeries)localObject9).getMinY();
          arrayOfDouble1[j] = Math.min(arrayOfDouble1[j], (float)d1);
          ((double[])this.mCalcRange.get(Integer.valueOf(j)))[2] = arrayOfDouble1[j];
        }
        if (localObject8[j] == 0)
        {
          d1 = ((XYSeries)localObject9).getMaxY();
          arrayOfDouble2[j] = Math.max(arrayOfDouble2[j], (float)d1);
          ((double[])this.mCalcRange.get(Integer.valueOf(j)))[3] = arrayOfDouble2[j];
        }
      }
    }
    i = 0;
    while (i < i8)
    {
      if (localObject2[i] - localObject1[i] != 0.0D) {
        localObject3[i] = ((k - i4) / (localObject2[i] - localObject1[i]));
      }
      if (arrayOfDouble2[i] - arrayOfDouble1[i] != 0.0D) {
        arrayOfDouble3[i] = ((float)((m - i5) / (arrayOfDouble2[i] - arrayOfDouble1[i])));
      }
      if (i > 0)
      {
        localObject3[i] = localObject3[0];
        localObject1[i] = localObject1[0];
        localObject2[i] = localObject2[0];
      }
      i += 1;
    }
    j = 0;
    i = 0;
    if (i < i7)
    {
      if (this.mDataset.getSeriesAt(i).getItemCount() == 0) {}
      for (;;)
      {
        i += 1;
        break;
        j = 1;
      }
    }
    if ((this.mRenderer.isShowLabels()) && (j != 0)) {}
    boolean bool1;
    boolean bool2;
    boolean bool3;
    boolean bool4;
    int i3;
    int i9;
    for (int i2 = 1;; i2 = 0)
    {
      bool1 = this.mRenderer.isShowGridX();
      bool2 = this.mRenderer.isShowGridY();
      if ((bool1) || (bool2))
      {
        localObject5 = getValidLabels(getXLabels(localObject1[0], localObject2[0], this.mRenderer.getXLabels()));
        localObject6 = getYLabels(arrayOfDouble1, arrayOfDouble2, i8);
        bool3 = this.mRenderer.isShowXLabels();
        bool4 = this.mRenderer.isShowYLabels();
        this.mRenderer.setShowLabels(false);
        if (this.mGridPaint == null) {
          this.mGridPaint = new Paint(1);
        }
        drawXLabels((List)localObject5, this.mRenderer.getXTextLabelLocations(), paramCanvas, paramPaint, i4, i5, m, localObject3[0], localObject1[0], localObject2[0]);
        drawYLabels((Map)localObject6, paramCanvas, paramPaint, i8, i4, k, m, arrayOfDouble3, arrayOfDouble1);
        this.mRenderer.setShowLabels(bool3, bool4);
      }
      this.clickableAreas = new HashMap();
      i3 = 0;
      for (;;)
      {
        if (i3 >= i7) {
          break label2215;
        }
        localObject5 = this.mDataset.getSeriesAt(i3);
        i9 = ((XYSeries)localObject5).getScaleNumber();
        if (((XYSeries)localObject5).getItemCount() != 0) {
          break;
        }
        label1472:
        i3 += 1;
      }
    }
    localObject6 = (XYSeriesRenderer)this.mRenderer.getSeriesRendererAt(i3);
    localObject7 = new ArrayList();
    localObject8 = new ArrayList();
    float f1 = Math.min(m, (float)(m + arrayOfDouble3[i9] * arrayOfDouble1[i9]));
    Object localObject9 = new LinkedList();
    this.clickableAreas.put(Integer.valueOf(i3), localObject9);
    Object localObject10;
    for (;;)
    {
      try
      {
        localObject10 = ((XYSeries)localObject5).getRange(localObject1[i9], localObject2[i9], ((XYSeriesRenderer)localObject6).isDisplayBoundingPoints());
        i = -1;
        localObject10 = ((SortedMap)localObject10).entrySet().iterator();
        if (!((Iterator)localObject10).hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject10).next();
        d1 = ((Double)localEntry.getKey()).doubleValue();
        double d2 = ((Double)localEntry.getValue()).doubleValue();
        j = i;
        if (i < 0) {
          if (isNullValue(d2))
          {
            j = i;
            if (!isRenderNullValues()) {}
          }
          else
          {
            j = ((XYSeries)localObject5).getIndexForKey(d1);
          }
        }
        ((List)localObject8).add(localEntry.getKey());
        ((List)localObject8).add(localEntry.getValue());
        if (!isNullValue(d2))
        {
          ((List)localObject7).add(Float.valueOf((float)(i4 + localObject3[i9] * (d1 - localObject1[i9]))));
          ((List)localObject7).add(Float.valueOf((float)(m - arrayOfDouble3[i9] * (d2 - arrayOfDouble1[i9]))));
          i = j;
          continue;
        }
        if (!isRenderNullValues()) {
          break label1885;
        }
      }
      finally {}
      ((List)localObject7).add(Float.valueOf((float)(i4 + localObject3[i9] * (d1 - localObject1[i9]))));
      ((List)localObject7).add(Float.valueOf((float)(m - arrayOfDouble3[i9] * -arrayOfDouble1[i9])));
      i = j;
      continue;
      label1885:
      i = j;
      if (((List)localObject7).size() > 0)
      {
        drawSeries((XYSeries)localObject5, paramCanvas, paramPaint, (List)localObject7, (XYSeriesRenderer)localObject6, f1, i3, localOrientation, j);
        ((LinkedList)localObject9).addAll(Arrays.asList(clickableAreasForPoints((List)localObject7, (List)localObject8, f1, i3, j)));
        ((List)localObject7).clear();
        ((List)localObject8).clear();
        i = -1;
      }
      ((LinkedList)localObject9).add(null);
    }
    int i10 = ((XYSeries)localObject5).getAnnotationCount();
    if (i10 > 0)
    {
      paramPaint.setColor(((XYSeriesRenderer)localObject6).getAnnotationsColor());
      paramPaint.setTextSize(((XYSeriesRenderer)localObject6).getAnnotationsTextSize());
      paramPaint.setTextAlign(((XYSeriesRenderer)localObject6).getAnnotationsTextAlign());
      localObject10 = new Rect();
      j = 0;
    }
    for (;;)
    {
      if (j < i10)
      {
        float f2 = (float)(i4 + localObject3[i9] * (((XYSeries)localObject5).getAnnotationX(j) - localObject1[i9]));
        float f3 = (float)(m - arrayOfDouble3[i9] * (((XYSeries)localObject5).getAnnotationY(j) - arrayOfDouble1[i9]));
        paramPaint.getTextBounds(((XYSeries)localObject5).getAnnotationAt(j), 0, ((XYSeries)localObject5).getAnnotationAt(j).length(), (Rect)localObject10);
        if ((f2 < ((Rect)localObject10).width() + f2) && (f3 < paramCanvas.getHeight())) {
          drawString(paramCanvas, ((XYSeries)localObject5).getAnnotationAt(j), f2, f3, paramPaint);
        }
      }
      else
      {
        if (((List)localObject7).size() > 0)
        {
          drawSeries((XYSeries)localObject5, paramCanvas, paramPaint, (List)localObject7, (XYSeriesRenderer)localObject6, f1, i3, localOrientation, i);
          ((LinkedList)localObject9).addAll(Arrays.asList(clickableAreasForPoints((List)localObject7, (List)localObject8, f1, i3, i)));
        }
        break label1472;
        label2215:
        drawBackground(this.mRenderer, paramCanvas, paramInt1, m, paramInt3, paramInt4 - m, paramPaint, true, this.mRenderer.getMarginsColor());
        drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, localObject4[0], paramPaint, true, this.mRenderer.getMarginsColor());
        if (localOrientation == XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
        {
          drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, i4 - paramInt1, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
          drawBackground(this.mRenderer, paramCanvas, k, paramInt2, localObject4[3], paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
          bool3 = this.mRenderer.isShowTickMarks();
          bool4 = this.mRenderer.isShowCustomTextGridY();
          if (i2 == 0) {
            break label3299;
          }
          localObject4 = getValidLabels(getXLabels(localObject1[0], localObject2[0], this.mRenderer.getXLabels()));
          localObject5 = getYLabels(arrayOfDouble1, arrayOfDouble2, i8);
          if (i2 != 0)
          {
            paramPaint.setColor(this.mRenderer.getXLabelsColor());
            paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
            paramPaint.setTextAlign(this.mRenderer.getXLabelsAlign());
          }
          this.mRenderer.setShowGrid(false);
          drawXLabels((List)localObject4, this.mRenderer.getXTextLabelLocations(), paramCanvas, paramPaint, i4, i5, m, localObject3[0], localObject1[0], localObject2[0]);
          drawYLabels((Map)localObject5, paramCanvas, paramPaint, i8, i4, k, m, arrayOfDouble3, arrayOfDouble1);
          this.mRenderer.setShowGridX(bool1);
          this.mRenderer.setShowGridY(bool2);
          if (i2 != 0)
          {
            paramPaint.setColor(this.mRenderer.getLabelsColor());
            i = 0;
          }
        }
        else
        {
          for (;;)
          {
            if (i >= i8) {
              break label3055;
            }
            localObject1 = this.mRenderer.getYAxisAlign(i);
            localObject2 = this.mRenderer.getYTextLabelLocations(i);
            i3 = localObject2.length;
            j = 0;
            label2577:
            if (j < i3)
            {
              localObject3 = localObject2[j];
              if ((arrayOfDouble1[i] <= ((Double)localObject3).doubleValue()) && (((Double)localObject3).doubleValue() <= arrayOfDouble2[i]))
              {
                f1 = (float)(m - arrayOfDouble3[i] * (((Double)localObject3).doubleValue() - arrayOfDouble1[i]));
                localObject3 = this.mRenderer.getYTextLabel((Double)localObject3, i);
                paramPaint.setColor(this.mRenderer.getYLabelsColor(i));
                paramPaint.setTextAlign(this.mRenderer.getYLabelsAlign(i));
                if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL) {
                  break label2948;
                }
                if (localObject1 != Paint.Align.LEFT) {
                  break label2880;
                }
                if (bool3) {
                  paramCanvas.drawLine(getLabelLinePos((Paint.Align)localObject1) + i4, f1, i4, f1, paramPaint);
                }
                drawText(paramCanvas, (String)localObject3, i4 - this.mRenderer.getYLabelsPadding(), f1 - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
                label2765:
                if (bool4)
                {
                  paramPaint.setColor(this.mRenderer.getGridColor(i));
                  paramCanvas.drawLine(i4, f1, k, f1, paramPaint);
                }
              }
              for (;;)
              {
                j += 1;
                break label2577;
                if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL) {
                  break;
                }
                drawBackground(this.mRenderer, paramCanvas, k, paramInt2, paramInt3 - k, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
                drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, i4 - paramInt1, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
                break;
                label2880:
                if (bool3) {
                  paramCanvas.drawLine(k, f1, getLabelLinePos((Paint.Align)localObject1) + k, f1, paramPaint);
                }
                drawText(paramCanvas, (String)localObject3, k - this.mRenderer.getYLabelsPadding(), f1 - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
                break label2765;
                label2948:
                if (bool3) {
                  paramCanvas.drawLine(k - getLabelLinePos((Paint.Align)localObject1), f1, k, f1, paramPaint);
                }
                drawText(paramCanvas, (String)localObject3, k + 10, f1 - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
                if (bool4)
                {
                  paramPaint.setColor(this.mRenderer.getGridColor(i));
                  paramCanvas.drawLine(k, f1, i4, f1, paramPaint);
                }
              }
            }
            i += 1;
          }
        }
        label3055:
        if (i2 != 0)
        {
          paramPaint.setColor(this.mRenderer.getLabelsColor());
          f1 = this.mRenderer.getAxisTitleTextSize();
          paramPaint.setTextSize(f1);
          paramPaint.setTextAlign(Paint.Align.CENTER);
          if (localOrientation == XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
          {
            drawText(paramCanvas, this.mRenderer.getXTitle(), paramInt3 / 2 + paramInt1, m + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F + this.mRenderer.getXLabelsPadding() + f1, paramPaint, 0.0F);
            i = 0;
            if (i < i8)
            {
              if (this.mRenderer.getYAxisAlign(i) == Paint.Align.LEFT) {
                drawText(paramCanvas, this.mRenderer.getYTitle(i), paramInt1 + f1, paramInt4 / 2 + paramInt2, paramPaint, -90.0F);
              }
              for (;;)
              {
                i += 1;
                break;
                drawText(paramCanvas, this.mRenderer.getYTitle(i), paramInt1 + paramInt3, paramInt4 / 2 + paramInt2, paramPaint, -90.0F);
              }
            }
            paramPaint.setTextSize(this.mRenderer.getChartTitleTextSize());
            drawText(paramCanvas, this.mRenderer.getChartTitle(), paramInt3 / 2 + paramInt1, paramInt2 + this.mRenderer.getChartTitleTextSize(), paramPaint, 0.0F);
          }
        }
        else
        {
          label3299:
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL) {
            break label3550;
          }
          drawLegend(paramCanvas, this.mRenderer, arrayOfString, i4, k, paramInt2 + (int)this.mRenderer.getXLabelsPadding(), paramInt3, paramInt4, n, paramPaint, false);
          label3342:
          if (!this.mRenderer.isShowAxes()) {
            break label3667;
          }
          paramPaint.setColor(this.mRenderer.getXAxisColor());
          paramCanvas.drawLine(i4, m, k, m, paramPaint);
          paramPaint.setColor(this.mRenderer.getYAxisColor());
          paramInt1 = 0;
          paramInt2 = 0;
          label3398:
          if ((paramInt2 >= i8) || (paramInt1 != 0)) {
            break label3619;
          }
          if (this.mRenderer.getYAxisAlign(paramInt2) != Paint.Align.RIGHT) {
            break label3614;
          }
        }
        label3550:
        label3614:
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramInt2 += 1;
          break label3398;
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL) {
            break;
          }
          drawText(paramCanvas, this.mRenderer.getXTitle(), paramInt3 / 2 + paramInt1, paramInt2 + paramInt4 - f1 + this.mRenderer.getXLabelsPadding(), paramPaint, -90.0F);
          drawText(paramCanvas, this.mRenderer.getYTitle(), k + 20, paramInt4 / 2 + paramInt2, paramPaint, 0.0F);
          paramPaint.setTextSize(this.mRenderer.getChartTitleTextSize());
          drawText(paramCanvas, this.mRenderer.getChartTitle(), paramInt1 + f1, paramInt4 / 2 + i5, paramPaint, 0.0F);
          break;
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL) {
            break label3342;
          }
          transform(paramCanvas, i6, true);
          drawLegend(paramCanvas, this.mRenderer, arrayOfString, i4, k, paramInt2 + (int)this.mRenderer.getXLabelsPadding(), paramInt3, paramInt4, n, paramPaint, false);
          transform(paramCanvas, i6, false);
          break label3342;
        }
        label3619:
        if (localOrientation == XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
        {
          paramCanvas.drawLine(i4, i5, i4, m, paramPaint);
          if (paramInt1 != 0) {
            paramCanvas.drawLine(k, i5, k, m, paramPaint);
          }
        }
        label3667:
        while (i1 != 0)
        {
          transform(paramCanvas, i6, true);
          return;
          if (localOrientation == XYMultipleSeriesRenderer.Orientation.VERTICAL) {
            paramCanvas.drawLine(k, i5, k, m, paramPaint);
          }
        }
        break label562;
        label3711:
        break;
      }
      j += 1;
    }
  }
  
  protected void drawChartValuesText(Canvas paramCanvas, XYSeries paramXYSeries, XYSeriesRenderer paramXYSeriesRenderer, Paint paramPaint, List<Float> paramList, int paramInt1, int paramInt2)
  {
    if (paramList.size() > 2)
    {
      float f4 = ((Float)paramList.get(0)).floatValue();
      float f3 = ((Float)paramList.get(1)).floatValue();
      paramInt1 = 0;
      if (paramInt1 < paramList.size())
      {
        float f1;
        float f2;
        if (paramInt1 == 2) {
          if (Math.abs(((Float)paramList.get(2)).floatValue() - ((Float)paramList.get(0)).floatValue()) <= paramXYSeriesRenderer.getDisplayChartValuesDistance())
          {
            f1 = f4;
            f2 = f3;
            if (Math.abs(((Float)paramList.get(3)).floatValue() - ((Float)paramList.get(1)).floatValue()) <= paramXYSeriesRenderer.getDisplayChartValuesDistance()) {}
          }
          else
          {
            drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(paramInt2)), ((Float)paramList.get(0)).floatValue(), ((Float)paramList.get(1)).floatValue() - paramXYSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
            drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(paramInt2 + 1)), ((Float)paramList.get(2)).floatValue(), ((Float)paramList.get(3)).floatValue() - paramXYSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
            f1 = ((Float)paramList.get(2)).floatValue();
            f2 = ((Float)paramList.get(3)).floatValue();
          }
        }
        for (;;)
        {
          paramInt1 += 2;
          f4 = f1;
          f3 = f2;
          break;
          f1 = f4;
          f2 = f3;
          if (paramInt1 > 2) {
            if (Math.abs(((Float)paramList.get(paramInt1)).floatValue() - f4) <= paramXYSeriesRenderer.getDisplayChartValuesDistance())
            {
              f1 = f4;
              f2 = f3;
              if (Math.abs(((Float)paramList.get(paramInt1 + 1)).floatValue() - f3) <= paramXYSeriesRenderer.getDisplayChartValuesDistance()) {}
            }
            else
            {
              drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(paramInt1 / 2 + paramInt2)), ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue() - paramXYSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
              f1 = ((Float)paramList.get(paramInt1)).floatValue();
              f2 = ((Float)paramList.get(paramInt1 + 1)).floatValue();
            }
          }
        }
      }
    }
    else
    {
      paramInt1 = 0;
      while (paramInt1 < paramList.size())
      {
        drawText(paramCanvas, getLabel(paramXYSeriesRenderer.getChartValuesFormat(), paramXYSeries.getY(paramInt1 / 2 + paramInt2)), ((Float)paramList.get(paramInt1)).floatValue(), ((Float)paramList.get(paramInt1 + 1)).floatValue() - paramXYSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
        paramInt1 += 2;
      }
    }
  }
  
  protected void drawPoints(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    if (isRenderPoints(paramXYSeriesRenderer))
    {
      ScatterChart localScatterChart = getPointsChart();
      if (localScatterChart != null) {
        localScatterChart.drawSeries(paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, paramInt1, paramInt2);
      }
    }
  }
  
  public abstract void drawSeries(Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, int paramInt2);
  
  protected void drawSeries(XYSeries paramXYSeries, Canvas paramCanvas, Paint paramPaint, List<Float> paramList, XYSeriesRenderer paramXYSeriesRenderer, float paramFloat, int paramInt1, XYMultipleSeriesRenderer.Orientation paramOrientation, int paramInt2)
  {
    BasicStroke localBasicStroke = paramXYSeriesRenderer.getStroke();
    Paint.Cap localCap = paramPaint.getStrokeCap();
    Paint.Join localJoin = paramPaint.getStrokeJoin();
    float f = paramPaint.getStrokeMiter();
    PathEffect localPathEffect = paramPaint.getPathEffect();
    Paint.Style localStyle = paramPaint.getStyle();
    if (localBasicStroke != null)
    {
      DashPathEffect localDashPathEffect = null;
      if (localBasicStroke.getIntervals() != null) {
        localDashPathEffect = new DashPathEffect(localBasicStroke.getIntervals(), localBasicStroke.getPhase());
      }
      setStroke(localBasicStroke.getCap(), localBasicStroke.getJoin(), localBasicStroke.getMiter(), Paint.Style.FILL_AND_STROKE, localDashPathEffect, paramPaint);
    }
    drawSeries(paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, paramInt1, paramInt2);
    drawPoints(paramCanvas, paramPaint, paramList, paramXYSeriesRenderer, paramFloat, paramInt1, paramInt2);
    paramPaint.setTextSize(paramXYSeriesRenderer.getChartValuesTextSize());
    if (paramOrientation == XYMultipleSeriesRenderer.Orientation.HORIZONTAL) {
      paramPaint.setTextAlign(Paint.Align.CENTER);
    }
    for (;;)
    {
      if (paramXYSeriesRenderer.isDisplayChartValues())
      {
        paramPaint.setTextAlign(paramXYSeriesRenderer.getChartValuesTextAlign());
        drawChartValuesText(paramCanvas, paramXYSeries, paramXYSeriesRenderer, paramPaint, paramList, paramInt1, paramInt2);
      }
      if (localBasicStroke != null) {
        setStroke(localCap, localJoin, f, localStyle, localPathEffect, paramPaint);
      }
      return;
      paramPaint.setTextAlign(Paint.Align.LEFT);
    }
  }
  
  protected void drawText(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, Paint paramPaint, float paramFloat3)
  {
    paramFloat3 = -this.mRenderer.getOrientation().getAngle() + paramFloat3;
    if (paramFloat3 != 0.0F) {
      paramCanvas.rotate(paramFloat3, paramFloat1, paramFloat2);
    }
    drawString(paramCanvas, paramString, paramFloat1, paramFloat2, paramPaint);
    if (paramFloat3 != 0.0F) {
      paramCanvas.rotate(-paramFloat3, paramFloat1, paramFloat2);
    }
  }
  
  protected void drawXLabels(List<Double> paramList, Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    int j = paramList.size();
    boolean bool1 = this.mRenderer.isShowXLabels();
    boolean bool2 = this.mRenderer.isShowGridY();
    if (bool2)
    {
      this.mGridPaint.setStyle(Paint.Style.STROKE);
      this.mGridPaint.setStrokeWidth(this.mRenderer.getGridLineWidth());
    }
    boolean bool3 = this.mRenderer.isShowTickMarks();
    int i = 0;
    while (i < j)
    {
      double d = ((Double)paramList.get(i)).doubleValue();
      float f = (float)(paramInt1 + (d - paramDouble2) * paramDouble1);
      if (bool1)
      {
        paramPaint.setColor(this.mRenderer.getXLabelsColor());
        if (bool3) {
          paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
        }
        drawText(paramCanvas, getLabel(this.mRenderer.getXLabelFormat(), d), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F + this.mRenderer.getXLabelsPadding(), paramPaint, this.mRenderer.getXLabelsAngle());
      }
      if (bool2)
      {
        this.mGridPaint.setColor(this.mRenderer.getGridColor(0));
        paramCanvas.drawLine(f, paramInt3, f, paramInt2, this.mGridPaint);
      }
      i += 1;
    }
    drawXTextLabels(paramArrayOfDouble, paramCanvas, paramPaint, bool1, paramInt1, paramInt2, paramInt3, paramDouble1, paramDouble2, paramDouble3);
  }
  
  protected void drawXTextLabels(Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    boolean bool1 = this.mRenderer.isShowCustomTextGridX();
    boolean bool2 = this.mRenderer.isShowTickMarks();
    if (paramBoolean)
    {
      paramPaint.setColor(this.mRenderer.getXLabelsColor());
      int j = paramArrayOfDouble.length;
      int i = 0;
      while (i < j)
      {
        Double localDouble = paramArrayOfDouble[i];
        if ((paramDouble2 <= localDouble.doubleValue()) && (localDouble.doubleValue() <= paramDouble3))
        {
          float f = (float)(paramInt1 + (localDouble.doubleValue() - paramDouble2) * paramDouble1);
          paramPaint.setColor(this.mRenderer.getXLabelsColor());
          if (bool2) {
            paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
          }
          drawText(paramCanvas, this.mRenderer.getXTextLabel(localDouble), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F + this.mRenderer.getXLabelsPadding(), paramPaint, this.mRenderer.getXLabelsAngle());
          if (bool1)
          {
            paramPaint.setColor(this.mRenderer.getGridColor(0));
            paramCanvas.drawLine(f, paramInt3, f, paramInt2, paramPaint);
          }
        }
        i += 1;
      }
    }
  }
  
  protected void drawYLabels(Map<Integer, List<Double>> paramMap, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    XYMultipleSeriesRenderer.Orientation localOrientation = this.mRenderer.getOrientation();
    boolean bool1 = this.mRenderer.isShowGridX();
    if (bool1)
    {
      this.mGridPaint.setStyle(Paint.Style.STROKE);
      this.mGridPaint.setStrokeWidth(this.mRenderer.getGridLineWidth());
    }
    boolean bool2 = this.mRenderer.isShowYLabels();
    boolean bool3 = this.mRenderer.isShowTickMarks();
    int i = 0;
    while (i < paramInt1)
    {
      paramPaint.setTextAlign(this.mRenderer.getYLabelsAlign(i));
      List localList = (List)paramMap.get(Integer.valueOf(i));
      int m = localList.size();
      int j = 0;
      if (j < m)
      {
        double d = ((Double)localList.get(j)).doubleValue();
        Paint.Align localAlign = this.mRenderer.getYAxisAlign(i);
        int k;
        label171:
        float f;
        if (this.mRenderer.getYTextLabel(Double.valueOf(d), i) != null)
        {
          k = 1;
          f = (float)(paramInt4 - paramArrayOfDouble1[i] * (d - paramArrayOfDouble2[i]));
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL) {
            break label440;
          }
          if ((bool2) && (k == 0))
          {
            paramPaint.setColor(this.mRenderer.getYLabelsColor(i));
            if (localAlign != Paint.Align.LEFT) {
              break label361;
            }
            if (bool3) {
              paramCanvas.drawLine(getLabelLinePos(localAlign) + paramInt2, f, paramInt2, f, paramPaint);
            }
            drawText(paramCanvas, getLabel(this.mRenderer.getYLabelFormat(i), d), paramInt2 - this.mRenderer.getYLabelsPadding(), f - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
          }
          label307:
          if (bool1)
          {
            this.mGridPaint.setColor(this.mRenderer.getGridColor(i));
            paramCanvas.drawLine(paramInt2, f, paramInt3, f, this.mGridPaint);
          }
        }
        for (;;)
        {
          j += 1;
          break;
          k = 0;
          break label171;
          label361:
          if (bool3) {
            paramCanvas.drawLine(paramInt3, f, getLabelLinePos(localAlign) + paramInt3, f, paramPaint);
          }
          drawText(paramCanvas, getLabel(this.mRenderer.getYLabelFormat(i), d), paramInt3 + this.mRenderer.getYLabelsPadding(), f - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
          break label307;
          label440:
          if (localOrientation == XYMultipleSeriesRenderer.Orientation.VERTICAL)
          {
            if ((bool2) && (k == 0))
            {
              paramPaint.setColor(this.mRenderer.getYLabelsColor(i));
              if (bool3) {
                paramCanvas.drawLine(paramInt3 - getLabelLinePos(localAlign), f, paramInt3, f, paramPaint);
              }
              drawText(paramCanvas, getLabel(this.mRenderer.getLabelFormat(), d), paramInt3 + 10 + this.mRenderer.getYLabelsPadding(), f - this.mRenderer.getYLabelsVerticalPadding(), paramPaint, this.mRenderer.getYLabelsAngle());
            }
            if (bool1)
            {
              this.mGridPaint.setColor(this.mRenderer.getGridColor(i));
              if (bool3) {
                paramCanvas.drawLine(paramInt3, f, paramInt2, f, this.mGridPaint);
              }
            }
          }
        }
      }
      i += 1;
    }
  }
  
  public double[] getCalcRange(int paramInt)
  {
    return (double[])this.mCalcRange.get(Integer.valueOf(paramInt));
  }
  
  public abstract String getChartType();
  
  public XYMultipleSeriesDataset getDataset()
  {
    return this.mDataset;
  }
  
  public double getDefaultMinimum()
  {
    return Double.MAX_VALUE;
  }
  
  public ScatterChart getPointsChart()
  {
    return null;
  }
  
  public XYMultipleSeriesRenderer getRenderer()
  {
    return this.mRenderer;
  }
  
  protected Rect getScreenR()
  {
    return this.mScreenR;
  }
  
  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    if (this.clickableAreas != null)
    {
      int i = this.clickableAreas.size() - 1;
      while (i >= 0)
      {
        int j = 0;
        if (this.clickableAreas.get(Integer.valueOf(i)) != null)
        {
          Iterator localIterator = ((List)this.clickableAreas.get(Integer.valueOf(i))).iterator();
          while (localIterator.hasNext())
          {
            ClickableArea localClickableArea = (ClickableArea)localIterator.next();
            if (localClickableArea != null)
            {
              RectF localRectF = localClickableArea.getRect();
              if ((localRectF != null) && (localRectF.contains(paramPoint.getX(), paramPoint.getY()))) {
                return new SeriesSelection(i, j, localClickableArea.getX(), localClickableArea.getY());
              }
            }
            j += 1;
          }
        }
        i -= 1;
      }
    }
    return super.getSeriesAndPointForScreenCoordinate(paramPoint);
  }
  
  protected List<Double> getXLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    return MathHelper.getLabels(paramDouble1, paramDouble2, paramInt);
  }
  
  protected Map<Integer, List<Double>> getYLabels(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramInt)
    {
      localHashMap.put(Integer.valueOf(i), getValidLabels(MathHelper.getLabels(paramArrayOfDouble1[i], paramArrayOfDouble2[i], this.mRenderer.getYLabels())));
      i += 1;
    }
    return localHashMap;
  }
  
  protected boolean isRenderNullValues()
  {
    return false;
  }
  
  public boolean isRenderPoints(SimpleSeriesRenderer paramSimpleSeriesRenderer)
  {
    return false;
  }
  
  public void setCalcRange(double[] paramArrayOfDouble, int paramInt)
  {
    this.mCalcRange.put(Integer.valueOf(paramInt), paramArrayOfDouble);
  }
  
  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    this.mDataset = paramXYMultipleSeriesDataset;
    this.mRenderer = paramXYMultipleSeriesRenderer;
  }
  
  protected void setScreenR(Rect paramRect)
  {
    this.mScreenR = paramRect;
  }
  
  public double[] toRealPoint(float paramFloat1, float paramFloat2)
  {
    return toRealPoint(paramFloat1, paramFloat2, 0);
  }
  
  public double[] toRealPoint(float paramFloat1, float paramFloat2, int paramInt)
  {
    double d5 = this.mRenderer.getXAxisMin(paramInt);
    double d6 = this.mRenderer.getXAxisMax(paramInt);
    double d7 = this.mRenderer.getYAxisMin(paramInt);
    double d8 = this.mRenderer.getYAxisMax(paramInt);
    double d4;
    double d3;
    double d2;
    double d1;
    if ((this.mRenderer.isMinXSet(paramInt)) && (this.mRenderer.isMaxXSet(paramInt)) && (this.mRenderer.isMinYSet(paramInt)))
    {
      d4 = d6;
      d3 = d8;
      d2 = d5;
      d1 = d7;
      if (this.mRenderer.isMaxYSet(paramInt)) {}
    }
    else
    {
      double[] arrayOfDouble = getCalcRange(paramInt);
      d4 = d6;
      d3 = d8;
      d2 = d5;
      d1 = d7;
      if (arrayOfDouble != null)
      {
        d2 = arrayOfDouble[0];
        d4 = arrayOfDouble[1];
        d1 = arrayOfDouble[2];
        d3 = arrayOfDouble[3];
      }
    }
    if (this.mScreenR != null) {
      return new double[] { (paramFloat1 - this.mScreenR.left) * (d4 - d2) / this.mScreenR.width() + d2, (this.mScreenR.top + this.mScreenR.height() - paramFloat2) * (d3 - d1) / this.mScreenR.height() + d1 };
    }
    return new double[] { paramFloat1, paramFloat2 };
  }
  
  public double[] toScreenPoint(double[] paramArrayOfDouble)
  {
    return toScreenPoint(paramArrayOfDouble, 0);
  }
  
  public double[] toScreenPoint(double[] paramArrayOfDouble, int paramInt)
  {
    double d3 = this.mRenderer.getXAxisMin(paramInt);
    double d1 = this.mRenderer.getXAxisMax(paramInt);
    double d4 = this.mRenderer.getYAxisMin(paramInt);
    double d2 = this.mRenderer.getYAxisMax(paramInt);
    if ((!this.mRenderer.isMinXSet(paramInt)) || (!this.mRenderer.isMaxXSet(paramInt)) || (!this.mRenderer.isMinYSet(paramInt)) || (!this.mRenderer.isMaxYSet(paramInt)))
    {
      arrayOfDouble = getCalcRange(paramInt);
      d3 = arrayOfDouble[0];
      d1 = arrayOfDouble[1];
      d4 = arrayOfDouble[2];
      d2 = arrayOfDouble[3];
    }
    double[] arrayOfDouble = paramArrayOfDouble;
    if (this.mScreenR != null) {
      arrayOfDouble = new double[] { (paramArrayOfDouble[0] - d3) * this.mScreenR.width() / (d1 - d3) + this.mScreenR.left, (d2 - paramArrayOfDouble[1]) * this.mScreenR.height() / (d2 - d4) + this.mScreenR.top };
    }
    return arrayOfDouble;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/XYChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */