package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzcr
  extends zzak
{
  private static final String ID = zzad.zzbT.toString();
  
  public zzcr()
  {
    super(ID, new String[0]);
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    return zzdf.zzR(Integer.valueOf(Build.VERSION.SDK_INT));
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzcr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */