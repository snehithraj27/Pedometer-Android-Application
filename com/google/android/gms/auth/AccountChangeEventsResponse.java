package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.List;

public class AccountChangeEventsResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<AccountChangeEventsResponse> CREATOR = new zzc();
  final int mVersion;
  final List<AccountChangeEvent> zzpH;
  
  AccountChangeEventsResponse(int paramInt, List<AccountChangeEvent> paramList)
  {
    this.mVersion = paramInt;
    this.zzpH = ((List)zzx.zzz(paramList));
  }
  
  public AccountChangeEventsResponse(List<AccountChangeEvent> paramList)
  {
    this.mVersion = 1;
    this.zzpH = ((List)zzx.zzz(paramList));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<AccountChangeEvent> getEvents()
  {
    return this.zzpH;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/auth/AccountChangeEventsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */