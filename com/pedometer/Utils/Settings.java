package com.pedometer.Utils;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings
  extends PreferenceActivity
{
  GoogleAnalyticsHelper mGoogleHelper;
  
  private void InitGoogleAnalytics()
  {
    this.mGoogleHelper = new GoogleAnalyticsHelper();
    this.mGoogleHelper.init(this);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131099648);
    InitGoogleAnalytics();
    this.mGoogleHelper.SendScreenNameGA(this, GAConstants.SETTINGS_SCREEN);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */