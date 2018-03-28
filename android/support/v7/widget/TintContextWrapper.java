package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

class TintContextWrapper
  extends ContextWrapper
{
  private static final ArrayList<WeakReference<TintContextWrapper>> sCache = new ArrayList();
  private Resources mResources;
  
  private TintContextWrapper(Context paramContext)
  {
    super(paramContext);
  }
  
  public static Context wrap(@NonNull Context paramContext)
  {
    if (!(paramContext instanceof TintContextWrapper))
    {
      int i = 0;
      int j = sCache.size();
      while (i < j)
      {
        Object localObject = (WeakReference)sCache.get(i);
        if (localObject != null) {}
        for (localObject = (TintContextWrapper)((WeakReference)localObject).get(); (localObject != null) && (((TintContextWrapper)localObject).getBaseContext() == paramContext); localObject = null) {
          return (Context)localObject;
        }
        i += 1;
      }
      paramContext = new TintContextWrapper(paramContext);
      sCache.add(new WeakReference(paramContext));
      return paramContext;
    }
    return paramContext;
  }
  
  public Resources getResources()
  {
    if (this.mResources == null) {
      this.mResources = new TintResources(this, super.getResources());
    }
    return this.mResources;
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/android/support/v7/widget/TintContextWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */