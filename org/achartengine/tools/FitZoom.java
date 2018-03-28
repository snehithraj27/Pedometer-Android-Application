package org.achartengine.tools;

import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.RoundChart;
import org.achartengine.chart.XYChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

public class FitZoom
  extends AbstractTool
{
  public FitZoom(AbstractChart paramAbstractChart)
  {
    super(paramAbstractChart);
  }
  
  public void apply()
  {
    if ((this.mChart instanceof XYChart))
    {
      if (((XYChart)this.mChart).getDataset() == null) {}
      for (;;)
      {
        return;
        int k = this.mRenderer.getScalesCount();
        int i;
        if (this.mRenderer.isInitialRangeSet())
        {
          i = 0;
          while (i < k)
          {
            if (this.mRenderer.isInitialRangeSet(i)) {
              this.mRenderer.setRange(this.mRenderer.getInitialRange(i), i);
            }
            i += 1;
          }
        }
        else
        {
          localObject = ((XYChart)this.mChart).getDataset().getSeries();
          int m = localObject.length;
          if (m > 0)
          {
            i = 0;
            while (i < k)
            {
              double[] arrayOfDouble = new double[4];
              double[] tmp134_132 = arrayOfDouble;
              tmp134_132[0] = Double.MAX_VALUE;
              double[] tmp140_134 = tmp134_132;
              tmp140_134[1] = -1.7976931348623157E308D;
              double[] tmp146_140 = tmp140_134;
              tmp146_140[2] = Double.MAX_VALUE;
              double[] tmp152_146 = tmp146_140;
              tmp152_146[3] = -1.7976931348623157E308D;
              tmp152_146;
              int j = 0;
              while (j < m)
              {
                if (i == tmp134_132[j].getScaleNumber())
                {
                  tmp140_134[0] = Math.min(tmp140_134[0], tmp134_132[j].getMinX());
                  tmp140_134[1] = Math.max(tmp140_134[1], tmp134_132[j].getMaxX());
                  tmp140_134[2] = Math.min(tmp140_134[2], tmp134_132[j].getMinY());
                  tmp140_134[3] = Math.max(tmp140_134[3], tmp134_132[j].getMaxY());
                }
                j += 1;
              }
              double d1 = Math.abs(tmp140_134[1] - tmp140_134[0]) / 40.0D;
              double d2 = Math.abs(tmp140_134[3] - tmp140_134[2]) / 40.0D;
              this.mRenderer.setRange(new double[] { tmp140_134[0] - d1, tmp140_134[1] + d1, tmp140_134[2] - d2, tmp140_134[3] + d2 }, i);
              i += 1;
            }
          }
        }
      }
    }
    double[] tmp134_132 = ((RoundChart)this.mChart).getRenderer();
    ((DefaultRenderer)tmp134_132).setScale(((DefaultRenderer)tmp134_132).getOriginalScale());
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/org/achartengine/tools/FitZoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */