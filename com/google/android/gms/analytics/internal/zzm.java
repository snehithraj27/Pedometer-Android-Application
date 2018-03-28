package com.google.android.gms.analytics.internal;

public enum zzm
{
  private zzm() {}
  
  public static zzm zzbm(String paramString)
  {
    if ("BATCH_BY_SESSION".equalsIgnoreCase(paramString)) {
      return zzRl;
    }
    if ("BATCH_BY_TIME".equalsIgnoreCase(paramString)) {
      return zzRm;
    }
    if ("BATCH_BY_BRUTE_FORCE".equalsIgnoreCase(paramString)) {
      return zzRn;
    }
    if ("BATCH_BY_COUNT".equalsIgnoreCase(paramString)) {
      return zzRo;
    }
    if ("BATCH_BY_SIZE".equalsIgnoreCase(paramString)) {
      return zzRp;
    }
    return zzRk;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/analytics/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */