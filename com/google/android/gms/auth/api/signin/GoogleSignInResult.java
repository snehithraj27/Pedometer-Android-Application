package com.google.android.gms.auth.api.signin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class GoogleSignInResult
  implements Result
{
  private Status zzUX;
  private GoogleSignInAccount zzXg;
  
  public GoogleSignInResult(@Nullable GoogleSignInAccount paramGoogleSignInAccount, @NonNull Status paramStatus)
  {
    this.zzXg = paramGoogleSignInAccount;
    this.zzUX = paramStatus;
  }
  
  @Nullable
  public GoogleSignInAccount getSignInAccount()
  {
    return this.zzXg;
  }
  
  @NonNull
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  public boolean isSuccess()
  {
    return this.zzUX.isSuccess();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/google/android/gms/auth/api/signin/GoogleSignInResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */