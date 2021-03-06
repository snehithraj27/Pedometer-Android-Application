package com.google.android.gms.internal;

import com.google.android.gms.measurement.zze;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzkc
  extends zze<zzkc>
{
  private Map<Integer, Double> zzPM = new HashMap(4);
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.zzPM.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put("metric" + localEntry.getKey(), localEntry.getValue());
    }
    return zzF(localHashMap);
  }
  
  public void zza(zzkc paramzzkc)
  {
    paramzzkc.zzPM.putAll(this.zzPM);
  }
  
  public Map<Integer, Double> zziQ()
  {
    return Collections.unmodifiableMap(this.zzPM);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/internal/zzkc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */