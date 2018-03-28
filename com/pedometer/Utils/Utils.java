package com.pedometer.Utils;

import android.app.Service;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.format.Time;
import android.util.Log;
import com.pedometer.Database.SQLiteDBHelper;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils
  implements TextToSpeech.OnInitListener
{
  private static final String TAG = "Utils";
  private static Utils instance = null;
  public static SQLiteDBHelper sqLiteDBHelper;
  private Service mService;
  private boolean mSpeak = false;
  private boolean mSpeakingEngineAvailable = false;
  private TextToSpeech mTts;
  
  public static long currentTimeInMillis()
  {
    Time localTime = new Time();
    localTime.setToNow();
    return localTime.toMillis(false);
  }
  
  public static String getCurrentDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    System.out.println("Current time => " + localCalendar.getTime());
    return new SimpleDateFormat("dd-MMM-yyyy").format(localCalendar.getTime());
  }
  
  public static Utils getInstance()
  {
    if (instance == null) {
      instance = new Utils();
    }
    return instance;
  }
  
  public void ding() {}
  
  public void initTTS()
  {
    Log.i("Utils", "Initializing TextToSpeech...");
    this.mTts = new TextToSpeech(this.mService, this);
  }
  
  public boolean isSpeakingEnabled()
  {
    return this.mSpeak;
  }
  
  public boolean isSpeakingNow()
  {
    return this.mTts.isSpeaking();
  }
  
  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      paramInt = this.mTts.setLanguage(Locale.US);
      if ((paramInt == -1) || (paramInt == -2))
      {
        Log.e("Utils", "Language is not available.");
        return;
      }
      Log.i("Utils", "TextToSpeech Initialized.");
      this.mSpeakingEngineAvailable = true;
      return;
    }
    Log.e("Utils", "Could not initialize TextToSpeech.");
  }
  
  public void say(String paramString)
  {
    if ((this.mSpeak) && (this.mSpeakingEngineAvailable)) {
      this.mTts.speak(paramString, 1, null);
    }
  }
  
  public void setService(Service paramService)
  {
    this.mService = paramService;
  }
  
  public void setSpeak(boolean paramBoolean)
  {
    this.mSpeak = paramBoolean;
  }
  
  public void shutdownTTS()
  {
    Log.i("Utils", "Shutting Down TextToSpeech...");
    this.mSpeakingEngineAvailable = false;
    this.mTts.shutdown();
    Log.i("Utils", "TextToSpeech Shut Down.");
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Utils/Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */