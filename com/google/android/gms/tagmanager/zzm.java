package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class zzm<K, V>
{
  final zza<K, V> zzbhK = new zza()
  {
    public int sizeOf(K paramAnonymousK, V paramAnonymousV)
    {
      return 1;
    }
  };
  
  int zzFY()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public zzl<K, V> zza(int paramInt, zza<K, V> paramzza)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (zzFY() < 12) {
      return new zzcw(paramInt, paramzza);
    }
    return new zzba(paramInt, paramzza);
  }
  
  public static abstract interface zza<K, V>
  {
    public abstract int sizeOf(K paramK, V paramV);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */