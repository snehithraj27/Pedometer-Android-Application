package com.pedometer.Utils;

public class CaloriesNotifier
  implements StepListener, SpeakingTimer.Listener
{
  private static double IMPERIAL_RUNNING_FACTOR;
  private static double IMPERIAL_WALKING_FACTOR = 0.517D;
  private static double METRIC_RUNNING_FACTOR = 1.02784823D;
  private static double METRIC_WALKING_FACTOR;
  float mBodyWeight;
  private double mCalories = 0.0D;
  boolean mIsMetric;
  boolean mIsRunning;
  private Listener mListener;
  PedometerSettings mSettings;
  float mStepLength;
  Utils mUtils;
  
  static
  {
    IMPERIAL_RUNNING_FACTOR = 0.75031498D;
    METRIC_WALKING_FACTOR = 0.708D;
  }
  
  public CaloriesNotifier(Listener paramListener, PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mListener = paramListener;
    this.mUtils = paramUtils;
    this.mSettings = paramPedometerSettings;
    reloadSettings();
  }
  
  private void notifyListener()
  {
    this.mListener.valueChanged((float)this.mCalories);
  }
  
  public void isMetric(boolean paramBoolean)
  {
    this.mIsMetric = paramBoolean;
  }
  
  public void onStep()
  {
    if (this.mIsMetric)
    {
      d2 = this.mCalories;
      d3 = this.mBodyWeight;
      if (this.mIsRunning) {}
      for (d1 = METRIC_RUNNING_FACTOR;; d1 = METRIC_WALKING_FACTOR)
      {
        this.mCalories = (d1 * d3 * this.mStepLength / 100000.0D + d2);
        notifyListener();
        return;
      }
    }
    double d2 = this.mCalories;
    double d3 = this.mBodyWeight;
    if (this.mIsRunning) {}
    for (double d1 = IMPERIAL_RUNNING_FACTOR;; d1 = IMPERIAL_WALKING_FACTOR)
    {
      this.mCalories = (d1 * d3 * this.mStepLength / 63360.0D + d2);
      break;
    }
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    this.mIsMetric = this.mSettings.isMetric();
    this.mIsRunning = this.mSettings.isRunning();
    this.mStepLength = this.mSettings.getStepLength();
    this.mBodyWeight = this.mSettings.getBodyWeight();
    notifyListener();
  }
  
  public void resetValues()
  {
    this.mCalories = 0.0D;
  }
  
  public void setCalories(float paramFloat)
  {
    this.mCalories = paramFloat;
    notifyListener();
  }
  
  public void setStepLength(float paramFloat)
  {
    this.mStepLength = paramFloat;
  }
  
  public void speak()
  {
    if ((this.mSettings.shouldTellCalories()) && (this.mCalories > 0.0D)) {
      this.mUtils.say("" + (int)this.mCalories + " calories burned");
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void passValue();
    
    public abstract void valueChanged(float paramFloat);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/CaloriesNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */