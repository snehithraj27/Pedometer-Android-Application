package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;

class zzaj
{
  private long zzCv;
  private final zzmq zzqW;
  
  public zzaj(zzmq paramzzmq)
  {
    zzx.zzz(paramzzmq);
    this.zzqW = paramzzmq;
  }
  
  public zzaj(zzmq paramzzmq, long paramLong)
  {
    zzx.zzz(paramzzmq);
    this.zzqW = paramzzmq;
    this.zzCv = paramLong;
  }
  
  public void clear()
  {
    this.zzCv = 0L;
  }
  
  public void start()
  {
    this.zzCv = this.zzqW.elapsedRealtime();
  }
  
  public boolean zzv(long paramLong)
  {
    if (this.zzCv == 0L) {}
    while (this.zzqW.elapsedRealtime() - this.zzCv > paramLong) {
      return true;
    }
    return false;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/analytics/internal/zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */