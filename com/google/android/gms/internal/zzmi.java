package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;

abstract class zzmi<R extends Result>
  extends zza.zza<R, zzmj>
{
  public zzmi(GoogleApiClient paramGoogleApiClient)
  {
    super(zzmf.zzUI, paramGoogleApiClient);
  }
  
  static abstract class zza
    extends zzmi<Status>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/internal/zzmi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */