package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AppCompatImageHelper
{
  private final AppCompatDrawableManager mDrawableManager;
  private final ImageView mView;
  
  public AppCompatImageHelper(ImageView paramImageView, AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    this.mView = paramImageView;
    this.mDrawableManager = paramAppCompatDrawableManager;
  }
  
  public void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, R.styleable.AppCompatImageView, paramInt, 0);
    try
    {
      Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(R.styleable.AppCompatImageView_android_src);
      if (localDrawable != null) {
        this.mView.setImageDrawable(localDrawable);
      }
      paramInt = paramAttributeSet.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
      if (paramInt != -1)
      {
        localDrawable = this.mDrawableManager.getDrawable(this.mView.getContext(), paramInt);
        if (localDrawable != null) {
          this.mView.setImageDrawable(localDrawable);
        }
      }
      localDrawable = this.mView.getDrawable();
      if (localDrawable != null) {
        DrawableUtils.fixDrawable(localDrawable);
      }
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  public void setImageResource(int paramInt)
  {
    if (paramInt != 0)
    {
      if (this.mDrawableManager != null) {}
      for (Drawable localDrawable = this.mDrawableManager.getDrawable(this.mView.getContext(), paramInt);; localDrawable = ContextCompat.getDrawable(this.mView.getContext(), paramInt))
      {
        if (localDrawable != null) {
          DrawableUtils.fixDrawable(localDrawable);
        }
        this.mView.setImageDrawable(localDrawable);
        return;
      }
    }
    this.mView.setImageDrawable(null);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/android/support/v7/widget/AppCompatImageHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */