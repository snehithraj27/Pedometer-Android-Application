package org.achartengine.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.RoundChart;
import org.achartengine.chart.XYChart;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

public class Pan
  extends AbstractTool
{
  private boolean limitsReachedX = false;
  private boolean limitsReachedY = false;
  private List<PanListener> mPanListeners = new ArrayList();
  
  public Pan(AbstractChart paramAbstractChart)
  {
    super(paramAbstractChart);
  }
  
  private double getAxisRatio(double[] paramArrayOfDouble)
  {
    return Math.abs(paramArrayOfDouble[1] - paramArrayOfDouble[0]) / Math.abs(paramArrayOfDouble[3] - paramArrayOfDouble[2]);
  }
  
  private void notifyPanListeners()
  {
    try
    {
      Iterator localIterator = this.mPanListeners.iterator();
      while (localIterator.hasNext()) {
        ((PanListener)localIterator.next()).panApplied();
      }
    }
    finally {}
  }
  
  public void addPanListener(PanListener paramPanListener)
  {
    try
    {
      this.mPanListeners.add(paramPanListener);
      return;
    }
    finally
    {
      paramPanListener = finally;
      throw paramPanListener;
    }
  }
  
  public void apply(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int n = 1;
    int i = 1;
    int j = 1;
    int i1 = 1;
    if ((this.mChart instanceof XYChart))
    {
      int i6 = this.mRenderer.getScalesCount();
      localObject = this.mRenderer.getPanLimits();
      if ((localObject != null) && (localObject.length == 4)) {}
      XYChart localXYChart;
      int i4;
      double[] arrayOfDouble1;
      for (int i3 = 1;; i3 = 0)
      {
        localXYChart = (XYChart)this.mChart;
        i4 = 0;
        if (i4 >= i6) {
          break label647;
        }
        arrayOfDouble1 = getRange(i4);
        arrayOfDouble2 = localXYChart.getCalcRange(i4);
        if ((!this.limitsReachedX) || (!this.limitsReachedY) || (((arrayOfDouble1[0] != arrayOfDouble1[1]) || (arrayOfDouble2[0] != arrayOfDouble2[1])) && ((arrayOfDouble1[2] != arrayOfDouble1[3]) || (arrayOfDouble2[2] != arrayOfDouble2[3])))) {
          break;
        }
        return;
      }
      checkRange(arrayOfDouble1, i4);
      double[] arrayOfDouble2 = localXYChart.toRealPoint(paramFloat1, paramFloat2, i4);
      double[] arrayOfDouble3 = localXYChart.toRealPoint(paramFloat3, paramFloat4, i4);
      double d3 = arrayOfDouble2[0] - arrayOfDouble3[0];
      double d4 = arrayOfDouble2[1] - arrayOfDouble3[1];
      double d5 = getAxisRatio(arrayOfDouble1);
      double d2 = d3;
      double d1 = d4;
      if (localXYChart.isVertical(this.mRenderer))
      {
        d2 = -d4;
        d1 = d3 / d5;
        d2 *= d5;
      }
      int i2 = j;
      int i5 = i1;
      int m;
      int k;
      if (this.mRenderer.isPanXEnabled())
      {
        i2 = j;
        m = i1;
        if (localObject != null)
        {
          k = j;
          if (j != 0)
          {
            if (localObject[0] > arrayOfDouble1[0] + d2) {
              break label555;
            }
            k = 1;
          }
          label318:
          i2 = k;
          m = i1;
          if (i1 != 0)
          {
            if (localObject[1] < arrayOfDouble1[1] + d2) {
              break label561;
            }
            m = 1;
            i2 = k;
          }
        }
        label353:
        if ((i3 != 0) && ((i2 == 0) || (m == 0))) {
          break label571;
        }
        setXRange(arrayOfDouble1[0] + d2, arrayOfDouble1[1] + d2, i4);
        this.limitsReachedX = false;
        i5 = m;
      }
      else
      {
        label397:
        m = i;
        k = n;
        if (this.mRenderer.isPanYEnabled())
        {
          m = i;
          k = n;
          if (localObject != null)
          {
            j = i;
            if (i != 0)
            {
              if (localObject[2] > arrayOfDouble1[2] + d1) {
                break label583;
              }
              j = 1;
            }
            label455:
            m = j;
            k = n;
            if (n != 0)
            {
              if (localObject[3] < arrayOfDouble1[3] + d1) {
                break label589;
              }
              k = 1;
              m = j;
            }
          }
          label490:
          if ((i3 != 0) && ((m == 0) || (k == 0))) {
            break label599;
          }
          setYRange(arrayOfDouble1[2] + d1, arrayOfDouble1[3] + d1, i4);
        }
      }
      label555:
      label561:
      label571:
      label583:
      label589:
      label599:
      for (this.limitsReachedY = false;; this.limitsReachedY = true)
      {
        i4 += 1;
        i = m;
        j = i2;
        i1 = i5;
        n = k;
        break;
        k = 0;
        break label318;
        m = 0;
        i2 = k;
        break label353;
        this.limitsReachedX = true;
        i5 = m;
        break label397;
        j = 0;
        break label455;
        k = 0;
        m = j;
        break label490;
      }
    }
    Object localObject = (RoundChart)this.mChart;
    ((RoundChart)localObject).setCenterX(((RoundChart)localObject).getCenterX() + (int)(paramFloat3 - paramFloat1));
    ((RoundChart)localObject).setCenterY(((RoundChart)localObject).getCenterY() + (int)(paramFloat4 - paramFloat2));
    label647:
    notifyPanListeners();
  }
  
  public void removePanListener(PanListener paramPanListener)
  {
    try
    {
      this.mPanListeners.remove(paramPanListener);
      return;
    }
    finally
    {
      paramPanListener = finally;
      throw paramPanListener;
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/tools/Pan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */