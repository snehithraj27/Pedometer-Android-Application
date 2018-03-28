package com.google.android.gms.internal;

import android.os.SystemClock;

public final class zzmt
  implements zzmq
{
  private static zzmt zzaoa;
  
  public static zzmq zzsc()
  {
    try
    {
      if (zzaoa == null) {
        zzaoa = new zzmt();
      }
      zzmt localzzmt = zzaoa;
      return localzzmt;
    }
    finally {}
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
  
  public long nanoTime()
  {
    return System.nanoTime();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/internal/zzmt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */