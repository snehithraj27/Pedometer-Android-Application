package com.pedometer.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class PaceNotifier
  implements StepListener, SpeakingTimer.Listener
{
  int mCounter = 0;
  int mDesiredPace;
  private long[] mLastStepDeltas = { -1L, -1L, -1L, -1L };
  private int mLastStepDeltasIndex = 0;
  private long mLastStepTime = 0L;
  private ArrayList<Listener> mListeners = new ArrayList();
  private long mPace = 0L;
  PedometerSettings mSettings;
  boolean mShouldTellFasterslower;
  private long mSpokenAt = 0L;
  Utils mUtils;
  
  public PaceNotifier(PedometerSettings paramPedometerSettings, Utils paramUtils)
  {
    this.mUtils = paramUtils;
    this.mSettings = paramPedometerSettings;
    this.mDesiredPace = this.mSettings.getDesiredPace();
    reloadSettings();
  }
  
  private void notifyListener()
  {
    Iterator localIterator = this.mListeners.iterator();
    while (localIterator.hasNext()) {
      ((Listener)localIterator.next()).paceChanged((int)this.mPace);
    }
  }
  
  public void addListener(Listener paramListener)
  {
    this.mListeners.add(paramListener);
  }
  
  public void onStep()
  {
    long l2 = System.currentTimeMillis();
    this.mCounter += 1;
    long l1;
    int i;
    if (this.mLastStepTime > 0L)
    {
      l1 = this.mLastStepTime;
      this.mLastStepDeltas[this.mLastStepDeltasIndex] = (l2 - l1);
      this.mLastStepDeltasIndex = ((this.mLastStepDeltasIndex + 1) % this.mLastStepDeltas.length);
      l1 = 0L;
      int k = 1;
      i = 0;
      int j = k;
      if (i < this.mLastStepDeltas.length)
      {
        if (this.mLastStepDeltas[i] >= 0L) {
          break label222;
        }
        j = 0;
      }
      if ((j == 0) || (l1 <= 0L)) {
        break label400;
      }
      l1 /= this.mLastStepDeltas.length;
      if (l1 > 0L) {
        this.mPace = (60000L / l1);
      }
      if ((this.mShouldTellFasterslower) && (!this.mUtils.isSpeakingEnabled()) && (l2 - this.mSpokenAt > 3000L) && (!this.mUtils.isSpeakingNow()))
      {
        i = 1;
        if ((float)this.mPace >= this.mDesiredPace * (1.0F - 0.5F)) {
          break label240;
        }
        this.mUtils.say("much faster!");
        label201:
        if (i != 0) {
          this.mSpokenAt = l2;
        }
      }
    }
    for (;;)
    {
      this.mLastStepTime = l2;
      notifyListener();
      return;
      label222:
      l1 += this.mLastStepDeltas[i];
      i += 1;
      break;
      label240:
      if ((float)this.mPace > this.mDesiredPace * (1.0F + 0.5F))
      {
        this.mUtils.say("much slower!");
        break label201;
      }
      if ((float)this.mPace < this.mDesiredPace * (1.0F - 0.3F))
      {
        this.mUtils.say("faster!");
        break label201;
      }
      if ((float)this.mPace > this.mDesiredPace * (1.0F + 0.3F))
      {
        this.mUtils.say("slower!");
        break label201;
      }
      if ((float)this.mPace < this.mDesiredPace * (1.0F - 0.1F))
      {
        this.mUtils.say("a little faster!");
        break label201;
      }
      if ((float)this.mPace > this.mDesiredPace * (1.0F + 0.1F))
      {
        this.mUtils.say("a little slower!");
        break label201;
      }
      i = 0;
      break label201;
      label400:
      this.mPace = -1L;
    }
  }
  
  public void passValue() {}
  
  public void reloadSettings()
  {
    if ((this.mSettings.shouldTellFasterslower()) && (this.mSettings.getMaintainOption() == PedometerSettings.M_PACE)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mShouldTellFasterslower = bool;
      notifyListener();
      return;
    }
  }
  
  public void setDesiredPace(int paramInt)
  {
    this.mDesiredPace = paramInt;
  }
  
  public void setPace(int paramInt)
  {
    this.mPace = paramInt;
    int i = (int)(60000.0D / this.mPace);
    paramInt = 0;
    while (paramInt < this.mLastStepDeltas.length)
    {
      this.mLastStepDeltas[paramInt] = i;
      paramInt += 1;
    }
    notifyListener();
  }
  
  public void speak()
  {
    if ((this.mSettings.shouldTellPace()) && (this.mPace > 0L)) {
      this.mUtils.say(this.mPace + " steps per minute");
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void paceChanged(int paramInt);
    
    public abstract void passValue();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/PaceNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */