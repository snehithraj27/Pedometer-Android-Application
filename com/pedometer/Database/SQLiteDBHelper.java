package com.pedometer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.pedometer.Utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper
  extends SQLiteOpenHelper
{
  public static final String DATABASE_NAME = "XmppDB";
  public static final int DATABASE_VERSION = 2;
  private static final String TableName = "Pedometer";
  private Context context;
  private String databaseName;
  
  public SQLiteDBHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    this.context = paramContext;
    this.databaseName = paramString;
  }
  
  public int createSingleRecord()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("steps", Integer.valueOf(0));
      localContentValues.put("distance", Integer.valueOf(0));
      localContentValues.put("calories", Integer.valueOf(0));
      localContentValues.put("pace", Integer.valueOf(0));
      localContentValues.put("speed", Integer.valueOf(0));
      localContentValues.put("date", Utils.getCurrentDate());
      localSQLiteDatabase.insertOrThrow("Pedometer", null, localContentValues);
      Log.d("Database", "Record Inserted");
      return 0;
    }
    catch (Exception localException)
    {
      Log.d("Database", localException.getMessage());
    }
    return 0;
  }
  
  public ResultModel getCurrentValueFromDb(String paramString)
  {
    localResultModel = new ResultModel();
    try
    {
      paramString = getReadableDatabase().rawQuery("SELECT  * FROM Pedometer WHERE date = '" + paramString + "'", null);
      if (paramString.moveToFirst()) {
        while (!paramString.isAfterLast())
        {
          localResultModel.setDate(paramString.getString(6));
          localResultModel.setSpeed(paramString.getFloat(5));
          localResultModel.setPace(paramString.getInt(4));
          localResultModel.setCalories(paramString.getInt(3));
          localResultModel.setDistance(paramString.getFloat(2));
          localResultModel.setSteps(paramString.getInt(1));
          Log.d("steps", paramString.getInt(1) + "");
          paramString.moveToNext();
        }
      }
      return localResultModel;
    }
    catch (Exception paramString)
    {
      Log.d("Database error", paramString.getMessage());
    }
  }
  
  public List<ResultModel> getPedoValuesLastSevenDays()
  {
    localArrayList = new ArrayList();
    try
    {
      Cursor localCursor = getReadableDatabase().rawQuery("SELECT * FROM Pedometer ORDER BY id DESC LIMIT 7", null);
      if (localCursor.moveToFirst()) {
        while (!localCursor.isAfterLast())
        {
          ResultModel localResultModel = new ResultModel();
          localResultModel.setDate(localCursor.getString(6));
          localResultModel.setSpeed(localCursor.getFloat(5));
          localResultModel.setPace(localCursor.getInt(4));
          localResultModel.setCalories(localCursor.getInt(3));
          localResultModel.setDistance(localCursor.getFloat(2));
          localResultModel.setSteps(localCursor.getInt(1));
          Log.d("steps", localCursor.getInt(1) + "");
          localArrayList.add(localResultModel);
          localCursor.moveToNext();
        }
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      Log.d("Database error", localException.getMessage());
    }
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Pedometer (id INTEGER PRIMARY KEY AUTOINCREMENT,steps int,distance float,calories int,pace int,speed float,date TEXT)");
    Log.d("Database", "Created");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public void updateRecord(ResultModel paramResultModel, String paramString)
  {
    if (verifyIfDateExists(paramString))
    {
      Log.v("sqlite", "new steps--->" + paramResultModel.getSteps() + "---id--->" + paramString);
      try
      {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("steps", Integer.valueOf(paramResultModel.getSteps()));
        localContentValues.put("distance", Float.valueOf(paramResultModel.getDistance()));
        localContentValues.put("calories", Integer.valueOf(paramResultModel.getCalories()));
        localContentValues.put("pace", Integer.valueOf(paramResultModel.getPace()));
        localContentValues.put("speed", Float.valueOf(paramResultModel.getSpeed()));
        localContentValues.put("date", paramString);
        try
        {
          if (localSQLiteDatabase.update("Pedometer", localContentValues, "date = '" + paramString + "'", null) == -1L)
          {
            Log.d("Database---->", "Record Updated false ");
            return;
          }
          Log.d("Database---->", "Record Updated true ");
          return;
        }
        catch (Exception paramResultModel)
        {
          paramResultModel = paramResultModel;
          paramResultModel.printStackTrace();
          return;
        }
        finally {}
        createSingleRecord();
      }
      catch (Exception paramResultModel)
      {
        Log.d("DBHelper", paramResultModel.getMessage());
        return;
      }
    }
  }
  
  public boolean verifyIfDateExists(String paramString)
  {
    try
    {
      paramString = getReadableDatabase().rawQuery("SELECT 1 FROM Pedometer WHERE date=?", new String[] { paramString });
      boolean bool = paramString.moveToFirst();
      paramString.close();
      return bool;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/Database/SQLiteDBHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */