package com.pedometer.Utils;

import android.content.Context;
import android.os.Vibrator;

public class StepBuzzer
  implements StepListener
{
  private Context mContext;
  private Vibrator mVibrator;
  
  public StepBuzzer(Context paramContext)
  {
    this.mContext = paramContext;
    this.mVibrator = ((Vibrator)this.mContext.getSystemService("vibrator"));
  }
  
  private void buzz()
  {
    this.mVibrator.vibrate(50L);
  }
  
  public void onStep()
  {
    buzz();
  }
  
  public void passValue() {}
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/StepBuzzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */