package com.pedometer.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class BodyWeightPreference
  extends EditMeasurementPreference
{
  public BodyWeightPreference(Context paramContext)
  {
    super(paramContext);
  }
  
  public BodyWeightPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public BodyWeightPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void initPreferenceDetails()
  {
    this.mTitleResource = 2131230747;
    this.mMetricUnitsResource = 2131230770;
    this.mImperialUnitsResource = 2131230789;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/com/pedometer/preferences/BodyWeightPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */