package com.pedometer;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class BackGroundService
  extends Service
{
  boolean appInForeGround = false;
  Thread thread = null;
  
  private void code()
  {
    if (isAppOnForeground(this)) {
      if (!this.appInForeGround)
      {
        this.appInForeGround = true;
        new updateui().execute(new String[] { "1" });
      }
    }
    while (!this.appInForeGround) {
      return;
    }
    this.appInForeGround = false;
    new updateui().execute(new String[] { "3" });
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
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.thread = new Thread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          BackGroundService.this.code();
        }
      }
    });
    if (!this.thread.isAlive()) {
      this.thread.start();
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    try
    {
      if (this.thread != null) {
        this.thread.interrupt();
      }
      stopSelf();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return 1;
  }
  
  class updateui
    extends AsyncTask<String, Void, Void>
  {
    int value = -1;
    
    updateui() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      Log.v("xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxx 123---  " + this.value);
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


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/BackGroundService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */