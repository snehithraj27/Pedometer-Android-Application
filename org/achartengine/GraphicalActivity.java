package org.achartengine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.achartengine.chart.AbstractChart;

public class GraphicalActivity
  extends Activity
{
  private AbstractChart mChart;
  private GraphicalView mView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    this.mChart = ((AbstractChart)paramBundle.getSerializable("chart"));
    this.mView = new GraphicalView(this, this.mChart);
    paramBundle = paramBundle.getString("title");
    if (paramBundle == null) {
      requestWindowFeature(1);
    }
    for (;;)
    {
      setContentView(this.mView);
      return;
      if (paramBundle.length() > 0) {
        setTitle(paramBundle);
      }
    }
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/GraphicalActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */