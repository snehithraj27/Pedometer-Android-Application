package com.pedometer.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class SpeakingTimer
  implements StepListener
{
  float mInterval;
  long mLastSpeakTime = System.currentTimeMillis();
  private ArrayList<Listener> mListeners = new ArrayList();
  PedometerSettings mSettings;
  boolean mShouldSpeak;
  Utils mUtils;
  
  public SpeakingTimer(PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mSettings = paramPedometerSettings;
    this.mUtils = paramUtils;
    reloadSettings();
  }
  
  public void addListener(Listener paramListener)
  {
    this.mListeners.add(paramListener);
  }
  
  public boolean isSpeaking()
  {
    return this.mUtils.isSpeakingNow();
  }
  
  public void notifyListeners()
  {
    this.mUtils.ding();
    Iterator localIterator = this.mListeners.iterator();
    while (localIterator.hasNext()) {
      ((Listener)localIterator.next()).speak();
    }
  }
  
  public void onStep()
  {
    long l = System.currentTimeMillis();
    if ((l - this.mLastSpeakTime) / 60000.0D >= this.mInterval)
    {
      this.mLastSpeakTime = l;
      notifyListeners();
    }
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    this.mShouldSpeak = this.mSettings.shouldSpeak();
    this.mInterval = this.mSettings.getSpeakingInterval();
  }
  
  public static abstract interface Listener
  {
    public abstract void speak();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/SpeakingTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */