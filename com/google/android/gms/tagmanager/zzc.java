package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzc
  extends zzak
{
  private static final String ID = zzad.zzbs.toString();
  private final zza zzbhC;
  
  public zzc(Context paramContext)
  {
    this(zza.zzaW(paramContext));
  }
  
  zzc(zza paramzza)
  {
    super(ID, new String[0]);
    this.zzbhC = paramzza;
  }
  
  public boolean zzFW()
  {
    return false;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    if (!this.zzbhC.isLimitAdTrackingEnabled()) {}
    for (boolean bool = true;; bool = false) {
      return zzdf.zzR(Boolean.valueOf(bool));
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */