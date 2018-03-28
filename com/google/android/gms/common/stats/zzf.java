package com.google.android.gms.common.stats;

public abstract class zzf
{
  public abstract int getEventType();
  
  public abstract long getTimeMillis();
  
  public String toString()
  {
    return getTimeMillis() + "\t" + getEventType() + "\t" + zzrL() + zzrO();
  }
  
  public abstract long zzrL();
  
  public abstract String zzrO();
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/common/stats/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */