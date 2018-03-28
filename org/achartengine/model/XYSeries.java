package org.achartengine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.util.IndexXYMap;
import org.achartengine.util.XYEntry;

public class XYSeries
  implements Serializable
{
  private List<String> mAnnotations = new ArrayList();
  private double mMaxX = -1.7976931348623157E308D;
  private double mMaxY = -1.7976931348623157E308D;
  private double mMinX = Double.MAX_VALUE;
  private double mMinY = Double.MAX_VALUE;
  private final int mScaleNumber;
  private final IndexXYMap<Double, Double> mStringXY = new IndexXYMap();
  private String mTitle;
  private final IndexXYMap<Double, Double> mXY = new IndexXYMap();
  
  public XYSeries(String paramString)
  {
    this(paramString, 0);
  }
  
  public XYSeries(String paramString, int paramInt)
  {
    this.mTitle = paramString;
    this.mScaleNumber = paramInt;
    initRange();
  }
  
  private void initRange()
  {
    this.mMinX = Double.MAX_VALUE;
    this.mMaxX = -1.7976931348623157E308D;
    this.mMinY = Double.MAX_VALUE;
    this.mMaxY = -1.7976931348623157E308D;
    int j = getItemCount();
    int i = 0;
    while (i < j)
    {
      updateRange(getX(i), getY(i));
      i += 1;
    }
  }
  
  private void updateRange(double paramDouble1, double paramDouble2)
  {
    this.mMinX = Math.min(this.mMinX, paramDouble1);
    this.mMaxX = Math.max(this.mMaxX, paramDouble1);
    this.mMinY = Math.min(this.mMinY, paramDouble2);
    this.mMaxY = Math.max(this.mMaxY, paramDouble2);
  }
  
  public void add(double paramDouble1, double paramDouble2)
  {
    try
    {
      while (this.mXY.get(Double.valueOf(paramDouble1)) != null) {
        paramDouble1 += getPadding(paramDouble1);
      }
      this.mXY.put(Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
      updateRange(paramDouble1, paramDouble2);
      return;
    }
    finally {}
  }
  
  public void add(int paramInt, double paramDouble1, double paramDouble2)
  {
    try
    {
      while (this.mXY.get(Double.valueOf(paramDouble1)) != null) {
        paramDouble1 += getPadding(paramDouble1);
      }
      this.mXY.put(paramInt, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
      updateRange(paramDouble1, paramDouble2);
      return;
    }
    finally {}
  }
  
  public void addAnnotation(String paramString, double paramDouble1, double paramDouble2)
  {
    this.mAnnotations.add(paramString);
    while (this.mStringXY.get(Double.valueOf(paramDouble1)) != null) {
      paramDouble1 += getPadding(paramDouble1);
    }
    this.mStringXY.put(Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
  }
  
  public void clear()
  {
    try
    {
      clearAnnotations();
      clearSeriesValues();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void clearAnnotations()
  {
    try
    {
      this.mAnnotations.clear();
      this.mStringXY.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void clearSeriesValues()
  {
    try
    {
      this.mXY.clear();
      initRange();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getAnnotationAt(int paramInt)
  {
    return (String)this.mAnnotations.get(paramInt);
  }
  
  public int getAnnotationCount()
  {
    return this.mAnnotations.size();
  }
  
  public double getAnnotationX(int paramInt)
  {
    return ((Double)this.mStringXY.getXByIndex(paramInt)).doubleValue();
  }
  
  public double getAnnotationY(int paramInt)
  {
    return ((Double)this.mStringXY.getYByIndex(paramInt)).doubleValue();
  }
  
  public int getIndexForKey(double paramDouble)
  {
    return this.mXY.getIndexForKey(Double.valueOf(paramDouble));
  }
  
  public int getItemCount()
  {
    try
    {
      int i = this.mXY.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public double getMaxX()
  {
    return this.mMaxX;
  }
  
  public double getMaxY()
  {
    return this.mMaxY;
  }
  
  public double getMinX()
  {
    return this.mMinX;
  }
  
  public double getMinY()
  {
    return this.mMinY;
  }
  
  protected double getPadding(double paramDouble)
  {
    return Math.ulp(paramDouble);
  }
  
  /* Error */
  public java.util.SortedMap<Double, Double> getRange(double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: dload_1
    //   3: dstore 8
    //   5: dload_3
    //   6: dstore 6
    //   8: iload 5
    //   10: ifeq +123 -> 133
    //   13: aload_0
    //   14: getfield 36	org/achartengine/model/XYSeries:mXY	Lorg/achartengine/util/IndexXYMap;
    //   17: dload_1
    //   18: invokestatic 93	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   21: invokevirtual 168	org/achartengine/util/IndexXYMap:headMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
    //   24: astore 10
    //   26: aload 10
    //   28: invokeinterface 174 1 0
    //   33: ifne +17 -> 50
    //   36: aload 10
    //   38: invokeinterface 178 1 0
    //   43: checkcast 89	java/lang/Double
    //   46: invokevirtual 145	java/lang/Double:doubleValue	()D
    //   49: dstore_1
    //   50: aload_0
    //   51: getfield 36	org/achartengine/model/XYSeries:mXY	Lorg/achartengine/util/IndexXYMap;
    //   54: dload_3
    //   55: invokestatic 93	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   58: invokevirtual 181	org/achartengine/util/IndexXYMap:tailMap	(Ljava/lang/Object;)Ljava/util/SortedMap;
    //   61: astore 10
    //   63: dload_1
    //   64: dstore 8
    //   66: dload_3
    //   67: dstore 6
    //   69: aload 10
    //   71: invokeinterface 174 1 0
    //   76: ifne +57 -> 133
    //   79: aload 10
    //   81: invokeinterface 185 1 0
    //   86: invokeinterface 191 1 0
    //   91: astore 10
    //   93: aload 10
    //   95: invokeinterface 196 1 0
    //   100: checkcast 89	java/lang/Double
    //   103: astore 11
    //   105: aload 10
    //   107: invokeinterface 199 1 0
    //   112: ifeq +53 -> 165
    //   115: aload 10
    //   117: invokeinterface 196 1 0
    //   122: checkcast 89	java/lang/Double
    //   125: invokevirtual 145	java/lang/Double:doubleValue	()D
    //   128: dstore 6
    //   130: dload_1
    //   131: dstore 8
    //   133: dload 8
    //   135: dload 6
    //   137: dcmpg
    //   138: ifgt +42 -> 180
    //   141: aload_0
    //   142: getfield 36	org/achartengine/model/XYSeries:mXY	Lorg/achartengine/util/IndexXYMap;
    //   145: dload 8
    //   147: invokestatic 93	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   150: dload 6
    //   152: invokestatic 93	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   155: invokevirtual 203	org/achartengine/util/IndexXYMap:subMap	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedMap;
    //   158: astore 10
    //   160: aload_0
    //   161: monitorexit
    //   162: aload 10
    //   164: areturn
    //   165: dload_3
    //   166: aload 11
    //   168: invokevirtual 145	java/lang/Double:doubleValue	()D
    //   171: dadd
    //   172: dstore 6
    //   174: dload_1
    //   175: dstore 8
    //   177: goto -44 -> 133
    //   180: new 205	java/util/TreeMap
    //   183: dup
    //   184: invokespecial 206	java/util/TreeMap:<init>	()V
    //   187: astore 10
    //   189: goto -29 -> 160
    //   192: astore 10
    //   194: aload_0
    //   195: monitorexit
    //   196: aload 10
    //   198: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	this	XYSeries
    //   0	199	1	paramDouble1	double
    //   0	199	3	paramDouble2	double
    //   0	199	5	paramBoolean	boolean
    //   6	167	6	d1	double
    //   3	173	8	d2	double
    //   24	164	10	localObject1	Object
    //   192	5	10	localObject2	Object
    //   103	64	11	localDouble	Double
    // Exception table:
    //   from	to	target	type
    //   13	26	192	finally
    //   26	50	192	finally
    //   50	63	192	finally
    //   69	130	192	finally
    //   141	160	192	finally
    //   165	174	192	finally
    //   180	189	192	finally
  }
  
  public int getScaleNumber()
  {
    return this.mScaleNumber;
  }
  
  public String getTitle()
  {
    return this.mTitle;
  }
  
  public double getX(int paramInt)
  {
    try
    {
      double d = ((Double)this.mXY.getXByIndex(paramInt)).doubleValue();
      return d;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public IndexXYMap<Double, Double> getXYMap()
  {
    try
    {
      IndexXYMap localIndexXYMap = this.mXY;
      return localIndexXYMap;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public double getY(int paramInt)
  {
    try
    {
      double d = ((Double)this.mXY.getYByIndex(paramInt)).doubleValue();
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
      XYEntry localXYEntry = this.mXY.removeByIndex(paramInt);
      double d1 = ((Double)localXYEntry.getKey()).doubleValue();
      double d2 = ((Double)localXYEntry.getValue()).doubleValue();
      if ((d1 == this.mMinX) || (d1 == this.mMaxX) || (d2 == this.mMinY) || (d2 == this.mMaxY)) {
        initRange();
      }
      return;
    }
    finally {}
  }
  
  public void removeAnnotation(int paramInt)
  {
    this.mAnnotations.remove(paramInt);
    this.mStringXY.removeByIndex(paramInt);
  }
  
  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/model/XYSeries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */