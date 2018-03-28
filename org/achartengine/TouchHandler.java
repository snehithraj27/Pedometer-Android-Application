package org.achartengine;

import android.graphics.RectF;
import android.view.MotionEvent;
import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.RoundChart;
import org.achartengine.chart.XYChart;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.tools.Pan;
import org.achartengine.tools.PanListener;
import org.achartengine.tools.Zoom;
import org.achartengine.tools.ZoomListener;

public class TouchHandler
  implements ITouchHandler
{
  private GraphicalView graphicalView;
  private Pan mPan;
  private Zoom mPinchZoom;
  private DefaultRenderer mRenderer;
  private float oldX;
  private float oldX2;
  private float oldY;
  private float oldY2;
  private RectF zoomR = new RectF();
  
  public TouchHandler(GraphicalView paramGraphicalView, AbstractChart paramAbstractChart)
  {
    this.graphicalView = paramGraphicalView;
    this.zoomR = this.graphicalView.getZoomRectangle();
    if ((paramAbstractChart instanceof XYChart)) {}
    for (this.mRenderer = ((XYChart)paramAbstractChart).getRenderer();; this.mRenderer = ((RoundChart)paramAbstractChart).getRenderer())
    {
      if (this.mRenderer.isPanEnabled()) {
        this.mPan = new Pan(paramAbstractChart);
      }
      if (this.mRenderer.isZoomEnabled()) {
        this.mPinchZoom = new Zoom(paramAbstractChart, true, 1.0F);
      }
      return;
    }
  }
  
  private void applyZoom(float paramFloat, int paramInt)
  {
    paramFloat = Math.min(Math.max(paramFloat, 0.9F), 1.1F);
    if ((this.mPinchZoom != null) && (paramFloat > 0.9D) && (paramFloat < 1.1D))
    {
      this.mPinchZoom.setZoomRate(paramFloat);
      this.mPinchZoom.apply(paramInt);
    }
  }
  
  public void addPanListener(PanListener paramPanListener)
  {
    if (this.mPan != null) {
      this.mPan.addPanListener(paramPanListener);
    }
  }
  
  public void addZoomListener(ZoomListener paramZoomListener)
  {
    if (this.mPinchZoom != null) {
      this.mPinchZoom.addZoomListener(paramZoomListener);
    }
  }
  
  public boolean handleTouch(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((this.mRenderer != null) && (i == 2))
    {
      if ((this.oldX >= 0.0F) || (this.oldY >= 0.0F))
      {
        float f2 = paramMotionEvent.getX(0);
        float f3 = paramMotionEvent.getY(0);
        float f1;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        if ((paramMotionEvent.getPointerCount() > 1) && ((this.oldX2 >= 0.0F) || (this.oldY2 >= 0.0F)) && (this.mRenderer.isZoomEnabled()))
        {
          float f4 = paramMotionEvent.getX(1);
          float f5 = paramMotionEvent.getY(1);
          f1 = Math.abs(f2 - f4);
          f6 = Math.abs(f3 - f5);
          f7 = Math.abs(this.oldX - this.oldX2);
          f8 = Math.abs(this.oldY - this.oldY2);
          f9 = Math.abs(f3 - this.oldY) / Math.abs(f2 - this.oldX);
          f10 = Math.abs(f5 - this.oldY2) / Math.abs(f4 - this.oldX2);
          if ((f9 <= 0.25D) && (f10 <= 0.25D))
          {
            applyZoom(f1 / f7, 1);
            this.oldX2 = f4;
            this.oldY2 = f5;
          }
        }
        for (;;)
        {
          this.oldX = f2;
          this.oldY = f3;
          this.graphicalView.repaint();
          return true;
          if ((f9 >= 3.73D) && (f10 >= 3.73D))
          {
            applyZoom(f6 / f8, 2);
            break;
          }
          if (Math.abs(f2 - this.oldX) >= Math.abs(f3 - this.oldY)) {}
          for (f1 /= f7;; f1 = f6 / f8)
          {
            applyZoom(f1, 0);
            break;
          }
          if (this.mRenderer.isPanEnabled())
          {
            this.mPan.apply(this.oldX, this.oldY, f2, f3);
            this.oldX2 = 0.0F;
            this.oldY2 = 0.0F;
          }
        }
      }
    }
    else if (i == 0)
    {
      this.oldX = paramMotionEvent.getX(0);
      this.oldY = paramMotionEvent.getY(0);
      if ((this.mRenderer != null) && (this.mRenderer.isZoomEnabled()) && (this.zoomR.contains(this.oldX, this.oldY)))
      {
        if (this.oldX < this.zoomR.left + this.zoomR.width() / 3.0F) {
          this.graphicalView.zoomIn();
        }
        for (;;)
        {
          return true;
          if (this.oldX < this.zoomR.left + this.zoomR.width() * 2.0F / 3.0F) {
            this.graphicalView.zoomOut();
          } else {
            this.graphicalView.zoomReset();
          }
        }
      }
    }
    else if ((i == 1) || (i == 6))
    {
      this.oldX = 0.0F;
      this.oldY = 0.0F;
      this.oldX2 = 0.0F;
      this.oldY2 = 0.0F;
      if (i == 6)
      {
        this.oldX = -1.0F;
        this.oldY = -1.0F;
      }
    }
    return !this.mRenderer.isClickEnabled();
  }
  
  public void removePanListener(PanListener paramPanListener)
  {
    if (this.mPan != null) {
      this.mPan.removePanListener(paramPanListener);
    }
  }
  
  public void removeZoomListener(ZoomListener paramZoomListener)
  {
    if (this.mPinchZoom != null) {
      this.mPinchZoom.removeZoomListener(paramZoomListener);
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/TouchHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */