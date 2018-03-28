package org.achartengine.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MathHelper
{
  private static final NumberFormat FORMAT = ;
  public static final double NULL_VALUE = Double.MAX_VALUE;
  
  private static double[] computeLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    if (Math.abs(paramDouble1 - paramDouble2) < 1.0000000116860974E-7D) {
      return new double[] { paramDouble1, paramDouble1, 0.0D };
    }
    int i = 0;
    double d1 = paramDouble2;
    double d2 = paramDouble1;
    if (paramDouble1 > paramDouble2)
    {
      i = 1;
      d2 = paramDouble2;
      d1 = paramDouble1;
    }
    paramDouble1 = roundUp(Math.abs(d2 - d1) / paramInt);
    paramDouble2 = paramDouble1 * Math.ceil(d2 / paramDouble1);
    d1 = paramDouble1 * Math.floor(d1 / paramDouble1);
    if (i != 0) {
      return new double[] { d1, paramDouble2, -1.0D * paramDouble1 };
    }
    return new double[] { paramDouble2, d1, paramDouble1 };
  }
  
  public static List<Double> getLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramInt <= 0) {}
    for (;;)
    {
      return localArrayList;
      FORMAT.setMaximumFractionDigits(5);
      double[] arrayOfDouble = computeLabels(paramDouble1, paramDouble2, paramInt);
      int i = (int)((arrayOfDouble[1] - arrayOfDouble[0]) / arrayOfDouble[2]);
      paramInt = 0;
      while (paramInt < i + 1)
      {
        paramDouble1 = arrayOfDouble[0] + paramInt * arrayOfDouble[2];
        try
        {
          paramDouble2 = FORMAT.parse(FORMAT.format(paramDouble1)).doubleValue();
          paramDouble1 = paramDouble2;
        }
        catch (ParseException localParseException)
        {
          for (;;) {}
        }
        localArrayList.add(Double.valueOf(paramDouble1));
        paramInt += 1;
      }
    }
  }
  
  public static double[] minmax(List<Double> paramList)
  {
    if (paramList.size() == 0) {
      return new double[2];
    }
    double d2 = ((Double)paramList.get(0)).doubleValue();
    double d1 = d2;
    int j = paramList.size();
    int i = 1;
    while (i < j)
    {
      double d3 = ((Double)paramList.get(i)).doubleValue();
      d2 = Math.min(d2, d3);
      d1 = Math.max(d1, d3);
      i += 1;
    }
    return new double[] { d2, d1 };
  }
  
  private static double roundUp(double paramDouble)
  {
    int i = (int)Math.floor(Math.log10(paramDouble));
    double d = paramDouble * Math.pow(10.0D, -i);
    if (d > 5.0D) {
      paramDouble = 10.0D;
    }
    for (;;)
    {
      return paramDouble * Math.pow(10.0D, i);
      if (d > 2.0D)
      {
        paramDouble = 5.0D;
      }
      else
      {
        paramDouble = d;
        if (d > 1.0D) {
          paramDouble = 2.0D;
        }
      }
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/util/MathHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */