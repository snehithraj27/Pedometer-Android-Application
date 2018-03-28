package org.achartengine.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.RoundChart;
import org.achartengine.chart.XYChart;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

public class Zoom
  extends AbstractTool
{
  public static final int ZOOM_AXIS_X = 1;
  public static final int ZOOM_AXIS_XY = 0;
  public static final int ZOOM_AXIS_Y = 2;
  private boolean limitsReachedX = false;
  private boolean limitsReachedY = false;
  private boolean mZoomIn;
  private List<ZoomListener> mZoomListeners = new ArrayList();
  private float mZoomRate;
  
  public Zoom(AbstractChart paramAbstractChart, boolean paramBoolean, float paramFloat)
  {
    super(paramAbstractChart);
    this.mZoomIn = paramBoolean;
    setZoomRate(paramFloat);
  }
  
  private void notifyZoomListeners(ZoomEvent paramZoomEvent)
  {
    try
    {
      Iterator localIterator = this.mZoomListeners.iterator();
      while (localIterator.hasNext()) {
        ((ZoomListener)localIterator.next()).zoomApplied(paramZoomEvent);
      }
    }
    finally {}
  }
  
  public void addZoomListener(ZoomListener paramZoomListener)
  {
    try
    {
      this.mZoomListeners.add(paramZoomListener);
      return;
    }
    finally
    {
      paramZoomListener = finally;
      throw paramZoomListener;
    }
  }
  
  public void apply(int paramInt)
  {
    Object localObject;
    if ((this.mChart instanceof XYChart))
    {
      int j = this.mRenderer.getScalesCount();
      int i = 0;
      if (i < j)
      {
        localObject = getRange(i);
        checkRange((double[])localObject, i);
        double[] arrayOfDouble = this.mRenderer.getZoomLimits();
        double d5 = (localObject[0] + localObject[1]) / 2.0D;
        double d6 = (localObject[2] + localObject[3]) / 2.0D;
        double d1 = localObject[1] - localObject[0];
        double d4 = localObject[3] - localObject[2];
        double d2 = d1 / 2.0D;
        double d3 = d1 / 2.0D;
        double d7 = d4 / 2.0D;
        double d8 = d4 / 2.0D;
        boolean bool;
        if (i == 0)
        {
          if ((arrayOfDouble != null) && ((d5 - d2 <= arrayOfDouble[0]) || (d5 + d3 >= arrayOfDouble[1])))
          {
            bool = true;
            label174:
            this.limitsReachedX = bool;
            if ((arrayOfDouble == null) || ((d6 - d7 > arrayOfDouble[2]) && (d6 + d8 < arrayOfDouble[3]))) {
              break label489;
            }
            bool = true;
            label214:
            this.limitsReachedY = bool;
          }
        }
        else
        {
          if (!this.mZoomIn) {
            break label523;
          }
          d2 = d1;
          if (this.mRenderer.isZoomXEnabled()) {
            if (paramInt != 1)
            {
              d2 = d1;
              if (paramInt != 0) {}
            }
            else
            {
              if ((!this.limitsReachedX) || (this.mZoomRate >= 1.0F)) {
                break label495;
              }
              d2 = d1;
            }
          }
          label271:
          d1 = d4;
          d3 = d2;
          if (this.mRenderer.isZoomYEnabled()) {
            if (paramInt != 2)
            {
              d1 = d4;
              d3 = d2;
              if (paramInt != 0) {}
            }
            else
            {
              if ((!this.limitsReachedY) || (this.mZoomRate >= 1.0F)) {
                break label507;
              }
              d3 = d2;
              d1 = d4;
            }
          }
          label327:
          if (arrayOfDouble == null) {
            break label630;
          }
          d4 = Math.min(this.mRenderer.getZoomInLimitX(), arrayOfDouble[1] - arrayOfDouble[0]);
        }
        for (d2 = Math.min(this.mRenderer.getZoomInLimitY(), arrayOfDouble[3] - arrayOfDouble[2]);; d2 = this.mRenderer.getZoomInLimitY())
        {
          d3 = Math.max(d3, d4);
          d1 = Math.max(d1, d2);
          if ((this.mRenderer.isZoomXEnabled()) && ((paramInt == 1) || (paramInt == 0))) {
            setXRange(d5 - d3 / 2.0D, d5 + d3 / 2.0D, i);
          }
          if ((this.mRenderer.isZoomYEnabled()) && ((paramInt == 2) || (paramInt == 0))) {
            setYRange(d6 - d1 / 2.0D, d6 + d1 / 2.0D, i);
          }
          i += 1;
          break;
          bool = false;
          break label174;
          label489:
          bool = false;
          break label214;
          label495:
          d2 = d1 / this.mZoomRate;
          break label271;
          label507:
          d1 = d4 / this.mZoomRate;
          d3 = d2;
          break label327;
          label523:
          d2 = d1;
          if (this.mRenderer.isZoomXEnabled())
          {
            d2 = d1;
            if (!this.limitsReachedX) {
              if (paramInt != 1)
              {
                d2 = d1;
                if (paramInt != 0) {}
              }
              else
              {
                d2 = d1 * this.mZoomRate;
              }
            }
          }
          d1 = d4;
          d3 = d2;
          if (!this.mRenderer.isZoomYEnabled()) {
            break label327;
          }
          d1 = d4;
          d3 = d2;
          if (this.limitsReachedY) {
            break label327;
          }
          if (paramInt != 2)
          {
            d1 = d4;
            d3 = d2;
            if (paramInt != 0) {
              break label327;
            }
          }
          d1 = d4 * this.mZoomRate;
          d3 = d2;
          break label327;
          label630:
          d4 = this.mRenderer.getZoomInLimitX();
        }
      }
    }
    else
    {
      localObject = ((RoundChart)this.mChart).getRenderer();
      if (!this.mZoomIn) {
        break label705;
      }
      ((DefaultRenderer)localObject).setScale(((DefaultRenderer)localObject).getScale() * this.mZoomRate);
    }
    for (;;)
    {
      notifyZoomListeners(new ZoomEvent(this.mZoomIn, this.mZoomRate));
      return;
      label705:
      ((DefaultRenderer)localObject).setScale(((DefaultRenderer)localObject).getScale() / this.mZoomRate);
    }
  }
  
  public void notifyZoomResetListeners()
  {
    try
    {
      Iterator localIterator = this.mZoomListeners.iterator();
      while (localIterator.hasNext()) {
        ((ZoomListener)localIterator.next()).zoomReset();
      }
    }
    finally {}
  }
  
  public void removeZoomListener(ZoomListener paramZoomListener)
  {
    try
    {
      this.mZoomListeners.remove(paramZoomListener);
      return;
    }
    finally
    {
      paramZoomListener = finally;
      throw paramZoomListener;
    }
  }
  
  public void setZoomRate(float paramFloat)
  {
    this.mZoomRate = paramFloat;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/tools/Zoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */