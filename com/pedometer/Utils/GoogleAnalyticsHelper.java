package com.pedometer.Utils;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;

public class GoogleAnalyticsHelper
{
  private static final String PRODUCTION_ID = "UA-77344610-1";
  private static String TAG = "GoogleAnalyticsHelper";
  private Tracker mGaTracker = null;
  
  public void SendEventGA(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    init(paramContext);
    this.mGaTracker.send(new HitBuilders.EventBuilder().setCategory(paramString1).setAction(paramString2).setLabel(paramString3).build());
  }
  
  public void SendScreenNameGA(Context paramContext, String paramString)
  {
    init(paramContext);
    this.mGaTracker.setScreenName(paramString);
    this.mGaTracker.send(new HitBuilders.AppViewBuilder().build());
  }
  
  public void init(Context paramContext)
  {
    try
    {
      if ((this.mGaTracker == null) && (paramContext != null)) {
        this.mGaTracker = GoogleAnalytics.getInstance(paramContext).newTracker("UA-77344610-1");
      }
      return;
    }
    catch (Exception paramContext)
    {
      Log.d(TAG, "init, e=" + paramContext);
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/GoogleAnalyticsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */