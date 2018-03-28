package com.pedometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.pedometer.Utils.GAConstants;
import com.pedometer.Utils.GoogleAnalyticsHelper;

public class Splashscreen
  extends Activity
{
  private final int SPLASH_DISPLAY_LENGTH = 3000;
  GoogleAnalyticsHelper mGoogleHelper;
  
  private void InitGoogleAnalytics()
  {
    this.mGoogleHelper = new GoogleAnalyticsHelper();
    this.mGoogleHelper.init(this);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968613);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(Splashscreen.this, Pedometer.class);
        Splashscreen.this.startActivity(localIntent);
        Splashscreen.this.finish();
      }
    }, 3000L);
    InitGoogleAnalytics();
    this.mGoogleHelper.SendScreenNameGA(this, GAConstants.SPLASH_SCREEN);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Splashscreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */