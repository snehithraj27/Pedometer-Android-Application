package org.achartengine.chart;

import java.io.Serializable;

public class PieSegment
  implements Serializable
{
  private int mDataIndex;
  private float mEndAngle;
  private float mStartAngle;
  private float mValue;
  
  public PieSegment(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mStartAngle = paramFloat2;
    this.mEndAngle = (paramFloat3 + paramFloat2);
    this.mDataIndex = paramInt;
    this.mValue = paramFloat1;
  }
  
  protected int getDataIndex()
  {
    return this.mDataIndex;
  }
  
  protected float getEndAngle()
  {
    return this.mEndAngle;
  }
  
  protected float getStartAngle()
  {
    return this.mStartAngle;
  }
  
  protected float getValue()
  {
    return this.mValue;
  }
  
  public boolean isInSegment(double paramDouble)
  {
    if ((paramDouble >= this.mStartAngle) && (paramDouble <= this.mEndAngle)) {}
    double d2;
    double d1;
    do
    {
      return true;
      d2 = paramDouble % 360.0D;
      d1 = this.mStartAngle;
      for (paramDouble = this.mEndAngle; paramDouble > 360.0D; paramDouble -= 360.0D) {
        d1 -= 360.0D;
      }
    } while ((d2 >= d1) && (d2 <= paramDouble));
    return false;
  }
  
  public String toString()
  {
    return "mDataIndex=" + this.mDataIndex + ",mValue=" + this.mValue + ",mStartAngle=" + this.mStartAngle + ",mEndAngle=" + this.mEndAngle;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/PieSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */