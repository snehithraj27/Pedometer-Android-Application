package org.achartengine.tools;

public class ZoomEvent
{
  private boolean mZoomIn;
  private float mZoomRate;
  
  public ZoomEvent(boolean paramBoolean, float paramFloat)
  {
    this.mZoomIn = paramBoolean;
    this.mZoomRate = paramFloat;
  }
  
  public float getZoomRate()
  {
    return this.mZoomRate;
  }
  
  public boolean isZoomIn()
  {
    return this.mZoomIn;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/tools/ZoomEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */