package org.achartengine.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.achartengine.model.Point;
import org.achartengine.model.SeriesSelection;

public class PieMapper
  implements Serializable
{
  private int mCenterX;
  private int mCenterY;
  private int mPieChartRadius;
  private List<PieSegment> mPieSegmentList = new ArrayList();
  
  public void addPieSegment(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mPieSegmentList.add(new PieSegment(paramInt, paramFloat1, paramFloat2, paramFloat3));
  }
  
  public boolean areAllSegmentPresent(int paramInt)
  {
    return this.mPieSegmentList.size() == paramInt;
  }
  
  public void clearPieSegments()
  {
    this.mPieSegmentList.clear();
  }
  
  public double getAngle(Point paramPoint)
  {
    double d = paramPoint.getX() - this.mCenterX;
    d = Math.atan2(-(paramPoint.getY() - this.mCenterY), d);
    if (d < 0.0D) {}
    for (d = Math.abs(d);; d = 6.283185307179586D - d) {
      return Math.toDegrees(d);
    }
  }
  
  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    if (isOnPieChart(paramPoint))
    {
      double d = getAngle(paramPoint);
      paramPoint = this.mPieSegmentList.iterator();
      while (paramPoint.hasNext())
      {
        PieSegment localPieSegment = (PieSegment)paramPoint.next();
        if (localPieSegment.isInSegment(d)) {
          return new SeriesSelection(0, localPieSegment.getDataIndex(), localPieSegment.getValue(), localPieSegment.getValue());
        }
      }
    }
    return null;
  }
  
  public boolean isOnPieChart(Point paramPoint)
  {
    return Math.pow(this.mCenterX - paramPoint.getX(), 2.0D) + Math.pow(this.mCenterY - paramPoint.getY(), 2.0D) <= this.mPieChartRadius * this.mPieChartRadius;
  }
  
  public void setDimensions(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mPieChartRadius = paramInt1;
    this.mCenterX = paramInt2;
    this.mCenterY = paramInt3;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/chart/PieMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */