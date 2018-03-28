package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class zzad<T extends IInterface>
  extends zzj<T>
{
  private final Api.zzd<T> zzamx;
  
  public zzad(Context paramContext, Looper paramLooper, int paramInt, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, zzf paramzzf, Api.zzd paramzzd)
  {
    super(paramContext, paramLooper, paramInt, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzamx = paramzzd;
  }
  
  protected T zzW(IBinder paramIBinder)
  {
    return this.zzamx.zzW(paramIBinder);
  }
  
  protected void zzc(int paramInt, T paramT)
  {
    this.zzamx.zza(paramInt, paramT);
  }
  
  protected String zzgu()
  {
    return this.zzamx.zzgu();
  }
  
  protected String zzgv()
  {
    return this.zzamx.zzgv();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/common/internal/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */