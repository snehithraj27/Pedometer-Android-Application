package org.achartengine.model;

import java.util.Date;

public class TimeSeries
  extends XYSeries
{
  public TimeSeries(String paramString)
  {
    super(paramString);
  }
  
  public void add(Date paramDate, double paramDouble)
  {
    try
    {
      super.add(paramDate.getTime(), paramDouble);
      return;
    }
    finally
    {
      paramDate = finally;
      throw paramDate;
    }
  }
  
  protected double getPadding(double paramDouble)
  {
    return 1.0D;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/model/TimeSeries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */