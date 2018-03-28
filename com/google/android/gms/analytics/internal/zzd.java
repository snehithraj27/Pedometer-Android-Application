package com.google.android.gms.analytics.internal;

public abstract class zzd
  extends zzc
{
  private boolean zzQk;
  private boolean zzQl;
  
  protected zzd(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public boolean isInitialized()
  {
    return (this.zzQk) && (!this.zzQl);
  }
  
  public void zza()
  {
    zziJ();
    this.zzQk = true;
  }
  
  protected abstract void zziJ();
  
  protected void zzjv()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/analytics/internal/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */