package com.pedometer.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

public abstract class EditMeasurementPreference
  extends EditTextPreference
{
  protected int mImperialUnitsResource;
  boolean mIsMetric;
  protected int mMetricUnitsResource;
  protected int mTitleResource;
  
  public EditMeasurementPreference(Context paramContext)
  {
    super(paramContext);
    initPreferenceDetails();
  }
  
  public EditMeasurementPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initPreferenceDetails();
  }
  
  public EditMeasurementPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initPreferenceDetails();
  }
  
  protected abstract void initPreferenceDetails();
  
  protected void onAddEditTextToDialogView(View paramView, EditText paramEditText)
  {
    paramEditText.setRawInputType(8194);
    super.onAddEditTextToDialogView(paramView, paramEditText);
  }
  
  public void onDialogClosed(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      Float.valueOf(getEditText().getText().toString());
      super.onDialogClosed(paramBoolean);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      showDialog(null);
    }
  }
  
  protected void showDialog(Bundle paramBundle)
  {
    this.mIsMetric = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("units", "imperial").equals("metric");
    StringBuilder localStringBuilder = new StringBuilder().append(getContext().getString(this.mTitleResource)).append(" (");
    Context localContext = getContext();
    int i;
    if (this.mIsMetric) {
      i = this.mMetricUnitsResource;
    }
    for (;;)
    {
      setDialogTitle(localContext.getString(i) + ")");
      try
      {
        Float.valueOf(getText());
        super.showDialog(paramBundle);
        return;
        i = this.mImperialUnitsResource;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          setText("20");
        }
      }
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/preferences/EditMeasurementPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */