package com.pedometer.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat.Builder;
import com.pedometer.Database.ResultModel;
import com.pedometer.Database.SQLiteDBHelper;
import com.pedometer.Pedometer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StepService
  extends Service
{
  private static final String TAG = "com.pedometer.StepService";
  private static float mCalories = 0.0F;
  private static float mDistance;
  private static int mPace;
  private static float mSpeed;
  private static int mSteps = 0;
  public final IBinder mBinder = new StepBinder();
  private ICallback mCallback;
  private CaloriesNotifier.Listener mCaloriesListener = new CaloriesNotifier.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null)
      {
        StepService.this.CreateOrUpdateRecord();
        StepService.this.mCallback.caloriesChanged(StepService.mCalories);
      }
    }
    
    public void valueChanged(float paramAnonymousFloat)
    {
      StepService.access$602(paramAnonymousFloat);
      passValue();
    }
  };
  private CaloriesNotifier mCaloriesNotifier;
  private int mDesiredPace;
  private float mDesiredSpeed;
  private DistanceNotifier.Listener mDistanceListener = new DistanceNotifier.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null)
      {
        StepService.this.CreateOrUpdateRecord();
        StepService.this.mCallback.distanceChanged(StepService.mDistance);
      }
    }
    
    public void valueChanged(float paramAnonymousFloat)
    {
      StepService.access$402(paramAnonymousFloat);
      passValue();
    }
  };
  private DistanceNotifier mDistanceNotifier;
  private NotificationManager mNM;
  private PaceNotifier.Listener mPaceListener = new PaceNotifier.Listener()
  {
    public void paceChanged(int paramAnonymousInt)
    {
      StepService.access$302(paramAnonymousInt);
      passValue();
    }
    
    public void passValue()
    {
      if (StepService.this.mCallback != null)
      {
        StepService.this.CreateOrUpdateRecord();
        StepService.this.mCallback.paceChanged(StepService.mPace);
      }
    }
  };
  private PaceNotifier mPaceNotifier;
  private PedometerSettings mPedometerSettings;
  private BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
      {
        StepService.this.unregisterDetector();
        StepService.this.registerDetector();
        if (StepService.this.mPedometerSettings.wakeAggressively())
        {
          StepService.this.wakeLock.release();
          StepService.this.acquireWakeLock();
        }
      }
    }
  };
  private Sensor mSensor;
  private SensorManager mSensorManager;
  private SharedPreferences mSettings;
  private SpeakingTimer mSpeakingTimer;
  private SpeedNotifier.Listener mSpeedListener = new SpeedNotifier.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null)
      {
        StepService.this.CreateOrUpdateRecord();
        StepService.this.mCallback.speedChanged(StepService.mSpeed);
      }
    }
    
    public void valueChanged(float paramAnonymousFloat)
    {
      StepService.access$502(paramAnonymousFloat);
      passValue();
    }
  };
  private SpeedNotifier mSpeedNotifier;
  private SharedPreferences mState;
  private SharedPreferences.Editor mStateEditor;
  private StepDetector mStepDetector;
  private StepDisplayer mStepDisplayer;
  private StepDisplayer.Listener mStepListener = new StepDisplayer.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null)
      {
        StepService.this.CreateOrUpdateRecord();
        StepService.this.mCallback.stepsChanged(StepService.mSteps);
      }
    }
    
    public void stepsChanged(int paramAnonymousInt)
    {
      StepService.access$002(paramAnonymousInt);
      passValue();
    }
  };
  private Utils mUtils;
  private PowerManager.WakeLock wakeLock;
  
  static
  {
    mPace = 0;
    mDistance = 0.0F;
    mSpeed = 0.0F;
  }
  
  private void CreateOrUpdateRecord()
  {
    ResultModel localResultModel = new ResultModel();
    localResultModel.setSpeed(mSpeed);
    localResultModel.setPace(mPace);
    localResultModel.setCalories((int)mCalories);
    localResultModel.setDistance(mDistance);
    localResultModel.setSteps(mSteps);
    Date localDate = new Date();
    new SimpleDateFormat("hh:mm a").format(localDate);
    Utils.sqLiteDBHelper.updateRecord(localResultModel, Utils.getCurrentDate());
  }
  
  private void acquireWakeLock()
  {
    PowerManager localPowerManager = (PowerManager)getSystemService("power");
    int i;
    if (this.mPedometerSettings.wakeAggressively()) {
      i = 268435462;
    }
    for (;;)
    {
      this.wakeLock = localPowerManager.newWakeLock(i, "com.pedometer.StepService");
      this.wakeLock.acquire();
      return;
      if (this.mPedometerSettings.keepScreenOn()) {
        i = 6;
      } else {
        i = 1;
      }
    }
  }
  
  private void registerDetector()
  {
    this.mSensor = this.mSensorManager.getDefaultSensor(1);
    this.mSensorManager.registerListener(this.mStepDetector, this.mSensor, 0);
  }
  
  private void showNotification()
  {
    Object localObject1 = getText(2131230742);
    new Notification(2130837581, null, System.currentTimeMillis()).flags = 34;
    Object localObject2 = new Intent();
    ((Intent)localObject2).setComponent(new ComponentName(this, Pedometer.class));
    ((Intent)localObject2).addFlags(67108864);
    localObject2 = PendingIntent.getActivity(this, 0, (Intent)localObject2, 0);
    localObject1 = new NotificationCompat.Builder(this).setContentIntent((PendingIntent)localObject2).setSmallIcon(2130837581).setTicker((CharSequence)localObject1).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentTitle((CharSequence)localObject1).setContentText("Counting steps").build();
    this.mNM.notify(2131230742, (Notification)localObject1);
  }
  
  private void unregisterDetector()
  {
    this.mSensorManager.unregisterListener(this.mStepDetector);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.mNM = ((NotificationManager)getSystemService("notification"));
    showNotification();
    this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);
    this.mPedometerSettings = new PedometerSettings(this.mSettings);
    this.mState = getSharedPreferences("state", 0);
    this.mUtils = Utils.getInstance();
    this.mUtils.setService(this);
    this.mUtils.initTTS();
    acquireWakeLock();
    this.mStepDetector = new StepDetector();
    this.mSensorManager = ((SensorManager)getSystemService("sensor"));
    registerDetector();
    Object localObject = new IntentFilter("android.intent.action.SCREEN_OFF");
    registerReceiver(this.mReceiver, (IntentFilter)localObject);
    this.mStepDisplayer = new StepDisplayer(this.mPedometerSettings, this.mUtils);
    localObject = this.mStepDisplayer;
    int i = this.mState.getInt("steps", mSteps);
    mSteps = i;
    ((StepDisplayer)localObject).setSteps(i);
    this.mStepDisplayer.addListener(this.mStepListener);
    this.mStepDetector.addStepListener(this.mStepDisplayer);
    this.mPaceNotifier = new PaceNotifier(this.mPedometerSettings, this.mUtils);
    localObject = this.mPaceNotifier;
    i = this.mState.getInt("pace", mPace);
    mPace = i;
    ((PaceNotifier)localObject).setPace(i);
    this.mPaceNotifier.addListener(this.mPaceListener);
    this.mStepDetector.addStepListener(this.mPaceNotifier);
    this.mDistanceNotifier = new DistanceNotifier(this.mDistanceListener, this.mPedometerSettings, this.mUtils);
    localObject = this.mDistanceNotifier;
    float f = this.mState.getFloat("distance", mDistance);
    mDistance = f;
    ((DistanceNotifier)localObject).setDistance(f);
    this.mStepDetector.addStepListener(this.mDistanceNotifier);
    this.mSpeedNotifier = new SpeedNotifier(this.mSpeedListener, this.mPedometerSettings, this.mUtils);
    localObject = this.mSpeedNotifier;
    f = this.mState.getFloat("speed", mSpeed);
    mSpeed = f;
    ((SpeedNotifier)localObject).setSpeed(f);
    this.mPaceNotifier.addListener(this.mSpeedNotifier);
    this.mCaloriesNotifier = new CaloriesNotifier(this.mCaloriesListener, this.mPedometerSettings, this.mUtils);
    localObject = this.mCaloriesNotifier;
    f = this.mState.getFloat("calories", mCalories);
    mCalories = f;
    ((CaloriesNotifier)localObject).setCalories(f);
    this.mStepDetector.addStepListener(this.mCaloriesNotifier);
    this.mSpeakingTimer = new SpeakingTimer(this.mPedometerSettings, this.mUtils);
    this.mSpeakingTimer.addListener(this.mStepDisplayer);
    this.mSpeakingTimer.addListener(this.mPaceNotifier);
    this.mSpeakingTimer.addListener(this.mDistanceNotifier);
    this.mSpeakingTimer.addListener(this.mSpeedNotifier);
    this.mSpeakingTimer.addListener(this.mCaloriesNotifier);
    this.mStepDetector.addStepListener(this.mSpeakingTimer);
    reloadSettings();
  }
  
  public void onDestroy()
  {
    this.mUtils.shutdownTTS();
    unregisterReceiver(this.mReceiver);
    unregisterDetector();
    CreateOrUpdateRecord();
    this.mNM.cancel(2131230742);
    super.onDestroy();
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
  }
  
  public void registerCallback(ICallback paramICallback)
  {
    this.mCallback = paramICallback;
  }
  
  public void reloadSettings()
  {
    this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);
    if (this.mStepDetector != null) {
      this.mStepDetector.setSensitivity(Float.valueOf(this.mSettings.getString("sensitivity", "10")).floatValue());
    }
    if (this.mStepDisplayer != null) {
      this.mStepDisplayer.reloadSettings();
    }
    if (this.mPaceNotifier != null) {
      this.mPaceNotifier.reloadSettings();
    }
    if (this.mDistanceNotifier != null) {
      this.mDistanceNotifier.reloadSettings();
    }
    if (this.mSpeedNotifier != null) {
      this.mSpeedNotifier.reloadSettings();
    }
    if (this.mCaloriesNotifier != null) {
      this.mCaloriesNotifier.reloadSettings();
    }
    if (this.mSpeakingTimer != null) {
      this.mSpeakingTimer.reloadSettings();
    }
  }
  
  public void resetValues()
  {
    this.mStepDisplayer.setSteps(0);
    this.mPaceNotifier.setPace(0);
    this.mDistanceNotifier.setDistance(0.0F);
    this.mSpeedNotifier.setSpeed(0.0F);
    this.mCaloriesNotifier.setCalories(0.0F);
  }
  
  public void setDesiredPace(int paramInt)
  {
    this.mDesiredPace = paramInt;
    if (this.mPaceNotifier != null) {
      this.mPaceNotifier.setDesiredPace(this.mDesiredPace);
    }
  }
  
  public void setDesiredSpeed(float paramFloat)
  {
    this.mDesiredSpeed = paramFloat;
    if (this.mSpeedNotifier != null) {
      this.mSpeedNotifier.setDesiredSpeed(this.mDesiredSpeed);
    }
  }
  
  public static abstract interface ICallback
  {
    public abstract void caloriesChanged(float paramFloat);
    
    public abstract void distanceChanged(float paramFloat);
    
    public abstract void paceChanged(int paramInt);
    
    public abstract void speedChanged(float paramFloat);
    
    public abstract void stepsChanged(int paramInt);
  }
  
  public class StepBinder
    extends Binder
  {
    public StepBinder() {}
    
    public StepService getService()
    {
      return StepService.this;
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/StepService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */