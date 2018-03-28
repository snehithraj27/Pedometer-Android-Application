package com.pedometer.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class StepDisplayer
  implements StepListener, SpeakingTimer.Listener
{
  private int mCount = 0;
  private ArrayList<Listener> mListeners = new ArrayList();
  PedometerSettings mSettings;
  Utils mUtils;
  
  public StepDisplayer(PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mUtils = paramUtils;
    this.mSettings = paramPedometerSettings;
    notifyListener();
  }
  
  public void addListener(Listener paramListener)
  {
    this.mListeners.add(paramListener);
  }
  
  public void notifyListener()
  {
    Iterator localIterator = this.mListeners.iterator();
    while (localIterator.hasNext()) {
      ((Listener)localIterator.next()).stepsChanged(this.mCount);
    }
  }
  
  public void onStep()
  {
    this.mCount += 1;
    notifyListener();
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    notifyListener();
  }
  
  public void setSteps(int paramInt)
  {
    this.mCount = paramInt;
    notifyListener();
  }
  
  public void setUtils(Utils paramUtils)
  {
    this.mUtils = paramUtils;
  }
  
  public void speak()
  {
    if ((this.mSettings.shouldTellSteps()) && (this.mCount > 0)) {
      this.mUtils.say("" + this.mCount + " steps");
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void passValue();
    
    public abstract void stepsChanged(int paramInt);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/StepDisplayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */