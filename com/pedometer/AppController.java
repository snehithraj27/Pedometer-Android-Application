package com.pedometer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

public class AppController
  extends Application
  implements Application.ActivityLifecycleCallbacks
{
  private static AppController mInstance;
  private String TAG = "AppController";
  boolean appInForeGround = false;
  
  public static AppController getInstance()
  {
    try
    {
      AppController localAppController = mInstance;
      return localAppController;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static boolean isAppOnForeground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!((Iterator)localObject).hasNext())
      {
        return false;
        paramContext.getPackageName();
        localObject = ((List)localObject).iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while ((localRunningAppProcessInfo.importance != 100) || (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())));
    return true;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity)
  {
    if (((paramActivity instanceof Pedometer)) && (!isAppOnForeground(this)) && (this.appInForeGround))
    {
      this.appInForeGround = false;
      new updateui().execute(new String[] { "3" });
    }
  }
  
  public void onActivityResumed(Activity paramActivity)
  {
    if (((paramActivity instanceof Pedometer)) && (isAppOnForeground(this)) && (!this.appInForeGround))
    {
      this.appInForeGround = true;
      new updateui().execute(new String[] { "1" });
    }
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity) {}
  
  public void onActivityStopped(Activity paramActivity)
  {
    if ((!isAppOnForeground(this)) && (this.appInForeGround))
    {
      this.appInForeGround = false;
      new updateui().execute(new String[] { "3" });
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    mInstance = this;
    registerActivityLifecycleCallbacks(this);
  }
  
  class updateui
    extends AsyncTask<String, Void, Void>
  {
    int value = -1;
    
    updateui() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      try
      {
        this.value = Integer.valueOf(paramVarArgs[0]).intValue();
        return null;
      }
      catch (NumberFormatException paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/AppController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */