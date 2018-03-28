package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
class zzba<K, V>
  implements zzl<K, V>
{
  private LruCache<K, V> zzbjq;
  
  zzba(int paramInt, final zzm.zza<K, V> paramzza)
  {
    this.zzbjq = new LruCache(paramInt)
    {
      protected int sizeOf(K paramAnonymousK, V paramAnonymousV)
      {
        return paramzza.sizeOf(paramAnonymousK, paramAnonymousV);
      }
    };
  }
  
  public V get(K paramK)
  {
    return (V)this.zzbjq.get(paramK);
  }
  
  public void zzh(K paramK, V paramV)
  {
    this.zzbjq.put(paramK, paramV);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */