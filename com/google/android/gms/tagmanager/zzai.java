package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzai
  extends zzak
{
  private static final String ID = zzad.zzbI.toString();
  private final zzcp zzbhO;
  
  public zzai(zzcp paramzzcp)
  {
    super(ID, new String[0]);
    this.zzbhO = paramzzcp;
  }
  
  public boolean zzFW()
  {
    return false;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    paramMap = this.zzbhO.zzHe();
    if (paramMap == null) {
      return zzdf.zzHF();
    }
    return zzdf.zzR(paramMap);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */