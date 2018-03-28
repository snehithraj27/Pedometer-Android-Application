package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzx;

public class BooleanResult
  implements Result
{
  private final Status zzUX;
  private final boolean zzagf;
  
  public BooleanResult(Status paramStatus, boolean paramBoolean)
  {
    this.zzUX = ((Status)zzx.zzb(paramStatus, "Status must not be null"));
    this.zzagf = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof BooleanResult)) {
        return false;
      }
      paramObject = (BooleanResult)paramObject;
    } while ((this.zzUX.equals(((BooleanResult)paramObject).zzUX)) && (this.zzagf == ((BooleanResult)paramObject).zzagf));
    return false;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  public boolean getValue()
  {
    return this.zzagf;
  }
  
  public final int hashCode()
  {
    int j = this.zzUX.hashCode();
    if (this.zzagf) {}
    for (int i = 1;; i = 0) {
      return i + (j + 527) * 31;
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/common/api/BooleanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */