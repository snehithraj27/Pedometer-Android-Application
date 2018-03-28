package com.pedometer.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class StepLengthPreference
  extends EditMeasurementPreference
{
  public StepLengthPreference(Context paramContext)
  {
    super(paramContext);
  }
  
  public StepLengthPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public StepLengthPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void initPreferenceDetails()
  {
    this.mTitleResource = 2131230803;
    this.mMetricUnitsResource = 2131230749;
    this.mImperialUnitsResource = 2131230761;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/preferences/StepLengthPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */