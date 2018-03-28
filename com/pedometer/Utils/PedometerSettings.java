package com.pedometer.Utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PedometerSettings
{
  public static int M_NONE = 1;
  public static int M_PACE = 2;
  public static int M_SPEED = 3;
  SharedPreferences mSettings;
  
  public PedometerSettings(SharedPreferences paramSharedPreferences)
  {
    this.mSettings = paramSharedPreferences;
  }
  
  public void clearServiceRunning()
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", false);
    localEditor.putLong("last_seen", 0L);
    localEditor.commit();
  }
  
  public float getBodyWeight()
  {
    try
    {
      float f = Float.valueOf(this.mSettings.getString("body_weight", "50").trim()).floatValue();
      return f;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0.0F;
  }
  
  public int getDesiredPace()
  {
    return this.mSettings.getInt("desired_pace", 180);
  }
  
  public float getDesiredSpeed()
  {
    return this.mSettings.getFloat("desired_speed", 4.0F);
  }
  
  public int getMaintainOption()
  {
    String str = this.mSettings.getString("maintain", "none");
    if (str.equals("none")) {
      return M_NONE;
    }
    if (str.equals("pace")) {
      return M_PACE;
    }
    if (str.equals("speed")) {
      return M_SPEED;
    }
    return 0;
  }
  
  public float getSpeakingInterval()
  {
    try
    {
      float f = Float.valueOf(this.mSettings.getString("speaking_interval", "1")).floatValue();
      return f;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 1.0F;
  }
  
  public float getStepLength()
  {
    try
    {
      float f = Float.valueOf(this.mSettings.getString("step_length", "20").trim()).floatValue();
      return f;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0.0F;
  }
  
  public boolean isMetric()
  {
    return this.mSettings.getString("units", "imperial").equals("metric");
  }
  
  public boolean isNewStart()
  {
    return this.mSettings.getLong("last_seen", 0L) < Utils.currentTimeInMillis() - 600000L;
  }
  
  public boolean isRunning()
  {
    return this.mSettings.getString("exercise_type", "running").equals("running");
  }
  
  public boolean isServiceRunning()
  {
    return this.mSettings.getBoolean("service_running", false);
  }
  
  public boolean keepScreenOn()
  {
    return this.mSettings.getString("operation_level", "run_in_background").equals("keep_screen_on");
  }
  
  public void savePaceOrSpeedSetting(int paramInt, float paramFloat)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    if (paramInt == M_PACE) {
      localEditor.putInt("desired_pace", (int)paramFloat);
    }
    for (;;)
    {
      localEditor.commit();
      return;
      if (paramInt == M_SPEED) {
        localEditor.putFloat("desired_speed", paramFloat);
      }
    }
  }
  
  public void saveServiceRunningWithNullTimestamp(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", paramBoolean);
    localEditor.putLong("last_seen", 0L);
    localEditor.commit();
  }
  
  public void saveServiceRunningWithTimestamp(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", paramBoolean);
    localEditor.putLong("last_seen", Utils.currentTimeInMillis());
    localEditor.commit();
  }
  
  public boolean shouldSpeak()
  {
    return this.mSettings.getBoolean("speak", false);
  }
  
  public boolean shouldTellCalories()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_calories", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean shouldTellDistance()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_distance", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean shouldTellFasterslower()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_fasterslower", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean shouldTellPace()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_pace", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean shouldTellSpeed()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_speed", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean shouldTellSteps()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mSettings.getBoolean("speak", false))
    {
      bool1 = bool2;
      if (this.mSettings.getBoolean("tell_steps", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean wakeAggressively()
  {
    return this.mSettings.getString("operation_level", "run_in_background").equals("wake_up");
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/PedometerSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */