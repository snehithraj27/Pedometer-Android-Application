package com.pedometer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pedometer.Database.ResultModel;
import com.pedometer.Database.SQLiteDBHelper;
import com.pedometer.Utils.BarGraph;
import com.pedometer.Utils.FloatingActionButton;
import com.pedometer.Utils.FloatingActionButton.Builder;
import com.pedometer.Utils.GAConstants;
import com.pedometer.Utils.GoogleAnalyticsHelper;
import com.pedometer.Utils.PedometerSettings;
import com.pedometer.Utils.Settings;
import com.pedometer.Utils.StepService;
import com.pedometer.Utils.StepService.ICallback;
import com.pedometer.Utils.StepService.StepBinder;
import com.pedometer.Utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pedometer
  extends Activity
{
  private static final int CALORIES_MSG = 5;
  private static final int DISTANCE_MSG = 3;
  private static final int MENU_PAUSE = 1;
  private static final int MENU_QUIT = 9;
  private static final int MENU_RESET = 3;
  private static final int MENU_RESUME = 2;
  private static final int MENU_SETTINGS = 8;
  private static final int PACE_MSG = 2;
  private static final int SPEED_MSG = 4;
  private static final int STEPS_MSG = 1;
  private static final String TAG = "Pedometer";
  public static ArrayList<Integer> arrSteps = new ArrayList();
  boolean appInForeGround = false;
  FloatingActionButton fBtnStat;
  LinearLayout ll_mailayout;
  private StepService.ICallback mCallback = new StepService.ICallback()
  {
    public void caloriesChanged(float paramAnonymousFloat)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(5, (int)paramAnonymousFloat, 0));
    }
    
    public void distanceChanged(float paramAnonymousFloat)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(3, (int)(1000.0F * paramAnonymousFloat), 0));
    }
    
    public void paceChanged(int paramAnonymousInt)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(2, paramAnonymousInt, 0));
    }
    
    public void speedChanged(float paramAnonymousFloat)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(4, (int)(1000.0F * paramAnonymousFloat), 0));
    }
    
    public void stepsChanged(int paramAnonymousInt)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(1, paramAnonymousInt, 0));
    }
  };
  private int mCaloriesValue;
  private TextView mCaloriesValueView;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Pedometer.access$402(Pedometer.this, ((StepService.StepBinder)paramAnonymousIBinder).getService());
      Pedometer.this.mService.registerCallback(Pedometer.this.mCallback);
      Pedometer.this.mService.reloadSettings();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Pedometer.access$402(Pedometer.this, null);
    }
  };
  private float mDesiredPaceOrSpeed;
  TextView mDesiredPaceView;
  private float mDistanceValue;
  private TextView mDistanceValueView;
  GoogleAnalyticsHelper mGoogleHelper;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        super.handleMessage(paramAnonymousMessage);
      case 1: 
      case 2: 
      case 3: 
        do
        {
          do
          {
            return;
            Pedometer.access$702(Pedometer.this, paramAnonymousMessage.arg1);
            Pedometer.this.mStepValueView.setText("" + Pedometer.this.mStepValue);
            return;
            Pedometer.access$902(Pedometer.this, paramAnonymousMessage.arg1);
          } while (Pedometer.this.mPaceValue <= 0);
          Pedometer.this.mPaceValueView.setText("" + Pedometer.this.mPaceValue);
          return;
          Pedometer.access$1102(Pedometer.this, paramAnonymousMessage.arg1 / 1000.0F);
        } while (Pedometer.this.mDistanceValue <= 0.0F);
        Pedometer.this.mDistanceValueView.setText(("" + (Pedometer.this.mDistanceValue + 1.0E-6F)).substring(0, 5));
        return;
      case 4: 
        Pedometer.access$1302(Pedometer.this, paramAnonymousMessage.arg1 / 1000.0F);
        if (Pedometer.this.mSpeedValue <= 0.0F)
        {
          Pedometer.this.mSpeedValueView.setText("0");
          return;
        }
        Pedometer.this.mSpeedValueView.setText(("" + (Pedometer.this.mSpeedValue + 1.0E-6F)).substring(0, 4));
        return;
      }
      Pedometer.access$1502(Pedometer.this, paramAnonymousMessage.arg1);
      if (Pedometer.this.mCaloriesValue <= 0)
      {
        Pedometer.this.mCaloriesValueView.setText("0");
        return;
      }
      Pedometer.this.mCaloriesValueView.setText("" + Pedometer.this.mCaloriesValue);
    }
  };
  private boolean mIsMetric;
  private boolean mIsRunning;
  private int mMaintain;
  private float mMaintainInc;
  private int mPaceValue;
  private TextView mPaceValueView;
  private PedometerSettings mPedometerSettings;
  private boolean mQuitting = false;
  private StepService mService;
  private SharedPreferences mSettings;
  private float mSpeedValue;
  private TextView mSpeedValueView;
  private int mStepValue;
  private TextView mStepValueView;
  private Utils mUtils;
  LinearLayout splash_screen;
  SQLiteDatabase sqLiteDatabase;
  
  private void InitGoogleAnalytics()
  {
    this.mGoogleHelper = new GoogleAnalyticsHelper();
    this.mGoogleHelper.init(this);
  }
  
  private void bindStepService()
  {
    Log.i("Pedometer", "[SERVICE] Bind");
    bindService(new Intent(this, StepService.class), this.mConnection, 3);
  }
  
  private void displayDesiredPaceOrSpeed()
  {
    if (this.mMaintain == PedometerSettings.M_PACE)
    {
      this.mDesiredPaceView.setText("" + (int)this.mDesiredPaceOrSpeed);
      return;
    }
    this.mDesiredPaceView.setText("" + this.mDesiredPaceOrSpeed);
  }
  
  private void initilizeviews()
  {
    this.ll_mailayout = ((LinearLayout)findViewById(2131689554));
    this.mStepValueView = ((TextView)findViewById(2131689557));
    this.mPaceValueView = ((TextView)findViewById(2131689564));
    this.mDistanceValueView = ((TextView)findViewById(2131689561));
    this.mSpeedValueView = ((TextView)findViewById(2131689567));
    this.mCaloriesValueView = ((TextView)findViewById(2131689570));
    this.mDesiredPaceView = ((TextView)findViewById(2131689574));
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
  
  private boolean isMyServiceRunning(Class<?> paramClass)
  {
    Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
      if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  private void resetValues(boolean paramBoolean)
  {
    if ((this.mService != null) && (this.mIsRunning)) {
      this.mService.resetValues();
    }
    SharedPreferences.Editor localEditor;
    do
    {
      return;
      this.mStepValueView.setText("0");
      this.mPaceValueView.setText("0");
      this.mDistanceValueView.setText("0");
      this.mSpeedValueView.setText("0");
      this.mCaloriesValueView.setText("0");
      localEditor = getSharedPreferences("state", 0).edit();
    } while (!paramBoolean);
    localEditor.putInt("steps", 0);
    localEditor.putInt("pace", 0);
    localEditor.putFloat("distance", 0.0F);
    localEditor.putFloat("speed", 0.0F);
    localEditor.putFloat("calories", 0.0F);
    localEditor.commit();
  }
  
  private void savePaceSetting()
  {
    this.mPedometerSettings.savePaceOrSpeedSetting(this.mMaintain, this.mDesiredPaceOrSpeed);
  }
  
  private void setDesiredPaceOrSpeed(float paramFloat)
  {
    if (this.mService != null)
    {
      if (this.mMaintain != PedometerSettings.M_PACE) {
        break label27;
      }
      this.mService.setDesiredPace((int)paramFloat);
    }
    label27:
    while (this.mMaintain != PedometerSettings.M_SPEED) {
      return;
    }
    this.mService.setDesiredSpeed(paramFloat);
  }
  
  private void setViewData()
  {
    Utils.sqLiteDBHelper = new SQLiteDBHelper(this, "PedoDb", null, 1);
    Object localObject = getSharedPreferences("Pedometer", 0);
    SharedPreferences.Editor localEditor = ((SharedPreferences)localObject).edit();
    if (((SharedPreferences)localObject).getBoolean("isFirstTime", true))
    {
      localEditor.putBoolean("isFirstTime", false);
      localEditor.commit();
      Utils.sqLiteDBHelper.createSingleRecord();
    }
    localObject = Utils.sqLiteDBHelper.getCurrentValueFromDb(Utils.getCurrentDate());
    if (localObject != null)
    {
      this.mStepValueView.setText(((ResultModel)localObject).getSteps() + "");
      this.mPaceValueView.setText(((ResultModel)localObject).getPace() + "");
      this.mSpeedValueView.setText(((ResultModel)localObject).getSpeed() + "");
      this.mCaloriesValueView.setText(((ResultModel)localObject).getCalories() + "");
      this.mDistanceValueView.setText(((ResultModel)localObject).getDistance() + "");
      return;
    }
    resetValues(true);
  }
  
  private void setdefaultdata()
  {
    this.mIsMetric = this.mPedometerSettings.isMetric();
    Object localObject = (TextView)findViewById(2131689560);
    if (this.mIsMetric)
    {
      i = 2131230771;
      ((TextView)localObject).setText(getString(i));
      localObject = (TextView)findViewById(2131689566);
      if (!this.mIsMetric) {
        break label235;
      }
      i = 2131230772;
      label64:
      ((TextView)localObject).setText(getString(i));
      this.mMaintain = this.mPedometerSettings.getMaintainOption();
      localObject = (LinearLayout)findViewById(2131689571);
      if (this.mMaintain == PedometerSettings.M_NONE) {
        break label242;
      }
      i = 0;
      label107:
      ((LinearLayout)localObject).setVisibility(i);
      if (this.mMaintain != PedometerSettings.M_PACE) {
        break label248;
      }
      this.mMaintainInc = 5.0F;
      this.mDesiredPaceOrSpeed = this.mPedometerSettings.getDesiredPace();
      label141:
      ((Button)findViewById(2131689572)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Pedometer.access$024(Pedometer.this, Pedometer.this.mMaintainInc);
          Pedometer.access$002(Pedometer.this, Math.round(Pedometer.this.mDesiredPaceOrSpeed * 10.0F) / 10.0F);
          Pedometer.this.displayDesiredPaceOrSpeed();
          Pedometer.this.setDesiredPaceOrSpeed(Pedometer.this.mDesiredPaceOrSpeed);
        }
      });
      ((Button)findViewById(2131689575)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Pedometer.access$016(Pedometer.this, Pedometer.this.mMaintainInc);
          Pedometer.access$002(Pedometer.this, Math.round(Pedometer.this.mDesiredPaceOrSpeed * 10.0F) / 10.0F);
          Pedometer.this.displayDesiredPaceOrSpeed();
          Pedometer.this.setDesiredPaceOrSpeed(Pedometer.this.mDesiredPaceOrSpeed);
        }
      });
      if (this.mMaintain != PedometerSettings.M_NONE)
      {
        localObject = (TextView)findViewById(2131689573);
        if (this.mMaintain != PedometerSettings.M_PACE) {
          break label279;
        }
      }
    }
    label235:
    label242:
    label248:
    label279:
    for (int i = 2131230750;; i = 2131230751)
    {
      ((TextView)localObject).setText(i);
      setViewData();
      return;
      i = 2131230782;
      break;
      i = 2131230783;
      break label64;
      i = 8;
      break label107;
      if (this.mMaintain != PedometerSettings.M_SPEED) {
        break label141;
      }
      this.mDesiredPaceOrSpeed = this.mPedometerSettings.getDesiredSpeed();
      this.mMaintainInc = 0.1F;
      break label141;
    }
  }
  
  private void startStepService()
  {
    if (!this.mIsRunning)
    {
      Log.i("Pedometer", "[SERVICE] Start");
      this.mIsRunning = true;
      startService(new Intent(this, StepService.class));
    }
  }
  
  private void stopStepService()
  {
    Log.i("Pedometer", "[SERVICE] Stop");
    if (this.mService != null)
    {
      Log.i("Pedometer", "[SERVICE] stopService");
      stopService(new Intent(this, StepService.class));
    }
    this.mIsRunning = false;
  }
  
  private void unbindStepService()
  {
    Log.i("Pedometer", "[SERVICE] Unbind");
    unbindService(this.mConnection);
  }
  
  public List<ResultModel> getPedoValuesLastSevenDays()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.sqLiteDatabase = new SQLiteDBHelper(this, "PedoDb", null, 1).getReadableDatabase();
      Cursor localCursor = this.sqLiteDatabase.rawQuery("SELECT * FROM " + "Pedometer", null);
      int i = localCursor.getCount();
      Log.v("Pedometer", "database cursor count-=--->" + i);
      if (localCursor != null)
      {
        if (localCursor.moveToFirst()) {
          do
          {
            ResultModel localResultModel = new ResultModel();
            localResultModel.setDate(localCursor.getString(6));
            localResultModel.setSpeed(localCursor.getFloat(5));
            localResultModel.setPace(localCursor.getInt(4));
            localResultModel.setCalories(localCursor.getInt(3));
            localResultModel.setDistance(localCursor.getFloat(2));
            localResultModel.setSteps(localCursor.getInt(1));
            localResultModel.setId(localCursor.getInt(0));
            Log.d("steps", localCursor.getInt(1) + "");
            localArrayList.add(localResultModel);
          } while (localCursor.moveToNext());
        }
        localCursor.close();
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localArrayList;
    }
    finally
    {
      this.sqLiteDatabase.close();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Log.i("Pedometer", "[ACTIVITY] onCreate");
    super.onCreate(paramBundle);
    setContentView(2130968601);
    initilizeviews();
    this.mStepValue = 0;
    this.mPaceValue = 0;
    this.mUtils = Utils.getInstance();
    if (!isMyServiceRunning(BackGroundService.class)) {
      startService(new Intent(this, BackGroundService.class));
    }
    if ((AppController.isAppOnForeground(this)) && (!this.appInForeGround)) {
      this.appInForeGround = true;
    }
    this.fBtnStat = new FloatingActionButton.Builder(this).withDrawable(getResources().getDrawable(2130837580)).withButtonColor(getResources().getColor(2131623959)).withGravity(85).withMargins(0, 0, 8, 8).create();
    this.fBtnStat.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.v("pedometr", "db values---->" + Pedometer.this.getPedoValuesLastSevenDays());
        new ArrayList();
        paramAnonymousView = Pedometer.this.getPedoValuesLastSevenDays();
        Pedometer.arrSteps.clear();
        int i = 0;
        while (i < paramAnonymousView.size())
        {
          Log.v("pedometer", "--steps------>" + ((ResultModel)paramAnonymousView.get(i)).getSteps() + "--->" + ((ResultModel)paramAnonymousView.get(i)).getDate());
          Pedometer.arrSteps.add(Integer.valueOf(((ResultModel)paramAnonymousView.get(i)).getSteps()));
          i += 1;
        }
        paramAnonymousView = new BarGraph().getIntent(Pedometer.this);
        Pedometer.this.startActivity(paramAnonymousView);
        Pedometer.this.mGoogleHelper.SendScreenNameGA(Pedometer.this, GAConstants.GRAPH_SCREEN);
        Pedometer.this.mGoogleHelper.SendEventGA(Pedometer.this, GAConstants.GRAPH_FAB, GAConstants.PEDOMETER_SCREEN, "FAB CLICKED");
      }
    });
    InitGoogleAnalytics();
    this.mGoogleHelper.SendScreenNameGA(this, GAConstants.PEDOMETER_SCREEN);
  }
  
  protected void onDestroy()
  {
    Log.i("Pedometer", "[ACTIVITY] onDestroy");
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    default: 
      return false;
    case 1: 
      this.mGoogleHelper.SendEventGA(this, GAConstants.PAUSE_BUTTON, GAConstants.PEDOMETER_SCREEN, "PAUSE");
      paramMenuItem = new ResultModel();
      paramMenuItem.setCalories(this.mCaloriesValue);
      paramMenuItem.setDistance(this.mDistanceValue);
      paramMenuItem.setPace(this.mPaceValue);
      paramMenuItem.setSteps(this.mStepValue);
      paramMenuItem.setSpeed(this.mSpeedValue);
      Utils.sqLiteDBHelper.updateRecord(paramMenuItem, Utils.getCurrentDate());
      unbindStepService();
      stopStepService();
      return true;
    case 2: 
      this.mGoogleHelper.SendEventGA(this, GAConstants.RESUME_BUTTON, GAConstants.PEDOMETER_SCREEN, "RESUME");
      paramMenuItem = Utils.sqLiteDBHelper.getCurrentValueFromDb(Utils.getCurrentDate());
      if (paramMenuItem != null)
      {
        this.mStepValueView.setText(paramMenuItem.getSteps() + "");
        Log.v("Distance", "distance" + paramMenuItem.getDistance());
        this.mDistanceValueView.setText(paramMenuItem.getDistance() + "");
        this.mPaceValueView.setText(paramMenuItem.getPace() + "");
        this.mSpeedValueView.setText(paramMenuItem.getSpeed() + "");
        this.mCaloriesValueView.setText(paramMenuItem.getCalories() + "");
      }
      for (;;)
      {
        startStepService();
        bindStepService();
        return true;
        resetValues(true);
      }
    case 3: 
      this.mGoogleHelper.SendEventGA(this, GAConstants.RESET_BUTTON, GAConstants.PEDOMETER_SCREEN, "RESET");
      resetValues(true);
      return true;
    }
    this.mGoogleHelper.SendEventGA(this, GAConstants.EXIT_APP, GAConstants.PEDOMETER_SCREEN, "EXIT");
    resetValues(false);
    unbindStepService();
    stopStepService();
    this.mQuitting = true;
    finish();
    return true;
  }
  
  protected void onPause()
  {
    Log.i("Pedometer", "[ACTIVITY] onPause");
    if (this.mIsRunning) {
      unbindStepService();
    }
    if (this.mQuitting) {
      this.mPedometerSettings.saveServiceRunningWithNullTimestamp(this.mIsRunning);
    }
    for (;;)
    {
      super.onPause();
      savePaceSetting();
      return;
      this.mPedometerSettings.saveServiceRunningWithTimestamp(this.mIsRunning);
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    if (this.mIsRunning) {
      paramMenu.add(0, 1, 0, 2131230788).setIcon(17301539).setShortcut('1', 'p');
    }
    for (;;)
    {
      paramMenu.add(0, 3, 0, 2131230791).setIcon(17301560).setShortcut('2', 'r');
      paramMenu.add(0, 8, 0, 2131230797).setIcon(17301577).setShortcut('8', 's').setIntent(new Intent(this, Settings.class));
      paramMenu.add(0, 9, 0, 2131230790).setIcon(17301552).setShortcut('9', 'q');
      return true;
      paramMenu.add(0, 2, 0, 2131230792).setIcon(17301540).setShortcut('1', 'p');
    }
  }
  
  protected void onRestart()
  {
    Log.i("Pedometer", "[ACTIVITY] onRestart");
    ResultModel localResultModel = Utils.sqLiteDBHelper.getCurrentValueFromDb(Utils.getCurrentDate());
    if (localResultModel != null)
    {
      this.mStepValueView.setText(localResultModel.getSteps() + "");
      Log.v("Distance", "distance" + localResultModel.getDistance());
      this.mDistanceValueView.setText(localResultModel.getDistance() + "");
      this.mPaceValueView.setText(localResultModel.getPace() + "");
      this.mSpeedValueView.setText(localResultModel.getSpeed() + "");
      this.mCaloriesValueView.setText(localResultModel.getCalories() + "");
    }
    for (;;)
    {
      super.onDestroy();
      return;
      resetValues(true);
    }
  }
  
  protected void onResume()
  {
    Log.i("Pedometer", "[ACTIVITY] onResume");
    super.onResume();
    setViewData();
    this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);
    this.mPedometerSettings = new PedometerSettings(this.mSettings);
    this.mUtils.setSpeak(this.mSettings.getBoolean("speak", false));
    this.mIsRunning = this.mPedometerSettings.isServiceRunning();
    if ((!this.mIsRunning) && (this.mPedometerSettings.isNewStart()))
    {
      startStepService();
      bindStepService();
    }
    for (;;)
    {
      this.mPedometerSettings.clearServiceRunning();
      setdefaultdata();
      displayDesiredPaceOrSpeed();
      return;
      if (this.mIsRunning) {
        bindStepService();
      }
    }
  }
  
  protected void onStart()
  {
    Log.i("Pedometer", "[ACTIVITY] onStart");
    super.onStart();
  }
  
  protected void onStop()
  {
    Log.i("Pedometer", "[ACTIVITY] onStop");
    super.onStop();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Pedometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */