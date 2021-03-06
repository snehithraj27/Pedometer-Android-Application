package com.google.android.gms.auth.api.signin;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.R.string;

public enum zzd
{
  private final String zzVY;
  private final String zzXj;
  private final int zzXk;
  
  private zzd(String paramString1, int paramInt, String paramString2)
  {
    this.zzXj = paramString1;
    this.zzXk = paramInt;
    this.zzVY = paramString2;
  }
  
  public static zzd zzbL(String paramString)
  {
    if (paramString != null)
    {
      zzd[] arrayOfzzd = values();
      int j = arrayOfzzd.length;
      int i = 0;
      while (i < j)
      {
        zzd localzzd = arrayOfzzd[i];
        if (localzzd.zzmT().equals(paramString)) {
          return localzzd;
        }
        i += 1;
      }
      Log.w("IdProvider", "Unrecognized providerId: " + paramString);
    }
    return null;
  }
  
  public String toString()
  {
    return this.zzXj;
  }
  
  public CharSequence zzae(Context paramContext)
  {
    return paramContext.getResources().getString(this.zzXk);
  }
  
  public String zzmT()
  {
    return this.zzXj;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/auth/api/signin/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */