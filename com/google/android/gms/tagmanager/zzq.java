package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzq
  extends zzak
{
  private static final String ID = zzad.zzbA.toString();
  private final String zzadc;
  
  public zzq(String paramString)
  {
    super(ID, new String[0]);
    this.zzadc = paramString;
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    if (this.zzadc == null) {
      return zzdf.zzHF();
    }
    return zzdf.zzR(this.zzadc);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */