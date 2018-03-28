package org.achartengine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategorySeries
  implements Serializable
{
  private List<String> mCategories = new ArrayList();
  private String mTitle;
  private List<Double> mValues = new ArrayList();
  
  public CategorySeries(String paramString)
  {
    this.mTitle = paramString;
  }
  
  public void add(double paramDouble)
  {
    try
    {
      add(this.mCategories.size() + "", paramDouble);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void add(String paramString, double paramDouble)
  {
    try
    {
      this.mCategories.add(paramString);
      this.mValues.add(Double.valueOf(paramDouble));
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
      this.mCategories.clear();
      this.mValues.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getCategory(int paramInt)
  {
    try
    {
      String str = (String)this.mCategories.get(paramInt);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getItemCount()
  {
    try
    {
      int i = this.mCategories.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getTitle()
  {
    return this.mTitle;
  }
  
  public double getValue(int paramInt)
  {
    try
    {
      double d = ((Double)this.mValues.get(paramInt)).doubleValue();
      return d;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void remove(int paramInt)
  {
    try
    {
      this.mCategories.remove(paramInt);
      this.mValues.remove(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void set(int paramInt, String paramString, double paramDouble)
  {
    try
    {
      this.mCategories.set(paramInt, paramString);
      this.mValues.set(paramInt, Double.valueOf(paramDouble));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public XYSeries toXYSeries()
  {
    XYSeries localXYSeries = new XYSeries(this.mTitle);
    int i = 0;
    Iterator localIterator = this.mValues.iterator();
    while (localIterator.hasNext())
    {
      double d = ((Double)localIterator.next()).doubleValue();
      i += 1;
      localXYSeries.add(i, d);
    }
    return localXYSeries;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/model/CategorySeries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */