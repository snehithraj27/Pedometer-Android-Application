package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzaq
{
  private final long zzSL;
  private final long zzbiX;
  private final long zzbiY;
  private String zzbiZ;
  
  zzaq(long paramLong1, long paramLong2, long paramLong3)
  {
    this.zzbiX = paramLong1;
    this.zzSL = paramLong2;
    this.zzbiY = paramLong3;
  }
  
  long zzGD()
  {
    return this.zzbiX;
  }
  
  long zzGE()
  {
    return this.zzbiY;
  }
  
  String zzGF()
  {
    return this.zzbiZ;
  }
  
  void zzgf(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim()))) {
      return;
    }
    this.zzbiZ = paramString;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/tagmanager/zzaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */