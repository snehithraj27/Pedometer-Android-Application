package com.pedometer.Utils;

public class SpeedNotifier
  implements PaceNotifier.Listener, SpeakingTimer.Listener
{
  int mCounter = 0;
  float mDesiredSpeed;
  boolean mIsMetric;
  private Listener mListener;
  PedometerSettings mSettings;
  boolean mShouldTellFasterslower;
  boolean mShouldTellSpeed;
  float mSpeed = 0.0F;
  private long mSpokenAt = 0L;
  float mStepLength;
  Utils mUtils;
  
  public SpeedNotifier(Listener paramListener, PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mListener = paramListener;
    this.mUtils = paramUtils;
    this.mSettings = paramPedometerSettings;
    this.mDesiredSpeed = this.mSettings.getDesiredSpeed();
    reloadSettings();
  }
  
  private void notifyListener()
  {
    this.mListener.valueChanged(this.mSpeed);
  }
  
  private void tellFasterSlower()
  {
    long l;
    int i;
    if ((this.mShouldTellFasterslower) && (this.mUtils.isSpeakingEnabled()))
    {
      l = System.currentTimeMillis();
      if ((l - this.mSpokenAt > 3000L) && (!this.mUtils.isSpeakingNow()))
      {
        i = 1;
        if (this.mSpeed >= this.mDesiredSpeed * (1.0F - 0.5F)) {
          break label82;
        }
        this.mUtils.say("much faster!");
      }
    }
    for (;;)
    {
      if (i != 0) {
        this.mSpokenAt = l;
      }
      return;
      label82:
      if (this.mSpeed > this.mDesiredSpeed * (1.0F + 0.5F)) {
        this.mUtils.say("much slower!");
      } else if (this.mSpeed < this.mDesiredSpeed * (1.0F - 0.3F)) {
        this.mUtils.say("faster!");
      } else if (this.mSpeed > this.mDesiredSpeed * (1.0F + 0.3F)) {
        this.mUtils.say("slower!");
      } else if (this.mSpeed < this.mDesiredSpeed * (1.0F - 0.1F)) {
        this.mUtils.say("a little faster!");
      } else if (this.mSpeed > this.mDesiredSpeed * (1.0F + 0.1F)) {
        this.mUtils.say("a little slower!");
      } else {
        i = 0;
      }
    }
  }
  
  public void paceChanged(int paramInt)
  {
    if (this.mIsMetric) {}
    for (this.mSpeed = (paramInt * this.mStepLength / 100000.0F * 60.0F);; this.mSpeed = (paramInt * this.mStepLength / 63360.0F * 60.0F))
    {
      tellFasterSlower();
      notifyListener();
      return;
    }
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    this.mIsMetric = this.mSettings.isMetric();
    this.mStepLength = this.mSettings.getStepLength();
    this.mShouldTellSpeed = this.mSettings.shouldTellSpeed();
    if ((this.mSettings.shouldTellFasterslower()) && (this.mSettings.getMaintainOption() == PedometerSettings.M_SPEED)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mShouldTellFasterslower = bool;
      notifyListener();
      return;
    }
  }
  
  public void setDesiredSpeed(float paramFloat)
  {
    this.mDesiredSpeed = paramFloat;
  }
  
  public void setSpeed(float paramFloat)
  {
    this.mSpeed = paramFloat;
    notifyListener();
  }
  
  public void speak()
  {
    Utils localUtils;
    StringBuilder localStringBuilder;
    if ((this.mSettings.shouldTellSpeed()) && (this.mSpeed >= 0.01F))
    {
      localUtils = this.mUtils;
      localStringBuilder = new StringBuilder().append(("" + (this.mSpeed + 1.0E-6F)).substring(0, 4));
      if (!this.mIsMetric) {
        break label89;
      }
    }
    label89:
    for (String str = " kilometers per hour";; str = " miles per hour")
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


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/SpeedNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */