package com.pedometer.Utils;

public class DistanceNotifier
  implements StepListener, SpeakingTimer.Listener
{
  float mDistance = 0.0F;
  boolean mIsMetric;
  private Listener mListener;
  PedometerSettings mSettings;
  float mStepLength;
  Utils mUtils;
  
  public DistanceNotifier(Listener paramListener, PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mListener = paramListener;
    this.mUtils = paramUtils;
    this.mSettings = paramPedometerSettings;
    reloadSettings();
  }
  
  private void notifyListener()
  {
    this.mListener.valueChanged(this.mDistance);
  }
  
  public void onStep()
  {
    if (this.mIsMetric) {}
    for (this.mDistance += (float)(this.mStepLength / 100000.0D);; this.mDistance += (float)(this.mStepLength / 63360.0D))
    {
      notifyListener();
      return;
    }
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    this.mIsMetric = this.mSettings.isMetric();
    this.mStepLength = this.mSettings.getStepLength();
    notifyListener();
  }
  
  public void setDistance(float paramFloat)
  {
    this.mDistance = paramFloat;
    notifyListener();
  }
  
  public void speak()
  {
    Utils localUtils;
    StringBuilder localStringBuilder;
    if ((this.mSettings.shouldTellDistance()) && (this.mDistance >= 0.001F))
    {
      localUtils = this.mUtils;
      localStringBuilder = new StringBuilder().append(("" + (this.mDistance + 1.0E-6F)).substring(0, 4));
      if (!this.mIsMetric) {
        break label89;
      }
    }
    label89:
    for (String str = " kilometers";; str = " miles")
    {
      localUtils.say(str);
      return;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void passValue();
    
    public abstract void valueChanged(float paramFloat);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/DistanceNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */