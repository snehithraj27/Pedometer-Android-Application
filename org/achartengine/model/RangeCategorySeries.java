package org.achartengine.model;

import java.util.ArrayList;
import java.util.List;

public class RangeCategorySeries
  extends CategorySeries
{
  private List<Double> mMaxValues = new ArrayList();
  
  public RangeCategorySeries(String paramString)
  {
    super(paramString);
  }
  
  public void add(double paramDouble1, double paramDouble2)
  {
    try
    {
      super.add(paramDouble1);
      this.mMaxValues.add(Double.valueOf(paramDouble2));
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void add(String paramString, double paramDouble1, double paramDouble2)
  {
    try
    {
      super.add(paramString, paramDouble1);
      this.mMaxValues.add(Double.valueOf(paramDouble2));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void clear()
  {
    try
    {
      super.clear();
      this.mMaxValues.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public double getMaximumValue(int paramInt)
  {
    return ((Double)this.mMaxValues.get(paramInt)).doubleValue();
  }
  
  public double getMinimumValue(int paramInt)
  {
    return getValue(paramInt);
  }
  
  public void remove(int paramInt)
  {
    try
    {
      super.remove(paramInt);
      this.mMaxValues.remove(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public XYSeries toXYSeries()
  {
    XYSeries localXYSeries = new XYSeries(getTitle());
    int j = getItemCount();
    int i = 0;
    while (i < j)
    {
      localXYSeries.add(i + 1, getMinimumValue(i));
      localXYSeries.add(i + 1.000001D, getMaximumValue(i));
      i += 1;
    }
    return localXYSeries;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/model/RangeCategorySeries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */