package org.achartengine.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

public class TimeChart
  extends LineChart
{
  public static final long DAY = 86400000L;
  public static final String TYPE = "Time";
  private String mDateFormat;
  private Double mStartPoint;
  
  TimeChart() {}
  
  public TimeChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }
  
  private DateFormat getDateFormat(double paramDouble1, double paramDouble2)
  {
    if (this.mDateFormat != null) {}
    do
    {
      try
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(this.mDateFormat);
        return localSimpleDateFormat;
      }
      catch (Exception localException) {}
      DateFormat localDateFormat = SimpleDateFormat.getDateInstance(2);
      paramDouble1 = paramDouble2 - paramDouble1;
      if ((paramDouble1 > 8.64E7D) && (paramDouble1 < 4.32E8D)) {
        return SimpleDateFormat.getDateTimeInstance(3, 3);
      }
    } while (paramDouble1 >= 8.64E7D);
    return SimpleDateFormat.getTimeInstance(2);
  }
  
  protected void drawXLabels(List<Double> paramList, Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    int j = paramList.size();
    if (j > 0)
    {
      boolean bool1 = this.mRenderer.isShowXLabels();
      boolean bool2 = this.mRenderer.isShowGridY();
      if (bool2)
      {
        this.mGridPaint.setStyle(Paint.Style.STROKE);
        this.mGridPaint.setStrokeWidth(this.mRenderer.getGridLineWidth());
      }
      boolean bool3 = this.mRenderer.isShowTickMarks();
      DateFormat localDateFormat = getDateFormat(((Double)paramList.get(0)).doubleValue(), ((Double)paramList.get(j - 1)).doubleValue());
      int i = 0;
      while (i < j)
      {
        long l = Math.round(((Double)paramList.get(i)).doubleValue());
        float f = (float)(paramInt1 + (l - paramDouble2) * paramDouble1);
        if (bool1)
        {
          paramPaint.setColor(this.mRenderer.getXLabelsColor());
          if (bool3) {
            paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
          }
          drawText(paramCanvas, localDateFormat.format(new Date(l)), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F + this.mRenderer.getXLabelsPadding(), paramPaint, this.mRenderer.getXLabelsAngle());
        }
        if (bool2)
        {
          this.mGridPaint.setColor(this.mRenderer.getGridColor(0));
          paramCanvas.drawLine(f, paramInt3, f, paramInt2, this.mGridPaint);
        }
        i += 1;
      }
    }
    drawXTextLabels(paramArrayOfDouble, paramCanvas, paramPaint, true, paramInt1, paramInt2, paramInt3, paramDouble1, paramDouble2, paramDouble3);
  }
  
  public String getChartType()
  {
    return "Time";
  }
  
  public String getDateFormat()
  {
    return this.mDateFormat;
  }
  
  protected List<Double> getXLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    Object localObject;
    if (!this.mRenderer.isXRoundedLabels())
    {
      if (this.mDataset.getSeriesCount() > 0)
      {
        XYSeries localXYSeries = this.mDataset.getSeriesAt(0);
        int i1 = localXYSeries.getItemCount();
        int k = 0;
        i = -1;
        int j = 0;
        while (j < i1)
        {
          d1 = localXYSeries.getX(j);
          int m = k;
          int n = i;
          if (paramDouble1 <= d1)
          {
            m = k;
            n = i;
            if (d1 <= paramDouble2)
            {
              k += 1;
              m = k;
              n = i;
              if (i < 0)
              {
                n = j;
                m = k;
              }
            }
          }
          j += 1;
          k = m;
          i = n;
        }
        if (k < paramInt)
        {
          paramInt = i;
          for (;;)
          {
            localObject = localArrayList;
            if (paramInt >= i + k) {
              break;
            }
            localArrayList.add(Double.valueOf(localXYSeries.getX(paramInt)));
            paramInt += 1;
          }
        }
        float f = k / paramInt;
        j = 0;
        i = 0;
        for (;;)
        {
          localObject = localArrayList;
          if (i >= i1) {
            break;
          }
          localObject = localArrayList;
          if (j >= paramInt) {
            break;
          }
          d1 = localXYSeries.getX(Math.round(i * f));
          k = j;
          if (paramDouble1 <= d1)
          {
            k = j;
            if (d1 <= paramDouble2)
            {
              localArrayList.add(Double.valueOf(d1));
              k = j + 1;
            }
          }
          i += 1;
          j = k;
        }
      }
      localObject = super.getXLabels(paramDouble1, paramDouble2, paramInt);
    }
    double d3;
    do
    {
      return (List<Double>)localObject;
      if (this.mStartPoint == null) {
        this.mStartPoint = Double.valueOf(paramDouble1 - paramDouble1 % 8.64E7D + 8.64E7D + new Date(Math.round(paramDouble1)).getTimezoneOffset() * 60 * 1000);
      }
      i = paramInt;
      if (paramInt > 25) {
        i = 25;
      }
      d3 = (paramDouble2 - paramDouble1) / i;
      localObject = localArrayList;
    } while (d3 <= 0.0D);
    double d2 = 8.64E7D;
    double d1 = d2;
    if (d3 <= 8.64E7D) {
      for (d1 = d2;; d1 /= 2.0D)
      {
        d2 = d1;
        if (d3 >= d1 / 2.0D) {
          break;
        }
      }
    }
    for (;;)
    {
      d2 = d1;
      if (d3 <= d1) {
        break;
      }
      d1 *= 2.0D;
    }
    paramDouble1 = this.mStartPoint.doubleValue() - Math.floor((this.mStartPoint.doubleValue() - paramDouble1) / d2) * d2;
    paramInt = 0;
    for (;;)
    {
      if (paramDouble1 >= paramDouble2) {
        break label545;
      }
      localObject = localArrayList;
      if (paramInt > i) {
        break;
      }
      localArrayList.add(Double.valueOf(paramDouble1));
      paramDouble1 += d2;
      paramInt += 1;
    }
    label545:
    return localArrayList;
  }
  
  public void setDateFormat(String paramString)
  {
    this.mDateFormat = paramString;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/TimeChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */